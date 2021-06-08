package io.dbychkowsky.globaltempstats;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class GlobaltempStatsApplication {

	public static void main(String[] args) {
		SpringApplication.run(GlobaltempStatsApplication.class, args);
	}

}
