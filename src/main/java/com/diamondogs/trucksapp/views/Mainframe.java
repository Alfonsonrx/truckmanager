package com.diamondogs.trucksapp.views;

import com.diamondogs.trucksapp.config.DatabaseConfig;
import com.diamondogs.trucksapp.views.panels.DashboardPanel.DashboardPanel;
import com.diamondogs.trucksapp.views.panels.LoginPanel;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;

public class Mainframe extends JFrame implements AppNavigator {

    private final JLabel connectionStatusLabel;
    private final JPanel contentPanel;
    private final CardLayout cardLayout = new CardLayout();

    private LoginPanel loginPanel;
    private DashboardPanel dashboardPanel;

    public Mainframe() {
        setTitle("Truck Manager");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setMinimumSize(new Dimension(1000, 700));
        setLocationRelativeTo(null);

        contentPanel = new JPanel(cardLayout);

        JPanel statusBar = new JPanel(new BorderLayout());
        statusBar.setBorder(BorderFactory.createMatteBorder(1,0,0,0,Color.gray));
        statusBar.setPreferredSize(new Dimension(0,28));

        connectionStatusLabel = new JLabel("Checking database connection...");
        statusBar.add(connectionStatusLabel, BorderLayout.WEST);

        loginPanel = new LoginPanel(this);
        dashboardPanel = new DashboardPanel(this);

        contentPanel.add(loginPanel.getRootPanel(), "login");
        contentPanel.add(dashboardPanel.getRootPanel(), "dashboard");
        add(contentPanel, BorderLayout.CENTER);

        add(statusBar, BorderLayout.SOUTH);

        showPanel("login");

        checkDatabaseConnection();
    }

    private void checkDatabaseConnection() {
        SwingWorker<Boolean, Void> worker = new SwingWorker<>() {
            @Override
            protected Boolean doInBackground() {
                try (Connection conn = DatabaseConfig.getConnection()) {
                    try (var stmt = conn.createStatement();
                         var rs = stmt.executeQuery("SELECT 1")) {
                        return rs.next();
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    return false;
                }
            }

            @Override
            protected void done() {
                try {
                    boolean isConnected = get();
                    if (isConnected) {
                        connectionStatusLabel.setText("● Connected to Database");
                        connectionStatusLabel.setForeground(new Color(0, 180, 0)); // Green
                    } else {
                        connectionStatusLabel.setText("● Database Connection Failed");
                        connectionStatusLabel.setForeground(Color.RED);
                    }
                } catch (Exception ex) {
                    connectionStatusLabel.setText("● Connection Error");
                    connectionStatusLabel.setForeground(Color.RED);
                }
            }
        };
        worker.execute();
    }

    @Override
    public void refreshConnectionStatus() {
        checkDatabaseConnection();
    }
    @Override
    public void showPanel(String name) {
        cardLayout.show(contentPanel, name);
    }
}
