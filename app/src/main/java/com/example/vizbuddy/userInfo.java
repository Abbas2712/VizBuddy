package com.example.vizbuddy;

public class userInfo {

    String name;
    String email;

    public userInfo(String id, String uName, String email){

    }

    public userInfo(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
