package com.diamondogs.trucksapp.repositories;

import com.diamondogs.trucksapp.config.DatabaseConfig;
import com.diamondogs.trucksapp.model.Computer;
import com.diamondogs.trucksapp.model.ComputerMaintenance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ComputerMaintenanceRepository {

    public static List<ComputerMaintenance> generalMaintenances() {
        List<ComputerMaintenance> computersMaintenances = new ArrayList<>();
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

                computersMaintenances.add(maintenance);

            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return computersMaintenances;
    }

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
    }

    public static int save(ComputerMaintenance cm) {
        String query = "INSERT INTO computerMaintenance (sn_computer, date, type, reasons) VALUES (?, ?, ?, ?)";
        try (Connection con = DatabaseConfig.getConnection()) {
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, cm.getSn_computer());
            pstmt.setDate(2, new java.sql.Date(cm.getDate().getTime()));
            pstmt.setString(3, cm.getType());
            pstmt.setString(4, cm.getReasons());

            return pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static int delete(int id) {
        String query = "DELETE FROM computerMaintenance WHERE id = ?";
        try (Connection con = DatabaseConfig.getConnection()) {
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, id);

            return pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
