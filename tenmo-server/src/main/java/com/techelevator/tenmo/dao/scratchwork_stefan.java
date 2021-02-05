package com.techelevator.tenmo.dao;

import java.math.BigDecimal;

public class scratchwork_stefan {

	
	/*
	 * Get balance (a big decimal) - account_from
	 * from 'User ID'
	 * 
	 * try to get balance - which means there are exceptions?
	 * 
	 * 
	 */
	
	
	public BigDecimal getBalance(int userId) {
		String balanceString = "SELECT balance FROM accounts WHERE user_id = ?";
		try {
		
		}
		catch {
			
		}
		return null;
	}
	
	
	//add balance - account_to
	// need math to add balance
	
	String addBalanceString = "UPDATE accounts SET balance = ? WHERE user_id = ?";
	
	
	
	// subtract balance - account_from
	// need math to subtract balance
	
	String subtractBalanceString = "UPDATE accounts SET balance"
	
}
