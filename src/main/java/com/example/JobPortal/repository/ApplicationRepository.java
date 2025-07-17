package com.example.JobPortal.repository;

import com.example.JobPortal.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
    List<Application> findByApplicant(User user);
    List<Application> findByJob(Job job);
    List<Application> findByJobIn(List<Job> jobsPosted);
}
