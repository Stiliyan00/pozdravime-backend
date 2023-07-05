package com.tutorial.employeemanagmentbackend.controller;

import com.tutorial.employeemanagmentbackend.exceptions.BusinessException;
import com.tutorial.employeemanagmentbackend.exceptions.ErrorCode;
import com.tutorial.employeemanagmentbackend.model.GreetingRequest;
import com.tutorial.employeemanagmentbackend.service.RequestService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/greetings")
@CrossOrigin("*")
public class GreetingsController {

    @Autowired
    private RequestService requestService; //we are bringing in Employee Service instance

    @PostMapping
    public ResponseEntity createGreeting(@RequestBody @Validated GreetingRequest greetingRequest){
        checkIfValid(greetingRequest);
        return requestService.saveRequest(greetingRequest);
    }

    private void checkIfValid(GreetingRequest greetingRequest) {
        if (greetingRequest.getEmail() == null) {
            throw new BusinessException(ErrorCode.EMAIL_NULL, "Email is null.");
        }
        if (greetingRequest.getGreetingText() == null) {
            throw new BusinessException(ErrorCode.GREETING_TEXT_NULL, "Greeting text is null.");
        }
        if (greetingRequest.getActor() == null) {
            throw new BusinessException(ErrorCode.ACTOR_NULL, "Actor is null.");
        }
    }
}
