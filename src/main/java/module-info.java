module com.zerebos {
    requires javafx.controls;
    requires javafx.fxml;
    // requires java.awt;
    requires javafx.swing;
    requires transitive javafx.graphics;

    opens com.zerebos to javafx.fxml;
    exports com.zerebos;
}
