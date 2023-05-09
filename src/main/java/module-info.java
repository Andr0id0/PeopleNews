module com.example.userhub {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.xml;
    requires java.sql;
    requires mysql.connector.j;
    requires hibernate.jpa;


    opens com.example.userhub to javafx.fxml;
    exports com.example.userhub;

}