package cr.ac.ucr.progra2.paraiso.prograproject.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TinMarinServidor {
    public static void ServidorTimeMarin(){
        ServerSocket serverSocket = null;
        try{
            serverSocket = new ServerSocket(9999);
        }catch (IOException eO){
            eO.printStackTrace();
            System.exit(-1);
        }
        Socket clienteSocket;
        try{
            clienteSocket = serverSocket.accept();
            PrintWriter writer = new PrintWriter(clienteSocket.getOutputStream(),true);
            BufferedReader reader = new BufferedReader(new InputStreamReader(clienteSocket.getInputStream()));
            TinMarinProtocol protocol = new TinMarinProtocol();
            String salida = protocol.procesarEntrada(null);
            //Modificar para que esto salga en el textArea de la interfaz
            writer.println(salida);
            String entrada;
            while((entrada = reader.readLine()) != null){
                salida = protocol.procesarEntrada(entrada);
                //Modificar para que esto salga en el textArea de la interfaz
                writer.println(salida);
                if (salida.equals("Chao!"))
                    break;
            }
            //Retornar en la interfaz en caso de que el jugador indique chao! para terminar el funcionamiento
            salida = "Se ha terminado el juego";
            writer.close();
            reader.close();
            clienteSocket.close();
            serverSocket.close();
        }catch (IOException iOE){
            iOE.printStackTrace();
            System.exit(-1);//Se termina la aplicacion
        }
    }
}
