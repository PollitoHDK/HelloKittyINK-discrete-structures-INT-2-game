module com.example.animationg3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.junit.jupiter.api;


    opens com.example.animationg3 to javafx.fxml;
    exports com.example.animationg3.util;
    exports com.example.animationg3;
    exports com.example.animationg3.control;
    opens com.example.animationg3.control to javafx.fxml;
}