package com.vaultit.rest.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vaultit.rest.model.Department;
import com.vaultit.rest.model.Employee;
import com.vaultit.rest.model.Job;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	List<Employee> findByjobid(Job jobid, Pageable pageable);
	List<Employee> findBymanagerid(Employee managerid, Pageable pageable);
	List<Employee> findBylastname(String lastname, Pageable pageable);
	List<Employee> findBydepartmentid(Department departmentid);
	
}
