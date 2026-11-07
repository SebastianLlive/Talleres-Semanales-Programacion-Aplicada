package Modelo;

public class Tecnico extends Obrero {

    private String claveAcceso;

    public Tecnico(String nombre, Long cedula,
            String codigo, String claveAcceso) {

        super(nombre, cedula, codigo);
        this.claveAcceso = claveAcceso;
    }

    public String getClaveAcceso() {
        return claveAcceso;
    }

    public void setClaveAcceso(String claveAcceso) {
        this.claveAcceso = claveAcceso;
    }

    @Override
    public void informacion() {

        System.out.println("TIPO: TECNICO");
        System.out.println("Nombre: " + getNombre());
        System.out.println("Cedula: " + getCedula());
        System.out.println("Codigo: " + codigo);
        System.out.println("Clave de acceso: " + claveAcceso);
    }
}