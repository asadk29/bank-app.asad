package com.asadk.cards.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.asadk.cards.dto.ErrorResponseDto;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{

	@ExceptionHandler(CardAlreadyExistException.class)
	public ResponseEntity<ErrorResponseDto> handleCardAlreadyExist(CardAlreadyExistException ex, WebRequest webRequest){
		
		ErrorResponseDto errorResponse = new ErrorResponseDto();
		
		errorResponse.setApiPath(webRequest.getDescription(false));
		errorResponse.setErrorCode(HttpStatus.BAD_REQUEST);
		errorResponse.setErrorMsg(ex.getMessage());
		errorResponse.setErrorTime(LocalDateTime.now());
		
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorResponseDto> handleCardAlreadyExist(ResourceNotFoundException ex, WebRequest webRequest){
		
		ErrorResponseDto errorResponse = new ErrorResponseDto();
		
		errorResponse.setApiPath(webRequest.getDescription(false));
		errorResponse.setErrorCode(HttpStatus.NOT_FOUND);
		errorResponse.setErrorMsg(ex.getMessage());
		errorResponse.setErrorTime(LocalDateTime.now());
		
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponseDto> handleCardAlreadyExist(Exception ex, WebRequest webRequest){
		
		ErrorResponseDto errorResponse = new ErrorResponseDto();
		
		errorResponse.setApiPath(webRequest.getDescription(false));
		errorResponse.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR);
		errorResponse.setErrorMsg(ex.getMessage());
		errorResponse.setErrorTime(LocalDateTime.now());
		
		return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	@Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request){
		
		Map<String, String> validationErrors = new HashMap<>();
		
		List<ObjectError> validationErrorList = ex.getBindingResult().getAllErrors();
		
		validationErrorList.forEach(error -> {
			
			String fieldName = ((FieldError) error).getField();
			String validationMessage = error.getDefaultMessage();
			
			validationErrors.put(fieldName, validationMessage);
			
		});
		
		return new ResponseEntity<>(validationErrors, HttpStatus.BAD_REQUEST);
		
	}
	
	
	
}
