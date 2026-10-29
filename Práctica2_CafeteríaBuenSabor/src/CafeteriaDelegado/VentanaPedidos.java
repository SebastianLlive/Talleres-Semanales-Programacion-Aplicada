package CafeteriaDelegado;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import CafeteriaModelo.UtilGenerico;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import CafeteriaModelo.Detalle_Pedido;
import CafeteriaModelo.Gestion_Inventario;
import CafeteriaModelo.Gestion_Pedidos;
import CafeteriaModelo.Pedido;
import CafeteriaModelo.Producto;

public class VentanaPedidos extends JFrame {

    private static final long serialVersionUID = 1L;

    private Gestion_Inventario gestionInventario;
    private Gestion_Pedidos gestionPedidos;
    private Pedido pedidoActual;

    private JPanel contentPane;
    private JComboBox<Producto> comboProductos;
    private JTextField campoCantidad;
    private JTable tablaDetalles;
    private DefaultTableModel modeloTabla;

    private JLabel etiquetaSubtotal;
    private JLabel etiquetaIVA;
    private JLabel etiquetaTotal;

    private static int contadorPedidos = 1;

    private static final double PORCENTAJE_IVA = 0.15;

    public VentanaPedidos() {

        gestionInventario = new Gestion_Inventario();
        gestionPedidos = new Gestion_Pedidos();

        pedidoActual = new Pedido(contadorPedidos++);

        setTitle("El Buen Sabor - Gestión de pedidos");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(850, 550);
        setLocationRelativeTo(null);
        setResizable(true);

        contentPane = new JPanel(new BorderLayout(10, 10));
        contentPane.setBorder(new EmptyBorder(15, 15, 15, 15));
        setContentPane(contentPane);

        // Título
        JLabel titulo = new JLabel(
                "GESTIÓN DE PEDIDOS",
                SwingConstants.CENTER
        );

        titulo.setFont(new Font("Arial", Font.BOLD, 22));

        contentPane.add(titulo, BorderLayout.NORTH);

        // Panel para seleccionar productos
        JPanel panelSeleccion = new JPanel(new GridLayout(2, 2, 10, 10));

        panelSeleccion.add(new JLabel("Seleccione un producto:"));

        comboProductos = new JComboBox<>();
        panelSeleccion.add(comboProductos);

        panelSeleccion.add(new JLabel("Cantidad:"));

        campoCantidad = new JTextField();
        panelSeleccion.add(campoCantidad);

        // Tabla de detalles
        modeloTabla = new DefaultTableModel(
                new Object[]{
                    "Código",
                    "Producto",
                    "Precio",
                    "Cantidad",
                    "Subtotal"
                }, 0
        ) {
            private static final long serialVersionUID = 1L;

            @Override
            public boolean isCellEditable(int fila, int columna) {
                return false;
            }
        };

        tablaDetalles = new JTable(modeloTabla);

        // Panel central
        JPanel panelCentral = new JPanel(new BorderLayout(10, 10));

        panelCentral.add(panelSeleccion, BorderLayout.NORTH);
        panelCentral.add(
                new JScrollPane(tablaDetalles),
                BorderLayout.CENTER
        );

        contentPane.add(panelCentral, BorderLayout.CENTER);

        // Panel inferior
        JPanel panelInferior = new JPanel(new BorderLayout(10, 10));

        JPanel panelTotales = new JPanel(new GridLayout(3, 1, 5, 5));

        etiquetaSubtotal = new JLabel("Subtotal: $0.00");
        etiquetaIVA = new JLabel("IVA (15%): $0.00");
        etiquetaTotal = new JLabel("Total: $0.00");

        etiquetaTotal.setFont(new Font("Arial", Font.BOLD, 16));

        panelTotales.add(etiquetaSubtotal);
        panelTotales.add(etiquetaIVA);
        panelTotales.add(etiquetaTotal);

        panelInferior.add(panelTotales, BorderLayout.WEST);

        JPanel panelBotones = new JPanel(new FlowLayout());

        JButton botonAgregar = new JButton("Agregar producto");
        JButton botonQuitar = new JButton("Quitar producto");
        JButton botonConfirmar = new JButton("Confirmar pedido");
        JButton botonVolver = new JButton("Volver");

        panelBotones.add(botonAgregar);
        panelBotones.add(botonQuitar);
        panelBotones.add(botonConfirmar);
        panelBotones.add(botonVolver);

        panelInferior.add(panelBotones, BorderLayout.SOUTH);

        contentPane.add(panelInferior, BorderLayout.SOUTH);

        // Eventos
        botonAgregar.addActionListener(e -> agregarProducto());
        botonQuitar.addActionListener(e -> quitarProducto());
        botonConfirmar.addActionListener(e -> confirmarPedido());
        botonVolver.addActionListener(e -> dispose());

        cargarProductos();

    }

