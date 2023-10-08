package com.cafe.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.cafe.wrapper.UserWrapper;



public interface UserService {

	
	ResponseEntity<String> signup(Map<String ,String> requestMap);
	
	ResponseEntity<String> login(Map<String ,String> requestMap);
	
	ResponseEntity<List<UserWrapper>> getAllUser();

	
}
