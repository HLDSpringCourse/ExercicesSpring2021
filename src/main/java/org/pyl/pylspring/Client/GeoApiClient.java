package org.pyl.pylspring.Client;

import org.pyl.pylspring.exception.APIException;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import org.pyl.pylspring.dto.RegionDTO;
import org.springframework.stereotype.Service;
import util.Constants;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class GeoApiClient {

    private final RestTemplate restTemplate = new RestTemplate();


    public List<RegionDTO> getAllRegions() {
        return Arrays.asList(Objects.requireNonNull(restTemplate.getForObject(Constants.API_GOV_BASE_URL + Constants.API_GOV_REGIONS_URL, RegionDTO[].class)));
    }

    public String getRegion(String code) throws APIException {

        if(code == null || code.isEmpty()) throw new APIException(Constants.MESSAGE_BAD_ITEM, HttpStatus.BAD_REQUEST);

        RegionDTO regionDTO;

        try {
            regionDTO = restTemplate.getForObject(Constants.API_GOV_BASE_URL + Constants.API_GOV_REGIONS_URL + "/" + code+"?fields=nom", RegionDTO.class);
        }
        catch(RestClientException e) {
            // pas tip top
            throw new APIException(Constants.MESSAGE_CODE_REGION_INVALID, HttpStatus.NOT_FOUND);
        }

        if(regionDTO == null) throw new APIException(Constants.MESSAGE_CODE_REGION_INVALID, HttpStatus.NOT_FOUND);

        return regionDTO.getNom();
    }

}
