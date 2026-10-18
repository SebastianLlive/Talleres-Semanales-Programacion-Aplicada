package ParqueaderoAutos;

public class Vehiculo {
	
	private String placa;
	private String marca;
	private String modelo;
	private long horaIngreso;
	
	//Constructor Vacío
	public Vehiculo() {
	}

	//Constructor con Parámetros
	public Vehiculo(String placa, String marca, String modelo, long horaIngreso) {
		super();
		this.placa = placa;
		this.marca = marca;
		this.modelo = modelo;
		this.horaIngreso = horaIngreso;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public long getHoraIngreso() {
		return horaIngreso;
	}

	public void setHoraIngreso(long horaIngreso) {
		this.horaIngreso = horaIngreso;
	}

	@Override
	public String toString() {
		return "Vehiculo [placa=" + placa + ", marca=" + marca + ", modelo=" + modelo + ", horaIngreso=" + horaIngreso
				+ "]";
	}
	
	
	
	
	
}
