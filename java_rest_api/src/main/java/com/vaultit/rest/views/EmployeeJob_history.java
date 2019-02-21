package com.vaultit.rest.views;
import java.util.List;

import com.vaultit.rest.model.Employee;
import com.vaultit.rest.model.Job_history;

public class EmployeeJob_history {
	private Employee employee;
	private List<Job_history> jobhistoy;
	public List<Job_history> getJobhistoy() {
		return jobhistoy;
	}
	public void setJobhistoy(List<Job_history> jobhistoy) {
		this.jobhistoy = jobhistoy;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
}
