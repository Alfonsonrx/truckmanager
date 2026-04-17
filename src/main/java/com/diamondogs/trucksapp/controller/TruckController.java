package com.diamondogs.trucksapp.controller;

import com.diamondogs.trucksapp.model.Truck;
import com.diamondogs.trucksapp.repositories.TruckRepository;
import com.diamondogs.trucksapp.views.panels.DashboardPanel.dashboardCards.TrucksPanel;
import com.diamondogs.trucksapp.views.panels.DashboardPanel.dashboardCards.forms.VentanaCamion;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class TruckController implements ActionListener {

    private VentanaCamion vistaCamion;
    private TruckRepository repositorio;
    private TrucksPanel vista;

    // Constructor que RECIBE la vista y activa el botón
    public TruckController(VentanaCamion vistaCamion, TrucksPanel vista) {
        this.vistaCamion = vistaCamion;
        this.repositorio = new TruckRepository();
        this.vista = vista;


        if (this.vista.getBtnGuardar() != null) {
            this.vista.getBtnGuardar().addActionListener(this);
            System.out.println("Controlador: Botón de guardado vinculado correctamente.");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.getBtnGuardar()) {
            procesarGuardadoCamion();
        }
    }

    private void procesarGuardadoCamion() {
        try {
            // Extraer datos de la vista
            String patente = vistaCamion.getPatente();
            String marca = vistaCamion.getMarca();
            String modelo = vistaCamion.getModelo();
            String color = vistaCamion.getColor();
            String fechaStr = vistaCamion.getLatest_maintenance();
            int anio = Integer.parseInt(vistaCamion.getAnio());
            int kms = Integer.parseInt(vistaCamion.getKilometraje());
            int idCond = Integer.parseInt(vistaCamion.getConductor());

            // Conversión de fecha segura
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.sql.Date fechaSQL = null;
            if (fechaStr != null && !fechaStr.trim().isEmpty()) {
                Date parsed = sdf.parse(fechaStr);
                fechaSQL = new java.sql.Date(parsed.getTime());
            }

            // Crear objeto Modelo
            Truck nuevo = new Truck(0, patente, marca, modelo, color, anio, fechaSQL, kms, idCond);

            // Guardar
            boolean exito = repositorio.save(nuevo);

            if (exito) {
                JOptionPane.showMessageDialog(vistaCamion, "¡Guardado en MySQL!");
                loadAndShowTrucks(); // Refresh the table
            } else {
                JOptionPane.showMessageDialog(vistaCamion, "Error al guardar. Revisa la consola.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vistaCamion, "Datos inválidos: " + ex.getMessage());
        }
    }
    public void ExcesoKilometraje(int kilometraje){

        if (kilometraje > 5000) {
            JOptionPane.showMessageDialog(
                    vistaCamion, 
                    "El camión tiene " + kilometraje + " km. Necesita ser llevado a mantenimiento urgente.",
                    "Alerta de Mantenimiento",
                    JOptionPane.WARNING_MESSAGE // Esto le pone un ícono amarillo de advertencia a la ventana
            );
        }

    }
    public void loadAndShowTrucks() {
        SwingWorker<List<Truck>, Void> worker = new SwingWorker<>() {
            @Override
            protected List<Truck> doInBackground() {
                return TruckRepository.findALl();
            }

            @Override
            protected void done() {
                try {
                    List<Truck> trucks = get();
                    vista.updateTable(trucks);
                    ExcesoKilometraje(trucks.getLast().getKilometers());
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Failed to load trucks!");
                }
            }
        };
        worker.execute();
    }

    public void loadOneTruck(int id){
        Truck truck = new Truck();
    }
    }



