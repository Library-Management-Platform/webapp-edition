package com.platform.libraryManager;

import com.platform.libraryManager.setup.configurationProperties.DatasourceConfigurationProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableConfigurationProperties(DatasourceConfigurationProperties.class)
public class LibraryManagerApplication {
	public static void main(String[] args) {
		SpringApplication.run(LibraryManagerApplication.class, args);
	}
}
