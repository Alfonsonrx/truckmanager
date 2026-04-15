package com.diamondogs.trucksapp.repositories;

import com.diamondogs.trucksapp.config.DatabaseConfig;
import com.diamondogs.trucksapp.model.Maintenance;
import com.diamondogs.trucksapp.model.Truck;
import com.diamondogs.trucksapp.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MaintenanceRepository {
    public List<Maintenance> findALl() {
        List<Maintenance> maintenances = new ArrayList<>();
        String sql = "SELECT id, truck, date, maintenanceType, description FROM maintenance;";

        try (Connection conn = DatabaseConfig.getConnection()){
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet res_set = pstmt.executeQuery();

            while (res_set.next()) {
                Maintenance maintenance = new Maintenance();
                maintenance.setId(res_set.getInt("id"));
                maintenance.setTruck(res_set.getInt("truck"));
                maintenance.setDate(res_set.getDate("date"));
                maintenance.setMaintenanceType(res_set.getString("maintenanceType"));
                maintenance.setDescription(res_set.getString("description"));
                maintenances.add(maintenance);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return maintenances;
    }

    public Maintenance findOneMaintenance(int m_id) {
        Maintenance maintenance = new Maintenance();
        String sql = "SELECT id, truck, date, maintenanceType, description FROM maintenance WHERE id = ?;";
        try (Connection conn = DatabaseConfig.getConnection()){
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, m_id);

            ResultSet res_set = pstmt.executeQuery();

            while (res_set.next()) {
                maintenance.setId(res_set.getInt("id"));
                maintenance.setTruck(res_set.getInt("truck"));
                maintenance.setDate(res_set.getDate("date"));
                maintenance.setMaintenanceType(res_set.getString("maintenanceType"));
                maintenance.setDescription(res_set.getString("description"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return maintenance;
    }

    public boolean save(Maintenance m) {
        // Asegúrate de que los nombres coincidan con tu phpMyAdmin
        String sql = "INSERT INTO maintenance (truck, date, maintenanceType, description) " +
                "VALUES (?, ?, ?, ?)";

        try (Connection con = DatabaseConfig.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, m.getTruck());
            ps.setDate(2, m.getDate());
            ps.setString(3, m.getMaintenanceType());
            ps.setString(4, m.getDescription());

            int rowsInserted = ps.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            System.err.println("Error SQL al guardar: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(int m_id) {
        String sql = "DELETE FROM maintenance AS m WHERE m.id = ?;";
        try (Connection con = DatabaseConfig.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)){

            ps.setInt(1, m_id);

            int rowsInserted = ps.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            System.err.println("Error SQL al eliminar: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean update(Maintenance m, int m_id) {
        String sql = "UPDATE maintenance " +
                "SET truck=?, date=?, maintenanceType=?, description=?" +
                "WHERE id=?;";
        try (Connection con = DatabaseConfig.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)){

            ps.setInt(1, m.getTruck());
            ps.setDate(2, m.getDate());
            ps.setString(3, m.getMaintenanceType()); // ID del conductor
            ps.setString(4, m.getDescription());

            ps.setInt(5, m_id);

            int rowsInserted = ps.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            System.err.println("Error SQL al actualizar: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}
