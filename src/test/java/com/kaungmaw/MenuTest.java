package com.kaungmaw;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MenuTest {

    static Menu menu;

    @BeforeEach
    void setUp() {
        menu = new Menu();
    }

    @Test
    void connectDatabase() {
        assertNotNull(menu.connectDatabase());
    }

    @Test
    void displayMenu() {
        menu.displayMenu();
    }

    @Test
    void validateMenuChoice() {
        assertTrue(menu.validateMenuChoice("1"));
        assertTrue(menu.validateMenuChoice("5"));
        assertFalse(menu.validateMenuChoice("-1"));
        assertFalse(menu.validateMenuChoice("6"));
        assertFalse(menu.validateMenuChoice("abc"));
    }

}