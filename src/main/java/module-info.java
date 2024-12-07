module com.library.library {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.library.library to javafx.fxml;
    exports com.library.library;
}