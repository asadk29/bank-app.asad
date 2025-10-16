package com.asadk.loans.service;


import com.asadk.loans.dto.LoansDto;

public interface ILoansService {

    public void createLoan(String mobileNumber);

    public LoansDto fetchLoan(String mobileNumber);

    public void updateLoan(LoansDto loansDto);

    public void deleteLoan(String mobileNumber);

}