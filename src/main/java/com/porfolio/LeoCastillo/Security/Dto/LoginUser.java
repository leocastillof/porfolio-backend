package com.porfolio.LeoCastillo.Security.Dto;

import jakarta.validation.constraints.NotBlank;

public class LoginUser {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    
    // Getter & Setter

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}
