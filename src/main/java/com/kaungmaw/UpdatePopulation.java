package com.kaungmaw;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class UpdatePopulation {

    private final Connection conn;

    public UpdatePopulation(Connection conn) {
        this.conn = conn;
    }

    public void update() {
        Scanner sc = new Scanner(System.in);

        String countryName = getCountryName(sc);

        String year = getYearOfPopulation(sc);

        String newPopulation = getPopulation(sc);

        try {
            updatePopulationByCountryName(countryName, year, newPopulation);
            System.out.println("Successfully updated.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public String getPopulation(Scanner sc) {
        boolean isPopulationValid = false;
        String population = "";
        while (!isPopulationValid) {
            System.out.print("Enter new population: ");
            population = sc.nextLine();
            if (isPopulationInRightFormat(population)) {
                isPopulationValid = true;
            } else {
                System.out.println("Only positive numbers allowed. Try again.");
            }
        }
        return population;
    }

    public boolean isPopulationInRightFormat(String population) {
        return Pattern.matches("\\d+", population);
    }

    public String getYearOfPopulation(Scanner sc) {
        boolean isYearValid = false;
        String year = "";
        while (!isYearValid) {
            System.out.print("Enter year of population: ");
            year = sc.nextLine();
            if (isYearInRightFormat(year)) {
                if (isYearInValidRange(year)) {
                    isYearValid = true;
                } else {
                    System.out.println("Current population data is collected from 2020 to 2024. Try again.");
                }
            } else {
                System.out.println("Year of population must be four digits. Try again.");
            }
        }
        return year;
    }

    public boolean isYearInRightFormat(String year) {
        return Pattern.matches("\\d{4}", year);
    }

    public boolean isYearInValidRange(String year) {
        int yearInInt = Integer.parseInt(year);
        return (yearInInt >= 2020 && yearInInt <= 2024);
    }

    public String getCountryName(Scanner sc) {
        String countryName = "";
        boolean isCountryNameValid = false;
        while (!isCountryNameValid) {
            System.out.print("Enter country name: ");
            countryName = sc.nextLine();
            if (isCountryExist(getCountryNamesFromDatabase(), countryName)) {
                isCountryNameValid = true;
            } else {
                System.out.println("Entered country name is not exist in database. Try again.");
            }
        }
        return countryName;
    }

    public boolean isCountryExist(ArrayList<String> countryNames, String inputCountry) {
        return countryNames.contains(inputCountry);
    }

    public ArrayList<String> getCountryNamesFromDatabase() {
        ArrayList<String> countryNames = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT country_name FROM population");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                countryNames.add(rs.getString(1));
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return countryNames;
    }

    public void updatePopulationByCountryName(String countryName, String year, String newValue) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("UPDATE population SET " + year + "_population = ? WHERE country_name = ?");
        ps.setInt(1, Integer.parseInt(newValue));
        ps.setString(2, countryName);
        ps.executeUpdate();
        ps.close();
    }

}
