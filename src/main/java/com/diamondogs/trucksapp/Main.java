package com.diamondogs.trucksapp;

import com.diamondogs.trucksapp.controller.TruckController;
import com.diamondogs.trucksapp.views.Mainframe;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());   // or FlatDarkLaf()
            // Alternative shortcut:
            // FlatLightLaf.setup();
        } catch (Exception ex) {
            System.err.println("Failed to initialize FlatLaf");
            ex.printStackTrace();
        }
        SwingUtilities.invokeLater(()-> {
            new Mainframe().setVisible(true);
        });
    }
}
