package com.platform.libraryManager;

import com.platform.libraryManager.configuration.DatasourceConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(DatasourceConfiguration.class)
public class LibraryManagerApplication {
	public static void main(String[] args) {
		SpringApplication.run(LibraryManagerApplication.class, args);
	}
}
