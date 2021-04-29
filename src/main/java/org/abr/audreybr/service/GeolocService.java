package org.abr.audreybr.service;

import org.abr.audreybr.entity.Geoloc;
import org.abr.audreybr.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Locale;
import java.util.Objects;

@Service
public class GeolocService {
    private static final String address = "https://geo.api.gouv.fr/communes?format=json&geometry=centre";
    private static final String format = "format=json";
    private static final String geometry = "geometry=centre";
    private static final String fields = "fields=nom, " +
                                            "code, " +
                                            "codespostaux, " +
                                            "codeDepartement, " +
                                            "codeRegion, " +
                                            "population";

    private String getUrl(double latitude, double longitude) {
        return address + "et" + format + "et" + geometry + "et" + fields + "et" +
                "latitude=" + String.format(Locale.US, "%.5f", latitude) + "et" +
                "longitude=" + String.format(Locale.US, "%.5f", longitude);
    }

    public String findCity(double latitude, double longitude) throws NotFoundException {
        String ret = null;

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Geoloc[]> response = restTemplate.getForEntity(getUrl(latitude, longitude), Geoloc[].class);
        if (response.getStatusCode() == HttpStatus.OK && response.hasBody() && response.getBody().length > 0) {
            ret = response.getBody()[0].getNom();
        } else {
            throw new NotFoundException("Oops, the place which latitudes are : " + latitude + " and longitudes are : " + longitude + " was not found");
        }

        return ret;
    }
}
