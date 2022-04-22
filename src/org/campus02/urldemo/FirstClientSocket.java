package org.campus02.urldemo;

import java.io.*;
import java.net.Socket;

public class FirstClientSocket {
    public static void main(String[] args) {
        try {

            // Erzeuge einen neuen Socket und verbinde dich zum Server
            Socket socket = new Socket("info.cern.ch", 80);

            // lese Daten vom Server
            InputStream read = socket.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(read));

            // sende Daten an den Server
            OutputStream send = socket.getOutputStream();
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(send));

            // sende einen Request an den Server
            bw.write("GET / HTTP/1.1\r\n");
            //bw.newLine();
            bw.write("Host: info.cern.ch\r\n\r\n"); // !! Zeilumbrüche
            bw.flush();

            String html;
            while ((html = br.readLine()) != null) {
                System.out.println(html);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
