package com.bdlabz.fundoo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bdlabz.fundoo.Dto.UserDto;
import com.bdlabz.fundoo.Dto.UserForgetDto;
import com.bdlabz.fundoo.Dto.UserLoginDto;
import com.bdlabz.fundoo.Dto.UserresetDto;
import com.bdlabz.fundoo.entitymodel.User;
import com.bdlabz.fundoo.repository.UserRepository;
import com.bdlabz.fundoo.service.UserService;
import com.bdlabz.fundoo.util.Jwt;
import com.bdlabz.fundoo.util.Response;

@RestController
@RequestMapping(value = "/user")
@CrossOrigin(allowedHeaders = "*", origins = "*", exposedHeaders = { "jwtToken" })
public class UserController {
	@Autowired
	Jwt jwt;
	@Autowired
	UserRepository repos;
	@Autowired
	UserService uservice;
	
	@PostMapping(value = "/register")
	public ResponseEntity<Response> register(@RequestBody UserDto dto) {
	
		boolean is_registered = uservice.register(dto);
		
		if(is_registered == true)
			return ResponseEntity.status(HttpStatus.CREATED).body(new Response("Registration Successfull", 200));
		else
			return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body(new Response("Registration UnSuccessfull", 400));
	}
	
	
	@PostMapping(value = "/login")
	public ResponseEntity<Response> login(@RequestBody UserLoginDto dto) {
	String is_login = uservice.login(dto);
	
	User user = repos.findOneByemail(dto.getEmail());
	
	String message = jwt.getToken(user.getId());
	
	System.out.println("NNNNNNNNN"+message);
	
	System.out.println("LOGIN"+is_login);
	
   if(is_login != "null")
	return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response("Successfull", 200, message));
else
	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("login UnSuccessfully", 400));
	}

	@GetMapping(value = "/verifying")
	public ResponseEntity<Response> tokenVerify(@Valid @RequestHeader(value = "token") String token) {
		
		System.out.println("ABCDEFGHIJK"+token);
		
		boolean is_verified = uservice.verifyEmail(token);
		if(is_verified == true) 
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response("Verified Successfully", 200));
		else
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("Verified UnSuccessfully", 400));
	}
	
	@PostMapping(value = "/forgetPassword")
	public ResponseEntity<Response> forgetPassword(@RequestBody UserForgetDto dto) {
	boolean is_forgetPassword = uservice.forgetPassword(dto);
	
   if(is_forgetPassword == true)
	return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response("Successfull", 200));
else
	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Response("UnSuccessfull", 400));
	}
	
	@PostMapping(value = "/resetpassword")
	public ResponseEntity<Response> resetPassword(@RequestBody UserresetDto dto, 
			@RequestHeader("token") String token) {
		System.out.println("token in reset"+token);
	boolean is_resetPassword = uservice.resetPassword(token, dto);
	
   if(is_resetPassword == true)
	return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response("Successfull", 200));
else
	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("UnSuccessfull", 400));
	}
	
	@GetMapping(value = "/getAllUser")
	public ResponseEntity<Response> getallUser( @RequestHeader("token") String token) {
		User user = uservice.getallUserdetailes(token);
		if(user != null)
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Response("Successfull", 200, user));
		else
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response("UnSuccessfull", 400));
	}
	
}
