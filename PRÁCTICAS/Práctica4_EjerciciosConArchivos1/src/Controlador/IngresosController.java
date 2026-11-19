
package Controlador;

import java.util.ArrayList;

import Modelo.Aportador;
import Modelo.ProcesadorIngresos;
import archivos.GestorArchivos;
import Vista.MenuVista;

public class IngresosController {

    private ProcesadorIngresos procesador;
    private GestorArchivos gestorArchivos;
    private MenuVista vista;

    private ArrayList<Aportador> aportadores;
    private boolean archivoProcesado;

    public IngresosController() {

        procesador = new ProcesadorIngresos();

        gestorArchivos = new GestorArchivos("registro_ingresos.txt");

        vista = new MenuVista();

        aportadores = new ArrayList<>();

        archivoProcesado = false;
    }

    public void iniciar() {

        int opcion;

        do {

            vista.mostrarMenu();

            opcion = vista.leerOpcion();

            switch (opcion) {

                case 1:
                    cargarRegistros();
                    break;

                case 2:
                    if (archivoProcesado) {
                        vista.mostrarRegistros(aportadores);
                    } else {
                        vista.mostrarMensaje(
                            "Primero analice el archivo."
                        );
                    }
                    break;

                case 3:
                    if (archivoProcesado) {
                        contarErrores();
                    } else {
                        vista.mostrarMensaje(
                            "Primero analice el archivo."
                        );
                    }
                    break;

                case 4:
                    vista.mostrarMensaje("Programa finalizado.");
                    break;

                default:
                    vista.mostrarMensaje("Opcion incorrecta.");
                    break;
            }

        } while (opcion != 4);
    }

    public void cargarRegistros() {

        String contenido = gestorArchivos.leerArchivo();

        if (contenido.trim().isEmpty()) {

            archivoProcesado = false;
            aportadores.clear();

            vista.mostrarMensaje(
                "El archivo esta vacio o no se encontro."
            );

            return;
        }

        aportadores = procesador.procesarInformacion(contenido);

        archivoProcesado = true;

        vista.mostrarMensaje("Archivo analizado correctamente.");

        vista.mostrarRegistros(aportadores);

        contarErrores();
    }

    public double calcularTotalIngresos() {

        double total = 0;

        for (Aportador a : aportadores) {

            if (a.isCedulaValida()
                    && a.isCorreoValido()
                    && a.isMontoValido()) {

                total += a.getMonto();
            }
        }

        return total;
    }

    public void contarErrores() {

        int erroresCedula = 0;
        int erroresCorreo = 0;
        int erroresMonto = 0;

        for (Aportador a : aportadores) {

            if (!a.isCedulaValida()) {
                erroresCedula++;
            }

            if (!a.isCorreoValido()) {
                erroresCorreo++;
            }

            if (!a.isMontoValido()) {
                erroresMonto++;
            }
        }

        vista.mostrarResumen(
            calcularTotalIngresos(),
            erroresCedula,
            erroresCorreo,
            erroresMonto
        );
    }
}
