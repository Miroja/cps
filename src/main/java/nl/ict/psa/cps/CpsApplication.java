package nl.ict.psa.cps;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * This application is built on the Spring framework (Spring.io)
 * It is using a H2 embedded sql database. Using Spring data(jpa, h2) there is no setup needed to use the h2 database.
 * You can just run this application and
 */
@SpringBootApplication
@EnableScheduling
public class CpsApplication {
	/**
	 * This is the main method that boots up the application.
	 */
	public static void main(String[] args) {
		SpringApplication.run(CpsApplication.class, args);
	}

}
