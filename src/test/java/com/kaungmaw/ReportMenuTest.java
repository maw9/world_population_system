package com.kaungmaw;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReportMenuTest {

    static ReportMenu reportMenu;

    @BeforeEach
    void setUp() {
        reportMenu = new ReportMenu();
    }

    @Test
    void promptReportOption() {
        reportMenu.promptReportOption();
    }

    @Test
    void validateReportChoice() {
        assertFalse(reportMenu.validateReportChoice("abc"));
        assertFalse(reportMenu.validateReportChoice("-1"));
        assertFalse(reportMenu.validateReportChoice("3"));
        assertTrue(reportMenu.validateReportChoice("1"));
        assertTrue(reportMenu.validateReportChoice("2"));
    }
}