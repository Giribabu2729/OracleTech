package com.oracle.tech.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import com.oracle.tech.exceptions.TechChallengeException;

import java.util.List;
import java.util.Map;

public class CustomerServiceTest {

	@Mock
	private CustomerService customerService;

	@BeforeEach
	public void setup() {
		customerService = Mockito.mock(CustomerService.class);
	}

	@Test
	public void uniqueCustIdsUnderGeoZoneAndContractId_ContractId() throws TechChallengeException {
		Map<String, Integer> response = customerService.uniqueCustIdsUnderGeoZoneAndContractId("contractId");

		Assertions.assertNotNull(response);
	}

	@Test
	public void uniqueCustIdsUnderGeoZoneAndContractId_GeoZone() throws TechChallengeException {
		Map<String, Integer> response = customerService.uniqueCustIdsUnderGeoZoneAndContractId("geozone");

		Assertions.assertNotNull(response);
	}

	@Test
	public void geoZoneAvgBuild() {
		Map<String, String> response = customerService.geoZoneAvgBuild();
		Assertions.assertNotNull(response);
	}

	@Test
	public void uniqueCustIdsUnderGeoZone() throws TechChallengeException {
		Map<String, List<String>> response = customerService.uniqueCustIdsUnderGeoZone();
		Assertions.assertNotNull(response);
	}

}
