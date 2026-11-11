package Modelo;

public class Administrativo extends Persona {

    private String cargo;

    public Administrativo(String nombre, Long cedula, String cargo) {
        super(nombre, cedula);
        this.cargo = cargo;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    @Override
    public void informacion() {

        System.out.println("TIPO: ADMINISTRATIVO");
        System.out.println("Nombre: " + getNombre());
        System.out.println("Cedula: " + getCedula());
        System.out.println("Cargo: " + cargo);
    }
}