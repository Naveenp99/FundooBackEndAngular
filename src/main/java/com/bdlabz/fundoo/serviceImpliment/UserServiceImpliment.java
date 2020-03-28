package com.bdlabz.fundoo.serviceImpliment;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.bdlabz.fundoo.Dto.UserDto;
import com.bdlabz.fundoo.Dto.UserForgetDto;
import com.bdlabz.fundoo.Dto.UserLoginDto;
import com.bdlabz.fundoo.Dto.UserresetDto;
import com.bdlabz.fundoo.entitymodel.User;
import com.bdlabz.fundoo.repository.UserRepository;
import com.bdlabz.fundoo.service.UserService;
import com.bdlabz.fundoo.util.Jwt;
import com.bdlabz.fundoo.util.Mail;

@Service
public class UserServiceImpliment implements UserService{

	@Autowired
	UserRepository repository;
	@Autowired
	Jwt jwt;
	@Autowired
	Mail mail;
	
	BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
	
	Date date = new Date();
	
	@Override
	public boolean register(UserDto dto)  {
		Date date = new Date();
		try {
	//		String pass = jwt.encoder(dto.getPassword());
	
			User checkUser = repository.findOneByemail(dto.getEmail());
			
			if(checkUser != null) 
				throw new IllegalArgumentException();
			
		User user = new User();
	
		user.setFirstname(dto.getFirstname());
		user.setLastname(dto.getLastname());
		user.setEmail(dto.getEmail());
		user.setPassword(bc.encode(dto.getPassword()));
		user.setDate(date); 
		repository.save(user);
		
			String token = "http://localhost:8080/user/verifying/"+jwt.getToken(user.getId());
			System.out.println("token.... "+token);
			mail.sendVerificationMail(user.getEmail(), token);
			return true;
		
		} catch (Exception  e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public String login(UserLoginDto dto) {
		User user = repository.findOneByemail(dto.getEmail());
		
//		System.out.println("naveeeeee"+jwt.encoder(dto.getPassword()));
//		System.out.println("LOGIN"+user);
		
		if(user != null && user.getEmail().equals(dto.getEmail()) && bc.matches(dto.getPassword(),user.getPassword())) {
			String token = null;
			try {
				token = jwt.getToken(user.getId());
			} catch (Exception  e) {
				e.printStackTrace();
			}
			return token;
		}
		return "null";
	}

	@Override
	public boolean verifyEmail( String token) {
		try {
			
			long email = jwt.idDetails(token);
			
			User user = repository.findOneById(email);
			
			if(user != null) {
				if(!user.isIs_mail_verified()) {
					user.setIs_mail_verified(true);
					repository.save(user);
					return true;
				}
			}
			
		} catch (Exception  e) {
			e.printStackTrace();
		}
		
		
		
		return false;
	}

	@Override
	public boolean forgetPassword( UserForgetDto dto) {
		
		User user = repository.findOneByemail(dto.getEmail());
		if(user != null && user.isIs_mail_verified() == true) {
			String token = "http://localhost:4200/resetpassword/"+jwt.getToken(user.getId());
			mail.sendVerificationMail(dto.getEmail(), token);
			return true;
		}
		return false;
	}

	@Override
	public boolean resetPassword(String token, UserresetDto dto) {
		if(dto.getPassword().equals(dto.getCon_password())) {
		long id = jwt.idDetails(token);
		User user = repository.findOneById(id);
		if(user != null) {
			user.setPassword(bc.encode(dto.getCon_password()));
			user.setDate(date);
			repository.save(user);
			return true;
		}
		
		}
		return false;
	}

	@Override
	public User getallUserdetailes(String token) {
		  long id = jwt.idDetails(token);
		  User user = repository.findOneById(id);
		  if( user != null) 
			return user;
		  else
		return null;
	}


}
