package com.diamondogs.trucksapp;

import com.diamondogs.trucksapp.theme.ThemeManager;
import com.diamondogs.trucksapp.views.Mainframe;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        ThemeManager.setMode(ThemeManager.Mode.DARK);

        SwingUtilities.invokeLater(() -> new Mainframe().setVisible(true));
    }
}
