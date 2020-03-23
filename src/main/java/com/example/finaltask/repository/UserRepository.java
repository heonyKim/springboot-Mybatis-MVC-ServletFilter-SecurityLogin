package com.example.finaltask.repository;

import com.example.finaltask.model.User;

public interface UserRepository {
	int save(User user);
	User findByUsername(String username);
}
