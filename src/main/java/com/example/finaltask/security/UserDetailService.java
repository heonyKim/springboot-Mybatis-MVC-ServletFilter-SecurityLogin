package com.example.finaltask.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.finaltask.model.User;
import com.example.finaltask.repository.UserRepository;



@Service
public class UserDetailService implements UserDetailsService{

	@Autowired
	private UserRepository rep;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = rep.findByUsername(username);
		UserDetail userDetails = null;
		
		if(user != null) {
			userDetails = new UserDetail();
			userDetails.setUser(user);
		} else {
			throw new UsernameNotFoundException("Username not found");
		}
		return userDetails;
	}

}