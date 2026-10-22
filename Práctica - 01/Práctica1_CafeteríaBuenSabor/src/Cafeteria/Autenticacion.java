package Cafeteria;

public class Autenticacion {
	private String usuario = "sebas";
	private String contraseña = "ringo";
	
	
	public String getUsuario() {
		return usuario;
	}

	public String getContraseña() {
		return contraseña;
	}

	public boolean validacionAutenticacion(String usuario, String contraseña) {
	    if (usuario.equals(this.usuario) && contraseña.equals(this.contraseña)) {
	        System.out.println("______________BIENVENIDO________________");
	        return true;
	    } else {
	        System.out.println("Usuario o contraseña incorrectos...");
	        return false;
	    }
	}
}
