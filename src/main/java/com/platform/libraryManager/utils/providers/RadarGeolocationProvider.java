package com.platform.libraryManager.utils.providers;

import com.platform.libraryManager.setup.configurationProperties.RadarGeolocationConfigurationProperties;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

public class RadarGeolocationProvider {

    private final RadarGeolocationConfigurationProperties radarGeolocationConfigurationProperties;
    private final RestTemplate restTemplate;

    private static final String HOST = "api.radar.io/v1";


    public RadarGeolocationProvider(
            RadarGeolocationConfigurationProperties radarGeolocationConfigurationProperties
    ) {
        this.radarGeolocationConfigurationProperties = radarGeolocationConfigurationProperties;
        this.restTemplate = new RestTemplate();
    }


    private HttpHeaders buildHeaders(boolean testMode, boolean usePublishableKey) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String key;
        if (usePublishableKey) key = testMode ? radarGeolocationConfigurationProperties.getTestPublishKey() : radarGeolocationConfigurationProperties.getPublishKey();
        else key = testMode ? radarGeolocationConfigurationProperties.getTestSecretKey() : radarGeolocationConfigurationProperties.getSecretKey();

        headers.setBearerAuth(key);
        return headers;
    }


    /**
     * Autocomplete partial addresses or place names.
     *
     * @param query The partial address or place name.
     * @param near Optional, preferred location as "latitude,longitude".
     * @param layers Optional, comma-separated layers (place,address,postalCode,...).
     * @param limit Optional, max results (1-100, default 10).
     * @param countryCode Optional, comma-separated country codes (e.g., US,CA).
     * @param lang Optional, language code (ar,de,en,...).
     * @param testMode true to use test keys, false for live keys.
     * @return JSON response from Radar API as String.
     */
    public String autocomplete(
            String query,
            String near,
            String layers,
            Integer limit,
            String countryCode,
            String lang,
            boolean testMode
    ) {
        UriComponentsBuilder builder = UriComponentsBuilder.newInstance()
                .scheme("https")
                .host(HOST)
                .path("/search/autocomplete")
                .queryParam("query", query);

        if (near != null) builder.queryParam("near", near);
        if (layers != null) builder.queryParam("layers", layers);
        if (limit != null) builder.queryParam("limit", limit);
        if (countryCode != null) builder.queryParam("countryCode", countryCode);
        if (lang != null) builder.queryParam("lang", lang);

        return restTemplate.postForObject(
                builder.toUriString(),
                new HttpEntity<>(buildHeaders(testMode, true)),
                String.class
        );
    }
}
