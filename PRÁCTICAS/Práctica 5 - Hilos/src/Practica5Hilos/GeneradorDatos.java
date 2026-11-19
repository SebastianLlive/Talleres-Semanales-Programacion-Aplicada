package Practica5Hilos;

import java.io.*;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class GeneradorDatos {

    public static ArrayList<Mecanico> cargarMecanicos()
            throws IOException {

        ArrayList<Mecanico> lista = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(
                new FileReader("mecanicos.txt"))) {

            String linea;
            int codigo = 1;

            while ((linea = br.readLine()) != null) {

                if (!linea.trim().isEmpty()) {
                    lista.add(new Mecanico(codigo++, linea.trim()));
                }
            }
        }

        return lista;
    }

    public static ArrayList<Vehiculo> cargarVehiculos()
            throws IOException {

        ArrayList<Vehiculo> lista = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(
                new FileReader("vehiculos.txt"))) {

            String linea;

            while ((linea = br.readLine()) != null) {

                if (!linea.trim().isEmpty()) {

                    String[] datos = linea.split(",");

                    if (datos.length != 2 ||
                        datos[0].trim().isEmpty() ||
                        datos[1].trim().isEmpty()) {

                        throw new IOException(
                            "Datos incorrectos en vehiculos.txt: " + linea
                        );
                    }

                    String marca = datos[0].trim();
                    String placa = datos[1].trim();

                    Gases gases = new Gases(
                        ThreadLocalRandom.current().nextDouble(0.7, 1.5),
                        ThreadLocalRandom.current().nextDouble(1, 3),
                        ThreadLocalRandom.current().nextDouble(20, 65),
                        ThreadLocalRandom.current().nextDouble(10, 25)
                    );

                    lista.add(new Vehiculo(marca, placa, gases));
                }
            }
        }

        return lista;
    }
}
