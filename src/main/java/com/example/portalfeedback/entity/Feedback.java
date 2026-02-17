package com.example.portalfeedback.entity;

import com.example.portalfeedback.helper.Generate;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="pf_feedback")
public class Feedback {
    @Id
    private String id;
    @Transient
    private Generate generate=new Generate();
//    private String userid;
    private String message;
    private int rating;
    private boolean is_anonymous;
    private String status;

    @Column(updatable = false)
    private LocalDateTime createon;
    private LocalDateTime updateon;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User user;

    public Feedback(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

//    public String getUserid() {
//        return userid;
//    }
//
//    public void setUserid(String userid) {
//        this.userid = userid;
//    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public boolean isIs_anonymous() {
        return is_anonymous;
    }

    public void setIs_anonymous(boolean is_anonymous) {
        this.is_anonymous = is_anonymous;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreateon() {
        return createon;
    }

    public void setCreateon(LocalDateTime createon) {
        this.createon = createon;
    }

    public LocalDateTime getUpdateon() {
        return updateon;
    }

    public void setUpdateon(LocalDateTime updateon) {
        this.updateon = updateon;
    }
}
