package com.example.phoneduck;

import com.example.phoneduck.repo.JpaChannelRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PhoneDuckApplication {

	public static void main(String[] args) {
		SpringApplication.run(PhoneDuckApplication.class, args);
	}

}
