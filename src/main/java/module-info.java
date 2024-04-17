module com.libraryproject {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.libraryproject to javafx.fxml;
    exports com.libraryproject;
}