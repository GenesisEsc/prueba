package org.example;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Clase principal del programa.
 * Contiene un menú interactivo para crear paquetes nacionales o internacionales,
 * mostrar sus resúmenes y manejar excepciones de validación.
 */
public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in); // Scanner para leer datos desde consola
        ArrayList<Paquete> paquetes = new ArrayList<>(); // Lista para almacenar paquetes creados
        int opcion; // variable para controlar la opción del menú

        //  BUCLE DEL MENU
        do {
            System.out.println("\n--- MENU DE ENVÍOS ---");
            System.out.println("1. Crear Paquete Nacional");
            System.out.println("2. Crear Paquete Internacional");
            System.out.println("3. Mostrar Resumen de Paquetes");
            System.out.println("4. Salir");
            System.out.print("Elija una opción: ");
            opcion = sc.nextInt();
            sc.nextLine(); // limpiar buffer después de leer int

            switch (opcion) {

                case 1:
                    System.out.print("Ingrese zona (COSTA, SIERRA, AMAZONIA, GALAPAGOS): ");
                    String zona = sc.nextLine();
                    try {
                        // Crear un objeto de tipo PaqueteNacional usando el método auxiliar
                        PaqueteNacional pn = crearPaqueteNacional(sc, zona);
                        paquetes.add(pn); // agregar a la lista
                        System.out.println("Paquete Nacional creado correctamente.");
                    } catch (IllegalArgumentException e) {
                        // Captura errores de validación (peso, dimensiones, zona)
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 2:
                    System.out.print("Ingrese región (EUROPE, ASIA, AMERICA, etc.): ");
                    String region = sc.nextLine();
                    try {
                        // Crear un objeto de tipo PaqueteInternacional usando el método auxiliar
                        PaqueteInternacional pi = crearPaqueteInternacional(sc, region);
                        paquetes.add(pi); // agregar a la lista
                        System.out.println("Paquete Internacional creado correctamente.");
                    } catch (IllegalArgumentException e) {
                        // Captura errores de validación (peso, dimensiones, región)
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 3:
                    if (paquetes.isEmpty()) {
                        // Si no hay paquetes, mostrar mensaje
                        System.out.println("No hay paquetes creados.");
                    } else {
                        // Mostrar resumen de todos los paquetes
                        System.out.println("\n--- RESUMEN DE PAQUETES ---");
                        for (Paquete p : paquetes) {
                            System.out.println(p.resumen()); // polimorfismo: llama a resumen() específico
                            System.out.println(); // salto de línea entre paquetes
                        }
                    }
                    break;

                case 4:
                    System.out.println("Saliendo del programa..."); // opción de salida
                    break;

                default:
                    // Control de opciones inválidas
                    System.out.println("Opción inválida. Intente nuevamente.");
            }

        } while (opcion != 4); // repetir hasta que el usuario elija salir

        sc.close(); // cerrar Scanner para liberar recursos
    }

    // METODOS AUXILIARES

    /**
     * Metodo auxiliar para crear un paquete nacional desde consola
     * @param sc Scanner para leer datos
     * @param zona Zona del paquete
     * @return objeto PaqueteNacional
     */
    private static PaqueteNacional crearPaqueteNacional(Scanner sc, String zona) {
        System.out.print("Peso (kg): ");
        double peso = sc.nextDouble();
        System.out.print("Largo (cm): ");
        double largo = sc.nextDouble();
        System.out.print("Ancho (cm): ");
        double ancho = sc.nextDouble();
        System.out.print("Alto (cm): ");
        double alto = sc.nextDouble();
        sc.nextLine(); // limpiar buffer
        System.out.print("Contenido: ");
        String contenido = sc.nextLine();

        // Crea y devuelve el objeto PaqueteNacional con los datos ingresados
        return new PaqueteNacional(peso, largo, ancho, alto, contenido, zona);
    }

    /**
     * Metodo auxiliar para crear un paquete internacional desde consola
     * @param sc Scanner para leer datos
     * @param region Región del paquete
     * @return objeto PaqueteInternacional
     */
    private static PaqueteInternacional crearPaqueteInternacional(Scanner sc, String region) {
        System.out.print("Peso (kg): ");
        double peso = sc.nextDouble();
        System.out.print("Largo (cm): ");
        double largo = sc.nextDouble();
        System.out.print("Ancho (cm): ");
        double ancho = sc.nextDouble();
        System.out.print("Alto (cm): ");
        double alto = sc.nextDouble();
        sc.nextLine(); // limpiar buffer
        System.out.print("Contenido: ");
        String contenido = sc.nextLine();

        // Crea y devuelve el objeto PaqueteInternacional con los datos ingresados
        return new PaqueteInternacional(peso, largo, ancho, alto, contenido, region);
    }
}



