package com.kaungmaw;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class ReRankTestIT {

    static ReRank reRank;

    @BeforeAll
    static void setUp() {
        Connection conn = new Menu().connectDatabase();
        reRank = new ReRank(conn);
    }

    @Test
    void getCountryNamesInDescSortByYear() throws SQLException {
        ResultSet rs = reRank.getCountryNamesInDescSortByYearOfPopulation("2024");
        ArrayList<String> result = new ArrayList<>();
        while (rs.next())
            result.add(rs.getString(1));

        assertFalse(result.isEmpty());
        assertEquals("India", result.get(0));
        assertEquals("Vatican City State (Holy See)", result.get(result.size() - 1));
    }
}