package com.diamondogs.trucksapp.model;

import java.sql.Date;

public class ComputerAssignment {
    private String sn_computer;
    private int user_id;
    private String user_name;
    private Date assigned_date;
    private Date unassigned_date;

    public ComputerAssignment() {
    }

    public ComputerAssignment(String sn_computer, int user_id, String user_name, Date assigned_date, Date unassigned_date) {
        this.sn_computer = sn_computer;
        this.user_id = user_id;
        this.user_name = user_name;
        this.assigned_date = assigned_date;
        this.unassigned_date = unassigned_date;
    }
    public String getSn_computer() {
        return sn_computer;
    }

    public void setSn_computer(String sn_computer) {
        this.sn_computer = sn_computer;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public Date getAssigned_date() {
        return assigned_date;
    }

    public void setAssigned_date(Date assigned_date) {
        this.assigned_date = assigned_date;
    }

    public Date getUnassigned_date() {
        return unassigned_date;
    }

    public void setUnassigned_date(Date unassigned_date) {
        this.unassigned_date = unassigned_date;
    }
}
