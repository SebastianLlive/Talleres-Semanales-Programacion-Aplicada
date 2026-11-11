package Cafeteria;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class Gestor_Archivos {
	private static final String ARCHIVO_PRODUCTOS = "productos.txt";
	private static final String ARCHIVO_PEDIDOS = "pedidos.txt";
	
	public static void guardarProductos(ArrayList<Producto> productos) {
	    try {
	        FileWriter escritor = new FileWriter(ARCHIVO_PRODUCTOS);

	        for (Producto producto : productos) {
	            escritor.write(
	                producto.getCodigo() + ";" +
	                producto.getNombre() + ";" +
	                producto.getPrecio() + ";" +
	                producto.getStock() + "\n"
	            );
	        }

	        escritor.close();

	    } catch (IOException e) {
	        System.out.println("Error al guardar los productos...");
	    }
	}
	
	public static void guardarPedidos(ArrayList<Pedido> pedidos) {
	    try {
	        FileWriter escritor = new FileWriter(ARCHIVO_PEDIDOS);

	        for (Pedido pedido : pedidos) {
	            for (Detalle_Pedido detalle : pedido.getDetalles()) {
	                escritor.write(
	                    pedido.getIdPedido() + ";" +
	                    detalle.getProducto().getCodigo() + ";" +
	                    detalle.getProducto().getNombre() + ";" +
	                    detalle.getUnidades() + ";" +
	                    detalle.getProducto().getPrecio() + ";" +
	                    detalle.getSubtotal() + "\n"
	                );
	            }
	        }

	        escritor.close();

	    } catch (IOException e) {
	        System.out.println("Error al guardar los pedidos...");
	    }
	}
	
	public static ArrayList<Producto> cargarProductos() {
	    ArrayList<Producto> productos = new ArrayList<>();
	    File archivo = new File(ARCHIVO_PRODUCTOS);

	    if (archivo.exists()) {
	        try {
	            Scanner lector = new Scanner(archivo);

	            while (lector.hasNextLine()) {
	                String linea = lector.nextLine();
	                String[] datos = linea.split(";");

	                Producto producto = new Producto(
	                    datos[0],
	                    datos[1],
	                    Double.parseDouble(datos[2]),
	                    Integer.parseInt(datos[3])
	                );

	                productos.add(producto);
	            }

	            lector.close();

	        } catch (IOException e) {
	            System.out.println("Error al cargar los productos...");
	        }
	    }

	    return productos;
	}
	
	
}