package com.code.durgeshg.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(RsourseNotFoundException.class)
	public ResponseEntity<ApiResponse> resousceNotFoundException(RsourseNotFoundException ex){
		String mesg=ex.getMessage();
		ApiResponse s=new ApiResponse(mesg,false);
		return new ResponseEntity<ApiResponse>(s,HttpStatus.NOT_FOUND);
		
	}
	//it is alredy class
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String,String>> hsandleMethodNotValideException(MethodArgumentNotValidException ex){
		Map<String,String> error=new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((er)->{
			String fieldError=((FieldError)er).getField();
			String message=er.getDefaultMessage();
			error.put(fieldError, message);
		}
		
				);
		
		return new ResponseEntity<Map<String,String>> (error,HttpStatus.BAD_REQUEST);
	}
	
}










