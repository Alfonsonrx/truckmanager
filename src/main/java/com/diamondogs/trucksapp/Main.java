package com.diamondogs.trucksapp;

import com.diamondogs.trucksapp.controller.TruckController;
import com.diamondogs.trucksapp.views.Mainframe;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

//        CamionDAOMemoria modeloDAO = new CamionDAOMemoria();
//        ConductorDAOMemoria modeloConductorDAO = new ConductorDAOMemoria();
//
//        VentanaRegistroCamion vista = new VentanaRegistroCamion();
//
//        TruckController controlador = new TruckController(vista, modeloDAO, modeloConductorDAO);

        SwingUtilities.invokeLater(()-> {
            new Mainframe().setVisible(true);
        });
    }
}
