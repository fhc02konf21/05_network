package org.campus02.urldemo.networking.time;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TimeServer {

    public static void main(String[] args) {
        System.out.println("Erzeuge den Server");
        try(ServerSocket serverSocket = new ServerSocket(1111)) {
            int clients = 0;

            // laufe unendlich
            while (clients < 5) {
                System.out.println("Warte auf Clients...");
                try(Socket client = serverSocket.accept()) {
                    System.out.println("Client hat sich verbunden");
                    clients ++;
                    TimeHandler timeHandler = new TimeHandler(client);
                    timeHandler.start();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
