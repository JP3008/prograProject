package cr.ac.ucr.progra2.paraiso.prograproject.cliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Cliente {
    public void servidorCliente(String ingreso) throws UnknownHostException {
        String salida = null;
        InetAddress inetAddress;
        if (ingreso != null) {
            try{
            Socket echoSocket = new Socket("localhost", 5000);
                 System.out.println("Entro");
                 PrintWriter writer = new PrintWriter(echoSocket.getOutputStream(), true);
                 BufferedReader reader = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
                writer.println(ingreso);
                String entrada = reader.readLine();
                    if (entrada != null) {
                        salida = "Servidor: " + entrada;
                    } else {
                        salida = "No se recibió respuesta del servidor.";
                    }
                    entrada = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
                salida =  "Error de comunicación con el servidor.";
            }
        }
    }
}

