package Cafeteria;

import java.util.ArrayList;

public class Gestion_Inventario {
	private ArrayList<Producto> productos;
	
	public Gestion_Inventario() {
	    this.productos = Gestor_Archivos.cargarProductos();
	} 
	
	public void registrarProducto(Producto producto) {
	    for (Producto existente : this.productos) {
	        if (existente.getCodigo().equals(producto.getCodigo())) {
	            System.out.println("Ya existe un producto con ese código...");
	            return;
	        }
	    }

	    this.productos.add(producto);
	    Gestor_Archivos.guardarProductos(this.productos);
	    System.out.println("Producto registrado correctamente...");
	}
	
	public void listarProductos() {
	    for (Producto producto : this.productos) {
	        System.out.println("Código: " + producto.getCodigo());
	        System.out.println("Nombre: " + producto.getNombre());
	        System.out.println("Precio: " + producto.getPrecio());
	        System.out.println("Stock: " + producto.getStock());
	        System.out.println("------------------------");
	    }
	}
	
	public void reponerStock(String codigo, int unidades) {
	    if (unidades <= 0) {
	        System.out.println("Las unidades deben ser mayores que cero...");
	        return;
	    }

	    for (Producto producto : this.productos) {
	        if (producto.getCodigo().equals(codigo)) {
	            producto.setStock(producto.getStock() + unidades);
	            Gestor_Archivos.guardarProductos(this.productos);
	            System.out.println("Stock actualizado correctamente.");
	            return;
	        }
	    }

	    System.out.println("Producto no encontrado.");
	}
	
	public boolean hayStock(String codigo, int unidades) {
	    if (unidades <= 0) {
	        return false;
	    }

	    for (Producto producto : this.productos) {
	        if (producto.getCodigo().equals(codigo)) {
	            return producto.getStock() >= unidades;
	        }
	    }

	    return false;
	}
	
	public boolean descontarStock(String codigo, int unidades) {
	    if (unidades <= 0) {
	        return false;
	    }

	    for (Producto producto : this.productos) {
	        if (producto.getCodigo().equals(codigo)) {
	            if (producto.getStock() >= unidades) {
	                producto.setStock(producto.getStock() - unidades);
	                Gestor_Archivos.guardarProductos(this.productos);
	                return true;
	            } else {
	                return false;
	            }
	        }
	    }

	    return false;
	}
	
	public ArrayList<Producto> getProductos() {
	    return productos;
	}
	
	
}

