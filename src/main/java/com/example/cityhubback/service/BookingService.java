package com.example.cityhubback.service;

import com.example.cityhubback.dto.booking.BookingRequest;
import com.example.cityhubback.model.Booking;
import com.example.cityhubback.model.BookingStatus;
import com.example.cityhubback.model.JobAd;
import com.example.cityhubback.repository.BookingRepository;
import com.example.cityhubback.repository.JobAdRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;
    private final JobAdRepository jobAdRepository;

    public Booking create(BookingRequest req, String userId) {
        JobAd jobAd = jobAdRepository.findById(req.getJobAdId())
                .orElseThrow(() -> new RuntimeException("Usługa nie istnieje"));

        LocalDateTime start = req.getStartTime();
        LocalDateTime end = start.plusMinutes(30);

        if (bookingRepository.findByJobAdIdAndStartTimeBetween(
                req.getJobAdId(), start.minusMinutes(1), end.plusMinutes(1)).size() > 0) {
            throw new RuntimeException("Termin zajęty");
        }

        Booking booking = Booking.builder()
                .jobAdId(req.getJobAdId())
                .userId(userId)
                .providerId(jobAd.getUserId())
                .startTime(start)
                .endTime(end)
                .build();

        return bookingRepository.save(booking);
    }

    public List<Booking> getMyBookings(String userId) {
        return bookingRepository.findByUserId(userId);
    }

    public List<Booking> getProviderBookings(String providerId) {
        return bookingRepository.findByProviderId(providerId);
    }

    public Booking updateStatus(String id, BookingStatus status, String userId, boolean isAdmin) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rezerwacja nie istnieje"));

        if (!booking.getProviderId().equals(userId) && !isAdmin) {
            throw new RuntimeException("Brak uprawnień");
        }

        booking.setStatus(status);
        return bookingRepository.save(booking);
    }

    public void cancel(String id, String userId, boolean isAdmin) {
        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rezerwacja nie istnieje"));

        if (!booking.getUserId().equals(userId) && !isAdmin) {
            throw new RuntimeException("Brak uprawnień");
        }

        booking.setStatus(BookingStatus.CANCELLED);
        bookingRepository.save(booking);
    }
}