package com.example.portalfeedback.entity;

import com.example.portalfeedback.helper.Generate;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

import java.time.LocalDateTime;

@Table(name = "pf_user")
@Entity
public class User {

    @Id
    private String id;
    private String username;

    private String emailid;
    private String password;

    private LocalDateTime createon;
    private LocalDateTime updateon;

    private String role;
    private long otpGenerationTime;
    private String otp;
    @Transient
    @JsonIgnore
    private Generate generate=new Generate();

    public User() {
        this.id = new Generate().generateId();
    }

}
