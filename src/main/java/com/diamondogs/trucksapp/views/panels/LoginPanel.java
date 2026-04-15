package com.diamondogs.trucksapp.views.panels;

import com.diamondogs.trucksapp.views.AppNavigator;
import com.diamondogs.trucksapp.views.Mainframe;

import javax.swing.*;

public class LoginPanel {
    private JPanel rootPanel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JPasswordField passwordField1;

    private final AppNavigator navigator;

    public LoginPanel(AppNavigator navigator) {
        this.navigator = navigator;
        loginButton.addActionListener(e -> performLogin());
    }

    private void performLogin() {
        if (true) {
            navigator.showPanel("dashboard");
        }

    }

    public JPanel getRootPanel() {
        return rootPanel;
    }
}
