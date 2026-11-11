package CafeteriaDelegado;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import CafeteriaModelo.Gestion_Inventario;
import CafeteriaModelo.Producto;

public class VentanaProductos extends JFrame {

    private static final long serialVersionUID = 1L;

    private Gestion_Inventario gestionInventario;

    private JPanel contentPane;
    private JTextField campoCodigo;
    private JTextField campoNombre;
    private JTextField campoPrecio;
    private JTextField campoStock;
    private JTable tablaProductos;
    private DefaultTableModel modeloTabla;

    public VentanaProductos() {

        gestionInventario = new Gestion_Inventario();

        setTitle("El Buen Sabor - Gestión de productos");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(750, 500);
        setLocationRelativeTo(null);
        setResizable(true);

        contentPane = new JPanel(new BorderLayout(10, 10));
        contentPane.setBorder(new EmptyBorder(15, 15, 15, 15));
        setContentPane(contentPane);

        // Título
        JLabel titulo = new JLabel(
                "GESTIÓN DE PRODUCTOS",
                SwingConstants.CENTER
        );

        titulo.setFont(new java.awt.Font(
                "Arial",
                java.awt.Font.BOLD,
                20
        ));

        contentPane.add(titulo, BorderLayout.NORTH);

        // Formulario
        JPanel panelFormulario = new JPanel(
                new GridLayout(4, 2, 8, 8)
        );

        panelFormulario.add(new JLabel("Código:"));
        campoCodigo = new JTextField();
        panelFormulario.add(campoCodigo);

        panelFormulario.add(new JLabel("Nombre:"));
        campoNombre = new JTextField();
        panelFormulario.add(campoNombre);

        panelFormulario.add(new JLabel("Precio:"));
        campoPrecio = new JTextField();
        panelFormulario.add(campoPrecio);

        panelFormulario.add(new JLabel("Stock:"));
        campoStock = new JTextField();
        panelFormulario.add(campoStock);

        // Tabla de productos
        modeloTabla = new DefaultTableModel(
                new Object[]{"Código", "Nombre", "Precio", "Stock"}, 0
        ) {
            private static final long serialVersionUID = 1L;

            @Override
            public boolean isCellEditable(int fila, int columna) {
                return false;
            }
        };

        tablaProductos = new JTable(modeloTabla);

        JPanel panelCentral = new JPanel(
                new BorderLayout(10, 10)
        );

        panelCentral.add(panelFormulario, BorderLayout.NORTH);

        panelCentral.add(
                new JScrollPane(tablaProductos),
                BorderLayout.CENTER
        );

        contentPane.add(panelCentral, BorderLayout.CENTER);

        // Botones
        JPanel panelBotones = new JPanel();

        JButton botonRegistrar = new JButton("Registrar producto");
        JButton botonListar = new JButton("Listar productos");
        JButton botonReponer = new JButton("Reponer stock");
        JButton botonVolver = new JButton("Volver al menú");

        panelBotones.add(botonRegistrar);
        panelBotones.add(botonListar);
        panelBotones.add(botonReponer);
        panelBotones.add(botonVolver);

        contentPane.add(panelBotones, BorderLayout.SOUTH);

        // Eventos
        botonRegistrar.addActionListener(e -> registrarProducto());
        botonListar.addActionListener(e -> listarProductos());
        botonReponer.addActionListener(e -> reponerStock());

        botonVolver.addActionListener(e -> dispose());

        // Cargar los productos al abrir la ventana
        listarProductos();
    }

    // Registrar un producto
    private void registrarProducto() {

        try {

            String codigo = campoCodigo.getText().trim();
            String nombre = campoNombre.getText().trim();
            String textoPrecio = campoPrecio.getText().trim();
            String textoStock = campoStock.getText().trim();

            if (codigo.isEmpty()
                    || nombre.isEmpty()
                    || textoPrecio.isEmpty()
                    || textoStock.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Complete todos los campos.",
                        "Campos obligatorios",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }

            double precio = Double.parseDouble(textoPrecio);
            int stock = Integer.parseInt(textoStock);

            if (precio <= 0 || stock < 0) {

                JOptionPane.showMessageDialog(
                        this,
                        "El precio debe ser mayor que cero y el stock no puede ser negativo.",
                        "Datos inválidos",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }

            // Comprobar si el código ya existe
            ArrayList<Producto> productos =
                    gestionInventario.getProductos();

            for (Producto productoExistente : productos) {

                if (productoExistente.getCodigo().equalsIgnoreCase(codigo)) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Ya existe un producto con ese código.",
                            "Producto duplicado",
                            JOptionPane.WARNING_MESSAGE
                    );

                    return;
                }
            }

            // Crear el producto
            Producto producto = new Producto(
                    codigo,
                    nombre,
                    precio,
                    stock
            );

            // Registrar usando la lógica del modelo
            gestionInventario.registrarProducto(producto);

            JOptionPane.showMessageDialog(
                    this,
                    "Producto registrado correctamente.",
                    "Registro exitoso",
                    JOptionPane.INFORMATION_MESSAGE
            );

            limpiarCampos();
            listarProductos();

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Ingrese un precio decimal válido y un stock entero.",
                    "Error en los datos",
                    JOptionPane.ERROR_MESSAGE
            );

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "No se pudo registrar el producto: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // Mostrar los productos en la tabla
    private void listarProductos() {

        modeloTabla.setRowCount(0);

        ArrayList<Producto> productos =
                gestionInventario.getProductos();

        for (Producto producto : productos) {

            modeloTabla.addRow(new Object[]{
                    producto.getCodigo(),
                    producto.getNombre(),
                    producto.getPrecio(),
                    producto.getStock()
            });
        }
    }

    // Reponer el stock de un producto
    private void reponerStock() {

        try {

            String codigo = campoCodigo.getText().trim();
            String textoStock = campoStock.getText().trim();

            if (codigo.isEmpty() || textoStock.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Ingrese el código del producto y las unidades que desea reponer.",
                        "Campos obligatorios",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }

            int unidades = Integer.parseInt(textoStock);

            if (unidades <= 0) {

                JOptionPane.showMessageDialog(
                        this,
                        "Las unidades que desea reponer deben ser mayores que cero.",
                        "Cantidad inválida",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }

            // Comprobar que el producto exista
            Producto encontrado = null;

            for (Producto producto : gestionInventario.getProductos()) {

                if (producto.getCodigo().equalsIgnoreCase(codigo)) {
                    encontrado = producto;
                    break;
                }
            }

            if (encontrado == null) {

                JOptionPane.showMessageDialog(
                        this,
                        "No existe un producto con ese código.",
                        "Producto no encontrado",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }

            // Reponer mediante el modelo
            gestionInventario.reponerStock(codigo, unidades);

            JOptionPane.showMessageDialog(
                    this,
                    "Stock repuesto correctamente.",
                    "Operación exitosa",
                    JOptionPane.INFORMATION_MESSAGE
            );

            limpiarCampos();
            listarProductos();

        } catch (NumberFormatException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Ingrese un número entero válido para el stock.",
                    "Cantidad inválida",
                    JOptionPane.ERROR_MESSAGE
            );

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "No se pudo reponer el stock: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // Limpiar los campos del formulario
    private void limpiarCampos() {

        campoCodigo.setText("");
        campoNombre.setText("");
        campoPrecio.setText("");
        campoStock.setText("");

        campoCodigo.requestFocusInWindow();
    }
}