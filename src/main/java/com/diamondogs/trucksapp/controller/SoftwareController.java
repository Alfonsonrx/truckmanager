package com.diamondogs.trucksapp.controller;

import com.diamondogs.trucksapp.model.Software;
import com.diamondogs.trucksapp.repositories.SoftwareRepository;
import com.diamondogs.trucksapp.views.panels.DashboardPanel.dashboardCards.ComputerDetailFrame;

import javax.swing.*;
import java.util.List;

public class SoftwareController {
    private ComputerDetailFrame vistaFrame;
    private final String serialNum;

    public SoftwareController(ComputerDetailFrame vista, String serialNum) {
        this.vistaFrame = vista;
        this.serialNum = serialNum;
    }

    public void loadAndShowSoftware() {
        SwingWorker<List<Software>, Void> worker = new SwingWorker<>() {
            @Override
            protected List<Software> doInBackground() {
                return SoftwareRepository.findBySerialNum(serialNum);
            }

            @Override
            protected void done() {
                try {
                    List<Software> softwareList = get();
                    if (vistaFrame != null) {
                        vistaFrame.updateSoftwareTable(softwareList);
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(vistaFrame, "Error al cargar software instalado.");
                    e.printStackTrace();
                }
            }
        };
        worker.execute();
    }
}
