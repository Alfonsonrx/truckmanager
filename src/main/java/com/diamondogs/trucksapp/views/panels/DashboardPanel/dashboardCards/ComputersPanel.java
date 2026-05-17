package com.diamondogs.trucksapp.views.panels.DashboardPanel.dashboardCards;

import com.diamondogs.trucksapp.controller.TruckController;
import com.diamondogs.trucksapp.model.User;
import com.diamondogs.trucksapp.session.SessionManager;
import com.diamondogs.trucksapp.views.panels.DashboardPanel.dashboardCards.forms.FormComputer;
import com.diamondogs.trucksapp.views.panels.DashboardPanel.dashboardCards.forms.VentanaCamion;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.function.Consumer;

public class ComputersPanel extends JPanel {
    private JPanel rootPanel;

    private final FormComputer formComputer;
//    private final ComputersController computersController;

    private final Consumer<User> sessionListener;

    private final JTable computersTable = new JTable();
    private final String[] columnNames = {"Numero Serie", "Fecha Adquisicion", "Usuario Asignado", "Detalles", "Estado"};

    public ComputersPanel() {
        formComputer = new FormComputer("Registro de computadores","Ingrese los datos del computador", true);

//        truckController = new TruckController(formCamion, this);
        initializeComponents();
        sessionListener = user -> SwingUtilities.invokeLater(()->{
            boolean isAdmin = "administrador".equalsIgnoreCase(SessionManager.getInstance().getRole());
            formComputer.setVisible(isAdmin);
            setupTable();
//            truckController.loadAndShowTrucks();
        });
        SessionManager.getInstance().addListener(sessionListener);
    }

    private void initializeComponents() {
        rootPanel = new JPanel(new BorderLayout());
        rootPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JScrollPane tableScrollPane = new JScrollPane(computersTable);
        tableScrollPane.setPreferredSize(new Dimension(0, 250)); // Limit table height
        tableScrollPane.setMinimumSize(new Dimension(0, 200));
        tableScrollPane.setMaximumSize(new Dimension(0, 500));

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(tableScrollPane, BorderLayout.CENTER);
        centerPanel.add(formComputer.getRootPanel(), BorderLayout.SOUTH);

        rootPanel.add(new JLabel("Gestión de Computadores"), BorderLayout.NORTH);
        rootPanel.add(centerPanel, BorderLayout.CENTER);
    }
    private void setupTable() {
        boolean isAdmin = "administrador".equalsIgnoreCase(SessionManager.getInstance().getRole());
        boolean isTechnician = "tecnico".equalsIgnoreCase(SessionManager.getInstance().getRole());

        String[] columns = (isAdmin || isTechnician) ? columnNames : new String[]{"Numero Serie", "Fecha Adquisicion", "Usuario Asignado", "Detalles"};

        DefaultTableModel model = new DefaultTableModel(columns, 0){
        };
    }
    public JPanel getRootPanel() {
        return rootPanel;
    }
}
