package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DeveloperDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/dev_manager";
    private static final String USER = "root";
    private static final String PASS = "root";

    // Método para obtener todos los programadores
    public List<Programador> getAll() {
        List<Programador> list = new ArrayList<>();
        String sql = "SELECT name, language, experience FROM developers";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                list.add(new Programador(
                        rs.getString("name"),
                        rs.getString("language"),
                        rs.getString("experience")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error loading data: " + e.getMessage());
        }
        return list;
    }

    // Método para guardar un programador
    public void save(Programador p) {
        String sql = "INSERT INTO developers (name, language, experience) VALUES (?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, p.name);
            pstmt.setString(2, p.MainLanguage);
            pstmt.setString(3, p.yearsOfExperience);
            pstmt.executeUpdate();
            System.out.println("[DB]: Saved successfully!");
        } catch (SQLException e) {
            System.out.println("Error saving: " + e.getMessage());
        }
    }

    // Método para borrar
    public void delete(String name) {
        String sql = "DELETE FROM developers WHERE name = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, name);
            int rows = pstmt.executeUpdate();
            if (rows > 0) System.out.println("Deleted from DB.");
            else System.out.println("Not found.");
        } catch (SQLException e) {
            System.out.println("Error deleting: " + e.getMessage());
        }
    }
}