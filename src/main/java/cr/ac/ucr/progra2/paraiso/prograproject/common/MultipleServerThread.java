package cr.ac.ucr.progra2.paraiso.prograproject.common;

import cr.ac.ucr.progra2.paraiso.prograproject.cliente.Cliente;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

public class MultipleServerThread extends Thread{

        private Socket socket;

        public MultipleServerThread(Socket socket) {
            super("MultiServidorHilo");
            this.socket = socket;
        }

        public void run() {

            PrintWriter writer;
            try {
                writer = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader reader = new BufferedReader(new InputStreamReader(
                        socket.getInputStream()));
                PatronesProtocol protocolo = new PatronesProtocol();
                String salida = protocolo.procesarEntrada(null);
                writer.println(salida);
                String entrada = null;
                while ((entrada = reader.readLine()) != null) {
                    salida = protocolo.procesarEntrada(entrada);
                    writer.println(salida);
                    if (salida.equals("Adios."))
                        break;
                }// while
                writer.close();
                reader.close();
                socket.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }// run




    }
