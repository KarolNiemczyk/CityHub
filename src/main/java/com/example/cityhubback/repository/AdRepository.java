package com.example.cityhubback.repository;

import com.example.cityhubback.model.Ad;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface AdRepository extends MongoRepository<Ad, String> {
    List<Ad> findByActiveTrue();
    List<Ad> findByCategoryAndActiveTrue(String category);
    List<Ad> findByLocationContainingIgnoreCaseAndActiveTrue(String location);
}