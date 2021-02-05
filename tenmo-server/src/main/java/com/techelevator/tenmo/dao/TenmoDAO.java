package com.techelevator.tenmo.dao;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import com.techelevator.tenmo.model.TenmoAccount;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.TransferRequest;
import com.techelevator.tenmo.model.User;

public interface TenmoDAO {
	
	TenmoAccount getBalance(String username);
	void transfer(TransferRequest request) throws SQLException, Exception;
	List<Transfer> listTransfers(int userId);
	Transfer transferById(int transferId);
	//List<User> listUsers();

}
