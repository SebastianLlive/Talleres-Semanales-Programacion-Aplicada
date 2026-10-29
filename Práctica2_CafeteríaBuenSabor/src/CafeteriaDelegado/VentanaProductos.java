package CafeteriaDelegado;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class VentanaProductos extends JFrame {

    private static final long serialVersionUID = 1L;

    private JPanel contentPane;
    private JTextField campoCodigo;
    private JTextField campoNombre;
    private JTextField campoPrecio;
    private JTextField campoStock;
    private JTable tablaProductos;
    private DefaultTableModel modeloTabla;

    public VentanaProductos() {
        setTitle("El Buen Sabor - Gestión de productos");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(750, 500);
        setLocationRelativeTo(null);

        contentPane = new JPanel(new BorderLayout(10, 10));
        contentPane.setBorder(new EmptyBorder(15, 15, 15, 15));
        setContentPane(contentPane);

        JLabel titulo = new JLabel("GESTIÓN DE PRODUCTOS",
                JLabel.CENTER);
        titulo.setFont(new java.awt.Font("Arial",
                java.awt.Font.BOLD, 20));
        contentPane.add(titulo, BorderLayout.NORTH);

        JPanel panelFormulario = new JPanel(new GridLayout(4, 2, 8, 8));

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

        modeloTabla = new DefaultTableModel(
                new Object[]{"Código", "Nombre", "Precio", "Stock"}, 0);

        tablaProductos = new JTable(modeloTabla);

        JPanel panelCentral = new JPanel(new BorderLayout(10, 10));
        panelCentral.add(panelFormulario, BorderLayout.NORTH);
        panelCentral.add(new JScrollPane(tablaProductos),
                BorderLayout.CENTER);

        contentPane.add(panelCentral, BorderLayout.CENTER);

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

        // Los conectaremos con el modelo en el siguiente paso.
        botonVolver.addActionListener(e -> dispose());
    }
}