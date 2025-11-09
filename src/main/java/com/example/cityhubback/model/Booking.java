package com.example.cityhubback.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Document(collection = "bookings")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Booking {
    @Id
    private String id;
    private String jobAdId;
    private String userId;       // klient
    private String providerId;   // wykonawca
    private LocalDateTime startTime; // np. 2025-04-05T10:00
    private LocalDateTime endTime;   // +30 min

    @Builder.Default
    private BookingStatus status = BookingStatus.PENDING;

    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();
}