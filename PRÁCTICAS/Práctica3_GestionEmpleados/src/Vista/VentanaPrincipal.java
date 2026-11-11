package Vista;

import javax.swing.*;
import Controlador.ControladorEmpleado;
import java.awt.*;

public class VentanaPrincipal extends JFrame {

    private ControladorEmpleado controlador;

    public VentanaPrincipal() {

        controlador = new ControladorEmpleado();

        setTitle("Sistema de Gestion de Empleados");
        setSize(450, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7, 1, 10, 10));

        panel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        JLabel titulo = new JLabel(
                "GESTION DE EMPLEADOS", SwingConstants.CENTER);

        titulo.setFont(new Font("Arial", Font.BOLD, 20));

        JButton btnAdministrativo = new JButton("Registrar Administrativo");
        JButton btnObrero = new JButton("Registrar Obrero");
        JButton btnTecnico = new JButton("Registrar Tecnico");
        JButton btnConsulta = new JButton("Consultar Empleado");
        JButton btnListar = new JButton("Listar Empleados");
        JButton btnSalir = new JButton("Salir");

        panel.add(titulo);
        panel.add(btnAdministrativo);
        panel.add(btnObrero);
        panel.add(btnTecnico);
        panel.add(btnConsulta);
        panel.add(btnListar);
        panel.add(btnSalir);

        add(panel);

        btnAdministrativo.addActionListener(e ->
                new FormularioAdministrativo(controlador).setVisible(true));

        btnObrero.addActionListener(e ->
                new FormularioObrero(controlador).setVisible(true));

        btnTecnico.addActionListener(e ->
                new FormularioTecnico(controlador).setVisible(true));

        btnConsulta.addActionListener(e ->
                new FormularioConsulta(controlador).setVisible(true));

        btnListar.addActionListener(e -> mostrarListas());

        btnSalir.addActionListener(e -> System.exit(0));
    }

    private void mostrarListas() {

        String[] opciones = {
                "Administrativos",
                "Obreros",
                "Tecnicos"
        };

        String opcion = (String) JOptionPane.showInputDialog(
                this,
                "Seleccione el tipo de empleado:",
                "Listar empleados",
                JOptionPane.QUESTION_MESSAGE,
                null,
                opciones,
                opciones[0]);

        if (opcion == null) {
            return;
        }

        String resultado = "";

        switch (opcion) {
            case "Administrativos":
                resultado = controlador.listarAdministrativos();
                break;

            case "Obreros":
                resultado = controlador.listarObreros();
                break;

            case "Tecnicos":
                resultado = controlador.listarTecnicos();
                break;
        }

        JOptionPane.showMessageDialog(this, resultado);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VentanaPrincipal ventana = new VentanaPrincipal();
            ventana.setVisible(true);
        });
    }
}