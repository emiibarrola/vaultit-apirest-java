package com.vaultit.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.vaultit.rest.model.Location;
import com.vaultit.rest.repository.LocationRepository;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class LocationController {

    @Autowired
    LocationRepository  countRepository;

    @GetMapping("/Location")
    public List<Location> getAllEmployees() {
        return countRepository.findAll();
    }

    @PostMapping("/Location")
    public Location createEmployee(@Valid @RequestBody Location emp) {
        return countRepository.save(emp);
    }

    @GetMapping("/Location/{id}")
    public Location getEmployeeById(@PathVariable(value = "id") Long empId) {
        return countRepository.findById(empId).get();
                //.orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));
    }

    @PutMapping("/Location/{id}")
    public Location updateEmployee(@PathVariable(value = "id") Long employeeId,
                                           @Valid @RequestBody Location employeeDetails) {

    	Location emp = countRepository.findById(employeeId).get();
                //.orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));

        emp.setStreet_adress(employeeDetails.getStreet_adress());
        emp.setLocation_id(employeeDetails.getLocation_id());
        emp.setPostal_code(employeeDetails.getPostal_code());
        emp.setCity(employeeDetails.getCity());
        emp.setState_province(employeeDetails.getState_province());
        emp.setCountry_id(employeeDetails.getCountry_id());


        Location updatedEmp = countRepository.save(emp);
        return updatedEmp;
    }

    @DeleteMapping("/Location/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable(value = "id") Long empId) {
    	Location emp = countRepository.findById(empId).get();
               // .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));

        countRepository.delete(emp);

        return ResponseEntity.ok().build();
    }
}
