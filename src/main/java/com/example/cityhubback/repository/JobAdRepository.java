package com.example.cityhubback.repository;

import com.example.cityhubback.model.JobAd;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface JobAdRepository extends MongoRepository<JobAd, String> {
    List<JobAd> findByActiveTrue();
    List<JobAd> findByTypeAndActiveTrue(String type);
    List<JobAd> findByLocationContainingIgnoreCaseAndActiveTrue(String location);
}