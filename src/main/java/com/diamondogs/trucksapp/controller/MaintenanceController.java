package com.diamondogs.trucksapp.controller;


import com.diamondogs.trucksapp.model.Maintenance;
import com.diamondogs.trucksapp.repositories.MaintenanceRepository;
import com.diamondogs.trucksapp.views.panels.DashboardPanel.dashboardCards.MaintenancePanel;
import com.diamondogs.trucksapp.views.panels.DashboardPanel.dashboardCards.forms.FormMantenimiento;
import com.diamondogs.trucksapp.views.panels.DashboardPanel.dashboardCards.forms.VentanaCamion;

import javax.swing.*;
import java.util.List;

public class MaintenanceController {

    private FormMantenimiento formMantenimiento;
    private final MaintenanceRepository repository = new MaintenanceRepository();
    private final MaintenancePanel vista; // Vista

    public MaintenanceController(FormMantenimiento formMantenimiento, MaintenancePanel vista) {
        this.formMantenimiento = formMantenimiento;
        this.vista = vista;
    }

    public void loadAndShowMaintenances() {
        SwingWorker<List<Maintenance>, Void> worker = new SwingWorker<>() {
            @Override
            protected List<Maintenance> doInBackground() {
                return repository.findALl();
            }

            @Override
            protected void done() {
                try {
                    List<Maintenance> maintenances = get();
                    vista.updateTable(maintenances);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Failed to load maintenances");
                }
            }
        };
        worker.execute();
    }
}
