
package Controlador;

import javax.swing.SwingUtilities;

public class Principal {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            IngresosController controlador =
                new IngresosController();

            controlador.iniciar();

        });
    }
}
