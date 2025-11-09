package com.example.cityhubback.dto.booking;

import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class BookingRequest {
    @NotBlank private String jobAdId;
    @Future private LocalDateTime startTime; // np. 2025-04-05T10:00
}