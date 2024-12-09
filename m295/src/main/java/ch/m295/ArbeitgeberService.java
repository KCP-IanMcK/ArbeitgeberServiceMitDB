package ch.m295;

import java.util.ArrayList;
import java.util.List;

import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/arbeitgeberService")
public class ArbeitgeberService {
	
	private IArbeitgeberDAO dao = new ArbeitgeberDAO();

	public void setDao(IArbeitgeberDAO dao) {
		this.dao = dao;
	}

	@RolesAllowed({"ADMIN", "USER"})
	@GET
	@Path("/getArbeitgeber")
	@Produces({MediaType.APPLICATION_JSON})
	public Response getArbeitgeber() {
		
		List<Arbeitgeber> arbeitgeber = new ArrayList<>();
		
		arbeitgeber.addAll(dao.select());
		if (arbeitgeber.size() > 0) {
			return Response.ok(arbeitgeber).build();
		}
		else {
			return Response.status(Status.BAD_REQUEST).build();
		}
	}
	
	@RolesAllowed("ADMIN")
	@POST
	@Path("/getArbeitgeber")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public Response getSpecificArbeitgeber(Arbeitgeber arbeitgeber) {
		
		IArbeitgeberDAO dao = new ArbeitgeberDAO();
		Arbeitgeber a = dao.select(arbeitgeber);
		if (a.getName() != null) {
			return Response.ok(a).build();
		}
		else {
			return Response.status(Status.BAD_REQUEST).build();
		}
	}
	
	@RolesAllowed("ADMIN")
	@POST
	@Path("/addArbeitgeber")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.TEXT_PLAIN})
	public Response addArbeitgeber(Arbeitgeber arbeitgeber) throws Exception {
		
		IArbeitgeberDAO dao = new ArbeitgeberDAO();
		int count = dao.insert(arbeitgeber);
		if(count == 1) {
			return Response.ok().build();
		}
		else {
			return Response.status(Status.BAD_REQUEST).build();
		}
	}
	
	@RolesAllowed("ADMIN")
	@PUT
	@Path("/updateArbeitgeber")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public Response updateArbeitgeber(List<Arbeitgeber> arbeitgeber) { //First in list is what to update and second in list is what it will be updated to
		
		IArbeitgeberDAO dao = new ArbeitgeberDAO();
		int count = dao.update(arbeitgeber.get(0), arbeitgeber.get(1));
		if(count == 1) {
			return Response.ok().build();
		}
		else {
			return Response.status(Status.BAD_REQUEST).build();
		}
	}
	
	@RolesAllowed("ADMIN")
	@DELETE //Muss post sein, sonst kann man beim Testen keinen Body mitgeben
	@Path("/deleteArbeitgeber/{arbeitgeberName}")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public Response deleteArbeitgeber(@PathParam("arbeitgeberName") String name) {
		
		IArbeitgeberDAO dao = new ArbeitgeberDAO();
		int count = dao.delete(name);
		if(count == 1) {
			return Response.ok().build();
		}
		else {
			return Response.status(Status.BAD_REQUEST).build();
		}
	}
}
