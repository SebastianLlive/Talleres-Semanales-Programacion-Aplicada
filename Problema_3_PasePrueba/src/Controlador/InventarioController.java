
package Controlador;

import java.util.ArrayList;

import Modelo.Producto;
import Vista.InventarioView;

public class InventarioController {

    private InventarioView vista;
    private ArrayList<Producto> productos;

    public InventarioController() {

        productos = new ArrayList<>();
        vista = new InventarioView();

        vista.getBtnRegistrar().addActionListener(e -> registrarProducto());
        vista.getBtnAumentar().addActionListener(e -> aumentarStock());
        vista.getBtnDisminuir().addActionListener(e -> disminuirStock());
        vista.getBtnConsultar().addActionListener(e -> consultarStock());

        vista.setVisible(true);
    }

    public static void main(String[] args) {
        new InventarioController();
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

    // Buscar producto
    public Producto buscarProducto(String nombre) {

        for (Producto p : productos) {

            if (p.getNombre().equalsIgnoreCase(nombre)) {
                return p;
            }
        }

        return null;
    }

    // RF1: Registrar producto
    public void registrarProducto() {

        String nombre = vista.obtenerNombre();
        String cantidadTexto = vista.obtenerCantidad();

        if (nombre.isEmpty() || cantidadTexto.isEmpty()) {
            vista.mostrarMensaje("Complete todos los campos");
            return;
        }

        try {

            int cantidad = Integer.parseInt(cantidadTexto);

            if (cantidad < 0) {
                vista.mostrarMensaje("La cantidad no puede ser negativa");
                return;
            }

            if (buscarProducto(nombre) != null) {
                vista.mostrarMensaje("El producto ya existe");
                return;
            }

            Producto nuevo = new Producto(nombre, cantidad);
            productos.add(nuevo);

            vista.mostrarMensaje("Producto registrado correctamente");
            vista.limpiarCampos();

        } catch (NumberFormatException e) {
            vista.mostrarMensaje("Ingrese una cantidad valida");
        }
    }

    // RF2: Aumentar stock
    public void aumentarStock() {

        String nombre = vista.obtenerNombre();
        String cantidadTexto = vista.obtenerCantidad();

        if (nombre.isEmpty() || cantidadTexto.isEmpty()) {
            vista.mostrarMensaje("Complete todos los campos");
            return;
        }

        try {

            int cantidad = Integer.parseInt(cantidadTexto);

            if (cantidad <= 0) {
                vista.mostrarMensaje("Ingrese una cantidad mayor a cero");
                return;
            }

            Producto p = buscarProducto(nombre);

            if (p == null) {
                vista.mostrarMensaje("Producto no encontrado");
                return;
            }

            p.setCantidad(p.getCantidad() + cantidad);

            vista.mostrarMensaje("Stock aumentado correctamente");
            vista.limpiarCampos();

        } catch (NumberFormatException e) {
            vista.mostrarMensaje("Ingrese una cantidad valida");
        }
    }

    // RF3: Disminuir stock
    public void disminuirStock() {

        String nombre = vista.obtenerNombre();
        String cantidadTexto = vista.obtenerCantidad();

        if (nombre.isEmpty() || cantidadTexto.isEmpty()) {
            vista.mostrarMensaje("Complete todos los campos");
            return;
        }

        try {

            int cantidad = Integer.parseInt(cantidadTexto);

            if (cantidad <= 0) {
                vista.mostrarMensaje("Ingrese una cantidad mayor a cero");
                return;
            }

            Producto p = buscarProducto(nombre);

            if (p == null) {
                vista.mostrarMensaje("Producto no encontrado");
                return;
            }

            if (cantidad > p.getCantidad()) {
                vista.mostrarMensaje("No existe suficiente stock");
                return;
            }

            p.setCantidad(p.getCantidad() - cantidad);

            vista.mostrarMensaje("Stock disminuido correctamente");
            vista.limpiarCampos();

        } catch (NumberFormatException e) {
            vista.mostrarMensaje("Ingrese una cantidad valida");
        }
    }

    // RF4: Consultar stock
    public void consultarStock() {

        String nombre = vista.obtenerNombre();

        if (nombre.isEmpty()) {
            vista.mostrarMensaje("Ingrese el nombre del producto");
            return;
        }

        Producto p = buscarProducto(nombre);

        if (p == null) {
            vista.mostrarMensaje("Producto no encontrado");
            return;
        }

        vista.mostrarMensaje(
            "Producto: " + p.getNombre()
            + "\nCantidad disponible: " + p.getCantidad()
        );
    }
}
