package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        ArrayList<Programador> teamList = new ArrayList<>();

        System.out.println("=== WELCOME TO THE DEV MANAGER 2.0 ===");
        // Registration Loop
        String op = "y";
        while (op.equalsIgnoreCase("y")) {
            System.out.println("\nName: ");
            String n = reader.nextLine();
            System.out.println("Language: ");
            String l = reader.nextLine();
            System.out.println("Years: ");
            String y = reader.nextLine();
            // Using the Constructor we created!
            teamList.add(new Programador(n, l, y));
            System.out.println("Add another? (y/n): ");
            op = reader.nextLine();
        }
        // Display all
        System.out.println("\n--- CURRENT TEAM ---");
        for (Programador p : teamList) {
            p.evaluateProfile();
        }
            // --- SEARCH FEATURE ---
            System.out.println("\n--- Search Developer by Name ---");
            System.out.println("Enter the name to find: ");
            String searchName = reader.nextLine();

            boolean found = false;

            for (Programador p : teamList) {
                // .equalsIgnoreCase ignores if it's uppercase or lowercase
                if (p.name.equalsIgnoreCase(searchName)) {
                    System.out.println("\n[MATCH FOUND]:");
                    p.evaluateProfile();
                    found = true;
                    break;// We stop searching once we find it
                }
            }

            if (!found) {
                System.out.println("\n[NOT FOUND]: The developer '" + searchName + "' is not in the list.");
            }

            System.out.println("===============================\n");
        }
    }
