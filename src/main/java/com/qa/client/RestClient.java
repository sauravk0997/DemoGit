 package com.qa.client;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class RestClient {
	
	//1.GET Method
	public void GET(String url) throws ClientProtocolException, IOException {
		
		CloseableHttpClient httpclient = HttpClients.createDefault(); //it creates one client connection
		HttpGet httpget = new HttpGet(url);//It just create one GET connection with url. This also a http GET request
		CloseableHttpResponse httpresponse = httpclient.execute(httpget);//it hit the GET url(Send) that is available in httpget
		
		//httpclient.execute(httpget) hit the GET URL and return the response that response we store in httpresponse
		//the complete response with http, headers, status everything store in httpresponse
		
		
		//1.STATUS COSE
		
		int statsuscode = httpresponse.getStatusLine().getStatusCode();
		//we fetch status code from httpresponse
		
		System.out.println("The stsus of API is -->"  + statsuscode);
		
		//2..JSON STRING
		
		//Now we have to fetch the string body of response from httpresponse
		
		String responsestring = EntityUtils.toString(httpresponse.getEntity(), "UTF-8");
		//Entity can't provide JSON directly. So, we have converted JSON into string
		
		//Now we have to convert the string into JSON
		JSONObject responsejson  = new JSONObject(responsestring);
		System.out.println("JSON response of API is " + responsejson);
		
		//3.ALL HEADERS
		
		//To fetch the headers details from API..This is also store in httpresponse
		
		Header[] headerarray = httpresponse.getAllHeaders();
		
		//As header in form of <key, value>, so we convert Header[] to HashMap. Hash Map store data in form of <key, value>
		
		HashMap<String, String> allHeaders = new HashMap<String, String>();
		
		for(Header hd:headerarray){
			allHeaders.put(hd.getName(), hd.getValue());
			
		}
		System.out.println("Header of API is " + allHeaders);
		
		
		
	}
	
		
	
	

}
