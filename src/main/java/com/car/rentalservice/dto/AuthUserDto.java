package com.car.rentalservice.dto;

import java.time.LocalDateTime;

import com.car.rentalservice.model.Roles;

import lombok.Data;

@Data
public class AuthUserDto {
	
	private String userName;
	private String email;
	private String password;
	private String mobileNo;
	private String otp;
	private String roleId;
	private String roleName;
	private LocalDateTime loginStamp;
	
    public AuthUserDto(String userName, String email,String password, String mobileNo,String otp, Roles roles, LocalDateTime loginStamp) {
       this.userName = userName;
       this.email = email;
       this.password = password;
       this.mobileNo = mobileNo;
       this.otp = otp;
       this.roleId = (roles != null) ? roles.getRoleId() : null;
       this.roleName = (roles != null) ? roles.getRoleName() : null;
       this.loginStamp = loginStamp;
   }
	
}
