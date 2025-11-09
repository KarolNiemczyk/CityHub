package com.example.cityhubback.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Document(collection = "ads")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ad {
    @Id
    private String id;
    private String title;
    private String description;
    private String category;
    private Double price;
    private String userId;
    private String location;
    private LocalDateTime createdAt = LocalDateTime.now();
    private boolean active = true;
}