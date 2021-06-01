package com.example.mindtree.CustomerManagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mindtree.CustomerManagement.model.Customer;
import com.example.mindtree.CustomerManagement.repository.CustomerRepository;

@Service
public class CutomerService implements CustomerServiceInterface{

	@Autowired
	CustomerRepository custRepository;

	@Override
	public void createCustomerDetails(Customer customer) {
		// TODO Auto-generated method stub
		custRepository.save(customer);
		
	}

	@Override
	public List<Customer> getAllCustomerDetails() {
		// TODO Auto-generated method stub
		return custRepository.findAll();
	}
	
	
	
}
