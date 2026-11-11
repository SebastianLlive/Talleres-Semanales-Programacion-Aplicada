package CafeteriaModelo;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

public class Reporte_Ventas {

    private static final String ARCHIVO_PEDIDOS = "pedidos.txt";

    // Método para mostrar el reporte en la consola
    public static void generarReporte() {

        System.out.println(obtenerReporte());
    }

    // Método para obtener el reporte y mostrarlo en la interfaz gráfica
    public static String obtenerReporte() {

        File archivo = new File(ARCHIVO_PEDIDOS);

        double totalVentas = 0.0;
        int registrosProductos = 0;
        int unidadesVendidas = 0;

        Map<String, Integer> productosVendidos = new HashMap<>();
        Map<String, String> nombresProductos = new HashMap<>();

        StringWriter contenido = new StringWriter();
        PrintWriter salida = new PrintWriter(contenido);

        // Verificar si existe el archivo
        if (!archivo.exists()) {
            return "Todavía no existen ventas registradas.";
        }

        try (Scanner lector = new Scanner(archivo)) {

            while (lector.hasNextLine()) {

                String linea = lector.nextLine();
                String[] datos = linea.split(";");

                // Ignorar líneas que no tengan los campos necesarios
                if (datos.length < 6) {
                    continue;
                }

                String codigo = datos[1];
                String nombre = datos[2];

                int unidades = Integer.parseInt(datos[3]);
                double subtotal = Double.parseDouble(datos[5]);

                // Calcular ventas y unidades
                totalVentas += subtotal;
                unidadesVendidas += unidades;
                registrosProductos++;

                // Acumular las unidades vendidas por producto
                productosVendidos.put(
                        codigo,
                        productosVendidos.getOrDefault(codigo, 0) + unidades
                );

                nombresProductos.put(codigo, nombre);
            }

            // Mostrar resumen de ventas
            salida.println("========== REPORTE DE VENTAS ==========\n");

            salida.println(
                    "Registros de productos vendidos: " + registrosProductos
            );

            salida.println("Unidades vendidas: " + unidadesVendidas);

            salida.printf("Total de ventas: $%.2f%n", totalVentas);

            salida.println("\n========== PRODUCTOS VENDIDOS ==========\n");

            if (productosVendidos.isEmpty()) {

                salida.println("No hay productos vendidos.");

            } else {

                int mayorCantidad = 0;

                // Mostrar todos los productos vendidos
                for (Map.Entry<String, Integer> producto
                        : productosVendidos.entrySet()) {

                    salida.println(
                            nombresProductos.get(producto.getKey())
                            + " (Código: " + producto.getKey() + ")"
                            + " - Unidades vendidas: " + producto.getValue()
                    );

                    if (producto.getValue() > mayorCantidad) {
                        mayorCantidad = producto.getValue();
                    }
                }

                // Mostrar los productos más vendidos
                salida.println(
                        "\n========== PRODUCTO(S) MÁS VENDIDO(S) ==========\n"
                );

                for (Map.Entry<String, Integer> producto
                        : productosVendidos.entrySet()) {

                    if (producto.getValue() == mayorCantidad) {

                        salida.println(
                                nombresProductos.get(producto.getKey())
                                + " - " + mayorCantidad + " unidades"
                        );
                    }
                }
            }

        } catch (IOException | NumberFormatException e) {

            return "Error al leer el archivo de pedidos: " + e.getMessage();
        }

        salida.flush();

        return contenido.toString();
    }
}