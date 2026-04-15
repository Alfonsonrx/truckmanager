package com.diamondogs.trucksapp.model;

public class User {
    private int id;
    private String username;
    private String name;
    private String role;
    private String phone;


    public User() {
    }

    public User(int id, String name, String username, String role, String phone) {
        this.username = username;
        this.name = name;
        this.role = role;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }
    public String getUsername() {
        return username;
    }
    public String getName() {
        return name;
    }
    public String getRole() {
        return role;
    }
    public String getPhone() {
        return phone;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setRole(String role) {
        this.role = role;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
}
