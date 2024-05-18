module cr.ac.ucr.progra2.paraiso.prograproject {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;

    opens cr.ac.ucr.progra2.paraiso.prograproject to javafx.fxml;
    exports cr.ac.ucr.progra2.paraiso.prograproject;
}