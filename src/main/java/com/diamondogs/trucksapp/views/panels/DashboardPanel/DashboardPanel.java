package com.diamondogs.trucksapp.views.panels.DashboardPanel;

import com.diamondogs.trucksapp.session.SessionManager;
import com.diamondogs.trucksapp.views.AppNavigator;
import com.diamondogs.trucksapp.views.panels.DashboardPanel.dashboardCards.*;
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
    private final TrucksMultiPanel trucksMultiPanel;
    private final ComputersMultiPanel computersMultiPanel;

    public DashboardPanel(AppNavigator navigator) {
        setLayout(new BorderLayout());

        contentPanel = new JPanel(cardLayout);

        usersPanel = new UsersPanel();
        trucksMultiPanel = new TrucksMultiPanel();
        computersMultiPanel = new ComputersMultiPanel();

        Map<NavItem, JPanel> cards = new EnumMap<>(NavItem.class);
        cards.put(NavItem.USERS, usersPanel.getRootPanel());
        cards.put(NavItem.TRUCKS, trucksMultiPanel.getRootPanel());
        cards.put(NavItem.COMPUTERS, computersMultiPanel.getRootPanel());


        cards.forEach((item, panel) -> contentPanel.add(panel, item.getCardKey()));

        sidebar = new Sidebar(this::showCard, () -> {
            SessionManager.getInstance().clear();
            navigator.showPanel(LOGIN_CARD);
        });

        add(sidebar, BorderLayout.WEST);
        add(contentPanel, BorderLayout.CENTER);

        showCard(NavItem.USERS);
    }

    public void showCard(NavItem item) {
        cardLayout.show(contentPanel, item.getCardKey());
        sidebar.setActive(item);
    }

    public JPanel getRootPanel() {
        return this;
    }
}
