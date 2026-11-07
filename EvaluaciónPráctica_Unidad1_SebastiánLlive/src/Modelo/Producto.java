package Modelo;

public class Producto {
	private String nombreProducto;
	private String codigo;
	private String cantidadProducida;
	
	public Producto(String nombreProducto, String codigo, String cantidadProducida) {
		super();
		this.nombreProducto = nombreProducto;
		this.codigo = codigo;
		this.cantidadProducida = cantidadProducida;
	}

	public String getNombreProducto() {
		return nombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getCantidadProducida() {
		return cantidadProducida;
	}

	public void setCantidadProducida(String cantidadProducida) {
		this.cantidadProducida = cantidadProducida;
	}
	
	
	
	
	
}
