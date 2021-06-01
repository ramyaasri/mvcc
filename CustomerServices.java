package com.mindtree.customer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.customer.entity.Customer;
import com.mindtree.customer.exception.CustomerServiceException;
import com.mindtree.customer.exception.IdNotFoundException;
import com.mindtree.customer.repository.CustomerRepository;

@Service
public class CustomerServices {

	@Autowired
	private CustomerRepository repository;

	public Customer saveCustomer(Customer customer) {
		return repository.save(customer);
	}

	public void saveMultipleCustomers(List<Customer> customerList) {
		repository.saveAll(customerList);
	}

	public Customer updateCustomer(int id, Customer customer) throws CustomerServiceException {
		Customer cus = null;
		try {
			cus = repository.findById(id).orElse(null);
			if (cus == null) {
				throw new IdNotFoundException("No Such Id Exception...");
			}
			cus.setName(customer.getName());
			cus.setAge(customer.getAge());
			cus.setAddress(customer.getAddress());
			cus.setEmail(customer.getEmail());
			
			repository.save(cus);
			return cus;

			
		} catch (IdNotFoundException e) {
			throw new CustomerServiceException(e.getMessage());

		}
	}

	public void deleteCustomer(int id) {
		repository.deleteById(id);
	}

	public Customer getCustomerById(int id) throws CustomerServiceException {
		Customer emp = null;
		try {

			emp = repository.findById(id).orElse(null);
			if (emp == null) {
				throw new IdNotFoundException("No Such Id Exception...");
			}
		} catch (IdNotFoundException e) {
			throw new CustomerServiceException(e.getMessage());
		}
		return emp;

	}

	public List<Customer> getAllCustomer() {
		return repository.findAll();
	}

	public List<Customer> getByName(String name) {
		return repository.findByName(name);
	}
	
	

}
