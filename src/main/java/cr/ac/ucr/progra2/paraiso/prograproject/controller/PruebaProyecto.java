package cr.ac.ucr.progra2.paraiso.prograproject.controller;

import cr.ac.ucr.progra2.paraiso.prograproject.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class PruebaProyecto
{
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
    }

    @javafx.fxml.FXML
    public void paginaPrincipal(ActionEvent actionEvent) {
        loadPage("paginaPrincipal.fxml");
    }
}