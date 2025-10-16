package com.asadk.accounts.service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.asadk.accounts.constants.AccountsConstants;
import com.asadk.accounts.dto.AccountsDto;
import com.asadk.accounts.dto.CustomerDto;
import com.asadk.accounts.entity.Accounts;
import com.asadk.accounts.entity.Customer;
import com.asadk.accounts.exception.CustomerAlreadyExistException;
import com.asadk.accounts.exception.ResourceNotFoundException;
import com.asadk.accounts.mapper.AccountsMapper;
import com.asadk.accounts.mapper.CustomerMapper;
import com.asadk.accounts.repository.AccountsRepository;
import com.asadk.accounts.repository.CustomerRepository;

@Service
public class AccountsServiceImp implements IAccountsService {

	private AccountsRepository accountsRepo;

	private CustomerRepository customerRepo;

	@Autowired
	public AccountsServiceImp(AccountsRepository accountsRepo, CustomerRepository customerRepo) {

		this.accountsRepo = accountsRepo;
		this.customerRepo = customerRepo;

	}

	@Override
	public void createAccount(CustomerDto customerDto) {

		Customer customer = CustomerMapper.mapToCustomer(new Customer(), customerDto);
		
		Optional<Customer> optional = customerRepo.findByMobileNumber(customerDto.getMobileNumber());
		if (optional.isPresent()) {
			throw new CustomerAlreadyExistException(
					"Customer already registered with given mobile number: " + customerDto.getMobileNumber());
		}

		Customer savedCustomer = customerRepo.save(customer);
		accountsRepo.save(createNewAccount(savedCustomer));

	}

	private Accounts createNewAccount(Customer customer) {

		Accounts account = new Accounts();

		account.setCustomerId(customer.getCustomerId());

		long randomAccNo = 1000000000L + new Random().nextInt(900000000);
		account.setAccountNumber(randomAccNo);
		account.setAccountType(AccountsConstants.SAVINGS);
		account.setBranchAddress(AccountsConstants.ADDRESS);
		
		return account;

	}

	@Override
	public CustomerDto fetchAccountDetails(String mobileNumber) {

		Customer customer = customerRepo.findByMobileNumber(mobileNumber)
				                        .orElseThrow(() -> new ResourceNotFoundException("Customer", "Mobile Number", mobileNumber));

		Accounts account = accountsRepo.findByCustomerId(customer.getCustomerId())
				                       .orElseThrow(() -> new ResourceNotFoundException("Account", "Customer Id", customer.getCustomerId().toString()));

		CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer, new CustomerDto());
		customerDto.setAccountsDto(AccountsMapper.mapToAccountDto(account, new AccountsDto()));
		
		return customerDto;
	}

	@Override
	public boolean updateAccountDetails(CustomerDto customerDto) {
		
		boolean isUpdated = false;
		AccountsDto accountDto = customerDto.getAccountsDto();
		
		if(accountDto != null) {
			
			Accounts account = accountsRepo.findById(accountDto.getAccountNumber())
					                       .orElseThrow(() -> new ResourceNotFoundException("Account","Account Number",accountDto.getAccountNumber().toString()));
			
			
			AccountsMapper.mapToAccounts(account, accountDto);
			
			Customer customer = customerRepo.findById(account.getCustomerId())
					                        .orElseThrow(() -> new ResourceNotFoundException("Customer","Customer Id",account.getCustomerId().toString()));
			  
			CustomerMapper.mapToCustomer(customer, customerDto);
			
			accountsRepo.save(account);
			customerRepo.save(customer);
			
			isUpdated = true;
			
		}
		
		return isUpdated;
	}

	@Override
	public boolean deleteAccountDetails(String mobileNum) {
		
		boolean isUpdate = false;
		
		Customer customer = customerRepo.findByMobileNumber(mobileNum)
				                        .orElseThrow(() -> new ResourceNotFoundException("Customer","Mobile Number",mobileNum.toString()));
		
		accountsRepo.deleteByCustomerId(customer.getCustomerId());
		customerRepo.deleteById(customer.getCustomerId());
		
		isUpdate = true;
		
		return isUpdate;
		
	}

}
