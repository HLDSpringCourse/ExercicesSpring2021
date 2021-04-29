package org.pyl.pylspring.Client;

import org.apache.logging.log4j.util.Strings;
import org.pyl.pylspring.exception.APIException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import org.pyl.pylspring.dto.RegionDTO;
import org.springframework.stereotype.Service;
import util.Constants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class GeoApiClient {

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${api-gov.geo.base-url}")
    private String apiGeoBaseUrl;

    @Value("${api-gov.geo.regions-url}")
    private String apiGeoRegionsUrl;


    public List<RegionDTO> getAllRegions() {
        List<RegionDTO> regionDTOList = new ArrayList<>();

        RegionDTO[] regionDTOArr = restTemplate.getForObject(apiGeoBaseUrl + apiGeoRegionsUrl, RegionDTO[].class);

        if(regionDTOArr != null) {
            regionDTOList = Arrays.asList(regionDTOArr);
        }

        return regionDTOList;
    }

    public String getRegion(String code) throws APIException {

        if(Strings.isBlank(code)) throw new APIException(Constants.MESSAGE_BAD_ITEM, HttpStatus.BAD_REQUEST);

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
