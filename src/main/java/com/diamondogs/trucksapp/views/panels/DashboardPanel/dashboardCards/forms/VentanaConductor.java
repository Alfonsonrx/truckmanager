package com.diamondogs.trucksapp.views.panels.DashboardPanel.dashboardCards.forms;

import com.diamondogs.trucksapp.model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class VentanaConductor extends JPanel {
    private JLabel lblTitulo;

    private JLabel lblUsername;
    private JLabel lblnombreCompleto;
    private JLabel lblrol;
    private JLabel lbltelefono;
    private JLabel lblclave;

    private JTextField txtUsername;
    private JTextField txtnombreCompleto;
    private JTextField txtrol;
    private JTextField txttelefono;
    private JTextField txtclave;

    private JButton btnGuardar;

    private JPanel panelTitulo;
    private JPanel panelFormulario;
    private JPanel panelBotones;

    public VentanaConductor(String title_label, String title_form, boolean showSaveButton) {
        setSize(600, 450);
        setLayout(new BorderLayout());

        panelTitulo = new JPanel();
        lblTitulo = new JLabel(title_label);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        panelTitulo.add(lblTitulo);

        panelFormulario = new JPanel();
        panelFormulario.setLayout(new GridLayout(5, 2, 10, 10));
        panelFormulario.setBorder(BorderFactory.createTitledBorder(title_form));

        lblUsername = new JLabel("Usuario:");
        txtUsername = new JTextField();

        lblnombreCompleto = new JLabel("Nombre Completo:");
        txtnombreCompleto = new JTextField();

        lblrol = new JLabel("Rol: ");
        txtrol = new JTextField();

        lbltelefono = new JLabel("Telefono:");
        txttelefono = new JTextField();

        lblclave = new JLabel("Contraseña:");
        txtclave = new JTextField();

        panelFormulario.add(lblUsername);
        panelFormulario.add(txtUsername);

        panelFormulario.add(lblnombreCompleto);
        panelFormulario.add(txtnombreCompleto);

        panelFormulario.add(lblrol);
        panelFormulario.add(txtrol);

        panelFormulario.add(lbltelefono);
        panelFormulario.add(txttelefono);

        panelFormulario.add(lblclave);
        panelFormulario.add(txtclave);

        btnGuardar = new JButton("Guardar");

        panelBotones = new JPanel();
        panelBotones.add(btnGuardar);

        add(panelTitulo, BorderLayout.NORTH);
        add(panelFormulario, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        if (btnGuardar != null) {
            btnGuardar.setVisible(showSaveButton);
        }

        setVisible(true);
    }

    public VentanaConductor(String title, String formTitle) {
        this(title, formTitle, false); // Default = no button
    }

    public void addSaveListener(ActionListener listener) {
        if (btnGuardar != null) {
            btnGuardar.addActionListener(listener);
        }
    }

    // Getters para que el controlador acceda al botón y a los datos ingresados
    public JButton getBtnGuardar() {
        return btnGuardar;
    }

    public String getUsername() {
        return txtUsername.getText();
    }

    public String getNombreCompleto() {
        return txtnombreCompleto.getText();
    }

    public String getRol() {
        return txtrol.getText();
    }

    public String getTelefono() {
        return txttelefono.getText();
    }

    public String getClave() {
        return txtclave.getText();
    }

    public void setUsername(String value) { txtUsername.setText(value); }
    public void setNombreCompleto(String value) { txtnombreCompleto.setText(value); }
    public void setRol(String value) { txtrol.setText(value); }
    public void setTelefono(String value) { txttelefono.setText(value); }
    public void setClave(String value) { txtclave.setText(value); }

    public void clearForm() {
        txtUsername.setText("");
        txtnombreCompleto.setText("");
        txtrol.setText("");
        txttelefono.setText("");
        txtclave.setText("");
    }

    public void fillForm(User user) {
        if (user == null) return;

        txtUsername.setText(user.getUsername() != null ? user.getUsername() : "");
        txtnombreCompleto.setText(user.getName() != null ? user.getName() : "");
        txtrol.setText(user.getRole() != null ? user.getRole() : "");
        txttelefono.setText(user.getPhone() != null ? user.getPhone() : "");
        txtclave.setText(user.getPassword() != null ? user.getPassword() : "");
    }
}
