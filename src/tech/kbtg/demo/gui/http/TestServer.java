package tech.kbtg.demo.gui.http;

import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpServer;

import tech.kbtg.demo.gui.http.handler.MyHandler;

public class TestServer {
	
	private HttpServer server;
	
    public void startServer() throws Exception {
        server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/test", new MyHandler());
        server.setExecutor(null); // creates a default executor
        server.start();
    }
    
    public void stopServer() {
    	server.stop(0);
    }

}
