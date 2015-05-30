package ru.ifmo.lang.rasputnyak.t10;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Владелец on 27.04.2015.
 */
public class HServer {
    public static void main(String[] args) {
        final HttpServer server;
        try {
            server = HttpServer.create(new InetSocketAddress(8080), 0);
            server.createContext("/clear_background", new MyHandler());
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static class MyHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange httpExchange) throws IOException {
            String response = httpExchange.getRequestURI().getQuery();
            String string = response.substring(7);
            URL url = null;
            try {
                url = new URL(string);
                URLConnection connection = url.openConnection();
                InputStream inputStream = connection.getInputStream();
                BufferedImage image = ImageIO.read(inputStream);
                for (int i = 0; i < image.getWidth(); i++) {
                    for (int j = 0; j < image.getHeight(); j++) {
                        if (image.getRGB(i, j) == Color.WHITE.getRGB()) {
                            image.setRGB(i, j, Color.TRANSLUCENT);
                        }
                    }
                }
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                ImageIO.write(image, "PNG", outputStream);
                httpExchange.sendResponseHeaders(200, outputStream.toByteArray().length);
                final OutputStream responseBody = httpExchange.getResponseBody();
                responseBody.write(outputStream.toByteArray());
                responseBody.close();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
