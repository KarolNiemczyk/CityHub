package com.example.cityhubback.dto.ad;

import jakarta.validation.constraints.*;
import lombok.Data;
import java.util.List;

@Data
public class JobAdDto {
    @NotBlank private String title;
    @NotBlank private String description;
    @NotBlank private String type;
    @Positive private Double rate;
    @NotBlank private String location;
    @NotEmpty private List<String> availableDays;
    @NotBlank private String startTime;
    @NotBlank private String endTime;
}