package com.vaultit.rest.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vaultit.rest.model.Employee;
import com.vaultit.rest.model.Job_history;

@Repository
public interface Job_historyRepository extends JpaRepository<Job_history, Long> {

	List<Job_history> findByemployeeId(Employee employeeId);
}
