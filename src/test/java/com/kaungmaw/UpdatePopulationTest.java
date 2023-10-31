package com.kaungmaw;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UpdatePopulationTest {

    ArrayList<String> countryNames;
    UpdatePopulation updatePopulation;

    @BeforeEach
    void setUp() {
        countryNames = new ArrayList<>();
        countryNames.add("Brazil");
        countryNames.add("Myanmar");
        countryNames.add("Canada");
        countryNames.add("Korea");
        countryNames.add("Singapore");
        updatePopulation = new UpdatePopulation(null);
    }

    @Test
    void isPopulationInRightFormat() {
        assertFalse(updatePopulation.isPopulationInRightFormat("-123"));
        assertFalse(updatePopulation.isPopulationInRightFormat("abc"));
        assertTrue(updatePopulation.isPopulationInRightFormat("346577"));
    }

    @Test
    void isYearInRightFormat() {
        assertFalse(updatePopulation.isYearInRightFormat("-2024"));
        assertFalse(updatePopulation.isYearInRightFormat("0"));
        assertFalse(updatePopulation.isYearInRightFormat("20100"));
        assertTrue(updatePopulation.isYearInRightFormat("2024"));
    }

    @Test
    void isYearInValidRange() {
        assertFalse(updatePopulation.isYearInValidRange("2019"));
        assertFalse(updatePopulation.isYearInValidRange("2025"));
        assertTrue(updatePopulation.isYearInValidRange("2020"));
        assertTrue(updatePopulation.isYearInValidRange("2021"));
        assertTrue(updatePopulation.isYearInValidRange("2022"));
        assertTrue(updatePopulation.isYearInValidRange("2023"));
        assertTrue(updatePopulation.isYearInValidRange("2024"));
    }

    @Test
    void isCountryExist() {
        assertFalse(updatePopulation.isCountryExist(countryNames, "abcd"));
        assertFalse(updatePopulation.isCountryExist(countryNames, "1234"));
        assertFalse(updatePopulation.isCountryExist(countryNames, "MM"));
        assertFalse(updatePopulation.isCountryExist(countryNames, "SG"));
        assertTrue(updatePopulation.isCountryExist(countryNames, "Myanmar"));
        assertTrue(updatePopulation.isCountryExist(countryNames, "Singapore"));
    }
}