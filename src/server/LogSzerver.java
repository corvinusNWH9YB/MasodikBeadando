package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Path;
import java.nio.file.Paths;

public class LogSzerver {
    public static void main(String [] args) throws IOException {
        ServerSocket ss = new ServerSocket(1234);

        while (true){
            Socket s = ss.accept();
            new Thread(new LogMultiKiszolgalo(s)).start();
        }
    }
}
