package org.campus02.networking.pingpong;

import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable {

    private Socket client;
    private String name;

    public ClientHandler(Socket client) {
        this.client = client;
    }

    public void start() {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()))) {

            bw.write("Hallo, wie lautet ihr Name? Antwort mit 'name:<your name>'");
            bw.newLine();
            bw.flush();

            System.out.println("lese vom client -->");
            String line;
            while ((line = br.readLine()) != null) {

                if (line.startsWith("name:")) {
                    // name:Max
                    String [] names = line.split(":");
                    if (names.length == 2) {
                        name = names[1];
                        continue;
                    }
                }

                switch (line) {
                    case "ping":
                        System.out.println(name + " Schicke PONG <---");
                        bw.write("pong");
                        break;
                    case "pong":
                        System.out.println(name + "Schicke PING <---");
                        bw.write("ping");
                        break;
                    case "bye":
                        System.out.println(name + " leaves server");
                        bw.write("good bye " + name);
                        bw.flush();
                        return;
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

    @Override
    public void run() {
        start();
    }
}
