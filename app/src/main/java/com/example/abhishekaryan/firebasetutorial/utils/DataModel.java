package com.example.abhishekaryan.firebasetutorial.utils;

public class DataModel {

    private String id;
    private String name;
    private String email;


    public DataModel(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public DataModel() {
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
