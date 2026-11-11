
package Modelo;

import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;

public class ArchivoTXT {

    private String archivo = "placas.txt";

    public void guardar(String placa) throws IOException {

        try (BufferedWriter escritor =
                new BufferedWriter(new FileWriter(archivo, true))) {

            escritor.write(placa);
            escritor.newLine();
        }
    }

    public boolean existe(String placa) throws IOException {

        File fichero = new File(archivo);

        if (!fichero.exists()) {
            return false;
        }

        try (BufferedReader lector =
                new BufferedReader(new FileReader(archivo))) {

            String linea;

            while ((linea = lector.readLine()) != null) {

                if (linea.equalsIgnoreCase(placa)) {
                    return true;
                }
            }
        }

        return false;
    }
}
