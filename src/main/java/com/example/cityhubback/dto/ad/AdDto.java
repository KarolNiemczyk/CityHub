package com.example.cityhubback.dto.ad;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class AdDto {
    @NotBlank private String title;
    @NotBlank private String description;
    @NotBlank private String category;
    private Double price;
    @NotBlank private String location;
}