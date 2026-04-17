package com.diamondogs.trucksapp.views.panels.DashboardPanel.dashboardCards.forms;
import javax.swing.*;
import java.awt.*;

public class VentanaConductor extends JPanel {
    private JLabel lblTitulo;

    private JLabel lblnombreCompleto;
    private JLabel lblrol;
    private JLabel lbltelefono;
    private JLabel lblclave;

    private JTextField txtnombreCompleto;
    private JTextField txtrol;
    private JTextField txttelefono;
    private JTextField txtclave;

    private JButton btnGuardar;
    private JButton btnVolver;

    private JPanel panelTitulo;
    private JPanel panelFormulario;
    private JPanel panelBotones;

    public VentanaConductor(){
        setSize(600,450);
        setLayout(new BorderLayout());

        panelTitulo = new JPanel();
        lblTitulo = new JLabel("REGISTRO DE CONDUCTORES");
        lblTitulo.setFont(new Font("Arial",Font.BOLD,16));
        panelTitulo.add(lblTitulo);

        panelFormulario = new JPanel();
        panelFormulario.setLayout(new GridLayout(6,2,10,10));
        panelFormulario.setBorder(BorderFactory.createTitledBorder("Ingrese los datos del Conductor"));

        lblnombreCompleto=new JLabel("Nombre Completo:");
        txtnombreCompleto=new JTextField();

        lblrol=new JLabel("Rol: ");
        txtrol=new JTextField();

        lbltelefono=new JLabel("Telefono:");
        txttelefono=new JTextField();

        lblclave=new JLabel("Contraseña:");
        txtclave=new JTextField();

        panelFormulario.add(lblnombreCompleto);
        panelFormulario.add(txtnombreCompleto);

        panelFormulario.add(lblrol);
        panelFormulario.add(txtrol);

        panelFormulario.add(lbltelefono);
        panelFormulario.add(txttelefono);

        panelFormulario.add(lblclave);
        panelFormulario.add(txtclave);

        btnGuardar=new JButton("Guardar");
//        btnVolver=new JButton("Volver");

        panelBotones = new JPanel();
        panelBotones.add(btnGuardar);
//        panelBotones.add(btnVolver);

        add(panelTitulo,BorderLayout.NORTH);
        add(panelFormulario,BorderLayout.CENTER);
        add(panelBotones,BorderLayout.SOUTH);

        btnGuardar.addActionListener(e -> guardarConductor());
//        btnVolver.addActionListener(e -> dispose());
        setVisible(true);

}
    private void guardarConductor(){
        String nombreCompleto =txtnombreCompleto.getText();
        String Rol = txtrol.getText();
        String Telefono =txttelefono.getText();
        String contraseña = txtclave.getText();

        JOptionPane.showMessageDialog(this,
                "Conductor guardado correctamente\n" +
                        "Nombre: " + nombreCompleto + "\n" +
                        "Rol: " + Rol + "\n" +
                        "Telefono: " + Telefono + "\n" +
                        "Contraseña Agregada" );

        }

    }


