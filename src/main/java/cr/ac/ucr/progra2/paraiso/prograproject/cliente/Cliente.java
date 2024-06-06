package cr.ac.ucr.progra2.paraiso.prograproject.cliente;

import cr.ac.ucr.progra2.paraiso.prograproject.HelloApplication;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.io.*;
import java.net.*;

public class Cliente extends Thread { //hilo para INTERFAZ
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 12345;

    @Override
    public void run() {
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
             ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream ois = new ObjectInputStream(socket.getInputStream())) {

            System.out.println("Cliente conectado al servidor.");

            // Espera hasta que el usuario cierre la interfaz para salir
            Scanner scanner = new Scanner(System.in);
            while (true) {
                // Leer la entrada del usuario
                System.out.print("Ingrese 'exit' para cerrar el cliente o un patrón para enviar al servidor: ");
                String input = scanner.nextLine();

                // Enviar la entrada al servidor
                oos.writeObject(input);

                if ("exit".equalsIgnoreCase(input)) {
                    break; // Salir del bucle si el usuario ingresa "exit"
                }

                // Leer la respuesta del servidor y mostrarla
                String response = (String) ois.readObject();
                System.out.println("Respuesta del servidor: " + response);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("Cliente cerrado.");
    }

    public static void main(String[] args) {
        Cliente cliente = new Cliente();
        cliente.start();
        HelloApplication.main(new String[]{});
    }
}


