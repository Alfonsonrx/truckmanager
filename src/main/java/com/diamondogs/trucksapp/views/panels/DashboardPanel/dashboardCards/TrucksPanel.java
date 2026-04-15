package com.diamondogs.trucksapp.views.panels.DashboardPanel.dashboardCards;

import com.diamondogs.trucksapp.controller.TruckController;
import com.diamondogs.trucksapp.model.Truck;
import com.diamondogs.trucksapp.views.panels.DashboardPanel.dashboardCards.dialogs.TruckEditDialog;
import com.diamondogs.trucksapp.views.panels.DashboardPanel.dashboardCards.forms.VentanaCamion;
import com.diamondogs.trucksapp.views.panels.DashboardPanel.utils.ButtonEditor;
import com.diamondogs.trucksapp.views.panels.DashboardPanel.utils.ButtonRenderer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class TrucksPanel extends JPanel {
    private JPanel rootPanel;
    private final JTable truckTable = new JTable();
    private final String[] columnNames = {"ID", "Patente", "Conductor", "Kilometros","Color","Mantenimiento", "Acciones"};

    private JPanel panelBotones;
    private JButton btnGuardar;

    public TrucksPanel() {
        initializeComponents();
    }

    private void initializeComponents() {
        rootPanel = new JPanel(new BorderLayout());
        rootPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JScrollPane tableScrollPane = new JScrollPane(truckTable);
        setupTable();

        // --- CONEXIÓN MVC ---
        // 1. Creamos la instancia del panel de registro
        VentanaCamion formCamion = new VentanaCamion("Registro de camiones","Ingrese los datos del camion");

        // 2. Creamos el controlador pasándole ESA instancia
        // (Esto hará que el botón de formCamion empiece a funcionar)
        TruckController truckController = new TruckController(formCamion, this);
        truckController.loadAndShowTrucks();

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(tableScrollPane, BorderLayout.CENTER);

        // 3. Agregamos el formulario (ya conectado) al panel
        centerPanel.add(formCamion, BorderLayout.SOUTH);

        btnGuardar = new JButton("Guardar");

        panelBotones = new JPanel();
        panelBotones.add(btnGuardar);

        rootPanel.add(new JLabel("Gestión de Camiones"), BorderLayout.NORTH);
        rootPanel.add(centerPanel, BorderLayout.CENTER);
        rootPanel.add(panelBotones, BorderLayout.SOUTH);
    }

    private void setupTable() {
        DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 6;
            }
        };
        truckTable.setModel(model);

        // Agregando Boton de editar
        truckTable.getColumn("Acciones").setCellRenderer(new ButtonRenderer("Editar"));
        truckTable.getColumn("Acciones").setCellEditor(new ButtonEditor("Editar", this::editTruck));

        truckTable.getColumn("Acciones").setMaxWidth(80);
    }
    private void editTruck(int row) {
        DefaultTableModel model = (DefaultTableModel) truckTable.getModel();
        int truckId = (int) model.getValueAt(row, 0);   // Get ID from first column

        // Abre el dialog para editar, se pasa el id para los procesos correspondientes
        new TruckEditDialog(this, truckId).setVisible(true);
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }

    public void updateTable(List<Truck> trucks) {
        if (trucks == null) return;

        DefaultTableModel model = (DefaultTableModel) truckTable.getModel();
        model.setRowCount(0);

        for (Truck truck : trucks) {
            model.addRow(new Object[]{
                    truck.getId(),
                    truck.getPlate(),
                    truck.getDriver(),
                    truck.getKilometers(),
                    truck.getColor(),
                    truck.getLatest_maintenance()
            });
        }
    }

    public JButton getBtnGuardar() {
        return btnGuardar;
    }
}