
package Vista;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

public class VentanaIngresos extends JFrame {

    private JButton btnAnalizar;
    private JButton btnLimpiar;

    private JLabel lblArchivo;

    private JTable tabla;
    private DefaultTableModel modeloTabla;

    private JTextArea areaResumen;

    public VentanaIngresos() {

        setTitle("Sistema de Gestion de Ingresos");
        setSize(1000, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new BorderLayout(10, 10));

        // PANEL SUPERIOR
        JPanel panelSuperior = new JPanel(new BorderLayout());

        JLabel titulo = new JLabel(
            "SISTEMA DE INGRESOS ECONOMICOS",
            JLabel.CENTER
        );

        titulo.setFont(new Font("Arial", Font.BOLD, 22));

        lblArchivo = new JLabel("Archivo: Ninguno seleccionado");

        panelSuperior.add(titulo, BorderLayout.NORTH);
        panelSuperior.add(lblArchivo, BorderLayout.SOUTH);

        add(panelSuperior, BorderLayout.NORTH);

        // TABLA
        String[] columnas = {
            "N°",
            "Cedula",
            "Correo",
            "Monto",
            "Estado cedula",
            "Estado correo",
            "Estado monto",
            "Resultado"
        };

        modeloTabla = new DefaultTableModel(columnas, 0) {

            @Override
            public boolean isCellEditable(int fila, int columna) {
                return false;
            }
        };

        tabla = new JTable(modeloTabla);
        tabla.setRowHeight(25);

        add(new JScrollPane(tabla), BorderLayout.CENTER);

        // PANEL INFERIOR
        JPanel panelInferior = new JPanel(new BorderLayout());

        areaResumen = new JTextArea(6, 30);
        areaResumen.setEditable(false);
        areaResumen.setFont(new Font("Monospaced", Font.PLAIN, 13));

        panelInferior.add(
            new JScrollPane(areaResumen),
            BorderLayout.CENTER
        );

        // BOTONES
        JPanel panelBotones = new JPanel(new FlowLayout());

        btnAnalizar = new JButton("Seleccionar y analizar archivo");
        btnLimpiar = new JButton("Limpiar resultados");

        panelBotones.add(btnAnalizar);
        panelBotones.add(btnLimpiar);

        panelInferior.add(panelBotones, BorderLayout.SOUTH);

        add(panelInferior, BorderLayout.SOUTH);
    }

    public JButton getBtnAnalizar() {
        return btnAnalizar;
    }

    public JButton getBtnLimpiar() {
        return btnLimpiar;
    }

    public String seleccionarArchivo() {

        JFileChooser selector = new JFileChooser();

        selector.setDialogTitle("Seleccionar archivo de ingresos");

        int resultado = selector.showOpenDialog(this);

        if (resultado == JFileChooser.APPROVE_OPTION) {

            String ruta = selector.getSelectedFile().getAbsolutePath();

            lblArchivo.setText(
                "Archivo: " + selector.getSelectedFile().getName()
            );

            return ruta;
        }

        return null;
    }

    public void mostrarRegistros(String[][] datos) {

        modeloTabla.setRowCount(0);

        for (String[] fila : datos) {
            modeloTabla.addRow(fila);
        }
    }

    public void mostrarResumen(String resumen) {
        areaResumen.setText(resumen);
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    public void limpiar() {

        modeloTabla.setRowCount(0);

        areaResumen.setText("");

        lblArchivo.setText("Archivo: Ninguno seleccionado");
    }
}
