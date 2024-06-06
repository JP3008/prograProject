package cr.ac.ucr.progra2.paraiso.prograproject.controller;

import cr.ac.ucr.progra2.paraiso.prograproject.HelloApplication;
import cr.ac.ucr.progra2.paraiso.prograproject.domain.DesignPattern;
import cr.ac.ucr.progra2.paraiso.prograproject.domain.DesignPatternType;
import cr.ac.ucr.progra2.paraiso.prograproject.data.DesignPatternData;
import cr.ac.ucr.progra2.paraiso.prograproject.data.DesignPatternTypeData;
import cr.ac.ucr.progra2.paraiso.prograproject.util.Utility;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.jdom2.JDOMException;

import java.io.*;
import java.net.Socket;

public class CrearPatron {
    @javafx.fxml.FXML
    private BorderPane bp;
    @javafx.fxml.FXML
    private TextField textFieldProblem;
    @javafx.fxml.FXML
    private ComboBox<DesignPatternType> comboBoxPattern;
    @javafx.fxml.FXML
    private Label labelID;
    @javafx.fxml.FXML
    private Button buttonSelectFile;
    @javafx.fxml.FXML
    private Button buttonInsert;
    @javafx.fxml.FXML
    private TextField textFieldSolution;
    @javafx.fxml.FXML
    private Button buttonBack;
    @javafx.fxml.FXML
    private TextField textFieldContext;
    @javafx.fxml.FXML
    private TextField textFieldExample;
    private Alert alert = new Alert(Alert.AlertType.INFORMATION);

    public File selectedFile;

    @javafx.fxml.FXML
    private ImageView imageViewFile;
    @javafx.fxml.FXML
    private Button buttonType;

    @javafx.fxml.FXML
    public void initialize() throws IOException, JDOMException {
        labelID.setText(String.valueOf(Utility.getMaxID(Utility.usualDataFile())));
        selectedFile = null;
        DesignPatternTypeData data = new DesignPatternTypeData(String.valueOf(Utility.usualTypeFile()));
        ObservableList<DesignPatternType> options = FXCollections.observableArrayList(data.findAll());
        comboBoxPattern.setItems(options);
    }

    private void loadPage(String page) {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(page));
        try {
            this.bp.setCenter(fxmlLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @javafx.fxml.FXML
    public void insertarPatron(ActionEvent actionEvent) throws IOException, JDOMException {
        DesignPattern dp = new DesignPattern();

        if (!textFieldExample.getText().isEmpty() && !labelID.getText().isEmpty() && !textFieldContext.getText().isEmpty() && !textFieldProblem.getText().isEmpty() && !textFieldSolution.getText().isEmpty() && comboBoxPattern.getValue() != null && selectedFile != null) {

            dp.setDesignID(Integer.parseInt(labelID.getText()));
            dp.setContext(textFieldContext.getText());
            dp.setProblem(textFieldProblem.getText());
            dp.setSolution(textFieldSolution.getText());
            dp.setExample(textFieldExample.getText());
            dp.setImage(String.valueOf(selectedFile));
            dp.setType((DesignPatternType) comboBoxPattern.getValue());

            // Enviar el objeto DesignPattern al servidor
            try (Socket socket = new Socket("10.59.19.178", 12345);
                 ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream())) {
                oos.writeObject(dp);
                alert.setContentText("Diseño añadido!");
                alert.showAndWait();
            } catch (IOException e) {
                e.printStackTrace();
                alert.setContentText("Error al conectar con el servidor.");
                alert.showAndWait();
            }

            // Limpiar los campos después de la inserción
            labelID.setText("");
            textFieldContext.setText("");
            textFieldProblem.setText("");
            textFieldSolution.setText("");
            textFieldExample.setText("");
            selectedFile = null;
            imageViewFile.setImage(null);
            buttonSelectFile.setText("Seleccionar");
            labelID.setText(String.valueOf(Utility.getMaxID(Utility.usualDataFile())));

        } else {
            alert.setContentText("Falta Información");
            alert.showAndWait();
        }
    }

    @javafx.fxml.FXML
    public void paginaPrincipal(ActionEvent actionEvent) {
        loadPage("paginaPrincipal.fxml");
    }

    @javafx.fxml.FXML
    public void selectPattern(ActionEvent actionEvent) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("PNG Files", "*.png"),
                new FileChooser.ExtensionFilter("JPG Files", "*.jpg"),
                new FileChooser.ExtensionFilter("WEBP Files", "*.webp")
        );

        fileChooser.setTitle("Select Image File");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));

        Stage stage = (Stage) bp.getScene().getWindow();
        selectedFile = fileChooser.showOpenDialog(stage);

        if (selectedFile == null) {
            selectedFile = null;
        } else {
            buttonSelectFile.setText("Cargado!");
            imageViewFile.setImage(new Image(selectedFile.toURI().toString()));
        }
    }

    @javafx.fxml.FXML
    public void newType(ActionEvent actionEvent) {
        loadPage("crearTipo.fxml");
    }
}
