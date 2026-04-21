package com.diamondogs.trucksapp.views.panels.DashboardPanel.dashboardCards;


import com.diamondogs.trucksapp.controller.TruckController;
import com.diamondogs.trucksapp.model.Truck;
import com.diamondogs.trucksapp.views.panels.DashboardPanel.dashboardCards.dialogs.TruckEditDialog;
import com.diamondogs.trucksapp.views.panels.DashboardPanel.dashboardCards.forms.VentanaCamion;
import com.diamondogs.trucksapp.views.panels.DashboardPanel.utils.ButtonEditor;
import com.diamondogs.trucksapp.views.panels.DashboardPanel.utils.ButtonRenderer;
import com.diamondogs.trucksapp.views.panels.DashboardPanel.utils.DynamicStateButtonEditor;
import com.diamondogs.trucksapp.views.panels.DashboardPanel.utils.DynamicStateButtonRenderer;

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
    private final String[] columnNames = {"ID", "Patente", "Conductor", "Kilometros","Color","Mantenimiento", "Habilitado?", "Editar", "Estado"};

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
                return column == 7 || column == 8;
            }
        };
        truckTable.setModel(model);

        TableColumn idColumn = truckTable.getColumn("ID");
        idColumn.setMinWidth(20);
        idColumn.setMaxWidth(30);

        // Agregando Boton de editar
        truckTable.getColumn("Editar").setCellRenderer(new ButtonRenderer("Editar"));
        truckTable.getColumn("Editar").setCellEditor(new ButtonEditor("Editar", this::editTruck));

        truckTable.getColumn("Editar").setMaxWidth(90);
        truckTable.getColumn("Editar").setMinWidth(70);
        truckTable.getColumn("Editar").setPreferredWidth(80);

        truckTable.getColumn("Estado").setCellRenderer(new DynamicStateButtonRenderer("Habilitado?"));
        truckTable.getColumn("Estado").setCellEditor(new DynamicStateButtonEditor("Habilitado?", this::toggleTruckState));
        truckTable.getColumn("Estado").setMaxWidth(100);
        truckTable.getColumn("Estado").setMinWidth(80);
        truckTable.getColumn("Estado").setPreferredWidth(100);

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
                    truck.getLatest_maintenance(),
                    truck.getIs_active() != null ? truck.getIs_active() : "N/A"
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

    private void toggleTruckState(int row) {
        DefaultTableModel model = (DefaultTableModel) truckTable.getModel();
        int userId = (int) model.getValueAt(row, 0);   // Get ID from first column
        String currentState = (String) model.getValueAt(row, 6);   // Get ID from first column

        boolean is_active = !"Si".equalsIgnoreCase(currentState);

//        boolean is_active = Objects.equals(currentState, "Si");
        String confirmText = String.format("¿Estás seguro de %s este camion?", !is_active ? "Inhabilitar" : "Habilitar");
        int confirm = JOptionPane.showConfirmDialog(this,
                confirmText,
                "Confirmar",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            truckController.processDisableTruck(is_active ? 1 : 0, userId);
        }
    }
    public JPanel getRootPanel() {
        return rootPanel;
    }

}