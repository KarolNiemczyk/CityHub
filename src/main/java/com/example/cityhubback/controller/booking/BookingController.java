package com.example.cityhubback.controller.booking;

import com.example.cityhubback.dto.booking.BookingRequest;
import com.example.cityhubback.model.Booking;
import com.example.cityhubback.model.BookingStatus;
import com.example.cityhubback.security.AuthContext;
import com.example.cityhubback.service.BookingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;
    private final AuthContext authContext;

    @PostMapping
    public ResponseEntity<Booking> create(@Valid @RequestBody BookingRequest req) {
        if (!authContext.isAuthenticated()) return ResponseEntity.status(401).build();
        return ResponseEntity.ok(bookingService.create(req, authContext.getUserId()));
    }

    @GetMapping
    public List<Booking> getMyBookings() {
        if (!authContext.isAuthenticated()) return List.of();
        return bookingService.getMyBookings(authContext.getUserId());
    }

    @GetMapping("/provider/{providerId}")
    public List<Booking> getProviderBookings(@PathVariable String providerId) {
        if (!authContext.isAuthenticated()) return List.of();
        if (!authContext.getUserId().equals(providerId) && !authContext.hasRole("ADMIN")) {
            return List.of();
        }
        return bookingService.getProviderBookings(providerId);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Booking> updateStatus(
            @PathVariable String id,
            @RequestParam BookingStatus status) {
        if (!authContext.isAuthenticated()) return ResponseEntity.status(401).build();
        return ResponseEntity.ok(bookingService.updateStatus(
                id, status, authContext.getUserId(), authContext.hasRole("ADMIN")));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> cancel(@PathVariable String id) {
        if (!authContext.isAuthenticated()) return ResponseEntity.status(401).build();
        bookingService.cancel(id, authContext.getUserId(), authContext.hasRole("ADMIN"));
        return ResponseEntity.ok().build();
    }
}