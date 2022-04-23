package org.campus02.networking.pingpong;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class PingPongClient {

    public static void main(String[] args) {

        System.out.println("Starte Verbindung zu PingPongServer");
        try (Socket serverSocket = new Socket("localhost", 1111);
             BufferedWriter bw = new BufferedWriter(
                     new OutputStreamWriter(serverSocket.getOutputStream()));
             BufferedReader br = new BufferedReader(
                     new InputStreamReader(serverSocket.getInputStream()));
             BufferedReader cli = new BufferedReader(
                     new InputStreamReader(System.in)
             )) {

            System.out.println("Mit Server verbunden");

            String command;
            while ((command = cli.readLine()) != null) {
                if (command.equalsIgnoreCase("quit")) {
                    System.out.println("Client will beenden");
                    break;
                }

                System.out.println("Schicke command an Server");
                bw.write(command);
                bw.newLine(); // !!!!!!!!
                bw.flush();

                System.out.println("Erhalte die Antwort vom Server");
                String response = br.readLine();
                System.out.println(response);
            }


        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
