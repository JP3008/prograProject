package cr.ac.ucr.progra2.paraiso.prograproject.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorCliente {

    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        boolean listening = true;

        try {
            serverSocket = new ServerSocket(9999);
            System.out.println("Active server");
            while (listening) {
                MultipleServerThread hilo = new MultipleServerThread(serverSocket.accept());
                hilo.start();
            }
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }

    }
}
