package com.vaultit.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.vaultit.rest.model.Region;
import com.vaultit.rest.repository.RegionRepository;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class RegionController {

    @Autowired
    RegionRepository countRepository;

    @GetMapping("/Region")
    public List<Region> getAllEmployees() {
        return countRepository.findAll();
    }

    @PostMapping("/Region")
    public Region createEmployee(@Valid @RequestBody Region emp) {
        return countRepository.save(emp);
    }

    @GetMapping("/Region/{id}")
    public Region getEmployeeById(@PathVariable(value = "id") Long empId) {
        return countRepository.findById(empId).get();
                //.orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));
    }

    @PutMapping("/Region/{id}")
    public Region updateEmployee(@PathVariable(value = "id") Long employeeId,
                                           @Valid @RequestBody Region employeeDetails) {

    	Region emp = countRepository.findById(employeeId).get();
                //.orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));

        emp.setRegion_name(employeeDetails.getRegion_name());

        Region updatedEmp = countRepository.save(emp);
        return updatedEmp;
    }

    @DeleteMapping("/Region/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable(value = "id") Long empId) {
    	Region emp = countRepository.findById(empId).get();
               // .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));

        countRepository.delete(emp);

        return ResponseEntity.ok().build();
    }
}
