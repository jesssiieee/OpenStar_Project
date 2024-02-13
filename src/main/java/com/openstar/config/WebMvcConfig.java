package com.openstar.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.openstar.common.FileManagerService;


@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		
		registry
			.addResourceHandler("/images/**") // web path: http://localhost/images/aaaa_1705483559044/cat-8361048_1280.jpg
		.addResourceLocations("file://" + FileManagerService.FILE_UPLOAD_PATH); // 실제 이미지 파일 위치
									// mac os는 // 2개, window는 /// 3개
		
	}
	
}
