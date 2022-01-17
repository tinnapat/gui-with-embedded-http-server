package tech.kbtg.demo.gui.http.handler;

import java.io.IOException;
import java.io.OutputStream;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class MyHandler implements HttpHandler {
	
	private static final String CHARSET 				= "UTF-8";
	private static final String HEADER_CONTENT_TYPE		= "Content-Type";
	
	private static final int STATUS_OK 					= 200;
	
    public void handle(HttpExchange exchange) throws IOException {
    	try {
    		final Headers headers = exchange.getResponseHeaders();
	        headers.set(HEADER_CONTENT_TYPE, String.format("application/json; charset=%s", new String[] {CHARSET}));

	        //final String requestMethod = exchange.getRequestMethod().toUpperCase();
    		
	        String responseBody = "This is the response";
	        final byte[] rawResponseBody = responseBody.getBytes(CHARSET);
	        exchange.sendResponseHeaders(STATUS_OK, rawResponseBody.length);
	        
	        OutputStream os = exchange.getResponseBody();
	        os.write(rawResponseBody);
	        os.close();
    	}
        finally {
        	exchange.close();
        }
    }

}
