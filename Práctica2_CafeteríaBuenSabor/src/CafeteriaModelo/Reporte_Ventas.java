
package CafeteriaModelo;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

public class Reporte_Ventas {

    public static void generarReporte() {
        File archivo = new File("pedidos.txt");

        double totalVentas = 0.0;
        int cantidadPedidos = 0;
        int unidadesVendidas = 0;

        Map<String, Integer> productosVendidos = new HashMap<>();
        Map<String, String> nombresProductos = new HashMap<>();

        if (!archivo.exists()) {
            System.out.println("Todavía no existen ventas registradas...");
            return;
        }

        try (Scanner lector = new Scanner(archivo)) {

            while (lector.hasNextLine()) {
                String linea = lector.nextLine();
                String[] datos = linea.split(";");

                if (datos.length < 6) {
                    continue;
                }

                String codigo = datos[1];
                String nombre = datos[2];
                int unidades = Integer.parseInt(datos[3]);
                double subtotal = Double.parseDouble(datos[5]);

                totalVentas += subtotal;
                unidadesVendidas += unidades;

                productosVendidos.put(
                    codigo,
                    productosVendidos.getOrDefault(codigo, 0) + unidades
                );

                nombresProductos.put(codigo, nombre);
                cantidadPedidos++;
            }

            System.out.println("\n===== REPORTE DE VENTAS =====");
            System.out.println("Registros de productos vendidos: "
                    + cantidadPedidos);
            System.out.println("Unidades vendidas: " + unidadesVendidas);
            System.out.printf("Total de ventas: $%.2f%n", totalVentas);

            System.out.println("\n===== PRODUCTOS MÁS DEMANDADOS =====");

            if (productosVendidos.isEmpty()) {
                System.out.println("No hay productos vendidos.");
            } else {
                int mayorCantidad = 0;

                for (Map.Entry<String, Integer> producto
                        : productosVendidos.entrySet()) {

                    System.out.println(
                        nombresProductos.get(producto.getKey())
                        + " (Código: " + producto.getKey() + ")"
                        + " - Unidades vendidas: " + producto.getValue()
                    );

                    if (producto.getValue() > mayorCantidad) {
                        mayorCantidad = producto.getValue();
                    }
                }

                System.out.println("\nProducto(s) más demandado(s):");

                for (Map.Entry<String, Integer> producto
                        : productosVendidos.entrySet()) {

                    if (producto.getValue() == mayorCantidad) {
                        System.out.println(
                            nombresProductos.get(producto.getKey())
                            + " - " + mayorCantidad + " unidades"
                        );
                    }
                }
            }

        } catch (IOException | NumberFormatException e) {
            System.out.println("Error al leer el archivo de pedidos...");
        }
    }
}
