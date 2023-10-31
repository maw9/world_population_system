package com.kaungmaw;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TopTenCountriesWithLargestAreaTest {

    static TopTenCountriesWithLargestArea reportTwo;

    @BeforeEach
    void setUp() {
        reportTwo = new TopTenCountriesWithLargestArea(null);
    }

    @Test
    void calculateGrowthRate() {
        assertEquals(3.0, reportTwo.calculateGrowthRate(37742154, 38874305));
        assertNotEquals(1.12, reportTwo.calculateGrowthRate(145934462, 144303049));
        assertEquals(-1.12f, reportTwo.calculateGrowthRate(145934462, 144303049));
    }
}