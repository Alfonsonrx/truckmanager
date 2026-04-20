package com.diamondogs.trucksapp.views.panels.DashboardPanel.dashboardCards;

import com.diamondogs.trucksapp.controller.UserController;
import com.diamondogs.trucksapp.model.User;
import com.diamondogs.trucksapp.views.panels.DashboardPanel.dashboardCards.dialogs.UserEditDialog;
import com.diamondogs.trucksapp.views.panels.DashboardPanel.dashboardCards.forms.VentanaConductor;
import com.diamondogs.trucksapp.views.panels.DashboardPanel.utils.ButtonEditor;
import com.diamondogs.trucksapp.views.panels.DashboardPanel.utils.ButtonRenderer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.List;


public class UsersPanel extends JPanel {
    private JPanel rootPanel;
    private final VentanaConductor formConductor;
    private final UserController userController;

    private final JTable userTable = new JTable();
    private final String[] columnNames = {"ID", "Username", "Nombre", "Rol", "Acciones"};

    public UsersPanel() {
        // --- CONEXIÓN MVC ---
        // 1. Creamos la instancia del panel de registro
        formConductor = new VentanaConductor("Registro de Usuarios", "Ingrese los datos del usuario", true);

        // 2. Creamos el controlador pasándole ESA instancia
        // (Esto hará que el botón de formConductor empiece a funcionar)
        userController = new UserController(formConductor, this);
        initializeComponents();
        userController.loadAndShowUsers();
    }

    private void initializeComponents() {
        rootPanel = new JPanel(new BorderLayout());
        rootPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JScrollPane tableScrollPane = new JScrollPane(userTable);
        tableScrollPane.setPreferredSize(new Dimension(0, 250)); // Limit table height
        tableScrollPane.setMinimumSize(new Dimension(0, 200));
        tableScrollPane.setMaximumSize(new Dimension(0, 500));
        setupTable();

        // 2.a Agregamos el formulario (ya conectado) al panel
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(tableScrollPane, BorderLayout.CENTER);
        centerPanel.add(formConductor, BorderLayout.SOUTH);

        rootPanel.add(new JLabel("Gestión de Usuarios"), BorderLayout.NORTH);
        rootPanel.add(centerPanel, BorderLayout.CENTER);
    }

    private void setupTable() {
        DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 4;
            }
        };
        userTable.setModel(model);

        TableColumn idColumn = userTable.getColumn("ID");
        idColumn.setMinWidth(20);
        idColumn.setMaxWidth(30);

        // Agregando Boton de editar
        userTable.getColumn("Acciones").setCellRenderer(new ButtonRenderer("Editar"));
        userTable.getColumn("Acciones").setCellEditor(new ButtonEditor("Editar", this::editUser));

        userTable.getColumn("Acciones").setMaxWidth(90);
        userTable.getColumn("Acciones").setMinWidth(70);
        userTable.getColumn("Acciones").setPreferredWidth(80);

        userTable.setRowHeight(30);

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

    // Funcion ejecutada al momento de clickear editar
    private void editUser(int row) {
        DefaultTableModel model = (DefaultTableModel) userTable.getModel();
        int userId = (int) model.getValueAt(row, 0);   // Get ID from first column

        // Abre el dialog para editar, se pasa el id para los procesos correspondientes
        UserEditDialog dialog = new UserEditDialog(this, userId, userController);
        dialog.loadUserData();
        dialog.setVisible(true);
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }
}
