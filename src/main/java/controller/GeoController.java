package controller;

import org.lfc.dto.GeoDto;
import org.lfc.service.GeoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/geo")
public class GeoController {
	@Autowired
	GeoService geoService;

	@GetMapping(path = "/get/code/{code}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GeoDto> getItem(@PathVariable int code) {
		return ResponseEntity.status(HttpStatus.OK).body(geoService.foundCity(code));
    }
}
