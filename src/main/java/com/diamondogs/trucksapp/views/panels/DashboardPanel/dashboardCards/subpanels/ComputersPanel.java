package com.diamondogs.trucksapp.views.panels.DashboardPanel.dashboardCards.subpanels;

import com.diamondogs.trucksapp.controller.ComputerController;
import com.diamondogs.trucksapp.model.Computer;
import com.diamondogs.trucksapp.model.User;
import com.diamondogs.trucksapp.session.SessionManager;
import com.diamondogs.trucksapp.views.panels.DashboardPanel.dashboardCards.ComputerDetailFrame;
import com.diamondogs.trucksapp.views.panels.DashboardPanel.dashboardCards.forms.FormComputer;
import com.diamondogs.trucksapp.views.panels.DashboardPanel.utils.ButtonEditor;
import com.diamondogs.trucksapp.views.panels.DashboardPanel.utils.ButtonRenderer;

import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.function.Consumer;

public class ComputersPanel extends JPanel {
    private JPanel rootPanel;

    private final FormComputer formComputer;
    private final ComputerController computersController;

    private final Consumer<User> sessionListener;

    private final JTable computersTable = new JTable();
    private final String[] columnNames = {"Numero Serie", "Fecha Adquisicion", "Tipo", "Detalle"};

    public ComputersPanel() {

        formComputer = new FormComputer("Registro de computadores","Ingrese los datos del computador", true);
        computersController = new ComputerController(this);

        initializeComponents();
        sessionListener = user -> SwingUtilities.invokeLater(()->{
            boolean isAdmin = "administrador".equalsIgnoreCase(SessionManager.getInstance().getRole());
            formComputer.setVisible(isAdmin);
            setupTable();
            computersController.loadAndShowComputers();
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

        String[] columns = (isAdmin || isTechnician) ? columnNames : new String[]{"Numero Serie", "Fecha Adquisicion", "Tipo"};

        DefaultTableModel model = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 3;
            }
        };

        //Aplique el modelo a la JTable física para que pinte las columnas en la pantalla (Pua IA esto)
        computersTable.setModel(model);
        computersTable.getColumn("Detalle").setCellRenderer(new ButtonRenderer("Ver Detalle"));
        computersTable.getColumn("Detalle").setCellEditor(new ButtonEditor("Ver Detalle", this::showDetail));

        computersTable.getColumn("Detalle").setMaxWidth(100);
        computersTable.getColumn("Detalle").setMinWidth(70);
        computersTable.getColumn("Detalle").setPreferredWidth(100);
        computersTable.setRowHeight(30);
    }

    /**
     * Recibe los datos procesados en segundo plano por el
     * controlador e introduce dinámicamente las filas en la tabla visual.
     */
    public void updateTable(List<Computer> computers) {
        if (computers == null) return;

        DefaultTableModel model = (DefaultTableModel) computersTable.getModel();
        model.setRowCount(0); // Limpia filas viejas para evitar duplicaciones visuales

        for (Computer comp : computers) {
            // Insertamos los valores de tu modelo en orden correspondiente a las columnas
            model.addRow(new Object[]{
                    comp.getSerial_num() != null ? comp.getSerial_num() : "N/A",
                    comp.getAdquisicion() != null ? comp.getAdquisicion().toString() : "Sin fecha",
                    comp.getTipo() != null ? comp.getTipo() : "N/A",
            });
        }
    }
    private void showDetail(int row) {
        DefaultTableModel model = (DefaultTableModel) computersTable.getModel();
        String itemId = (String) model.getValueAt(row, 0);   // Get ID from first column

        // Open new window
        new ComputerDetailFrame(this, itemId).setVisible(true);
    }
    public JPanel getRootPanel() {
        return rootPanel;
    }
}
