package com.diamondogs.trucksapp.views.panels.DashboardPanel.dashboardCards.forms;

import javax.swing.*;
import java.awt.*;

public class VentanaCamion extends JPanel {
    private JLabel lblTitulo;

    private JLabel lblPatente;
    private JLabel lblMarca;
    private JLabel lblModelo;
    private JLabel lblAnio;
    private JLabel lblKilometraje;
    private JLabel lblConductor;
    private JLabel lblColor;
    private JLabel lblLatest_maintenance;

    private JTextField txtPatente;
    private JTextField txtMarca;
    private JTextField txtModelo;
    private JTextField txtAnio;
    private JTextField txtKilometraje;
    private JTextField txtConductor;
    private JTextField txtColor;
    private JTextField txtLatest_maintenance;

    private JButton btnGuardar;
    private JButton btnVolver;

    private JPanel panelTitulo;
    private JPanel panelFormulario;
    private JPanel panelBotones;

    public VentanaCamion(String title_label, String title_form) {
        setSize(600, 450);
        setLayout(new BorderLayout());

        panelTitulo = new JPanel();
        lblTitulo = new JLabel(title_label);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24));
        panelTitulo.add(lblTitulo);

        panelFormulario = new JPanel();

        panelFormulario.setLayout(new GridLayout(8, 2, 10, 10));
        panelFormulario.setBorder(BorderFactory.createTitledBorder(title_form));

        lblPatente = new JLabel("Patente:");
        txtPatente = new JTextField();

        lblMarca = new JLabel("Marca:");
        txtMarca = new JTextField();

        lblModelo = new JLabel("Modelo:");
        txtModelo = new JTextField();

        lblAnio = new JLabel("Año:");
        txtAnio = new JTextField();

        lblKilometraje = new JLabel("Kilometraje:");
        txtKilometraje = new JTextField();

        lblConductor = new JLabel("Conductor (ID):");
        txtConductor = new JTextField();

        lblColor = new JLabel("Color:");
        txtColor = new JTextField();

        lblLatest_maintenance = new JLabel("Última mantención (AAAA-MM-DD):");
        txtLatest_maintenance = new JTextField();

        panelFormulario.add(lblPatente);
        panelFormulario.add(txtPatente);

        panelFormulario.add(lblMarca);
        panelFormulario.add(txtMarca);

        panelFormulario.add(lblModelo);
        panelFormulario.add(txtModelo);

        panelFormulario.add(lblAnio);
        panelFormulario.add(txtAnio);

        panelFormulario.add(lblKilometraje);
        panelFormulario.add(txtKilometraje);

        panelFormulario.add(lblConductor);
        panelFormulario.add(txtConductor);

        panelFormulario.add(lblColor);
        panelFormulario.add(txtColor);


        panelFormulario.add(lblLatest_maintenance);
        panelFormulario.add(txtLatest_maintenance);

//        btnGuardar = new JButton("Guardar");
//
//        panelBotones = new JPanel();
//        panelBotones.add(btnGuardar);

        add(panelTitulo, BorderLayout.NORTH);
        add(panelFormulario, BorderLayout.CENTER);
//        add(panelBotones, BorderLayout.SOUTH);

        setVisible(true);
    }

    // Getters para que el controlador acceda al botón y a los datos ingresados
//    public JButton getBtnGuardar() {
//        return btnGuardar;
//    }

    public String getPatente() {
        return txtPatente.getText();
    }

    public String getMarca() {
        return txtMarca.getText();
    }

    public String getModelo() {
        return txtModelo.getText();
    }

    public String getAnio() {
        return txtAnio.getText();
    }

    public String getKilometraje() {
        return txtKilometraje.getText();
    }

    public String getConductor() {
        return txtConductor.getText();
    }

    public String getColor() {
        return txtColor.getText();
    }

    public String getLatest_maintenance() {
        return txtLatest_maintenance.getText();
    }

    public void setPatente(String value) { txtPatente.setText(value); }
    public void setMarca(String value) { txtMarca.setText(value); }
    public void setModelo(String value) { txtModelo.setText(value); }
    public void setAnio(String value) { txtAnio.setText(value); }
    public void setKilometraje(String value) { txtKilometraje.setText(value); }
    public void setConductor(String value) { txtConductor.setText(value); }
    public void setColor(String value) { txtColor.setText(value); }
    public void setLatest_maintenance(String value) { txtLatest_maintenance.setText(value); }
}