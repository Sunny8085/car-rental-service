package com.car.rentalservice.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.car.rentalservice.dto.EmailDetails;

@Component
public class EmailService {
	
	@Autowired 
	private JavaMailSender javaMailSender;
	 
    @Value("${spring.mail.username}") private String sender;
	
    public String sendSimpleMail(EmailDetails details)
    {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(sender);
            mailMessage.setTo(details.getRecipient());
            mailMessage.setText(details.getMsgBody());
            mailMessage.setSubject(details.getSubject());
            javaMailSender.send(mailMessage);
            return "OTP Sent Successfully...";
        }
        catch (Exception e) {
            return "Error while Sending Mail";
        }
    }
}
