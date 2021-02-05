package com.techelevator.tenmo.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.techelevator.tenmo.model.TenmoAccount;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.TransferRequest;
import com.techelevator.tenmo.model.User;
@Component
public class TenmoSqlDAO implements TenmoDAO{
	private JdbcTemplate jdbcTemplate;
	private DataSource dataSource;
	
	public TenmoSqlDAO(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.dataSource = dataSource;
    }

	@Override
	public TenmoAccount getBalance(String username) {
	SqlRowSet result =	jdbcTemplate.queryForRowSet("Select accounts.* FROM accounts " + 
				"JOIN users ON users.user_id = accounts.user_id " + 
				"WHERE username = ?", username); 
	if(result.next()) {
		return new TenmoAccount(result.getInt("account_id"), result.getInt("user_id"), result.getBigDecimal("balance"));
	}		
		
		return null;
		
	}

	

	@Override
	public void transfer(TransferRequest request) throws SQLException {
		try{
			this.dataSource.getConnection().setAutoCommit(false);
			
			
			
			
			
			
			
			
			this.dataSource.getConnection().commit();
		}
		catch(Exception e) {
			this.dataSource.getConnection().rollback();
			
		}finally {
			this.dataSource.getConnection().setAutoCommit(true);
		}
	}
	 


	@Override
	public List<Transfer> listTransfers(int userId) {
		List<Transfer> transfer = new ArrayList<>();
		String query = "SELECT t.*, u.username AS userFrom, v.username AS userTo FROM transfers " + 
				"JOIN accounts a ON t.account_from = a.account_id " + 
				"JOIN accounts b ON t.account_to = b.account_id " + 
				"JOIN users u ON a.user_id = u.user_id " + 
				"JOIN users v ON b.user_id = v.user_id ;" + 
				"WHERE a.user_id = ? OR b.user_id = ?";
		SqlRowSet results = this.jdbcTemplate.queryForRowSet(query, userId, userId);
		while(results.next()) {
			Transfer resultTransfer = this.mapRowToTransfer(results);
			transfer.add(resultTransfer);
		}
		return transfer;
	}

	@Override
	public Transfer transferById(int transferId) {
		// TODO Auto-generated method stub
		SqlRowSet results = jdbcTemplate.queryForRowSet("SELECT * FROM transfer WHERE transfer_id = ?", transferId);
		if(results.next()) {
			return mapRowToTransfer(results);
		}return null;
	}
	
	private Transfer mapRowToTransfer(SqlRowSet rs) {
       Transfer transfer = new Transfer();
        transfer.setAccountFrom(rs.getInt("account_from"));
        transfer.setAccountTo(rs.getInt("account_to"));
        transfer.setAmount(rs.getBigDecimal("amount"));
        return transfer;
        
    }

	
	
}
