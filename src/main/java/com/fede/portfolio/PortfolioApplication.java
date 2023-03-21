package com.fede.portfolio;


import com.fede.portfolio.repository.RoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

import com.fede.portfolio.model.Role;
import static com.fede.portfolio.model.ERole.*;
import com.fede.portfolio.model.User;

@SpringBootApplication
@EnableAsync
@Slf4j
public class PortfolioApplication {

	public static void main(String[] args) {
		SpringApplication.run(PortfolioApplication.class, args);
	}

	//for dev use only
	@Bean
	CommandLineRunner populateRoleRepo(RoleRepository roleRepository) {
		return args -> {
			if (!roleRepository.findAll().isEmpty()) {
				PortfolioApplication.log.info("Skipping db roles insertion.");
			} else {
				log.info("Inserting roles");
				roleRepository.saveAndFlush(new Role(ROLE_USER));
				roleRepository.saveAndFlush(new Role(ROLE_ADMIN));
				roleRepository.saveAndFlush(new Role(ROLE_MODERATOR));
			}
		};
	}


}
