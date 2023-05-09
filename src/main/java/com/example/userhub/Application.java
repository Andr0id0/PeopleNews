package com.example.userhub;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException, ClassNotFoundException {
        DB d = new DB();

        File file = new File("user.set");
        if(file.exists()){
            FileInputStream fis = new FileInputStream("user.set");
            ObjectInputStream ois = new ObjectInputStream(fis);
            user user = (user) ois.readObject();
            ois.close();

            if(d.allLogins2().contains(user.getLogin())){
                openScene("home.fxml", stage);
            }
            else {
                openScene("avtorization.fxml", stage);            }
        }
        else {
            openScene("avtorization.fxml", stage);
        }




    }

    public static void main(String[] args)throws SQLException, ClassCastException  {
        HomeController homeController = new HomeController();
        launch();
        System.out.println(homeController.getId());
//       DB db = new DB();
//        try {
//            db.isConnected();
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }

    }
    public static void openScene(String sceneName, Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource(sceneName));
        Scene scene = new Scene(fxmlLoader.load(), 820, 560);
        stage.setScene(scene);
        stage.show();
    }

}