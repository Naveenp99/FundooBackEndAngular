package com.bdlabz.fundoo.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.bdlabz.fundoo.entitymodel.Notes;

@Component
public class Mail {

	@Autowired
	JavaMailSender mailSender;
	
	public void sendVerificationMail(String mail, String token) {
		
		System.out.println(token);
		
		SimpleMailMessage mess = new SimpleMailMessage();
		mess.setFrom("naveenpagrahara@gmail.com");
		mess.setTo(mail);
		mess.setSubject("Verification Mail....!!!!");
		mess.setText(token);
		mailSender.send(mess);
	}
	
	public void sendNotesColl(String mail,  Notes notes) {
		System.out.println("all notes: "+ notes);
		SimpleMailMessage mess = new SimpleMailMessage();
		mess.setFrom("naveenpagrahara@gmail.com");
		mess.setTo(mail);
		mess.setSubject("Notes");
		mess.setText(notes.toString());
		mailSender.send(mess);

	}
	
}
