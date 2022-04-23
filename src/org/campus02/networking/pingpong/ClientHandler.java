package org.campus02.networking.pingpong;

import java.io.*;
import java.net.Socket;

public class ClientHandler {

    private Socket client;

    public ClientHandler(Socket client) {
        this.client = client;
    }

    public void start() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()))) {

            System.out.println("lese vom client -->");
            String line;
            while ((line = br.readLine()) != null) {
                switch (line) {
                    case "ping":
                        System.out.println("Schicke PONG <---");
                        bw.write("pong");
                        break;
                    case "pong":
                        System.out.println("Schicke PING <---");
                        bw.write("ping");
                        break;
                    default:
                        System.out.println("Schicke ERROR <---");
                        bw.write("error");
                }
                bw.newLine();
                bw.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
