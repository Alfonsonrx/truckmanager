package com.diamondogs.trucksapp.repositories;

import com.diamondogs.trucksapp.config.DatabaseConfig;
import com.diamondogs.trucksapp.model.ComputerAssignment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ComputerAssignmentRepository {

    public static List<ComputerAssignment> findBySerialNum(String serialNum) {
        List<ComputerAssignment> list = new ArrayList<>();
        String query = "SELECT uc.sn_computer, uc.id_user, u.name, uc.start_date, uc.end_date " +
                       "FROM user_computer uc " +
                       "JOIN user u ON uc.id_user = u.id " +
                       "WHERE uc.sn_computer = ?";
        try (Connection con = DatabaseConfig.getConnection()) {
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, serialNum);
            ResultSet res_set = pstmt.executeQuery();

            while (res_set.next()) {
                ComputerAssignment uc = new ComputerAssignment();
                uc.setSn_computer(res_set.getString("sn_computer"));
                uc.setUser_id(res_set.getInt("id_user"));
                uc.setUser_name(res_set.getString("name"));
                uc.setAssigned_date(res_set.getDate("start_date"));
                uc.setUnassigned_date(res_set.getDate("end_date"));

                list.add(uc);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}
