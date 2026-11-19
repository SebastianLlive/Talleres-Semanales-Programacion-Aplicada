package Practica5Hilos;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class ProcesoMecanico implements Runnable {

    private Mecanico mecanico;
    private ArrayList<Vehiculo> vehiculos;

    public ProcesoMecanico(Mecanico mecanico,
                           ArrayList<Vehiculo> vehiculos) {

        this.mecanico = mecanico;
        this.vehiculos = vehiculos;
    }

    @Override
    public void run() {

        try {

            int tiempo = ThreadLocalRandom.current()
                    .nextInt(0, 3001);

            Thread.sleep(tiempo);

            synchronized (System.out) {

                System.out.println("\n==============================================");
                System.out.println("MECANICO: " + mecanico.getNombre());
                System.out.println("CODIGO: " + mecanico.getCodigo());
                System.out.println("TIEMPO DE ESPERA: " + tiempo + " ms");
                System.out.println("==============================================");

                System.out.printf(
                    "%-12s %-12s %-10s %-10s %-10s %-10s %-12s %-25s%n",
                    "MARCA", "PLACA", "LAMBDA", "O2",
                    "N2O", "CO2", "% ERROR", "RESULTADO"
                );

                int aprobados = 0;
                int reprobados = 0;

                for (Vehiculo v : vehiculos) {

                    boolean resultado =
                            EvaluadorGases.evaluarVehiculo(v);

                    if (resultado) {
                        aprobados++;
                    } else {
                        reprobados++;
                    }
                }

                System.out.println("----------------------------------------------");
                System.out.println("TOTAL DE VEHICULOS: " + vehiculos.size());
                System.out.println("APROBADOS: " + aprobados);
                System.out.println("SEGUNDA REVISION: " + reprobados);
                System.out.println("==============================================");
            }

        } catch (InterruptedException e) {

            System.out.println(
                "Proceso interrumpido: " + mecanico.getNombre()
            );

            Thread.currentThread().interrupt();
        }
    }
}
