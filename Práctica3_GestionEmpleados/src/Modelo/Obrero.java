package Modelo;

public class Obrero extends Persona {

    protected String codigo;

    public Obrero(String nombre, Long cedula, String codigo) {
        super(nombre, cedula);
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @Override
    public void informacion() {

        System.out.println("TIPO: OBRERO");
        System.out.println("Nombre: " + getNombre());
        System.out.println("Cedula: " + getCedula());
        System.out.println("Codigo: " + codigo);
    }
}