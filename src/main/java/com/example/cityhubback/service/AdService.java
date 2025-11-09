package com.example.cityhubback.service;

import com.example.cityhubback.dto.ad.AdDto;
import com.example.cityhubback.model.Ad;
import com.example.cityhubback.repository.AdRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdService {

    private final AdRepository adRepository;

    public List<Ad> getAll(String category, String location) {
        if (category != null && location != null) {
            return adRepository.findByCategoryAndActiveTrue(category);
        } else if (location != null) {
            return adRepository.findByLocationContainingIgnoreCaseAndActiveTrue(location);
        } else {
            return adRepository.findByActiveTrue();
        }
    }

    public Ad getById(String id) {
        return adRepository.findById(id).orElseThrow(() -> new RuntimeException("Nie znaleziono"));
    }

    public Ad create(AdDto dto, String userId) {
        Ad ad = Ad.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .category(dto.getCategory())
                .price(dto.getPrice())
                .location(dto.getLocation())
                .userId(userId)
                .build();
        return adRepository.save(ad);
    }

    public Ad update(String id, AdDto dto, String userId) {
        Ad ad = getById(id);
        if (!ad.getUserId().equals(userId)) throw new RuntimeException("Brak uprawnień");
        ad.setTitle(dto.getTitle());
        ad.setDescription(dto.getDescription());
        ad.setCategory(dto.getCategory());
        ad.setPrice(dto.getPrice());
        ad.setLocation(dto.getLocation());
        return adRepository.save(ad);
    }

    public void delete(String id, String userId, boolean isAdmin) {
        Ad ad = getById(id);
        if (!ad.getUserId().equals(userId) && !isAdmin) throw new RuntimeException("Brak uprawnień");
        adRepository.delete(ad);
    }
}