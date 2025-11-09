package com.example.cityhubback.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "jobs")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobAd {
    @Id
    private String id;
    private String title;
    private String description;
    private String type; // "praca", "usluga"
    private Double rate; // stawka za 30 min
    private String userId;
    private String location;
    private List<String> availableDays; // "MONDAY", "TUESDAY"
    private String startTime; // "09:00"
    private String endTime;   // "17:00"

    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();

    @Builder.Default
    private boolean active = true;
}