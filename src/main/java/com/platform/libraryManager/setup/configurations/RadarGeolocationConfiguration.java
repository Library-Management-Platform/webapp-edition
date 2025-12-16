package com.platform.libraryManager.setup.configurations;


import com.platform.libraryManager.setup.configurationProperties.RadarGeolocationConfigurationProperties;
import com.platform.libraryManager.shared.providers.RadarGeolocationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RadarGeolocationConfiguration {

    @Autowired private RadarGeolocationConfigurationProperties radarGeolocationConfigurationProperties;


    @Bean
    public RadarGeolocationProvider radarGeolocationProvider() {
        return new RadarGeolocationProvider(radarGeolocationConfigurationProperties);
    }
}
