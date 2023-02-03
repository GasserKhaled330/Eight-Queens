module com.eightqueens {
    requires javafx.controls;



    opens com.eightqueens to javafx.fxml;
    exports com.eightqueens;
}