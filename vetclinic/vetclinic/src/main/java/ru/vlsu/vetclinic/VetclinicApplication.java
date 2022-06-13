package ru.vlsu.vetclinic;

import org.apache.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class VetclinicApplication {

	@Bean
	public BCryptPasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
	private final static Logger log =
			Logger.getLogger(VetclinicApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(VetclinicApplication.class, args);
			log.info("saleh sasat");
			log.warn("saleh sasat");
			log.error("saleh sasat");
	}

}