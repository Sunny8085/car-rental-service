package com.car.rentalservice.dto;

import lombok.Data;

@Data
public class ChatMessageDto {
	
	private String sender;
    private String content;
    private String timestamp;
	
}
