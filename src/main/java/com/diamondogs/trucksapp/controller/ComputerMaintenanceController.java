package com.diamondogs.trucksapp.controller;

import com.diamondogs.trucksapp.model.Computer;
import com.diamondogs.trucksapp.model.ComputerMaintenance;
import com.diamondogs.trucksapp.repositories.ComputerMaintenanceRepository;
import com.diamondogs.trucksapp.repositories.ComputerRepository;
import com.diamondogs.trucksapp.views.panels.DashboardPanel.dashboardCards.ComputerDetailFrame;
import com.diamondogs.trucksapp.views.panels.DashboardPanel.dashboardCards.forms.FormComputerMaintenance;
import com.diamondogs.trucksapp.views.panels.DashboardPanel.dashboardCards.subpanels.ComputerMaintenancePanel;

import javax.swing.*;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ComputerMaintenanceController implements ActionListener {
    private ComputerDetailFrame vistaFrame;
    private ComputerMaintenancePanel vistaPanel;
    private final String serialNum;
    private FormComputerMaintenance formComputerMaintenance;

    public ComputerMaintenanceController(ComputerDetailFrame vista, String serialNum,FormComputerMaintenance formComputerMaintenance) {
        this.formComputerMaintenance = formComputerMaintenance;
        this.vistaFrame = vista;
        this.serialNum = serialNum;

        if(formComputerMaintenance != null && formComputerMaintenance.getBtnGuardar() != null) {
            this.formComputerMaintenance.getBtnGuardar().addActionListener(this);
            System.out.println("Controlador: Botón de guardado vinculado correctamente.");
        }
    }

    public ComputerMaintenanceController(ComputerDetailFrame vista, String serialNum) {
        this.vistaFrame = vista;
        this.serialNum = serialNum;
        this.formComputerMaintenance = null;
    }

    public ComputerMaintenanceController(FormComputerMaintenance formComputerMaintenance, ComputerMaintenancePanel vista) {
        this.formComputerMaintenance = formComputerMaintenance;
        this.vistaPanel = vista;
        this.serialNum = null;

        if(formComputerMaintenance.getBtnGuardar() != null) {
            this.formComputerMaintenance.getBtnGuardar().addActionListener(this);
            System.out.println("Controlador: Botón de guardado vinculado correctamente.");
        }
    }

    public ComputerMaintenanceController(ComputerMaintenancePanel vista) {
        this.vistaPanel = vista;
        this.serialNum = null;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == formComputerMaintenance.getBtnGuardar()) {
            procesarGuardadoMantencion();
        }
    }

    private void procesarGuardadoMantencion() {
        try {
            ComputerMaintenance comp = new ComputerMaintenance();
            comp.setSn_computer(formComputerMaintenance.getSerialNum());
            
            String dateStr = formComputerMaintenance.getDate();
            if (dateStr != null && !dateStr.trim().isEmpty()) {
                java.text.SimpleDateFormat format = new java.text.SimpleDateFormat("yyyy-MM-dd");
                java.util.Date parsedDate = format.parse(dateStr);
                comp.setDate(parsedDate);
            }

            comp.setType(formComputerMaintenance.getType());
            comp.setReasons(formComputerMaintenance.getReasons());

            boolean success = ComputerMaintenanceRepository.save(comp);

            if (success) {
                JOptionPane.showMessageDialog(null, "Mantenimiento guardado exitosamente.");
                formComputerMaintenance.clearForm();
                loadAndShowComputersMaintenance(); // Refresh table
            } else {
                JOptionPane.showMessageDialog(null, "Error al guardar en la base de datos.");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error validando datos: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void loadAndShowComputersMaintenance() {
        SwingWorker<List<ComputerMaintenance>, Void> worker = new SwingWorker<>() {
            @Override
            protected List<ComputerMaintenance> doInBackground() {
                if (serialNum != null) {
                    return ComputerMaintenanceRepository.findBySerialNum(serialNum);
                } else {
                    return ComputerMaintenanceRepository.AllComputersMaintenance();
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
