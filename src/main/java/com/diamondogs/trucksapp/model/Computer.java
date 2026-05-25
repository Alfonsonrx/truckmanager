package com.diamondogs.trucksapp.model;

import java.util.Date;

public class Computer {
    private String serial_num;
    private Date adquisicion;
    private String tipo;
    private String ram;
    private String motherboard;
    private String cpu;
    private String storage;
    private java.sql.Date lastMaintenanceDate;
    private int currentUserId;
    private String currentUserName;

    public Computer() {
    }

    public Computer( String tipo, Date adquisicion, String serial_num) {
        this.tipo = tipo;
        this.adquisicion = adquisicion;
        this.serial_num = serial_num;
    }

    public Computer(String serialNum, java.sql.Date adquisicionDate, String type, String ram, String motherboard, String cpu, String storage, java.sql.Date lastMaintenanceDate, int currentUserId, String currentUserName) {
        this.serial_num = serialNum;
        this.adquisicion = adquisicionDate;
        this.tipo = type;
        this.ram = ram;
        this.motherboard = motherboard;
        this.cpu = cpu;
        this.storage = storage;
        this.lastMaintenanceDate = lastMaintenanceDate;
        this.currentUserId = currentUserId;
        this.currentUserName = currentUserName;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Date getAdquisicion() {
        return adquisicion;
    }

    public void setAdquisicion(Date adquisicion) {
        this.adquisicion = adquisicion;
    }

    public String getSerial_num() {
        return serial_num;
    }

    public void setSerial_num(String serial_num) {
        this.serial_num = serial_num;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getMotherboard() {
        return motherboard;
    }

    public void setMotherboard(String motherboard) {
        this.motherboard = motherboard;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getStorage() {
        return storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }

    public java.sql.Date getLastMaintenanceDate() {
        return lastMaintenanceDate;
    }

    public void setLastMaintenanceDate(java.sql.Date lastMaintenanceDate) {
        this.lastMaintenanceDate = lastMaintenanceDate;
    }

    public int getCurrentUserId() {
        return currentUserId;
    }

    public void setCurrentUserId(int currentUserId) {
        this.currentUserId = currentUserId;
    }

    public String getCurrentUserName() {
        return currentUserName;
    }

    public void setCurrentUserName(String currentUserName) {
        this.currentUserName = currentUserName;
    }

}
