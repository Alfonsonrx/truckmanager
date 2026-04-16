package com.diamondogs.trucksapp.views.panels.DashboardPanel.dashboardCards.dialogs;

import com.diamondogs.trucksapp.model.Truck;
import com.diamondogs.trucksapp.repositories.TruckRepository;
import com.diamondogs.trucksapp.views.panels.DashboardPanel.dashboardCards.forms.VentanaCamion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TruckEditDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JPanel panelForm;

    public TruckEditDialog(Component parent, int truckId) {
        super((Frame) SwingUtilities.getWindowAncestor(parent), "Editar Camion", true);
        setSize(600, 500);
        setLocationRelativeTo(parent);

        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);


        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);


        VentanaCamion formCamion = new VentanaCamion("Actualizar Camion","Ingrese los nuevos datos");
        
        TruckRepository repo = new TruckRepository();
        Truck truck = repo.findOneTruck(truckId);
        
        if (truck.getPlate() != null) {
            formCamion.setPatente(truck.getPlate());
            formCamion.setMarca(truck.getBrand());
            formCamion.setModelo(truck.getModel());
            formCamion.setAnio(String.valueOf(truck.getYear()));
            formCamion.setKilometraje(String.valueOf(truck.getKilometers()));
            formCamion.setConductor(String.valueOf(truck.getDriver()));
            formCamion.setColor(truck.getColor());
            if (truck.getLatest_maintenance() != null) {
                formCamion.setLatest_maintenance(truck.getLatest_maintenance().toString());
            }
        }
        
        panelForm.add(formCamion, BorderLayout.CENTER);
    }

    private void onOK() {
        // add your code here
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}
