package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class LogKliens {
    public static void main(String[] args) throws IOException {
        Socket s = new Socket("127.0.0.1", 1234);
        PrintWriter pw = new PrintWriter(s.getOutputStream());
        String lineToBeSent;
        BufferedReader input;
        PrintWriter output;
        input = new BufferedReader(new InputStreamReader(System.in));
        output = new PrintWriter(s.getOutputStream(), true);
        BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
        // beolvassuk a felhasználó által írt szöveget és elküldjük a szervernek, hogy logolja
        while (true) {
            String szovegToKuldes = input.readLine();
            //leállítjuk a kapcsolatot, ha a felhasználó befejezte a gépelést
            if (szovegToKuldes == null || szovegToKuldes.equals("")) break;
            output.println(szovegToKuldes);
            String valasz = br.readLine();
            System.out.println(valasz);
        }
        br.close();
        pw.close();
        s.close();
    }
}