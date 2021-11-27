package com.example.PatientList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.PatientList.domain.Doctor;
import com.example.PatientList.domain.DoctorRepository;
import com.example.PatientList.domain.Patient;
import com.example.PatientList.domain.PatientRepository;
import com.example.PatientList.domain.User;
import com.example.PatientList.domain.UserRepository;

@SpringBootApplication
public class PatientListApplication {

	private static final Logger log = LoggerFactory.getLogger(PatientListApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(PatientListApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner patientDemo(PatientRepository repository, DoctorRepository docrepository, UserRepository urepository) {
		return (args) -> {
			log.info("Patient list sample");
			
			docrepository.save(new Doctor(" "));
			docrepository.save(new Doctor("Dr. Giovani"));
			docrepository.save(new Doctor("Dr. Francisco"));
			docrepository.save(new Doctor("Dr. Who"));
			
			repository.save(new Patient("John Snow", "Surgery", "Hematoma and Massive Bleeding secondary to horse accident", "January 25, 2021", docrepository.findByName("Dr. Giovani").get(0)));
			repository.save(new Patient("Daenerys Targaryen", "Orthopedics", "Broken tibia secondary to fall from a dragon ride", "January 16, 2021", docrepository.findByName("Dr. Francisco").get(0)));

			User user1 = new User("user", "$2a$12$33C0zUQ.2LYJGQ3UMEBPQukcjymql38NxyCIBVLqy.36IYFHbGcHy", "user@gmail.com", "USER");
			User user2 = new User("admin", "$2a$12$bZpNp5UIAqKCUeRijzZhvedWjCZlnaJHeRoYevdBb1RnZprIL7tLa", "admin@gmail.com", "ADMIN");
			urepository.save(user1);
			urepository.save(user2);
			
			log.info("fetch all patients");
			for (Patient patient : repository.findAll()) {
				log.info(patient.toString());
			}
			
			log.info("fetch all USERS");
			for (User user : urepository.findAll()) {
				log.info(user.toString());
			}
			
		};
	}

}
