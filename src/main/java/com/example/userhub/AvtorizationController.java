package com.example.userhub;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AvtorizationController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button Abutton;

    @FXML
    private PasswordField ApasswordInput;

    @FXML
    private TextField AloginInput;

    @FXML
    private PasswordField RgstrtonRpasswordInput1;

    @FXML
    private Button Rgstrtonbutton;
    DB db = new DB();

    @FXML
    private TextField RgstrtonemailInput;

    @FXML
    private TextField RgstrtonloginInput;

    @FXML
    private Label Rerrors;

    @FXML
    private PasswordField RgstrtonpasswordInput;

    @FXML
    private Label l1;

    @FXML
    private Label l2;

    @FXML
    void initialize() {
        Abutton.setOnAction(event ->{
            try {
                InAccaunt(event);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        Rgstrtonbutton.setOnAction(event ->{
            RegAcc(event);
        });

            }

    private void RegAcc(ActionEvent event) {
        String Rlogin = RgstrtonloginInput.getCharacters().toString();
        String Remail = RgstrtonemailInput.getCharacters().toString();
        String Rpassword = Controller.mb5(RgstrtonpasswordInput.getCharacters().toString());
        String Rpassword2 = Controller.mb5(RgstrtonRpasswordInput1.getCharacters().toString());
        if(Rlogin.contains(db.allLogins2())){
            Rerrors.setText("Логин занят");
        }
        else if(Remail.contains(db.allEmailss2())){
            Rerrors.setText("Email занят");
        }
        else if (!Rpassword.equals(Rpassword2)){
            Rerrors.setText("Установите одинаковые пароли");
            RgstrtonpasswordInput.setStyle("-fx-background-color: red;");
            RgstrtonRpasswordInput1.setStyle("-fx-background-color: red;");
        }
        else {
            db.createUser(Rlogin, Remail, Rpassword);
            Rgstrtonbutton.setText("Готово!");
            RgstrtonloginInput.setText("");
            RgstrtonemailInput.setText("");
            RgstrtonpasswordInput.setText("");
            RgstrtonRpasswordInput1.setText("");
            RgstrtonloginInput.setStyle("-fx-border-color: green;");
            RgstrtonemailInput.setStyle("-fx-border-color: green;");
            RgstrtonpasswordInput.setStyle("-fx-border-color: green;");
            RgstrtonRpasswordInput1.setStyle("-fx-border-color: green;");

        }
    }

    private void InAccaunt(ActionEvent event) throws IOException {


        String Alogin = AloginInput.getCharacters().toString();
        String Apassword = Controller.mb5(ApasswordInput.getCharacters().toString());

        if(!Apassword.equals(db.enterAC(Alogin)))  {

            l2.setText("Неверный пароль");
        }
        else{

            FileOutputStream fos = new FileOutputStream("user.set");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(new user(Alogin));
            oos.close();

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Application.openScene("home.fxml", stage);
        }


    }

}
