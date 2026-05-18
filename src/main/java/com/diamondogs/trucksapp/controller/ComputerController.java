package com.diamondogs.trucksapp.controller;

import com.diamondogs.trucksapp.model.Computer;
import com.diamondogs.trucksapp.model.User;
import com.diamondogs.trucksapp.repositories.ComputerRepository;
import com.diamondogs.trucksapp.views.panels.DashboardPanel.dashboardCards.ComputersPanel;

import javax.swing.*;
import java.util.List;

public class ComputerController {
    private final ComputersPanel vista;

    public ComputerController(ComputersPanel vista) {
        this.vista = vista;
    }

    //Carga y muestra los computadores
    public void loadAndShowComputers() {
        SwingWorker <List<Computer>,Void> worker = new SwingWorker<>() {
            @Override
            protected List<Computer> doInBackground() {
                return ComputerRepository.AllComputers();
            }
            @Override
            protected void done() {
                try {
                    List<Computer> computers = get();
                    vista.updateTable(computers);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Error al cargar la lista de computadores.");
                    e.printStackTrace();
                }
            }
        };
        worker.execute();

    }//Fin de loadAndShowComputers
}
