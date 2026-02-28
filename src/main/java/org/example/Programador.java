package org.example;
import java.util.Scanner;

  public  class Programador {
      String name;
      String MainLanguage;
      String yearsOfExperience;

      void evaluateProfile() {
          // We convert the String to an integer to perform comparisons
          int years = Integer.parseInt(yearsOfExperience);
          String rank;

          // Decision Logic (IF - ELSE Structure)
          if (years < 2) {
              rank = "Junior (You are just starting your journey!)";
          } else if (years >= 2 && years < 5) {
              rank = "mid-level (You have a solid traack record";
          } else {
              rank = "Senior (You are an expert)";
          }

          System.out.println("\n--- ANALYSIS RESULT ---\n");
          System.out.println("Developer: " + name);
          System.out.println("Main Language: " + MainLanguage);
          System.out.println("Assigned Rank: " + rank);
          System.out.println("\n--- ANALYSIS COMPLETE---");
      }
      public static void  main(String[] args) {
          Scanner lector = new Scanner(System.in);

          Programador nuevoTalento = new Programador();

          System.out.println("--- Registro de Desarrollador ---");
          System.out.println("Introduce tu nombre: ");
          nuevoTalento.name = lector .nextLine();

          System.out.println("Que lenguaje estas aprendiendo?: ");
          nuevoTalento.MainLanguage = lector .nextLine();

          System.out.println("Cuantos años tienes de experiencia?");
          nuevoTalento.yearsOfExperience = lector .nextLine();
//Pruebaaa
          nuevoTalento.evaluateProfile();
          System.out.println("¡Configuracion guardada para " + nuevoTalento.name + "!");


      }
  }


