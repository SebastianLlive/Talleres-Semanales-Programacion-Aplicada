package Modelo;

public class administrativos extends persona{
    private String cargo;

    public administrativos(String nombre, long cedula, String cargo) {
        this.setNombre(nombre);
        this.setCedula(cedula);
        this.cargo=cargo;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public void informacion() {
        print("NOMBRE \t CARGO \n "+ this.getNombre() + "\t" + this.getCargo());
    }
}