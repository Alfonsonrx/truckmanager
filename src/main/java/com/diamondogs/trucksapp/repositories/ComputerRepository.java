package com.diamondogs.trucksapp.repositories;

import com.diamondogs.trucksapp.config.DatabaseConfig;
import com.diamondogs.trucksapp.model.Computer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ComputerRepository {
    //Trae todos los computadores y los almacena en una lista.
    public static List<Computer> AllComputers() {
        List<Computer> computers = new ArrayList<>();
        String query = "SELECT * FROM computer";
        try(Connection con = DatabaseConfig.getConnection())
        {
            PreparedStatement pstmt = con.prepareStatement(query);
            ResultSet res_set = pstmt.executeQuery();

            while(res_set.next())
            {
                Computer comp = new Computer();
                comp.setSerial_num(res_set.getString("serial_num"));
                comp.setAdquisicion(res_set.getDate("adquisition_date"));
                comp.setTipo(res_set.getString("type"));

                computers.add(comp);

            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return computers;
    }//Fin AllConuters

    //Inserta valores
    public static boolean insertComputer(Computer comp) {
        String queryComputer = "INSERT INTO computer (serial_num, adquisition_date, type) VALUES (?, ?, ?)";
        String queryHardware = "INSERT INTO hardware_detail (sn_computer, ram, motherboard, cpu, storage) VALUES (?, ?, ?, ?, ?)";
        
        Connection con = null;
        try {
            con = DatabaseConfig.getConnection();
            con.setAutoCommit(false); // Start transaction
            
            // 1. Insert into computer table
            try (PreparedStatement pstmt = con.prepareStatement(queryComputer)) {
                pstmt.setString(1, comp.getSerial_num());
                pstmt.setDate(2, comp.getAdquisicion() != null ? new java.sql.Date(comp.getAdquisicion().getTime()) : null);
                pstmt.setString(3, comp.getTipo());
                pstmt.executeUpdate();
            }
            
            // 2. Insert into hardware_detail table
            try (PreparedStatement pstmt2 = con.prepareStatement(queryHardware)) {
                pstmt2.setString(1, comp.getSerial_num());
                pstmt2.setString(2, comp.getRam());
                pstmt2.setString(3, comp.getMotherboard());
                pstmt2.setString(4, comp.getCpu());
                pstmt2.setString(5, comp.getStorage());
                pstmt2.executeUpdate();
            }
            
            con.commit(); // Commit transaction
            return true;
        } catch (Exception e) {
            if (con != null) {
                try {
                    con.rollback(); // Rollback on error
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
            return false;
        } finally {
            if (con != null) {
                try {
                    con.setAutoCommit(true);
                    con.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }//Fin de insertar
}
