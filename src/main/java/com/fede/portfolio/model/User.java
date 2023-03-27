package com.fede.portfolio.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.*;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;

@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long userId;

    @NotBlank(message = "Username is required")
    @Size(max=20)
    private String username;

    @NotBlank(message = "Password is required")
    @Size(max=120)
    private String password;

    @Email
    @NotEmpty(message = "Email is required")
    @Size(max=100)
    private String email;

    private LocalDateTime createdAt;

    private boolean enabled;

    private String imgUrl;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(	name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles = new ArrayList<>();

//TODO: not implemented feature yet
    @JoinTable(name = "USER_FRIENDS", joinColumns = {
            @JoinColumn(name = "ADDING_USER", referencedColumnName = "userId", nullable =   false)}, inverseJoinColumns = {
            @JoinColumn(name = "ADDED_USER", referencedColumnName = "userId", nullable = false)})
    @ManyToMany(fetch=LAZY)
    private Collection<User> friends;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Educacion> educacionList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Experiencia> experienciaList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HyS> hySList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Info> infoList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Proyecto> proyectoList = new ArrayList<>();

}
