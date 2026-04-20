package com.diamondogs.trucksapp.model;

public class User {
    private int id;
    private String username;
    private String name;
    private String role;
    private String phone;
    private String password;
    private String is_active;

    public User() {
    }

    public User(int id, String name, String username, String role, String phone,  String password) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.role = role;
        this.phone = phone;
        this.password = password;
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
    public String getPassword() {return password;}
    public String getIs_active() {return is_active;}

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
    public void setIs_active(String is_active) {
        this.is_active = is_active;
    }
    public void setPassword(String password) {this.password = password;}
}
