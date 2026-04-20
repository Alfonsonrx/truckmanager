package com.diamondogs.trucksapp.views.panels.DashboardPanel;

import com.diamondogs.trucksapp.views.AppNavigator;
import com.diamondogs.trucksapp.views.panels.DashboardPanel.dashboardCards.MaintenancePanel;
import com.diamondogs.trucksapp.views.panels.DashboardPanel.dashboardCards.TrucksPanel;
import com.diamondogs.trucksapp.views.panels.DashboardPanel.dashboardCards.UsersPanel;
import com.diamondogs.trucksapp.views.panels.DashboardPanel.sidebar.NavItem;
import com.diamondogs.trucksapp.views.panels.DashboardPanel.sidebar.Sidebar;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.util.EnumMap;
import java.util.Map;

public class DashboardPanel extends JPanel {

    private static final String LOGIN_CARD = "login";

    private final Sidebar sidebar;
    private final JPanel contentPanel;
    private final CardLayout cardLayout = new CardLayout();

    private final UsersPanel usersPanel;
    private final TrucksPanel trucksPanel;
    private final MaintenancePanel maintenancePanel;

    public DashboardPanel(AppNavigator navigator) {
        setLayout(new BorderLayout());

        contentPanel = new JPanel(cardLayout);

        usersPanel = new UsersPanel();
        trucksPanel = new TrucksPanel();
        maintenancePanel = new MaintenancePanel();

        Map<NavItem, JPanel> cards = new EnumMap<>(NavItem.class);
        cards.put(NavItem.USERS, usersPanel.getRootPanel());
        cards.put(NavItem.TRUCKS, trucksPanel.getRootPanel());
        cards.put(NavItem.MAINTENANCES, maintenancePanel.getRootPanel());
        cards.forEach((item, panel) -> contentPanel.add(panel, item.getCardKey()));

        sidebar = new Sidebar(this::showCard, () -> navigator.showPanel(LOGIN_CARD));

        add(sidebar, BorderLayout.WEST);
        add(contentPanel, BorderLayout.CENTER);

        showCard(NavItem.USERS);
    }

    public void showCard(NavItem item) {
        cardLayout.show(contentPanel, item.getCardKey());
        sidebar.setActive(item);
    }

    /**
     * Compatibility shim: {@code Mainframe} registers the dashboard under the
     * {@code "dashboard"} card via {@code dashboardPanel.getRootPanel()}. The
     * double-root structure has been collapsed, so this simply returns
     * {@code this}.
     */
    public JPanel getRootPanel() {
        return this;
    }
}
