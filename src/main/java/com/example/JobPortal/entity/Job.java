package com.example.JobPortal.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "job")
public class Job {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;
    private String description;
    private String location;

    @ManyToOne
    private User postedBy;

    public Job(){

    }

    public Job(String title, String description, String location){
        this.title = title;
        this.description = description;
        this.location = location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setPostedBy(User postedBy) {
        this.postedBy = postedBy;
    }

    public User getPostedBy() {
        return postedBy;
    }
}
