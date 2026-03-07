package org.example;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        DeveloperDAO dao = new DeveloperDAO();
        boolean exit = false;

        System.out.println("=== DEV MANAGER 2.0 (MODO PROFESIONAL) ===");

        while (!exit) {
            System.out.println("\n--- MENU ---");
            System.out.println("1. View all developers");
            System.out.println("2. Add new developer");
            System.out.println("3. Search developer by name");
            System.out.println("4. Delete developer");
            System.out.println("5. Exit");
            System.out.print("Select an option: ");

            String option = reader.nextLine();

            switch (option) {
                case "1":
                    // Ver lista
                    List<Programador> teamList = dao.getAll1();
                    System.out.println("\n--- CURRENT TEAM ---");
                    for (Programador p : teamList) {
                        p.evaluateProfile();
                    }
                    break;

                case "2":
                    // Agregar
                    System.out.print("Name: "); String n = reader.nextLine();
                    System.out.print("Language: "); String l = reader.nextLine();
                    System.out.print("Years: "); String y = reader.nextLine();
                    dao.save(new Programador(n, l, y));
                    break;

                case "3":
                    // Buscar (Lo hacemos filtrando la lista del DAO)
                    System.out.print("Enter name to find: ");
                    String searchName = reader.nextLine();
                    List<Programador> all = dao.getAll();
                    boolean found = false;
                    for (Programador p : all) {
                        if (p.name.equalsIgnoreCase(searchName)) {
                            p.evaluateProfile();
                            found = true;
                            break;
                        }
                    }
                    if (!found) System.out.println("Developer not found.");
                    break;

                case "4":
                    // Borrar
                    System.out.print("Enter name to delete: ");
                    String toDelete = reader.nextLine();
                    dao.delete(toDelete);
                    break;

                case "5":
                    System.out.println("Goodbye!");
                    exit = true;
                    break;

                default:
                    System.out.println("Invalid option, try again.");
            }
        }
    }
}