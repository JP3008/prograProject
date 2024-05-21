package cr.ac.ucr.progra2.paraiso.prograproject.cliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Cliente {
    public String servidorCliente(String ingreso) throws UnknownHostException {
        if (ingreso != null) {
            InetAddress inetAddress = InetAddress.getLocalHost();
            Socket echoSocket;
            PrintWriter writer;
            BufferedReader reader;
            try {
                echoSocket = new Socket(inetAddress, 9999);
                writer = new PrintWriter(echoSocket.getOutputStream(), true);
                reader = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
                writer.println(ingreso);
                String entrada = reader.readLine();
                if (entrada != null){
                    reader.close();
                    writer.close();
                    echoSocket.close();
                    return "Servidor: " + entrada;
                }else{
                    return "";
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "";
    }
}
