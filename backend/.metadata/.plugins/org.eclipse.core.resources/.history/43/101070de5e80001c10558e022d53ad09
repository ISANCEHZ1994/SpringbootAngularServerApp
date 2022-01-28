package com.fullstack.server;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

import com.fullstack.server.model.Server;
import com.fullstack.server.repo.ServerRepo;

import static com.fullstack.server.enumeration.Status.SERVER_UP;
import static com.fullstack.server.enumeration.Status.SERVER_DOWN;

@SpringBootApplication
public class ServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	};
	
	@Bean
	 CommandLineRunner run(ServerRepo serverRepo) {		 // following Server MODEL
		 return args -> {			// id,     ipAddress, 		name, 			memory, 	type, 		imageUrl, 										status
			serverRepo.save( new Server(null, "192.168.1.160", "Sanchez Linux", "32 GB", "Personal PC", "http://localhost:8080/server/image/server1.png", SERVER_UP));
			serverRepo.save( new Server(null, "192.168.1.58", "Lopez macOS", "84 GB", "Personal Mac", "http://localhost:8080/server/image/server2.png", SERVER_UP));
			serverRepo.save( new Server(null, "192.168.1.21", "Contreas Windows", "16 GB", "Lawyer Laptop", "http://localhost:8080/server/image/server3.png", SERVER_UP));
			serverRepo.save( new Server(null, "192.168.1.14", "Sanchez macOS", "108 GB", "Developer Mac", "http://localhost:8080/server/image/server4.png", SERVER_DOWN));
			serverRepo.save( new Server(null, "192.168.1.84", "Beltran Unix", "85 GB", "Gaming PC", "http://localhost:8080/server/image/server5.png", SERVER_DOWN));
			serverRepo.save( new Server(null, "192.168.1.42", "Sebasitan Andriod", "100 GB", "Ultimate PC", "http://localhost:8080/server/image/server6.png", SERVER_DOWN));
		 };
	 };	 
	 
	 @Bean
		public CorsFilter corsFilter() {
			UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
			CorsConfiguration corsConfiguration = new CorsConfiguration();
			corsConfiguration.setAllowCredentials(true);
			corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:3000", "http://localhost:4200"));
			corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin", "Content-Type",
					"Accept", "Jwt-Token", "Authorization", "Origin, Accept", "X-Requested-With",
					"Access-Control-Request-Method", "Access-Control-Request-Headers"));
			corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Jwt-Token", "Authorization",
					"Access-Control-Allow-Origin", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials", "Filename"));
			corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
			urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
			return new CorsFilter(urlBasedCorsConfigurationSource);
		};
	

};
