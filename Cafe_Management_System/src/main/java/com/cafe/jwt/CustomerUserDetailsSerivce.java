package com.cafe.jwt;

import java.util.ArrayList;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cafe.dao.UserDao;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class CustomerUserDetailsSerivce implements UserDetailsService {

	@Autowired
	UserDao userDao;
	
	private com.cafe.pojo.User userDetails;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		log.info("inside loaduser by username{}",username);
		userDetails=userDao.findByEmailId(username);
		if (!Objects.isNull(userDetails)) {
			return new User(userDetails.getEmail(),userDetails.getPassword(),new ArrayList<>());
		}
		else {
			throw new UsernameNotFoundException("User Not Found");
		}
	
	}
	public com.cafe.pojo.User getUserDetails(){
		return userDetails;
		 
	}
}