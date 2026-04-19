package com.diamondogs.trucksapp.views.panels.DashboardPanel.dashboardCards.dialogs;

import com.diamondogs.trucksapp.views.panels.DashboardPanel.dashboardCards.forms.VentanaCamion;
import com.diamondogs.trucksapp.views.panels.DashboardPanel.dashboardCards.forms.VentanaConductor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class UserEditDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JPanel panelForm;
    private int userId;

    public UserEditDialog(Component parent, int userId) {
        super((Frame) SwingUtilities.getWindowAncestor(parent), "Editar Usuario", true);
        this.userId = userId;

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

        VentanaConductor formUsuario = new VentanaConductor("REGISTRO DE CONDUCTORES", "Ingrese los datos del Conductor");
        panelForm.add(formUsuario, BorderLayout.CENTER);
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
