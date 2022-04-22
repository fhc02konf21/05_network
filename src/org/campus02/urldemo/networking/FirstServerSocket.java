package org.campus02.urldemo.networking;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class FirstServerSocket {

    public static void main(String[] args) {

        // Erzeuge einen Server

        System.out.println("Server wird erstellt");
        try (ServerSocket serverSocket = new ServerSocket(1111)) {

            while (true) {
                System.out.println("Warte auf Client");
                // accept -> warte bis sich ein Client verbindet
                try (Socket client = serverSocket.accept();
                     BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()))) {
                    System.out.println("Client hat sich verbunden");
                    bw.write("Hallo Client");
                    bw.flush();
                    System.out.println("Antwort an Client gesendet");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
