package com.example.cityhubback.service;

import com.example.cityhubback.repository.AdRepository;
import com.example.cityhubback.repository.BookingRepository;
import com.example.cityhubback.repository.UserRepository;
import com.example.cityhubback.repository.JobAdRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final UserRepository userRepository;
    private final AdRepository adRepository;
    private final JobAdRepository jobAdRepository;
    private final BookingRepository bookingRepository;

    public Map<String, Long> getStats() {
        return Map.of(
                "users", userRepository.count(),
                "ads", adRepository.count(),
                "jobAds", jobAdRepository.count(),
                "bookings", bookingRepository.count()
        );
    }

    public void forceDeleteAd(String adId) {
        adRepository.deleteById(adId);
    }

    public void forceDeleteUser(String userId) {
        userRepository.deleteById(userId);
    }
    public void forceDeleteJobAd(String id) {
        jobAdRepository.deleteById(id);
    }
}