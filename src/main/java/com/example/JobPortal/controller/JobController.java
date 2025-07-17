package com.example.JobPortal.controller;

import com.example.JobPortal.entity.Job;
import com.example.JobPortal.entity.User;
import com.example.JobPortal.repository.JobRepository;
import com.example.JobPortal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class JobController {

    @Autowired private JobRepository jobRepo;
    @Autowired private UserRepository userRepo;

    //Show all job applicants
    @GetMapping("/applicant/search")
    public  String viewJobs(@RequestParam(required = false) String keyword, Model model){
        List<Job> jobs = (keyword == null || keyword.isEmpty()) ? jobRepo.findAll() : jobRepo.findByTitleContainingIgnoreCase(keyword);
        model.addAttribute("jobs", jobs);
        return "applicant/jobs";
    }

    //Employer - view posted jobs
    @GetMapping("/employer/jobs")
    public String employerJobs(Authentication auth, Model model){
        User employer = userRepo.findByUsername(auth.getName());
        model.addAttribute("jobs", jobRepo.findAll().stream().filter(job -> job.getPostedBy().getId() == (employer.getId())).toList());
        return "employer/jobs";
    }

    //Employer can fill form for new job post
    @GetMapping("/employer/job/new")
    public String showPostJobForm(Model model){
        model.addAttribute("job", new Job());
        return "employer/post-job";
    }

    @PostMapping("/employer/job/new")
    public String postJob(Job job, Authentication auth){
        User user = userRepo.findByUsername(auth.getName());
        job.setPostedBy(user);
        jobRepo.save(job);
        return "redirect:/employer/jobs";
    }
}
