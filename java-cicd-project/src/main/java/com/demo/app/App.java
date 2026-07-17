package com.demo.app;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class App {

    static class HelloHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String response = "<!DOCTYPE html>"
                + "<html><head><title>Java CI/CD App</title>"
                + "<style>body{font-family:Arial;text-align:center;margin-top:80px;background:#1a1a2e;color:white;}"
                + "h1{color:#0f3460;} .card{background:#16213e;padding:30px;border-radius:10px;display:inline-block;}"
                + ".status{color:#00ff88;font-size:24px;}</style></head>"
                + "<body><div class='card'>"
                + "<h1>Java CI/CD Pipeline Demo</h1>"
                + "<p class='status'>Application is Running Successfully!</p>"
                + "<p>Built with Maven | Containerized with Docker</p>"
                + "<p>Deployed on Kubernetes via Jenkins CI/CD</p>"
                + "</div></body></html>";
            exchange.getResponseHeaders().set("Content-Type", "text/html");
            exchange.sendResponseHeaders(200, response.getBytes().length);
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }

    public static void main(String[] args) throws IOException {
        int port = Integer.parseInt(System.getenv().getOrDefault("PORT", "8080"));
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        server.createContext("/", new HelloHandler());
        server.setExecutor(null);
        server.start();
        System.out.println("Java CI/CD App started on port " + port);
    }
}
