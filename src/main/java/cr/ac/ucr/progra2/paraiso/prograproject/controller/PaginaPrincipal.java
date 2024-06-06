package cr.ac.ucr.progra2.paraiso.prograproject.controller;

import cr.ac.ucr.progra2.paraiso.prograproject.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class PaginaPrincipal {
    @FXML
    private Button botonBuscar;
    @FXML
    private Button botonCrear;
    @FXML
    private Button botonSalir;
    @FXML
    private BorderPane bp;
    @FXML
    private Button botonPrueba;
    @FXML
    private Button botonBuscar1;

    private void loadPage(String page){
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(page));
        try {
            this.bp.setCenter(fxmlLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void initialize() {
    }

    @Deprecated
    public void buscar(ActionEvent actionEvent) {
        loadPage("buscarPatron.fxml");
    }

    @FXML
    public void exit(ActionEvent actionEvent) {
        System.exit(0);

    }

    @FXML
    public void crear(ActionEvent actionEvent) {
        loadPage("crearPatron.fxml");
    }

    @FXML
    public void pruebaUno(ActionEvent actionEvent) {
        loadPage("pruebaProyecto.fxml");
    }

    @FXML
    public void buscarParaModificar(ActionEvent actionEvent) {

    }

    @FXML
    public void busqueda(ActionEvent actionEvent) {loadPage("busqueda.fxml");
    }
}