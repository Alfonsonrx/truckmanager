package com.diamondogs.trucksapp.views.panels.DashboardPanel.dashboardCards.subpanels;

import com.diamondogs.trucksapp.controller.ComputerMaintenanceController;
import com.diamondogs.trucksapp.model.ComputerMaintenance;
import com.diamondogs.trucksapp.model.User;
import com.diamondogs.trucksapp.session.SessionManager;
import com.diamondogs.trucksapp.views.panels.DashboardPanel.dashboardCards.forms.FormComputerMaintenance;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.function.Consumer;

public class ComputerMaintenancePanel extends JPanel {
    private JPanel rootPanel;

    private final FormComputerMaintenance formComputerMaintenance;
    private final ComputerMaintenanceController maintenanceController;

    private final Consumer<User> sessionListener;

    private final JTable computersMaintenanceTable = new JTable();
    private final String[] columnNames = {"ID","Numero Serie", "Fecha", "Tipo", "Razones"};

    public ComputerMaintenancePanel() {
        formComputerMaintenance = new FormComputerMaintenance("Registro de computadores","Ingrese los datos del computador", true);
        maintenanceController = new ComputerMaintenanceController(this);

        initializeComponents();
        sessionListener = user -> SwingUtilities.invokeLater(()->{
            boolean isAdmin = "administrador".equalsIgnoreCase(SessionManager.getInstance().getRole());
            formComputerMaintenance.setVisible(isAdmin);
            setupTable();
            maintenanceController.loadAndShowMaintenances();
        });
        SessionManager.getInstance().addListener(sessionListener);
    }

    private void initializeComponents() {
        rootPanel = new JPanel(new BorderLayout());
        rootPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JScrollPane tableScrollPane = new JScrollPane(computersMaintenanceTable);
        tableScrollPane.setPreferredSize(new Dimension(0, 250)); // Limit table height
        tableScrollPane.setMinimumSize(new Dimension(0, 200));
        tableScrollPane.setMaximumSize(new Dimension(0, 500));

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(tableScrollPane, BorderLayout.CENTER);
        centerPanel.add(formComputerMaintenance.getRootPanel(), BorderLayout.SOUTH);

        rootPanel.add(new JLabel("Gestión de Computadores"), BorderLayout.NORTH);
        rootPanel.add(centerPanel, BorderLayout.CENTER);
    }
    private void setupTable() {
        boolean isAdmin = "administrador".equalsIgnoreCase(SessionManager.getInstance().getRole());
        boolean isTechnician = "tecnico".equalsIgnoreCase(SessionManager.getInstance().getRole());

        String[] columns = (isAdmin || isTechnician) ? columnNames : new String[]{"Numero Serie", "Fecha Adquisicion", "Tipo", "Software"};

        DefaultTableModel model = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        //Aplique el modelo a la JTable física para que pinte las columnas en la pantalla (Pua IA esto)
        computersMaintenanceTable.setModel(model);
        computersMaintenanceTable.setRowHeight(30);
    }
    public void updateTable(List<ComputerMaintenance> maintenances) {
        if (maintenances == null) return;
        DefaultTableModel model = (DefaultTableModel) computersMaintenanceTable.getModel();
        model.setRowCount(0);
        for (ComputerMaintenance m : maintenances) {
            model.addRow(new Object[]{
                    m.getId(),
                    m.getSn_computer(),
                    m.getDate(),
                    m.getType(),
                    m.getReasons()
            });
        }
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }
}
