package com.diamondogs.trucksapp.views.panels.DashboardPanel.sidebar;

import java.awt.event.KeyEvent;

/**
 * Single source of truth for dashboard navigation entries.
 * <p>
 * Holds the card key used with {@link java.awt.CardLayout}, the user-facing
 * label (Spanish) and the mnemonic key for keyboard activation.
 */
public enum NavItem {
    USERS("users", "Usuarios", KeyEvent.VK_U),
    TRUCKS("trucks", "Camiones", KeyEvent.VK_C),
    MAINTENANCES("maintenances", "Mantenimientos", KeyEvent.VK_M);

    private final String cardKey;
    private final String label;
    private final int mnemonic;

    NavItem(String cardKey, String label, int mnemonic) {
        this.cardKey = cardKey;
        this.label = label;
        this.mnemonic = mnemonic;
    }

    public String getCardKey() {
        return cardKey;
    }

    public String getLabel() {
        return label;
    }

    public int getMnemonic() {
        return mnemonic;
    }
}
