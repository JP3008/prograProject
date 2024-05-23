package cr.ac.ucr.progra2.paraiso.prograproject.cliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Cliente {
    public static void main(String[] args) throws UnknownHostException {

        Socket echoSocket = null;
        PrintWriter writer = null;
        BufferedReader reader = null;


        try {
            echoSocket = new Socket("localhost",9999);
            writer = new PrintWriter(echoSocket.getOutputStream(),true);
            reader = new BufferedReader(new InputStreamReader(echoSocket.getInputStream()));
            String entrada = reader.readLine();
            System.out.println("Server: " + entrada);
            String salida;
            BufferedReader keyboardReader = new BufferedReader(new InputStreamReader(System.in));
            while ((salida = keyboardReader.readLine()) != null){
                writer.println(salida);
                entrada = reader.readLine();
                System.out.println("Server:" + entrada);
            }//while
            reader.close();
            writer.close();
            keyboardReader.close();
            echoSocket.close();
        }catch (IOException io){
            io.printStackTrace();
        }

    }
}
