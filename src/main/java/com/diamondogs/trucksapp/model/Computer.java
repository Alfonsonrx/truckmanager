package com.diamondogs.trucksapp.model;

import java.util.Date;

public class Computer {
    private String serial_num;
    private Date adquisicion;
    private String tipo;
    private String software;

    public Computer() {
    }

    public Computer(String software, String tipo, Date adquisicion, String serial_num) {
        this.software = software;
        this.tipo = tipo;
        this.adquisicion = adquisicion;
        this.serial_num = serial_num;
    }

    public String getSoftware() {
        return software;
    }

    public void setSoftware(String software) {
        this.software = software;
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
}
