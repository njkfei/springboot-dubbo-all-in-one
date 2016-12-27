package com.jinhui.dubbo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:dubbo-provider.xml")
public class SpringbootProviderUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootProviderUserApplication.class, args);
	}
}
