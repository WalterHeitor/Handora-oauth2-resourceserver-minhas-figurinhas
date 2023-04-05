package br.com.zup.edu.albunsfreemium;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class AlbunsfreemiumApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlbunsfreemiumApplication.class, args);
	}

}
