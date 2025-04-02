module com.tap.schoolplatform {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires java.logging;
    requires java.management;
    requires org.jetbrains.annotations;

    opens com.tap.schoolplatform to javafx.fxml;
    exports com.tap.schoolplatform;
    exports com.tap.schoolplatform.controllers;
    opens com.tap.schoolplatform.controllers to javafx.fxml;
}