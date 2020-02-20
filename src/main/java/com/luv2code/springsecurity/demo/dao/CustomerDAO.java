package com.luv2code.springsecurity.demo.dao;

import java.util.List;

import com.luv2code.springsecurity.demo.entity.Customer;

public interface CustomerDAO {

	public List<Customer> getCustomers();

	public void saveCustomer(Customer theCustomer);

	public Customer getCustomer(int theId);

	public void deleteCustomer(int theId);
	
}
