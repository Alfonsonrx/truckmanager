package com.diamondogs.trucksapp.views.panels.DashboardPanel.dashboardCards;

import javax.swing.*;
import java.awt.*;

public class TrucksMultiPanel extends JPanel {
    private JPanel rootPanel;
    private final JTabbedPane tabbedPane;

    private final TrucksPanel trucksPanel;
    private final MaintenancePanel maintenancePanel;

    public TrucksMultiPanel() {
        rootPanel = new JPanel(new BorderLayout());
        tabbedPane = new JTabbedPane();

        trucksPanel = new TrucksPanel();
        maintenancePanel = new MaintenancePanel();

        tabbedPane.addTab("Lista Camiones", trucksPanel.getRootPanel());
        tabbedPane.addTab("Mantenimientos", maintenancePanel.getRootPanel());

        rootPanel.add(tabbedPane, BorderLayout.CENTER);
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }
}
