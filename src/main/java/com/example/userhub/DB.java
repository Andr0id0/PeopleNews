package com.example.userhub;

import javafx.scene.control.Label;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DB {
     private final String HOST = "localhost";
     private final String PORT = "3306";
     private final String DB_NAME = "dbSP";
     private final String LOGIN = "root";
     private final String PASS = "";

    private Connection dbConn = null;

    public Connection getDBConnect() throws ClassNotFoundException, SQLException {
        String connStr = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DB_NAME + "?characterEncoding=UTF-8&useLegacyDatetimeCode=false&serverTimezone=UTC";
        Class.forName("com.mysql.cj.jdbc.Driver");

        dbConn = DriverManager.getConnection(connStr, LOGIN, PASS);
        return dbConn;
    }

    public void isConnected() throws SQLException, ClassNotFoundException {
        dbConn = getDBConnect();
        System.out.println(dbConn.isValid(1000));

    }

    public void createUser(String login, String email, String password){

        String sql = "INSERT INTO users (login, email, password) VALUES (?, ?, ?)";
        try {
            PreparedStatement PRstatement = getDBConnect().prepareStatement(sql);
            PRstatement.setString(1, login);
            PRstatement.setString(2, email);
            PRstatement.setString(3, password);
            PRstatement.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    public String getUserPassword(Integer id) {
        String passwordd = "";
        String sql = "SELECT password FROM users WHERE id = 1";
        try {
            Statement statement = getDBConnect().createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                String password = resultSet.getString("password");
                return password;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


        return passwordd;
    }

    public String getUserLogin(Integer id){
        String loginn = "";
        String sql = "SELECT login FROM users WHERE id = 1";
        try {
            Statement statement = getDBConnect().createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()){
                String login = resultSet.getString("login");
                return login;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }



        return loginn;
    }
    public String getUserEmail(Integer id){
        String emaill = "";
        String sql = "SELECT email FROM users WHERE id = 1";
        try {
            Statement statement = getDBConnect().createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()){
                String email = resultSet.getString("email");
                return email;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return emaill;
    }


    public void changeUserInfo(String login, String email, String password){
        String sql = "UPDATE users SET login = ?, email = ?, password = ? WHERE id = 1";


        try {
            PreparedStatement prSt = getDBConnect().prepareStatement(sql);
            prSt.setString(1, login);
            prSt.setString(2, email);
            prSt.setString(3, password);
            prSt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }
    public String allLogins(){

        String sql = "SELECT login FROM users";
        try {
            Statement statement = getDBConnect().createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            List<String> allUsers = new ArrayList<>(Arrays.asList());
            while (resultSet.next()){
                allUsers.add(resultSet.getString("login"));

            }
            allUsers.remove(getUserLogin(1));

            return String.valueOf(allUsers);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }

    public String allLogins2(){

        String sql = "SELECT login FROM users";
        try {
            Statement statement = getDBConnect().createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            List<String> allUsers = new ArrayList<>(Arrays.asList());
            while (resultSet.next()){
                allUsers.add(resultSet.getString("login"));

            }

            return String.valueOf(allUsers);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }

    public String allEmailss(){

        String sql = "SELECT email FROM users";
        try {
            Statement statement = getDBConnect().createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            List<String> allEmails = new ArrayList<>(Arrays.asList());
            while (resultSet.next()){
                allEmails.add(resultSet.getString("email"));


            }
            allEmails.remove(getUserEmail(1));

            return String.valueOf(allEmails);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }
    public String allEmailss2(){

        String sql = "SELECT email FROM users";
        try {
            Statement statement = getDBConnect().createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            List<String> allEmails = new ArrayList<>(Arrays.asList());
            while (resultSet.next()){
                allEmails.add(resultSet.getString("email"));


            }

            return String.valueOf(allEmails);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }


    public String enterAC(String login){
        String passwordd = "";
        String sql = "SELECT password FROM users WHERE login = ?";
        try {
            PreparedStatement statement = getDBConnect().prepareStatement(sql);
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){

                String password = resultSet.getString("password");
                return password;
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return passwordd;
    }
    public ResultSet getArticles(){
        String sql = "SELECT * FROM article;";
        Statement statement = null;
        try {
             statement = getDBConnect().createStatement();
            return statement.executeQuery(sql);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;

    }

    public void createState(String title, String text) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO article (title, text, views) VALUES (?, ?, ?);";

            PreparedStatement PRstatement = getDBConnect().prepareStatement(sql);
            PRstatement.setString(1, title);
            PRstatement.setString(2, text);
            PRstatement.setString(3, "228");

            PRstatement.executeUpdate();



    }

    public ResultSet findeStateInfio(String id){
        String sql = "SELECT * FROM article WHERE id = " + id +";";
        Statement statement = null;

        try {
            statement = getDBConnect().createStatement();
            return statement.executeQuery(sql);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
}

    public String findId(String title) throws SQLException, ClassNotFoundException {

        String sql = "SELECT id FROM article WHERE title = " + title ;

        Statement statement = getDBConnect().createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()){
            return resultSet.getString("id");
        }

        return null;

}
}




