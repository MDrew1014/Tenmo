package com.techelevator.tenmo.dao;

import java.util.List;

import com.techelevator.tenmo.model.User;

public interface TenmoDAO {
	
	int getBalance();
	List<User> list();
	int transfer();

}
