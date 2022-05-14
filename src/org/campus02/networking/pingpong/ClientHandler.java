package org.campus02.networking.pingpong;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;

public class ClientHandler implements Runnable {

    private Socket client;
    private HashMap<String, Integer> highScore;
    private String name;

    public ClientHandler(Socket client, HashMap<String, Integer> highScore) {
        this.client = client;
        this.highScore = highScore;
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

                        synchronized (highScore) {
                            highScore.put(name, 0);
                        }

                        continue;
                    }
                }

                switch (line) {
                    case "ping":
                        System.out.println(name + " Schicke PONG <---");
                        bw.write("pong");

                        synchronized (highScore) {
                            highScore.put(name, highScore.get(name) + 1);
                        }

                        break;
                    case "pong":
                        System.out.println(name + " Schicke PING <---");
                        bw.write("ping");

                        synchronized (highScore) {
                            highScore.put(name, highScore.get(name) + 2);
                        }

                        break;
                    case "myScore":
                        System.out.println(name + " myScore");
                        synchronized (highScore) {
                            bw.write("Your score: " + highScore.get(name));
                        }
                    case "highScore":
                        System.out.println(name + " highScore");
                        synchronized (highScore) {
                            for (String n : highScore.keySet()){
                                bw.write("Score of " + n + " - " + highScore.get(name) + " points");
                                bw.newLine();
                            }
                        }

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
