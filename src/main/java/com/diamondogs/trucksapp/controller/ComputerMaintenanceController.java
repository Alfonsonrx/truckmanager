package com.diamondogs.trucksapp.controller;

import com.diamondogs.trucksapp.model.ComputerMaintenance;
import com.diamondogs.trucksapp.repositories.ComputerMaintenanceRepository;
import com.diamondogs.trucksapp.views.panels.DashboardPanel.dashboardCards.ComputerDetailFrame;
import com.diamondogs.trucksapp.views.panels.DashboardPanel.dashboardCards.subpanels.ComputerMaintenancePanel;

import javax.swing.*;
import java.util.List;

public class ComputerMaintenanceController {
    private ComputerDetailFrame vistaFrame;
    private ComputerMaintenancePanel vistaPanel;
    private final String serialNum;

    public ComputerMaintenanceController(ComputerDetailFrame vista, String serialNum) {
        this.vistaFrame = vista;
        this.serialNum = serialNum;
    }

    public ComputerMaintenanceController(ComputerMaintenancePanel vista) {
        this.vistaPanel = vista;
        this.serialNum = null;
    }

    public void loadAndShowMaintenances() {
        SwingWorker<List<ComputerMaintenance>, Void> worker = new SwingWorker<>() {
            @Override
            protected List<ComputerMaintenance> doInBackground() {
                if (serialNum != null) {
                    return ComputerMaintenanceRepository.findBySerialNum(serialNum);
                } else {
                    return ComputerMaintenanceRepository.generalMaintenances();
                }
            }

            @Override
            protected void done() {
                try {
                    List<ComputerMaintenance> maintenances = get();
                    if (vistaFrame != null) {
                        vistaFrame.updateMaintenanceTable(maintenances);
                    } else if (vistaPanel != null) {
                        vistaPanel.updateTable(maintenances);
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(
                        vistaFrame != null ? vistaFrame : vistaPanel,
                        "Error al cargar mantenimientos."
                    );
                    e.printStackTrace();
                }
            }
        };
        worker.execute();
    }
}
