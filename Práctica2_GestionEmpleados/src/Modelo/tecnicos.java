package Modelo;

public class tecnicos extends obreros{
    private String clave_acceso;

    public tecnicos(String nombre_, long cedula_, String codigo, String clave_acceso) {
        super(nombre_, cedula_, codigo);
        this.setClave_acceso(clave_acceso);
        // TODO Auto-generated constructor stub
    }

    public String getClave_acceso() {
        return clave_acceso;
    }

    public void setClave_acceso(String clave_acceso) {
        this.clave_acceso = clave_acceso;
    }
}