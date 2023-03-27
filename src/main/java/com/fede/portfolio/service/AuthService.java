package com.fede.portfolio.service;

import com.fede.portfolio.dto.request.LoginRequest;
import com.fede.portfolio.dto.request.SignupRequest;
import com.fede.portfolio.dto.response.MessageResponse;
import com.fede.portfolio.dto.response.UserInfoResponse;
import com.fede.portfolio.exceptions.BlogException;
import com.fede.portfolio.exceptions.TokenRefreshException;
import com.fede.portfolio.model.*;
import com.fede.portfolio.repository.*;
import com.fede.portfolio.security.jwt.JwtUtils;
import com.fede.portfolio.security.services.RefreshTokenService;
import com.fede.portfolio.security.services.UserDetailsImpl;
import com.fede.portfolio.security.services.UserDetailsServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.OK;

@Service
@AllArgsConstructor
@Transactional
@Slf4j
public class AuthService {

    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final VerificationTokenRepository verificationTokenRepository;
    private final MailService mailService;
    private final AuthenticationManager authenticationManager;
    private final RefreshTokenService refreshTokenService;
    private final RefreshTokenRepository refreshTokenRepository;
    private final RoleRepository roleRepository;
    private final UserDetailsServiceImpl userDetailsService;
    private final RInfo ipersonaRepository;

    static final String accountVerificationUri = "/api/v1/auth/accountVerification/";

    public ResponseEntity<String> signup(SignupRequest signupRequest, HttpServletRequest request) {


        if (userRepository.existsByUsername(signupRequest.getUsername())) {
            return ResponseEntity.badRequest().body("Error: Username is already taken!");
        }


        if (userRepository.existsByEmail(signupRequest.getEmail())) {
           return ResponseEntity.badRequest().body("Error: Email is already in use!");

        }
        User user = new User();
        user.setUsername(signupRequest.getUsername());
        user.setEmail(signupRequest.getEmail());
        user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
        user.setCreatedAt(LocalDateTime.now());
        user.setEnabled(false);

        Set<String> strRoles = signupRequest.getRole();
        List<Role> roles = new ArrayList<>();


        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin" -> {
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);
                    }
                    case "mod" -> {
                        Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);
                    }
                    default -> {
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                    }
                }
            });
        }
        user.setRoles(roles);
        userRepository.save(user);

        String domainUrl = getDomain(request);
        String token = generateVerificationToken(user);
        mailService.sendMail(new NotificationEmail("Activate your Account.",
                user.getEmail(), "You received this email because you signed up to sudobarre's portfolio." +
                " If this wasn't you, ignore this mail. Please click on the following link to activate your account: "
                + domainUrl
                + accountVerificationUri + token));



        return new ResponseEntity<>("User Registration Successful. Please check your email inbox to verify your account.",
                OK);
    }

    public String getDomain(HttpServletRequest request) {
        URL url = null;
        try {
            url = new URL(request.getRequestURL().toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        assert url != null;
        return url.getProtocol() + "://" + url.getHost();
    }

    /*
    //TODO
    public void forgotPassword(String email){
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User name not found - " + email));
        String token = generateVerificationToken(user);
        mailService.sendMail(new NotificationEmail("Password reset",
                user.getEmail(), "You requested a password change. " +
                "Please click on the below url to change your password: " + backendDomainUrl +
                accountVerificationUri + token));
    }

     */

    @Transactional(readOnly = true)
    public User getCurrentUser() {
        //Jwt principal = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByUsername(userName)
                .orElseThrow(() -> new UsernameNotFoundException("User name not found - " + userName));
    }

    private void fetchUserAndEnable(VerificationToken verificationToken) {
        String username = verificationToken.getUser().getUsername();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new BlogException("User not found with name - " + username));
        user.setEnabled(true);

        //create new info with just the username
        Info info = new Info();
        info.setUser(user);
        info.setNombre(user.getUsername());
        info.setImg("");
        info.setDescripcion("");
        info.setApellido("");
        log.info("info: " + info);
        ipersonaRepository.save(info);
        log.info("Info saved for user: " + user.getUsername());
    }

    private String generateVerificationToken(User user) {
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setUser(user);

        verificationTokenRepository.save(verificationToken);
        return token;
    }

    public void verifyAccount(String token) {
        Optional<VerificationToken> verificationToken = verificationTokenRepository.findByToken(token);
        fetchUserAndEnable(verificationToken.orElseThrow(() -> new BlogException("Invalid Token")));
    }

    public ResponseEntity<?> login(LoginRequest loginRequest) {

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);

        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)//item -> item.getAuthority()
                .collect(Collectors.toList());

        RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getId());

        ResponseCookie jwtRefreshCookie = jwtUtils.generateRefreshJwtCookie(refreshToken.getToken());

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                .header(HttpHeaders.SET_COOKIE, jwtRefreshCookie.toString())
                .body(new UserInfoResponse(userDetails.getId(),
                        userDetails.getUsername(),
                        userDetails.getEmail(),
                        roles));
    }

    public ResponseEntity<?> refreshToken(HttpServletRequest request) {
        String refreshToken = jwtUtils.getJwtRefreshFromCookies(request);

        if ((refreshToken != null) && (refreshToken.length() > 0)) {
            return refreshTokenService.findByToken(refreshToken)
                    .map(refreshTokenService::verifyExpiration)
                    .map(RefreshToken::getUser)
                    .map(user -> {
                        ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(user);

                        return ResponseEntity.ok()
                                .header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                                .body(new MessageResponse("Token is refreshed successfully!"));
                    })
                    .orElseThrow(() -> new TokenRefreshException(refreshToken,
                            "Refresh token is not in database!"));
        }

        return ResponseEntity.badRequest().body(new MessageResponse("Refresh Token is empty!"));
    }


    public boolean isLoggedIn() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return !(authentication instanceof AnonymousAuthenticationToken) && authentication.isAuthenticated();
    }

    public ResponseEntity<?> logout() {
        Object principle = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!(principle.toString().equals("anonymousUser"))) {
            Long userId = ((UserDetailsImpl) principle).getId();
            refreshTokenService.deleteByUserId(userId);
        }

        ResponseCookie jwtCookie = jwtUtils.getCleanJwtCookie();
        ResponseCookie jwtRefreshCookie = jwtUtils.getCleanJwtRefreshCookie();

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                .header(HttpHeaders.SET_COOKIE, jwtRefreshCookie.toString())
                .body(new MessageResponse("You've been signed out!"));
    }
}
