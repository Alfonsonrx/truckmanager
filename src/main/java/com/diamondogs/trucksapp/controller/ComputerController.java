package com.diamondogs.trucksapp.controller;

import com.diamondogs.trucksapp.model.Computer;
import com.diamondogs.trucksapp.model.User;
import com.diamondogs.trucksapp.repositories.ComputerRepository;
import com.diamondogs.trucksapp.views.panels.DashboardPanel.dashboardCards.ComputerDetailFrame;
import com.diamondogs.trucksapp.views.panels.DashboardPanel.dashboardCards.forms.FormComputer;
import com.diamondogs.trucksapp.views.panels.DashboardPanel.dashboardCards.subpanels.ComputersPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.List;

public class ComputerController implements ActionListener {
    private ComputersPanel vistaPanel;
    private FormComputer formComputer;
    private ComputerDetailFrame vistaFrame;
    private ComputerRepository repositorio;

    public ComputerController(ComputersPanel vistaPanel, FormComputer formComputer) {
        this.formComputer = formComputer;
        this.repositorio = new ComputerRepository();
        this.vistaPanel = vistaPanel;

        if(formComputer.getBtnGuardar() != null) {
            this.formComputer.getBtnGuardar().addActionListener(this);
            System.out.println("Controlador: Botón de guardado vinculado correctamente.");
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == formComputer.getBtnGuardar()) {
            procesarGuardadoComputador();
        }
    }

    private void procesarGuardadoComputador() {
        try {
            Computer comp = new Computer();
            comp.setSerial_num(formComputer.getSerialNum());
            comp.setTipo(formComputer.getType());
            comp.setRam(formComputer.getRam());
            comp.setMotherboard(formComputer.getMotherboard());
            comp.setCpu(formComputer.getCPU());
            comp.setStorage(formComputer.getStorage());
            // TODO: add adquisition_date if added to form, for now using current date
            comp.setAdquisicion(new java.util.Date());
            
            boolean success = ComputerRepository.insertComputer(comp);
            
            if (success) {
                JOptionPane.showMessageDialog(null, "Computador guardado exitosamente.");
                formComputer.clearForm();
                loadAndShowComputers(); // Refresh table
            } else {
                JOptionPane.showMessageDialog(null, "Error al guardar en la base de datos.");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error validando datos: " + ex.getMessage());
            ex.printStackTrace();
        }
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
