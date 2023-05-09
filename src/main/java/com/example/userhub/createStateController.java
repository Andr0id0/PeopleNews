package com.example.userhub;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class createStateController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;



    @FXML
    private Button stateButton;

    @FXML
    private TextField stateName;


    @FXML
    private TextArea stateText1;

    @FXML
    void initialize() {

        stateButton.setOnAction(event -> {
            try {
                createState(event);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });


    }

    private void createState(ActionEvent event) throws SQLException, ClassNotFoundException, IOException {
        DB db = new DB();

        String StateName = stateName.getCharacters().toString();
        String StateText = stateText1.getText().toString();

        db.createState(StateName, StateText);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Application.openScene("home.fxml", stage);
    }

}
