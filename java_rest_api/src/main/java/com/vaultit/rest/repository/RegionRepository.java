package com.vaultit.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vaultit.rest.model.Region;

@Repository
public interface RegionRepository extends JpaRepository<Region, Long> {

}
