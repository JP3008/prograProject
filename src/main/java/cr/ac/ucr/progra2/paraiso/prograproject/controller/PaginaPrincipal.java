package cr.ac.ucr.progra2.paraiso.prograproject.controller;

import cr.ac.ucr.progra2.paraiso.prograproject.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class PaginaPrincipal {
    @javafx.fxml.FXML
    private Button botonBuscar;
    @javafx.fxml.FXML
    private Button botonCrear;
    @javafx.fxml.FXML
    private Button botonSalir;
    @FXML
    private BorderPane bp;
    @FXML
    private Button botonPrueba;

    private void loadPage(String page){
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(page));
        try {
            this.bp.setCenter(fxmlLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void buscar(ActionEvent actionEvent) {
        loadPage("buscarPatron.fxml");
    }

    @javafx.fxml.FXML
    public void exit(ActionEvent actionEvent) {
        System.exit(0);

    }

    @javafx.fxml.FXML
    public void crear(ActionEvent actionEvent) {
        loadPage("crearPatron.fxml");
    }

    @FXML
    public void pruebaUno(ActionEvent actionEvent) {
        loadPage("pruebaProyecto.fxml");
    }
}