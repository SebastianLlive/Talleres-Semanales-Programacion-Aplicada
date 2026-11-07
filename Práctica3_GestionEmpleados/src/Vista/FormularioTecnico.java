package Vista;

import javax.swing.*;
import Controlador.ControladorEmpleado;

public class FormularioTecnico extends JFrame {

    private ControladorEmpleado controlador;

    public FormularioTecnico(ControladorEmpleado controlador) {

        this.controlador = controlador;

        setTitle("Registro de Tecnico");
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

        String clave = JOptionPane.showInputDialog(
                "Ingrese la clave de acceso:");
        if (clave == null) return;

        String resultado = controlador.registrarTecnico(
                nombre, cedula, codigo, clave);

        JOptionPane.showMessageDialog(this, resultado);
    }
}