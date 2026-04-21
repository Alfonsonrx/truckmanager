package com.diamondogs.trucksapp.views.panels.DashboardPanel.utils;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class DynamicStateButtonRenderer extends JButton implements TableCellRenderer {
    private final String statusColumnName;
    public DynamicStateButtonRenderer(String statusColumnName) {
        this.statusColumnName = statusColumnName;
        setFocusPainted(false);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus, int row, int column) {

        String isActive = (String) table.getValueAt(row, table.getColumn("Habilitado?").getModelIndex()); // Asume que "is_active" está en columna 4 (ajusta si es diferente)

        if ("Si".equalsIgnoreCase(isActive) || "1".equals(isActive)) {
            setText("Inhabilitar");
            setForeground(Color.RED);
        } else {
            setText("Habilitar");
            setForeground(new Color(0, 150, 0));
        }
        return this;
    }
}
