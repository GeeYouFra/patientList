package com.example.PatientList.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class Patient {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String name;
	private String ward;
	private String diagnosis;
	private String dateAdmission;
	
	@ManyToOne
    @JoinColumn(name = "doctorid")
    private Doctor doctor;
	
	public Patient() {}

	public Patient(String name, String ward, String diagnosis, String dateAdmission, Doctor doctor) {
		super();
		this.name = name;
		this.ward = ward;
		this.diagnosis = diagnosis;
		this.dateAdmission = dateAdmission;
		this.doctor = doctor;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getWard() {
		return ward;
	}
	public void setWard(String ward) {
		this.ward = ward;
	}
	public String getDiagnosis() {
		return diagnosis;
	}
	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}
	public String getDateAdmission() {
		return dateAdmission;
	}
	public void setDateAdmission(String dateAdmission) {
		this.dateAdmission = dateAdmission;
	}
	
	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	
	}

	@Override
	public String toString() {
		return "Patient [id=" + id + ", name=" + name + ", ward=" + ward + ", diagnosis=" + diagnosis + ", dateAdmission=" + dateAdmission + "]";
	}
	
}
