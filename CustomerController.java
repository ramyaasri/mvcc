package com.mindtree.customer.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.mindtree.customer.entity.Customer;
import com.mindtree.customer.exception.CustomerControllerException;
import com.mindtree.customer.exception.CustomerServiceException;
import com.mindtree.customer.service.CustomerServices;
@CrossOrigin
@Controller

public class CustomerController {

	
	
	@Autowired
	private CustomerServices service;
	
	@GetMapping(value = "/home")
	public String homepage(Model model) {
		model.addAttribute("customers",service.getAllCustomer());
		return "customers";                                        
	}
	
	@GetMapping("/customer/new")
	public String createCustomer(Model model) {
		Customer customer=new Customer();
		model.addAttribute("customer",customer);
		return "addcustomer";
	}
	
	@GetMapping("/customer/edit/{id}")
	public String editCustomer(@PathVariable int id,Model model) throws CustomerServiceException {
		model.addAttribute("customer",service.getCustomerById(id));
		return "editcustomer";
		
	}
	
	@PostMapping(value = "/add")
	public String addCustomer(@ModelAttribute("customer") Customer customer) {
		service.saveCustomer(customer);
		return "redirect:/home";
		
	}
	
	@PostMapping(value = "/addcustomers")
	public ResponseEntity<String> addCustomers(@Valid @RequestBody List<Customer> customerList) {
		service.saveMultipleCustomers(customerList);
		return new ResponseEntity<String>("Added Successfully",HttpStatus.OK);

	}
	
	@PostMapping(value = "/update/{id}")
	public String update(@PathVariable int id,@ModelAttribute("customer") Customer customer,Model model) throws CustomerControllerException{
		
		try {
		service.updateCustomer(id, customer);
		} catch (CustomerServiceException e) {
			throw new CustomerControllerException(e.getMessage());
		}
		 return "redirect:/home";

	}
	
	@GetMapping(value = "/delete/{id}")
	public String delete(@PathVariable int id) {
		service.deleteCustomer(id);
		return "redirect:/home";

	}
	
	@GetMapping(value = "/getcustomer/{id}")
	public ResponseEntity<Customer> getCustomerById(@PathVariable int id) throws CustomerControllerException{
		Customer emp;
		try {
			emp = service.getCustomerById(id);
		} catch (CustomerServiceException e) {
			throw new CustomerControllerException(e.getMessage());
		}
		return new ResponseEntity<Customer>(emp,HttpStatus.OK);

	}
	
	@GetMapping(value = "/getallcustomer")
	public ResponseEntity<List<Customer>> getAllCustomer() {
		List<Customer> emplist=service.getAllCustomer();
		return new ResponseEntity<List<Customer>>(emplist,HttpStatus.OK);
		
	}
	
	@GetMapping(value = "/getbyname")
	public ResponseEntity<List<Customer>> getEmpByName(@PathVariable String name){
		List<Customer> emplist=service.getByName(name);
		return new ResponseEntity<List<Customer>>(emplist,HttpStatus.OK);
		
	}
}
