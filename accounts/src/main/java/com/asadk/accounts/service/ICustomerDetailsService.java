package com.asadk.accounts.service;

import com.asadk.accounts.dto.CustomerDetailsDto;

public interface ICustomerDetailsService {

	public CustomerDetailsDto fetchCustomerDetails(String mobileNum, String correlationId);
	
	
}
