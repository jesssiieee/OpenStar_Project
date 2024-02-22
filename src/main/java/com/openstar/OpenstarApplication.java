package com.openstar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

@EnableScheduling // batch를 수행시킬 어노테이션, 설정된 시간에 수행시킴을 허용한다.
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
// Spring Boot 웹 애플리케이션에 Security Starter를 추가하면 자동으로 웹 보안이 적용하기 때문에
// @SpringBootApplication 어노테이션에 SecurityAutoConfiguration 를 읽어 들이지 않도록 설정
public class OpenstarApplication {

	public static void main(String[] args) {
		SpringApplication.run(OpenstarApplication.class, args);
	}

}
