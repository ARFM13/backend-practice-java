package org.example;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static int leerEntero(Scanner reader) {
        while (true) {
            try {
                return Integer.parseInt(reader.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("⚠️ Invalid input. Please enter a number: ");
            }
        }
    }
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
            System.out.println("5. Update developer");
            System.out.println("6. Filter by language");
            System.out.println("7. Exit");
            System.out.print("Select an option: ");

            String option = reader.nextLine();

            switch (option) {
                case "1":
                    List<Programador> teamList = dao.getAll();
                    System.out.println("\n--- CURRENT TEAM ---");
                    for (Programador p : teamList) {
                        p.printBasicInfo(); // <--- CAMBIO AQUÍ
                    }
                    break;
                case "2":
                // Agregar
                System.out.print("Name: ");
                String n = reader.nextLine();
                    // Truco para poner la primera en Mayúscula:
                    n = n.substring(0, 1).toUpperCase() + n.substring(1).toLowerCase();

                System.out.print("Language: ");
                String l = reader.nextLine();

                System.out.print("Years of experience: ");
                // Aquí usamos nuestro nuevo validador
                int y = leerEntero(reader);

                // Convertimos el int a String para que tu clase Programador no proteste
                dao.save(new Programador(n, l, String.valueOf(y)));
                break;

                case "3":
                    System.out.print("Enter name to find: ");
                    String searchName = reader.nextLine();
                    List<Programador> all = dao.getAll();
                    boolean found = false;
                    for (Programador p : all) {
                        if (p.getName().equalsIgnoreCase(searchName)) {
                            p.evaluateProfile(); // <--- AQUÍ SÍ USAMOS EL ANÁLISIS
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
                    System.out.print("Enter the name of the dev to update: ");
                    String nameToUpdate = reader.nextLine();

                    System.out.print("New Language: ");
                    String newLang = reader.nextLine();

                    System.out.print("New Years of Experience: ");
                    int newY = leerEntero(reader); // Usamos tu validador de ayer

                    dao.update(nameToUpdate, newLang, String.valueOf(newY));
                    break;

                case "6":
                    // Filtrar por Lenguaje
                    System.out.print("Enter the language to filter: ");
                    String langFilter = reader.nextLine();

                    List<Programador> filteredList = dao.getByLanguage(langFilter);

                    System.out.println("\n--- SEARCH RESULTS FOR: " + langFilter.toUpperCase() + " ---");

                    if (filteredList.isEmpty()) {
                        System.out.println("No developers found specialized in " + langFilter + ".");
                    } else {
                        for (Programador p : filteredList) {
                            // Usamos el método de info básica para que salga uno debajo de otro
                            p.printBasicInfo();
                        }
                        System.out.println("Total found: " + filteredList.size());
                    }
                    System.out.println("--------------------------------------------");
                    break;

                case "7":
                    System.out.println("Goodbye!");
                    exit = true;
                    break;


                default:
                    System.out.println("Invalid option, try again.");
            }
        }
    }
}