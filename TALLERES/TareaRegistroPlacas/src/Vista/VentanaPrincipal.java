
package Vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import Controlador.PlacaController;

public class VentanaPrincipal extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    private JTextField txtPlaca;
    private JButton btnValidar;
    private JButton btnRegistrar;
    private JLabel lblResultado;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    VentanaPrincipal frame = new VentanaPrincipal();

                    new PlacaController(frame);

                    frame.setVisible(true);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public VentanaPrincipal() {

        setTitle("Sistema de Registro de Placas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));

        setContentPane(contentPane);

        JPanel panelSuperior = new JPanel();
        contentPane.add(panelSuperior, BorderLayout.NORTH);

        JLabel lblTitulo = new JLabel("REGISTRO DE PLACAS");
        panelSuperior.add(lblTitulo);

        JPanel panelCentral = new JPanel();
        contentPane.add(panelCentral, BorderLayout.CENTER);
        panelCentral.setLayout(null);

        JLabel lblPlaca = new JLabel("Ingrese la placa:");
        lblPlaca.setBounds(45, 35, 120, 25);
        panelCentral.add(lblPlaca);

        txtPlaca = new JTextField();
        txtPlaca.setBounds(160, 35, 190, 25);
        panelCentral.add(txtPlaca);
        txtPlaca.setColumns(10);

        btnValidar = new JButton("VALIDAR");
        btnValidar.setBounds(65, 95, 120, 35);
        panelCentral.add(btnValidar);

        btnRegistrar = new JButton("REGISTRAR");
        btnRegistrar.setBounds(220, 95, 120, 35);
        panelCentral.add(btnRegistrar);

        lblResultado = new JLabel("Ingrese una placa");
        lblResultado.setHorizontalAlignment(SwingConstants.CENTER);
        lblResultado.setBounds(35, 155, 350, 30);
        panelCentral.add(lblResultado);
    }

    public String getPlaca() {
        return txtPlaca.getText();
    }

    public JButton getBtnValidar() {
        return btnValidar;
    }

    public JButton getBtnRegistrar() {
        return btnRegistrar;
    }

    public void mostrarMensaje(String mensaje) {
        lblResultado.setText(mensaje);
    }

    public void limpiarCampo() {
        txtPlaca.setText("");
    }
}
