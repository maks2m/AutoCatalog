package com.company.autocatalog.gui.guihome;

import com.company.autocatalog.db.DBConnector;
import com.company.autocatalog.gui.guifindvehicle.GUIFindVehicle;
import com.company.autocatalog.gui.guivehicle.GUIVehicle;
import com.company.autocatalog.vehicle.Auto;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import static com.company.autocatalog.constans.Constants.*;

public class GUIHome extends JFrame {

    public ArrayList<Auto> globalCars;
    private Auto selectionAutoInTable = new Auto();

    public GUIHome () {

        super("Auto Catalog");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(100, 100, 1630, 700);

        Container container = this.getContentPane();
        container.setLayout(new GridBagLayout());

        ArrayList<Auto> Cars;
        DBConnector dBconnector = new DBConnector();
        Cars = dBconnector.getTableFromDB("SELECT * FROM vehicle");
        TableModel tableModel = new TableModel(Cars);
        JTable table1 = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table1);
        scrollPane.setPreferredSize( new Dimension(1600, 600));


        ComboBoxModel comboBoxModelBrand = new ComboBoxModel();
        JComboBox comboBoxBrand = new JComboBox(comboBoxModelBrand);
        comboBoxModelBrand.setArrayList(dBconnector.getColumnFromDB("brand"));

        ComboBoxModel comboBoxModelModel = new ComboBoxModel();
        JComboBox comboBoxModel = new JComboBox(comboBoxModelModel);
        comboBoxModelModel.setArrayList(dBconnector.getColumnFromDB("model"));

        ComboBoxModel comboBoxModelCategory = new ComboBoxModel();
        JComboBox comboBoxCategory = new JComboBox(comboBoxModelCategory);
        comboBoxModelCategory.setArrayList(dBconnector.getColumnFromDB("category"));

        ComboBoxModel comboBoxModelType = new ComboBoxModel();
        JComboBox comboBoxType = new JComboBox(comboBoxModelType);
        comboBoxModelType.setArrayList(dBconnector.getColumnFromDB("type"));

        ComboBoxModel comboBoxModelRegistrationNumber = new ComboBoxModel();
        JComboBox comboBoxRegistrationNumber = new JComboBox(comboBoxModelRegistrationNumber);
        comboBoxModelRegistrationNumber.setArrayList(dBconnector.getColumnFromDB("registrationNumber"));

        ComboBoxModel comboBoxModelManufacturingYear = new ComboBoxModel();
        JComboBox comboBoxManufacturingYear = new JComboBox(comboBoxModelManufacturingYear);
        comboBoxModelManufacturingYear.setArrayList(dBconnector.getColumnFromDB("manufacturingYear"));

