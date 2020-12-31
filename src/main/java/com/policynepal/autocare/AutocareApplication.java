package com.policynepal.autocare;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.policynepal.autocare.user.model.Organization;
import com.policynepal.autocare.user.model.User;
import com.policynepal.autocare.user.repository.UserRepository;

@EnableMongoAuditing
@SpringBootApplication
public class AutocareApplication implements CommandLineRunner{
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(AutocareApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		if (userRepository.findByUsername("autocare") == null) {
			Organization organization = new Organization();
			organization.setName("Policy Nepal");
			organization.setDescription("Policy Nepal company description");
			
			User user = User.builder()
					.firstname("Policy")
					.lastname("Nepal")
					.address("Sankhamul")
					.email("rokinmaharjan@gmail.com")
					.active(true)
					.username("autocare")
					.password(passwordEncoder.encode("test123"))
					.roles(Arrays.asList("SUPER_ADMIN"))
					.organization(organization)
					.build();

			userRepository.save(user);
		}
	}
	
}
