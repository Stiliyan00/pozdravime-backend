package com.tutorial.employeemanagmentbackend.repository;

import com.tutorial.employeemanagmentbackend.model.GreetingRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestRepository extends JpaRepository<GreetingRequest, Integer> {
}
