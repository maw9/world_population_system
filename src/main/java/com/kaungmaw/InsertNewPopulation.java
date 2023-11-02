package com.kaungmaw;

import java.io.FileNotFoundException;
import java.sql.*;
import java.util.LinkedList;
import java.util.Scanner;

public class InsertNewPopulation {

    Connection conn;
    CSVFileReader csvFileReader;

    public InsertNewPopulation(Connection conn) {
        this.conn = conn;
        csvFileReader = new CSVFileReader();
    }

    public void insert() {
        LinkedList<Population> newPopulationData;
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter csv file path: ");
        try {
            newPopulationData = csvFileReader.read(sc.nextLine());
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            return;
        }

        try {
            if (!isColumnAlreadyExist("2024_population")) {
                addNewColumn();
            } else {
                System.out.println("2024_population column is already exist.");
                return;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage() + ". Try again.");
            return;
        }

        for (Population each : newPopulationData) {
            updateNewPopulationByCountryName(each.getCountryName(), each.getLatestPopulation());
        }

        System.out.println("Successfully added new population data.");
    }

    public void updateNewPopulationByCountryName(String countryName, int population) {
        try {
            PreparedStatement ps = conn.prepareStatement("UPDATE population SET 2024_population = ? WHERE country_name = ?");
            ps.setInt(1, population);
            ps.setString(2, countryName);
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void addNewColumn() throws Exception {
        PreparedStatement ps = conn.prepareStatement("ALTER TABLE population ADD 2024_population int(11)");
        ps.executeUpdate();
        ps.close();
    }

    public boolean isColumnAlreadyExist(String columnName) throws SQLException {
        DatabaseMetaData metaData = conn.getMetaData();
        String tableName = "population";
        ResultSet rs = metaData.getColumns(null, null, tableName, columnName);
        return rs.next();
    }

}
