package com.diamondogs.trucksapp.views.panels.DashboardPanel.dashboardCards;

import com.diamondogs.trucksapp.views.panels.DashboardPanel.dashboardCards.subpanels.ComputerMaintenancePanel;
import com.diamondogs.trucksapp.views.panels.DashboardPanel.dashboardCards.subpanels.ComputersPanel;

import javax.swing.*;
import java.awt.*;

public class ComputersMultiPanel extends JPanel {
    private JPanel rootPanel;
    private final JTabbedPane tabbedPane;

    private final ComputersPanel computersPanel;
    private final ComputerMaintenancePanel computerMaintenancePanel;

    public ComputersMultiPanel() {
        rootPanel = new JPanel(new BorderLayout());
        tabbedPane = new JTabbedPane();

        computersPanel = new ComputersPanel();
        computerMaintenancePanel = new ComputerMaintenancePanel();

        tabbedPane.addTab("Lista Computadores", computersPanel.getRootPanel());
        tabbedPane.addTab("Mantenimientos", computerMaintenancePanel.getRootPanel());

        rootPanel.add(tabbedPane, BorderLayout.CENTER);
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }
}
