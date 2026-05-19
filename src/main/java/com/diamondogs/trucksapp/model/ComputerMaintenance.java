package com.diamondogs.trucksapp.model;

import java.util.Date;

public class ComputerMaintenance {
    private int id;
    private String sn_computer;
    private Date date;
    private String type;
    private String reasons;

    public ComputerMaintenance() {
    }

    public ComputerMaintenance(int id, String sn_computer, Date date, String type, String reasons) {
        this.id = id;
        this.sn_computer = sn_computer;
        this.date = date;
        this.type = type;
        this.reasons = reasons;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSn_computer() {
        return sn_computer;
    }

    public void setSn_computer(String sn_computer) {
        this.sn_computer = sn_computer;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getReasons() {
        return reasons;
    }

    public void setReasons(String reasons) {
        this.reasons = reasons;
    }
}
