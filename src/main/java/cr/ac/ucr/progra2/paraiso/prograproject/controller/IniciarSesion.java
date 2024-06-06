package cr.ac.ucr.progra2.paraiso.prograproject.controller;

import cr.ac.ucr.progra2.paraiso.prograproject.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class IniciarSesion
{


    @javafx.fxml.FXML
    private BorderPane bp;
    @javafx.fxml.FXML
    private Button botonIniciarSesion;
    @javafx.fxml.FXML
    private TextField txf_usuario;
    @javafx.fxml.FXML
    private PasswordField txf_contraseña;
    Alert alert = new Alert(Alert.AlertType.ERROR);

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

    //Maes el usuario es TechDesigns
    //La contraseña va a ser Tiburoncin123
    @javafx.fxml.FXML
    public void iniciarSesion(ActionEvent actionEvent) {

        loadPage("paginaPrincipal.fxml");

    }
}