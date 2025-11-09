package com.example.cityhubback.controller.admin;

import com.example.cityhubback.security.AuthContext;
import com.example.cityhubback.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;
    private final AuthContext authContext;

    private void requireAdmin() {
        if (!authContext.hasRole("ADMIN")) throw new RuntimeException("Brak uprawnień");
    }

    @GetMapping("/stats")
    public Map<String, Long> getStats() {
        requireAdmin();
        return adminService.getStats();
    }

    @DeleteMapping("/ads/items/{id}")
    public ResponseEntity<?> deleteItemAd(@PathVariable String id) {
        requireAdmin();
        adminService.forceDeleteAd(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/ads/jobs/{id}")
    public ResponseEntity<?> deleteJobAd(@PathVariable String id) {
        requireAdmin();
        adminService.forceDeleteJobAd(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable String id) {
        requireAdmin();
        adminService.forceDeleteUser(id);
        return ResponseEntity.ok().build();
    }
}