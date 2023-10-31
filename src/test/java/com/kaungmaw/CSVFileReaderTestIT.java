package com.kaungmaw;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class CSVFileReaderTestIT {

    static CSVFileReader csvFileReader;

    @BeforeAll
    static void setUp() {
        csvFileReader = new CSVFileReader();
    }

    @Test()
    void read() throws Exception {
        Throwable exception = assertThrows(FileNotFoundException.class, () -> csvFileReader.read(""));
        assertEquals("Invalid file path", exception.getMessage());

        Throwable exception1 = assertThrows(FileNotFoundException.class, () -> csvFileReader.read("abcd"));
        assertEquals("Invalid file path", exception1.getMessage());

        LinkedList<Population> results = csvFileReader.read(
                "/Users/kaungmawaung/Documents/IMU/HND/APDP/Kaung Maw Aung (APDP)/new_population.csv"
        );
        assertFalse(results.isEmpty());
        assertEquals("Afghanistan", results.getFirst().getCountryName());
        assertEquals(42559143, results.getFirst().getLatestPopulation());
    }
}