package Controlador;

import java.util.ArrayList;

import Modelo.Producto;
import Vista.RegistroView;


public class ControllerProducto {
	
    private RegistroView vista;
    private ArrayList<Producto> productos;
    
	public ControllerProducto() {
		super();
		

	    productos = new ArrayList<>();
	    vista = new RegistroView();
	    
	    //vista.
	    
	}
    
    // Metodo generico
    public <T> void mostrarDato(T dato) {
        System.out.println(dato);
    }

    // Metodo Varargs
    public void mostrarMensajes(String... mensajes) {
        for (String mensaje : mensajes) {
            System.out.println(mensaje);
        }
    }

  
	
	
}

