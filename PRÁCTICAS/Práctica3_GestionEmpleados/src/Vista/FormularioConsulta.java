package Vista;

import javax.swing.*;
import Controlador.ControladorEmpleado;

public class FormularioConsulta extends JFrame {

    private ControladorEmpleado controlador;

    public FormularioConsulta(ControladorEmpleado controlador) {

        this.controlador = controlador;

        setTitle("Consulta de Empleados");
        setSize(350, 200);
        setLocationRelativeTo(null);

        JButton btnConsultar = new JButton("Consultar");

        btnConsultar.addActionListener(e -> consultar());

        add(btnConsultar);
    }

    private void consultar() {

        String cedula = JOptionPane.showInputDialog(
                this, "Ingrese la cedula del empleado:");

        if (cedula == null) {
            return;
        }

        String resultado = controlador.consultarEmpleado(cedula);

        JOptionPane.showMessageDialog(this, resultado);
    }
}