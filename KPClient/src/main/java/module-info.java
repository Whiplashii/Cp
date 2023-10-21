module com.lavalamp.kpclient {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.lavalamp.kpclient to javafx.fxml;
    exports com.lavalamp.kpclient;
}