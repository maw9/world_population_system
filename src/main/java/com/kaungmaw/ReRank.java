package com.kaungmaw;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReRank {

    private final Connection conn;

    public ReRank(Connection conn) {
        this.conn = conn;
    }

    public ResultSet getCountryNamesInDescSortByYearOfPopulation(String year) {
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT country_name FROM population ORDER BY " + year + "_population DESC");
            return ps.executeQuery();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void reRank(ResultSet countryNames) {
        try {
            int count = 1;
            while (countryNames.next()) {
                updateCountryRankByName(countryNames.getString(1), count++);
            }
            System.out.println("Countries' world ranks updated.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateCountryRankByName(String name, int rank) {
        try {
            PreparedStatement ps = conn.prepareStatement("UPDATE population SET world_rank = ? WHERE country_name = ?");
            ps.setInt(1, rank);
            ps.setString(2, name);
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
