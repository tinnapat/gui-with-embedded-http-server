package tinnapat.demo.gui.http;

import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpServer;

import tinnapat.demo.gui.http.handler.DemoHandler;

public class DemoServer {

	private HttpServer server;

	public void startServer() throws Exception {
		server = HttpServer.create(new InetSocketAddress(8000), 0);
		server.createContext("/test", new DemoHandler());
		server.setExecutor(null); // creates a default executor
		server.start();
	}

	public void stopServer() {
		server.stop(0);
	}

}
