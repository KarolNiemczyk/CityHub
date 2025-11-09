package com.example.cityhubback.security;

import com.example.cityhubback.model.User;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequestScope
public class AuthContext {
    private User currentUser;

    public void setCurrentUser(User user) { this.currentUser = user; }
    public User getCurrentUser() { return currentUser; }
    public boolean isAuthenticated() { return currentUser != null; }
    public boolean hasRole(String role) {
        return currentUser != null && currentUser.getRole().name().equals(role);
    }
    public String getUserId() { return currentUser != null ? currentUser.getId() : null; }
}