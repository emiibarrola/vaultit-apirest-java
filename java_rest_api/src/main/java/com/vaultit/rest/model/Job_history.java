package com.vaultit.rest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "job_history")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(allowGetters = true)
public class Job_history {
	
	@Id
	@Column(columnDefinition = "BINARY(16)")
	private UUID uuid;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "employee_id")
    private Employee employeeId;
    
    private Date start_date;
    
    private Date end_date;
    
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "job_id")
    private Job job_id;
    
    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "department_id")
    private Department department_id;

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	public Employee getEmployee_id() {
		return employeeId;
	}

	public void setEmployee_id(Employee employee_id) {
		this.employeeId = employee_id;
	}

	public Date getStart_date() {
		return start_date;
	}

	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}

	public Date getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}

	public Job getJob_id() {
		return job_id;
	}

	public void setJob_id(Job job_id) {
		this.job_id = job_id;
	}

	public Department getDepartment_id() {
		return department_id;
	}

	public void setDepartment_id(Department department_id) {
		this.department_id = department_id;
	}


	
}
