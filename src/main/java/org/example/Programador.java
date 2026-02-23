package org.example;
import java.util.Scanner;

  public  class Programador {
      String nombre;
      String lenguajePrincipal;
      String añosExperiencia;

      //programmer method what it can do
      //metodo programador lo que puede hacer
      void presentarse() {
          System.out.println("Hola, soy " + nombre + ". Domino " + lenguajePrincipal + " y tengo " + añosExperiencia + " años de Experiencia");
      }
    public static void  main(String[] args) {
        Scanner lector = new Scanner(System.in);

        Programador nuevoTalento = new Programador();

        System.out.println("--- Registro de Desarrollador ---");
        System.out.println("Introduce tu nombre: ");
        nuevoTalento.nombre = lector .nextLine();

        System.out.println("Que lenguaje estas aprendiendo?: ");
        nuevoTalento.lenguajePrincipal = lector .nextLine();

        System.out.println("Cuantos años tienes de experiencia?");
        nuevoTalento.añosExperiencia = lector .nextLine();

        nuevoTalento.presentarse();
        System.out.println("¡Configuracion guardada para " + nuevoTalento.nombre + "!");
    }
  }