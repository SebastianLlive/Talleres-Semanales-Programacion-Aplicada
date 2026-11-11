package Modelo;

import java.util.ArrayList;

public class Repositorio<T extends Persona> {

    private ArrayList<T> empleados;

    public Repositorio() {
        empleados = new ArrayList<T>();
    }

    // RF-01, RF-02 y RF-03: registrar empleados
    public void registrar(T empleado) {
        empleados.add(empleado);
    }

    // RF-04: buscar empleado por cedula
    public T buscar(Long cedula) {

        for (T empleado : empleados) {

            // Unboxing: convierte Long a long automáticamente
            long cedulaEmpleado = empleado.getCedula();

            if (cedulaEmpleado == cedula.longValue()) {
                return empleado;
            }
        }

        return null;
    }

    // RF-06: comprobar cedulas duplicadas
    public boolean cedulaExiste(Long cedula) {
        return buscar(cedula) != null;
    }

    // RF-05: listar empleados
    public ArrayList<T> listar() {
        return empleados;
    }

    public int cantidad() {
        return empleados.size();
    }
}