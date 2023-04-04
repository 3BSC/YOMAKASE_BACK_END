package com.yomakase.yomakase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class YomakaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(YomakaseApplication.class, args);
	}

}
