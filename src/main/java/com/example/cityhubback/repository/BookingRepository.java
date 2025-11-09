package com.example.cityhubback.repository;

import com.example.cityhubback.model.Booking;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.time.LocalDateTime;
import java.util.List;

public interface BookingRepository extends MongoRepository<Booking, String> {
    List<Booking> findByUserId(String userId);
    List<Booking> findByProviderId(String providerId);
    List<Booking> findByJobAdIdAndStartTimeBetween(String jobAdId, LocalDateTime start, LocalDateTime end);
}