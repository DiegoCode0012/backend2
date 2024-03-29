package com.formacionbdi.springboot.backendAngularapirestHotel;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;


@SpringBootApplication
public class SpringBootBackendAngularApirestHotelApplication implements CommandLineRunner {

	@Autowired
    PasswordEncoder passwordEncoder;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootBackendAngularApirestHotelApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	
		String password="12345";
		for(int i=0;i<2;i++) {
			String bcryptPassword= passwordEncoder.encode(password);
			System.out.println(bcryptPassword);
		}
	}

	
}
