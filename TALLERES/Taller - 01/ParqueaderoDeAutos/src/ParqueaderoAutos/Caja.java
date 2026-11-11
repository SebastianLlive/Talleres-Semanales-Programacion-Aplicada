package ParqueaderoAutos;

public class Caja {
	
	private double recaudacionTotal;

	public Caja() {
	    recaudacionTotal = GestorArchivos.cargarRecaudacion();
	}
	
	public double calcularValor(long horaIngreso) {
		
		long horaSalida = System.currentTimeMillis();
		long tiempo = horaSalida - horaIngreso;
		
		long horas = (long) Math.ceil(tiempo / 3600000.0);
		
		//Toda estancia debe cobrar al menos una hora
		if(horas < 1) {
			horas = 1;
		}
		
		return horas * 0.50;
	}
	
	public void registrarPago(double valor) {
	    GestorArchivos.guardarPago(valor);
	    recaudacionTotal += valor;
	}
	
	public void mostrarTotal() {
        System.out.printf("Total recaudado: $%.2f%n",recaudacionTotal);
	}
	
    public double getTotalRecaudado() {
        return recaudacionTotal;
    }
	
	
	
}
