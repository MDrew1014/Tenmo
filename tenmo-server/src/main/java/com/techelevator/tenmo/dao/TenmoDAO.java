package com.techelevator.tenmo.dao;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.User;

public interface TenmoDAO {
	
	BigDecimal getBalance(String username);
	void transfer() throws SQLException;
	List<Transfer> listTransfers(int userId);
	Transfer transferById(int transferId);
	

}
