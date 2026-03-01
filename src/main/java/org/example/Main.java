package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    // 1. Ponemos las credenciales aquí arriba para que TODO el código las vea
    private static final String URL = "jdbc:mysql://localhost:3306/dev_manager";
    private static final String USER = "root";
    private static final String PASS = "root";

    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        ArrayList<Programador> teamList = new ArrayList<>();

        // --- CARGA INICIAL DESDE MYSQL ---
        try (Connection connection = DriverManager.getConnection(URL, USER, PASS)) {
            String sql = "SELECT name, language, experience FROM developers";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                teamList.add(new Programador(
                        rs.getString("name"),
                        rs.getString("language"),
                        rs.getString("experience")
                ));
            }
            if (!teamList.isEmpty()) {
                System.out.println("[SISTEMA]: Se han cargado " + teamList.size() + " programadores previos.");
            }
        } catch (SQLException e) {
            System.out.println("No hay datos previos o la tabla está vacía.");
        }

        System.out.println("=== WELCOME TO THE DEV MANAGER 2.0 ===");

        // --- BUCLE DE REGISTRO E INSERCIÓN ---
        System.out.println("Add a new developer? (y/n): ");
        String op = reader.nextLine();

        while (op.equalsIgnoreCase("y")) {
            System.out.println("\nName: ");
            String n = reader.nextLine();
            System.out.println("Language: ");
            String l = reader.nextLine();
            System.out.println("Years: ");
            String y = reader.nextLine();

            teamList.add(new Programador(n, l, y));

            try (Connection connection = DriverManager.getConnection(URL, USER, PASS)) {
                String sql = "INSERT INTO developers (name, language, experience) VALUES (?, ?, ?)";
                try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
                    pstmt.setString(1, n);
                    pstmt.setString(2, l);
                    pstmt.setString(3, y);
                    pstmt.executeUpdate();
                    System.out.println("[DB]: ¡" + n + " guardado en Fedora!");
                }
            } catch (SQLException e) {
                System.out.println("Error al guardar en DB: " + e.getMessage());
            }

            System.out.println("Add another? (y/n): ");
            op = reader.nextLine();
        }

        // --- MOSTRAR TODO EL EQUIPO ---
        System.out.println("\n--- CURRENT TEAM ---");
        for (Programador p : teamList) {
            p.evaluateProfile();
        }

        // --- FUNCIÓN DE BÚSQUEDA ---
        System.out.println("\n--- Search Developer by Name ---");
        System.out.println("Enter the name to find: ");
        String searchName = reader.nextLine();

        boolean found = false;
        for (Programador p : teamList) {
            if (p.name.equalsIgnoreCase(searchName)) {
                System.out.println("\n[MATCH FOUND]:");
                p.evaluateProfile();
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("\n[NOT FOUND]: The developer '" + searchName + "' is not in the list.");
        }

        // --- FUNCIÓN DE BORRADO ---
        System.out.println("\n--- Delete Developer ---");
        System.out.println("Enter the name to delete (or press Enter to skip): ");
        String toDelete = reader.nextLine();
        if (!toDelete.isEmpty()) {
            deleteDeveloper(toDelete);
        }

        System.out.println("\n===============================");
    } // AQUÍ CIERRA EL MAIN

    // 2. EL MÉTODO DELETE FUERA DEL MAIN PERO DENTRO DE LA CLASE
    private static void deleteDeveloper(String name) {
        String sql = "DELETE FROM developers WHERE name = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, name);
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("❌ Developer '" + name + "' deleted successfully from DB.");
            } else {
                System.out.println("⚠️ No developer found with that name in DB.");
            }
        } catch (SQLException e) {
            System.out.println("Error deleting developer: " + e.getMessage());
        }
    }
} // AQUÍ CIERRA LA CLASE