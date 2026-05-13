module org.example.multithreadeddata {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.multithreadeddata to javafx.fxml;
    exports org.example.multithreadeddata;
}