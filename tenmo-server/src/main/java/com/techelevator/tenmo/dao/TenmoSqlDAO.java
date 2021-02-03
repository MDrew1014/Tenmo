package com.techelevator.tenmo.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.ArrayList;
import java.util.List;
import com.techelevator.tenmo.model.User;

public class TenmoSqlDAO implements TenmoDAO{
	private JdbcTemplate jdbcTemplate;
	
	public TenmoSqlDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

	@Override
	public double getBalance(String username) {
		return jdbcTemplate.queryForObject("Select balance FROM accounts " + 
				"JOIN users ON users.user_id = accounts.user_id " + 
				"WHERE username = ?", double.class, username);
		
	}

	@Override
	public List<User> list() {
		List<User> users = new ArrayList<>();
        String sql = "select * from users";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while(results.next()) {
            User user = mapRowToUser(results);
            users.add(user);
        }

        return users;
    }

	@Override
	public int transfer() {
		// TODO Auto-generated method stub
		return 0;
	}
	 private User mapRowToUser(SqlRowSet rs) {
	        User user = new User();
	        user.setId(rs.getLong("user_id"));
	        user.setUsername(rs.getString("username"));
	        user.setPassword(rs.getString("password_hash"));
	        user.setActivated(true);
	        user.setAuthorities("ROLE_USER");
	        return user;
	    }
	
//create interface methods
}
