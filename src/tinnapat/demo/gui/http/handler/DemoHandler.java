package tinnapat.demo.gui.http.handler;

import java.io.IOException;
import java.io.OutputStream;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import tinnapat.demo.gui.CommandQueue;

public class DemoHandler implements HttpHandler {

	private static final String CHARSET = "UTF-8";
	private static final String HEADER_CONTENT_TYPE = "Content-Type";

	private static final int STATUS_OK = 200;

	public void handle(HttpExchange exchange) throws IOException {
		try {
			final Headers headers = exchange.getResponseHeaders();
			headers.set(HEADER_CONTENT_TYPE, String.format("application/json; charset=%s", new Object[] { CHARSET }));

			String messageTemplate = "Received %s %s %s from IP address %s";
			String message = String.format(messageTemplate, 
					exchange.getRequestMethod().toUpperCase(),
					exchange.getProtocol(),
					exchange.getRequestURI(),
					exchange.getRemoteAddress()
			);
			CommandQueue.add(message);
			
			String responseBody = message;
			final byte[] rawResponseBody = responseBody.getBytes(CHARSET);
			exchange.sendResponseHeaders(STATUS_OK, rawResponseBody.length);

			OutputStream os = exchange.getResponseBody();
			os.write(rawResponseBody);
			os.close();
		} finally {
			exchange.close();
		}
	}

}
