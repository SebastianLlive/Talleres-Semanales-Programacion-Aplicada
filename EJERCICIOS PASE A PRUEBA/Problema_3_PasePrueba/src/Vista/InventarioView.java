
package Vista;

import javax.swing.*;
import java.awt.*;

public class InventarioView extends JFrame {

    private JTextField txtNombre;
    private JTextField txtCantidad;

    private JButton btnRegistrar;
    private JButton btnAumentar;
    private JButton btnDisminuir;
    private JButton btnConsultar;

    public InventarioView() {

        setTitle("Control de Inventario");
        setSize(420, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 2, 8, 8));

        panel.add(new JLabel("Nombre del producto:"));
        txtNombre = new JTextField();
        panel.add(txtNombre);

        panel.add(new JLabel("Cantidad:"));
        txtCantidad = new JTextField();
        panel.add(txtCantidad);

        btnRegistrar = new JButton("Registrar");
        btnAumentar = new JButton("Aumentar stock");
        btnDisminuir = new JButton("Disminuir stock");
        btnConsultar = new JButton("Consultar stock");

        panel.add(btnRegistrar);
        panel.add(btnAumentar);
        panel.add(btnDisminuir);
        panel.add(btnConsultar);

        add(panel);
    }

    public String obtenerNombre() {
        return txtNombre.getText().trim();
    }

    public String obtenerCantidad() {
        return txtCantidad.getText().trim();
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    public void limpiarCampos() {
        txtNombre.setText("");
        txtCantidad.setText("");
    }

    public JButton getBtnRegistrar() {
        return btnRegistrar;
    }

    public JButton getBtnAumentar() {
        return btnAumentar;
    }

    public JButton getBtnDisminuir() {
        return btnDisminuir;
    }

    public JButton getBtnConsultar() {
        return btnConsultar;
    }
}
