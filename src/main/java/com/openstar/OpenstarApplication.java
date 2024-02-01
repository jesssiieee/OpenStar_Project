package com.openstar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
// Spring Boot 웹 애플리케이션에 Security Starter를 추가하면 자동으로 웹 보안이 적용하기 때문에
// @SpringBootApplication 애노테이션에 SecurityAutoConfiguration 를 읽어 들이지 않도록 설정
public class OpenstarApplication {

	public static void main(String[] args) {
		SpringApplication.run(OpenstarApplication.class, args);
	}

}
