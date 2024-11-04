package com.sathya.rest.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sathya.rest.models.Hospital;

import jakarta.transaction.Transactional;

@Repository
public interface HospitalRepository extends JpaRepository <Hospital,Long>{

	Optional<Hospital> findByspecialization(String specialization);
	@Query("SELECT CASE WHEN COUNT(h) > 0 THEN true ELSE false END FROM Hospital h WHERE h.specialization = :specialization")
	boolean existsBySpecialization(String specialization);
@Transactional
	void deleteBySpecialization(String specialization);

}

