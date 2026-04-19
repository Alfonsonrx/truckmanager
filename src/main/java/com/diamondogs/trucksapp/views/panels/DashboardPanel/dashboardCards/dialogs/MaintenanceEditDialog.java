package com.diamondogs.trucksapp.views.panels.DashboardPanel.dashboardCards.dialogs;

import com.diamondogs.trucksapp.views.panels.DashboardPanel.dashboardCards.forms.FormMantenimiento;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MaintenanceEditDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JPanel panelForm;
    private int maintenanceId;

    public MaintenanceEditDialog(Component parent, int maintenanceId) {
        super((Frame) SwingUtilities.getWindowAncestor(parent), "Editar Mantenimiento", true);
        this.maintenanceId = maintenanceId;

        setSize(600, 300);
        setMinimumSize(new Dimension(500, 250));
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

        FormMantenimiento formMantenimiento = new FormMantenimiento("Actualizar mantenimiento","Ingrese los nuevos datos");
        panelForm.add(formMantenimiento.getRootPanel(), BorderLayout.CENTER);
    }

    public int getMaintenanceId() {
        return this.maintenanceId;
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
