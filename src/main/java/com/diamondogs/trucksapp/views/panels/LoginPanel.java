package com.diamondogs.trucksapp.views.panels;

import com.diamondogs.trucksapp.controller.LoginController;
import com.diamondogs.trucksapp.model.User;
import com.diamondogs.trucksapp.views.AppNavigator;
import com.diamondogs.trucksapp.views.Mainframe;

import javax.swing.*;

public class LoginPanel {
    private JPanel rootPanel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JPasswordField passwordField1;
    private LoginController loginController;

    private final AppNavigator navigator;

    public LoginPanel(AppNavigator navigator) {
        this.navigator = navigator;
        loginButton.addActionListener(e -> performLogin());
        loginController = new LoginController();
    }

    private void performLogin() {
        String username = usernameField.getText();
        String password = new String(passwordField1.getPassword());
        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(rootPanel, "LLena los 2 campos");
            return;
        }
        else {
            User findUser = loginController.login(username, password);
            if (findUser != null) {
                JOptionPane.showMessageDialog(rootPanel, "Login exitoso " + findUser.getUsername());
                navigator.showPanel("dashboard");
            }
            else {
                JOptionPane.showMessageDialog(rootPanel, "Usuario o Contraseña incorrecto");
            }
        }

    }

    public JPanel getRootPanel() {
        return rootPanel;
    }
}
