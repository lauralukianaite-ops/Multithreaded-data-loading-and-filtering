module org.example.multithreadeddata {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens org.example.multithreadeddata to javafx.fxml, javafx.base;
    exports org.example.multithreadeddata;
}