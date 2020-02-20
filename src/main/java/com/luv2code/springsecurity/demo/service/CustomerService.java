package com.luv2code.springsecurity.demo.service;

import java.util.List;

import com.luv2code.springsecurity.demo.entity.Customer;

public interface CustomerService {

	public List<Customer> getCustomers();

	public void saveCustomer(Customer theCustomer);

	public Customer getCustomer(int theId);

	public void deleteCustomer(int theId);
	
}
