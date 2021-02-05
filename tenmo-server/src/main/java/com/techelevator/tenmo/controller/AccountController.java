package com.techelevator.tenmo.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.tenmo.dao.TenmoDAO;
import com.techelevator.tenmo.dao.UserDAO;
import com.techelevator.tenmo.model.TenmoAccount;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.TransferRequest;
@RequestMapping("/tenmo/account")
@RestController
public class AccountController {
	private TenmoDAO tDAO;
	private UserDAO uDAO;
	
	
	public AccountController(TenmoDAO tDAO, UserDAO uDAO) {
		this.tDAO = tDAO;
		this.uDAO = uDAO;
	}


	@PreAuthorize("isAuthenticated()")
	@RequestMapping(path = "/balance", method = RequestMethod.GET )
	public TenmoAccount getBalance(Principal principal) {
		String username = principal.getName();
		
		//TODO get user name for principal use userDAO to get user ID
		//TODO use user id to get user account from tenmoDAO(make get account by user ID)

		return tDAO.getBalance(username);

	}
	
	@PreAuthorize("isAuthenticated()")
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(path = "/transfer", method = RequestMethod.POST)
	public void transfer(@RequestBody TransferRequest request, Principal principal) throws Exception {
		String username = principal.getName();
		int userId = this.uDAO.findIdByUsername(username);
		if(request.getUserIdFrom()!= userId) {
			//TODO throw exception here 400 or forbidden
			
		}tDAO.transfer(request);
		//fill out transfer class from user to user amount
		//principal.getName()
		

	}
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(path = "/transfer", method = RequestMethod.GET)
	public List<Transfer> listTransfers(Principal principal){
		String username = principal.getName();
		int userId = this.uDAO.findIdByUsername(username);
		return tDAO.listTransfers(userId);
	}
	@PreAuthorize("isAuthenticated()")
	@RequestMapping(path = "/transfer/{transferId}", method = RequestMethod.GET)
	public Transfer transferById(@PathVariable int transferId){
		return tDAO.transferById(transferId);
		
		
		
		
	}

}
