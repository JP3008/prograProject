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
import javafx.fxml.FXML;
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
import java.util.List;
import java.util.Optional;

public class BuscarPatron
{
    @FXML
    private BorderPane bp;
    @FXML
    private Button buttonBack;
    @FXML
    private Button modifyButton;
    private Alert alert = new Alert(Alert.AlertType.INFORMATION);
    private Alert deleting = new Alert(Alert.AlertType.CONFIRMATION);
    public int saveId;
    @FXML
    private TextField solutionLabel;
    @FXML
    private TextField exampleLabel;
    @FXML
    private TextField contextLabel;
    @FXML
    private TextField problemLabel;
    @FXML
    private Button selectButton;
    private boolean modifying = false;
    private boolean fileExploring = false;
    @FXML
    private ComboBox comboBoxType;
    @FXML
    private Label typeLabel;
    private File selectedFile;
    @FXML
    private Button searchButton;
    @FXML
    private Button buttonDelete;
    @FXML
    private ImageView designImageView;
    @FXML
    private ComboBox cbFilter;
    @FXML
    private Button filterButton;
    @FXML
    private ComboBox comboBoxIDList;

    DesignPatternTypeData dataType = new DesignPatternTypeData(String.valueOf(Utility.usualTypeFile()));
    DesignPatternData dataDesign = new DesignPatternData(String.valueOf(Utility.usualDataFile()));
    @FXML
    private Button removeFilterButton;

    public BuscarPatron() throws IOException, JDOMException {
    }

    private void loadPage(String page){
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(page));
        try {
            this.bp.setCenter(fxmlLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    ObservableList<Integer> cbIDOptions;
    ObservableList<DesignPatternType> cbTypeOptions;

    @FXML
    public void initialize() throws IOException, JDOMException {

        this.cbIDOptions = FXCollections.observableArrayList(Utility.getIDList(Utility.usualDataFile()));
        comboBoxIDList.setItems(cbIDOptions);

        modifyButton.setDisable(true);
        buttonDelete.setDisable(true);


        this.cbTypeOptions = FXCollections.observableArrayList(dataType.findAll());
        cbFilter.setItems(cbTypeOptions);
        comboBoxType.setItems(cbTypeOptions);


    }

    @FXML
    public void paginaPrincipal(ActionEvent actionEvent) {


        if (!modifying) {
            loadPage("paginaPrincipal.fxml");
        }else{
            //Copiar esto
            modifying=false;

            comboBoxIDList.setDisable(false);
            problemLabel.setEditable(false);
            contextLabel.setEditable(false);
            solutionLabel.setEditable(false);
            exampleLabel.setEditable(false);
            typeLabel.setVisible(true);
            comboBoxType.setVisible(false);
            selectButton.setVisible(false);
            searchButton.setDisable(false);

            modifyButton.setText("Modificar");
            buttonBack.setText("Página principal");

            contextLabel.setText(contextSave);
            exampleLabel.setText(exampleSave);
            solutionLabel.setText(solutionSave);
            typeLabel.setText(typeSave);
            problemLabel.setText(problemSave);
            designImageView.setImage(imgSave);

            modifying=false;


        }
    }

    @FXML
    public void searchOnAction(ActionEvent actionEvent) throws IOException, JDOMException {

        if (comboBoxIDList.getValue() != null) {

            int value = (int) comboBoxIDList.getValue();
            DesignPattern design = Utility.getDesign(value);
            problemLabel.setText(design.getProblem());
            selectedFile = new File(design.getImage());
            designImageView.setImage(Utility.decode(design.getImage()));
            contextLabel.setText(design.getContext());
            solutionLabel.setText(design.getSolution());
            exampleLabel.setText(design.getExample());

            DesignPatternType dpt = Utility.getDesignType(design.getType());
            //System.out.println(dpt.getID());
            typeLabel.setText(dpt.getType());
            buttonDelete.setDisable(false);
            modifyButton.setDisable(false);
            comboBoxType.setValue(typeLabel.getText());
        }else{
            alert.setContentText("No se selecciono un patrón, seleccione un ID");
            alert.showAndWait();

        }
    }

    String problemSave;
    String contextSave;
    String solutionSave;
    String exampleSave;
    String typeSave;
    Image imgSave;
    String imgSave64;

    @FXML
    public void modifyOnAction(ActionEvent actionEvent) throws IOException, JDOMException {
        if (!modifying){

            //saving in case they change their mind lmao
            problemSave = problemLabel.getText();
            contextSave = contextLabel.getText();
            solutionSave = solutionLabel.getText();
            exampleSave = exampleLabel.getText();
            typeSave = typeLabel.getText();
            DesignPattern aux = Utility.getDesign((Integer) comboBoxIDList.getValue());
            imgSave = Utility.decode(aux.getImage());
            imgSave64 = aux.getImage();

            comboBoxIDList.setDisable(true);
            problemLabel.setEditable(true);
            contextLabel.setEditable(true);
            solutionLabel.setEditable(true);
            exampleLabel.setEditable(true);
            typeLabel.setVisible(false);
            comboBoxType.setVisible(true);
            selectButton.setVisible(true);
            searchButton.setDisable(true);

            cbFilter.setDisable(true);
            filterButton.setDisable(true);
            removeFilterButton.setDisable(true);

            modifyButton.setText("Aceptar");
            buttonBack.setText("Cancelar");
            modifying=true;
            comboBoxType.setValue(typeLabel.getText());



        }else{
            DesignPattern dp = new DesignPattern();


            if (!problemLabel.getText().isEmpty() && !solutionLabel.getText().isEmpty() && !contextLabel.getText().isEmpty() && !exampleLabel.getText().isEmpty() && comboBoxType.getValue() != null) {

                dp.setDesignID((Integer) comboBoxIDList.getValue());
                dp.setContext(contextLabel.getText());
                dp.setProblem(problemLabel.getText());
                dp.setSolution(solutionLabel.getText());

                dp.setExample(exampleLabel.getText());

                if (fileExploring) {
                    dp.setImage(String.valueOf(selectedFile));
                }else{
                    dp.setImageAs64(imgSave64);

                }

                if (comboBoxType.getValue()!=null) {
                    dp.setType((DesignPatternType) comboBoxType.getValue());
                    dataDesign.modifyDesign((Integer) comboBoxIDList.getValue(), dp);

                    alert.setContentText("Design changed!");
                    alert.showAndWait();
                    //Copiar esto
                    modifying = false;

                    restore();

                    modifyButton.setText("Modificar");
                    buttonBack.setText("Página principal");

                    fileExploring=false;
                }else {
                    alert.setContentText("Information missing!");
                    alert.showAndWait();
                }


            } else {
                alert.setContentText("Information missing!");
                alert.showAndWait();
            }

        }
    }

    @FXML
    public void deleteOnAction(ActionEvent actionEvent) throws IOException, JDOMException {


        deleting.setContentText("Are you sure you want to delete this pattern?");
        Optional<ButtonType> result = deleting.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {

            dataDesign.deleteDesign((Integer) comboBoxIDList.getValue());

            restore();

        }
    }

    private void restore() throws IOException, JDOMException {

        this.cbIDOptions = FXCollections.observableArrayList(Utility.getIDList(Utility.usualDataFile()));
        comboBoxIDList.setItems(cbIDOptions);
        this.cbTypeOptions = FXCollections.observableArrayList(dataType.findAll());
        cbFilter.setItems(cbTypeOptions);
        comboBoxType.setItems(cbTypeOptions);

        comboBoxIDList.setValue(null);
        comboBoxType.setValue(null);
        cbFilter.setValue(null);


        designImageView.setImage(null);
        problemLabel.setText("");
        contextLabel.setText("");
        exampleLabel.setText("");
        typeLabel.setText("");
        solutionLabel.setText("");

        comboBoxIDList.setDisable(false);
        problemLabel.setEditable(false);
        contextLabel.setEditable(false);
        solutionLabel.setEditable(false);
        exampleLabel.setEditable(false);
        typeLabel.setVisible(true);
        comboBoxType.setVisible(false);
        selectButton.setVisible(false);
        searchButton.setDisable(false);

        cbFilter.setDisable(false);
        filterButton.setDisable(false);
        removeFilterButton.setDisable(true);



    }

    @FXML
    public void selectOnAction(ActionEvent actionEvent) {

        fileExploring=true;

        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("PNG Files", "*.png"),
                new FileChooser.ExtensionFilter("JPG Files", "*.jpg"),
                new FileChooser.ExtensionFilter("WEBP Files", "*.webp")
        );

        fileChooser.setTitle("Select Image File");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));

