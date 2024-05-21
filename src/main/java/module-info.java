module cr.ac.ucr.progra2.paraiso.prograproject {
    requires javafx.controls;
    requires javafx.fxml;


    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.jdom2;
    requires java.xml;

    opens cr.ac.ucr.progra2.paraiso.prograproject to javafx.fxml;
    exports cr.ac.ucr.progra2.paraiso.prograproject;
    exports cr.ac.ucr.progra2.paraiso.prograproject.controller;
    opens cr.ac.ucr.progra2.paraiso.prograproject.controller to javafx.fxml;
}