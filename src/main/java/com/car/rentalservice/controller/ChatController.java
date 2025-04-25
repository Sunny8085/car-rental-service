//package com.car.rentalservice.controller;
//
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//
//import org.springframework.messaging.handler.annotation.MessageMapping;
//import org.springframework.messaging.handler.annotation.SendTo;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.CrossOrigin;
//
//import com.car.rentalservice.dto.ChatMessageDto;
//
//@CrossOrigin("*")
//@Controller
//public class ChatController {
//	
//	@MessageMapping("/chat")
//    @SendTo("/topic/messages")
//    public ChatMessageDto sendMessage(ChatMessageDto message) {
//        message.setTimestamp(LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
//        return message;
//	}
//
//	
//}
