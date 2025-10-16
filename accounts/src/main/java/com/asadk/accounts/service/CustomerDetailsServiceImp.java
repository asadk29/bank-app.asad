package com.asadk.accounts.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.asadk.accounts.dto.AccountsDto;
import com.asadk.accounts.dto.CardsDto;
import com.asadk.accounts.dto.CustomerDetailsDto;
import com.asadk.accounts.dto.LoansDto;
import com.asadk.accounts.entity.Accounts;
import com.asadk.accounts.entity.Customer;
import com.asadk.accounts.exception.ResourceNotFoundException;
import com.asadk.accounts.mapper.AccountsMapper;
import com.asadk.accounts.mapper.CustomerMapper;
import com.asadk.accounts.repository.AccountsRepository;
import com.asadk.accounts.repository.CustomerRepository;
import com.asadk.accounts.service.client.CardsFeignClient;
import com.asadk.accounts.service.client.LoansFeignClient;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CustomerDetailsServiceImp implements ICustomerDetailsService {

	
	private AccountsRepository accountsRepo;
	
	private CustomerRepository customerRepo;
	
	private LoansFeignClient loansFeign;
	
	private CardsFeignClient cardsFeign;
	
	@Override
	public CustomerDetailsDto fetchCustomerDetails(String mobileNum, String correlationId) {
		
		Customer customer = customerRepo.findByMobileNumber(mobileNum).orElseThrow(() -> new ResourceNotFoundException("Customer", "Mobile Number", mobileNum));
		
		Accounts account = accountsRepo.findByCustomerId(customer.getCustomerId()).orElseThrow(() -> new ResourceNotFoundException("Account", "Customer Id", customer.getCustomerId().toString()));
		
		CustomerDetailsDto customerDetailsDto = CustomerMapper.mapToCustomerDetailsDto(customer, new CustomerDetailsDto());
		customerDetailsDto.setAccountsDto(AccountsMapper.mapToAccountDto(account, new AccountsDto()));
		
		ResponseEntity<LoansDto> loansResponseEntity = loansFeign.fetchLoanDetails(correlationId, mobileNum);
		ResponseEntity<CardsDto> cardsResponseEntity = cardsFeign.fetchCard(correlationId, mobileNum);
		
		customerDetailsDto.setLoansDto(loansResponseEntity.getBody());
		customerDetailsDto.setCardsDto(cardsResponseEntity.getBody());
		
		return customerDetailsDto;
		
	}

}
