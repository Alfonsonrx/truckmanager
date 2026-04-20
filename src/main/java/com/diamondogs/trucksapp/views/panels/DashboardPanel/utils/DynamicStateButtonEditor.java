package com.diamondogs.trucksapp.views.panels.DashboardPanel.utils;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.util.function.Consumer;

public class DynamicStateButtonEditor extends AbstractCellEditor implements TableCellEditor {
    private final JButton button;
    private final String statusColumnName;
    private int currentRow;
    private final Consumer<Integer> action;

    public DynamicStateButtonEditor(String statusColumnName, Consumer<Integer> action) {
        this.statusColumnName = statusColumnName;
        this.action = action;
        button = new JButton();
        button.addActionListener(e -> {
            action.accept(currentRow);
            fireEditingStopped();
        });
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value,
                                                 boolean isSelected, int row, int column) {

        currentRow = row;
        int statusColIndex = table.getColumn(statusColumnName).getModelIndex();
        String isActive = (String) table.getValueAt(row, 4); // Ajusta el índice de la columna is_active

        if ("Si".equalsIgnoreCase(isActive) || "1".equals(isActive)) {
            button.setText("Inhabilitar");
        } else {
            button.setText("Habilitar");
        }
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        return button.getText();
    }
}
