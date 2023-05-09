package com.example.userhub;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.w3c.dom.events.Event;

import javax.persistence.criteria.CriteriaBuilder;

public class HomeController {

    @FXML
    private VBox pVBox;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button CreateButton;

    @FXML
    private Button exitButton;



    public  void setId(String id) {
        this.idd = id;
    }

    public String getId() {
        return idd;
    }

    static String idd = "";

    @FXML
    public void initialize() throws IOException, SQLException {
        DB db = new DB();
        ResultSet resultSet = db.getArticles();

        while (resultSet.next()) {

            Node node = FXMLLoader.load(Objects.requireNonNull(Application.class.getResource("panel.fxml")));


            Label title = (Label) node.lookup("#title");
            title.setText(resultSet.getString("title"));

            Label views = (Label) node.lookup("#views");
            views.setText("Просмотров: " + resultSet.getString("views"));

            String.valueOf(views);

            Label id = (Label) node.lookup("#id");
            id.setText(resultSet.getString("id"));

            node.setOnMouseEntered(mouseEvent -> {
                node.setStyle("-fx-background-color: #343434;");
            });

            node.setOnMouseExited(mouseEvent -> {
                node.setStyle("-fx-background-color: #2b2a2a;");
            });


            pVBox.getChildren().add(node);
            pVBox.setSpacing(10);

            idd = resultSet.getString("id");
            node.setOnMouseClicked(mouseEvent ->{


                Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
                try {
                    Application.openScene("State.fxml", stage);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });


        }






        exitButton.setOnAction(event -> {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            try {
                File file = new File("user.set");
                file.delete();

                Application.openScene("avtorization.fxml", stage);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });


        CreateButton.setOnAction(event -> {


            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            try {
                Application.openScene("newState.fxml", stage);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });


    }

}








