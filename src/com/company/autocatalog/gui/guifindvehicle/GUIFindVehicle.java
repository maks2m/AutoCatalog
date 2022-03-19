package com.company.autocatalog.gui.guifindvehicle;

import com.company.autocatalog.db.DBConnector;
import com.company.autocatalog.gui.guihome.TableModel;
import com.company.autocatalog.vehicle.Auto;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static com.company.autocatalog.constans.Constants.*;

public class GUIFindVehicle extends JFrame {

    private JCheckBox checkBoxBrand = new JCheckBox(NAME_BRAND);
    private JTextField textFieldBrand = new JTextField();
    private JCheckBox checkBoxModel = new JCheckBox(NAME_MODEL);
    private JTextField textFieldModel = new JTextField();
    private JCheckBox checkBoxCategory = new JCheckBox(NAME_CATEGORY);
    private JComboBox comboBoxCategory = new JComboBox(LIST_CATEGORY);
    private JCheckBox checkBoxType = new JCheckBox(NAME_TYPE);
    private JComboBox comboBoxType = new JComboBox(LIST_TYPE);
    private JCheckBox checkBoxRegistrationNumber = new JCheckBox(NAME_REGISTRATION_NUMBER);
    private JTextField textFieldRegistrationNumber = new JTextField();
    private JCheckBox checkBoxManufacturingYear = new JCheckBox(NAME_MANUFACTURING_YEAR);
    private JTextField textFieldManufacturingYear = new JTextField();
    private JCheckBox checkBoxCarTrailer1 = new JCheckBox(NAME_CAR_TRAILER);
    private JCheckBox checkBoxCarTrailer2 = new JCheckBox();
    private JButton buttonFind = new JButton("Найти");
    private JButton buttonClose = new JButton("Закрыть");

    public ArrayList<Auto> globalCars;
    private final int WIDTH_WINDOW = 500;
    private final int HEIGHT_WINDOW = 300;

    public GUIFindVehicle(ArrayList newGlobalCars){
        super("Найти ТС");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setBounds(200, 200, WIDTH_WINDOW, HEIGHT_WINDOW);
        this.setResizable(true);

        this.globalCars = newGlobalCars;

        getRootPane().setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        Container container = this.getContentPane();
        fillingContainer(container);
        container.add(buttonFind);
        buttonFind.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Auto newAuto;
                newAuto = fillingAuto(container);
                Auto auto = new Auto();
                if (newAuto != null) {

                    String sqlQuery = "SELECT * FROM vehicle WHERE ";
                    if (checkBoxBrand.isSelected()) sqlQuery += "brand = '" + newAuto.getBrand() + "' AND ";
                    if (checkBoxModel.isSelected()) sqlQuery += "model = '" + newAuto.getModel() + "' AND ";
                    if (checkBoxCategory.isSelected()) sqlQuery += "category = '" + newAuto.getCategory() + "' AND ";
                    if (checkBoxType.isSelected()) sqlQuery += "type = '" + newAuto.getType() + "' AND ";
                    if (checkBoxRegistrationNumber.isSelected()) sqlQuery += "registrationNumber = '" + newAuto.getRegistrationNumber() + "' AND ";
                    if (checkBoxManufacturingYear.isSelected()) sqlQuery += "manufacturingYear = " + newAuto.getManufacturingYear() + " AND ";
                    if (checkBoxCarTrailer1.isSelected()) sqlQuery += "carTrailer = " + newAuto.isCarTrailer() + " AND ";
                    sqlQuery = sqlQuery.substring(0, sqlQuery.length()-5);

                    DBConnector dbConnector = new DBConnector();
                    ArrayList<Auto> Cars;
                    Cars = dbConnector.getTableFromDB(sqlQuery);
                    if (Cars.size() != 0) {
                        String massage = "По запросу:\n\n" +
                                (checkBoxBrand.isSelected() ? NAME_BRAND + ": " + newAuto.getBrand() + "\n" : "") +
                                (checkBoxModel.isSelected() ? NAME_MODEL + ": " + newAuto.getModel() + "\n" : "") +
                                (checkBoxCategory.isSelected() ? NAME_CATEGORY + ": " + newAuto.getCategory() + "\n" : "") +
                                (checkBoxType.isSelected() ? NAME_TYPE + ": " + newAuto.getType() + "\n" : "") +
                                (checkBoxRegistrationNumber.isSelected() ? NAME_REGISTRATION_NUMBER + ": " + newAuto.getRegistrationNumber() + "\n" : "") +
                                (checkBoxManufacturingYear.isSelected() ? NAME_MANUFACTURING_YEAR + ": " + newAuto.getManufacturingYear() + "\n" : "");
                        String tmp = "";
                        if (checkBoxCarTrailer1.isSelected()){
                            if(checkBoxCarTrailer2.isSelected()){
                                tmp = NAME_CAR_TRAILER + ": " + "есть\n";
                            } else {
                                tmp = NAME_CAR_TRAILER + ": " + "нет\n";
                            }
                        }
                        massage += tmp + "найдено строк: " + Cars.size() + "\n\n" +
                                "Показать результаты поска в общей таблице?";
                        int i = JOptionPane.showConfirmDialog(null, massage, "Внимание!", JOptionPane.YES_NO_CANCEL_OPTION);
                        switch (i) {
                            case 0:
                                globalCars = Cars;
                                dispose();
                                break;
                            case 1:
                                break;
                        }
                    } else {
                        JOptionPane.showMessageDialog(container, "Поиск не дал результатов!");
                    }
                }
            }
        });
    }

    private void fillingContainer(Container container){
        container.setLayout(new GridLayout(8, 2, 5, 5));
        container.add(checkBoxBrand);
        container.add(textFieldBrand);
        container.add(checkBoxModel);
        container.add(textFieldModel);
        container.add(checkBoxCategory);
        container.add(comboBoxCategory);
        container.add(checkBoxRegistrationNumber);
        container.add(textFieldRegistrationNumber);
        container.add(checkBoxType);
        container.add(comboBoxType);
        container.add(checkBoxManufacturingYear);
        container.add(textFieldManufacturingYear);
        container.add(checkBoxCarTrailer1);
        container.add(checkBoxCarTrailer2);
        container.add(buttonClose);
        buttonClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //System.out.println("buttonClose test");
                dispose();
            }
        });
    }

    private Auto fillingAuto(Container container){

        Auto auto = new Auto();
        if (checkBoxBrand.isSelected()) auto.setBrand(textFieldBrand.getText());
        if (checkBoxModel.isSelected()) auto.setModel(textFieldModel.getText());
        if (checkBoxCategory.isSelected()) auto.setCategory((String)comboBoxCategory.getSelectedItem());
        if (checkBoxType.isSelected()) auto.setType((String)comboBoxType.getSelectedItem());
        if (checkBoxRegistrationNumber.isSelected()) auto.setRegistrationNumber(textFieldRegistrationNumber.getText());
        if (checkBoxManufacturingYear.isSelected()) {
            if (textFieldManufacturingYear.getText().matches("(19|20)\\d\\d")) {
                auto.setManufacturingYear(Integer.parseInt(textFieldManufacturingYear.getText()));
            } else {
                JOptionPane.showMessageDialog(container, "Ошибка при вводе года!");
                return null;
                //System.out.println("Error");
            }
        }
        if (checkBoxCarTrailer1.isSelected()) auto.setCarTrailer(checkBoxCarTrailer2.isSelected());
        return auto;
    }

}
