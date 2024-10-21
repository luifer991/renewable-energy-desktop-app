module com.renewal.energies.renewalenergies {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;
    
    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires java.sql;
    
    opens com.renewal.energies to javafx.fxml;
    exports com.renewal.energies;
    exports com.renewal.energies.controlador;
    opens com.renewal.energies.controlador to javafx.fxml;
}