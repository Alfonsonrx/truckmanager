package com.diamondogs.trucksapp.model;

import java.sql.Date;

public class Truck {
    private int id;
    private String plate;
    private String brand;
    private String model;
    private String color;
    private int year;
    private Date latest_maintenance;
    private int kilometers;
    private int driver;
    private String is_active;

    public Truck() {}

    public Truck(int id, String plate, String brand, String model, String color, int year, Date latest_maintenance, int kilometers, int driver) {
        this.id = id;
        this.plate = plate;
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.year = year;
        this.latest_maintenance = latest_maintenance;
        this.kilometers = Math.abs(kilometers);
        this.driver = driver;
    }

    public boolean requiresMaintenance() {
        return this.kilometers > 5000;
    }

    public int getId() {
        return id;
    }
    public String getPlate() {
        return plate;
    }
    public String getBrand() {
        return brand;
    }
    public String getModel() {
        return model;
    }
    public String getColor() {
        return color;
    }
    public int getYear() {
        return year;
    }
    public Date getLatest_maintenance() {
        return latest_maintenance;
    }
    public int getKilometers() {
        return kilometers;
    }
    public int getDriver() {
        return driver;
    }
    public String getIs_active() {return is_active;}

    public void setId(int id) {
        this.id = id;
    }
    public void setPlate(String plate) {
        this.plate = plate;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }
    public void setModel(String model) {
        this.model = model;
    }
    public void setColor(String color) {
        this.color = color;
    }
    public void setYear(int year) {
        this.year = year;
    }
    public void setLatest_maintenance(Date latest_maintenance) {
        this.latest_maintenance = latest_maintenance;
    }
    public void setKilometers(int kilometers) {
        this.kilometers = Math.abs(kilometers);
    }
    public void setDriver(int driver) {
        this.driver = driver;
    }
    public void setIs_active(String is_active) {
        this.is_active = is_active;
    }

}
