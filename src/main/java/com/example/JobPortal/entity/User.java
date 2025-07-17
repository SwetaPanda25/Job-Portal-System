package com.example.JobPortal.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "user")
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private  String username;
    private  String password;

    @Enumerated(EnumType.STRING) // By default JPA stores enum as integer
    private Role role;

    public enum Role{
        EMPLOYER, APPLICANT // Role can only have 1 of these values, whether the user is employer or applicant
    }

    public User(){}
    public User(String username, String password, Role role){
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Role getRole() {
        return role;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public  String toString(){
        return "User { id= "+id+", username= "+username+", role= "+role+"}";
    }
}
