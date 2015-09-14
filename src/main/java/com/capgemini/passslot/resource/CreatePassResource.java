package com.capgemini.passslot.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;


import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.capgemini.passslot.model.pass.EmailMessage;
import com.capgemini.passslot.model.pass.InputMessage;
import com.capgemini.passslot.model.pass.PassInfo;
import com.capgemini.passslot.model.pass.PassResponse;
import com.capgemini.passslot.response.StatusMessage;
import com.capgemini.passslot.utility.PassslotUtility;

@Path("/pass")
@Produces({ "application/json", "application/xml" })
@Consumes({ "application/json", "application/xml" })
public class CreatePassResource 
{
	private Logger log = Logger.getLogger(CreatePassResource.class);
	
	@Autowired
	@Qualifier("passutil")
	PassslotUtility passutil;
	
	
	/*
	 	{
			"firstname":"Makarand",
			"lastname":"Parab",
			"date":"18-06-1976",
			"email":"makarandparab@gmail.com"
		}
	 */

	@POST
	@Path("/create")
	public Response doCreateTemplate(String InputMsg) 
	{
		//log.info("Entered the doCreateTemplate method ::::::::::::: " + InputMsg);
		StatusMessage statusMsg = new StatusMessage();

		ObjectMapper mapper =  new ObjectMapper();
		@SuppressWarnings("deprecation")
		ObjectWriter writer = mapper.defaultPrettyPrintingWriter();		
		
		try
		{
			
			//1. Map the input json message with InputMessage object
			InputMessage inputMsg = mapper.readValue(InputMsg, InputMessage.class);
			//log.info(inputMsg.getEmail());
			
			//2. Map InputMessage object with PassInfo
			PassInfo passInfo = passutil.getPassInfo(inputMsg);
			//log.info(passInfo.getFirstname());
			String passInfoMsg = writer.writeValueAsString(passInfo);
			//log.info(passInfoMsg);
			
			//3. Call http method to execute the create pass rest service
			String responseBody = passutil.createPass(passInfoMsg);
			//log.info("responseBody of the pass call ::: " + responseBody);
			
			//4. Map the response of the service with PassResponse object
			PassResponse passResponse = mapper.readValue(responseBody, PassResponse.class);
			//log.info(passResponse.getSerialNumber());
			
			//5. Map the email from InputMessage to EmailMessage object
			EmailMessage emailMsg = new EmailMessage();
			emailMsg.setEmail(inputMsg.getEmail());
			String emailInfoMsg = writer.writeValueAsString(emailMsg);
			
			//6. Call http method to execute the send email rest service
			passutil.sendEmail(emailInfoMsg, passResponse);
			
			//7. Set the status message
			statusMsg.setStatusCode("200");
			statusMsg.setStatusMessage("SUCCESS");
		}
		catch(Exception e)
		{
			statusMsg.setStatusCode("404");
			statusMsg.setStatusMessage("FAILURE");
			e.printStackTrace();
		}
		
		
		return Response.ok().entity(statusMsg).build();
	}
	
	public PassslotUtility getPassutil() 
	{
		return passutil;
	}

	public void setPassutil(PassslotUtility passutil) 
	{
		this.passutil = passutil;
	}
}
