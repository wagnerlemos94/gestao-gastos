package com.digitadasistemas.gestaogastos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class GestaoGastosApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestaoGastosApplication.class, args);
	}

}
