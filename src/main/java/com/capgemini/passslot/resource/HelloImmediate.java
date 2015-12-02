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
		taxis.put("taxi4sure", "white");
		return Response.ok().entity(taxis).build();
	}

	@GET
	@Produces("application/json")
	@Path("/customers")
	public Response listCustomers() {
		Map<String , String> customers = new HashMap<String, String>();
		customers.put("Clinton", "Hillary");
		customers.put("Trump", "Donald");
		customers.put("Carson", "Ben");
		customers.put("Rubio", "Marco");
//        customers.put("Kundu","Kirit");
		return Response.ok().entity(customers).build();
	}
    @GET
    @Produces("application/json")
    @Path("/bankingServices")
    public Response listServices() {
        Map<String , String> services = new HashMap<String, String>();
        services.put("1", "Internet Banking");
        services.put("2", "Internet Banking Plus");
        services.put("3", "Text Message Banking");
		services.put("4","Paym");
      //  services.put("5", "Telephone banking");
        return Response.ok().entity(services).build();
    }
	
	@GET
	@Produces("application/json")
	@Path("/public")
	public Response publicTransport() {
		Map<String , String> publics = new HashMap<String, String>();
		publics.put("Bus", "Red");
		publics.put("Metro", "Grey");
		return Response.ok().entity(publics).build();
	}
	
}
