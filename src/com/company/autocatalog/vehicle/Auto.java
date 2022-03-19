package com.company.autocatalog.vehicle;

import static com.company.autocatalog.constans.Constants.*;

public class Auto extends Vehicle implements Operations{

    private String type;
    private boolean carTrailer;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isCarTrailer() {
        return carTrailer;
    }

    public void setCarTrailer(boolean carTrailer) {
        this.carTrailer = carTrailer;
    }

    @Override
    public void findVehicle() {

    }

    @Override
    public void addVehicle() {

    }

    @Override
    public void editVehicle() {

    }

    @Override
    public void printAutoInConsole() {
        System.out.println(NAME_ID + " - " + id);
        System.out.println(NAME_BRAND + " - " + brand);
        System.out.println(NAME_MODEL + " - " + model);
        System.out.println(NAME_CATEGORY + " - " + category);
        System.out.println(NAME_TYPE + " - " + type);
        System.out.println(NAME_REGISTRATION_NUMBER + " - " + registrationNumber);
        System.out.println(NAME_MANUFACTURING_YEAR + " - " + manufacturingYear);
        System.out.println(NAME_CAR_TRAILER + " - " + carTrailer);

    }
}
