package com.vaultit.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.vaultit.rest.model.Employee;
import com.vaultit.rest.model.Job;
import com.vaultit.rest.repository.EmployeeRepository;
import com.vaultit.rest.repository.Job_historyRepository;
import com.vaultit.rest.views.EmployeeInterface;
import com.vaultit.rest.views.EmployeeJob_history;

import javax.validation.Valid;

import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    @Autowired
    EmployeeRepository empRepository;
    
    @Autowired
    Job_historyRepository jobhRepository;
    
    @GetMapping("/Employee_hierarchy")
    public List<Employee> getAllEmployees() {
        return empRepository.findAll();
    }
    
    @RequestMapping (value = "/Employee_hierarchy/job_id/{job_id}", method = RequestMethod.GET)
    public List<Employee> getAllEmployeesByJobId(
    		@PathVariable(value = "job_id") Job job_id, final Pageable pageable) {
    	List<Employee> lemp = empRepository.findByjobid(job_id,pageable);
    	Collections.sort(lemp, new Comparator<Employee>() {
    		  public int compare(Employee o1, Employee o2) {
    		      if (o1.getHire_date() == null || o2.getHire_date() == null)
    		        return 0;
    		      return o1.getHire_date().compareTo(o2.getHire_date());
    		  }
    		});
    	return lemp;
    }
    
    @RequestMapping (value = "/Employee_hierarchy/manager_id/{manager_id}", method = RequestMethod.GET)
    public List<Employee> getAllEmployeesByManagerId(
    		@PathVariable(value = "manager_id") Employee manager_id, final Pageable pageable) {
    	List<Employee> lemp = empRepository.findBymanagerid(manager_id,pageable);
    	Collections.sort(lemp, new Comparator<Employee>() {
  		  public int compare(Employee o1, Employee o2) {
  		      if (o1.getHire_date() == null || o2.getHire_date() == null)
  		        return 0;
  		      return o1.getHire_date().compareTo(o2.getHire_date());
  		  }
  		});
    	return lemp;
    }
    
    @RequestMapping (value = "/Employee_hierarchy/last_name/{last_name}", method = RequestMethod.GET)
    public List<Employee> getAllEmployeesByLastName(
    		@PathVariable(value = "last_name") String last_name, final Pageable pageable) {
    	List<Employee> lemp = empRepository.findBylastname(last_name,pageable);
    	Collections.sort(lemp, new Comparator<Employee>() {
  		  public int compare(Employee o1, Employee o2) {
  		      if (o1.getHire_date() == null || o2.getHire_date() == null)
  		        return 0;
  		      return o1.getHire_date().compareTo(o2.getHire_date());
  		  }
  		});
    	return lemp;
    }

    @GetMapping("/Employee_hierarchy/{id}")
    public EmployeeJob_history getEmployeeById(@PathVariable(value = "id") Long empId) {
    	EmployeeJob_history ejh = new EmployeeJob_history();
    	ejh.setEmployee(empRepository.findById(empId).get());
    	ejh.setJobhistoy(jobhRepository.findByemployeeId(ejh.getEmployee()));
        return ejh;
                //.orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));
    }
    
    @GetMapping("/Employee/{id}")
    public EmployeeInterface getEmployee(@PathVariable(value = "id") Long empId) {
    	Employee emp = empRepository.findById(empId).get();
    	EmployeeInterface ei = new EmployeeInterface();
    	ei.setEmployee_id(emp.getEmployee_id());
    	ei.setFirst_name(emp.getFirst_name());
    	ei.setLast_name(emp.getLast_name());
    	ei.setEmail(emp.getEmail());
    	ei.setPhone_number(emp.getPhone_number());
    	ei.setHire_date(emp.getHire_date());
    	ei.setJob_id(emp.getJob_id()==null?null:emp.getJob_id().getJob_id());
    	ei.setSalary(emp.getSalary());
    	ei.setComissionpct(emp.getComission_pct());
    	ei.setManager_id(emp.getManager_id()==null?null:emp.getManager_id().getEmployee_id());
    	ei.setDepartment_id(emp.getDepartment_id()==null?null:emp.getDepartment_id().getDepartment_id());
        return ei;
    }
    
    @PostMapping("/Employee")
    public Employee createEmployee(@Valid @RequestBody Employee emp) {
        return empRepository.save(emp);
    }

    @PutMapping("/Employee/{id}")
    public Employee updateEmployee(@PathVariable(value = "id") Long employeeId,
                                           @Valid @RequestBody Employee employeeDetails) {

        Employee emp = empRepository.findById(employeeId).get();
                //.orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));

        emp.setFirst_name(employeeDetails.getFirst_name());
        emp.setLast_name(employeeDetails.getLast_name());
        emp.setEmail(employeeDetails.getEmail());
        emp.setPhone_number(employeeDetails.getPhone_number());
        emp.setHire_date(employeeDetails.getHire_date());
        emp.setJob_id(employeeDetails.getJob_id());
        emp.setSalary(employeeDetails.getSalary());
        emp.setComission_pct(employeeDetails.getComission_pct());
        emp.setManager_id(employeeDetails.getManager_id());
        emp.setDepartment_id(employeeDetails.getDepartment_id());

        Employee updatedEmp = empRepository.save(emp);
        return updatedEmp;
    }

    @DeleteMapping("/Employee/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable(value = "id") Long empId) {
        Employee emp = empRepository.findById(empId).get();
               // .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));

        empRepository.delete(emp);

        return ResponseEntity.ok().build();
    }
}
