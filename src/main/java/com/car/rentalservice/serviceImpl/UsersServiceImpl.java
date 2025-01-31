package com.car.rentalservice.serviceImpl;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.car.rentalservice.dto.EmailDetails;
import com.car.rentalservice.dto.UsersDto;
import com.car.rentalservice.emums.Role;
import com.car.rentalservice.exception.ConflictException;
import com.car.rentalservice.exception.NotFoundException;
import com.car.rentalservice.mapper.UserMapper;
import com.car.rentalservice.model.Roles;
import com.car.rentalservice.model.Users;
import com.car.rentalservice.repository.RolesRepo;
import com.car.rentalservice.repository.UsersRepo;
import com.car.rentalservice.service.UsersService;
import com.car.rentalservice.util.EmailService;
import com.car.rentalservice.util.OTPGenerator;

@Service
public class UsersServiceImpl implements UsersService{
	
	@Autowired private UsersRepo userRepo;
	@Autowired private UserMapper userMapper;
	@Autowired private RolesRepo roleRepo;
	@Autowired private EmailService emailService;

	@Override
	public Users createUser(UsersDto user) {
		Optional<Users> found = userRepo.findById(user.getUsername());
		if(found.isPresent())
			throw new ConflictException("User already existss");
		else {
			Roles role = roleRepo.findById(Role.GUEST.getCode())
						.orElseThrow(() -> new NotFoundException("Role not found."));
			
			Users newUser = userMapper.mappToNewUser(user);
			newUser.setRoles(role);
			return userRepo.save(newUser);
		}
	}

	@Override
	public Users findUser(UsersDto loginRequest) {
		return userRepo.findByUserNameAndPassword(loginRequest.getUsername(),loginRequest.getPassword());
	}

	@Override
	public String genrateOTP(String username) {
		Users user = userRepo.findById(username).orElseThrow(() -> new NotFoundException("Invalid email id"));
		
		String otp = OTPGenerator.generateOTP();
		user.setOtp(otp);
		user.setOtpTime(LocalDateTime.now().plusMinutes(10));
		userRepo.save(user);
		
		String body = "Dear email, \n\n"+"We received a request to activate for your account."
				+ "Please use the following One-Time Password (OTP) to proceed with resetting your password: \n\n **OTP:**" +otp+ "\n\n"
				+ "This OTP is valid for the next 10 minutes. Please do not share this OTP with anyone. "
				+ "If you did not request to activate profile, please ignore this email or contact our support team immediately.\n\n"
				+ "Best regards,\n Car Rental Service Support Team";
		EmailDetails detail = new EmailDetails();
		detail.setRecipient(user.getEmail());
		detail.setMsgBody(body);
		detail.setSubject("Password Reset OTP for Your Account");
		emailService.sendSimpleMail(detail);
		
		return "OTP Send Sucessfully.";
	}
	
	@Override
	public String verifyOTP(String username, String OTP) {
		Users user = userRepo.findById(username).orElseThrow(() -> new NotFoundException("Invalid Username"));
		if(user.getOtpTime().isAfter(LocalDateTime.now())) {
			userRepo.updateOTPStaus(username);
		}else
			throw new NotFoundException("OTP Has Been Expired.");
		return "OTP verified successfully";
	}
	
	
	
}
