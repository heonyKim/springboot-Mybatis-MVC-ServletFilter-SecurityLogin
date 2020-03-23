package com.example.finaltask.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.finaltask.model.User;
import com.example.finaltask.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository uRepo;
	
	public int join(User user) {
		return uRepo.save(user);
	}
}
