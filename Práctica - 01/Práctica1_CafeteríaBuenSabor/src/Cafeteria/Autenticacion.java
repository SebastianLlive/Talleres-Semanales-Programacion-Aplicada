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

	public void validacionAutenticacion(String usuario, String contraseña) {
		if(usuario.equals(this.usuario) && contraseña.equals(this.contraseña)) {
			System.out.println("______________________BIENVENIDO_________________________");
		}else if(usuario.equals(this.usuario) && !contraseña.equals(this.contraseña)){
			System.out.println("La contraseña es incorrecta...");
		}else if(!usuario.equals(this.usuario) && contraseña.equals(this.contraseña)) {
			System.out.println("El usuario es incorrecto...");
		}else {
			System.out.println("Usuario o contraseña incorrectos...");
		}
	}
}
