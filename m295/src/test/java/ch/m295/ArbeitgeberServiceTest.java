package ch.m295;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.Response;
class ArbeitgeberServiceTest {
	
	 private ArbeitgeberService arbeitgeberService;
	 private IArbeitgeberDAO mockDao;  

	@BeforeEach
    public void setup() {
       
        mockDao = mock(IArbeitgeberDAO.class);
        
        arbeitgeberService = new ArbeitgeberService();
        arbeitgeberService.setDao(mockDao);
        
        try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			} catch (Exception e) {}
		
		try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/m295", "u_adm", "the_password")) {
			String deleteTableSql = "DELETE from arbeitgeber;"; //Achtung! Alles wird von der Datenbank gelöscht, um zu testen
			String insertTableSql = "INSERT INTO arbeitgeber (name) VALUES ('SBB')";
			try(PreparedStatement pstmt = con.prepareStatement(deleteTableSql)) {
				pstmt.execute();
			} catch (Exception e) {
				e.printStackTrace();
	            con.close();
	        }
			try (PreparedStatement pstmt = con.prepareStatement(insertTableSql)) {
						 pstmt.execute();
						 con.close();
						} catch (Exception e) {
							e.printStackTrace();
				            con.close();
				        }	
		} catch (Exception e) {
			e.printStackTrace();
			
		}
    }
	
	@Test
	void getArbeitgeberTest() {
	    List<Arbeitgeber> arbeitgeber = new ArrayList<>();
	    Arbeitgeber a = new Arbeitgeber("SBB");
	    arbeitgeber.add(a);
	    
	    when(mockDao.select()).thenReturn(arbeitgeber);
	    
	    Response response = arbeitgeberService.getArbeitgeber();
	    
	    assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertNotNull(response.getEntity());
        assertTrue(((List) response.getEntity()).size() > 0);
	}
	
	@Test
	void getArbeitgeberTest2() {
		List<Arbeitgeber> arbeitgeber = new ArrayList<>();
		
		when(mockDao.select()).thenReturn(arbeitgeber);

	    Response response = arbeitgeberService.getArbeitgeber();

		assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
	}
	
	@Test
	void addArbeitgeberTest() {
		Arbeitgeber a = new Arbeitgeber();
		a.setName("UBS");
		
		try {
		Response response = arbeitgeberService.addArbeitgeber(a);
		assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
		} catch (Exception e) {}
	}
	
	@Test
	void addArbeitgeberTest2() {
		Arbeitgeber a = new Arbeitgeber();
		
		try {
		Response response = arbeitgeberService.addArbeitgeber(a);
		assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
		} catch (Exception e) {}
	}
	
	@Test
	void updateArbeitgeberTest() {
		List<Arbeitgeber> arbeitgeber = new ArrayList<>();
		Arbeitgeber a = new Arbeitgeber();
		a.setName("SBB");
		Arbeitgeber b = new Arbeitgeber();
		b.setName("ZKB");
		arbeitgeber.add(a);
		arbeitgeber.add(b);
		
		Response response = arbeitgeberService.updateArbeitgeber(arbeitgeber);
		
		assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
	}
	
	@Test
	void updateArbeitgeberTest2() {
		List<Arbeitgeber> arbeitgeber = new ArrayList<>();
		Arbeitgeber a = new Arbeitgeber();
		a.setName("ZKB");
		Arbeitgeber b = new Arbeitgeber();
		b.setName("SBB");
		arbeitgeber.add(a);
		arbeitgeber.add(b);
		
		Response response = arbeitgeberService.updateArbeitgeber(arbeitgeber);
		
		assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
	}
	
	@Test
	void deleteArbeitgeberTest() {
		Arbeitgeber a = new Arbeitgeber();
		a.setName("SBB");
		
		Response response = arbeitgeberService.deleteArbeitgeber(a.getName());
		
		assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
	}
	
	@Test
	void deleteArbeitgeberTest2() {
		Arbeitgeber a = new Arbeitgeber();
		a.setName("ZKB");
		
		Response response = arbeitgeberService.deleteArbeitgeber(a.getName());
		
		assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
	}

	@Test
	void allowAccess() {
HttpUriRequest request = new HttpGet("http://localhost:8080/m295/resources/arbeitgeberService/getArbeitgeber");
		
		try {
		HttpResponse httpResponse = HttpClientBuilder
		          .create()
		          .build()
		          .execute(request);
		
		        assertEquals(httpResponse
		          .getStatusLine()
		          .getStatusCode(), HttpStatus.SC_UNAUTHORIZED);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void allowAccess2() {
			HttpUriRequest request = new HttpGet("http://localhost:8080/m295/resources/arbeitgeberService/getArbeitgeber");
			String username = "admin";
			String password = "password";

			String auth = username + ":" + password;
	        String encodedAuth = Base64.getEncoder().encodeToString(auth.getBytes());
	        
	        request.setHeader(HttpHeaders.AUTHORIZATION, "Basic " + encodedAuth);
	        
		try {
		HttpResponse httpResponse = HttpClientBuilder
		          .create()
		          .build()
		          .execute(request);
		
		        assertEquals(httpResponse
		          .getStatusLine()
		          .getStatusCode(), HttpStatus.SC_OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
