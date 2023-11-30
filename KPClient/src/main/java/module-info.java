module com.lavalamp.kpclient {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.lavalamp.kpclient to javafx.fxml;
    exports com.lavalamp.kpclient;
    exports com.lavalamp.kpclient.contollers;
    opens com.lavalamp.kpclient.contollers to javafx.fxml;
}