package com.onlylive.start;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
public class StartApplication {

	public static void main(String[] args) {
		SpringApplication.run(StartApplication.class, args);
	}

	@Controller
	public class SimpleController {
		@Value("${spring.application.name}")
		private String appName;

		@GetMapping("/")
		public String homePage(Model model1) {
			model1.addAttribute("appname", appName);
			return "home";
		}
	}

	@Configuration
	@EnableWebSecurity
	public class SecurityConfig {

		@Bean
		public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
			http.authorizeHttpRequests(expressionInterceptUrlRegistry ->
							expressionInterceptUrlRegistry
									.anyRequest()
									.permitAll())
					.csrf(AbstractHttpConfigurer::disable);
			return http.build();
		}
	}
}
