package CafeteriaModelo;

import java.util.ArrayList;

public class Gestion_Pedidos {
    private ArrayList<Pedido> pedidos;

    public Gestion_Pedidos() {
        this.pedidos = new ArrayList<>();
    }
    
    public void registrarPedido(Pedido pedido) {
        if (pedido.getDetalles().isEmpty()) {
            System.out.println("No se puede registrar un pedido vacío...");
            return;
        }

        pedido.totalPedido();
        this.pedidos.add(pedido);
        Gestor_Archivos.guardarPedidos(this.pedidos);

        System.out.println("Pedido registrado correctamente...");
        System.out.println("Total: $" + pedido.getTotal());
    }
    
    public Producto buscarProducto(Gestion_Inventario inventario, String codigo) {
        for (Producto producto : inventario.getProductos()) {
            if (producto.getCodigo().equals(codigo)) {
                return producto;
            }
        }

        return null;
    }
    
    public boolean agregarProductoPedido(
            Pedido pedido,
            Gestion_Inventario inventario,
            String codigo,
            int unidades) {

        if (pedido.isConfirmado()) {
            System.out.println("No se puede modificar un pedido confirmado...");
            return false;
        }

        Producto producto = buscarProducto(inventario, codigo);

        if (producto == null) {
            System.out.println("Producto no encontrado...");
            return false;
        }

        if (!inventario.hayStock(codigo, unidades)) {
            System.out.println("Stock insuficiente o unidades inválidas...");
            return false;
        }

        Detalle_Pedido detalle = new Detalle_Pedido(producto, unidades);
        pedido.agregarDetalle(detalle);

        return true;
    }
    
    public boolean confirmarPedido(Pedido pedido, Gestion_Inventario inventario) {
        if (pedido.isConfirmado()) {
            System.out.println("Este pedido ya fue confirmado.");
            return false;
        }

        if (pedido.getDetalles().isEmpty()) {
            System.out.println("No se puede confirmar un pedido vacío.");
            return false;
        }

        // Verificar el stock total solicitado de cada producto
        for (Detalle_Pedido detalle : pedido.getDetalles()) {
            String codigo = detalle.getProducto().getCodigo();
            int unidadesSolicitadas = 0;

            for (Detalle_Pedido otroDetalle : pedido.getDetalles()) {
                if (otroDetalle.getProducto().getCodigo().equals(codigo)) {
                    unidadesSolicitadas += otroDetalle.getUnidades();
                }
            }

            if (!inventario.hayStock(codigo, unidadesSolicitadas)) {
                System.out.println("Stock insuficiente para: "
                        + detalle.getProducto().getNombre());
                return false;
            }
        }

        // Descontar el stock después de validar todos los productos
        for (Detalle_Pedido detalle : pedido.getDetalles()) {
            inventario.descontarStock(
                    detalle.getProducto().getCodigo(),
                    detalle.getUnidades());
        }

        registrarPedido(pedido);
        pedido.setConfirmado(true);

        return true;
    }
    
    
}