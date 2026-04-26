package com.diamondogs.trucksapp.views.panels.DashboardPanel.utils;
import com.diamondogs.trucksapp.model.User;
import com.diamondogs.trucksapp.session.SessionManager;
import com.formdev.flatlaf.FlatClientProperties;
import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.util.function.BiPredicate;

public class ButtonRenderer extends JButton implements TableCellRenderer {
    private final String text;
    private final BiPredicate<JTable, Integer> canEditChecker;
    public ButtonRenderer(String text) {
        this(text, (table, row) -> true);

    }

    public ButtonRenderer(String text, BiPredicate<JTable, Integer> canEditChecker) {
        this.text = text;
        this.canEditChecker = canEditChecker;
        setOpaque(true);

        putClientProperty(FlatClientProperties.STYLE, "arc: 0; "+
                "focusWidth: 0; " +
                "borderWidth: 1");   // rounded corners

        setFocusPainted(false);
        setMargin(new Insets(2, 8, 2, 8));   // Reduce internal padding
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus, int row, int column) {
        setText(text);

        boolean canEdit = canEditChecker.test(table, row);
        setEnabled(canEdit);

        if (!canEdit) {
            setText("No permitido");
            setBackground(Color.LIGHT_GRAY);
            setForeground(Color.DARK_GRAY);
        } else {
            setBackground(UIManager.getColor("Button.background"));
            setForeground(UIManager.getColor("Button.foreground"));
        }

        if (isSelected) {
            setBackground(table.getSelectionBackground());
            setForeground(table.getSelectionForeground());
        }
        return this;
    }

//    private boolean isRowEditable(JTable table, int row) {
//        User current = SessionManager.getInstance().getCurrentUser();
//        if (current == null) return false;
//
//        if ("administrador".equalsIgnoreCase(current.getRole())) return true;
//
//        Object rowId = table.getValueAt(row, 2);
//        if (rowId == null) return false;
//
//        return Integer.parseInt(rowId.toString()) == current.getId();
//    }
}