//SEBASTIAN LLIVE
package ParqueaderoAutos;

import java.util.Scanner;

public class Principal {

	public static void main(String[] args) {
		
        Scanner sc = new Scanner(System.in);

        Parqueadero parqueadero = new Parqueadero();
        Caja caja = new Caja();

        boolean flag = true;

        do {
            System.out.println("\n===== PARQUEADERO =====");
            System.out.println("Ingreso [i]");
            System.out.println("Listar vehiculos [l]");
            System.out.println("Salida [s]");
            System.out.println("Consultar caja [c]");
            System.out.println("Salir [x]");
            System.out.print("Elija una opcion: ");

            char opcion = sc.next().toLowerCase().charAt(0);

            switch (opcion) {

                case 'i':
                    parqueadero.registrarIngreso(sc);
                    break;

                case 'l':
                    parqueadero.listarVehiculos();
                    break;

                case 's':
                    parqueadero.registrarSalida(sc, caja);
                    break;

                case 'c':
                    caja.mostrarTotal();
                    break;

                case 'x':
                    flag = false;
                    System.out.println(
                            "Fin del programa..."
                    );
                    break;

                default:
                    System.out.println(
                            "Opcion incorrecta."
                    );
                    break;
            }

        } while (flag);

        sc.close();

	}

}
