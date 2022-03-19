package com.company.autocatalog.db;

import com.company.autocatalog.vehicle.Auto;

import java.sql.*;
import java.util.ArrayList;

public class DBConnector {

    private String url = "jdbc:mysql://localhost/vehicledb";
    private String username = "user";
    private String password = "user";

    public int setNewAutoInDB(Auto auto) {
        int rows = -1;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            Connection conn = DriverManager.getConnection(url, username, password);
            try {
                //System.out.println("Connection to Store DB succesfull!");

                Statement statement = conn.createStatement();
                String sqlQuery = "INSERT vehicle(brand, model, category, type, registrationNumber, " +
                        "manufacturingYear, cartrailer) " +
                                "VALUES('" + auto.getBrand() + "', '" + auto.getModel() + "', '" + auto.getCategory() +
                                "', '" + auto.getType() + "', '" + auto.getRegistrationNumber() +
                                "', " + auto.getManufacturingYear() + ", " + auto.isCarTrailer() + ")";
                //System.out.println(test);

                rows = statement.executeUpdate(sqlQuery);

                System.out.printf("Added %d rows", rows);

                statement.close();
            } finally {
                conn.close();
            }
        }
        catch(Exception ex){
            System.out.println("Connection failed...");

            System.out.println(ex);
        }
        return rows;
    }

    public int setUpdateAutoInDB(Auto auto) {
        int rows = -1;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            Connection conn = DriverManager.getConnection(url, username, password);
            try {
                //System.out.println("Connection to Store DB succesfull!");

                Statement statement = conn.createStatement();
                String sqlQuery = "UPDATE vehicle " +
                        "SET brand = '" + auto.getBrand() + "', model = '" + auto.getModel() +
                        "', category = '" + auto.getCategory() + "', type = '" + auto.getType() +
                        "', registrationNumber = '" + auto.getRegistrationNumber() +
                        "', manufacturingYear = " + auto.getManufacturingYear() +
                        ", carTrailer = " + auto.isCarTrailer() +
                        " WHERE id = " + auto.getId();
                System.out.println(sqlQuery);
                rows = statement.executeUpdate(sqlQuery);
                //System.out.printf("Updated %d rows \n", rows);

                statement.close();
            } finally {
                conn.close();
            }
        }
        catch(Exception ex){
            System.out.println("Connection failed...");

            System.out.println(ex);
        }
        return rows;
    }

    public Auto getAutoFromDB(long id){
        Auto auto = new Auto();
        try{
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            Connection conn = DriverManager.getConnection(url, username, password);
            try {
                //System.out.println("Connection to Store DB succesfull!");

                Statement statement = conn.createStatement();

                ResultSet resultSet = statement.executeQuery("SELECT * FROM vehicle WHERE id = " + id);
                while(resultSet.next()){
                    auto.setBrand(resultSet.getString("brand"));
                    auto.setModel(resultSet.getString("model"));
                    auto.setCategory(resultSet.getString("category"));
                    auto.setType(resultSet.getString("type"));
                    auto.setRegistrationNumber(resultSet.getString("registrationNumber"));
                    auto.setManufacturingYear(resultSet.getInt("manufacturingYear"));
                    auto.setCarTrailer(resultSet.getBoolean("carTrailer"));

                    //System.out.printf("%d. %s \n", id, filename);
                }
                resultSet.close();
                statement.close();
            } finally {
                conn.close();
            }
        }
        catch(Exception ex){
            System.out.println("Connection failed...");

            System.out.println(ex);
        }
        return auto;
    }

    //для заполнения ComboBox-ов
    public ArrayList<String> getColumnFromDB(String column){
        ArrayList<String> arrayColumns = new ArrayList<>();
        try{
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            Connection conn = DriverManager.getConnection(url, username, password);
            try {
                //System.out.println("Connection to Store DB succesfull!");

                Statement statement = conn.createStatement();
                String tmp = "SELECT DISTINCT " + column +
                        " FROM vehicle ORDER BY " + column + " ASC";

                ResultSet resultSet = statement.executeQuery(tmp);
                //System.out.println(tmp);
                //arrayColumns = new String[resultSet.getRow()];
                while(resultSet.next()){
                    if (column == "brand") arrayColumns.add(resultSet.getString("brand"));
                    if (column == "model") arrayColumns.add(resultSet.getString("model"));
                    if (column == "category") arrayColumns.add(resultSet.getString("category"));
                    if (column == "type") arrayColumns.add(resultSet.getString("type"));
                    if (column == "registrationNumber") arrayColumns.add(resultSet.getString("registrationNumber"));
                    if (column == "manufacturingYear") arrayColumns.add(resultSet.getString("manufacturingYear"));
                }

                resultSet.close();
                statement.close();
            } finally {
                conn.close();
            }
        }
        catch(Exception ex){
            System.out.println("Connection failed...");

            System.out.println(ex);
        }
        return arrayColumns;
    }


    public ArrayList<Auto> getTableFromDB(String sqlQuery){

        ArrayList<Auto> cars = new ArrayList<>();
        try{
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            Connection conn = DriverManager.getConnection(url, username, password);
            try {
                //System.out.println("Connection to Store DB succesfull!");

                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery(sqlQuery);

                int i = 0;
                while(resultSet.next()){
                    Auto auto = new Auto();
                    auto.setId(resultSet.getLong("id"));
                    auto.setBrand(resultSet.getString("brand"));
                    auto.setModel(resultSet.getString("model"));
                    auto.setCategory(resultSet.getString("category"));
                    auto.setType(resultSet.getString("type"));
                    auto.setRegistrationNumber(resultSet.getString("registrationNumber"));
                    auto.setManufacturingYear(resultSet.getInt("manufacturingYear"));
                    auto.setCarTrailer(resultSet.getBoolean("carTrailer"));
                    cars.add(i, auto);
                    //auto.printAutoInConsole();
                    i++;
                }

                resultSet.close();
                statement.close();
            } finally {
                conn.close();
            }
        }
        catch(Exception ex){
            System.out.println("Connection failed...");

            System.out.println(ex);
        }
        return cars;
    }
}

