package de.elyesnasri.lieferdienst.lieferdienstelyesnasri;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class LieferdienstElyesnasriApplication {

	public static void main(String[] args) {
		SpringApplication.run(LieferdienstElyesnasriApplication.class, args);
	}

}
