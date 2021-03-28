package org.example;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.event.*;

public class SettingsPanel extends JPanel implements TableModelListener {
    MainFrame frame;
    JButton removeBtn = new JButton("Remove");
    JSpinner componentNumber;
    JTable table;
    Object test[][] = new Object[50][5];
    String columns[] = {"Name", "X", "Y", "Height", "Width"};
    DefaultTableModel testModel = new DefaultTableModel();


    public SettingsPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }

    public void init() {
        this.setLayout(new GridBagLayout());
        componentNumber = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
        table = new JTable(testModel);
        table.getModel().addTableModelListener(this::tableChanged);
        add(removeBtn);
        add(componentNumber);
        testModel.addColumn("Name");
        testModel.addColumn("X");
        testModel.addColumn("Y");
        testModel.addColumn("Height");
        testModel.addColumn("Width");
        // setUpTable();
        add(table);
        removeBtn.addActionListener(this::remove);
    }

    //in cazul in care butonul este apasat se va sterge din DesignPanel din lista de componente
    //componenta cu respectiva valoare(din spinner) si se va face update la tabela
    public void remove(ActionEvent e) {
        frame.designPanel.removeComponent((Integer) componentNumber.getValue());
    }

    //se adauga in tabela o anumita componenta
    public void addToTable(Component component) {
        testModel.addRow(new Object[]{component.getName(),component.getY(),component.getX(), component.getHeight(), component.getWidth()});
    }

    //se elimina din tabela componenta "component"
    public void removeFromTable(Component component) {
        testModel.removeRow(frame.designPanel.components.indexOf(component));
    }

    //Se elimina din tabela componenta cu un index specific
    public void removeFromTable(int index) {
        testModel.removeRow(index);
    }

    //In cazul in care se schimba o valoare din tabela , se indentifica caseta editata si se modifica in componenta valorile
    @Override
    public void tableChanged(TableModelEvent e) {
        int row = e.getFirstRow();
        int column = e.getColumn();

        if (testModel.getColumnName(column) == "Name")
            frame.designPanel.components.get(row).setName((String) testModel.getValueAt(row, column));
        else if (testModel.getColumnName(column) == "X")
            frame.designPanel.components.get(row).setLocation(Integer.parseInt(String.valueOf(testModel.getValueAt(row, column+1))), Integer.parseInt(String.valueOf(testModel.getValueAt(row, column))));
        else if (testModel.getColumnName(column) == "Y")
            frame.designPanel.components.get(row).setLocation(Integer.parseInt(String.valueOf(testModel.getValueAt(row, column))), Integer.parseInt(String.valueOf(testModel.getValueAt(row, column-1))));
        else if (testModel.getColumnName(column) == "Height")
            frame.designPanel.components.get(row).setSize(Integer.parseInt(String.valueOf(testModel.getValueAt(row, column+1))), Integer.parseInt(String.valueOf(testModel.getValueAt(row, column))));
        else if (testModel.getColumnName(column) == "Width")
            frame.designPanel.components.get(row).setSize(Integer.parseInt(String.valueOf(testModel.getValueAt(row, column))), Integer.parseInt(String.valueOf(testModel.getValueAt(row, column-1))));

        frame.controlPanel.refresh();
    }

}
