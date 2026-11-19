package Practica5Hilos;

import java.util.ArrayList;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        try {

            ArrayList<Mecanico> mecanicos =
                    GeneradorDatos.cargarMecanicos();

            ArrayList<Vehiculo> vehiculos =
                    GeneradorDatos.cargarVehiculos();

            if (mecanicos.size() != 10 || vehiculos.size() != 50) {

                System.out.println(
                    "Error: se necesitan 10 mecanicos y 50 vehiculos."
                );

                return;
            }

            System.out.println("SISTEMA DE REVISION VEHICULAR");
            System.out.println("Iniciando procesos mediante hilos...");

            ArrayList<Thread> hilos = new ArrayList<>();

            for (int i = 0; i < mecanicos.size(); i++) {

                ArrayList<Vehiculo> lote = new ArrayList<>();

                for (int j = 0; j < 5; j++) {
                    lote.add(vehiculos.get(i * 5 + j));
                }

                ProcesoMecanico proceso =
                        new ProcesoMecanico(mecanicos.get(i), lote);

                Thread hilo = new Thread(proceso);

                hilos.add(hilo);

                hilo.start();
            }

            for (Thread hilo : hilos) {
                hilo.join();
            }

            System.out.println(
                "\nTODOS LOS PROCESOS HAN FINALIZADO."
            );

        } catch (IOException e) {

            System.out.println(
                "Error al leer los archivos: " + e.getMessage()
            );

        } catch (InterruptedException e) {

            System.out.println(
                "El proceso principal fue interrumpido."
            );

            Thread.currentThread().interrupt();
        }
    }
}
