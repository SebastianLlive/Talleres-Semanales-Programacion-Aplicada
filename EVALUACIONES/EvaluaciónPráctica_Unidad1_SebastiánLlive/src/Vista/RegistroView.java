package Vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;

public class RegistroView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtNombreProducto;
	private JTextField txtCodigoProducto;
	private JTextField txtCantidadProducto;
	
	private JButton btnGuardarProducto;
	private JButton btnGuardarRegistro;
	private JButton btnSalir;
	private JButton btnMostarRegistros;
	private JTextArea txtMostrar;

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 465, 512);
		contentPane = new JPanel();
		contentPane.setBorder(new TitledBorder(null, "Registro de Producci\u00F3n", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNombreProducto = new JLabel("Nombre del Producto: ");
		lblNombreProducto.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNombreProducto.setBounds(10, 27, 143, 14);
		contentPane.add(lblNombreProducto);
		
		JLabel lblCodigoProducto = new JLabel("Código del Producto: ");
		lblCodigoProducto.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCodigoProducto.setBounds(10, 62, 127, 14);
		contentPane.add(lblCodigoProducto);
		
		JLabel lblCantidadProducida = new JLabel("Cantidad Producida: ");
		lblCantidadProducida.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCantidadProducida.setBounds(10, 102, 143, 14);
		contentPane.add(lblCantidadProducida);
		
		txtNombreProducto = new JTextField();
		txtNombreProducto.setBounds(143, 24, 209, 20);
		contentPane.add(txtNombreProducto);
		txtNombreProducto.setColumns(10);
		
		txtCodigoProducto = new JTextField();
		txtCodigoProducto.setBounds(143, 59, 209, 20);
		contentPane.add(txtCodigoProducto);
		txtCodigoProducto.setColumns(10);
		
		txtCantidadProducto = new JTextField();
		txtCantidadProducto.setBounds(143, 99, 209, 20);
		contentPane.add(txtCantidadProducto);
		txtCantidadProducto.setColumns(10);
		
		btnGuardarProducto = new JButton("Guardar Producto");
		btnGuardarProducto.setBounds(27, 393, 126, 22);
		contentPane.add(btnGuardarProducto);
		
		btnGuardarRegistro = new JButton("Guardar Registros");
		btnGuardarRegistro.setBounds(299, 393, 127, 22);
		contentPane.add(btnGuardarRegistro);
		
		btnSalir = new JButton("Salir");
		btnSalir.setBounds(181, 441, 88, 22);
		contentPane.add(btnSalir);
		
		btnMostarRegistros = new JButton("Mostrar Registros");
		btnMostarRegistros.setBounds(162, 393, 127, 22);
		contentPane.add(btnMostarRegistros);
		
		txtMostrar = new JTextArea();
		txtMostrar.setBounds(10, 127, 431, 258);
		contentPane.add(txtMostrar);
		
		

	}

	public JTextField getTxtNombreProducto() {
		return txtNombreProducto;
	}

	public void setTxtNombreProducto(JTextField txtNombreProducto) {
		this.txtNombreProducto = txtNombreProducto;
	}

	public JTextField getTxtCodigoProducto() {
		return txtCodigoProducto;
	}

	public void setTxtCodigoProducto(JTextField txtCodigoProducto) {
		this.txtCodigoProducto = txtCodigoProducto;
	}

	public JTextField getTxtCantidadProducto() {
		return txtCantidadProducto;
	}

	public void setTxtCantidadProducto(JTextField txtCantidadProducto) {
		this.txtCantidadProducto = txtCantidadProducto;
	}

	public JTextArea getTxtMostrar() {
		return txtMostrar;
	}

	public void setTxtMostrar(JTextArea txtMostrar) {
		this.txtMostrar = txtMostrar;
	}
	
	public String obtenerNombreProducto() {
		return txtNombreProducto.getText().trim();
	}
	
	public String obtenerCodigoProducto() {
		return txtCodigoProducto.getText().trim();
	}
	
	public String obtenerCantidadProducto() {
		return txtCantidadProducto.getText().trim();
	}
	
    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }
	
	public void vaciarCeldas() {
		txtNombreProducto.setText("");
		txtCodigoProducto.setText("");
		txtCantidadProducto.setText("");
	}

	public JButton getBtnGuardarProducto() {
		return btnGuardarProducto;
	}

	public JButton getBtnGuardarRegistro() {
		return btnGuardarRegistro;
	}

	public JButton getBtnMostarRegistros() {
		return btnMostarRegistros;
	}
	
	
	
}
