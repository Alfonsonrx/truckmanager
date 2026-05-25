package com.diamondogs.trucksapp.views.panels.DashboardPanel.dashboardCards;

import com.diamondogs.trucksapp.controller.ComputerAssignmentController;
import com.diamondogs.trucksapp.controller.ComputerController;
import com.diamondogs.trucksapp.controller.ComputerMaintenanceController;
import com.diamondogs.trucksapp.controller.SoftwareController;
import com.diamondogs.trucksapp.model.Computer;
import com.diamondogs.trucksapp.model.ComputerAssignment;
import com.diamondogs.trucksapp.model.ComputerMaintenance;
import com.diamondogs.trucksapp.model.Software;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ComputerDetailFrame extends JFrame {
    private final String computerSN;

    private JPanel mainPanel;
    private JPanel infoPanel;

    private JTable maintenanceTable;
    private JTable userHistoryTable;
    private JTable softwareInstalledTable;
    private Computer detail;

    public ComputerDetailFrame(Component parent, String computerSN) {
        this.computerSN = computerSN;

        ComputerController computerController = new ComputerController(this);
        ComputerMaintenanceController maintenanceController = new ComputerMaintenanceController(this, computerSN);
        SoftwareController softwareController = new SoftwareController(this, computerSN);
        ComputerAssignmentController assignmentController = new ComputerAssignmentController(this, computerSN);

        setTitle("Detalle de Computador - SN: " + computerSN);
        setSize(800, 900);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        initUI();

        computerController.loadAndShowComputerDetail(computerSN);
        maintenanceController.loadAndShowMaintenances();
        softwareController.loadAndShowSoftware();
        assignmentController.loadAndShowAssignments();
    }

    private void initUI() {
        mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        infoPanel = createInfoPanel();

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Historial de Mantenimientos", createMaintenanceTablePanel());
        tabbedPane.addTab("Historial usuarios asignados", createAssignedUsersTablePanel());
        tabbedPane.addTab("Software Instalado", createSoftwareTablePanel());

        mainPanel.add(infoPanel, BorderLayout.NORTH);
        mainPanel.add(tabbedPane, BorderLayout.CENTER);

        setContentPane(mainPanel);
    }

    // Details
    private JPanel createInfoPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Información General"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 12, 8, 12);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Cargando información..."), gbc);
        return panel;
    }

    public void updateDetail(Computer detail) {
        this.detail = detail;
        mainPanel.remove(infoPanel);
        infoPanel = buildInfoPanel(detail);
        mainPanel.add(infoPanel, BorderLayout.NORTH);
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    private JPanel buildInfoPanel(Computer detail) {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Información General"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 12, 8, 12);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        int row = 0;
        addInfoRow(panel, gbc, row++, "Serial Number:", detail.getSerial_num());
        addInfoRow(panel, gbc, row++, "Fecha de Adquisición:",
                detail.getAdquisicion() != null ? detail.getAdquisicion().toString() : "—");
        addInfoRow(panel, gbc, row++, "Tipo:", detail.getTipo());
        addInfoRow(panel, gbc, row++, "Último Mantenimiento:",
                detail.getLastMaintenanceDate() != null ? detail.getLastMaintenanceDate().toString() : "Sin registros");
        addInfoRow(panel, gbc, row++, "Usuario Actual:",
                detail.getCurrentUserName() != null ? detail.getCurrentUserName() : "Sin asignar");
        addInfoRow(panel, gbc, row++, "RAM:", detail.getRam());
        addInfoRow(panel, gbc, row++, "Motherboard:", detail.getMotherboard());
        addInfoRow(panel, gbc, row++, "CPU:", detail.getCpu());
        addInfoRow(panel, gbc, row++, "Almacenamiento:", detail.getStorage());

        return panel;
    }

    // Maintenances
    private JPanel createMaintenanceTablePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        maintenanceTable = new JTable();
        DefaultTableModel model = new DefaultTableModel(
                new String[]{"Fecha", "Tipo", "Razones"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        maintenanceTable.setModel(model);
        panel.add(new JScrollPane(maintenanceTable), BorderLayout.CENTER);
        return panel;
    }

    public void updateMaintenanceTable(List<ComputerMaintenance> maintenances) {
        if (maintenances == null || maintenanceTable == null) return;

        DefaultTableModel model = (DefaultTableModel) maintenanceTable.getModel();
        model.setRowCount(0);

        for (ComputerMaintenance m : maintenances) {
            model.addRow(new Object[]{
                    m.getDate() != null ? m.getDate().toString() : "",
                    m.getType() != null ? m.getType() : "",
                    m.getReasons() != null ? m.getReasons() : ""
            });
        }
    }

    // Assigned Users
    private JPanel createAssignedUsersTablePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        userHistoryTable = new JTable();

        DefaultTableModel model = new DefaultTableModel(
                new String[]{"ID Usuario", "Nombre", "Fecha asignacion", "Fecha termino"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        userHistoryTable.setModel(model);
        panel.add(new JScrollPane(userHistoryTable), BorderLayout.CENTER);
        return panel;
    }
    public void updateAssignedUsersTable(List<ComputerAssignment> assignments) {
        if (assignments == null || userHistoryTable == null) return;

        DefaultTableModel model = (DefaultTableModel) userHistoryTable.getModel();
        model.setRowCount(0);

        for (ComputerAssignment a : assignments) {
            model.addRow(new Object[]{
                    a.getUser_id(),
                    a.getUser_name() != null ? a.getUser_name() : "",
                    a.getAssigned_date() != null ? a.getAssigned_date() : "",
                    a.getUnassigned_date() != null ? a.getUnassigned_date() : "",
            });
        }
    }

    // Software Installed
    private JPanel createSoftwareTablePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        softwareInstalledTable = new JTable();

        DefaultTableModel model = new DefaultTableModel(
                new String[]{"ID Software", "Nombre", "Version", "Estado", "Fecha instalacion", "Ultima Actualizacion"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        softwareInstalledTable.setModel(model);

        panel.add(new JScrollPane(softwareInstalledTable), BorderLayout.CENTER);
        return panel;
    }
    public void updateSoftwareTable(List<Software> softwareList) {
        if (softwareList == null || softwareInstalledTable == null) return;

        DefaultTableModel model = (DefaultTableModel) softwareInstalledTable.getModel();
        model.setRowCount(0);

        for (Software s : softwareList) {
            model.addRow(new Object[]{
                    s.getId(),
                    s.getName() != null ? s.getName() : "",
                    s.getVersion() != null ? s.getVersion() : "",
                    s.getStatus() != null ? s.getStatus() : "",
                    s.getDate_installed() != null ? s.getDate_installed() : "",
                    s.getLast_updated() != null ? s.getLast_updated() : ""
            });
        }
    }

    //utils

    private void addInfoRow(JPanel panel, GridBagConstraints gbc, int row,
                            String labelText, String valueText) {
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.weightx = 0.3;
        panel.add(new JLabel(labelText), gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.7;
        JLabel valueLabel = new JLabel(valueText != null ? valueText : "—");
        valueLabel.setFont(valueLabel.getFont().deriveFont(Font.BOLD));
        panel.add(valueLabel, gbc);
    }
}
