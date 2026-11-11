
package CafeteriaDelegado;

import java.util.Scanner;

import CafeteriaModelo.Gestion_Inventario;
import CafeteriaModelo.Gestion_Pedidos;
import CafeteriaModelo.Pedido;
import CafeteriaModelo.Producto;
import CafeteriaModelo.Reporte_Ventas;

public class Principal {

    public static void main(String[] args) {

        Autenticacion autenticacion = new Autenticacion();
        Gestion_Inventario inventario = new Gestion_Inventario();
        Gestion_Pedidos gestionPedidos = new Gestion_Pedidos();
        Scanner entrada = new Scanner(System.in);

        System.out.println("===== EL BUEN SABOR =====");
        System.out.print("Usuario: ");
        String usuario = entrada.nextLine();

        System.out.print("Contraseña: ");
        String contraseña = entrada.nextLine();

        if (!autenticacion.validacionAutenticacion(usuario, contraseña)) {
            entrada.close();
            return;
        }

        System.out.println("Acceso concedido al sistema.");

        int opcion;

        do {
            System.out.println("\n===== MENÚ EL BUEN SABOR =====");
            System.out.println("1. Registrar producto");
            System.out.println("2. Listar productos");
            System.out.println("3. Reponer stock");
            System.out.println("4. Registrar pedido");
            System.out.println("5. Reporte de ventas");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");

            opcion = Integer.parseInt(entrada.nextLine());

            switch (opcion) {

                case 1:
                    System.out.println("\n===== REGISTRAR PRODUCTO =====");

                    System.out.print("Código: ");
                    String codigo = entrada.nextLine();

                    System.out.print("Nombre: ");
                    String nombre = entrada.nextLine();

                    System.out.print("Precio: ");
                    Double precio = Double.parseDouble(entrada.nextLine());

                    System.out.print("Stock inicial: ");
                    int stock = Integer.parseInt(entrada.nextLine());

                    Producto producto =
                            new Producto(codigo, nombre, precio, stock);

                    inventario.registrarProducto(producto);
                    break;

                case 2:
                    System.out.println("\n===== LISTA DE PRODUCTOS =====");
                    inventario.listarProductos();
                    break;

                case 3:
                    System.out.println("\n===== REPONER STOCK =====");

                    System.out.print("Código del producto: ");
                    String codigoReposicion = entrada.nextLine();

                    System.out.print("Unidades que desea agregar: ");
                    int unidades = Integer.parseInt(entrada.nextLine());

                    inventario.reponerStock(codigoReposicion, unidades);
                    break;

                case 4:
                    System.out.println("\n===== REGISTRAR PEDIDO =====");

                    System.out.print("Ingrese el ID del pedido: ");
                    int idPedido = Integer.parseInt(entrada.nextLine());

                    Pedido pedido = new Pedido(idPedido);
                    String respuesta;

                    do {
                        System.out.print("Código del producto: ");
                        String codigoProducto = entrada.nextLine();

                        System.out.print("Cantidad: ");
                        int cantidad = Integer.parseInt(entrada.nextLine());

                        boolean agregado =
                                gestionPedidos.agregarProductoPedido(
                                        pedido,
                                        inventario,
                                        codigoProducto,
                                        cantidad
                                );

                        if (agregado) {
                            System.out.println("Producto agregado al pedido...");
                        }

                        System.out.print("¿Desea agregar otro producto? (si/no): ");
                        respuesta = entrada.nextLine();

                    } while (respuesta.equalsIgnoreCase("si"));

                    if (gestionPedidos.confirmarPedido(pedido, inventario)) {
                        System.out.println(
                                "Pedido confirmado correctamente..."
                        );
                    }

                    break;

                case 5:
                	Reporte_Ventas.generarReporte();
                    break;

                case 6:
                    System.out.println("Saliendo...");
                    break;

                default:
                    System.out.println("Opción inválida...");
            }

        } while (opcion != 6);

        entrada.close();
    }
}
