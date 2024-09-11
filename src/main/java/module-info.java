module org.example.paint {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.paint to javafx.fxml;
    exports org.example.paint;
}