package com.asadk.accounts.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(
		name = "Customer",
		description = "Schema to hold account and customer information"
		)
public class CustomerDto {
    
	@Schema(
			description = "Name of the Customer", example = "Asad Khan" 
			)
	@NotEmpty(message = "Name can not be a null or empty")
	@Size(min = 2, max = 30, message = "Size can not be less than 3 and greater than 30")
	private String name;
    
	@Schema(
			description = "Email of the Customer", example = "asadk@xyz.com" 
			)
	@NotEmpty(message = "Email can not be a null or empty")
	@Email(message = "Email format is not valid")
	private String email;
    
	@Schema(
			description = "Mobile No. of the Customer", example = "989889xxxx" 
			)
	@Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be of 10 digits")
	private String mobileNumber;
	
	@Schema(
			description = "Account detail of the Customer"
			)
	private AccountsDto accountsDto;
}
