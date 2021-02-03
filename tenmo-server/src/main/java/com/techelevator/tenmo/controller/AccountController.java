package com.techelevator.tenmo.controller;

import java.security.Principal;

import org.springframework.http.HttpMethod;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.tenmo.dao.TenmoDAO;
import com.techelevator.tenmo.dao.UserDAO;
import com.techelevator.tenmo.model.TenmoAccount;
@RequestMapping("/tenmo/account")
@RestController
public class AccountController {
	
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(path = "/balance", method = RequestMethod.GET )
	public TenmoAccount getBalance(Principal principal) {
		return new TenmoAccount(999,888, 1000.00);
	}
		
	
}
