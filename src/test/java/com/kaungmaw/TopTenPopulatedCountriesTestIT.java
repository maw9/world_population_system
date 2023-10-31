package com.kaungmaw;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TopTenPopulatedCountriesTestIT {

    static TopTenPopulatedCountries topTenPopulatedCountries;

    @BeforeAll
    static void setUp() {
        Connection conn = new Menu().connectDatabase();
        topTenPopulatedCountries = new TopTenPopulatedCountries(conn);
    }

    @Test
    void getTopTenPopulatedCountriesFromDatabase() throws SQLException {
        ResultSet rs = topTenPopulatedCountries.getTopTenPopulatedCountriesFromDatabase();
        ArrayList<String> results = new ArrayList<>();
        while (rs.next())
            results.add(rs.getString(1));

        assertEquals(10, results.size());
        assertEquals("China", results.get(1));
        assertEquals("Russia", results.get(results.size() - 2));
    }
}