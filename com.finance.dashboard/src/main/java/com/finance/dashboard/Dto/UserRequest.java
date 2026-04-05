package com.finance.dashboard.Dto;

import com.finance.dashboard.Entity.Role;

public class UserRequest {

    private String name;
    private String email;
    private Role role; // Now uses enum
    private String password;

    //Default Constructor
    public UserRequest(){};

    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }

    public String getPassword() {

        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRequest(String name, String email, Role role) {
        this.name = name;
        this.email = email;
        this.role = role;
    }
}