package com.example.userhub;

import java.math.BigInteger;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button button;
    DB db = new DB();

    @FXML
    private TextField emailInput;

    @FXML
    private TextField loginInput;

    @FXML
    private PasswordField passwordInput;

    @FXML
    private Label l1;

    @FXML
    private Label l2;

    @FXML
    private Label  l3;

    @FXML
    void initialize() {
    loginInput.setText(db.getUserLogin(1));
    emailInput.setText(db.getUserEmail(1));



        button.setOnAction(event ->{
            changeUserInfo();
        });

    }

    private void changeUserInfo() {
        String login = loginInput.getCharacters().toString();
        String email = emailInput.getCharacters().toString();
        String password = mb5(passwordInput.getCharacters().toString());
        l1.setText("");
        l2.setText("");
        l3.setText("");
        button.setText("Изменить");

         if(login.length() <= 5) {
             loginInput.setStyle("-fx-border-color: red;");
             l1.setText("логин должен быть длиннее 5 символов");
             button.setStyle("-fx-background-color: red;");
         }


        else if (email.length() <= 8 || !email.contains("@") || !email.contains(".")) {
            emailInput.setStyle("-fx-border-color: red;");
            l2.setText("некорректный email");
            button.setStyle("-fx-background-color: red;");
        }


        else if (password.length() <= 6){
            passwordInput.setStyle("-fx-border-color: red;");
            l3.setText("пароль должен быть длиннее 5 символов");
            button.setStyle("-fx-background-color: red;");
        }
        else if (db.allLogins().contains(loginInput.getText())){
            l1.setText("Пользователь с таким логином уже существует");
         }
        else if(db.allEmailss().contains(emailInput.getText())){
            l2.setText("Этот email уже используется");
         }
        else if(!password.equals(db.getUserPassword(1))){
            l3.setText("Чтобы изменить данные нужно ввести правильный пароль");
             passwordInput.setStyle("-fx-border-color: red;");

         }

        else {
            l1.setText("");
            l2.setText("");
            l3.setText("");
            loginInput.setStyle("-fx-border-color: green;");
            emailInput.setStyle("-fx-border-color: green;");
            passwordInput.setStyle("-fx-border-color: green;");
            button.setStyle("-fx-background-color: green;");
            db.changeUserInfo(login, email, password);
            loginInput.setText("");
            emailInput.setText("");
            passwordInput.setText("");
            button.setText("Изменено!");
        }

    }
    public static String mb5(String password){
        MessageDigest messageDigest = null;
        byte[] digest = new byte[0];
        try {
            messageDigest =MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(password.getBytes());
            digest = messageDigest.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        BigInteger bigInteger = new BigInteger(1,digest);
        String m5dHex = bigInteger.toString(16);

        while (m5dHex.length() < 32);
            m5dHex = "" + m5dHex;

            return  m5dHex;
    }

}