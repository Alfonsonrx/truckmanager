package com.diamondogs.trucksapp.repositories;

import com.diamondogs.trucksapp.config.DatabaseConfig;
import com.diamondogs.trucksapp.model.Software;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SoftwareRepository {

    public static List<Software> findBySerialNum(String serialNum) {
        List<Software> list = new ArrayList<>();
        String query = "SELECT " +
                        "sci.id, " +
                        "sc.name, " +
                        "sci.version, " +
                        "sci.status, " +
                        "sci.date_installed, " +
                        "sci.last_updated " +
                        "FROM software_installation sci " +
                        "INNER JOIN software_catalog sc " +
                        "ON sci.software_id = sc.id " +
                        "WHERE sci.serial_num = ? " +
                        "ORDER BY sc.name;";
        try (Connection con = DatabaseConfig.getConnection()) {
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, serialNum);
            ResultSet res_set = pstmt.executeQuery();

            while (res_set.next()) {
                Software s = new Software();
                s.setId(res_set.getInt("id"));
                s.setName(res_set.getString("name"));
                s.setVersion(res_set.getString("version"));
                s.setStatus(res_set.getString("status"));
                s.setDate_installed(res_set.getDate("date_installed"));
                s.setLast_updated(res_set.getDate("last_updated"));

                list.add(s);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}
