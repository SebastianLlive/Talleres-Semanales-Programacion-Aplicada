package Cafeteria;

public class Detalle_Pedido {
	private Producto producto;
	private int unidades;
	private Double subtotal;
	
	public Detalle_Pedido(Producto producto, int unidades) {
		super();
		this.producto = producto;
		this.unidades = unidades;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public int getUnidades() {
		return unidades;
	}

	public void setUnidades(int unidades) {
		this.unidades = unidades;
	}

	public Double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(Double subtotal) {
		this.subtotal = subtotal;
	}
	
	public void calcularSubtotal() {
		
	}
	
	
}