        // Get the current stage from any component
        Stage stage = (Stage) bp.getScene().getWindow();
        selectedFile = fileChooser.showOpenDialog(stage);



        if (selectedFile == null) {
            selectedFile=null;
        }else{
            selectButton.setText("Cargado!");
            designImageView.setImage(new Image(selectedFile.toString()));
        }

    }

    @FXML
    public void filteringOnAction(ActionEvent actionEvent) {
        buttonDelete.setDisable(true);
        modifyButton.setDisable(true);
        comboBoxIDList.setValue(null);
    }


    @FXML
    public void SearchingOnAction(ActionEvent actionEvent) {
        buttonDelete.setDisable(true);
        modifyButton.setDisable(true);
    }


    //Añadir el filtro
    @FXML
    public void filterButton(ActionEvent actionEvent) throws IOException, JDOMException {
        if (cbFilter.getValue()!=null) {

            String typeFilter = cbFilter.getValue().toString();
            List<Integer> list = dataDesign.getTypesID(typeFilter);

            this.cbIDOptions = FXCollections.observableArrayList(list);
            comboBoxIDList.setItems(cbIDOptions);
            //Va a mostrar solo los id con la opción seleccionada

            filterButton.setText("Filtrando");
            removeFilterButton.setDisable(false);
        }else{
            alert.setContentText("Debe de seleccionar un filtro de tipo");
            alert.showAndWait();
        }
    }

    //Quitar el filtro
    @FXML
    public void removeFilterOnAction(ActionEvent actionEvent) {

        cbFilter.setValue(null);
        filterButton.setText("Filtrar");
        removeFilterButton.setDisable(true);


    }
}