package com.diamondogs.trucksapp.views.panels.DashboardPanel;

import com.diamondogs.trucksapp.views.AppNavigator;
import com.diamondogs.trucksapp.views.panels.DashboardPanel.dashboardCards.MaintenancePanel;
import com.diamondogs.trucksapp.views.panels.DashboardPanel.dashboardCards.TrucksPanel;
import com.diamondogs.trucksapp.views.panels.DashboardPanel.dashboardCards.UsersPanel;

import javax.swing.*;
import java.awt.*;

public class DashboardPanel extends JPanel {
    private final AppNavigator navigator;

    private JPanel rootPanel;
    private JPanel sidebarPanel;
    private JPanel contentPanel;
    private JButton usersButton;
    private JButton trucksButton;
    private JButton maintenancesButton;

    private final CardLayout cardLayout = new CardLayout();
    private final UsersPanel usersPanel;
    private final TrucksPanel trucksPanel;
    private final MaintenancePanel maintenancePanel;

    public DashboardPanel(AppNavigator navigator) {
        this.navigator = navigator;

        initializeComponents();

        usersPanel = new UsersPanel();
        contentPanel.add(usersPanel.getRootPanel(), "users");
        trucksPanel = new TrucksPanel();
        contentPanel.add(trucksPanel.getRootPanel(), "trucks");
        maintenancePanel = new MaintenancePanel();
        contentPanel.add(maintenancePanel.getRootPanel(), "maintenances");

        usersButton.addActionListener(e -> showCard("users"));
        trucksButton.addActionListener(e -> showCard("trucks"));
        maintenancesButton.addActionListener(e -> showCard("maintenances"));

        showCard("users");
    }

    private void initializeComponents() {
        // Set up rootPanel with BorderLayout
        setLayout(new BorderLayout());
        rootPanel = new JPanel(new BorderLayout());

        // Set up sidebarPanel with BoxLayout (keeps buttons at preferred size)
        sidebarPanel = new JPanel();
        sidebarPanel.setLayout(new BoxLayout(sidebarPanel, BoxLayout.Y_AXIS));
        sidebarPanel.setPreferredSize(new Dimension(150, 0));
        sidebarPanel.setMaximumSize(new Dimension(400, Integer.MAX_VALUE));
        sidebarPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Add sidebar components
        JLabel titleLabel = new JLabel("Truck Manager");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        usersButton = new JButton("Usuarios");
        usersButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        trucksButton = new JButton("Camiones");
        trucksButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        maintenancesButton = new JButton("Mantenimientos");
        maintenancesButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        sidebarPanel.add(titleLabel);
        sidebarPanel.add(Box.createVerticalStrut(20));
        sidebarPanel.add(usersButton);
        sidebarPanel.add(Box.createVerticalStrut(10));
        sidebarPanel.add(trucksButton);
        sidebarPanel.add(Box.createVerticalStrut(10));
        sidebarPanel.add(maintenancesButton);

        // Set up contentPanel with CardLayout
        contentPanel = new JPanel(cardLayout);

        // Add panels to rootPanel
        rootPanel.add(sidebarPanel, BorderLayout.WEST);
        rootPanel.add(contentPanel, BorderLayout.CENTER);

        // Add rootPanel to this panel
        add(rootPanel, BorderLayout.CENTER);
    }

    public void showCard(String cardName) {
        cardLayout.show(contentPanel, cardName);
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }
}
