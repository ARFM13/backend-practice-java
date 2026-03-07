package org.example;

public class Programador {
    private String name;
    private String MainLanguage;
    private String yearsOfExperience;

    public Programador(String name, String mainLanguage, String yearsOfExperience) {
        this.name = name;
        this.MainLanguage = mainLanguage;
        this.yearsOfExperience = yearsOfExperience;
    }

    // Getters
    public String getName() { return name; }
    public String getLanguage() { return MainLanguage; }
    public String getExperience() { return yearsOfExperience; }

    // Setters
    public void setName(String name) { this.name = name; }
    public void setLanguage(String language) { this.MainLanguage = language; }
    public void setExperience(String experience) { this.yearsOfExperience = experience; }

    // --- MÉTODO 1: Para la lista general (Opción 1 del Main) ---
    public void printBasicInfo() {
        System.out.println("Dev: " + this.name + " | Language: " + this.MainLanguage + " | Exp: " + this.yearsOfExperience + " years");
    }

    // --- MÉTODO 2: Para el análisis detallado (Opción 3 del Main) ---
    public void evaluateProfile() {
        try {
            int years = Integer.parseInt(yearsOfExperience);
            String rank;

            if (years < 0) {
                System.out.println("Error: Experience cannot be negative.");
                return;
            }

            if (years < 2) {
                rank = "Junior (Their journey has only just begun!)";
            } else if (years >= 2 && years < 5) {
                rank = "mid-level (It has a solid track record)";
            } else {
                rank = "Senior (He's an expert.)";
            }

            System.out.println("\n--- ANALYSIS RESULT ---");
            System.out.println("Developer: " + name);
            System.out.println("Main Language: " + MainLanguage);
            System.out.println("Assigned Rank: " + rank);
            System.out.println("-----------------------\n");

        } catch (NumberFormatException e) {
            System.out.println("\n[ERROR]: Invalid experience data for " + name);
        }
    }
}
