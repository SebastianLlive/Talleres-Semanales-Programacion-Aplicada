
package Controlador;

import java.util.ArrayList;

import Modelo.Producto;
import Vista.ProductoView;

public class ProductoController {

    private ProductoView vista;
    private ArrayList<Producto> productos;

    public ProductoController() {

        productos = new ArrayList<>();
        vista = new ProductoView();

        vista.getBtnRegistrar().addActionListener(e -> registrarProducto());
        vista.getBtnListar().addActionListener(e -> listarProductos());
        vista.getBtnBuscar().addActionListener(e -> buscarProducto());
        vista.getBtnEliminar().addActionListener(e -> eliminarProducto());

        vista.setVisible(true);
    }

    public static void main(String[] args) {
        new ProductoController();
    }

    // Metodo generico
    public <T> void mostrarDato(T dato) {
        System.out.println(dato);
    }

    // Metodo Varargs
    public void mostrarMensajes(String... mensajes) {
        for (String mensaje : mensajes) {
            System.out.println(mensaje);
        }
    }

    // RF1: Registrar producto
    public void registrarProducto() {

        String nombre = vista.obtenerNombre();
        String precioTexto = vista.obtenerPrecio();

        if (nombre.isEmpty() || precioTexto.isEmpty()) {
            vista.mostrarMensaje("Complete todos los campos");
            return;
        }

        try {

            double precio = Double.parseDouble(precioTexto);

            if (precio <= 0) {
                vista.mostrarMensaje("El precio debe ser mayor a cero");
                return;
            }

            Producto nuevo = new Producto(nombre, precio);
            productos.add(nuevo);

            vista.mostrarMensaje("Producto registrado correctamente");
            vista.limpiarCampos();

        } catch (NumberFormatException e) {
            vista.mostrarMensaje("Ingrese un precio valido");
        }
    }

    // RF2: Listar productos
    public void listarProductos() {

        String texto = "";

        if (productos.isEmpty()) {
            vista.mostrarMensaje("No existen productos registrados");
            return;
        }

        for (Producto p : productos) {
            texto += "Nombre: " + p.getNombre()
                    + " | Precio: $" + p.getPrecio() + "\n";
        }

        vista.mostrarProductos(texto);
    }

    // RF3: Buscar producto
    public void buscarProducto() {

        String nombre = vista.obtenerNombre();

        if (nombre.isEmpty()) {
            vista.mostrarMensaje("Ingrese el nombre del producto");
            return;
        }

        for (Producto p : productos) {

            if (p.getNombre().equalsIgnoreCase(nombre)) {

                vista.mostrarMensaje(
                    "Producto encontrado: " + p.getNombre()
                    + "\nPrecio: $" + p.getPrecio()
                );
                return;
            }
        }

        vista.mostrarMensaje("Producto no encontrado");
    }

    // RF4: Eliminar producto
    public void eliminarProducto() {

        String nombre = vista.obtenerNombre();

        if (nombre.isEmpty()) {
            vista.mostrarMensaje("Ingrese el nombre del producto");
            return;
        }

        for (int i = 0; i < productos.size(); i++) {

            if (productos.get(i).getNombre().equalsIgnoreCase(nombre)) {

                productos.remove(i);
                vista.mostrarMensaje("Producto eliminado correctamente");
                vista.limpiarCampos();
                return;
            }
        }

        vista.mostrarMensaje("Producto no encontrado");
    }
}
