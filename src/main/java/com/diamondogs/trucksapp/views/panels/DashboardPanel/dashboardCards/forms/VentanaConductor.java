package com.diamondogs.trucksapp.views.panels.DashboardPanel.dashboardCards.forms;
import com.diamondogs.trucksapp.controller.UserController;
import com.diamondogs.trucksapp.model.User;
import javax.swing.*;
import java.awt.*;

public class VentanaConductor extends JPanel {
    private JLabel lblTitulo;


    private JLabel lblnombreCompleto;
    private JLabel lblrol;
    private JLabel lbltelefono;
    private JLabel lblcontraseña;
    private JLabel lblusername;


    private JTextField txtnombreCompleto;
    private JTextField txtrol;
    private JTextField txttelefono;
    private JTextField txtcontraeña;
    private JTextField txtusername;

    private JButton btnGuardar;
    private JButton btnVolver;

    private JPanel panelTitulo;
    private JPanel panelFormulario;
    private JPanel panelBotones;

    public VentanaConductor(UserController controller) {
        setSize(600, 450);
        setLayout(new BorderLayout());

        panelTitulo = new JPanel();
        lblTitulo = new JLabel("REGISTRO DE CONDUCTORES");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        panelTitulo.add(lblTitulo);

        panelFormulario = new JPanel();
        panelFormulario.setLayout(new GridLayout(6, 2, 10, 10));
        panelFormulario.setBorder(BorderFactory.createTitledBorder("Ingrese los datos del Conductor"));

        lblnombreCompleto = new JLabel("Nombre Completo:");
        txtnombreCompleto = new JTextField();

        lblrol = new JLabel("Rol:");
        txtrol = new JTextField();

        lbltelefono = new JLabel("TELEFONO:");
        txttelefono = new JTextField();

        lblcontraseña = new JLabel("Contraseña");
        txtcontraeña = new JTextField();

        lblusername = new JLabel("Usuario:");
        txtusername = new JTextField();

        panelFormulario.add(lblnombreCompleto);
        panelFormulario.add(txtnombreCompleto);

        panelFormulario.add(lblrol);
        panelFormulario.add(txtrol);

        panelFormulario.add(lbltelefono);
        panelFormulario.add(txttelefono);

        panelFormulario.add(lblcontraseña);
        panelFormulario.add(txtcontraeña);

        panelFormulario.add(lblusername);
        panelFormulario.add(txtusername);

        btnGuardar = new JButton("Guardar");
//        btnVolver=new JButton("Volver");

        panelBotones = new JPanel();
        panelBotones.add(btnGuardar);
//        panelBotones.add(btnVolver);

        add(panelTitulo, BorderLayout.NORTH);
        add(panelFormulario, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        btnGuardar.addActionListener(e -> {
            User newUser = new User();
            newUser.setUsername(getUsername());
            newUser.setName(getNombreCompleto());
            newUser.setRole(getRol());
            newUser.setPhone(getTelefono());
            
            controller.saveUser(newUser, getPassword());
            
            txtusername.setText("");
            txtcontraeña.setText("");
            txtrol.setText("");
            txtnombreCompleto.setText("");
            txttelefono.setText("");
        });
        
        //btnVolver.addActionListener(e -> dispose());
        setVisible(true);
    }
    public String getUsername() {return txtusername.getText();}
    public String getPassword() {return txtcontraeña.getText();}
    public String getRol() {return txtrol.getText();}
    public String getNombreCompleto() {return txtnombreCompleto.getText();}
    public String getTelefono() {return txttelefono.getText();}


}



