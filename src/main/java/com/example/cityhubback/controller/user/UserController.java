package com.example.cityhubback.controller.user;

import com.example.cityhubback.model.User;
import com.example.cityhubback.security.AuthContext;
import com.example.cityhubback.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final AuthContext authContext;

    @GetMapping
    public List<User> getAll() {
        if (!authContext.hasRole("ADMIN")) return List.of();
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public User getById(@PathVariable String id) {
        if (!authContext.isAuthenticated()) throw new RuntimeException("Brak autoryzacji");
        return userService.getById(id, authContext.getUserId(), authContext.hasRole("ADMIN"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable String id, @RequestBody User updated) {
        if (!authContext.isAuthenticated()) return ResponseEntity.status(401).build();
        return ResponseEntity.ok(userService.update(id, updated, authContext.getUserId(), authContext.hasRole("ADMIN")));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        if (!authContext.hasRole("ADMIN")) return ResponseEntity.status(403).build();
        userService.delete(id, true);
        return ResponseEntity.ok().build();
    }
}