package com.example.cityhubback.service;

import com.example.cityhubback.dto.ad.JobAdDto;
import com.example.cityhubback.model.JobAd;
import com.example.cityhubback.repository.JobAdRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JobAdService {

    private final JobAdRepository jobAdRepository;

    public List<JobAd> getAll(String type, String location) {
        if (type != null && location != null) {
            return jobAdRepository.findByTypeAndActiveTrue(type);
        } else if (location != null) {
            return jobAdRepository.findByLocationContainingIgnoreCaseAndActiveTrue(location);
        } else if (type != null) {
            return jobAdRepository.findByTypeAndActiveTrue(type);
        } else {
            return jobAdRepository.findByActiveTrue();
        }
    }

    public JobAd getById(String id) {
        return jobAdRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ogłoszenie nie istnieje"));
    }

    public JobAd create(JobAdDto dto, String userId) {
        JobAd jobAd = JobAd.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .type(dto.getType())
                .rate(dto.getRate())
                .location(dto.getLocation())
                .availableDays(dto.getAvailableDays())
                .startTime(dto.getStartTime())
                .endTime(dto.getEndTime())
                .userId(userId)
                .build();
        return jobAdRepository.save(jobAd);
    }

    public JobAd update(String id, JobAdDto dto, String userId) {
        JobAd jobAd = getById(id);
        if (!jobAd.getUserId().equals(userId)) {
            throw new RuntimeException("Brak uprawnień");
        }
        jobAd.setTitle(dto.getTitle());
        jobAd.setDescription(dto.getDescription());
        jobAd.setType(dto.getType());
        jobAd.setRate(dto.getRate());
        jobAd.setLocation(dto.getLocation());
        jobAd.setAvailableDays(dto.getAvailableDays());
        jobAd.setStartTime(dto.getStartTime());
        jobAd.setEndTime(dto.getEndTime());
        return jobAdRepository.save(jobAd);
    }

    public void delete(String id, String userId, boolean isAdmin) {
        JobAd jobAd = getById(id);
        if (!jobAd.getUserId().equals(userId) && !isAdmin) {
            throw new RuntimeException("Brak uprawnień");
        }
        jobAdRepository.delete(jobAd);
    }
}