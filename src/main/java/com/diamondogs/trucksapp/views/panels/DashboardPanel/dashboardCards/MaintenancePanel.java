package com.diamondogs.trucksapp.views.panels.DashboardPanel.dashboardCards;

import com.diamondogs.trucksapp.controller.MaintenanceController;
import com.diamondogs.trucksapp.model.Maintenance;
import com.diamondogs.trucksapp.model.User;
import com.diamondogs.trucksapp.session.SessionManager;
import com.diamondogs.trucksapp.views.panels.DashboardPanel.dashboardCards.dialogs.MaintenanceEditDialog;
import com.diamondogs.trucksapp.views.panels.DashboardPanel.dashboardCards.forms.FormMantenimiento;
import com.diamondogs.trucksapp.views.panels.DashboardPanel.utils.ButtonEditor;
import com.diamondogs.trucksapp.views.panels.DashboardPanel.utils.ButtonRenderer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.function.Consumer;

public class MaintenancePanel extends JPanel {
    private JPanel rootPanel;

    private final FormMantenimiento formMantenimiento;
    private final MaintenanceController controller;

    private final Consumer<User> sessionListener;

    private final JTable maintenanceTable = new JTable();
    private final String[] columnNames = {"ID", "Camion", "Fecha", "Tipo","Descripcion","Editar", "Eliminar"};

//    private JButton btnGuardar;

    public MaintenancePanel() {

        // --- CONEXIÓN MVC (A realizar) ---
        // 1. Creamos la instancia del panel de registro
        formMantenimiento = new FormMantenimiento("Registro de mantenimientos","Ingrese los datos del mantenimiento", true);

        // 2. Creamos el controlador pasándole ESA instancia
        controller = new MaintenanceController(formMantenimiento, this);
        initializeComponents();
//        controller.loadAndShowMaintenances();
        sessionListener = user -> SwingUtilities.invokeLater(()->{
            setupTable();
            controller.loadAndShowMaintenances();
        });
        SessionManager.getInstance().addListener(sessionListener);
    }

    public void initializeComponents() {
        rootPanel = new JPanel(new BorderLayout());
        rootPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JScrollPane tableScrollPane = new JScrollPane(maintenanceTable);
        tableScrollPane.setPreferredSize(new Dimension(0, 250)); // Limit table height
        tableScrollPane.setMinimumSize(new Dimension(0, 200));
        tableScrollPane.setMaximumSize(new Dimension(0, 500));
//        setupTable();

        // 3. Agregamos el formulario (ya conectado) al panel
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(tableScrollPane, BorderLayout.CENTER);

        centerPanel.add(formMantenimiento.getRootPanel(), BorderLayout.SOUTH);

        rootPanel.add(new JLabel("Gestión de Mantenimiento"), BorderLayout.NORTH);
        rootPanel.add(centerPanel, BorderLayout.CENTER);
    }

    private void setupTable() {
        boolean isAdmin = "administrador".equalsIgnoreCase(SessionManager.getInstance().getRole());

        String[] columns = isAdmin ? columnNames : new String[]{"ID", "Camion", "Fecha", "Tipo","Descripcion"};

        DefaultTableModel model = new DefaultTableModel(columns, 0){
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 5 || column == 6;
            }
        };
        maintenanceTable.setModel(model);

        maintenanceTable.getColumn("ID").setMaxWidth(30);
        maintenanceTable.getColumn("Camion").setMaxWidth(80);
        maintenanceTable.getColumn("Fecha").setMaxWidth(80);
        maintenanceTable.getColumn("Tipo").setMaxWidth(100);
        if (isAdmin) {
            maintenanceTable.getColumn("Editar").setCellRenderer(new ButtonRenderer("Editar"));
            maintenanceTable.getColumn("Editar").setCellEditor(new ButtonEditor("Editar", this::editMaintenance));
            maintenanceTable.getColumn("Editar").setMaxWidth(90);
            maintenanceTable.getColumn("Editar").setMinWidth(70);
            maintenanceTable.getColumn("Editar").setPreferredWidth(80);

            maintenanceTable.getColumn("Eliminar").setCellRenderer(new ButtonRenderer("Eliminar"));
            maintenanceTable.getColumn("Eliminar").setCellEditor(new ButtonEditor("Eliminar", this::removeMaintenance));
            maintenanceTable.getColumn("Eliminar").setMaxWidth(90);
            maintenanceTable.getColumn("Eliminar").setMinWidth(70);
            maintenanceTable.getColumn("Eliminar").setPreferredWidth(80);
        }


        maintenanceTable.setRowHeight(30);
    }

    private void editMaintenance(int row) {
        DefaultTableModel model = (DefaultTableModel) maintenanceTable.getModel();
        int maintenanceId = (int) model.getValueAt(row, 0);   // Get ID from first column

        // Abre el dialog para editar, se pasa el id para los procesos correspondientes
        MaintenanceEditDialog dialog = new MaintenanceEditDialog(this, maintenanceId, controller);
        dialog.loadMaintenanceData();
        dialog.setVisible(true);
    }

    private void removeMaintenance(int row) {
        DefaultTableModel model = (DefaultTableModel) maintenanceTable.getModel();
        int maintenanceId = (int) model.getValueAt(row, 0);   // Get ID from first column

        int confirm = JOptionPane.showConfirmDialog(this,
                "¿Estás seguro de eliminar este mantenimiento?",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            controller.processDeleteMaintenance(maintenanceId);
        }
    }

    public void updateTable(List<Maintenance> maintenances) {
        if (maintenances == null) return;

        DefaultTableModel model = (DefaultTableModel) maintenanceTable.getModel();
        model.setRowCount(0);

        for (Maintenance maintenance : maintenances) {
            model.addRow(new Object[]{
                    maintenance.getId(),
                    maintenance.getTruck(),
                    maintenance.getDate(),
                    maintenance.getMaintenanceType(),
                    maintenance.getDescription()
            });
        }
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }

}
