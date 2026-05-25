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

        //Trae todos los computadores y los almacena en una lista.
        public static List<ComputerMaintenance> AllComputersMaintenance() {
            List<ComputerMaintenance> computersMaintenance = new ArrayList<>();
            String query = "SELECT * FROM computermaintenance";
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
}
