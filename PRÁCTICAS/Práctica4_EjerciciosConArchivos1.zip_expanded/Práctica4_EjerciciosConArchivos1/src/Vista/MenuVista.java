
package Vista;

import java.util.ArrayList;
import java.util.Scanner;

import Modelo.Aportador;

public class MenuVista {

    private Scanner entrada;

    public MenuVista() {
        entrada = new Scanner(System.in);
    }

    public void mostrarMenu() {

        System.out.println("\n======================================");
        System.out.println("     SISTEMA DE INGRESOS ECONOMICOS");
        System.out.println("======================================");
        System.out.println("1. Analizar archivo de ingresos");
        System.out.println("2. Mostrar registros");
        System.out.println("3. Mostrar resumen de errores");
        System.out.println("4. Salir");
        System.out.println("======================================");
    }

    public int leerOpcion() {

        System.out.print("Seleccione una opcion: ");

        try {
            return Integer.parseInt(entrada.nextLine());

        } catch (NumberFormatException e) {
            System.out.println("Ingrese un numero valido.");
            return -1;
        }
    }

    public void mostrarRegistros(ArrayList<Aportador> aportadores) {

        System.out.println("\n========== REGISTROS DE APORTADORES ==========");

        int contador = 1;

        for (Aportador a : aportadores) {

            System.out.println("\nAportador #" + contador);

            System.out.println("Cedula: " +
                (a.getCedula().isEmpty() ?
                "NO ENCONTRADA" : a.getCedula()));

            System.out.println("Estado cedula: " +
                (a.isCedulaValida() ? "VALIDA" : "ERROR"));

            System.out.println("Correo: " +
                (a.getCorreo().isEmpty() ?
                "NO ENCONTRADO" : a.getCorreo()));

            System.out.println("Estado correo: " +
                (a.isCorreoValido() ? "VALIDO" : "ERROR"));

            System.out.printf("Monto: $%.2f%n", a.getMonto());

            System.out.println("Estado monto: " +
                (a.isMontoValido() ? "VALIDO" : "ERROR"));

            if (a.isCedulaValida()
                    && a.isCorreoValido()
                    && a.isMontoValido()) {

                System.out.println("RESULTADO: REGISTRO VALIDO");

            } else {

                System.out.println("RESULTADO: REGISTRO CON ERRORES");
            }

            contador++;
        }

        System.out.println("\nTotal de registros: " + aportadores.size());
    }

    public void mostrarResumen(double total, int erroresCedula,
            int erroresCorreo, int erroresMonto) {

        System.out.println("\n========== RESUMEN DE ERRORES ==========");

        System.out.printf("Total de ingresos validos: $%.2f%n", total);

        System.out.println("Cedulas con errores: " + erroresCedula);

        System.out.println("Correos con errores: " + erroresCorreo);

        System.out.println("Montos con errores: " + erroresMonto);

        System.out.println("========================================");
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println("\n" + mensaje);
    }
}
