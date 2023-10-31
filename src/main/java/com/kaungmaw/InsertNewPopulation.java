package com.kaungmaw;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.LinkedList;
import java.util.Scanner;

public class InsertNewPopulation {

    Connection conn;
    CSVFileReader csvFileReader;

    public InsertNewPopulation(Connection conn) {
        this.conn = conn;
        csvFileReader = new CSVFileReader();
        insert();
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
            addNewColumn();
        } catch (Exception e) {
            System.out.println(e.getMessage() + ". Try again.");
            return;
        }

        for (Population each : newPopulationData) {
            updateNewPopulationByCountryName(each.getCountryName(), each.getLatestPopulation());
        }

        System.out.println("Successfully added new population data.");
    }

    private void updateNewPopulationByCountryName(String countryName, int population) {
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

    private void addNewColumn() throws Exception {
        PreparedStatement ps = conn.prepareStatement("ALTER TABLE population ADD 2024_population int(11)");
        ps.executeUpdate();
        ps.close();
    }

}
