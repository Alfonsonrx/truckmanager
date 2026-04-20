package com.diamondogs.trucksapp.views.panels.DashboardPanel.dashboardCards.dialogs;

import com.diamondogs.trucksapp.controller.UserController;
import com.diamondogs.trucksapp.views.panels.DashboardPanel.dashboardCards.forms.VentanaConductor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class UserEditDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JPanel panelForm;

    private final VentanaConductor formConductor;
    private final int userId;
    private final UserController controller;

    public UserEditDialog(Component parent, int userId, UserController controller) {
        super((Frame) SwingUtilities.getWindowAncestor(parent), "Editar Usuario", true);
        this.userId = userId;
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

        this.formConductor = new VentanaConductor("Actualizar Usuario", "Ingrese los nuevos datos");
        panelForm.add(formConductor, BorderLayout.CENTER);
    }

    public void loadUserData() {
        controller.loadUserForEdit(userId, formConductor);
    }

    private void onOK() {
        controller.makeUserUpdate(userId, formConductor);
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }
}
