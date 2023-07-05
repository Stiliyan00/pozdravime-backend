package com.tutorial.employeemanagmentbackend.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class GreetingRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String email;
    private String greetingText;
    private String greetingType;
    private String actor;
    private Timestamp timestamp;
    private String status;

    public void setId(int id) {
        this.id = id;
    }

    public String getGreetingType() {
        return greetingType;
    }

    public String getActor() {
        return actor;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setGreetingText(String greetingText) {
        this.greetingText = greetingText;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getGreetingText() {
        return greetingText;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public GreetingRequest() {
    }

    public GreetingRequest(int id, String email, String greetingText, Timestamp timestamp) {
        this.id = id;
        this.email = email;
        this.greetingText = greetingText;
        this.timestamp = timestamp;
    }
}
