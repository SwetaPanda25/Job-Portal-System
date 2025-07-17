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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class ApplicationController {
    @Autowired private ApplicationRepository applicationRepo;
    @Autowired private JobRepository jobRepo;
    @Autowired private UserRepository userRepo;

    //Applying for jobs
    @PostMapping("/applicant/apply/{jobId}")
    public String applyForJob(@PathVariable Long jobId, Authentication auth, RedirectAttributes redirectAttributes){
        Job job = jobRepo.findById(jobId).orElse(null);
        User applicant = userRepo.findByUsername(auth.getName());

        Application application = new Application();
        application.setApplicant(applicant);
        application.setJob(job);
        applicationRepo.save(application);

        redirectAttributes.addFlashAttribute("success", "Successfully applied for the job!");
        return "redirect:/applicant/jobs";
    }

    @GetMapping("/applicant/my-applications")
    public  String viewMyApplications(Authentication auth, Model model){
        User user = userRepo.findByUsername(auth.getName());
        model.addAttribute("applications", applicationRepo.findByApplicant(user));
        return "applicant/my-applications";
    }

    @GetMapping("/employer/applications/{jobId}")
    public String viewJobApplications(@PathVariable Long jobId, Model model){
        Job job = jobRepo.findById(jobId).orElse(null);
        model.addAttribute("applications", applicationRepo.findByJob(job));
        return "employer/job-applications";
    }

    @GetMapping("/employer/applications")
    public String viewEmployerApplications(Authentication auth, Model model) {
        User employer = userRepo.findByUsername(auth.getName());
        List<Job> jobsPosted = jobRepo.findByPostedBy(employer);
        List<Application> applications = applicationRepo.findByJobIn(jobsPosted);
        model.addAttribute("applications", applications);
        return "employer/job-applications";
    }

    // Change application status
    @PostMapping("/employer/applications/update/{applicationId}")
    public String updateStatus(@PathVariable Long applicationId,
                               @RequestParam("status") Application.Status status) {
        Application application = applicationRepo.findById(applicationId).orElse(null);
        if (application != null) {
            application.setStatus(status);
            applicationRepo.save(application);
        }
        return "redirect:/employer/applications/" + application.getJob().getId();
    }


}
