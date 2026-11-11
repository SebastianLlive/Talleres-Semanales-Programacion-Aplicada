
package Controlador;

import java.util.ArrayList;

import Modelo.Usuario;
import Vista.LoginView;
import Vista.RegistroView;

public class UsuarioController {

    private LoginView login;
    private RegistroView registro;

    private ArrayList<Usuario> usuarios;

    public UsuarioController() {

        usuarios = new ArrayList<>();

        login = new LoginView();
        registro = new RegistroView();

        login.getBtnIniciarSesion().addActionListener(e -> iniciarSesion());

        login.getBtnRegistrarse().addActionListener(e -> abrirRegistro());

        registro.getBtnRegistrar().addActionListener(e -> registrarUsuario());

        registro.getBtnCancelar().addActionListener(e -> cancelarRegistro());

        login.setVisible(true);
    }
    
    public static void main(String[] args) {
        new UsuarioController();
    }

    // Método genérico
    public <T> void mostrarDato(T dato) {
        System.out.println(dato);
    }

    // Método con Varargs
    public void mostrarMensajes(String... mensajes) {

        for (String mensaje : mensajes) {
            System.out.println(mensaje);
        }
    }

    // Registrar usuario
    public void registrarUsuario() {

        String nombre = registro.obtenerNombre();
        String correo = registro.obtenerCorreo();
        String contrasena = registro.obtenerContrasena();

        if (nombre.isEmpty() || correo.isEmpty() || contrasena.isEmpty()) {
            registro.mostrarMensaje("Complete todos los campos");
            return;
        }

        if (validarCorreo(correo)) {
            registro.mostrarMensaje("El correo ya está registrado");
            return;
        }

        Usuario nuevo = new Usuario(nombre, correo, contrasena);

        usuarios.add(nuevo);

        registro.mostrarMensaje("Usuario registrado correctamente");

        registro.limpiarCampos();
        registro.setVisible(false);
    }

    // Validar correo duplicado
    public boolean validarCorreo(String correo) {

        for (Usuario usuario : usuarios) {

            if (usuario.getCorreo().equalsIgnoreCase(correo)) {
                return true;
            }
        }

        return false;
    }

    // Iniciar sesión
    public void iniciarSesion() {

        String correo = login.obtenerCorreo();
        String contrasena = login.obtenerContrasena();

        for (Usuario usuario : usuarios) {

            if (usuario.getCorreo().equalsIgnoreCase(correo)
                    && usuario.getContrasena().equals(contrasena)) {

                login.mostrarMensaje("Bienvenido/a " + usuario.getNombre());
                login.limpiarCampos();
                return;
            }
        }

        login.mostrarMensaje("Correo o contraseña incorrectos");
    }

    // Abrir registro
    public void abrirRegistro() {
        registro.setVisible(true);
    }

    // Cancelar registro
    public void cancelarRegistro() {
        registro.limpiarCampos();
        registro.setVisible(false);
    }
}
