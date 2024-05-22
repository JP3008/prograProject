package cr.ac.ucr.progra2.paraiso.prograproject.controller;

import cr.ac.ucr.progra2.paraiso.prograproject.HelloApplication;
import cr.ac.ucr.progra2.paraiso.prograproject.domain.DesignPattern;
import cr.ac.ucr.progra2.paraiso.prograproject.domain.DesignPatternData;
import cr.ac.ucr.progra2.paraiso.prograproject.util.Utility;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.jdom2.JDOMException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.cert.Extension;

public class CrearPatron
{
    @javafx.fxml.FXML

    private BorderPane bp;
    @javafx.fxml.FXML
    private TextField textFieldProblem;
    @javafx.fxml.FXML
    private ComboBox comboBoxPattern;
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


    private File selectedFile;
    @javafx.fxml.FXML
    public void initialize() {
        labelID.setText(String.valueOf(Utility.getMaxID()));
        selectedFile = null;
        ObservableList<String> options = FXCollections.observableArrayList("Creacional","Estructural","Comportamiento");
        comboBoxPattern.setItems(options);
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
    public void insertarPatron(ActionEvent actionEvent) throws IOException, JDOMException {
        DesignPattern dp = new DesignPattern();
        DesignPatternData data = new DesignPatternData("file.xml");
        dp.setId(Integer.parseInt(labelID.getText()));
        dp.setContext(textFieldContext.getText());
        dp.setProblem(textFieldProblem.getText());
        dp.setSolution(textFieldSolution.getText());
        dp.setExample("No example");
        dp.setImage(String.valueOf(selectedFile));
        dp.setClassification(String.valueOf(comboBoxPattern.getValue()));

        data.addDesign(dp);
    }

    @javafx.fxml.FXML
    public void paginaPrincipal(ActionEvent actionEvent) {
        loadPage("paginaPrincipal.fxml");
    }

    @javafx.fxml.FXML
    public void selectPattern(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("PNG Files", "*.png"),
                new FileChooser.ExtensionFilter("JPG Files", "*.jpg")
        );

        fileChooser.setTitle("Select Image File");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));

        // Get the current stage from any component
        Stage stage = (Stage) bp.getScene().getWindow();
        selectedFile = fileChooser.showOpenDialog(stage);

        if (selectedFile == null) {
            selectedFile=null;
        }
    }
}