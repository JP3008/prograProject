package cr.ac.ucr.progra2.paraiso.prograproject.controller;

import cr.ac.ucr.progra2.paraiso.prograproject.HelloApplication;
import cr.ac.ucr.progra2.paraiso.prograproject.cliente.Cliente;
import cr.ac.ucr.progra2.paraiso.prograproject.common.ServidorCliente;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.UnknownHostException;

public class PruebaProyecto
{
    @javafx.fxml.FXML
    private BorderPane bp;
    @javafx.fxml.FXML
    private TextField txf_ingresar;
    @javafx.fxml.FXML
    private static TextArea txa_respuestaServidor;
    @javafx.fxml.FXML
    private Button botonAceptar;
    @javafx.fxml.FXML
    private Button botonPaginaPrincipal;
    public static int counter = 0;
    ServidorCliente ServidorCliente = new ServidorCliente();
    Cliente cliente = new Cliente();

    @javafx.fxml.FXML
    public void initialize() {
    }

    private void loadPage(String page){
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(page));
        try {
            this.bp.setCenter(fxmlLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

//
    @javafx.fxml.FXML
    public void aceptar(ActionEvent actionEvent) {
        String ingreso = txf_ingresar.getText();
        try {
            txa_respuestaServidor.appendText(ServidorCliente.iniciarServidor());
            cliente.servidorCliente(ingreso);
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
        counter++;
    }

    @javafx.fxml.FXML
    public void paginaPrincipal(ActionEvent actionEvent) {
        loadPage("paginaPrincipal.fxml");
    }

}