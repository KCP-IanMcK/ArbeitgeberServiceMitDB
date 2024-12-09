package ch.m295;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;

class StringServiceTest {

	@Test
	void testHello() throws ClientProtocolException, IOException {
		
		HttpUriRequest request = new HttpGet("http://localhost:8080/m295/resources/stringService/hello");
		
		HttpResponse httpResponse = HttpClientBuilder
		          .create()
		          .build()
		          .execute(request);

		        assertEquals(httpResponse
		          .getStatusLine()
		          .getStatusCode(), HttpStatus.SC_OK);
	}

	@Test
	void testGoodbye() throws ClientProtocolException, IOException {
		HttpUriRequest request = new HttpGet("http://localhost:8080/m295/resources/stringService/goodbye");
		
		HttpResponse httpResponse = HttpClientBuilder
		          .create()
		          .build()
		          .execute(request);

		        assertEquals(httpResponse
		          .getStatusLine()
		          .getStatusCode(), HttpStatus.SC_OK);
	}

	@Test
	void testHelloWithName() throws ClientProtocolException, IOException {
		HttpUriRequest request = new HttpGet("http://localhost:8080/m295/resources/stringService/sayHello/Hans");
		
		HttpResponse httpResponse = HttpClientBuilder
		          .create()
		          .build()
		          .execute(request);
		String body = EntityUtils.toString(httpResponse.getEntity());

		        assertEquals(httpResponse
		          .getStatusLine()
		          .getStatusCode(), HttpStatus.SC_OK);
		        
		        assertEquals("Hello Hans", body);
	}

	@Test
	void testHelloWithName2() throws ClientProtocolException, IOException {
		HttpUriRequest request = new HttpGet("http://localhost:8080/m295/resources/stringService/sayHelloQuery?name=Hans");
		
		HttpResponse httpResponse = HttpClientBuilder
		          .create()
		          .build()
		          .execute(request);

		        assertEquals(httpResponse
		          .getStatusLine()
		          .getStatusCode(), HttpStatus.SC_OK);
	}

}
