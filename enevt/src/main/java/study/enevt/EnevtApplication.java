package study.enevt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class EnevtApplication {

	public static void main(String[] args) {
		SpringApplication.run(EnevtApplication.class, args);
	}

}
