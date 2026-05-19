package com.diamondogs.trucksapp.controller;

import com.diamondogs.trucksapp.model.Computer;
import com.diamondogs.trucksapp.model.ComputerMaintenance;
import com.diamondogs.trucksapp.repositories.ComputerMaintenanceRepository;
import com.diamondogs.trucksapp.repositories.ComputerRepository;
import com.diamondogs.trucksapp.views.panels.DashboardPanel.dashboardCards.ComputerMaintenancePanel;
import com.diamondogs.trucksapp.views.panels.DashboardPanel.dashboardCards.ComputersPanel;

import javax.swing.*;
import java.util.List;

public class ComputerMaintenanceController {
    private final ComputerMaintenancePanel vista;

    public ComputerMaintenanceController(ComputerMaintenancePanel vista) {
        this.vista = vista;
    }
    //Carga y muestra los computadores
    public void loadAndShowComputersMaintenance() {
        SwingWorker<List<ComputerMaintenance>,Void> worker = new SwingWorker<>() {
            @Override
            protected List<ComputerMaintenance> doInBackground() {
                return ComputerMaintenanceRepository.AllComputersMaintenance();
            }
            @Override
            protected void done() {
                try {
                    List<ComputerMaintenance> computers = get();
                    vista.updateTable(computers);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Error al cargar la lista de computadores.");
                    e.printStackTrace();
                }
            }
        };
        worker.execute();

    }//Fin de loadAndShowComputers
}
