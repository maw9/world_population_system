package com.kaungmaw;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UpdatePopulationTestIT {

    static UpdatePopulation updatePopulation;

    @BeforeAll
    static void setUp() {
        new Menu().connectDatabase();
        updatePopulation = new UpdatePopulation();
    }

    @Test
    void getCountryNamesFromDatabase() {
        ArrayList<String> results = updatePopulation.getCountryNamesFromDatabase();
        assertEquals(213, results.size());
        assertEquals("Afghanistan", results.get(0));
        assertEquals("Zimbabwe", results.get(results.size() - 1));
    }
}