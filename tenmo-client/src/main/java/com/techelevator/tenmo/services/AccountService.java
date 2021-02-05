package com.techelevator.tenmo.services;

import java.io.InputStream;
import java.io.OutputStream;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import com.techelevator.tenmo.models.AuthenticatedUser;
import com.techelevator.tenmo.models.TenmoAccount;
import com.techelevator.tenmo.models.Transfer;
import com.techelevator.tenmo.models.UserCredentials;
import com.techelevator.view.ConsoleService;



public class AccountService {
	  public String AUTH_TOKEN = "";
	  private final String INVALID_ACCOUNT_MSG = "Invalid Account";
	  private final String BASE_URL;
	  private final RestTemplate restTemplate = new RestTemplate();
	 // private final ConsoleService console = new ConsoleService(InputStream, OutputStream );
	  public AccountService(String url) {
		  this.BASE_URL = url;
	  }
public TenmoAccount getTenmoAccount() throws AccountServiceException {
	TenmoAccount tenmoAccount = null;
	try{
		tenmoAccount = restTemplate.exchange(BASE_URL +"tenmo/account/balance",HttpMethod.GET, makeAuthEntity(), TenmoAccount.class)
				.getBody();
	} catch(RestClientResponseException ex) {
		throw new AccountServiceException(ex.getRawStatusCode()+ " : " + ex.getResponseBodyAsString());
	}
		return tenmoAccount;
	}
	
public Transfer[] listTransfer() throws AccountServiceException {
	Transfer[] transfer = null;
	try {
		transfer = restTemplate.exchange(BASE_URL + "tenmo/account/transfer", HttpMethod.GET, makeAuthEntity(), Transfer[].class)
				.getBody();
	}catch(RestClientResponseException ex) {
		throw new AccountServiceException(ex.getRawStatusCode()+ " : " + ex.getResponseBodyAsString());
	}
	return transfer;
	
}
	
	
	
	HttpEntity makeAuthEntity() {
	    HttpHeaders headers = new HttpHeaders();
	    headers.setBearerAuth(AUTH_TOKEN);
	    HttpEntity entity = new HttpEntity<>(headers);
	    return entity;
	  

	
	
}














}
