package Controlador;

import Modelo.Placa;
import Modelo.ArchivoTXT;
import Vista.VentanaPrincipal;

import java.io.IOException;

public class PlacaController {

    private VentanaPrincipal vista;
    private ArchivoTXT archivo;

    public PlacaController(VentanaPrincipal vista) {

        this.vista = vista;
        this.archivo = new ArchivoTXT();

        vista.getBtnValidar().addActionListener(e -> validarPlaca());

        vista.getBtnRegistrar().addActionListener(e -> registrarPlaca());
    }

    private void validarPlaca() {

        String numero = vista.getPlaca();

        Placa placa = new Placa(numero);

        if (placa.validar()) {

            vista.mostrarMensaje("Placa valida");

        } else {

            vista.mostrarMensaje("Formato incorrecto: ABC-1234");
        }
    }

    private void registrarPlaca() {

        String numero = vista.getPlaca();

        Placa placa = new Placa(numero);

        if (!placa.validar()) {

            vista.mostrarMensaje("Ingrese una placa valida");
            return;
        }

        try {

            if (archivo.existe(placa.getNumero())) {

                vista.mostrarMensaje("La placa ya esta registrada");
                return;
            }

            archivo.guardar(placa.getNumero());

            vista.mostrarMensaje("Placa registrada correctamente");

            vista.limpiarCampo();

        } catch (IOException e) {

            vista.mostrarMensaje("Error al guardar el archivo");
            e.printStackTrace();
        }
    }
}
