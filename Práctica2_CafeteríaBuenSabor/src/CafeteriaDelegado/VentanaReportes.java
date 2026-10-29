package CafeteriaDelegado;

import java.awt.BorderLayout;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import CafeteriaModelo.Reporte_Ventas;
import CafeteriaModelo.UtilGenerico;

public class VentanaReportes extends JFrame {

    private static final long serialVersionUID = 1L;

    private JPanel contentPane;
    private JTextArea areaReporte;

    public VentanaReportes() {

        setTitle("El Buen Sabor - Reportes de ventas");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(650, 500);
        setLocationRelativeTo(null);
        setResizable(true);

        contentPane = new JPanel(new BorderLayout(10, 10));
        contentPane.setBorder(new EmptyBorder(15, 15, 15, 15));
        setContentPane(contentPane);

        JLabel titulo = new JLabel(
                "REPORTE DE VENTAS",
                SwingConstants.CENTER
        );

        titulo.setFont(new Font("Arial", Font.BOLD, 22));
        contentPane.add(titulo, BorderLayout.NORTH);

        areaReporte = new JTextArea();
        areaReporte.setEditable(false);
        areaReporte.setFont(new Font("Monospaced", Font.PLAIN, 14));
        areaReporte.setLineWrap(true);
        areaReporte.setWrapStyleWord(true);

        JScrollPane desplazamiento = new JScrollPane(areaReporte);
        contentPane.add(desplazamiento, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel();

        JButton botonActualizar = new JButton("Actualizar reporte");
        JButton botonVolver = new JButton("Volver al menú");

        panelBotones.add(botonActualizar);
        panelBotones.add(botonVolver);

        contentPane.add(panelBotones, BorderLayout.SOUTH);

        botonActualizar.addActionListener(e -> actualizarReporte());

        botonVolver.addActionListener(e -> dispose());

        actualizarReporte();
    }

    private void actualizarReporte() {

        ArrayList<String> encabezados = UtilGenerico.crearLista(
                "EL BUEN SABOR"
        );

        UtilGenerico.agregarElementos(
                encabezados,
                "REPORTE DE VENTAS",
                "Resumen de ventas"
        );

        String reporte = Reporte_Ventas.obtenerReporte();

        areaReporte.setText(
                String.join("\n", encabezados)
                + "\n\n"
                + reporte
        );

        areaReporte.setCaretPosition(0);
    }
}