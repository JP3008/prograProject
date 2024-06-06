package cr.ac.ucr.progra2.paraiso.prograproject.common;


import cr.ac.ucr.progra2.paraiso.prograproject.common.PatronesProtocol;
import cr.ac.ucr.progra2.paraiso.prograproject.data.DesignPatternData;
import cr.ac.ucr.progra2.paraiso.prograproject.domain.DesignPattern;
import cr.ac.ucr.progra2.paraiso.prograproject.util.Utility;
import org.jdom2.JDOMException;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServidorCliente {
    private static final int PORT = 12345;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Servidor iniciado y esperando conexiones...");

            while (true) {
                // Aceptar la conexión del cliente y crear un nuevo hilo para manejarla
                Socket clientSocket = serverSocket.accept();
                System.out.println("Cliente conectado: " + clientSocket.getInetAddress());

                // Crear e iniciar un nuevo hilo para manejar al cliente
                new MultipleServerThread(clientSocket).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}




