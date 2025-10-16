package com.asadk.accounts.service;

import com.asadk.accounts.dto.CustomerDto;

public interface IAccountsService {

	void createAccount(CustomerDto customerDto);
	
	CustomerDto fetchAccountDetails(String mobileNumber);
	
	boolean updateAccountDetails(CustomerDto customerDto);
	
	boolean deleteAccountDetails(String mobileNum);
	
}
