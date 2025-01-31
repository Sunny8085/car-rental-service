package com.car.rentalservice.serviceImpl;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RedisServiceImpl {
	
	@Autowired private RedisTemplate<String, Object> redisTemplate;
	
	public <T> T get(String key, Class<T> entity) {
		try {
			Object o = redisTemplate.opsForValue().get(key);
			ObjectMapper mapper = new ObjectMapper();
			return mapper.readValue(o.toString(), entity);
		}catch(Exception e) {
			log.error("Exception",e);
			return null;
		}
	}
	
	public <T> T getClosestVehicle(String key, TypeReference<T> entity) {
		try {
			Object o = redisTemplate.opsForValue().get(key);
			if(o != null) {
				ObjectMapper mapper = new ObjectMapper();
				mapper.registerModule(new JavaTimeModule());
				return mapper.readValue(o.toString(), entity);
			}else
				return null;
		}catch(Exception e) {
			log.error("Exception",e);
			return null;
		}
	}
	
	public void set(String key, Object o, Long time) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.registerModule(new JavaTimeModule());
			String jsonValue = mapper.writeValueAsString(o);
			redisTemplate.opsForValue().set(key, jsonValue,time,TimeUnit.SECONDS);
		}catch(Exception e) {
			log.error("Exception",e);
		}
	}
	
}
