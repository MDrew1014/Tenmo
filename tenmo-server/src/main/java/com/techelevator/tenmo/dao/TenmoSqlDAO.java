package com.techelevator.tenmo.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.User;

public class TenmoSqlDAO implements TenmoDAO{
	private JdbcTemplate jdbcTemplate;
	
	public TenmoSqlDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

	@Override
	public BigDecimal getBalance(String username) {
		return jdbcTemplate.queryForObject("Select balance FROM accounts " + 
				"JOIN users ON users.user_id = accounts.user_id " + 
				"WHERE username = ?", BigDecimal.class, username);
		
	}

	

	@Override
	public void transfer() {
		// TODO Auto-generated method stub
		
	}
	 


	@Override
	public List<Transfer> listTransfers(int userId) {
		List<Transfer> transfer = new ArrayList<>();
		String query = "SELECT t.*, u.username AS userFrom, v.username AS userTo FROM transfers " + 
				"JOIN accounts a ON t.account_from = a.account_id " + 
				"JOIN accounts b ON t.account_to = b.account_id " + 
				"JOIN users u ON a.user_id = u.user_id " + 
				"JOIN users v ON b.user_id = v.user_id ;" + 
				"WHERE a.user_id = ?";
		SqlRowSet results = this.jdbcTemplate.queryForRowSet(query);
		while(results.next()) {
			Transfer resultTransfer = this.mapRowToUser(results);
			transfer.add(resultTransfer);
		}
		return transfer;
	}

	@Override
	public Transfer transferById(int transferId) {
		// TODO Auto-generated method stub
		return jdbcTemplate.queryForObject("SELECT t.*, u.username AS userFrom, v.username AS userTo, ts.transfer_status_desc, tt.transfer_type_desc FROM transfers " + 
				"JOIN accounts a ON t.account_from = a.account_id " + 
				"JOIN accounts b ON t.account_to = b.account_id " + 
				"JOIN users u ON a.user_id = u.user_id " + 
				"JOIN users v ON b.user_id = v.user_id " + 
				"JOIN transfer_statuses ts ON t.transfer_status_id = ts.transfer_status_id " + 
				"JOIN transfer_types tt ON t.transfer_type_id = tt.transfer_type_id " + 
				"WHERE t.transfer_id = ?", Transfer.class, transferId);
	}
	
	private Transfer mapRowToUser(SqlRowSet rs) {
       Transfer transfer = new Transfer();
        transfer.setAccountFrom(rs.getInt("account_from"));
        transfer.setAccountTo(rs.getInt("account_to"));
        transfer.setAmount(rs.getBigDecimal("amount"));
        return transfer;
        
    }
}
