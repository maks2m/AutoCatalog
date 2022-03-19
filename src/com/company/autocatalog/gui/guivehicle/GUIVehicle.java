package com.company.autocatalog.gui.guivehicle;

import com.company.autocatalog.db.DBConnector;
import com.company.autocatalog.vehicle.Auto;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import static com.company.autocatalog.constans.Constants.*;

public class GUIVehicle extends JFrame{

    private JLabel labelBrand = new JLabel("Марка");
    private JTextField textFieldBrand = new JTextField();
    private JLabel labelModel = new JLabel("Модель");
    private JTextField textFieldModel = new JTextField();
    private JLabel labelCategory = new JLabel("Категория ТС");
    private JComboBox comboBoxCategory = new JComboBox(LIST_CATEGORY);
    private JLabel labelRegistrationNumber = new JLabel("Гос. номер");
    private JTextField textFieldRegistrationNumber = new JTextField();
    private JLabel labelType = new JLabel("Тип ТС");
    private JComboBox comboBoxType = new JComboBox(LIST_TYPE);
    private JLabel labelManufacturingYear = new JLabel("Год выпуска");
    private JTextField textFieldManufacturingYear = new JTextField();
    private JLabel labelCarTrailer = new JLabel("Наличие прицепа");
    private JCheckBox checkBoxCarTrailer = new JCheckBox();
    private JButton buttonSave = new JButton("Сохранить");
    private JButton buttonFind = new JButton("Найти");
    private JButton buttonClose = new JButton("Закрыть");

    private Auto auto = new Auto();

    private final int WIDTH_WINDOW = 500;
    private final int HEIGHT_WINDOW = 300;

    public GUIVehicle(){

        super("Добавить ТС");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setBounds(200, 200, WIDTH_WINDOW, HEIGHT_WINDOW);
        this.setResizable(true);

        getRootPane().setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        Container container = this.getContentPane();
        fillingContainer(container);
        container.add(buttonSave);
        buttonSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //System.out.println("buttonSave test");
                Auto newAuto;
                newAuto = fillingAuto(container);
                if (newAuto != null) {
                    String massage = "Вы действительно хотите добавить:\n" + newAuto.getBrand() + "\n" + newAuto.getModel() + "\n" + newAuto.getCategory()
                            + "\n" + newAuto.getType() + "\n" + newAuto.getRegistrationNumber() + "\n"
                            + newAuto.getManufacturingYear() + "\n"
                            + (newAuto.isCarTrailer() ? "Прицеп есть\n" : "Прицепа нет\n") + "в базу данных?";
                    int i = JOptionPane.showConfirmDialog(null, massage, "Внимание!", JOptionPane.YES_NO_CANCEL_OPTION);
                    if (i == 0) {
                        DBConnector dbConnector = new DBConnector();
                        int tmp = dbConnector.setNewAutoInDB(newAuto);
                        if (tmp > 0) {
                            JOptionPane.showMessageDialog(container, "Внесение данных в БД выполнено" +
                                    " успешно! Добавлено позиций: " + tmp);
                            dispose();
                        } else {
                            JOptionPane.showMessageDialog(container, "Возникла ошибка при внесении " +
                                    "данных в БД!");
                        }
                    }
                }
            }
        });

    }

    public GUIVehicle(Auto gettingAuto){
        super("Изменить ТС");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setBounds(200, 200, WIDTH_WINDOW, HEIGHT_WINDOW);
        this.setResizable(true);

        getRootPane().setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        this.auto = gettingAuto;

        textFieldBrand.setText(auto.getBrand());
        textFieldModel.setText(auto.getModel());
        comboBoxCategory.setSelectedItem(auto.getCategory());
        textFieldRegistrationNumber.setText(auto.getRegistrationNumber());
        comboBoxType.setSelectedItem(auto.getType());
        textFieldManufacturingYear.setText(Integer.toString(auto.getManufacturingYear()));
        checkBoxCarTrailer.setSelected(auto.isCarTrailer());

        Container container = this.getContentPane();
        fillingContainer(container);
        container.add(buttonSave);
        buttonSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //System.out.println(auto.getId());
                long j = auto.getId();
                auto = fillingAuto(container);
                auto.setId(j);
                if (auto != null) {
                    String massage = "Вы действительно хотите обновить:\n" + auto.getBrand() + "\n" + auto.getModel() + "\n" + auto.getCategory()
                            + "\n" + auto.getType() + "\n" + auto.getRegistrationNumber() + "\n"
                            + auto.getManufacturingYear() + "\n"
                            + (auto.isCarTrailer() ? "Прицеп есть\n" : "Прицепа нет\n") + "в базу данных?";
                    int i = JOptionPane.showConfirmDialog(null, massage, "Внимание!", JOptionPane.YES_NO_CANCEL_OPTION);
                    if (i == 0) {
                        DBConnector dbConnector = new DBConnector();
                        int tmp = dbConnector.setUpdateAutoInDB(auto);
                        if (tmp > 0) {
                            JOptionPane.showMessageDialog(container, "Внесение данных в БД выполнено" +
                                    " успешно! Обновлено позиций: " + tmp);
                            dispose();
                        } else {
                            JOptionPane.showMessageDialog(container, "Возникла ошибка при внесении " +
                                    "данных в БД!");
                        }
                    }
                }
            }
        });
    }

    private void fillingContainer(Container container){
        container.setLayout(new GridLayout(8, 2, 5, 5));
        container.add(labelBrand);
        container.add(textFieldBrand);
        container.add(labelModel);
        container.add(textFieldModel);
        container.add(labelCategory);
        container.add(comboBoxCategory);
        container.add(labelRegistrationNumber);
        container.add(textFieldRegistrationNumber);
        container.add(labelType);
        container.add(comboBoxType);
        container.add(labelManufacturingYear);
        container.add(textFieldManufacturingYear);
        container.add(labelCarTrailer);
        container.add(checkBoxCarTrailer);
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

        if (textFieldBrand.getText() != "") {
            auto.setBrand(textFieldBrand.getText());
        } else {
            JOptionPane.showMessageDialog(container, "Поле бренд пустое!");
            return null;
        }
        if (textFieldModel.getText() != "null") {
            auto.setModel(textFieldModel.getText());
        } else {
            JOptionPane.showMessageDialog(container, "Поле модель пустое!");
            return null;
        }
        auto.setCategory((String)comboBoxCategory.getSelectedItem());
        auto.setType((String)comboBoxType.getSelectedItem());
        if (textFieldRegistrationNumber.getText() != "null") {
            auto.setRegistrationNumber(textFieldRegistrationNumber.getText());
        } else {
            JOptionPane.showMessageDialog(container, "Поле гос. номер пустое!");
            return null;
        }
        if (textFieldManufacturingYear.getText() != null && textFieldManufacturingYear.getText().matches("(19|20)\\d\\d")) {
            auto.setManufacturingYear(Integer.parseInt(textFieldManufacturingYear.getText()));
        } else {
            JOptionPane.showMessageDialog(container, "Ошибка при вводе года!");
            return null;
            //System.out.println("Error");
        }
        auto.setCarTrailer(checkBoxCarTrailer.isSelected());
        return auto;
    }
}
