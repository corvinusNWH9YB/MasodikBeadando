package server;

import util.Logolo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.MessageFormat;
import java.util.Date;

public class LogMultiKiszolgalo implements Runnable{
    private Socket socket;
    private boolean stop;

    public LogMultiKiszolgalo(Socket socket) {
        super();
        this.socket = socket;
        this.stop = false;
    }

    @Override
    public void run() {
        BufferedReader br = null;
        PrintWriter pw = null;
        Logolo logolo = new Logolo();
        try {
            while(!stop){
                br = new BufferedReader(new InputStreamReader((socket.getInputStream())));
                pw = new PrintWriter(socket.getOutputStream());
                String be = null;
                be = br.readLine();
                if(be == null || be.equals("")){
                    stop = true;
                    pw.println("Kapcsolat bontása...");
                    pw.flush();
                    break;
                }
                System.out.println("Eltárolandó üzenet: " + be);
                Date now = new Date();
                String szovegToLog = MessageFormat.format("{0} {1}", now, be);
                //Üzenet eltárolása szöveg fájlban
                logolo.log(szovegToLog);
                pw.println("Üzenet sikeresen eltárolva : " + be);
                pw.flush();
            }
            pw.close();
            br.close();
            socket.close();
        } catch (IOException e) {
            System.out.println("HIba történt: " + e);
        }
    }
}