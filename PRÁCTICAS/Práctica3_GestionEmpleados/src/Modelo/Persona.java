package Modelo;

public class Persona {

    private String nombre;
    private Long cedula;

    public Persona(String nombre, Long cedula) {
        this.nombre = nombre;
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getCedula() {
        return cedula;
    }

    public void setCedula(Long cedula) {
        this.cedula = cedula;
    }

    public void informacion() {
        System.out.println("Nombre: " + nombre);
        System.out.println("Cedula: " + cedula);
    }
}