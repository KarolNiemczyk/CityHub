package com.example.cityhubback.controller.ad;

import com.example.cityhubback.dto.ad.AdDto;
import com.example.cityhubback.model.Ad;
import com.example.cityhubback.security.AuthContext;
import com.example.cityhubback.service.AdService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/ads")
@RequiredArgsConstructor
public class AdController {

    private final AdService adService;
    private final AuthContext authContext;

    @GetMapping
    public List<Ad> getAll(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String location) {
        return adService.getAll(category, location);
    }

    @GetMapping("/{id}")
    public Ad getById(@PathVariable String id) {
        return adService.getById(id);
    }

    @PostMapping
    public ResponseEntity<Ad> create(@Valid @RequestBody AdDto dto) {
        if (!authContext.isAuthenticated()) return ResponseEntity.status(401).build();
        return ResponseEntity.ok(adService.create(dto, authContext.getUserId()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ad> update(@PathVariable String id, @Valid @RequestBody AdDto dto) {
        if (!authContext.isAuthenticated()) return ResponseEntity.status(401).build();
        return ResponseEntity.ok(adService.update(id, dto, authContext.getUserId()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        if (!authContext.isAuthenticated()) return ResponseEntity.status(401).build();
        boolean isAdmin = authContext.hasRole("ADMIN");
        adService.delete(id, authContext.getUserId(), isAdmin);
        return ResponseEntity.ok().build();
    }
}