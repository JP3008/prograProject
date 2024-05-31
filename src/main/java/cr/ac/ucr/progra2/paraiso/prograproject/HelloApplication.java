package cr.ac.ucr.progra2.paraiso.prograproject;

import cr.ac.ucr.progra2.paraiso.prograproject.util.Utility;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class
HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("iniciarSesion.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
       // String css = HelloApplication.class.getResource("stylesheet.css").toExternalForm();
       // scene.getStylesheets().add(css);
        stage.setTitle("Proyecto programado I");
        stage.setScene(scene);
      //  stage.setResizable(false);
        stage.show();
        File filePattern = new File(String.valueOf(Utility.usualDataFile()));
        File fileType = new File(String.valueOf(Utility.usualTypeFile()));

    }

    public static void main(String[] args) {
        launch();
    }
}