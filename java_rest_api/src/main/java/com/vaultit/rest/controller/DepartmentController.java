package com.vaultit.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.vaultit.rest.model.Department;
import com.vaultit.rest.model.Employee;
import com.vaultit.rest.repository.DepartmentRepository;
import com.vaultit.rest.repository.EmployeeRepository;

import javax.validation.Valid;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class DepartmentController {

    @Autowired
    DepartmentRepository countRepository;
    
    @Autowired
    DepartmentRepository depRepository;
    
    @Autowired
    EmployeeRepository empRepository;

    @GetMapping("/Department")
    public List<Department> getAllEmployees() {
        return countRepository.findAll();
    }

    @PostMapping("/Department")
    public Department createEmployee(@Valid @RequestBody Department dep) {
    	List<Department> deps = depRepository.findAll();
    	List<Department> deps_loc_id = deps.stream()
    		    .filter(p -> (p.getLocation_id()==null?0:p.getLocation_id().getLocation_id()) == (dep.getLocation_id()==null?0:dep.getLocation_id().getLocation_id()))
    		    .collect(Collectors.toList());
    	List<Employee> emps_loc_id = new ArrayList<Employee>();
    	for (Department dep_loc_id : deps_loc_id) {
    		emps_loc_id.addAll(empRepository.findBydepartmentid(dep_loc_id));
		}
    	double sum = 0;
    	int count = 0;
    	for (Employee emp_loc_id : emps_loc_id) {
    		sum += emp_loc_id.getSalary();
    		count++;
		}
    	double prom = count == 0 ? 0: sum /count;
    	LocalDate localDate = LocalDate.now();
    	if(localDate.getDayOfMonth()<=14 && prom <= 1000)
    		return countRepository.save(dep);
    	if(localDate.getDayOfMonth()>14 && prom <= 1500)
    		return countRepository.save(dep);
    	return null;
    }

    @GetMapping("/Department/{id}")
    public Department getEmployeeById(@PathVariable(value = "id") Long empId) {
        return countRepository.findById(empId).get();
                //.orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));
    }

    @PutMapping("/Department/{id}")
    public Department updateEmployee(@PathVariable(value = "id") Long employeeId,
                                           @Valid @RequestBody Department employeeDetails) {

    	Department emp = countRepository.findById(employeeId).get();
                //.orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));

        emp.setDepartment_name(employeeDetails.getDepartment_name());
        emp.setManager_id(employeeDetails.getManager_id());
        emp.setLocation_id(employeeDetails.getLocation_id());

        Department updatedEmp = countRepository.save(emp);
        return updatedEmp;
    }

    @DeleteMapping("/Department/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable(value = "id") Long empId) {
    	Department emp = countRepository.findById(empId).get();
               // .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));

        countRepository.delete(emp);

        return ResponseEntity.ok().build();
    }
}
