package com.diamondogs.trucksapp.controller;

import com.diamondogs.trucksapp.model.Computer;
import com.diamondogs.trucksapp.repositories.ComputerRepository;
import com.diamondogs.trucksapp.views.panels.DashboardPanel.dashboardCards.ComputerDetailFrame;
import com.diamondogs.trucksapp.views.panels.DashboardPanel.dashboardCards.subpanels.ComputersPanel;

import javax.swing.*;
import java.sql.Date;
import java.util.List;

public class ComputerController {
    private ComputersPanel vistaPanel;
    private ComputerDetailFrame vistaFrame;

    public ComputerController(ComputersPanel vistaPanel) {
        this.vistaPanel = vistaPanel;
    }

    public ComputerController(ComputerDetailFrame vistaFrame) {
        this.vistaFrame = vistaFrame;
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
                    vistaPanel.updateTable(computers);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Error al cargar la lista de computadores.");
                    e.printStackTrace();
                }
            }
        };
        worker.execute();

    }

    //Testeo
    public void loadAndShowComputerDetail(String serialNum) {
        SwingWorker<Computer, Void> worker = new SwingWorker<>() {
            @Override
            protected Computer doInBackground() {
                // TODO: replace with repository call when DB query is ready
                return new Computer(
                        serialNum,
                        Date.valueOf("2023-08-05"),
                        "Laptop",
                        "8GB Ram",
                        "ROG",
                        "i5-10500",
                        "WD BlueSSD 500GB",
                        Date.valueOf("2026-02-10"),
                        2,
                        "Karla Munoz"
                );
            }
            @Override
            protected void done() {
                try {
                    Computer detail = get();
                    vistaFrame.updateDetail(detail);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(vistaFrame, "Error al cargar detalle del computador.");
                    e.printStackTrace();
                }
            }
        };
        worker.execute();
    }
}
