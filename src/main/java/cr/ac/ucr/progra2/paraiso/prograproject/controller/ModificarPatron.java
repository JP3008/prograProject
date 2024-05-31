package cr.ac.ucr.progra2.paraiso.prograproject.controller;

import cr.ac.ucr.progra2.paraiso.prograproject.HelloApplication;
import cr.ac.ucr.progra2.paraiso.prograproject.data.DesignPatternData;
import cr.ac.ucr.progra2.paraiso.prograproject.data.DesignPatternTypeData;
import cr.ac.ucr.progra2.paraiso.prograproject.domain.DesignPattern;
import cr.ac.ucr.progra2.paraiso.prograproject.domain.DesignPatternType;
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

import java.io.File;
import java.io.IOException;

public class ModificarPatron {
    @javafx.fxml.FXML
    private TextField textFieldProblem;
    @javafx.fxml.FXML
    private ComboBox comboBoxPattern;
    @javafx.fxml.FXML
    private Label labelID;
    @javafx.fxml.FXML
    private Button buttonSelectFile;
    @javafx.fxml.FXML
    private TextField textFieldExample;
    @javafx.fxml.FXML
    private ImageView imageviewFile;
    @javafx.fxml.FXML
    private Button buttonInsert;
    @javafx.fxml.FXML
    private BorderPane bp;
    @javafx.fxml.FXML
    private TextField textFieldSolution;
    @javafx.fxml.FXML
    private TextField textFieldContext;
    @javafx.fxml.FXML
    private Button buttonBack;

    private Alert alert = new Alert(Alert.AlertType.INFORMATION);



    private File selectedFile;

    @javafx.fxml.FXML
    private ImageView imageViewFile;

    @javafx.fxml.FXML
    public void initialize() throws IOException, JDOMException {
        Utility ut = new Utility();
        DesignPattern design = Utility.getDesign(ut.returnID());
        labelID.setText(String.valueOf(design.getDesignID()));
        selectedFile = null;
        DesignPatternTypeData data = new DesignPatternTypeData(String.valueOf(Utility.usualTypeFile()));
        ObservableList<DesignPatternType> options = FXCollections.observableArrayList(data.findAll());
        comboBoxPattern.setItems(options);

        System.out.println(design);
        textFieldContext.setText(design.getContext());
        textFieldExample.setText(design.getExample());
        textFieldProblem.setText(design.getProblem());
        textFieldSolution.setText(design.getSolution());
        //DesignPatternType type = Utility.getDesignType(design.getType());
        //comboBoxPattern.setValue(type.getType());
        //imageViewFile.setImage(Utility.decode(design.getImage()));

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
        DesignPatternData data = new DesignPatternData(String.valueOf(Utility.usualDataFile()));


        if (!textFieldExample.getText().isEmpty() && !labelID.getText().isEmpty() && !textFieldContext.getText().isEmpty() && !textFieldProblem.getText().isEmpty() && !textFieldSolution.getText().isEmpty() && comboBoxPattern.getValue() != null && selectedFile!=null) {

            dp.setDesignID(Integer.parseInt(labelID.getText()));
            dp.setContext(textFieldContext.getText());
            dp.setProblem(textFieldProblem.getText());
            dp.setSolution(textFieldSolution.getText());

            dp.setExample(textFieldExample.getText());

            dp.setImage(String.valueOf(selectedFile));
            dp.setType((DesignPatternType) comboBoxPattern.getValue());

            data.modifyDesign(Integer.parseInt(labelID.getText()),dp);

            alert.setContentText("Design modified!");
            alert.showAndWait();

            paginaPrincipal(actionEvent);
        }else{
            alert.setContentText("Information missing!");
            alert.showAndWait();

        }

    }

    @javafx.fxml.FXML
    public void paginaPrincipal(ActionEvent actionEvent) {
        loadPage("buscarPatron.fxml");
    }

    @javafx.fxml.FXML
    public void selectPattern(ActionEvent actionEvent) throws IOException {
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
        }else{
            buttonSelectFile.setText("Cargado!");
            imageViewFile.setImage(new Image(selectedFile.toString()));
        }
    }

}
