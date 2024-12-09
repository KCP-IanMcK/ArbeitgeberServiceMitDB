package ch.m295;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ArbeitgeberDAOTest {
	
	@BeforeEach
	void setup() {
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
	void selectTest() {
		HttpUriRequest request = new HttpGet("http://localhost:8080/m295/resources/arbeitgeberService/getArbeitgeber");
		try {
		HttpResponse httpResponse = HttpClientBuilder
		          .create()
		          .build()
		          .execute(request);

		        assertEquals(HttpStatus.SC_OK, httpResponse
		          .getStatusLine()
		          .getStatusCode());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void insertTest() {
		HttpPost request = new HttpPost("http://localhost:8080/m295/resources/arbeitgeberService/addArbeitgeber");
		String json = "{\"name\":\"ZKB\"}";
		try {
			StringEntity entity = new StringEntity(json);
			request.setEntity(entity);
			request.setHeader("Content-Type", "APPLICATION/JSON");
				HttpResponse httpResponse = HttpClientBuilder
		          .create()
		          .build()
		          .execute(request);

		        assertEquals(HttpStatus.SC_OK, httpResponse
		          .getStatusLine()
		          .getStatusCode());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void insertTest2() {
		HttpPost request = new HttpPost("http://localhost:8080/m295/resources/arbeitgeberService/addArbeitgeber");
		String json = "{\"name\":}";
		try {
			StringEntity entity = new StringEntity(json);
			request.setEntity(entity);
			request.setHeader("Content-Type", "APPLICATION/JSON");
				HttpResponse httpResponse = HttpClientBuilder
		          .create()
		          .build()
		          .execute(request);

		        assertEquals(HttpStatus.SC_BAD_REQUEST, httpResponse
		          .getStatusLine()
		          .getStatusCode());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void insertTest3() {
		HttpPost request = new HttpPost("http://localhost:8080/m295/resources/arbeitgeberService/addArbeitgeber");
		String json = "{\"name\":\"ZKB\", \"date\":\"2022-12-12\", \"aktiv\":\"true\"}";
		try {
			StringEntity entity = new StringEntity(json);
			request.setEntity(entity);
			request.setHeader("Content-Type", "APPLICATION/JSON");
				HttpResponse httpResponse = HttpClientBuilder
		          .create()
		          .build()
		          .execute(request);

		        assertEquals(HttpStatus.SC_OK, httpResponse
		          .getStatusLine()
		          .getStatusCode());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void insertTest4() {
		HttpPost request = new HttpPost("http://localhost:8080/m295/resources/arbeitgeberService/addArbeitgeber");
		String json = "{\"name\":\"ZKB\", \"date\":\"12-12-2022\", \"aktiv\":\"true\"}";
		try {
			StringEntity entity = new StringEntity(json);
			request.setEntity(entity);
			request.setHeader("Content-Type", "APPLICATION/JSON");
				HttpResponse httpResponse = HttpClientBuilder
		          .create()
		          .build()
		          .execute(request);

		        assertEquals(HttpStatus.SC_BAD_REQUEST, httpResponse
		          .getStatusLine()
		          .getStatusCode());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void insertTest5() {
		HttpPost request = new HttpPost("http://localhost:8080/m295/resources/arbeitgeberService/addArbeitgeber");
		String json = "{\"name\":\"ZKB\", \"date\":\"2022-12-12\", \"aktiv\":\"false\"}";
		try {
			StringEntity entity = new StringEntity(json);
			request.setEntity(entity);
			request.setHeader("Content-Type", "APPLICATION/JSON");
				HttpResponse httpResponse = HttpClientBuilder
		          .create()
		          .build()
		          .execute(request);

		        assertEquals(HttpStatus.SC_OK, httpResponse
		          .getStatusLine()
		          .getStatusCode());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void insertTest6() {
		HttpPost request = new HttpPost("http://localhost:8080/m295/resources/arbeitgeberService/addArbeitgeber");
		String json = "{\"name\":\"ZKB\", \"date\":\"2022-12-12\"}";
		try {
			StringEntity entity = new StringEntity(json);
			request.setEntity(entity);
			request.setHeader("Content-Type", "APPLICATION/JSON");
				HttpResponse httpResponse = HttpClientBuilder
		          .create()
		          .build()
		          .execute(request);

		        assertEquals(HttpStatus.SC_OK, httpResponse
		          .getStatusLine()
		          .getStatusCode());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void updateTest() {
		HttpPut request = new HttpPut("http://localhost:8080/m295/resources/arbeitgeberService/updateArbeitgeber");
		String json = "[{\"name\":\"SBB\"}, {\"name\":\"ZKB\"}]";
		try {
			StringEntity entity = new StringEntity(json);
			request.setEntity(entity);
			request.setHeader("Content-Type", "APPLICATION/JSON");
				HttpResponse httpResponse = HttpClientBuilder
		          .create()
		          .build()
		          .execute(request);

		        assertEquals(HttpStatus.SC_OK, httpResponse
		          .getStatusLine()
		          .getStatusCode());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void updateTest2() {
		HttpPut request = new HttpPut("http://localhost:8080/m295/resources/arbeitgeberService/updateArbeitgeber");
		String json = "[{\"name\":\"ZKB\"}, {\"name\":\"SBB\"}]";
		try {
			StringEntity entity = new StringEntity(json);
			request.setEntity(entity);
			request.setHeader("Content-Type", "APPLICATION/JSON");
				HttpResponse httpResponse = HttpClientBuilder
		          .create()
		          .build()
		          .execute(request);

		        assertEquals(HttpStatus.SC_BAD_REQUEST, httpResponse
		          .getStatusLine()
		          .getStatusCode());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void updateTest3() {
		HttpPut request = new HttpPut("http://localhost:8080/m295/resources/arbeitgeberService/updateArbeitgeber");
		String json = "[{\"name\":\"SBB\"}, {\"name\":}]";
		try {
			StringEntity entity = new StringEntity(json);
			request.setEntity(entity);
			request.setHeader("Content-Type", "APPLICATION/JSON");
				HttpResponse httpResponse = HttpClientBuilder
		          .create()
		          .build()
		          .execute(request);

		        assertEquals(HttpStatus.SC_BAD_REQUEST, httpResponse
		          .getStatusLine()
		          .getStatusCode());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void deleteTest() {
		HttpPost request = new HttpPost("http://localhost:8080/m295/resources/arbeitgeberService/deleteArbeitgeber");
		String json = "{\"name\":\"SBB\"}";
		try {
			StringEntity entity = new StringEntity(json);
			request.setEntity(entity);
			request.setHeader("Content-Type", "APPLICATION/JSON");
				HttpResponse httpResponse = HttpClientBuilder
		          .create()
		          .build()
		          .execute(request);

		        assertEquals(HttpStatus.SC_OK, httpResponse
		          .getStatusLine()
		          .getStatusCode());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void deleteTest2() {
		HttpPost request = new HttpPost("http://localhost:8080/m295/resources/arbeitgeberService/deleteArbeitgeber");
		String json = "{\"name\":\"ZKB\"}";
		try {
			StringEntity entity = new StringEntity(json);
			request.setEntity(entity);
			request.setHeader("Content-Type", "APPLICATION/JSON");
				HttpResponse httpResponse = HttpClientBuilder
		          .create()
		          .build()
		          .execute(request);

		        assertEquals(HttpStatus.SC_BAD_REQUEST, httpResponse
		          .getStatusLine()
		          .getStatusCode());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
