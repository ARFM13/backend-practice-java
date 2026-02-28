package org.example;
import  java.util.ArrayList;
import java.util.Scanner;

  public  class Programador {
      String name;
      String MainLanguage;
      String yearsOfExperience;

      public Programador(String name, String mainLanguage, String yearsOfExperience) {
          this.name = name;
          this.MainLanguage = mainLanguage;
          this.yearsOfExperience = yearsOfExperience;
      }

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
  }

