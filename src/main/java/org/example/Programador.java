package org.example;
import  java.util.ArrayList;
import java.util.Scanner;

  public  class Programador {
      String name;
      String MainLanguage;
      String yearsOfExperience;

      void evaluateProfile() {
          // We convert the String to an integer to perform comparisons
          try {

              int years = Integer.parseInt(yearsOfExperience);
              String rank;
              if (years < 0) {
                  System.out.println("Error: Experience cannot be negative.");
                  return; //We exit the method if the data is invalid.
              }

              // Decision Logic (IF - ELSE Structure)
              if (years < 2) {
                  rank = "Junior (Their journey has only just begun!)";
              } else if (years >= 2 && years < 5) {
                  rank = "mid-level (It has a solid track record)";
              } else {
                  rank = "Senior (He's an expert.)";
              }

              System.out.println("\n--- ANALYSIS RESULT ---\n");
              System.out.println("Developer: " + name);
              System.out.println("Main Language: " + MainLanguage);
              System.out.println("Assigned Rank: " + rank);

          } catch (NumberFormatException e) {
              System.out.println(("\n[ERROR]: Please enter a valid NUMBER for years of experience."));

          }
      }


      public static void main(String[] args) {
          Scanner lector = new Scanner(System.in);
          // We create our "storage" for developers (ArrayList)
          ArrayList<Programador> teamList = new ArrayList<>();
          String continueAdding = "y";

          // Loop to register multiple developers
          while (continueAdding.equalsIgnoreCase("y")) {
              Programador newTalent = new Programador();

              System.out.println("\n--- New Developer Registration ---");

              System.out.println("Enter Name: ");
              newTalent.name = lector.nextLine();

              System.out.println("Enter Main Language: ");
              newTalent.MainLanguage = lector.nextLine();

              System.out.println("Enter Years of Experience: ");
              newTalent.yearsOfExperience = lector.nextLine();

              // SAVE the developer object into the list
              teamList.add(newTalent);
              System.out.println("\nDo you want to register another one? (y/n): ");
              continueAdding = lector.nextLine();
          }
          //---FINAL REPORT ---
          System.out.println("\n===============================");
          System.out.println("   TEAM SUMMARY (" + teamList.size() + " Devs)");
          System.out.println("===============================");
//Iterating through the list to analyze each developer
          for (Programador p : teamList) {
              p.evaluateProfile();// This calls the analysis for each one
          }
          // --- SEARCH FEATURE ---
          System.out.println("\n--- Search Developer by Name ---");
          System.out.println("Enter the name you want to find: ");
          String searchName = lector.nextLine();

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