    private void cargarProductos() {

        comboProductos.removeAllItems();

        ArrayList<Producto> productosOriginales =
                gestionInventario.getProductos();

        // Crear una lista utilizando el método genérico
        ArrayList<Producto> productos = UtilGenerico.crearLista();

        // Agregar los productos utilizando el método genérico
        UtilGenerico.agregarElementos(
                productos,
                productosOriginales.toArray(new Producto[0])
        );

        for (Producto producto : productos) {
            comboProductos.addItem(producto);
        }

        // Mostrar nombre, código y precio en el combo
        ListCellRenderer<? super Producto> renderer =
                new javax.swing.DefaultListCellRenderer() {

            private static final long serialVersionUID = 1L;

            @Override
            public java.awt.Component getListCellRendererComponent(
                    javax.swing.JList<?> lista,
                    Object valor,
                    int indice,
                    boolean seleccionado,
                    boolean tieneFoco) {

                super.getListCellRendererComponent(
                        lista,
                        valor,
                        indice,
                        seleccionado,
                        tieneFoco
                );

                if (valor instanceof Producto) {
                    Producto producto = (Producto) valor;

                    setText(
                            producto.getCodigo() + " - "
                            + producto.getNombre()
                            + " ($" + producto.getPrecio() + ")"
                    );
                }

                return this;
            }
        };

        comboProductos.setRenderer(renderer);
    }

    private void agregarProducto() {

        try {

            Producto productoSeleccionado =
                    (Producto) comboProductos.getSelectedItem();

            if (productoSeleccionado == null) {

                JOptionPane.showMessageDialog(
                        this,
                        "No hay productos disponibles. Registre productos primero.",
                        "Sin productos",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }

            String textoCantidad = campoCantidad.getText().trim();

            if (textoCantidad.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Ingrese la cantidad del producto.",
                        "Campo obligatorio",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }

            int cantidad = Integer.parseInt(textoCantidad);

            if (cantidad <= 0) {

                JOptionPane.showMessageDialog(
                        this,
                        "La cantidad debe ser mayor que cero.",
                        "Cantidad inválida",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }

            // Comprobar el stock disponible
            if (!gestionInventario.hayStock(
                    productoSeleccionado.getCodigo(), cantidad)) {

                JOptionPane.showMessageDialog(
                        this,
                        "No existe suficiente stock para esa cantidad.",
                        "Stock insuficiente",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }

            // Agregar el detalle al pedido mediante la lógica del modelo
            boolean agregado = gestionPedidos.agregarProductoPedido(
                    pedidoActual,
                    gestionInventario,
                    productoSeleccionado.getCodigo(),
                    cantidad
            );

            if (!agregado) {

                JOptionPane.showMessageDialog(
                        this,
                        "No se pudo agregar el producto al pedido.",
                        "Error",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }

            actualizarTabla();
            actualizarTotales();

            campoCantidad.setText("");
            campoCantidad.requestFocusInWindow();

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Ingrese una cantidad entera válida.",
                    "Error en la cantidad",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void quitarProducto() {

        int fila = tablaDetalles.getSelectedRow();

        if (fila == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Seleccione un producto de la tabla.",
                    "Seleccione una fila",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        int indice = tablaDetalles.convertRowIndexToModel(fila);

        pedidoActual.getDetalles().remove(indice);

        actualizarTabla();
        actualizarTotales();
    }

    private void actualizarTabla() {

        modeloTabla.setRowCount(0);

        for (Detalle_Pedido detalle : pedidoActual.getDetalles()) {

            Producto producto = detalle.getProducto();

            modeloTabla.addRow(new Object[]{
                producto.getCodigo(),
                producto.getNombre(),
                String.format("%.2f", producto.getPrecio()),
                detalle.getUnidades(),
                String.format("%.2f", detalle.getSubtotal())
            });
        }
    }

    private void actualizarTotales() {

        pedidoActual.totalPedido();

        double subtotal = pedidoActual.getTotal();
        double iva = subtotal * PORCENTAJE_IVA;
        double total = subtotal + iva;

        etiquetaSubtotal.setText(
                String.format("Subtotal: $%.2f", subtotal)
        );

        etiquetaIVA.setText(
                String.format("IVA (15%%): $%.2f", iva)
        );

        etiquetaTotal.setText(
                String.format("Total: $%.2f", total)
        );
    }

    private void confirmarPedido() {

        if (pedidoActual.getDetalles().isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Agregue al menos un producto antes de confirmar.",
                    "Pedido vacío",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        int respuesta = JOptionPane.showConfirmDialog(
                this,
                "¿Desea confirmar el pedido?",
                "Confirmar pedido",
                JOptionPane.YES_NO_OPTION
        );

        if (respuesta != JOptionPane.YES_OPTION) {
            return;
        }

        boolean confirmado = gestionPedidos.confirmarPedido(
                pedidoActual,
                gestionInventario
        );

        if (confirmado) {

            double subtotal = pedidoActual.getTotal();
            double iva = subtotal * PORCENTAJE_IVA;
            double total = subtotal + iva;

            JOptionPane.showMessageDialog(
                    this,
                    String.format(
                            "¡Pedido confirmado!%n%n"
                            + "Número de pedido: %d%n"
                            + "Subtotal: $%.2f%n"
                            + "IVA (15%%): $%.2f%n"
                            + "Total: $%.2f",
                            pedidoActual.getIdPedido(),
                            subtotal,
                            iva,
                            total
                    ),
                    "Pedido registrado",
                    JOptionPane.INFORMATION_MESSAGE
            );

            // Preparar un nuevo pedido
            pedidoActual = new Pedido(contadorPedidos++);

            actualizarTabla();
            actualizarTotales();
            cargarProductos();

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "No se pudo confirmar el pedido. "
                    + "Verifique el stock disponible.",
                    "Error al confirmar",
                    JOptionPane.ERROR_MESSAGE
            );

            cargarProductos();
        }
    }
}