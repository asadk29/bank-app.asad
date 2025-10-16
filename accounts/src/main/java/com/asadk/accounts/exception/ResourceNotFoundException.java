package com.asadk.accounts.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{

	public ResourceNotFoundException(String resourceType, String fieldName, String fieldValue) {
		
		super(String.format("%s is not found with data %s and its value %s", resourceType,fieldName, fieldValue));
		
	}
	
}
