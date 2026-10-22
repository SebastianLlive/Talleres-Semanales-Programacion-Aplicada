package Cafeteria;

import java.util.ArrayList;

public class Pedido {
	private int idPedido;
	private ArrayList<Detalle_Pedido> detalles;
	private Double total;
	
	
	public Pedido(int idPedido) {
		super();
		this.idPedido = idPedido;
		this.detalles = new ArrayList<>();
		
	}
	
		
	public Double getTotal() {
		return total;
	}

	
	
	public int getIdPedido() {
		return idPedido;
	}


	public ArrayList<Detalle_Pedido> getDetalles() {
		return detalles;
	}


	public void agregarDetalle(Detalle_Pedido detalle) {
		this.detalles.add(detalle);
	}
	
	public void totalPedido() {
		this.total = 0.0;
		
		for(Detalle_Pedido detalle: detalles) {
			detalle.calcularSubtotal();
			this.total = this.total + detalle.getSubtotal();
		}
	}
}
