package com.vaultit.rest.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(name = "departments")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(allowGetters = true)
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long department_id;

    private String department_name;
    
    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "manager_id",referencedColumnName= "employee_id")
    private Employee manager_id;
    
    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "location_id")
    private Location location_id;

	public Long getDepartment_id() {
		return department_id;
	}

	public void setDepartment_id(Long department_id) {
		this.department_id = department_id;
	}

	public String getDepartment_name() {
		return department_name;
	}

	public void setDepartment_name(String department_name) {
		this.department_name = department_name;
	}

	public Employee getManager_id() {
		return manager_id;
	}

	public void setManager_id(Employee manager_id) {
		this.manager_id = manager_id;
	}

	public Location getLocation_id() {
		return location_id;
	}

	public void setLocation_id(Location location_id) {
		this.location_id = location_id;
	}
   
}
