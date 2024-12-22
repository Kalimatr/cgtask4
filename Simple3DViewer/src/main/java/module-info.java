module com.cgvsu {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.graphics;



    opens com.cgvsu.Common to javafx.fxml;
    exports com.cgvsu.Common;
}