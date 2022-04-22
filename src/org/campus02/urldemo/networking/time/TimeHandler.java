package org.campus02.urldemo.networking.time;

import java.io.*;
import java.net.Socket;
import java.time.LocalDateTime;

public class TimeHandler {

    private Socket client;

    public TimeHandler(Socket client) {
        this.client = client;
    }

    public void start() {
        try (BufferedWriter bw = new BufferedWriter(
                new OutputStreamWriter(client.getOutputStream()))) {
            System.out.println("Mit dem Client verbunden");

            LocalDateTime now = LocalDateTime.now();
            //bw.write(now.toString());
            bw.write("current date: " + now);
            bw.flush(); // !!!!
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
