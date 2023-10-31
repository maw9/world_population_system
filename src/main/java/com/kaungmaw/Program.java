package com.kaungmaw;

public class Program {

    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.connectDatabase();
        menu.getUserMenuChoice();
    }

}
