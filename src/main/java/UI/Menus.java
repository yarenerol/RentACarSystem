package UI;

import Models.Users.User;

import java.util.Scanner;

public class Menus {

    public void getMainMenu () {
        System.out.println("=== WELCOME TO RENT A CAR SERVICE ===");
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("3. Exit");
        System.out.println("Your choice: ");

    }

    public static void adminMenu (User user) {
        System.out.println("Welcome " + user.getName());
        System.out.println("1. Create new vehicle");
        System.out.println("2. List all vehicles");
        System.out.println("3. Log out and back to main menu");
        System.out.println("0. Exit");
        System.out.println("Your choice: ");

    }

    public static void userMenu (User user) {
        System.out.println("Welcome " + user.getName());
        System.out.println("1. List all vehicles");
        System.out.println("2. Search by vehicle brand");
        System.out.println("3. Search by vehicle type");
        System.out.println("4. Search by price range");
        System.out.println("5. Rent a vehicle");
        System.out.println("6. List rental");
        System.out.println("7. Log out and back to main menu");
        System.out.println("0. Exit");
        System.out.println("Your choice: ");

    }




}
