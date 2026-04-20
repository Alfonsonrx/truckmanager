package com.diamondogs.trucksapp.repositories;

import com.diamondogs.trucksapp.config.DatabaseConfig;
import com.diamondogs.trucksapp.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    public List<User> findALl() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT id, username, name, role, phone, is_active FROM user;";

        try (Connection conn = DatabaseConfig.getConnection()){
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet res_set = pstmt.executeQuery();

            while (res_set.next()) {
                User user = new User();
                user.setId(res_set.getInt("id"));
                user.setUsername(res_set.getString("username"));
                user.setName(res_set.getString("name"));
                user.setRole(res_set.getString("role"));
                user.setPhone(res_set.getString("phone"));
                user.setIs_active(res_set.getInt("is_active") == 1 ? "Si" : "No");

                users.add(user);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return users;
    }

    public User findOneUser(int u_id) {
        User user = new User();
        String sql = "SELECT id, username, name, phone, role FROM user WHERE id = ?;";
        try (Connection conn = DatabaseConfig.getConnection()){
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, u_id);

            ResultSet res_set = pstmt.executeQuery();

            while (res_set.next()) {
                user.setId(res_set.getInt("id"));
                user.setUsername(res_set.getString("username"));
                user.setName(res_set.getString("name"));
                user.setPhone(res_set.getString("phone"));
                user.setRole(res_set.getString("role"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return user;
    }

    public boolean save(User user) {
        String sql = "INSERT INTO `user` (username, name, phone, password, role) "
                + "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConfig.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getName());
            pstmt.setString(3, user.getPhone());
            pstmt.setString(4, user.getPassword());
            pstmt.setString(5, user.getRole());
            int rowsInserted = pstmt.executeUpdate();

            return rowsInserted > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public boolean update(User user, int u_id) {
        boolean passwordChange = user.getPassword().isEmpty();
        String sqlPw = passwordChange ? "password=?" : "";
        String sql = "UPDATE user " +
                "SET username=?, name=?, phone=?, `role`=? " + sqlPw +
                "WHERE id=?;";
        try (Connection con = DatabaseConfig.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)){

            ps.setString(1, user.getUsername());
            ps.setString(2, user.getName());
            ps.setString(3, user.getPhone());
            ps.setString(4, user.getRole());
            ps.setString(5, user.getPassword());

            ps.setInt(passwordChange ? 6 : 5, u_id);

            int rowsInserted = ps.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            System.err.println("Error SQL al actualizar: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean disable_user(int is_active,int u_id) {
        String sql = "UPDATE user " +
                "SET is_active=? " +
                "WHERE id=?;";
        try (Connection con = DatabaseConfig.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)){

            ps.setInt(1, is_active);
            ps.setInt(2, u_id);

            int resultado = ps.executeUpdate();
            return resultado > 0;

        } catch (SQLException e) {
            System.err.println("Error SQL al desabilitar: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}
