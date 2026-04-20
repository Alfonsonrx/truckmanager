package com.diamondogs.trucksapp.theme;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.IntelliJTheme;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * Centralized light/dark theme switcher on top of FlatLaf.
 * <p>
 * Both modes are loaded from bundled FlatLaf IntelliJ themes
 * ({@code /darktheme.json} and {@code /lighttheme.json}). Swapping themes
 * applies the new Look &amp; Feel and refreshes every open Swing window via
 * {@link FlatLaf#updateUI()}.
 */
public final class ThemeManager {

    public enum Mode {
        LIGHT, DARK
    }

    private static final String DARK_THEME_RESOURCE = "/darktheme.json";
    private static final String LIGHT_THEME_RESOURCE = "/lighttheme.json";

    private static Mode current = Mode.DARK;
    private static final List<Consumer<Mode>> listeners = new ArrayList<>();

    private ThemeManager() {
        // utility class
    }

    /**
     * Applies the given mode. Safe to call from the EDT. Listeners registered
     * via {@link #addListener(Consumer)} are notified after the LaF is swapped.
     */
    public static void setMode(Mode mode) {
        if (mode == null) {
            return;
        }
        boolean applied = switch (mode) {
            case LIGHT -> applyLight();
            case DARK -> applyDark();
        };
        if (!applied) {
            return;
        }
        current = mode;
        FlatLaf.updateUI();
        notifyListeners();
    }

    public static void toggle() {
        setMode(current == Mode.DARK ? Mode.LIGHT : Mode.DARK);
    }

    public static Mode getMode() {
        return current;
    }

    public static void addListener(Consumer<Mode> listener) {
        if (listener != null) {
            listeners.add(listener);
        }
    }

    public static void removeListener(Consumer<Mode> listener) {
        listeners.remove(listener);
    }

    private static boolean applyLight() {
        try (InputStream in = ThemeManager.class.getResourceAsStream(LIGHT_THEME_RESOURCE)) {
            if (in == null) {
                System.err.println("Light theme resource not found: " + LIGHT_THEME_RESOURCE);
                return false;
            }
            IntelliJTheme.setup(in);
            return true;
        } catch (Exception ex) {
            System.err.println("Failed to apply custom light theme");
            ex.printStackTrace();
            return false;
        }
    }

    private static boolean applyDark() {
        try (InputStream in = ThemeManager.class.getResourceAsStream(DARK_THEME_RESOURCE)) {
            if (in == null) {
                System.err.println("Dark theme resource not found: " + DARK_THEME_RESOURCE);
                return false;
            }
            IntelliJTheme.setup(in);
            return true;
        } catch (Exception ex) {
            System.err.println("Failed to apply custom dark theme");
            ex.printStackTrace();
            return false;
        }
    }

    private static void notifyListeners() {
        // Snapshot to avoid ConcurrentModificationException if a listener
        // detaches itself during notification.
        for (Consumer<Mode> listener : new ArrayList<>(listeners)) {
            try {
                listener.accept(current);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
