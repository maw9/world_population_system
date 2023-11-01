package com.kaungmaw;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class TopTenCountriesWithLargestAreaTestIT {

    static TopTenCountriesWithLargestArea topTenCountriesWithLargestArea;

    @BeforeAll
    static void setUp() {
        new Menu().connectDatabase();
        topTenCountriesWithLargestArea = new TopTenCountriesWithLargestArea();
    }


    @Test
    void getTopTenCountriesWithLargestAreaFromDatabase() throws SQLException {
        ResultSet rs = topTenCountriesWithLargestArea.getTopTenCountriesWithLargestAreaFromDatabase();
        Map<String, Integer> results = new HashMap<>();
        while (rs.next())
            results.put(rs.getString(1), rs.getInt(3));

        assertFalse(results.isEmpty());
        assertTrue(results.containsKey("India"));
        assertFalse(results.containsKey("Myanmar"));
        assertEquals(results.get("Russia"), 17100000);
        assertEquals(results.get("Algeria"), 2400000);
    }
}