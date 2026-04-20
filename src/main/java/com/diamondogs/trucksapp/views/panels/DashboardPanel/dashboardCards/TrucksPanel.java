package com.diamondogs.trucksapp.views.panels.DashboardPanel.dashboardCards;


import com.diamondogs.trucksapp.controller.TruckController;
import com.diamondogs.trucksapp.model.Truck;
import com.diamondogs.trucksapp.views.panels.DashboardPanel.dashboardCards.dialogs.TruckEditDialog;
import com.diamondogs.trucksapp.views.panels.DashboardPanel.dashboardCards.forms.VentanaCamion;
import com.diamondogs.trucksapp.views.panels.DashboardPanel.utils.ButtonEditor;
import com.diamondogs.trucksapp.views.panels.DashboardPanel.utils.ButtonRenderer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.List;

public class TrucksPanel extends JPanel {
    private JPanel rootPanel;
    private final VentanaCamion formCamion;
    private final TruckController truckController;

    private final JTable truckTable = new JTable();
    private final String[] columnNames = {"ID", "Patente", "Conductor", "Kilometros","Color","Mantenimiento", "Acciones"};

    public TrucksPanel() {
        // --- CONEXIÓN MVC ---
        // 1. Creamos la instancia del panel de registro
        formCamion = new VentanaCamion("Registro de camiones","Ingrese los datos del camion", true);

        // 2. Creamos el controlador pasándole ESA instancia
        // (Esto hará que el botón de formCamion empiece a funcionar)
        truckController = new TruckController(formCamion, this);
        initializeComponents();
        truckController.loadAndShowTrucks();
    }

    private void initializeComponents() {
        rootPanel = new JPanel(new BorderLayout());
        rootPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JScrollPane tableScrollPane = new JScrollPane(truckTable);
        tableScrollPane.setPreferredSize(new Dimension(0, 250)); // Limit table height
        tableScrollPane.setMinimumSize(new Dimension(0, 200));
        tableScrollPane.setMaximumSize(new Dimension(0, 500));
        setupTable();

        // 2.a Agregamos el formulario (ya conectado) al panel
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(tableScrollPane, BorderLayout.CENTER);
        centerPanel.add(formCamion, BorderLayout.SOUTH);

        rootPanel.add(new JLabel("Gestión de Camiones"), BorderLayout.NORTH);
        rootPanel.add(centerPanel, BorderLayout.CENTER);
    }

    private void setupTable() {
        DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 6;
            }
        };
        truckTable.setModel(model);

        TableColumn idColumn = truckTable.getColumn("ID");
        idColumn.setMinWidth(20);
        idColumn.setMaxWidth(30);

        // Agregando Boton de editar
        truckTable.getColumn("Acciones").setCellRenderer(new ButtonRenderer("Editar"));
        truckTable.getColumn("Acciones").setCellEditor(new ButtonEditor("Editar", this::editTruck));

        truckTable.getColumn("Acciones").setMaxWidth(90);
        truckTable.getColumn("Acciones").setMinWidth(70);
        truckTable.getColumn("Acciones").setPreferredWidth(80);

        truckTable.setRowHeight(30);
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

    // Funcion ejecutada al momento de clickear editar
    private void editTruck(int row) {
        DefaultTableModel model = (DefaultTableModel) truckTable.getModel();
        int truckId = (int) model.getValueAt(row, 0);   // Get ID from first column

        // Abre el dialog para editar, se pasa el id para los procesos correspondientes
        TruckEditDialog dialog = new TruckEditDialog(this, truckId, truckController);
        dialog.loadTruckData();
        dialog.setVisible(true);
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }

}