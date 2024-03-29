package com.porfolio.LeoCastillo.Security.Dto;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author leoca
 */
public class NewUser {
    private String name;
    private String username;
    private String email;
    private String password;
    private Set<String> roles = new HashSet<>();
    
    // Getter & Setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }
    
}
