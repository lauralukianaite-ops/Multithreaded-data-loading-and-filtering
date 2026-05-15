module org.example.multithreadeddata {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.multithreadeddata to javafx.fxml, javafx.base;
    exports org.example.multithreadeddata;
}