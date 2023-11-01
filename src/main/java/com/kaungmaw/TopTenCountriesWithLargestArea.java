package com.kaungmaw;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;

public class TopTenCountriesWithLargestArea {

    private final Connection conn;

    public TopTenCountriesWithLargestArea() {
        conn = DatabaseConnection.getInstance();
    }

    public void displayTopTenCountriesWithLargestArea() {
        try {
            ResultSet rs = getTopTenCountriesWithLargestAreaFromDatabase();
            System.out.printf(
                    "%-25s%-15s%-35s%-15s%-15s\n",
                    "Country Name",
                    "ISO Name",
                    "Country Area (Square Kilometer)",
                    "Growth Rate", "World Rank"
            );
            while (rs.next()) {
                System.out.printf(
                        "%-25s%-15s%-35s%-15s%-15s\n",
                        rs.getString(1),
                        rs.getString(2),
                        rs.getInt(3),
                        calculateGrowthRate(rs.getInt(5), rs.getInt(6)) + "%",
                        rs.getInt(4)
                );
            }
            rs.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public ResultSet getTopTenCountriesWithLargestAreaFromDatabase() {
        try {
            PreparedStatement ps = conn.prepareStatement(
                    "SELECT country_name, iso_name, area_sq_km, " +
                            "world_rank, 2020_population, 2023_population" +
                            " FROM population ORDER BY area_sq_km DESC LIMIT 10"
            );
            return ps.executeQuery();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public float calculateGrowthRate(float initialPopulation, float finalPopulation) {
        DecimalFormat df = new DecimalFormat("#.##");
        float result = ((finalPopulation - initialPopulation) / initialPopulation) * 100;
        String roundedResult = df.format(Math.abs(result));
        float finalResult = Float.parseFloat(roundedResult);

        if (result < 0) {
            finalResult *= -1;
        }
        return finalResult;
    }

}
