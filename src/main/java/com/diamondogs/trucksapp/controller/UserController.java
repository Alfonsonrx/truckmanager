package com.diamondogs.trucksapp.controller;

import com.diamondogs.trucksapp.model.User;
import com.diamondogs.trucksapp.repositories.UserRepository;
import com.diamondogs.trucksapp.views.panels.DashboardPanel.dashboardCards.UsersPanel;

import javax.swing.*;
import java.util.List;

public class UserController {
    private final UserRepository repository = new UserRepository();
    private final UsersPanel vista; // Vista

    public UserController(UsersPanel vista) {
        this.vista = vista;
    }

    // In UserController.java
    public void loadAndShowUsers() {
        SwingWorker<List<User>, Void> worker = new SwingWorker<>() {
            @Override
            protected List<User> doInBackground() {
                return repository.findALl();
            }

            @Override
            protected void done() {
                try {
                    List<User> users = get();
                    vista.updateTable(users);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Failed to load users");
                }
            }
        };
        worker.execute();
    }
}
