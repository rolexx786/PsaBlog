package com.psaBlog;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.ui.Model;

@SpringBootApplication
public class PsaBlogApplication {

	public static void main(String[] args) {
		SpringApplication.run(PsaBlogApplication.class, args);
	}
	@Bean
    public ModelMapper modelMapper(){
		return new ModelMapper();
}

}
