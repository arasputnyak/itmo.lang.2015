package ru.ifmo.lang.rasputnyak.t7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Владелец on 23.04.2015.
 */
public class WhiteboardClient {
    public static void main(String[] args) {
        String address = "http://localhost:8080/";
        String request;
        if (args[0].equals("get")) {
            request = address + "get";
        } else {
            request = address + "post?message=" + args[1];
        }
        URL url = null;
        try {
            url = new URL(request);
            URLConnection connection = url.openConnection();
            InputStream inputStream = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String content = reader.readLine();
            reader.close();
            System.out.println(content);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
