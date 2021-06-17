package com.oracle.tech.service.impl;

import com.oracle.tech.controller.CustomerController;
import com.oracle.tech.domain.Customer;
import com.oracle.tech.exceptions.TechChallengeException;
import com.oracle.tech.service.CustomerService;
import com.oracle.tech.util.DataUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService{
	private final static Logger LOGGER = LoggerFactory.getLogger(CustomerServiceImpl.class);

      public Map<String, List<String>> uniqueCustIdsUnderGeoZone() throws TechChallengeException {
        DataUtil dataUtil = DataUtil.instance();
        LOGGER.info("Before calling the processRequest() method");
        return processRequest("geozone", dataUtil.customerList);
    }

    public Map<String, List<String>> processRequest(String type, List<Customer> customerList) throws TechChallengeException {
    	
    	if(type.isEmpty() || customerList.isEmpty()) {
    		throw new TechChallengeException("type/customerList not found");
    	}
    	
        Map<String, List<String>> response = new HashMap<>();
        List<Customer> list = new ArrayList<>();
        list = customerList.stream().filter(distinctByKey(Customer::getGeozone)).collect(Collectors.toList());
        LOGGER.info("List : "+ list);

        list.forEach(customer -> {
            List<String> custIds = new ArrayList<>();
             if (type.equalsIgnoreCase("geozone")) {
                customerList.forEach(cus -> {
                            if (cus.getGeozone().equalsIgnoreCase(customer.getGeozone())) {
                                custIds.add(cus.getCustomerId());
                            }
                        }
                );
            }

            if (type.equalsIgnoreCase("geozone")) {
                response.put(customer.getGeozone(), custIds);
            }
        });
        return response;
    }

    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(keyExtractor.apply(t));
    }

    public Map<String, String> geoZoneAvgBuild() {
        DataUtil dataUtil = DataUtil.instance();
        return processAvgBuild(dataUtil.customerList);
    }

    public Map<String, String> processAvgBuild(List<Customer> customerList) {
    	LOGGER.info("Finding the processAvgBuild : ");
        Map<String, String> response = new HashMap<>();
        List<Customer> list = customerList.stream().filter(distinctByKey(Customer::getGeozone)).collect(Collectors.toList());

        list.forEach(customer -> {
            AtomicInteger buildDuration = new AtomicInteger();
            AtomicInteger count = new AtomicInteger();
            customerList.forEach(cus -> {
                        if (cus.getGeozone().equalsIgnoreCase(customer.getGeozone())) {
                            String buildTime = cus.getBuildduration().substring(0, cus.getBuildduration().length() - 1);
                            buildDuration.addAndGet(Integer.parseInt(buildTime));
                            count.addAndGet(1);
                        }
                    }
            );
            response.put(customer.getGeozone(), buildDuration.get() / count.get()+"s");
        });

        return response;
    }

    public Map<String, Integer> uniqueCustIdsUnderGeoZoneAndContractId(String type) throws TechChallengeException {
    	
    	if(type.isEmpty()) {
    		throw new TechChallengeException("type not found");
    	}
    	
        DataUtil dataUtil = DataUtil.instance();
        Map<String, Integer> response = new HashMap<>();
        List<Customer> list = dataUtil.customerList.stream().filter(distinctByKey(Customer::getGeozone)).collect(Collectors.toList());
        LOGGER.info("List : "+ list);
        list.forEach(customer -> {
            if (type.equalsIgnoreCase("geozone")) {
                response.put(customer.getGeozone(), (int) dataUtil.customerList.stream().filter(cus -> cus.getGeozone().equalsIgnoreCase(customer.getGeozone())).count());
            } else if (type.equalsIgnoreCase("contractId")) {
                response.put(customer.getContractId(), (int) dataUtil.customerList.stream().filter(cus -> cus.getContractId().equalsIgnoreCase(customer.getContractId())).count());
            }
        });
        return response;
    }
}
