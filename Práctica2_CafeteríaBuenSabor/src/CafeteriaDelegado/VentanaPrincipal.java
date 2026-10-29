package CafeteriaDelegado;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public class VentanaPrincipal extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    public VentanaPrincipal() {
        setTitle("El Buen Sabor - Menú principal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);
        setResizable(false);

        contentPane = new JPanel(new BorderLayout(15, 20));
        contentPane.setBorder(new EmptyBorder(25, 30, 25, 30));
        setContentPane(contentPane);

        JLabel titulo = new JLabel("EL BUEN SABOR", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        contentPane.add(titulo, BorderLayout.NORTH);

        JPanel panelBotones = new JPanel();
        panelBotones.setBorder(new TitledBorder(null, "Men\u00FA", TitledBorder.LEADING, TitledBorder.TOP, null, null));

        JButton botonProductos = new JButton("Gestión de productos");
        botonProductos.setBounds(10, 26, 165, 37);
        JButton botonPedidos = new JButton("Gestión de pedidos");
        botonPedidos.setBounds(10, 74, 165, 37);
        JButton botonReportes = new JButton("Reportes de ventas");
        botonReportes.setBounds(10, 122, 165, 37);
        JButton botonCerrarSesion = new JButton("Cerrar sesión");
        botonCerrarSesion.setBounds(24, 187, 380, 49);
        panelBotones.setLayout(null);

        panelBotones.add(botonProductos);
        panelBotones.add(botonPedidos);
        panelBotones.add(botonReportes);
        panelBotones.add(botonCerrarSesion);
        
        botonProductos.addActionListener(e -> {
            VentanaProductos ventana = new VentanaProductos();
            ventana.setVisible(true);
        });

        contentPane.add(panelBotones, BorderLayout.CENTER);

        botonCerrarSesion.addActionListener(e -> {
            dispose();
            VentanaLogin login = new VentanaLogin();
            login.setVisible(true);
        });
    }
}