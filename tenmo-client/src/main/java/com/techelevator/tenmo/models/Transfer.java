package com.techelevator.tenmo.models;

import java.math.BigDecimal;

public class Transfer {
	
	private int accountFrom;
	private int accountTo;
	private BigDecimal amount;
	public int getAccountFrom() {
		return accountFrom;
	}
	public void setAccountFrom(int accountFrom) {
		this.accountFrom = accountFrom;
	}
	public int getAccountTo() {
		return accountTo;
	}
	public void setAccountTo(int accountTo) {
		this.accountTo = accountTo;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public Transfer(int accountFrom, int accountTo, BigDecimal amount) {
		this.accountFrom = accountFrom;
		this.accountTo = accountTo;
		this.amount = amount;
	}
	public Transfer() {
		
	}
	
	
	

}
