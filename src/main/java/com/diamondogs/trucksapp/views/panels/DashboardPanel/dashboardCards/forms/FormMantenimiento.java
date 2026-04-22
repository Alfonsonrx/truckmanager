package com.diamondogs.trucksapp.views.panels.DashboardPanel.dashboardCards.forms;

import com.diamondogs.trucksapp.model.Maintenance;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class FormMantenimiento extends JPanel {
    private JPanel rootPanel;
    private JLabel lblTitle;
    private JPanel formPanel;
    private JTextField inputTruck;
    private JTextField inputDate;
    private JTextField inputType;
    private JTextArea inputDesc;
    private JLabel lblTruck;
    private JLabel lblDate;
    private JLabel lblType;
    private JLabel lblDesc;
    private JButton btnGuardar;
    private JPanel panelBotones;

    public FormMantenimiento(String title_label, String title_form, boolean showSaveButton) {

        lblTitle.setText(title_label);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));

        formPanel.setLayout(new GridLayout(4, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createTitledBorder(title_form));
        btnGuardar.setVisible(showSaveButton);

        setVisible(true);
    }

    public FormMantenimiento(String title_label, String title_form) {
        this(title_label, title_form, false);
    }
    public void addSaveListener(ActionListener listener) {
        if (btnGuardar != null) {
            btnGuardar.addActionListener(listener);
        }
    }

    public JButton getBtnGuardar() {
        return btnGuardar;
    }

    public String getTruck() {
        return inputTruck.getText();
    }

    public String getDate() {
        return inputDate.getText();
    }

    public String getType() {
        return inputType.getText();
    }

    public String getDesc() {
        return inputDesc.getText();
    }


    public JPanel getRootPanel() {
        return rootPanel;
    }

    public void clearForm() {
        inputTruck.setText("");
        inputDate.setText("");
        inputType.setText("");
        inputDesc.setText("");
    }
    public void fillForm(Maintenance maintenance) {
        if (maintenance == null) return;

        inputTruck.setText(maintenance.getTruck() != 0 ? String.valueOf(maintenance.getTruck()) : "");
        inputDate.setText(maintenance.getDate() != null ? String.valueOf(maintenance.getDate()) : "");
        inputType.setText(maintenance.getMaintenanceType() != null ? maintenance.getMaintenanceType() : "");
        inputDesc.setText(maintenance.getDescription() != null ? maintenance.getDescription() : "");
    }
}
