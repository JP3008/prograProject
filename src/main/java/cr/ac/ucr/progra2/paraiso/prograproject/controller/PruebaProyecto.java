package cr.ac.ucr.progra2.paraiso.prograproject.controller;

import cr.ac.ucr.progra2.paraiso.prograproject.HelloApplication;
import cr.ac.ucr.progra2.paraiso.prograproject.cliente.Cliente;
import cr.ac.ucr.progra2.paraiso.prograproject.common.PatronesProtocol;
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
    /*
    @javafx.fxml.FXML
    private BorderPane bp;
    @javafx.fxml.FXML
    private TextField txf_ingresar;
    @javafx.fxml.FXML
    private TextArea txa_respuestaServidor;
    @javafx.fxml.FXML
    private Button botonAceptar;
    @javafx.fxml.FXML
    private Button botonPaginaPrincipal;

    PatronesProtocol pattern = new PatronesProtocol();

    public PruebaProyecto() throws IOException {
    }

    @javafx.fxml.FXML
    public void initialize() {

        txa_respuestaServidor.setText("Active Server\n" + "Ingrese 'pattern one', 'pattern two' o 'pattern three' para recibir información sobre un patrón en particular.");
        txa_respuestaServidor.appendText("Cliente: " + txf_ingresar.getText() + "\n");

    }

    @javafx.fxml.FXML
    public void aceptar(ActionEvent actionEvent) {

        txa_respuestaServidor.appendText("Cliente: " + txf_ingresar.getText() + "\n");
        txa_respuestaServidor.appendText("Server: " + pattern.procesarEntrada(txf_ingresar.getText()) + "\n");

        txf_ingresar.clear();

    }



    private void loadPage(String page){
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(page));
        try {
            this.bp.setCenter(fxmlLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @javafx.fxml.FXML
    public void paginaPrincipal(ActionEvent actionEvent) {
        loadPage("paginaPrincipal.fxml");
    }
    */

}