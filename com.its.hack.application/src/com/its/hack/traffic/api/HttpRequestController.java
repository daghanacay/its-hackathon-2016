package com.its.hack.traffic.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ProxySelector;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.SystemDefaultRoutePlanner;

public class HttpRequestController {
	
	private CloseableHttpClient httpclient;
	
	public HttpRequestController() {
		httpclient = createHttpClientOrProxy();
	}
	
	public HttpResponse getRequest(String url, String responseFormat) throws Exception
	{
		return getRequestResponse(url, responseFormat);
		
	}
	
	public void close() {
		try 
		{
			httpclient.close();
		} catch (IOException e) {
			System.out.println("Error closing http client: " + e.getMessage());
		}
	}
	
	
	private HttpResponse getRequestResponse(String url, String headerFormat) throws ClientProtocolException, IOException {
		HttpGet getRequest = new HttpGet(url);
		getRequest.addHeader("accept", headerFormat);
		return httpclient.execute(getRequest);
	}

	private CloseableHttpClient createHttpClientOrProxy() {
		return HttpClients.custom().setRoutePlanner(new
     		   SystemDefaultRoutePlanner(ProxySelector.getDefault()))
     		             .build();
	}

	public String convertStreamToString(InputStream is) {

	    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
	    StringBuilder sb = new StringBuilder();

	    String line = null;
	    try {
	        while ((line = reader.readLine()) != null) {
	            sb.append(line + "\n");
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            is.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	    return sb.toString();
	}
}
