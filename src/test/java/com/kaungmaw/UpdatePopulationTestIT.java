package com.kaungmaw;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class UpdatePopulationTestIT {

    static UpdatePopulation updatePopulation;

    @BeforeAll
    static void setUp() {
        Connection conn = new Menu().connectDatabase();
        updatePopulation = new UpdatePopulation(conn);
    }

    @Test
    void getCountryNamesFromDatabase() {
        ArrayList<String> results = updatePopulation.getCountryNamesFromDatabase();
        assertEquals(213, results.size());
        assertEquals("Afghanistan", results.get(0));
        assertEquals("Zimbabwe", results.get(results.size() - 1));
    }
}