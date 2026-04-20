package com.diamondogs.trucksapp.views.panels.DashboardPanel.sidebar;

import com.diamondogs.trucksapp.theme.ThemeManager;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.util.EnumMap;
import java.util.Map;
import java.util.function.Consumer;

/**
 * Vertical navigation sidebar for the dashboard.
 * <p>
 * Builds one {@link javax.swing.JToggleButton} per {@link NavItem}, wired
 * through a shared {@link ButtonGroup} so the current selection is always
 * mutually exclusive. The caller supplies navigation and logout callbacks;
 * the sidebar never knows about {@code DashboardPanel} or
 * {@code AppNavigator} directly.
 */
public class Sidebar extends JPanel {

    private static final int SIDEBAR_WIDTH = 180;
    private static final int BUTTON_HEIGHT = 36;

    private final Map<NavItem, javax.swing.JToggleButton> toggles = new EnumMap<>(NavItem.class);
    private final ButtonGroup buttonGroup = new ButtonGroup();
    private final JButton themeToggleButton;
    private final Consumer<ThemeManager.Mode> themeListener;

    public Sidebar(Consumer<NavItem> onSelect, Runnable onLogout) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(SIDEBAR_WIDTH, 0));
        setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));

        add(buildHeader());
        add(Box.createVerticalStrut(8));
        add(buildSeparator());
        add(Box.createVerticalStrut(12));

        // TODO: wrap the toggle stack in a JScrollPane when nav items grow
        // beyond what fits vertically.
        for (NavItem item : NavItem.values()) {
            javax.swing.JToggleButton toggle = buildToggle(item, onSelect);
            toggles.put(item, toggle);
            buttonGroup.add(toggle);
            add(toggle);
            add(Box.createVerticalStrut(6));
        }

        add(Box.createVerticalGlue());

        themeToggleButton = buildThemeToggleButton();
        add(themeToggleButton);
        add(Box.createVerticalStrut(6));

        add(buildLogoutButton(onLogout));

        // Keep the theme-toggle label in sync with the active mode. The
        // listener is removed automatically when the sidebar is detached
        // from the UI hierarchy to avoid leaks.
        themeListener = mode -> updateThemeToggleLabel();
        ThemeManager.addListener(themeListener);
    }

    @Override
    public void removeNotify() {
        ThemeManager.removeListener(themeListener);
        super.removeNotify();
    }

    /**
     * Visually marks {@code item} as the active entry. Safe to call for
     * programmatic navigation (e.g. the initial card selection).
     */
    public void setActive(NavItem item) {
        javax.swing.JToggleButton toggle = toggles.get(item);
        if (toggle != null) {
            toggle.setSelected(true);
        }
    }

    private JLabel buildHeader() {
        JLabel title = new JLabel("Truck Manager");
        title.setFont(title.getFont().deriveFont(Font.BOLD, 16f));
        title.setAlignmentX(Component.LEFT_ALIGNMENT);
        title.setBorder(BorderFactory.createEmptyBorder(0, 0, 6, 0));
        return title;
    }

    private JSeparator buildSeparator() {
        JSeparator separator = new JSeparator(SwingConstants.HORIZONTAL);
        separator.setAlignmentX(Component.LEFT_ALIGNMENT);
        // BoxLayout needs a bounded max height for the separator to render
        // at its preferred 2px height instead of stretching.
        separator.setMaximumSize(new Dimension(Integer.MAX_VALUE, 2));
        return separator;
    }

    private javax.swing.JToggleButton buildToggle(NavItem item, Consumer<NavItem> onSelect) {
        javax.swing.JToggleButton toggle = new javax.swing.JToggleButton(item.getLabel());
        toggle.setHorizontalAlignment(SwingConstants.LEFT);
        toggle.setAlignmentX(Component.LEFT_ALIGNMENT);
        toggle.setMaximumSize(new Dimension(Integer.MAX_VALUE, BUTTON_HEIGHT));
        toggle.setMnemonic(item.getMnemonic());
        toggle.setToolTipText(item.getLabel());
        toggle.setFocusPainted(false);
        toggle.addActionListener(e -> onSelect.accept(item));
        return toggle;
    }

    private JButton buildThemeToggleButton() {
        JButton button = new JButton();
        button.setHorizontalAlignment(SwingConstants.CENTER);
        button.setAlignmentX(Component.LEFT_ALIGNMENT);
        button.setMaximumSize(new Dimension(Integer.MAX_VALUE, BUTTON_HEIGHT));
        button.setFocusPainted(false);
        button.setToolTipText("Alternar entre tema claro y oscuro");
        button.addActionListener(e -> ThemeManager.toggle());
        applyThemeToggleLabel(button, ThemeManager.getMode());
        return button;
    }

    private void updateThemeToggleLabel() {
        applyThemeToggleLabel(themeToggleButton, ThemeManager.getMode());
    }

    private static void applyThemeToggleLabel(JButton button, ThemeManager.Mode mode) {
        // Label reflects the action the button will perform (target mode).
        if (mode == ThemeManager.Mode.DARK) {
            button.setText("Tema claro");
        } else {
            button.setText("Tema oscuro");
        }
    }

    private JButton buildLogoutButton(Runnable onLogout) {
        JButton logout = new JButton("Cerrar sesión");
        logout.setHorizontalAlignment(SwingConstants.CENTER);
        logout.setAlignmentX(Component.LEFT_ALIGNMENT);
        logout.setMaximumSize(new Dimension(Integer.MAX_VALUE, BUTTON_HEIGHT));
        logout.setFocusPainted(false);
        logout.addActionListener(e -> onLogout.run());
        return logout;
    }
}
