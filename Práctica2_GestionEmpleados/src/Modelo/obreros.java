package Modelo;

public class obreros extends persona{
    protected String codigo;

    public obreros(String nombre_, long cedula_, String codigo) {
        this.setNombre(nombre_);
        this.setCedula(cedula_);
        this.codigo=codigo;
    }

    public void informacion() {
        print("NOMBRES \t CEDULA \t CODIGO \n " + this.getNombre() + "\t"+ this.getCedula() +"\t" + codigo);
    }
}
