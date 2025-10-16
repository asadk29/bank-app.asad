package com.asadk.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(
		name = "Account",
		description = "Schema to hold account information of a customer"
		)
public class AccountsDto {

	@Schema(
			description = "A/c No. of the Customer", example = "34784xxxxx" 
			)
	@NotEmpty(message = "Account Number can not be a null or empty")
	@Pattern(regexp = "(^$|[0-9]{10})", message = "Account number must be of 10 digits")
	private Long accountNumber;

	@Schema(
			description = "Account type of the Customer", example = "Current" 
			)
	@NotEmpty(message = "Account Type can not be a null or empty")
	private String accountType;

	@Schema(
			description = "Branch Address of the Customer", example = "123 New Delhi" 
			)
	@NotEmpty(message = "Branch Address not be a null or empty")
	private String branchAddress;
	
}
