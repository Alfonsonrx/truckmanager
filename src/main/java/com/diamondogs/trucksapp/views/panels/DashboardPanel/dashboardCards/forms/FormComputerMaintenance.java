package com.diamondogs.trucksapp.views.panels.DashboardPanel.dashboardCards.forms;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class FormComputerMaintenance extends JPanel {
    private JPanel rootPanel;
    private JLabel lblTitle;
    private JPanel formPanel;
    private JLabel lblType;
    private JLabel lblReasons;
    private JTextField inputType;
    private JTextArea inputReasons;
    private JLabel lblSerialNum;
    private JTextField inputSerialNum;
    private JLabel lblDate;
    private JTextField inputDate;
    private JButton btnGuardar;

    public FormComputerMaintenance(String title_label, String title_form, boolean showSaveButton) {

        lblTitle.setText(title_label);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));

//        formPanel.setLayout(new GridLayout(4, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createTitledBorder(title_form));

        inputReasons.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(0x4a4a4a),1),
                BorderFactory.createEmptyBorder(6,8,6,8)
        ));

        btnGuardar.setVisible(showSaveButton);

        setVisible(true);
    }

    public FormComputerMaintenance(String title_label, String title_form) {
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

    public String getSerialNum() {
        return inputSerialNum.getText();
    }
    public String getDate() {
        return inputDate.getText();
    }
    public String getType() {
        return inputType.getText();
    }
    public String getReasons() {
        return inputReasons.getText();
    }


    public void clearForm() {
        inputSerialNum.setText("");
        inputDate.setText("");
        inputType.setText("");
        inputReasons.setText("");
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }
}
