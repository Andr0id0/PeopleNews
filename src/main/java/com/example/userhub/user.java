package com.example.userhub;

import java.io.Serializable;

public class user implements Serializable {


    private String login;

    public String getLogin() {
        return login;
    }

    public user(String login) {
        this.login = login;

    }
}
