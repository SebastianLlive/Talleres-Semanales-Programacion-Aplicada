
package archivos;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GestorArchivos {

    private String rutaArchivo;

    public GestorArchivos(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    public String leerArchivo() {

        StringBuilder contenido = new StringBuilder();

        try {
            File archivo = new File(rutaArchivo);
            Scanner lector = new Scanner(archivo);

            while (lector.hasNextLine()) {
                contenido.append(lector.nextLine()).append("\n");
            }

            lector.close();

        } catch (FileNotFoundException e) {
            System.out.println("No se encontro el archivo: " + rutaArchivo);
        }

        return contenido.toString();
    }
}
