package com.example.userhub;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ceckStateController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button BackButton;

    @FXML
    private Label StateName;

    @FXML
    private Label text;

    @FXML
    private Label views;

    @FXML
    void initialize() throws SQLException, IOException {

        DB db = new DB();
        HomeController HC = new HomeController();
        ResultSet resultSet =  db.findeStateInfio(HomeController.idd);

        while (resultSet.next()){

            StateName.setText(resultSet.getString("title"));
            text.setText(resultSet.getString("text"));
            views.setText("Просмотры: " + resultSet.getString("views"));


        }


        BackButton.setOnAction(event -> {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            try {
                Application.openScene("home.fxml", stage);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });


}}
