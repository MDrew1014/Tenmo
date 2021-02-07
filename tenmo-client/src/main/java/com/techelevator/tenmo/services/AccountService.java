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
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import com.techelevator.tenmo.models.AuthenticatedUser;
import com.techelevator.tenmo.models.TenmoAccount;
import com.techelevator.tenmo.models.Transfer;
import com.techelevator.tenmo.models.TransferRequest;
import com.techelevator.tenmo.models.User;
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
public User[] listUsers() throws AccountServiceException {
	User[] user = null;
	try {
		user = restTemplate.exchange(BASE_URL + "tenmo/account/users", HttpMethod.GET, makeAuthEntity(), User[].class)
				.getBody();
	}catch(RestClientResponseException ex) {
		throw new AccountServiceException(ex.getRawStatusCode()+ " : " + ex.getResponseBodyAsString());
	}
	return user;
}
public boolean startTransfer(TransferRequest transfer){
	String requestUrl = BASE_URL + "tenmo/account/transfer";
	HttpEntity<TransferRequest> entity = makeTransferRequestEntity(transfer);
	ResponseEntity<String> response =restTemplate.exchange(requestUrl, HttpMethod.POST,entity, String.class);
	return response.getStatusCodeValue()==201;
}
	
	
	private HttpEntity makeAuthEntity() {
		HttpHeaders headers = new HttpHeaders();
		headers.setBearerAuth(AUTH_TOKEN);
		HttpEntity entity = new HttpEntity<>(headers);
		return entity;
	}

	private HttpEntity<TransferRequest> makeTransferRequestEntity(TransferRequest transfer){
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setBearerAuth(AUTH_TOKEN);
		HttpEntity<TransferRequest> entity = new HttpEntity<>(transfer, headers);
		return entity;
	}
	

}















