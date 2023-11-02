package com.kaungmaw;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

class InsertNewPopulationTestIT {

    static Connection conn;
    static InsertNewPopulation insertNewPopulation;

    @BeforeAll
    static void setUp() {
        conn = new Menu().connectDatabase();
        insertNewPopulation = new InsertNewPopulation(conn);
    }

    @Test
    void addNewColumn() throws Exception {
        insertNewPopulation.addNewColumn();
        assertTrue(insertNewPopulation.isColumnAlreadyExist("2024_population"));
    }

    @Test
    void isColumnAlreadyExist() throws Exception {
        assertFalse(insertNewPopulation.isColumnAlreadyExist("2019"));
        assertFalse(insertNewPopulation.isColumnAlreadyExist("2025"));
        assertFalse(insertNewPopulation.isColumnAlreadyExist("2020"));
        assertFalse(insertNewPopulation.isColumnAlreadyExist("2024"));
    }
}