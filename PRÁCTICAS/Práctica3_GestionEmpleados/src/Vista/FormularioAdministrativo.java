package Vista;

import javax.swing.*;
import Controlador.ControladorEmpleado;

public class FormularioAdministrativo extends JFrame {

    private ControladorEmpleado controlador;

    public FormularioAdministrativo(ControladorEmpleado controlador) {

        this.controlador = controlador;

        setTitle("Registro de Administrativo");
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

        String cargo = JOptionPane.showInputDialog("Ingrese el cargo:");
        if (cargo == null) return;

        String resultado = controlador.registrarAdministrativo(
                nombre, cedula, cargo);

        JOptionPane.showMessageDialog(this, resultado);
    }
}