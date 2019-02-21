package com.vaultit.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vaultit.rest.model.Job;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> {

}
