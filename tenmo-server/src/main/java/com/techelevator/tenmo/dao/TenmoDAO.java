package com.techelevator.tenmo.dao;

import java.math.BigDecimal;
import java.util.List;

import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.User;

public interface TenmoDAO {
	
	BigDecimal getBalance(String username);
	void transfer();
	List<Transfer> listTransfers(int userId);
	Transfer transferById(int transferId);
	

}
