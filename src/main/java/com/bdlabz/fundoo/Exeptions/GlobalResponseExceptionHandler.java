package com.bdlabz.fundoo.Exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.bdlabz.fundoo.util.Response;

@RestControllerAdvice
public class GlobalResponseExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler( {IllegalArgumentException.class,JWTDecoderException.class,JWTVerificationException.class,
		                        TokenNotFoundExeption.class})
	public ResponseEntity<Response> handleAllUserException(Exception exception) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response(exception.getMessage(), 401));
	}
	
}
