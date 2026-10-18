package ParqueaderoAutos;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class GestorArchivos {
	
    // Archivo de vehículos
    private static final String ARCHIVO_VEHICULOS = "vehiculos.txt";

    // Archivo de caja
    private static final String ARCHIVO_CAJA = "caja.txt";

    // Guardar los vehículos que siguen estacionados
    public static void guardarVehiculos(
            ArrayList<Vehiculo> vehiculos) {

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO_VEHICULOS))) {

            for (Vehiculo v : vehiculos) {
            	bw.write(
                    v.getPlaca() + ";" +
                    v.getMarca() + ";" +
                    v.getModelo() + ";" +
                    v.getHoraIngreso()
                );

                bw.newLine();
            }

        } catch (IOException e) {
            System.out.println("Error al guardar los vehiculos: "+ e.getMessage());
        }
    }

    // Recuperar los vehículos al iniciar el programa
    public static ArrayList<Vehiculo> cargarVehiculos() {

        ArrayList<Vehiculo> vehiculos = new ArrayList<>();
        File archivo = new File(ARCHIVO_VEHICULOS);

        if (!archivo.exists()) {
            return vehiculos;
        }

        try (Scanner lector = new Scanner(archivo)) {

            while (lector.hasNextLine()) {
                String linea = lector.nextLine();
                String[] datos = linea.split(";");

                if (datos.length == 4) {
                    Vehiculo v = new Vehiculo(
                            datos[0],
                            datos[1],
                            datos[2],
                            Long.parseLong(datos[3])
                    );

                    vehiculos.add(v);
                }
            }

        } catch (IOException | NumberFormatException e) {
            System.out.println("Error al leer los vehiculos: "+ e.getMessage());
        }

        return vehiculos;
    }

    // Agregar un pago sin borrar los anteriores
    public static void guardarPago(double valor) {

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO_CAJA, true))) {
            bw.write(String.valueOf(valor));
            bw.newLine();

        } catch (IOException e) {
            System.out.println("Error al guardar el pago: "+ e.getMessage());
        }
    }

    // Sumar todos los pagos guardados
    public static double cargarRecaudacion() {

        double total = 0.0;
        File archivo = new File(ARCHIVO_CAJA);

        if (!archivo.exists()) {
            return total;
        }

        try (Scanner lector = new Scanner(archivo)) {

            while (lector.hasNextLine()) {
                String linea = lector.nextLine().trim();

                if (!linea.isEmpty()) {
                    total += Double.parseDouble(linea);
                }
            }

        } catch (IOException | NumberFormatException e) {
            System.out.println("Error al leer la caja: "+ e.getMessage());
        }

        return total;
    }
}
