package cr.ac.ucr.progra2.paraiso.prograproject.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorCliente {
    public String iniciarServidor() {
        String salida = null;
        try{
         ServerSocket serverSocket = new ServerSocket(5000);
            System.out.println("Servidor iniciado y esperando conexiones...");
            while (true) {
                try (Socket clienteSocket = serverSocket.accept();
                     PrintWriter writer = new PrintWriter(clienteSocket.getOutputStream(), true);
                     BufferedReader reader = new BufferedReader(new InputStreamReader(clienteSocket.getInputStream()))) {

                    PatronesProtocol protocol = new PatronesProtocol();
                    String entrada = reader.readLine();

                    if (entrada == null) {
                        salida = protocol.procesarEntrada(null);
                    } else {
                        salida = protocol.procesarEntrada(entrada);
                        if ("Chao!".equalsIgnoreCase(salida)) {
                            // Reiniciar el contador si la prueba termina para volver a empezar
                            salida = "Se termino la prueba";
                        }
                    }
                    writer.println(salida);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return salida;
    }
}

