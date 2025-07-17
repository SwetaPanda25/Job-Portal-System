package com.example.JobPortal.controller;

import com.example.JobPortal.entity.Application;
import com.example.JobPortal.entity.Job;
import com.example.JobPortal.entity.User;
import com.example.JobPortal.repository.ApplicationRepository;
import com.example.JobPortal.repository.JobRepository;
import com.example.JobPortal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/applicant")
public class ApplicantController {
    @Autowired private JobRepository jobRepo;
    @Autowired private UserRepository userRepo;
    @Autowired private ApplicationRepository applicationRepo;

    // Search & list available jobs
    @GetMapping("/jobs")
    public String viewAvailableJobs(@RequestParam(value = "keyword", required = false) String keyword, Model model) {
        List<Job> jobs = (keyword != null && !keyword.isEmpty())
                ? jobRepo.findByTitleContainingIgnoreCase(keyword)
                : jobRepo.findAll();

        model.addAttribute("jobs", jobs);
        return "applicant/jobs";
    }

    // View applied jobs
    @GetMapping("/applications")
    public String viewAppliedJobs(Authentication auth, Model model) {
        User applicant = userRepo.findByUsername(auth.getName());
        List<Application> applications = applicationRepo.findByApplicant(applicant);
        model.addAttribute("applications", applications);
        return "applicant/my-applications";
    }

    @GetMapping("/applicant/applications")
    public String viewMyApplications(Authentication auth, Model model) {
        User user = userRepo.findByUsername(auth.getName());
        List<Application> applications = applicationRepo.findByApplicant(user);
        model.addAttribute("applications", applications);
        return "applicant/my-applications";
    }

}
