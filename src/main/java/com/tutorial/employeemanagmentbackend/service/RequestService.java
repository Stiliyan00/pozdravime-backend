package com.tutorial.employeemanagmentbackend.service;

import com.tutorial.employeemanagmentbackend.model.GreetingRequest;
import com.tutorial.employeemanagmentbackend.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
public class RequestService  {

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private MailService mailService;

    public ResponseEntity<?> saveRequest(GreetingRequest greetingRequest) {
        requestRepository.save(greetingRequest);
        mailService.sendEmail(greetingRequest);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
