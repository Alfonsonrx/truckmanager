package com.diamondogs.trucksapp.views.panels.DashboardPanel.dashboardCards;

import javax.swing.*;
import java.awt.*;

public class MaintenancePanel extends JPanel {
    private JPanel rootPanel;
    private final JTable truckTable = new JTable();
    private final String[] columnNames = {"ID", "Camion", "Fecha", "Tipo","Descripcion"};

    public MaintenancePanel() {
        initializeComponents();
    }

    public void initializeComponents() {
        rootPanel = new JPanel(new BorderLayout());
        rootPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JScrollPane tableScrollPane = new JScrollPane(truckTable);


        // --- CONEXIÓN MVC (A realizar) ---
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }

}
