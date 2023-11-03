package com.kaungmaw;

import java.util.Scanner;

public class Menu {

    Scanner sc;

    public Menu() {
        sc = new Scanner(System.in);
    }

    public void connectDatabase() {
        DatabaseConnection.getInstance();
    }

    public void displayMenu() {
        String menuArt = """
                ===================
                   _   _   _   _ \s
                  / \\ / \\ / \\ / \\\s
                 ( M | e | n | u )
                  \\_/ \\_/ \\_/ \\_/\s
                  
                ===================
                 """;

        for (int i = 0, n = menuArt.length(); i < n; i++) {
            System.out.print(menuArt.charAt(i));
        }
        System.out.println("\n1. Insert New Population Data");
        System.out.println("2. Update Population Data");
        System.out.println("3. Re-rank The World Rank");
        System.out.println("4. Report");
        System.out.println("5. Exit");
        System.out.println("________________________________");
    }

    public boolean validateMenuChoice(String input) {
        try {
            int numInput = Integer.parseInt(input);
            return (numInput > 0 && numInput < 6);
        } catch (Exception e) {
            return false;
        }
    }

    public void getUserMenuChoice() {
        displayMenu();
        System.out.print("Enter menu number between 1 and 5: ");
        String input = sc.nextLine();
        while (!validateMenuChoice(input)) {
            System.out.println("Invalid Menu Option!");
            System.out.print("Enter menu number between 1 and 5: ");
            input = sc.nextLine();
        }

        switch (input) {
            case "1" -> {
                FileReaderFactory factory = new FileReaderFactory();
                ICSVFileReader fileReader = factory.getFileReader(FileReaderType.OpenCSV);
                new InsertNewPopulation(fileReader);
            }
            case "2" -> {
                UpdatePopulation updatePopulation = new UpdatePopulation();
                updatePopulation.update();
            }
            case "3" -> {
                ReRank reRank = new ReRank();
                reRank.reRank(reRank.getCountryNamesInDescSortByYearOfPopulation("2024"));
            }
            case "4" -> {
                ReportMenu menu = new ReportMenu();
                menu.getUserMenuChoice();

            }
            case "5" -> {
                exitProgram();
                return;
            }
        }
        getUserMenuChoice();
    }

    private void exitProgram() {
        sc.close();
        System.exit(0);
    }

}
