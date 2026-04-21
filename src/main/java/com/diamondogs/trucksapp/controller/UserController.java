package com.diamondogs.trucksapp.controller;

import com.diamondogs.trucksapp.model.User;
import com.diamondogs.trucksapp.repositories.UserRepository;
import com.diamondogs.trucksapp.views.panels.DashboardPanel.dashboardCards.UsersPanel;
import com.diamondogs.trucksapp.views.panels.DashboardPanel.dashboardCards.forms.VentanaConductor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class UserController implements ActionListener {

    private final VentanaConductor vistaUsuario;
    private final UserRepository repositorio;
    private final UsersPanel vista;

    // Constructor que RECIBE la vista y activa el botón
    public UserController(VentanaConductor vistaUsuario, UsersPanel vista) {
        this.vistaUsuario = vistaUsuario;
        this.repositorio = new UserRepository();
        this.vista = vista;

        if (this.vistaUsuario.getBtnGuardar() != null) {
            this.vistaUsuario.getBtnGuardar().addActionListener(this);
            System.out.println("Controlador: Botón de guardado vinculado correctamente.");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vistaUsuario.getBtnGuardar()) {
            procesarGuardadoUsuario();
        }
    }

    private void procesarGuardadoUsuario() {
        try {
            // Extraer datos de la vista
            String username = vistaUsuario.getUsername();
            String nombre = vistaUsuario.getNombreCompleto();
            String rol = vistaUsuario.getRol();
            String telefono = vistaUsuario.getTelefono();
            String clave = vistaUsuario.getClave();

            // Crear objeto Modelo
            User nuevo = new User(0, nombre, username, rol, telefono, clave);

            // Guardar
            boolean exito = repositorio.save(nuevo);

            if (exito) {
                JOptionPane.showMessageDialog(vistaUsuario, "¡Guardado en MySQL!");
                loadAndShowUsers(); // Refresh the table
                vistaUsuario.clearForm();
            } else {
                JOptionPane.showMessageDialog(vistaUsuario, "Error al guardar. Revisa la consola.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vistaUsuario, "Datos inválidos: " + ex.getMessage());
        }
    }

    public void loadAndShowUsers() {
        SwingWorker<List<User>, Void> worker = new SwingWorker<>() {
            @Override
            protected List<User> doInBackground() {
                return repositorio.findALl();
            }

            @Override
            protected void done() {
                try {
                    List<User> users = get();
                    vista.updateTable(users);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "Failed to load users!");
                }
            }
        };
        worker.execute();
    }

    public void loadUserForEdit(int userId, VentanaConductor formUsuario) {
        User user = repositorio.findOneUser(userId);
        if (user != null) {
            formUsuario.fillForm(user);
        }
    }

    public void makeUserUpdate(int userId, VentanaConductor formUsuario) {
        try {
            String username = formUsuario.getUsername();
            String nombre = formUsuario.getNombreCompleto();
            String rol = formUsuario.getRol();
            String telefono = formUsuario.getTelefono();
            String clave = formUsuario.getClave();

            User editado = new User(userId, nombre, username, rol, telefono, clave);

            // Guardar
            boolean exito = repositorio.update(editado, userId);

            if (exito) {
                JOptionPane.showMessageDialog(vistaUsuario, String.format("¡Guardado la version nueva del usuario %d con username %s en MySQL!", userId, username));
                loadAndShowUsers(); // Refresh the table
            } else {
                JOptionPane.showMessageDialog(vistaUsuario, "Error al guardar. Revisa la consola.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(formUsuario, "Error: " + ex.getMessage());
        }
    }

    public void processDisableUser(int is_active, int userId) {
        try {
            // Eliminar
            boolean exito = repositorio.disable_user(is_active, userId);

            if (exito) {
                JOptionPane.showMessageDialog(vista, String.format("¡%s usuario Nro. %d en MySQL!",is_active == 1 ? "Habilitado" :"Inhabilitado",userId));
                loadAndShowUsers(); // Refresh the table
            } else {
                JOptionPane.showMessageDialog(vista, "Error al deshabilitar. Revisa la consola.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, "Error: " + ex.getMessage());
        }
    }
}
