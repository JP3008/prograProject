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
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.jdom2.JDOMException;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

public class CrearTipo
{
    @javafx.fxml.FXML

    private BorderPane bp;
    @javafx.fxml.FXML
    private Label labelID;
    @javafx.fxml.FXML
    private Button buttonBack;
    private Alert alert = new Alert(Alert.AlertType.INFORMATION);
    private Alert deleting = new Alert(Alert.AlertType.CONFIRMATION);

    @javafx.fxml.FXML
    private Button buttonInsert1;
    @javafx.fxml.FXML
    private TextField textFieldName;
    @javafx.fxml.FXML
    private Button deleteButton;
    @javafx.fxml.FXML
    private Button modifyButton;
    @javafx.fxml.FXML
    private Button buttonSearch;
    @javafx.fxml.FXML
    private TextField textFieldType;
    @javafx.fxml.FXML
    private ComboBox searchComboBox;
    boolean isModifying = false;

    ObservableList<Integer> options;

    @javafx.fxml.FXML
    public void initialize() throws IOException, JDOMException {
        labelID.setText(String.valueOf(Utility.getMaxID(Utility.usualTypeFile())));
        this.options= FXCollections.observableArrayList(Utility.getIDList(Utility.usualTypeFile()));
        searchComboBox.setItems(options);
        deleteButton.setDisable(true);
        modifyButton.setDisable(false);
        //DesignPatternTypeData data = new DesignPatternTypeData(String.valueOf(Utility.usualTypeFile()));
        //ObservableList<DesignPatternType> options = FXCollections.observableArrayList(data.findAll());
        //comboBoxPattern.setItems(options);

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
    public void backOnAction(ActionEvent actionEvent) {loadPage("crearPatron.fxml");
    }

    String saveName;

    @javafx.fxml.FXML
    public void modifyOnAction(ActionEvent actionEvent) throws IOException, JDOMException {
        if (!isModifying){

            buttonSearch.setDisable(true);
            buttonInsert1.setDisable(true);
            textFieldName.setEditable(true);
            deleteButton.setText("Cancelar");
            isModifying=true;

        }else{

            if (!textFieldName.getText().isEmpty()) {
                int value = (Integer) searchComboBox.getValue();
                DesignPatternType dpt = new DesignPatternType(value,textFieldName.getText());


                DesignPatternTypeData data = new DesignPatternTypeData(String.valueOf(Utility.usualTypeFile()));
                data.modifyType((Integer) searchComboBox.getValue(), dpt);
                alert.setContentText("Diseño modificado");
                alert.showAndWait();
                textFieldName.setEditable(false);
                buttonInsert1.setDisable(false);
                buttonSearch.setDisable(false);
                deleteButton.setText("Eliminar");

                isModifying = false;

                labelID.setText(String.valueOf(Utility.getMaxID(Utility.usualTypeFile())));
                this.options = FXCollections.observableArrayList(Utility.getIDList(Utility.usualTypeFile()));
                searchComboBox.setItems(options);

            }else{
                alert.setContentText("Hace faltan campos");
                alert.showAndWait();
            }

        }
    }

    @javafx.fxml.FXML
    public void insertOnAction(ActionEvent actionEvent) throws IOException, JDOMException {

        DesignPatternType dpt = new DesignPatternType();
        DesignPatternTypeData data = new DesignPatternTypeData(String.valueOf(Utility.usualTypeFile()));


        if (!textFieldType.getText().isEmpty()) {

            dpt.setID(Integer.parseInt(labelID.getText()));
            dpt.setType(textFieldType.getText());

            data.addDesign(dpt);

            alert.setContentText("Tipo añadido!");
            alert.showAndWait();

            textFieldType.setText("");

            this.options= FXCollections.observableArrayList(Utility.getIDList(Utility.usualTypeFile()));
            searchComboBox.setItems(options);
            labelID.setText(String.valueOf(Utility.getMaxID(Utility.usualTypeFile())));



        }else{
            alert.setContentText("Falta Información");
            alert.showAndWait();

        }
    }

    @javafx.fxml.FXML
    public void searchOnAction(ActionEvent actionEvent) throws IOException, JDOMException {
        if (searchComboBox.getValue() != null) {

            int value = (int) searchComboBox.getValue();
            DesignPatternType designType = Utility.getDesignType(value);
            textFieldName.setText(designType.getType());
            deleteButton.setDisable(false);
            modifyButton.setDisable(false);

        }else{
            alert.setContentText("No se selecciono un patrón, seleccione un ID");
            alert.showAndWait();

        }
    }

    @javafx.fxml.FXML
    public void deleteOnAction(ActionEvent actionEvent) throws IOException, JDOMException {
        if (isModifying){

            //cancel
            buttonSearch.setDisable(false);
            buttonInsert1.setDisable(false);
            deleteButton.setText("Eliminar");
            textFieldName.setText(saveName);
            textFieldName.setEditable(false);
            isModifying=false;


         }else{
            DesignPatternType type = Utility.getDesignType((Integer)searchComboBox.getValue());
            DesignPatternTypeData data = new DesignPatternTypeData(String.valueOf(Utility.usualTypeFile()));

            deleting.setContentText("Are you sure you want to delete this type?");
            Optional<ButtonType> result = deleting.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {

                data.deleteDesignType((Integer) searchComboBox.getValue());

                searchComboBox.setValue(null);
                textFieldName.setText("");

                labelID.setText(String.valueOf(Utility.getMaxID(Utility.usualDataFile())));
                this.options= FXCollections.observableArrayList(Utility.getIDList(Utility.usualTypeFile()));
                searchComboBox.setItems(options);
                buttonSearch.setDisable(false);
                buttonInsert1.setDisable(false);
            }


        }
    }

    @javafx.fxml.FXML
    public void searchingOnAction(ActionEvent actionEvent) {
    }
}