package com.diamondogs.trucksapp.views.panels.DashboardPanel.dashboardCards.dialogs;

import com.diamondogs.trucksapp.controller.TruckController;
import com.diamondogs.trucksapp.views.panels.DashboardPanel.dashboardCards.forms.VentanaCamion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TruckEditDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JPanel panelForm;

    private final VentanaCamion formCamion;
    private final int truckId;
    private final TruckController controller;

    public TruckEditDialog(Component parent, int truckId, TruckController controller) {
        super((Frame) SwingUtilities.getWindowAncestor(parent), "Editar Camion", true);
        this.truckId = truckId;
        this.controller = controller;

        setSize(600, 500);
        setMinimumSize(new Dimension(500, 400));
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

        this.formCamion = new VentanaCamion("Actualizar camion","Ingrese los nuevos datos");
        panelForm.add(formCamion, BorderLayout.CENTER);
    }

    public void loadTruckData() {

        controller.loadTruckForEdit(truckId, formCamion);
    }

    private void onOK() {
        controller.makeTruckUpdate(truckId, formCamion);
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}
