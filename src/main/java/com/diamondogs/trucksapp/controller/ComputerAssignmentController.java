package com.diamondogs.trucksapp.controller;

import com.diamondogs.trucksapp.model.ComputerAssignment;
import com.diamondogs.trucksapp.repositories.ComputerAssignmentRepository;
import com.diamondogs.trucksapp.views.panels.DashboardPanel.dashboardCards.ComputerDetailFrame;

import javax.swing.*;
import java.util.List;

public class ComputerAssignmentController {
    private final ComputerDetailFrame vistaFrame;
    private final String serialNum;

    public ComputerAssignmentController(ComputerDetailFrame vista, String serialNum) {
        this.vistaFrame = vista;
        this.serialNum = serialNum;
    }

    public void loadAndShowAssignments() {
        SwingWorker<List<ComputerAssignment>, Void> worker = new SwingWorker<>() {
            @Override
            protected List<ComputerAssignment> doInBackground() {
                return ComputerAssignmentRepository.findBySerialNum(serialNum);
            }

            @Override
            protected void done() {
                try {
                    List<ComputerAssignment> assignments = get();
                    if (vistaFrame != null) {
                        vistaFrame.updateAssignedUsersTable(assignments);
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(vistaFrame, "Error al cargar historial de usuarios asignados.");
                    e.printStackTrace();
                }
            }
        };
        worker.execute();
    }
}
