package com.oracle.tech.service;

import java.util.List;
import java.util.Map;

import com.oracle.tech.domain.Customer;
import com.oracle.tech.exceptions.TechChallengeException;

public interface CustomerService {
	
	public Map<String, List<String>> processRequest(String type, List<Customer> customerList) throws TechChallengeException;
	
	public Map<String, String> processAvgBuild(List<Customer> customerList);
	
	public Map<String, Integer> uniqueCustIdsUnderGeoZoneAndContractId(String type) throws TechChallengeException;

	public Map<String, List<String>> uniqueCustIdsUnderGeoZone() throws TechChallengeException;

	public Map<String, String> geoZoneAvgBuild();

}
