package com.company.autocatalog.gui.guihome;

import com.company.autocatalog.vehicle.Auto;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.company.autocatalog.constans.Constants.*;

public class TableModel extends AbstractTableModel {

    int columnCount = 8;
    private List<Auto> Cars;

    private Set<TableModelListener> listeners = new HashSet<TableModelListener>();


    public TableModel(List<Auto> Cars){
        this.Cars = Cars;
    }

    public void addTableModelListener(TableModelListener listener) {
        listeners.add(listener);
    }
    public void removeTableModelListener(TableModelListener listener) {
        listeners.remove(listener);
    }

    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 1: return Long.class;
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
                return String.class;
            case 7: return Integer.class;
            case 8: return Boolean.class;
            default: return Object.class;
        }
    }

    @Override
    public int getColumnCount() {
        return columnCount;
    }

    @Override
    public String getColumnName(int columnIndex){
        switch (columnIndex){
            case 0: return NAME_ID;
            case 1: return NAME_BRAND;
            case 2: return NAME_MODEL;
            case 3: return NAME_CATEGORY;
            case 4: return NAME_TYPE;
            case 5: return NAME_REGISTRATION_NUMBER;
            case 6: return NAME_MANUFACTURING_YEAR;
            case 7: return NAME_CAR_TRAILER;
        }
        return "";
    }

    @Override
    public int getRowCount() {
        return Cars.size();
    }



    @Override
    public String getValueAt(int rowIndex, int columnIndex) {
        Auto auto = Cars.get(rowIndex);
        switch (columnIndex){
            case 0: return Long.toString(auto.getId());
            case 1: return auto.getBrand();
            case 2: return auto.getModel();
            case 3: return auto.getCategory();
            case 4: return auto.getType();
            case 5: return auto.getRegistrationNumber();
            case 6: return Integer.toString(auto.getManufacturingYear());
            case 7: return Boolean.toString(auto.isCarTrailer());
        }
        return "";
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }


    public void setValueAt(Object value, int rowIndex, int columnIndex) {

    }

}
