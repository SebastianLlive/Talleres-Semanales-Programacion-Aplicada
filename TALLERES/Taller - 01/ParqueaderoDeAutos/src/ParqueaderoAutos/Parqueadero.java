package ParqueaderoAutos;

import java.util.Scanner;
import java.util.ArrayList;

public class Parqueadero {
	
    private ArrayList<Vehiculo> vehiculos;
    
    public Parqueadero() {
        vehiculos = GestorArchivos.cargarVehiculos();
    }
    
    //Registrar el ingreso del vehiculo
    public void registrarIngreso(Scanner sc) {
    	
        System.out.println("Ingrese la placa:");
        String placa = sc.next();

        // Evitar placas repetidas
        if (buscarVehiculo(placa) != null) {
            System.out.println("Ese vehiculo ya esta en el parqueadero...");
            return;
        }

        System.out.println("Ingrese la marca:");
        String marca = sc.next();

        System.out.println("Ingrese el modelo:");
        String modelo = sc.next();

        long horaIngreso = System.currentTimeMillis();

        Vehiculo v = new Vehiculo(placa, marca, modelo, horaIngreso);

        vehiculos.add(v);
        GestorArchivos.guardarVehiculos(vehiculos);

        System.out.println("Vehiculo registrado correctamente...");
    }
	
    // Listar vehículos estacionados
    public void listarVehiculos() {
    	
        if (vehiculos.isEmpty()) {
            System.out.println("No hay vehiculos estacionados.");
            return;
        }

        for (int i = 0; i < vehiculos.size(); i++) {
            System.out.printf("%d. %s%n",i + 1,vehiculos.get(i));
        }
    }
    
    
    // Buscar vehículo por placa
    public Vehiculo buscarVehiculo(String placa) {
        for (Vehiculo v : vehiculos) {
            if (v.getPlaca().equalsIgnoreCase(placa)) {
                return v;
            }
        }

        return null;
    }
    
    // Registrar salida y cobrar
    public void registrarSalida(Scanner sc, Caja caja) {
        
    	System.out.println("Ingrese la placa del vehiculo:");
        String placa = sc.next();

        Vehiculo v = buscarVehiculo(placa);

        if (v == null) {
            System.out.println("Vehiculo no encontrado.");
            return;
        }

        double valor = caja.calcularValor(v.getHoraIngreso());

        System.out.println("Vehiculo: " + v);
        System.out.printf("Valor a pagar: $%.2f%n",valor);

        caja.registrarPago(valor);
        vehiculos.remove(v);
        GestorArchivos.guardarVehiculos(vehiculos);

        System.out.println("Salida registrada correctamente.");
    }
    
    
}
