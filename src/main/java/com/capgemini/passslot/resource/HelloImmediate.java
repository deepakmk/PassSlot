package com.capgemini.passslot.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.capgemini.passslot.response.StatusMessage;

@Path("/hello")
@Produces({ "application/json", "application/xml" })
@Consumes({ "application/json", "application/xml" })
public class HelloImmediate 
{
	private Logger log = Logger.getLogger(HelloImmediate.class);
	
	@GET
	@Produces("application/json")
	@Path("/list")
	public Response listAccels() {
		System.out.println(".................listAccels");
		StatusMessage statusMsg = new StatusMessage();
		statusMsg.setStatusCode("200");
		statusMsg.setStatusMessage("SUCCESSSSSS");
		return Response.ok().entity(statusMsg).build();
	}
	
	@GET
	@Produces("application/json")
	@Path("/deepak")
	public Response deepak() {
		System.out.println(".................listAccels");
		StatusMessage statusMsg = new StatusMessage();
		statusMsg.setStatusCode("200");
		statusMsg.setStatusMessage("Deepak chnaged back");
		return Response.ok().entity(statusMsg).build();
	}
}
