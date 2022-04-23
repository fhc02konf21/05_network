package org.campus02.networking.pingpong;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class PingPongServer {

    public static void main(String[] args) {
        System.out.println("Server erzeugen");
        try (ServerSocket serverSocket = new ServerSocket(1111)) {
            while (true) {
                System.out.println("Server wartet auf Clients...");
                try (Socket client = serverSocket.accept()) {
                    System.out.println("Verbindung zu Client hergestellt");
                    ClientHandler clientHandler = new ClientHandler(client);
                    clientHandler.start();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
