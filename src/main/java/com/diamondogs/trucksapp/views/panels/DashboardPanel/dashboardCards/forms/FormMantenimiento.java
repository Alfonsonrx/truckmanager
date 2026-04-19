package com.diamondogs.trucksapp.views.panels.DashboardPanel.dashboardCards.forms;

import javax.swing.*;
import java.awt.*;

public class FormMantenimiento extends JPanel {
    private JPanel rootPanel;
    private JLabel lblTitle;
    private JTextField inputTruck;
    private JPanel formPanel;
    private JLabel lblTruck;
    private JTextField inputDate;
    private JTextField inputType;
    private JTextArea inputDesc;
    private JLabel lblDate;
    private JLabel lblType;
    private JLabel lblDesc;

    public FormMantenimiento(String title_label, String title_form) {

        lblTitle.setText(title_label);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 24));

        formPanel.setLayout(new GridLayout(4, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createTitledBorder(title_form));
        setVisible(true);
    }


    public JPanel getRootPanel() {
        return rootPanel;
    }
}
