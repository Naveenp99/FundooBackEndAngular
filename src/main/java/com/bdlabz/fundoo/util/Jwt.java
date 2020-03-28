package com.bdlabz.fundoo.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

@Component
public class Jwt {
	static BCryptPasswordEncoder bc = new BCryptPasswordEncoder();

	
	public String encoder(String str) {
	
		return bc.encode(str);
	}

	public  boolean matcher(String pas, String enpas) {
		return bc.matches(pas, enpas);
	}

	private static final String SECRET_KEY = "6360803337";

	public String getToken(Long userid)
			 {
		String token = null;
		try {
			token =  JWT.create().withClaim("id",userid).sign(Algorithm.HMAC256(SECRET_KEY));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return token;
	}

	public Long idDetails(String jwt)  
			{
		
		System.out.println("NAVEENAGRAHARA"+jwt);
		 
		Long userid =(long) 0;
		if(jwt != null) {
			userid = JWT.require(Algorithm.HMAC256(SECRET_KEY)).build().verify(jwt).getClaim("id").asLong();
		}
		return userid;

	}
    
}
