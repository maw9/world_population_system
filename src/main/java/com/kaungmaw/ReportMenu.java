package com.kaungmaw;

import java.util.Scanner;

public class ReportMenu {

    public void promptReportOption() {
        System.out.println("Report");
        System.out.println("==========");
        System.out.println("1. Top 10 Populated Countries");
        System.out.println("2. Top 10 Countries with largest area");
        System.out.println("======================================");
    }

    public boolean validateReportChoice(String input) {
        try {
            int numInput = Integer.parseInt(input);
            return (numInput > 0 && numInput < 3);
        } catch (Exception e) {
            return false;
        }
    }

    public void getUserMenuChoice() {
        Scanner sc = new Scanner(System.in);
        promptReportOption();
        System.out.print("Enter report option (1 or 2): ");
        String input = sc.nextLine();
        while (!validateReportChoice(input)) {
            System.out.println("Invalid Menu Option!");
            System.out.print("Enter valid report option (1 or 2): ");
            input = sc.nextLine();
        }

        switch (input) {
            case "1" -> {
                TopTenPopulatedCountries reportOne = new TopTenPopulatedCountries();
                reportOne.displayTopTenPopulatedCountries();
            }
            case "2" -> {
                TopTenCountriesWithLargestArea reportTwo = new TopTenCountriesWithLargestArea();
                reportTwo.displayTopTenCountriesWithLargestArea();
            }
        }

    }

}