        ListSelectionModel listSelectionModel = table1.getSelectionModel();
        ArrayList<Auto> finalCars = Cars;
        listSelectionModel.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                int tmp = table1.convertRowIndexToModel(table1.getSelectedRow());
                selectionAutoInTable = finalCars.get(tmp);
            }
        });

        container.add(scrollPane, new GridBagConstraints(0, 0, GridBagConstraints.REMAINDER,
                1, 1, 1.0f,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(5, 5, 5, 5), 0, 0));


        JLabel labelBrand = new JLabel(NAME_BRAND);
        container.add(labelBrand, new GridBagConstraints(0, 1, 1, 1, 0.1f,
                0.005f, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(5, 5, 5, 5), 0, 0));

        container.add(comboBoxBrand, new GridBagConstraints(0, 2, 1, 1, 0.1f,
                0.005f, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(5, 5, 5, 5), 0, 0));
        comboBoxBrand.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Auto> tmp = dBconnector.getTableFromDB("SELECT * FROM vehicle " +
                        "WHERE brand = '" + (String)comboBoxBrand.getSelectedItem() + "'");
                globalCars = tmp;
                TableModel tableModel = new TableModel(globalCars);
                table1.setModel(tableModel);
            }
        });


        JLabel labelModel = new JLabel(NAME_MODEL);
        container.add(labelModel, new GridBagConstraints(1, 1, 1, 1, 0.1f,
                0.005f, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(5, 5, 5, 5), 0, 0));

        container.add(comboBoxModel, new GridBagConstraints(1, 2, 1, 1, 0.1f,
                0.005f, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(5, 5, 5, 5), 0, 0));
        comboBoxModel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Auto> tmp = dBconnector.getTableFromDB("SELECT * FROM vehicle " +
                        "WHERE model = '" + (String)comboBoxModel.getSelectedItem() + "'");
                globalCars = tmp;
                TableModel tableModel = new TableModel(globalCars);
                table1.setModel(tableModel);
                //System.out.println(tmp.size());

            }
        });


        JLabel labelCategory = new JLabel(NAME_CATEGORY);
        container.add(labelCategory, new GridBagConstraints(2, 1, 1, 1, 0.1f,
                0.005f, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(5, 5, 5, 5), 0, 0));

        container.add(comboBoxCategory, new GridBagConstraints(2, 2, 1, 1, 0.1f,
                0.005f, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(5, 5, 5, 5), 0, 0));
        comboBoxCategory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Auto> tmp = dBconnector.getTableFromDB("SELECT * FROM vehicle " +
                        "WHERE category = '" + (String)comboBoxCategory.getSelectedItem() + "'");
                globalCars = tmp;
                TableModel tableModel = new TableModel(globalCars);
                table1.setModel(tableModel);
            }
        });


        JLabel labelType = new JLabel(NAME_TYPE);
        container.add(labelType, new GridBagConstraints(3, 1, 1, 1, 0.1f,
                0.005f, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(5, 5, 5, 5), 0, 0));

        container.add(comboBoxType, new GridBagConstraints(3, 2, 1, 1, 0.1f,
                0.005f, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(5, 5, 5, 5), 0, 0));
        comboBoxType.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Auto> tmp = dBconnector.getTableFromDB("SELECT * FROM vehicle " +
                        "WHERE type = '" + (String)comboBoxType.getSelectedItem() + "'");
                globalCars = tmp;
                TableModel tableModel = new TableModel(globalCars);
                table1.setModel(tableModel);
            }
        });


        JLabel labelRegistrationNumber = new JLabel(NAME_REGISTRATION_NUMBER);
        container.add(labelRegistrationNumber, new GridBagConstraints(4, 1, 1, 1, 0.1f,
                0.005f, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(5, 5, 5, 5), 0, 0));

        container.add(comboBoxRegistrationNumber, new GridBagConstraints(4, 2, 1, 1, 0.1f,
                0.005f, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(5, 5, 5, 5), 0, 0));
        comboBoxRegistrationNumber.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Auto> tmp = dBconnector.getTableFromDB("SELECT * FROM vehicle " +
                        "WHERE registrationNumber = '" + (String)comboBoxRegistrationNumber.getSelectedItem() + "'");
                globalCars = tmp;
                TableModel tableModel = new TableModel(globalCars);
                table1.setModel(tableModel);
            }
        });


        JLabel labelManufacturingYear = new JLabel(NAME_MANUFACTURING_YEAR);
        container.add(labelManufacturingYear, new GridBagConstraints(5, 1, 1, 1, 0.1f,
                0.005f, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(5, 5, 5, 5), 0, 0));

        container.add(comboBoxManufacturingYear, new GridBagConstraints(5, 2, 1, 1, 0.1f,
                0.005f, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(5, 5, 5, 5), 0, 0));
        comboBoxManufacturingYear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Auto> tmp = dBconnector.getTableFromDB("SELECT * FROM vehicle " +
                        "WHERE manufacturingYear = '" + (String)comboBoxManufacturingYear.getSelectedItem() + "'");
                globalCars = tmp;
                TableModel tableModel = new TableModel(globalCars);
                table1.setModel(tableModel);
            }
        });

        JButton buttonUpdateTableComboBox = new JButton("Обновить данные");
        container.add(buttonUpdateTableComboBox, new GridBagConstraints(6, 1, 1, 1, 0.1f,
                0.005f, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(5, 5, 5, 5), 0, 0));
        buttonUpdateTableComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //globalCars = dBconnector.getTableFromDB("SELECT * FROM vehicle");
                TableModel tableModel = new TableModel(globalCars);
                table1.setModel(tableModel);
            }
        });

        JButton buttonFindVehicle = new JButton("Найти ТС");
        container.add(buttonFindVehicle, new GridBagConstraints(6, 2, 1, 1, 0.1f,
                0.005f, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(5, 5, 5, 5), 0, 0));
        buttonFindVehicle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Auto auto = new Auto();
                //auto.findVehicle();
                GUIFindVehicle guiFindVehicle = new GUIFindVehicle(globalCars);
                guiFindVehicle.setVisible(true);
            }
        });


        JButton buttonCreateVehicle = new JButton("Добавить ТС");
        container.add(buttonCreateVehicle, new GridBagConstraints(7, 2, 1, 1, 0.1f,
                0.005f, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(5, 5, 5, 5), 0, 0));
        buttonCreateVehicle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GUIVehicle guiCreateVehicle = new GUIVehicle();
                guiCreateVehicle.setVisible(true);
            }
        });

        JButton buttonEditVehicle = new JButton("Изменить ТС");
        container.add(buttonEditVehicle, new GridBagConstraints(8, 2, 1, 1, 0.1f,
                0.005f, GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(5, 5, 5, 5), 0, 0));
        buttonEditVehicle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GUIVehicle guiEditVehicle = new GUIVehicle(selectionAutoInTable);
                guiEditVehicle.setVisible(true);
            }
        });

        container.validate();
        setVisible(true);
    }
}
