package com.example.cityhubback.controller.ad;

import com.example.cityhubback.dto.ad.JobAdDto;
import com.example.cityhubback.model.JobAd;
import com.example.cityhubback.security.AuthContext;
import com.example.cityhubback.service.JobAdService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/jobs")
@RequiredArgsConstructor
public class JobAdController {

    private final JobAdService jobAdService;
    private final AuthContext authContext;

    @GetMapping
    public List<JobAd> getAll(
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String location) {
        return jobAdService.getAll(type, location);
    }

    @GetMapping("/{id}")
    public JobAd getById(@PathVariable String id) {
        return jobAdService.getById(id);
    }

    @PostMapping
    public ResponseEntity<JobAd> create(@Valid @RequestBody JobAdDto dto) {
        if (!authContext.isAuthenticated()) return ResponseEntity.status(401).build();
        return ResponseEntity.ok(jobAdService.create(dto, authContext.getUserId()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<JobAd> update(@PathVariable String id, @Valid @RequestBody JobAdDto dto) {
        if (!authContext.isAuthenticated()) return ResponseEntity.status(401).build();
        return ResponseEntity.ok(jobAdService.update(id, dto, authContext.getUserId()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        if (!authContext.isAuthenticated()) return ResponseEntity.status(401).build();
        boolean isAdmin = authContext.hasRole("ADMIN");
        jobAdService.delete(id, authContext.getUserId(), isAdmin);
        return ResponseEntity.ok().build();
    }
}