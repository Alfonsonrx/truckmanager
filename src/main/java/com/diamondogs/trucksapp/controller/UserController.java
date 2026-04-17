package com.diamondogs.trucksapp.controller;

import com.diamondogs.trucksapp.model.User;
import com.diamondogs.trucksapp.repositories.UserRepository;
import com.diamondogs.trucksapp.views.panels.DashboardPanel.dashboardCards.TrucksPanel;
import com.diamondogs.trucksapp.views.panels.DashboardPanel.dashboardCards.UsersPanel;
import com.diamondogs.trucksapp.views.panels.DashboardPanel.dashboardCards.forms.VentanaConductor;

import javax.swing.*;
import java.util.List;

public class UserController {
    private VentanaConductor vistaConductor;
    private UserRepository repository = new UserRepository();
    private UsersPanel vista; // Vista

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
    public void saveUser(User user, String password) {
        SwingWorker<Boolean, Void> worker = new SwingWorker<>() {
            @Override
            protected Boolean doInBackground() {
                // Llama al repositorio
                return repository.save(user, password);
            }

            @Override
            protected void done() {
                try {
                    boolean success = get();
                    if (success) {
                        JOptionPane.showMessageDialog(null, "Usuario guardado exitosamente");
                        loadAndShowUsers(); // Recarga la tabla de usuarios en la vista
                    } else {
                        JOptionPane.showMessageDialog(null, "Error al guardar el usuario", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Ocurrió un error inesperado", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        };
        worker.execute();
    }

}
