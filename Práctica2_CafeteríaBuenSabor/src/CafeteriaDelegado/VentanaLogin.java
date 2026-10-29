package CafeteriaDelegado;

import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.BorderFactory;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class VentanaLogin extends JFrame {

    private static final long serialVersionUID = 1L;

    private JPanel contentPane;
    private JTextField campoUsuario;
    private JPasswordField campoContraseña;

    private Autenticacion autenticacion;

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            try {
                VentanaLogin frame = new VentanaLogin();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public VentanaLogin() {

        autenticacion = new Autenticacion();

        setTitle("El Buen Sabor - Inicio de sesión");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(420, 280);
        setLocationRelativeTo(null);
        setResizable(false);

        contentPane = new JPanel();
        contentPane.setBorder(
                new EmptyBorder(20, 25, 20, 25)
        );

        contentPane.setLayout(new GridLayout(4, 2, 10, 15));

        setContentPane(contentPane);

        JLabel etiquetaTitulo = new JLabel(
                "EL BUEN SABOR",
                SwingConstants.CENTER
        );

        etiquetaTitulo.setFont(
                new java.awt.Font("Arial", java.awt.Font.BOLD, 18)
        );

        contentPane.add(etiquetaTitulo);
        contentPane.add(new JLabel(""));

        contentPane.add(new JLabel("Usuario:"));

        campoUsuario = new JTextField();
        contentPane.add(campoUsuario);

        contentPane.add(new JLabel("Contraseña:"));

        campoContraseña = new JPasswordField();
        contentPane.add(campoContraseña);

        contentPane.add(new JLabel(""));

        JButton botonIngresar = new JButton("Iniciar sesión");
        contentPane.add(botonIngresar);

        botonIngresar.addActionListener(e -> iniciarSesion());

        getRootPane().setDefaultButton(botonIngresar);
    }

    private void iniciarSesion() {

        String usuario = campoUsuario.getText().trim();

        String contraseña = new String(
                campoContraseña.getPassword()
        );

        if (usuario.isEmpty() || contraseña.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Complete todos los campos.",
                    "Advertencia",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        if (autenticacion.validacionAutenticacion(usuario, contraseña)) {

            JOptionPane.showMessageDialog(this,
                "¡Bienvenido a El Buen Sabor!",
                "Acceso concedido",
                JOptionPane.INFORMATION_MESSAGE);

            VentanaPrincipal ventana = new VentanaPrincipal();
            ventana.setVisible(true);

            dispose();

        } else {

            JOptionPane.showMessageDialog(this,
                "Usuario o contraseña incorrectos.",
                "Error de autenticación",
                JOptionPane.ERROR_MESSAGE);

            campoContraseña.setText("");
            campoUsuario.requestFocusInWindow();
        
        }
    }
}