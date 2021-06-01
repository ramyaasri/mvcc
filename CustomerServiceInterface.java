package com.example.mindtree.CustomerManagement.service;

import java.util.List;

import com.example.mindtree.CustomerManagement.model.Customer;

public interface CustomerServiceInterface {

	void createCustomerDetails(Customer customer);

	List<Customer> getAllCustomerDetails();
}
