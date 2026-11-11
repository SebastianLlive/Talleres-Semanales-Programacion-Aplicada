
package Modelo;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Placa {

    private String numero;

    public Placa(String numero) {
        this.numero = numero.toUpperCase().trim();
    }

    public String getNumero() {
        return numero;
    }

    public boolean validar() {

        Pattern patron = Pattern.compile("^[A-Z]{3}-[0-9]{4}$");

        Matcher analisis = patron.matcher(numero);

        return analisis.matches();
    }
}
