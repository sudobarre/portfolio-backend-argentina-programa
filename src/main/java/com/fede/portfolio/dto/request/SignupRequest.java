package com.fede.portfolio.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignupRequest {
    @NotBlank
    @Size(min = 1)
    private String username;

    @NotBlank
    @Size(max = 100)
    @Email
    private String email;

    private List<String> role;

    @NotBlank
    @Size(min = 4, max = 40)
    private String password;
}
