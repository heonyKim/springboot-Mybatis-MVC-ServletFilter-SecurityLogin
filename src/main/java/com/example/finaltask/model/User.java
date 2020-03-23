package com.example.finaltask.model;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
	private int id;
	private String username;
	private String password;
	private Timestamp createDate;
}
