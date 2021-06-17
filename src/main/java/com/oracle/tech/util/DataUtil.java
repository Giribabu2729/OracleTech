package com.oracle.tech.util;

import com.oracle.tech.domain.Customer;
import org.springframework.util.ResourceUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataUtil {

    private static DataUtil _instance = null;
    public static List<Customer> customerList = null;

    protected DataUtil() {
        try {
            File file = ResourceUtils.getFile("classpath:SampleData.txt");
            customerList = new ArrayList<>();
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] list = line.split(",");
                    Customer customer = new Customer();
                    customer.setCustomerId(list[0]);
                    customer.setContractId(list[1]);
                    customer.setGeozone(list[2]);
                    customer.setTeamcode(list[3]);
                    customer.setProjectcode(list[4]);
                    customer.setBuildduration(list[5]);
                    customerList.add(customer);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            System.out.println("Exception : " +e.getMessage());
        }
    }

    public static DataUtil instance(){
        if (_instance == null) {
            _instance = new DataUtil();
        }
        return _instance;
    }
}
