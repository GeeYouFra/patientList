package com.example.PatientList.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface DoctorRepository extends CrudRepository<Doctor, Long> {

	List<Doctor> findByName(String name);
	
}
