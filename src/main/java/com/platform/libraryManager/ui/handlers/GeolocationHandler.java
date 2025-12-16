package com.platform.libraryManager.ui.handlers;

import com.platform.libraryManager.shared.providers.RadarGeolocationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GeolocationHandler {

    @Autowired private RadarGeolocationProvider radarProvider;


    @GetMapping("/autocomplete")
    public String autocomplete(
            @RequestParam String query,
            @RequestParam(required = false) String near
    ) {
        return radarProvider.autocomplete(
                query,
                near,
                "place,address", // layers
                10,              // limit
                null,            // countryCode
                "en",            // lang
                false            // testMode
        );
    }


}
