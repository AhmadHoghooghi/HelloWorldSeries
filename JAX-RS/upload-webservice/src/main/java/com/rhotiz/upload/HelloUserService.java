package com.rhotiz.upload;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;


@Path("")
public class HelloUserService {
 
	@Path("{userName}")
	@GET
	public Response helloUser(@PathParam("userName") String usreName) {
		return Response.ok().entity("Hello "+usreName).build();
	}
}
