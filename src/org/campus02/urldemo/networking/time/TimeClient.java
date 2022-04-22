package org.campus02.urldemo.networking.time;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class TimeClient {

    public static void main(String[] args) {

        // Verbinde dich mit dem Server auf localhost mit dem Port 1111
        try(Socket serverConnection = new Socket("localhost", 1111);
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(serverConnection.getInputStream()))) {

            // lese die Daten welche vom Server gesendet werden
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println("Antwort vom Server: " + line);
            }

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
