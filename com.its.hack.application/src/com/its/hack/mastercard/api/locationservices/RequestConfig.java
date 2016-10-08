package com.its.hack.mastercard.api.locationservices;

import java.io.FileInputStream;
import java.io.InputStream;

import com.mastercard.api.core.security.oauth.OAuthAuthentication;

public class RequestConfig {
	private String consumerKey;
	
	private String keyAlias;
	
	private String passwordValue;
	
	private String authenticationFile;
	
	public RequestConfig(String consumerKey, String keyAlias, String passwordValue, String inputFilePath)
	{
		this.consumerKey = consumerKey;
		this.keyAlias = keyAlias;
		this.passwordValue = passwordValue;
		this.authenticationFile = inputFilePath;
	}
	
	public String getConsumerKey() {
		return consumerKey;
	}

	public void setConsumerKey(String consumerKey) {
		this.consumerKey = consumerKey;
	}

	public String getKeyAlias() {
		return keyAlias;
	}

	public void setKeyAlias(String keyAlias) {
		this.keyAlias = keyAlias;
	}

	public String getPasswordValue() {
		return passwordValue;
	}

	public void setPasswordValue(String passwordValue) {
		this.passwordValue = passwordValue;
	}
	
	public OAuthAuthentication getAuthentication() 
	{
		try (InputStream inputStream = new FileInputStream(authenticationFile))
		{
			return new OAuthAuthentication(consumerKey, inputStream, keyAlias, passwordValue);			
		}
		catch (Exception ex)
		{
			System.out.println("Authentication failed " + ex.getMessage());
			return null;
		}
	}
}
