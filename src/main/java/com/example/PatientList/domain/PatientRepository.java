package com.example.PatientList.domain;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PatientRepository extends PagingAndSortingRepository<Patient, Long> {
	List<Patient> findByName(String name);
	List<Patient> findByDoctor(Doctor doctor);
}
