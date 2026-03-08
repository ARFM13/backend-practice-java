package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DeveloperDAO {

    // 1. OBTENER TODOS
    public List<Programador> getAll() {
        List<Programador> list = new ArrayList<>();
        String sql = "SELECT name, language, experience FROM developers";

        // Usamos el Singleton para obtener la conexión
        try (Connection conn = DatabaseConnection.getConnection();
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

    // 2. GUARDAR (CREATE)
    public void save(Programador p) {
        String sql = "INSERT INTO developers (name, language, experience) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, p.getName());
            pstmt.setString(2, p.getLanguage());
            pstmt.setString(3, p.getExperience());

            pstmt.executeUpdate();
            System.out.println("[DB]: Saved successfully!");
        } catch (SQLException e) {
            System.out.println("Error saving: " + e.getMessage());
        }
    }

    // 3. ACTUALIZAR (UPDATE)
    public void update(String name, String newLanguage, String newExp) {
        String sql = "UPDATE developers SET language = ?, experience = ? WHERE name = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, newLanguage);
            pstmt.setString(2, newExp);
            pstmt.setString(3, name);

            int rows = pstmt.executeUpdate();
            if (rows > 0) System.out.println("Update successful!");
            else System.out.println("Developer not found.");
        } catch (SQLException e) {
            System.out.println("Update error: " + e.getMessage());
        }
    }

    // 4. BORRAR (DELETE)
    public void delete(String name) {
        String sql = "DELETE FROM developers WHERE name = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, name);
            int rows = pstmt.executeUpdate();
            if (rows > 0) System.out.println("Deleted from DB.");
            else System.out.println("⚠️ Not found.");
        } catch (SQLException e) {
            System.out.println("Error deleting: " + e.getMessage());
        }
    }

    // 5. FILTRAR POR LENGUAJE (SEARCH)
    public List<Programador> getByLanguage(String lang) {
        List<Programador> list = new ArrayList<>();
        String sql = "SELECT * FROM developers WHERE language LIKE ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, "%" + lang + "%");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                list.add(new Programador(
                        rs.getString("name"),
                        rs.getString("language"),
                        rs.getString("experience")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Filter error: " + e.getMessage());
        }
        return list;
    }
}