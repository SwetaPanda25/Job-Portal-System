package com.example.JobPortal.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "application")
public class Application {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private Job job;

    @ManyToOne
    private User applicant;

    @Enumerated(EnumType.STRING)
    private  Status status = Status.APPLIED;

    public enum Status{
        APPLIED, REVIEWED, SELECTED, REJECTED;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getApplicant() {
        return applicant;
    }

    public void setApplicant(User applicant) {
        this.applicant = applicant;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
