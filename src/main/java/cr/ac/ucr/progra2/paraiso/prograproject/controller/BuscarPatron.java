package cr.ac.ucr.progra2.paraiso.prograproject.controller;

import cr.ac.ucr.progra2.paraiso.prograproject.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class BuscarPatron
{
    @javafx.fxml.FXML
    private Button botonPaginaPrincipal;
    @FXML
    private BorderPane bp;

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
    public void paginaPrincipal(ActionEvent actionEvent) {
        loadPage("paginaPrincipal.fxml");
    }

    @FXML
    public void modificar(ActionEvent actionEvent) { loadPage("modificarPatron.fxml");}
}