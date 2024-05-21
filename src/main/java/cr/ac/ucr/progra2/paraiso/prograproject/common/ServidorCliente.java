package cr.ac.ucr.progra2.paraiso.prograproject.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorCliente {
    public String ServidorTimeMarin(int i) {
        String salida = null;
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(9999);
        } catch (IOException eO) {
            eO.printStackTrace();
            System.exit(-1);
        }
        while (true) {
            Socket clienteSocket;
            try {
                clienteSocket = serverSocket.accept();
                PrintWriter writer = new PrintWriter(clienteSocket.getOutputStream(), true);
                BufferedReader reader = new BufferedReader(new InputStreamReader(clienteSocket.getInputStream()));
                PatronesProtocol protocol = new PatronesProtocol();
                if (i == 0) {
                    salida = protocol.procesarEntrada(null);
                } else {
                    String entrada = reader.readLine();
                    if (salida.equalsIgnoreCase("Chao!")) {
                        //Reiniciar el contador si el juego termina para volver a empezar
                        cr.ac.ucr.progra2.paraiso.prograproject.controller.PruebaProyecto.setCounter();
                        salida = "Se ha terminado el juego";
                    }
                    if (entrada != null) {
                        salida = protocol.procesarEntrada(entrada);
                    }
                }
                writer.close();
                reader.close();
                clienteSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return salida;
        }
    }
}
