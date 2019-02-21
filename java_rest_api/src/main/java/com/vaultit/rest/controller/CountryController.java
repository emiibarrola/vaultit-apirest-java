package com.vaultit.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.vaultit.rest.model.Country;
import com.vaultit.rest.repository.CountryRepository;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CountryController {

    @Autowired
    CountryRepository countRepository;

    @GetMapping("/Country")
    public List<Country> getAllEmployees() {
        return countRepository.findAll();
    }

    @PostMapping("/Country")
    public Country createEmployee(@Valid @RequestBody Country emp) {
        return countRepository.save(emp);
    }

    @GetMapping("/Country/{id}")
    public Country getEmployeeById(@PathVariable(value = "id") Long empId) {
        return countRepository.findById(empId).get();
                //.orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));
    }

    @PutMapping("/Country/{id}")
    public Country updateEmployee(@PathVariable(value = "id") Long employeeId,
                                           @Valid @RequestBody Country employeeDetails) {

        Country emp = countRepository.findById(employeeId).get();
                //.orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));

        emp.setCountry_name(employeeDetails.getCountry_name());
        emp.setRegion_id(employeeDetails.getRegion_id());


        Country updatedEmp = countRepository.save(emp);
        return updatedEmp;
    }

    @DeleteMapping("/Country/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable(value = "id") Long empId) {
        Country emp = countRepository.findById(empId).get();
               // .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));

        countRepository.delete(emp);

        return ResponseEntity.ok().build();
    }
}
