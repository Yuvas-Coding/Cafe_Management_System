package com.cafe.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cafe.pojo.User;
import com.cafe.wrapper.UserWrapper;



@Repository
public interface UserDao extends JpaRepository<User	,Serializable> {
	
	User findByEmailId(@Param("email") String email);
	
	List<UserWrapper> getAllUser();
	


}
