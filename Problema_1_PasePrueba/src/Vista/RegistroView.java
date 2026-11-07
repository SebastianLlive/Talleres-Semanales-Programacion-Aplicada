package Vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;

public class RegistroView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtCorreo;
	private JPasswordField txtContraseña;
	
	private JButton btnRegistrar;
	private JButton btnCancelar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegistroView frame = new RegistroView();
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
	public RegistroView() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //evita que se cierre toda la app al cerrar ventana
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNombre = new JLabel("Nombre: ");
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNombre.setBounds(10, 61, 64, 14);
		contentPane.add(lblNombre);
		
		JLabel lblCorreo = new JLabel("Correo: ");
		lblCorreo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCorreo.setBounds(10, 100, 64, 14);
		contentPane.add(lblCorreo);
		
		JLabel lblContraseña = new JLabel("Contraseña: ");
		lblContraseña.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblContraseña.setBounds(11, 141, 72, 14);
		contentPane.add(lblContraseña);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(84, 58, 142, 20);
		contentPane.add(txtNombre);
		txtNombre.setColumns(10);
		
		txtCorreo = new JTextField();
		txtCorreo.setBounds(84, 97, 142, 20);
		contentPane.add(txtCorreo);
		txtCorreo.setColumns(10);
		
		txtContraseña = new JPasswordField();
		txtContraseña.setBounds(84, 138, 142, 20);
		contentPane.add(txtContraseña);
		txtContraseña.setColumns(10);
		
		btnRegistrar = new JButton("Registrar");
		btnRegistrar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnRegistrar.setBounds(53, 204, 88, 22);
		contentPane.add(btnRegistrar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCancelar.setBounds(255, 204, 88, 22);
		contentPane.add(btnCancelar);

	}
	
	public String obtenerNombre() {
		return txtNombre.getText().trim();
	}
	
	public String obtenerCorreo() {
		return txtCorreo.getText().trim();
	}
	
	public String obtenerContrasena() {
		return new String(txtContraseña.getPassword());
	}
	
	
	public void mostrarMensaje(String mensaje) {
		JOptionPane.showMessageDialog(this, mensaje);
	}
	
	public void limpiarCampos() {
		txtNombre.setText("");
		txtCorreo.setText("");
		txtContraseña.setText("");
	}

	public JButton getBtnRegistrar() {
		return btnRegistrar;
	}

	public JButton getBtnCancelar() {
		return btnCancelar;
	}
	
	

}
