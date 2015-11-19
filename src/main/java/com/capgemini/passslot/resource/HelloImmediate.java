package com.capgemini.passslot.resource;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

import com.capgemini.passslot.response.StatusMessage;

@Path("/list")
@Produces({ "application/json", "application/xml" })
@Consumes({ "application/json", "application/xml" })
public class HelloImmediate 
{
	private Logger log = Logger.getLogger(HelloImmediate.class);
	
	@GET
	@Produces("application/json")
	@Path("/taxis")
	public Response listTaxis() {
		Map<String , String> taxis = new HashMap<String, String>();
		taxis.put("Ola", "Yellow");
		taxis.put("Uber", "Blue");
		taxis.put("Meru", "Green");
		return Response.ok().entity(taxis).build();
	}
	
	
}
