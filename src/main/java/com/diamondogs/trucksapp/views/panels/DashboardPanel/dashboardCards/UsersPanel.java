package com.diamondogs.trucksapp.views.panels.DashboardPanel.dashboardCards;

import com.diamondogs.trucksapp.controller.UserController;
import com.diamondogs.trucksapp.model.User;
import com.diamondogs.trucksapp.views.panels.DashboardPanel.dashboardCards.forms.VentanaConductor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;


public class UsersPanel extends JPanel {
    private JPanel rootPanel;
    private final JTable userTable = new JTable();

    private final String[] columnNames = {"ID", "Username", "Nombre", "Rol"};
    public UsersPanel() {
        initializeComponents();
    }



    private void initializeComponents() {
        // Set up rootPanel with BorderLayout
        rootPanel = new JPanel(new BorderLayout());
        rootPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel userListLabel = new JLabel("Lista de Usuarios");
        userListLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

        JScrollPane tableScrollPane = new JScrollPane(userTable);
        tableScrollPane.setPreferredSize(new Dimension(0, 300)); // Limit table height
        tableScrollPane.setMinimumSize(new Dimension(0, 200));
        tableScrollPane.setMaximumSize(new Dimension(0, 500));

        // Crear Panel de tabla
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.add(tableScrollPane, BorderLayout.CENTER);

        // Crear tabla con columnas
        setupTable();

        // Asignar repo y controlador para cargar datos
        UserController userController = new UserController(this);
        userController.loadAndShowUsers();

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(tablePanel, BorderLayout.NORTH);

        VentanaConductor createDriver = new VentanaConductor();
        centerPanel.add(createDriver, BorderLayout.SOUTH);

        rootPanel.add(userListLabel, BorderLayout.NORTH);
        rootPanel.add(centerPanel, BorderLayout.CENTER);
    }
    private void setupTable() {
        DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        userTable.setModel(model);

        userTable.setRowHeight(28);

        userTable.getTableHeader().setReorderingAllowed(false);

    }
    public void updateTable(List<User> users) {
        if (users == null) return;

        DefaultTableModel model = (DefaultTableModel) userTable.getModel();
        model.setRowCount(0);

        for (User user : users) {
            model.addRow(new Object[]{
                    user.getId(),
                    user.getUsername(),
                    user.getName() != null ? user.getName() : "",
                    user.getRole() != null ? user.getRole() : "User"
            });
        }
    }
    public JPanel getRootPanel() {
        return rootPanel;
    }
}
