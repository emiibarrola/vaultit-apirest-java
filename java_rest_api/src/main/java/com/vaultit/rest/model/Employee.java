package com.vaultit.rest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import java.util.Date;

@Entity
@Table(name = "employees")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(allowGetters = true)
public class Employee {
	/*
	 EMPLOYEE_ID NUMBER (6) NOT NULL ,
	 FIRST_NAME VARCHAR2 (20 BYTE) ,
	 LAST_NAME VARCHAR2 (25 BYTE) NOT NULL ,
	 EMAIL VARCHAR2 (25 BYTE) NOT NULL ,
	 PHONE_NUMBER VARCHAR2 (20 BYTE) ,
	 HIRE_DATE DATE NOT NULL ,
	 JOB_ID VARCHAR2 (10 BYTE) NOT NULL ,
	 SALARY NUMBER (8,2) ,
	 COMMISSION_PCT NUMBER (2,2) ,
	 MANAGER_ID NUMBER (6) ,
	 DEPARTMENT_ID NUMBER (4) 
	 */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private Long employeeid;

    @Column(name = "first_name", nullable = false)
    private String firstname;
    
    @Column(name = "last_name", nullable = false)
    private String lastname;
    
    @NotBlank
    private String email;
    
    @Column(name = "phone_number", nullable = false)
    private String phonenumber;
    
    @Column(name = "hire_date", nullable = false)
    private Date hiredate;
    
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "job_id", nullable = false)
    private Job jobid;

	@Column(name="salary", columnDefinition="Decimal(8,2)")
    private double salary;

    @Column(name="comission_pct", columnDefinition="Decimal(2,2)")
    private double comissionpct;
    
    @ManyToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="manager_id")
	private Employee managerid;

	@ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "department_id")
    private Department departmentid;
	
	public Long getEmployee_id() {
		return employeeid;
	}

	public void setEmployee_id(Long employee_id) {
		this.employeeid = employee_id;
	}

	public String getFirst_name() {
		return firstname;
	}

	public void setFirst_name(String first_name) {
		this.firstname = first_name;
	}

	public String getLast_name() {
		return lastname;
	}

	public void setLast_name(String last_name) {
		this.lastname = last_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone_number() {
		return phonenumber;
	}

	public void setPhone_number(String phone_number) {
		this.phonenumber = phone_number;
	}

	public Date getHire_date() {
		return hiredate;
	}

	public void setHire_date(Date hire_date) {
		this.hiredate = hire_date;
	}

	public Job getJob_id() {
		return jobid;
	}

	public void setJob_id(Job job_id) {
		this.jobid = job_id;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public double getComission_pct() {
		return comissionpct;
	}

	public void setComission_pct(double comission_pct) {
		this.comissionpct = comission_pct;
	}

	public Employee getManager_id() {
		return managerid;
	}

	public void setManager_id(Employee manager_id) {
		this.managerid = manager_id;
	}
	
	public Department getDepartment_id() {
		return departmentid;
	}

	public void setDepartment_id(Department department_id) {
		this.departmentid = department_id;
	}

}
