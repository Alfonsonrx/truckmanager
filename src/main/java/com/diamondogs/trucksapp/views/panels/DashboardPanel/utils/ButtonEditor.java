package com.diamondogs.trucksapp.views.panels.DashboardPanel.utils;
import com.formdev.flatlaf.FlatClientProperties;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.util.EventObject;
import java.util.function.Consumer;

public class ButtonEditor extends AbstractCellEditor implements TableCellEditor {
    private final JButton button;
    private int currentRow;
    private final Consumer<Integer> action;   // What to do when button is clicked

    public ButtonEditor(String text, Consumer<Integer> action) {
        this.action = action;
        button = new JButton(text);
        button.addActionListener(e -> {
            int row = currentRow;
            fireEditingStopped();// Stop editing FIRST
            SwingUtilities.invokeLater(() -> {
                action.accept(row);   // Then open dialog
            });
        });
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
                                                 boolean isSelected, int row, int column) {
        currentRow = row;
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        return button.getText();
    }

    @Override
    public boolean isCellEditable(EventObject e) {
        return true;
    }
}
