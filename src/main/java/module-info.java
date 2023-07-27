module com.zerebos {
    requires javafx.controls;
    requires javafx.fxml;
    // requires java.awt;
    requires javafx.swing;

    opens com.zerebos to javafx.fxml;
    exports com.zerebos;
}
