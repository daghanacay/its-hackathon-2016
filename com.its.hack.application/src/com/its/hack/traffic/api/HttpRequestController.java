package com.its.hack.traffic.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ProxySelector;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

import javax.security.cert.CertificateException;
import javax.security.cert.X509Certificate;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.SystemDefaultRoutePlanner;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;

public class HttpRequestController {

	private CloseableHttpClient httpclient;

	public HttpRequestController() {
		try {
			httpclient = createHttpClientOrProxy();
		} catch (KeyManagementException | NoSuchAlgorithmException | KeyStoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public HttpResponse getRequest(String url, String responseFormat) throws Exception {
		return getRequestResponse(url, responseFormat);

	}

	public void close() {
		try {
			httpclient.close();
		} catch (IOException e) {
			System.out.println("Error closing http client: " + e.getMessage());
		}
	}

	private HttpResponse getRequestResponse(String url, String headerFormat)
			throws ClientProtocolException, IOException {
		HttpGet getRequest = new HttpGet(url);
		getRequest.addHeader("accept", headerFormat);
		return httpclient.execute(getRequest);
	}

	private CloseableHttpClient createHttpClientOrProxy()
			throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
		SSLContextBuilder builder = new SSLContextBuilder();
		builder.loadTrustMaterial(null, new TrustStrategy() {
			@Override
			public boolean isTrusted(java.security.cert.X509Certificate[] arg0, String arg1)
					throws java.security.cert.CertificateException {
				return true;
			}
		});

		SSLConnectionSocketFactory sslSF = new SSLConnectionSocketFactory(builder.build(),
				SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

		return HttpClients.custom().setRoutePlanner(new SystemDefaultRoutePlanner(ProxySelector.getDefault()))
				.setSSLSocketFactory(sslSF).build();
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
