package Controlador;

import Modelo.*;
import java.util.ArrayList;

public class ControladorEmpleado {

    private Repositorio<Administrativo> administrativos;
    private Repositorio<Obrero> obreros;
    private Repositorio<Tecnico> tecnicos;

    public ControladorEmpleado() {

        administrativos = new Repositorio<Administrativo>();
        obreros = new Repositorio<Obrero>();
        tecnicos = new Repositorio<Tecnico>();
    }

    // Valida los datos comunes
    private String validarDatos(String nombre, String cedula) {

        if (nombre == null || nombre.trim().isEmpty()) {
            return "El nombre es obligatorio.";
        }

        if (cedula == null || !cedula.matches("[0-9]{10}")) {
            return "La cedula debe contener exactamente 10 digitos.";
        }

        return null;
    }

    // Comprueba si la cedula ya esta registrada
    private boolean cedulaDuplicada(Long cedula) {

        return administrativos.cedulaExiste(cedula)
                || obreros.cedulaExiste(cedula)
                || tecnicos.cedulaExiste(cedula);
    }

    // Registra un administrativo
    public String registrarAdministrativo(
            String nombre, String cedulaTexto, String cargo) {

        String error = validarDatos(nombre, cedulaTexto);

        if (error != null) {
            return error;
        }

        if (cargo == null || cargo.trim().isEmpty()) {
            return "El cargo es obligatorio.";
        }

        long cedulaPrimitiva = Long.parseLong(cedulaTexto);

        // Boxing: convierte long en Long automaticamente
        Long cedula = cedulaPrimitiva;

        if (cedulaDuplicada(cedula)) {
            return "Ya existe un empleado con esa cedula.";
        }

        administrativos.registrar(
                new Administrativo(nombre.trim(), cedula, cargo.trim()));

        return "Administrativo registrado correctamente.";
    }

    // Registra un obrero
    public String registrarObrero(
            String nombre, String cedulaTexto, String codigo) {

        String error = validarDatos(nombre, cedulaTexto);

        if (error != null) {
            return error;
        }

        if (codigo == null || codigo.trim().isEmpty()) {
            return "El codigo es obligatorio.";
        }

        long cedulaPrimitiva = Long.parseLong(cedulaTexto);

        // Boxing: convierte long en Long automaticamente
        Long cedula = cedulaPrimitiva;

        if (cedulaDuplicada(cedula)) {
            return "Ya existe un empleado con esa cedula.";
        }

        obreros.registrar(
                new Obrero(nombre.trim(), cedula, codigo.trim()));

        return "Obrero registrado correctamente.";
    }

    // Registra un tecnico
    public String registrarTecnico(
            String nombre, String cedulaTexto,
            String codigo, String clave) {

        String error = validarDatos(nombre, cedulaTexto);

        if (error != null) {
            return error;
        }

        if (codigo == null || codigo.trim().isEmpty()) {
            return "El codigo es obligatorio.";
        }

        if (clave == null || clave.trim().isEmpty()) {
            return "La clave de acceso es obligatoria.";
        }

        long cedulaPrimitiva = Long.parseLong(cedulaTexto);

        // Boxing: convierte long en Long automaticamente
        Long cedula = cedulaPrimitiva;

        if (cedulaDuplicada(cedula)) {
            return "Ya existe un empleado con esa cedula.";
        }

        tecnicos.registrar(
                new Tecnico(nombre.trim(), cedula,
                        codigo.trim(), clave.trim()));

        return "Tecnico registrado correctamente.";
    }

    // RF-04: consulta de empleados
    public String consultarEmpleado(String cedulaTexto) {

        if (cedulaTexto == null
                || !cedulaTexto.matches("[0-9]{10}")) {
            return "Ingrese una cedula valida de 10 digitos.";
        }

        long cedulaPrimitiva = Long.parseLong(cedulaTexto);

        // Boxing
        Long cedula = cedulaPrimitiva;

        Administrativo administrativo = administrativos.buscar(cedula);

        if (administrativo != null) {
            return obtenerInformacion(administrativo, "ADMINISTRATIVO");
        }

        Tecnico tecnico = tecnicos.buscar(cedula);

        if (tecnico != null) {
            return obtenerInformacion(tecnico, "TECNICO");
        }

        Obrero obrero = obreros.buscar(cedula);

        if (obrero != null) {
            return obtenerInformacion(obrero, "OBRERO");
        }

        return "No se encontro ningun empleado con esa cedula.";
    }

    // Obtiene la informacion segun el tipo de empleado
    private String obtenerInformacion(Persona persona, String tipo) {

        String informacion = "TIPO: " + tipo
                + "\nNombre: " + persona.getNombre()
                + "\nCedula: "
                + String.format("%010d", persona.getCedula());

        if (persona instanceof Administrativo) {

            Administrativo a = (Administrativo) persona;
            informacion += "\nCargo: " + a.getCargo();

        } else if (persona instanceof Tecnico) {

            Tecnico t = (Tecnico) persona;
            informacion += "\nCodigo: " + t.getCodigo()
                    + "\nClave de acceso: " + t.getClaveAcceso();

        } else if (persona instanceof Obrero) {

            Obrero o = (Obrero) persona;
            informacion += "\nCodigo: " + o.getCodigo();
        }

        return informacion;
    }

    // RF-05: listar administrativos
    public String listarAdministrativos() {
        return listarPersonas(
                administrativos.listar(), "ADMINISTRATIVOS");
    }

    // RF-05: listar obreros
    public String listarObreros() {
        return listarPersonas(
                obreros.listar(), "OBREROS");
    }

    // RF-05: listar tecnicos
    public String listarTecnicos() {
        return listarPersonas(
                tecnicos.listar(), "TECNICOS");
    }

    // Metodo generico para mostrar las listas
    private String listarPersonas(
            ArrayList<? extends Persona> lista, String titulo) {

        if (lista.isEmpty()) {
            return "No existen registros de " + titulo.toLowerCase() + ".";
        }

        String resultado = titulo + "\n\n";

        for (Persona persona : lista) {

            resultado += "Nombre: " + persona.getNombre()
                    + "\nCedula: "
                    + String.format("%010d", persona.getCedula())
                    + "\n--------------------------\n";
        }

        return resultado;
    }
}