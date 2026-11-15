
package Controlador;

import java.util.ArrayList;

import Modelo.Aportador;
import Modelo.ProcesadorIngresos;
import archivos.GestorArchivos;
import Vista.VentanaIngresos;

public class IngresosController {

    private ProcesadorIngresos procesador;
    private VentanaIngresos vista;
    private ArrayList<Aportador> aportadores;

    // CONSTRUCTOR
    public IngresosController() {

        procesador = new ProcesadorIngresos();

        vista = new VentanaIngresos();

        aportadores = new ArrayList<>();

        configurarEventos();
    }

    // CONFIGURAR EVENTOS DE LOS BOTONES
    private void configurarEventos() {

        vista.getBtnAnalizar().addActionListener(e -> {
            analizarArchivo();
        });

        vista.getBtnLimpiar().addActionListener(e -> {
            limpiarResultados();
        });
    }

    // INICIAR INTERFAZ
    public void iniciar() {
        vista.setVisible(true);
    }

    // ANALIZAR ARCHIVO
    private void analizarArchivo() {

        String ruta = vista.seleccionarArchivo();

        if (ruta == null) {
            return;
        }

        GestorArchivos gestor = new GestorArchivos(ruta);

        String contenido = gestor.leerArchivo();

        if (contenido.isBlank()) {

            aportadores.clear();

            vista.limpiar();

            vista.mostrarMensaje(
                "El archivo esta vacio o no se pudo leer."
            );

            return;
        }

        aportadores = procesador.procesarInformacion(contenido);

        mostrarResultados();
    }

    // MOSTRAR REGISTROS
    private void mostrarResultados() {

        String[][] datos = new String[aportadores.size()][8];

        int contador = 0;

        for (Aportador a : aportadores) {

            boolean valido =
                a.isCedulaValida()
                && a.isCorreoValido()
                && a.isMontoValido();

            datos[contador][0] = String.valueOf(contador + 1);

            datos[contador][1] = a.getCedula();

            datos[contador][2] = a.getCorreo();

            datos[contador][3] =
                String.format("$%.2f", a.getMonto());

            datos[contador][4] =
                a.isCedulaValida() ? "VALIDA" : "ERROR";

            datos[contador][5] =
                a.isCorreoValido() ? "VALIDO" : "ERROR";

            datos[contador][6] =
                a.isMontoValido() ? "VALIDO" : "ERROR";

            datos[contador][7] =
                valido ? "VALIDO" : "CON ERRORES";

            contador++;
        }

        vista.mostrarRegistros(datos);

        mostrarResumen();

        vista.mostrarMensaje(
            "Archivo analizado correctamente."
        );
    }

    // CALCULAR INGRESOS Y ERRORES
    private void mostrarResumen() {

        double total = 0;

        int erroresCedula = 0;
        int erroresCorreo = 0;
        int erroresMonto = 0;

        for (Aportador a : aportadores) {

            if (a.isCedulaValida()
                    && a.isCorreoValido()
                    && a.isMontoValido()) {

                total += a.getMonto();
            }

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

        String resumen =
            "========== RESUMEN GENERAL ==========\n"
            + "Total de registros: " + aportadores.size() + "\n"
            + String.format("Ingresos validos: $%.2f%n", total)
            + "Cedulas con errores: " + erroresCedula + "\n"
            + "Correos con errores: " + erroresCorreo + "\n"
            + "Montos con errores: " + erroresMonto + "\n"
            + "=====================================";

        vista.mostrarResumen(resumen);
    }

    // LIMPIAR RESULTADOS
    private void limpiarResultados() {

        aportadores.clear();

        vista.limpiar();
    }
}
