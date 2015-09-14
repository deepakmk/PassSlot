package com.capgemini.passslot.utility;


import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.log4j.Logger;

import com.capgemini.passslot.model.pass.InputMessage;
import com.capgemini.passslot.model.pass.PassInfo;
import com.capgemini.passslot.model.pass.PassResponse;



public class PassslotUtility 
{
	private String authorizeHeader;
	private String passslotId;
	
	private Logger log = Logger.getLogger(PassslotUtility.class);
	
	public PassInfo getPassInfo(InputMessage inputMsg)
	{
		PassInfo passInfo = new PassInfo();
		passInfo.setFirstname(inputMsg.getFirstname());
		passInfo.setLastname(inputMsg.getLastname());
		passInfo.setDate(inputMsg.getDate());
		return passInfo;
	}
	
	@SuppressWarnings("deprecation")
	public String createPass(String inputMsg)
	{
		PostMethod post = null;
		String responseBody = "";
		StringBuilder builder = new StringBuilder();
		
		try
		{
				
			builder = builder.append("https://api.passslot.com/v1/templates/").append(passslotId).append("/pass");
			String url =  builder.toString();
			
			final HttpClient client = new HttpClient();
			post = new PostMethod(url);
			post.setRequestBody(inputMsg);
			post.setRequestHeader("Content-type", "application/json");
			post.setRequestHeader("Authorization", authorizeHeader);
			
			int status = client.executeMethod(post);
			//log.info("Status of the pass call ::: " + status);
			
			responseBody = post.getResponseBodyAsString();			
			
			post.releaseConnection();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			post.releaseConnection();
			builder.setLength(0);
			log.info("releasing the post connection in createPass");
		}
		return responseBody;
	}
	
	
	
	@SuppressWarnings("deprecation")
	public void sendEmail(String emailMsg, PassResponse passResponse)
	{
		PostMethod post = null;
		StringBuilder builder = new StringBuilder();
		
		try
		{
			builder = builder.append("https://api.passslot.com/v1/passes/")
								.append(passResponse.getPassTypeIdentifier()).append("/")
									.append(passResponse.getSerialNumber()).append("/")
										.append("email");
			
			String url =  builder.toString();
			
			final HttpClient client = new HttpClient();
			post = new PostMethod(url);
			post.setRequestBody(emailMsg);
			post.setRequestHeader("Content-type", "application/json");
			post.setRequestHeader("Authorization", authorizeHeader);
			
			int status = client.executeMethod(post);
			//log.info("Status of the pass call ::: " + status);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			post.releaseConnection();
			builder.setLength(0);
			log.info("releasing the post connection in sendEmal");
		}
	}
	
	
	
	
	public String getAuthorizeHeader() 
	{
		return authorizeHeader;
	}

	public void setAuthorizeHeader(String authorizeHeader) 
	{
		this.authorizeHeader = authorizeHeader;
	}

	public String getPassslotId() 
	{
		return passslotId;
	}

	public void setPassslotId(String passslotId) 
	{
		this.passslotId = passslotId;
	}
}
