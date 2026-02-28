package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/dev_manager";
        String user = "root";
        String pass = "root";

        Scanner reader = new Scanner(System.in);
        ArrayList<Programador> teamList = new ArrayList<>();
// --- CARGA INICIAL DESDE MYSQL ---
        try (Connection connection = DriverManager.getConnection(url, user, pass)) {
            String sql = "SELECT name, language, experience FROM developers";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                // Llenamos nuestra lista con lo que ya existe en la DB
                teamList.add(new Programador(
                        rs.getString("name"),
                        rs.getString("language"),
                        rs.getString("experience")
                ));
            }
            if (!teamList.isEmpty()) {
                System.out.println("📂 [SISTEMA]: Se han cargado " + teamList.size() + " programadores previos.");
            }
        } catch (SQLException e) {
            System.out.println("⚠️ No hay datos previos o la tabla está vacía.");
        }
        System.out.println("=== WELCOME TO THE DEV MANAGER 2.0 ===");

        // --- BUCLE DE REGISTRO E INSERCIÓN ---
        String op = "y";
        while (op.equalsIgnoreCase("y")) {
            System.out.println("\nName: ");
            String n = reader.nextLine();
            System.out.println("Language: ");
            String l = reader.nextLine();
            System.out.println("Years: ");
            String y = reader.nextLine();

            // 1. Guardar en la lista local (RAM)
            teamList.add(new Programador(n, l, y));

            // 2. Guardar en MySQL (Persistencia)
            try (Connection connection = DriverManager.getConnection(url, user, pass)) {
                String sql = "INSERT INTO developers (name, language, experience) VALUES (?, ?, ?)";
                try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
                    pstmt.setString(1, n);
                    pstmt.setString(2, l);
                    pstmt.setString(3, y);
                    pstmt.executeUpdate();
                    System.out.println("💾 [DB]: ¡" + n + " guardado en Fedora!");
                }
            } catch (SQLException e) {
                System.out.println("❌ Error al guardar en DB: " + e.getMessage());
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

        System.out.println("\n===============================");
    }
}