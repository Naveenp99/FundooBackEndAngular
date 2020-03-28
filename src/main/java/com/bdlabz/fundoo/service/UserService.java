package com.bdlabz.fundoo.service;

import javax.validation.Valid;
import com.bdlabz.fundoo.Dto.UserDto;
import com.bdlabz.fundoo.Dto.UserForgetDto;
import com.bdlabz.fundoo.Dto.UserLoginDto;
import com.bdlabz.fundoo.Dto.UserresetDto;
import com.bdlabz.fundoo.entitymodel.User;

public interface UserService {

	boolean register(UserDto dto);
	
	String login(UserLoginDto dto);
	
	boolean verifyEmail(@Valid String token);
	
	boolean forgetPassword( UserForgetDto dto);
	
	boolean resetPassword(String token, UserresetDto dto);
	
	User getallUserdetailes( String token);
	
}
