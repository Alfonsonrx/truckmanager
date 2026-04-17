package com.diamondogs.trucksapp.views.panels.DashboardPanel.utils;
import com.formdev.flatlaf.FlatClientProperties;
import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class ButtonRenderer extends JButton implements TableCellRenderer {

    public ButtonRenderer(String text) {
        super(text);

        // FlatLaf styling for better look and consistency
        putClientProperty(FlatClientProperties.STYLE, "arc: 0; "+
                "focusWidth: 0; " +
                "borderWidth: 1");   // rounded corners

        setFocusPainted(false);
        setMargin(new Insets(2, 8, 2, 8));   // Reduce internal padding
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus, int row, int column) {

        // Optional: change appearance when row is selected
        if (isSelected) {
            setBackground(table.getSelectionBackground());
            setForeground(table.getSelectionForeground());
        } else {
            setBackground(null);
            setForeground(null);
        }
        return this;
    }
}