package com.diamondogs.trucksapp.model;

import java.sql.Date;

public class Software {
    private int id;
    private String name;
    private String version;
    private String status;
    private Date date_installed;
    private Date last_update;

    public Software() {
    }

    public Software(int id, String sn_computer, String name, String version, String status, Date date_installed, Date last_update, String license_key) {
        this.id = id;
        this.name = name;
        this.version = version;
        this.status = status;
        this.date_installed = date_installed;
        this.last_update = last_update;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDate_installed() {
        return date_installed;
    }

    public void setDate_installed(Date date_installed) {
        this.date_installed = date_installed;
    }

    public Date getLast_updated() {
        return last_update;
    }

    public void setLast_updated(Date last_update) {
        this.last_update = last_update;
    }
}
