package ch.m295;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;

@Path("/stringService")
public class StringService {
	
	  @GET
	    @Path("/hello")
	  @Produces({MediaType.TEXT_PLAIN})
	    public String hello() {
	    	String newString = "Hello World";
	    	return newString;
	    }
	  
	  @GET
	    @Path("/goodbye")
	  @Produces({MediaType.TEXT_PLAIN})
	    public String goodbye() {
	    	String newString = "Goodbye World";
	    	return newString;
	    }
	  
	  @GET
	    @Path("/sayHello/{name}")
	  @Produces({MediaType.TEXT_PLAIN})
	    public String helloWithName(@PathParam("name") String name) {
	    	String newString = "Hello " + name;
	    	return newString;
	    }
	  
	  @GET
	    @Path("/sayHelloQuery")
	  @Produces({MediaType.TEXT_PLAIN})
	    public String helloWithName2(@QueryParam("name") String name) {
	    	String newString = "Hello " + name;
	    	return newString;
	    }
}
