package org.campus02.urldemo.networking.time;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;

public class TimeServer {

    public static void main(String[] args) {
        System.out.println("Erzeuge den Server");
        try(ServerSocket serverSocket = new ServerSocket(1111)) {

            // laufe unendlich
            while (true) {
                System.out.println("Warte auf Clients...");
                try(Socket client = serverSocket.accept();
                    BufferedWriter bw = new BufferedWriter(
                            new OutputStreamWriter(client.getOutputStream()))) {
                    System.out.println("Mit dem Client verbunden");

                    LocalDateTime now = LocalDateTime.now();
                    //bw.write(now.toString());
                    bw.write("current date: " + now);
                    bw.flush(); // !!!!
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
