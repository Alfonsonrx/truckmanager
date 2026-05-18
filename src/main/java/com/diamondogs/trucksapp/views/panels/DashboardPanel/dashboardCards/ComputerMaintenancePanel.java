package com.diamondogs.trucksapp.views.panels.DashboardPanel.dashboardCards;

import com.diamondogs.trucksapp.controller.ComputerController;
import com.diamondogs.trucksapp.model.Computer;
import com.diamondogs.trucksapp.model.User;
import com.diamondogs.trucksapp.session.SessionManager;
import com.diamondogs.trucksapp.views.panels.DashboardPanel.dashboardCards.forms.FormComputer;
import com.diamondogs.trucksapp.views.panels.DashboardPanel.dashboardCards.forms.FormComputerMaintenance;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.function.Consumer;

public class ComputerMaintenancePanel extends JPanel {
    private JPanel rootPanel;

    private final FormComputerMaintenance formComputerMaintenance;
    private final Consumer<User> sessionListener;

    private final JTable computersMaintenanceTable = new JTable();
    private final String[] columnNames = {"ID","Numero Serie", "Fecha", "Tipo", "Razones"};

    public ComputerMaintenancePanel() {
        formComputerMaintenance = new FormComputerMaintenance("Registro de computadores","Ingrese los datos del computador", true);

        initializeComponents();
        sessionListener = user -> SwingUtilities.invokeLater(()->{
            boolean isAdmin = "administrador".equalsIgnoreCase(SessionManager.getInstance().getRole());
            formComputerMaintenance.setVisible(isAdmin);
            setupTable();
//            computersController.loadAndShowComputers();
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

    public JPanel getRootPanel() {
        return rootPanel;
    }
}
