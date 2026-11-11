package Vista;

import javax.swing.*;
import Controlador.ControladorEmpleado;

public class FormularioObrero extends JFrame {

    private ControladorEmpleado controlador;

    public FormularioObrero(ControladorEmpleado controlador) {

        this.controlador = controlador;

        setTitle("Registro de Obrero");
        setSize(350, 250);
        setLocationRelativeTo(null);

        JButton btnRegistrar = new JButton("Registrar");

        btnRegistrar.addActionListener(e -> registrar());

        add(btnRegistrar);
    }

    private void registrar() {

        String nombre = JOptionPane.showInputDialog("Ingrese el nombre:");
        if (nombre == null) return;

        String cedula = JOptionPane.showInputDialog("Ingrese la cedula:");
        if (cedula == null) return;

        String codigo = JOptionPane.showInputDialog("Ingrese el codigo:");
        if (codigo == null) return;

        String resultado = controlador.registrarObrero(
                nombre, cedula, codigo);

        JOptionPane.showMessageDialog(this, resultado);
    }
}