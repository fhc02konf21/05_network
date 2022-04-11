package org.campus02.urldemo;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

public class MyUrl {

    public static void main(String[] args) {
        try {
            // 1. Erzeugung eines Objektes der Klasse URL
            URL myUrl = new URL(
                    "https://www.campus02.at:443");

            // BufferedReader: Lese die Daten von meiner URL
            // BufferedWriter zum Schreiben in ein File
            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(myUrl.openStream()));
                 BufferedWriter bw = new BufferedWriter(
                         new FileWriter(
                                 "src/org/campus02/urldemo/htmlFiles/campus02.html"));
            ) {
                String line;
                // lese Zeile für Zeile
                while ((line = br.readLine()) != null) {
                    System.out.println(line);
                    //System.out.println(); -> eine Variante einen Zeilenumbruch zu erzeugen
                    //bw.write(line + "\n"); -> zweite Variante: \n macht einen Zeilenumbruch

                    bw.write(line);
                    bw.newLine(); // Zeilenumbruch
                    // bw.flush(); hier könnte man auch Zwischenspeichern
                }

                // !!!! flush nicht vergessen
                bw.flush();

                // informationen der URL
                System.out.println("myUrl.protocol = " + myUrl.getProtocol());
                System.out.println("myUrl.hostname = " + myUrl.getHost());
                System.out.println("myUrl.port = " + myUrl.getPort());
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
