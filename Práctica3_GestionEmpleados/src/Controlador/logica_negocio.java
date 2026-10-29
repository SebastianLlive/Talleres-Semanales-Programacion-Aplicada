package Controlador;

import Modelo.persona;

public class logica_negocio {
    
    public void consultar(persona [] p1, long cedula) {
        for(int i=0; i<p1.length; i++ ) {
            if(p1[i].getCedula()==cedula) {
                p1[i].informacion();
            }
        }
    }
}