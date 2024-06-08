package com.masoongsoong.FreashKeepie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.boot.web.servlet.ServletComponentScan;
@EnableJpaAuditing //BaseTimeEntity를 위한 Auditing 어노테이션 활성화

@SpringBootApplication
@ServletComponentScan //서블릿 자동 등록

public class FreashKeepieApplication {

	public static void main(String[] args) {
		SpringApplication.run(FreashKeepieApplication.class, args);
	}

}
