package Vista;

import javax.swing.*;
import java.awt.*;

public class ProductoView extends JFrame {

    private JTextField txtNombre;
    private JTextField txtPrecio;
    private JTextArea areaProductos;

    private JButton btnRegistrar;
    private JButton btnListar;
    private JButton btnBuscar;
    private JButton btnEliminar;

    public ProductoView() {

        setTitle("Catalogo de Productos");
        setSize(450, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 2, 8, 8));

        panel.add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        panel.add(txtNombre);

        panel.add(new JLabel("Precio:"));
        txtPrecio = new JTextField();
        panel.add(txtPrecio);

        btnRegistrar = new JButton("Registrar");
        btnListar = new JButton("Listar");
        btnBuscar = new JButton("Buscar");
        btnEliminar = new JButton("Eliminar");

        panel.add(btnRegistrar);
        panel.add(btnListar);
        panel.add(btnBuscar);
        panel.add(btnEliminar);

        areaProductos = new JTextArea();
        areaProductos.setEditable(false);

        add(panel, BorderLayout.NORTH);
        add(new JScrollPane(areaProductos), BorderLayout.CENTER);
    }

    public String obtenerNombre() {
        return txtNombre.getText().trim();
    }

    public String obtenerPrecio() {
        return txtPrecio.getText().trim();
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    public void mostrarProductos(String texto) {
        areaProductos.setText(texto);
    }

    public void limpiarCampos() {
        txtNombre.setText("");
        txtPrecio.setText("");
    }

    public JButton getBtnRegistrar() {
        return btnRegistrar;
    }

    public JButton getBtnListar() {
        return btnListar;
    }

    public JButton getBtnBuscar() {
        return btnBuscar;
    }

    public JButton getBtnEliminar() {
        return btnEliminar;
    }
}
