package com.techelevator.tenmo.dao;

import java.util.List;

import com.techelevator.tenmo.model.User;

public interface TenmoDAO {
	
	double getBalance(String username);
	List<User> list();
	int transfer();

}
