package com.masoongsoong.FreashKeepie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing //BaseTimeEntity를 위한 Auditing 어노테이션 활성화
@SpringBootApplication
//@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class FreashKeepieApplication {

	public static void main(String[] args) {
		SpringApplication.run(FreashKeepieApplication.class, args);
	}

}
