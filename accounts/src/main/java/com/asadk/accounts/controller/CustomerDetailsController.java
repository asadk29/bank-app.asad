package com.asadk.accounts.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.asadk.accounts.dto.CustomerDetailsDto;
import com.asadk.accounts.dto.ErrorResponseDto;
import com.asadk.accounts.service.ICustomerDetailsService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Pattern;

@RestController
@RequestMapping(path = "/api", produces = {MediaType.APPLICATION_JSON_VALUE})
@Validated //perform validation on all the REST API's of the controller
@Tag(
		name = "CRUD REST APIs for Customers in a Bank",
		description = "REST APIs in a Bank to fetch customer details"
    )
public class CustomerDetailsController {
	
	private static final Logger logger = LoggerFactory.getLogger(CustomerDetailsController.class);
	
	private final ICustomerDetailsService customerDetailsService;
	
	public CustomerDetailsController(ICustomerDetailsService customerDetailsService) {
		this.customerDetailsService = customerDetailsService;
	}
    
	@Operation(
			summary = "Fetch Customer Details REST API",
			description = "REST API to fetch customer details based on Mobile number"
			)
	@ApiResponses({
        @ApiResponse(
                responseCode = "200",
                description = "HTTP Status OK"
        ),
        @ApiResponse(
                responseCode = "500",
                description = "HTTP Status Internal Server Error",
                content = @Content(
                        schema = @Schema(implementation = ErrorResponseDto.class)
                )
        )
      }
    )
	@GetMapping("/fetchCustomerDetails")
	public ResponseEntity<CustomerDetailsDto> fetchCustomerDetails(@RequestHeader("eazybank-correlation-id") String correlationId,
			                                                        @RequestParam @Pattern(regexp = "(^$|[0-9]{10})", 
                                                                     message = "Mobile number must be of 10 digits") 
                                                                     String mobileNumber){
		
		logger.debug("eazyBank-correlation-id found: {} ", correlationId);
		
		CustomerDetailsDto customerDetailsDto = customerDetailsService.fetchCustomerDetails(mobileNumber, correlationId);
		
		return ResponseEntity.status(HttpStatus.OK).body(customerDetailsDto);
		
	}
	
}
