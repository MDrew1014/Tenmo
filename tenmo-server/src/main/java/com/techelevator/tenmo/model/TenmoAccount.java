package com.techelevator.tenmo.model;

import java.math.BigDecimal;

public class TenmoAccount {
	private int accountId;
	private int userId;
	private double balance;

	public TenmoAccount(int accountId, int userId) {
		this.accountId = accountId;
		this.userId = userId;
		this.balance = 1000.00;
	}

	public TenmoAccount(int accountId, int userId, double balance) {
		this.accountId = accountId;
		this.userId = userId;
		this.balance = balance;
	}

	public int getAccountId() {
		return accountId;
	}

	public int getUserId() {
		return userId;
	}

	public double getBalance() {
		return balance;
	}
	
	
	
	
	
	
	
	
	
}
