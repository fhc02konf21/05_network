package org.campus02.networking.pingpong;

import org.omg.CORBA.INTERNAL;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class PingPongServer {

    public static void main(String[] args) {
        System.out.println("Server erzeugen");

        HashMap<String, Integer> highScore = new HashMap<>();

        try (ServerSocket serverSocket = new ServerSocket(1111)) {
            while (true) {
                System.out.println("Server wartet auf Clients...");
                //try (Socket client = serverSocket.accept()) { // Achtung
                Socket client = serverSocket.accept();
                System.out.println("Verbindung zu Client hergestellt");
                ClientHandler clientHandler = new ClientHandler(client, highScore);
                //clientHandler.start();

                Thread th = new Thread(clientHandler);
                th.start();

                //} // out of scope
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
