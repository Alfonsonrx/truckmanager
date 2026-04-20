package com.diamondogs.trucksapp.repositories;

import com.diamondogs.trucksapp.config.DatabaseConfig;
import com.diamondogs.trucksapp.model.Truck;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TruckRepository {

    public static List<Truck> findALl() {
        List<Truck> trucks = new ArrayList<Truck>();
        String sql = "SELECT id, plate, brand, model, color, year, latest_maintenance, kilometers, driver, is_active FROM truck;";
        try (Connection conn = DatabaseConfig.getConnection()){
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet res_set = pstmt.executeQuery();

            while (res_set.next()) {
                Truck truck= new Truck();
                truck.setId(res_set.getInt("id"));
                truck.setPlate(res_set.getString("plate"));
                truck.setBrand(res_set.getString("brand"));
                truck.setModel(res_set.getString("model"));
                truck.setColor(res_set.getString("color"));
                truck.setYear(res_set.getInt("year"));
                truck.setLatest_maintenance(res_set.getDate("latest_maintenance"));
                truck.setKilometers(res_set.getInt("kilometers"));
                truck.setDriver(res_set.getInt("driver"));
                truck.setIs_active(res_set.getInt("is_active") == 1 ? "Si" : "No");
                trucks.add(truck);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return trucks;
    }

    public Truck findOneTruck(int t_id) {
        Truck truck = new Truck();
        String sql = "SELECT id, plate, brand, model, color, year, latest_maintenance, kilometers, driver FROM truck WHERE id = ?;";
        try (Connection conn = DatabaseConfig.getConnection()){
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, t_id);

            ResultSet res_set = pstmt.executeQuery();

            while (res_set.next()) {
                truck.setId(res_set.getInt("id"));
                truck.setPlate(res_set.getString("plate"));
                truck.setBrand(res_set.getString("brand"));
                truck.setModel(res_set.getString("model"));
                truck.setColor(res_set.getString("color"));
                truck.setYear(res_set.getInt("year"));
                truck.setLatest_maintenance(res_set.getDate("latest_maintenance"));
                truck.setKilometers(res_set.getInt("kilometers"));
                truck.setDriver(res_set.getInt("driver"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return truck;
    }

    public boolean save(Truck t) {
        // Asegúrate de que los nombres coincidan con tu phpMyAdmin
        String sql = "INSERT INTO truck (plate, brand, model, color, year, latest_maintenance, kilometers, driver) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = DatabaseConfig.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, t.getPlate());
            ps.setString(2, t.getBrand());
            ps.setString(3, t.getModel());
            ps.setString(4, t.getColor());
            ps.setInt(5, t.getYear());
            ps.setDate(6, t.getLatest_maintenance());
            ps.setInt(7, t.getKilometers());
            ps.setInt(8, t.getDriver()); // ID del conductor

            int rowsInserted = ps.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            System.err.println("Error SQL al guardar: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(int t_id) {
        String sql = "CALL eliminar_camion(?);";
        try (Connection con = DatabaseConfig.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)){

            ps.setInt(1, t_id);

            int rowsInserted = ps.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            System.err.println("Error SQL al eliminar: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean update(Truck t, int t_id) {
        String sql = "UPDATE truck " +
                "SET plate=?, brand=?, model=?, color=?, `year`=?, latest_maintenance=?, driver=?, kilometers=? " +
                "WHERE id=?;";
        try (Connection con = DatabaseConfig.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)){

            ps.setString(1, t.getPlate());
            ps.setString(2, t.getBrand());
            ps.setString(3, t.getModel());
            ps.setString(4, t.getColor());
            ps.setInt(5, t.getYear());
            ps.setDate(6, t.getLatest_maintenance());
            ps.setInt(7, t.getDriver()); // ID del conductor
            ps.setInt(8, t.getKilometers());

            ps.setInt(9, t_id);

            int rowsInserted = ps.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            System.err.println("Error SQL al actualizar: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean disable_truck(int is_active,int t_id) {
        String sql = "UPDATE truck " +
                "SET is_active=? " +
                "WHERE id=?;";
        try (Connection con = DatabaseConfig.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)){

            ps.setInt(1, is_active);
            ps.setInt(2, t_id);

            int rowsInserted = ps.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            System.err.println("Error SQL al desabilitar: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}