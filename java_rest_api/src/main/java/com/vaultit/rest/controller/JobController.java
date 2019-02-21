package com.vaultit.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.vaultit.rest.model.Job;
import com.vaultit.rest.repository.JobRepository;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class JobController {

    @Autowired
    JobRepository countRepository;

    @GetMapping("/Job")
    public List<Job> getAllEmployees() {
        return countRepository.findAll();
    }

    @PostMapping("/Job")
    public Job createEmployee(@Valid @RequestBody Job emp) {
        return countRepository.save(emp);
    }

    @GetMapping("/Job/{id}")
    public Job getEmployeeById(@PathVariable(value = "id") Long empId) {
        return countRepository.findById(empId).get();
                //.orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));
    }

    @PutMapping("/Job/{id}")
    public Job updateEmployee(@PathVariable(value = "id") Long employeeId,
                                           @Valid @RequestBody Job employeeDetails) {

    	Job emp = countRepository.findById(employeeId).get();
                //.orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));

        emp.setJob_title(employeeDetails.getJob_title());
        emp.setMin_salary(employeeDetails.getMin_salary());
        emp.setMax_salary(employeeDetails.getMax_salary());

        Job updatedEmp = countRepository.save(emp);
        return updatedEmp;
    }

    @DeleteMapping("/Job/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable(value = "id") Long empId) {
    	Job emp = countRepository.findById(empId).get();
               // .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));

        countRepository.delete(emp);

        return ResponseEntity.ok().build();
    }
}
