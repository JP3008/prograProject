package cr.ac.ucr.progra2.paraiso.prograproject.common;

import cr.ac.ucr.progra2.paraiso.prograproject.cliente.Cliente;
import cr.ac.ucr.progra2.paraiso.prograproject.data.DesignPatternData;
import cr.ac.ucr.progra2.paraiso.prograproject.domain.DesignPattern;
import cr.ac.ucr.progra2.paraiso.prograproject.util.Utility;
import org.jdom2.JDOMException;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class MultipleServerThread extends Thread{
    private Socket clientSocket;

    public MultipleServerThread(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try (ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
             ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream())) {

            // Leer el DesignPattern enviado por el cliente
            DesignPattern dp = (DesignPattern) ois.readObject();
            PatronesProtocol protocol = new PatronesProtocol(new ArrayList<>());

            // Procesar y guardar el DesignPattern en el XML
            DesignPatternData data = new DesignPatternData(String.valueOf(Utility.usualDataFile()));
            data.addDesign(dp);

            String response = protocol.procesarEntrada(dp);

            // Enviar la respuesta al cliente
            oos.writeObject(response);
            System.out.println("Respuesta enviada al cliente: " + response);

        } catch (IOException | ClassNotFoundException | JDOMException e) {
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}







