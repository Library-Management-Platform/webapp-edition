package com.platform.libraryManager;

import com.platform.libraryManager.configurationProperties.DatasourceConfigurationProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(DatasourceConfigurationProperties.class)
public class LibraryManagerApplication {
	public static void main(String[] args) {
		SpringApplication.run(LibraryManagerApplication.class, args);
	}
}
