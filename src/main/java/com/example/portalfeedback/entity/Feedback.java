package com.example.portalfeedback.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="pf_feedback")
public class Feedback {
    private String id;
    private String userid;
    private String message;
    private int rating;

}
