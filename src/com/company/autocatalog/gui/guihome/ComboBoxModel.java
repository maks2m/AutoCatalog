package com.company.autocatalog.gui.guihome;

import javax.swing.*;
import java.util.AbstractList;
import java.util.ArrayList;

public class ComboBoxModel extends AbstractListModel implements javax.swing.ComboBoxModel {

    ArrayList<String> arrayList = new ArrayList<>();
    String selection = null;

    public void setArrayList(ArrayList arrayList) {
        this.arrayList = arrayList;
    }

    @Override
    public void setSelectedItem(Object anItem) {
        selection = (String) anItem;
    }

    @Override
    public Object getSelectedItem() {
        return selection;
    }

    @Override
    public int getSize() {
        return arrayList.size();
    }

    @Override
    public Object getElementAt(int index) {
        return arrayList.get(index);
    }
}
