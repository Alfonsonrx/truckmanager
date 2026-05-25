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
}
