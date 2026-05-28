package com.diamondogs.trucksapp.repositories;

import com.diamondogs.trucksapp.config.DatabaseConfig;
import com.diamondogs.trucksapp.model.Computer;
import com.diamondogs.trucksapp.model.ComputerMaintenance;
import com.diamondogs.trucksapp.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ComputerMaintenanceRepository {

        //Trae todos los computadores y los almacena en una lista.
        public static List<ComputerMaintenance> AllComputersMaintenance() {
            List<ComputerMaintenance> computersMaintenance = new ArrayList<>();
            String query = "SELECT * FROM computerMaintenance";
            try(Connection con = DatabaseConfig.getConnection())
            {
                PreparedStatement pstmt = con.prepareStatement(query);
                ResultSet res_set = pstmt.executeQuery();

            while(res_set.next())
            {
                ComputerMaintenance maintenance = new ComputerMaintenance();
                maintenance.setId(res_set.getInt("id"));
                maintenance.setSn_computer(res_set.getString("sn_computer"));
                maintenance.setDate(res_set.getDate("date"));
                maintenance.setType(res_set.getString("type"));
                maintenance.setReasons(res_set.getString("reasons"));

                computersMaintenance.add(maintenance);

            }

            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            return computersMaintenance;
        }//Fin AllComputersMaintenance

    public static List<ComputerMaintenance> findBySerialNum(String serialNum) {
        List<ComputerMaintenance> list = new ArrayList<>();
        String query = "SELECT * FROM computerMaintenance WHERE sn_computer = ?";
        try (Connection con = DatabaseConfig.getConnection()) {
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, serialNum);
            ResultSet res_set = pstmt.executeQuery();

            while (res_set.next()) {
                ComputerMaintenance cm = new ComputerMaintenance();
                cm.setId(res_set.getInt("id"));
                cm.setSn_computer(res_set.getString("sn_computer"));
                cm.setDate(res_set.getDate("date"));
                cm.setType(res_set.getString("type"));
                cm.setReasons(res_set.getString("reasons"));

                list.add(cm);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }//Final bySerialNumber

    public static boolean save(ComputerMaintenance computerMaintenance) {
        String sql = "INSERT INTO `computermaintenance`(sn_computer, date, type, reasons) "
                + "VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConfig.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, computerMaintenance.getSn_computer());
            pstmt.setDate(2, computerMaintenance.getDate() != null ? new java.sql.Date(computerMaintenance.getDate().getTime()) : null);
            pstmt.setString(3,computerMaintenance.getType() );
            pstmt.setString(4,computerMaintenance.getReasons() );
            int rowsInserted = pstmt.executeUpdate();

            return rowsInserted > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }//final save
}
