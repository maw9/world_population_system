package com.kaungmaw;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TopTenPopulatedCountries {

    private final Connection conn;

    public TopTenPopulatedCountries() {
        conn = DatabaseConnection.getInstance();
    }

    public void displayTopTenPopulatedCountries() {
        try {
            ResultSet rs = getTopTenPopulatedCountriesFromDatabase();
            System.out.printf("%-25s%-15s%-30s%-15s\n", "Country Name", "ISO Name", "Latest Population", "World Rank");
            while (rs.next()) {
                System.out.printf(
                        "%-25s%-15s%-30s%-15s\n",
                        rs.getString(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getInt(4)
                );
            }
            rs.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public ResultSet getTopTenPopulatedCountriesFromDatabase() {
        try {
            PreparedStatement ps = conn.prepareStatement(
                    "SELECT country_name, iso_name, 2023_population, world_rank FROM population ORDER BY 2024_population DESC LIMIT 10"
            );
            return ps.executeQuery();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

}
