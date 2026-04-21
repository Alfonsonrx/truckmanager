package com.diamondogs.trucksapp.controller;


import com.diamondogs.trucksapp.model.Maintenance;
import com.diamondogs.trucksapp.repositories.MaintenanceRepository;
import com.diamondogs.trucksapp.views.panels.DashboardPanel.dashboardCards.MaintenancePanel;
import com.diamondogs.trucksapp.views.panels.DashboardPanel.dashboardCards.forms.FormMantenimiento;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MaintenanceController implements ActionListener {

    private FormMantenimiento formMantenimiento;
    private final MaintenanceRepository repository = new MaintenanceRepository();
    private final MaintenancePanel vista; // Vista

    public MaintenanceController(FormMantenimiento formMantenimiento, MaintenancePanel vista) {
        this.formMantenimiento = formMantenimiento;
        this.vista = vista;

        if (this.formMantenimiento.getBtnGuardar() != null) {
            this.formMantenimiento.getBtnGuardar().addActionListener( this);
            System.out.println("Controlador: Botón de guardado vinculado correctamente.");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == formMantenimiento.getBtnGuardar()) {
            procesarGuardadoCamion();
        }
    }

    private void procesarGuardadoCamion() {
        try {
            // Extraer datos de la vista
            int idTruck = Integer.parseInt(formMantenimiento.getTruck());
            String fechaStr = formMantenimiento.getDate();
            String tipo = formMantenimiento.getType();
            String descripcion = formMantenimiento.getDesc();

            // Conversión de fecha segura
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.sql.Date fechaSQL = null;
            if (fechaStr != null && !fechaStr.trim().isEmpty()) {
                Date parsed = sdf.parse(fechaStr);
                fechaSQL = new java.sql.Date(parsed.getTime());
            }

            // Crear objeto Modelo
            Maintenance nuevo = new Maintenance(0, idTruck, fechaSQL, tipo, descripcion);

            // Guardar
            boolean exito = repository.save(nuevo);

            if (exito) {
                JOptionPane.showMessageDialog(formMantenimiento, "¡Guardado en MySQL!");
                loadAndShowMaintenances(); // Refresh the table
                formMantenimiento.clearForm();
            } else {
                JOptionPane.showMessageDialog(formMantenimiento, "Error al guardar. Revisa la consola.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(formMantenimiento, "Datos inválidos: " + ex.getMessage());
        }
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

    public void loadMaintenanceForEdit(int maintenanceId, FormMantenimiento form) {
        Maintenance maintenance = repository.findOneMaintenance(maintenanceId);
        if (maintenance != null) {
            form.fillForm(maintenance);
        }
    }

    public void makeMaintenanceUpdate(int maintenanceId, FormMantenimiento form) {
        try {
            int idTruck = Integer.parseInt(form.getTruck());
            String fechaStr = form.getDate();
            String tipo = form.getType();
            String descripcion = form.getDesc();

            // Conversión de fecha segura
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.sql.Date fechaSQL = null;
            if (fechaStr != null && !fechaStr.trim().isEmpty()) {
                Date parsed = sdf.parse(fechaStr);
                fechaSQL = new java.sql.Date(parsed.getTime());
            }
            Maintenance editado = new Maintenance(maintenanceId, idTruck, fechaSQL, tipo, descripcion);

            // Guardar
            boolean exito = repository.update(editado, maintenanceId);

            if (exito) {
                JOptionPane.showMessageDialog(formMantenimiento, String.format("¡Guardado la version nueva del mantenimiento Nro. %d en MySQL!",maintenanceId));
                loadAndShowMaintenances(); // Refresh the table
            } else {
                JOptionPane.showMessageDialog(formMantenimiento, "Error al guardar. Revisa la consola.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(formMantenimiento, "Error: " + ex.getMessage());
        }
    }

    public void processDeleteMaintenance(int maintenanceId) {
        try {
            // Eliminar
            boolean exito = repository.delete(maintenanceId);

            if (exito) {
                JOptionPane.showMessageDialog(vista, String.format("¡Eliminado mantenimiento Nro. %d en MySQL!",maintenanceId));
                loadAndShowMaintenances(); // Refresh the table
            } else {
                JOptionPane.showMessageDialog(vista, "Error al eliminar. Revisa la consola.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, "Error: " + ex.getMessage());
        }
    }
}
