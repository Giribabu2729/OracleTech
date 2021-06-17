package com.oracle.tech.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.oracle.tech.exceptions.TechChallengeException;
import com.oracle.tech.service.CustomerService;

@RestController
@Validated
public class CustomerController {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    public CustomerService customerService;

    @GetMapping("/{type}")
    public ResponseEntity uniqueCustIdsUnderContractId(@Valid @PathVariable("type") String type) throws TechChallengeException {
    	LOGGER.info("The number of unique customerId for each contractId/geoZone "+ type);
        return ResponseEntity.status(HttpStatus.OK).body(customerService.uniqueCustIdsUnderGeoZoneAndContractId(type));
    }

    @GetMapping("/uniqueCustId/geoZone")
    public ResponseEntity uniqueCustIdsUndergeoZone() throws TechChallengeException {
    	LOGGER.info("To find the list of unique customerId for each geozone.");
        return ResponseEntity.status(HttpStatus.OK).body(customerService.uniqueCustIdsUnderGeoZone());
    }

    @GetMapping("/geoZoneAvgBuild")
    public ResponseEntity geoZoneAvgBuild() {
    	LOGGER.info("To find the average buildduration for each geozone.");
        return ResponseEntity.status(HttpStatus.OK).body(customerService.geoZoneAvgBuild());
    }
}
