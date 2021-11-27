package com.example.PatientList.web;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.example.PatientList.domain.DoctorRepository;
import com.example.PatientList.domain.Patient;
import com.example.PatientList.domain.PatientRepository;

@Controller
public class PatientListController {
	@Autowired
	private PatientRepository repository;

	@Autowired
	private DoctorRepository docrepository;

	@RequestMapping(value = { "/", "/patientList" })
	public String showIndex(Model model) {
		model.addAttribute("patients", repository.findAll());
		return "patientList";
	}

	@RequestMapping(value = "/patients", method = RequestMethod.GET)
	public @ResponseBody List<Patient> patientListRest() {
		return (List<Patient>) repository.findAll();
	}

	@RequestMapping(value = "/patient/{id}", method = RequestMethod.GET)
	public @ResponseBody Optional<Patient> findPatientRest(@PathVariable("id") Long patientId) {
		return repository.findById(patientId);
	}

	@RequestMapping(value = "/add")
	public String addPatient(Model model) {
		model.addAttribute("patient", new Patient());
		model.addAttribute("doctors", docrepository.findAll());
		return "addPatient";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String savePatient(Patient patient, Model model) {
		repository.save(patient);
		model.addAttribute("patient", new Patient());
		return "redirect:patientList";
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deletePatient(@PathVariable("id") Long patientId, Model model) {
		repository.deleteById(patientId);
		return "redirect:../patientList";
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String editPatient(@PathVariable("id") Long patientId, Model model) {
		model.addAttribute("patient", repository.findById(patientId));
		model.addAttribute("doctors", docrepository.findAll());
		return "modifyPatient";
	}

	@RequestMapping(value = "/login")
	public String login() {
		return "login";
	}

	@GetMapping("/index")
	public String hello() {
		return "uploader";
	}

	@PostMapping("/upload")
	public ResponseEntity<Object> handleFileUpload(@RequestParam("file") MultipartFile file) {
		String fileName = file.getOriginalFilename();

		try {
			file.transferTo(new File("D:\\project/Sample/private/GiovaniFrancisco/upload\\" + fileName));
		} catch (IOException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		return ResponseEntity.ok("File uploaded successfully!");
	}

}
