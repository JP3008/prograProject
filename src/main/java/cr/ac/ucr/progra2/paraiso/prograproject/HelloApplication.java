package cr.ac.ucr.progra2.paraiso.prograproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("PaginaPrincipal.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
       // String css = HelloApplication.class.getResource("stylesheet.css").toExternalForm();
       // scene.getStylesheets().add(css);
        stage.setTitle("Proyecto programado I");
        stage.setScene(scene);
      //  stage.setResizable(false);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}