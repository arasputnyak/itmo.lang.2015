package ru.ifmo.lang.rasputnyak.t7;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

/**
 * Created by Владелец on 23.04.2015.
 */
public class WhiteboardServer {
    static String message;
    public static void main(String[] args) {
        final HttpServer server;
        try {
            server = HttpServer.create(new InetSocketAddress(8080), 0);
            server.createContext("/get", new GetHandler());
            server.createContext("/post", new PostHandler());
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static class GetHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange httpExchange) throws IOException {
            httpExchange.sendResponseHeaders(200, message.length());
            final OutputStream responseBody = httpExchange.getResponseBody();
            responseBody.write(message.getBytes());
            responseBody.close();
        }
    }
    private static class PostHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange httpExchange) throws IOException {
            message = httpExchange.getRequestURI().getQuery();
            message = message.substring(8, message.length());
        }
    }

}
