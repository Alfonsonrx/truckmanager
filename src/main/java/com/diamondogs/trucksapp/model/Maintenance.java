package com.diamondogs.trucksapp.model;

import java.sql.Date;

public class Maintenance {
    private int id;
    private int truck; // Relación con el camión al que se le hace el servicio
    private Date date;
    private String maintenanceType;
    private String description;

    public Maintenance() {}

    public Maintenance(int id, int truck, Date date, String maintenanceType, String description) {
        this.id = id;
        this.truck = truck;
        this.date = date;
        this.maintenanceType = maintenanceType;
        this.description = description;
    }

    public int getId() {
        return id;
    }
    public int getTruck() {
        return truck;
    }
    public Date getDate() {
        return date;
    }
    public String getMaintenanceType() {
        return maintenanceType;
    }
    public String getDescription() {
        return description;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setTruck(int truck) {
        this.truck = truck;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public void setMaintenanceType(String maintenanceType) {
        this.maintenanceType = maintenanceType;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}
