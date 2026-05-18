package com.diamondogs.trucksapp.views.panels.DashboardPanel.dashboardCards.forms;

import com.formdev.flatlaf.FlatClientProperties;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class FormComputer extends JPanel {
    private JPanel rootPanel;
    private JLabel lblTitle;

    private JPanel formPanel;
    private JLabel lblType;
    private JTextField inputType;
    private JLabel lblSwInstalled;
    private JTextArea inputSwInstalled;

    private JPanel HwDetailPanel;
    private JLabel lblCPU;
    private JTextField inputCPU;
    private JLabel lblMotherboard;
    private JTextField inputMotherboard;
    private JLabel lblRam;
    private JTextField inputRam;
    private JLabel lblStorage;
    private JTextField inputStorage;

    private JButton btnGuardar;
    private JLabel lblSerialNum;
    private JTextField inputSerialNum;

    public FormComputer(String title_label, String title_form, boolean showSaveButton) {

        lblTitle.setText(title_label);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));

//        formPanel.setLayout(new GridLayout(4, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createTitledBorder(title_form));

        inputSwInstalled.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(0x4a4a4a),1),
                BorderFactory.createEmptyBorder(6,8,6,8)
        ));

//        HwDetailPanel.setLayout(new GridLayout(4, 2, 10, 10));
        HwDetailPanel.setBorder(BorderFactory.createTitledBorder(title_form));
        btnGuardar.setVisible(showSaveButton);

        setVisible(true);
    }

    public FormComputer(String title_label, String title_form) {
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

    public String getType() {
        return inputType.getText();
    }
    public String getSwInstalled() {
        return inputSwInstalled.getText();
    }

    public String getCPU() {
        return inputCPU.getText();
    }
    public String getMotherboard() {
        return inputMotherboard.getText();
    }
    public String getRam() {
        return inputRam.getText();
    }
    public String getStorage() {
        return inputStorage.getText();
    }

    public void clearForm() {
        inputType.setText("");
        inputSwInstalled.setText("");

        inputCPU.setText("");
        inputMotherboard.setText("");
        inputRam.setText("");
        inputStorage.setText("");
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }
}
