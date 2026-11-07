package Vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;

public class LoginView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtCorreo;
	private JPasswordField txtContraseña;
	
	private JButton btnIniciarSesion;
	private JButton btnRegistrarse;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginView frame = new LoginView();
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
	public LoginView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblWelcome = new JLabel("WELCOME!!");
		lblWelcome.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblWelcome.setForeground(new Color(0, 255, 0));
		lblWelcome.setBounds(163, 11, 115, 32);
		contentPane.add(lblWelcome);
		
		JLabel lblCorreo = new JLabel("Correo: ");
		lblCorreo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCorreo.setBounds(49, 64, 48, 14);
		contentPane.add(lblCorreo);
		
		JLabel lblContraseña = new JLabel("Contraseña: ");
		lblContraseña.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblContraseña.setBounds(49, 105, 72, 14);
		contentPane.add(lblContraseña);
		
		txtCorreo = new JTextField();
		txtCorreo.setBounds(107, 61, 96, 20);
		contentPane.add(txtCorreo);
		txtCorreo.setColumns(10);
		
		txtContraseña = new JPasswordField();
		txtContraseña.setBounds(131, 102, 96, 20);
		contentPane.add(txtContraseña);
		txtContraseña.setColumns(10);
		
		
		
		btnIniciarSesion = new JButton("Iniciar Sesión");
		btnIniciarSesion.setBounds(163, 150, 115, 22);
		contentPane.add(btnIniciarSesion);
		
		JLabel lblMessage = new JLabel("¿No tienes una cuenta? ");
		lblMessage.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblMessage.setBounds(107, 209, 138, 14);
		contentPane.add(lblMessage);
		
		btnRegistrarse = new JButton("Registrarse");
		btnRegistrarse.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnRegistrarse.setBounds(216, 209, 89, 14);
		contentPane.add(btnRegistrarse);

	}

	public JTextField getTxtCorreo() {
		return txtCorreo;
	}

	public JPasswordField getTxtContraseña() {
		return txtContraseña;
	}

	public JButton getBtnIniciarSesion() {
		return btnIniciarSesion;
	}

	public JButton getBtnRegistrarse() {
		return btnRegistrarse;
	}
	
	public void mostrarMensaje(String mensaje) {
		JOptionPane.showMessageDialog(this, mensaje);
	}
	
	public String obtenerCorreo() {
		return txtCorreo.getText().trim();
	}
	
	public String obtenerContrasena() {
		return new String(txtContraseña.getPassword());
	}
	
	public void limpiarCampos() {
		txtCorreo.setText("");
		txtContraseña.setText("");
	}
	
}
