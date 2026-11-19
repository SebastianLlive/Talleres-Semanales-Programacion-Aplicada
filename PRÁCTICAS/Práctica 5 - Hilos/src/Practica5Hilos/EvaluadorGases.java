package Practica5Hilos;

public class EvaluadorGases {

    public static double calcularError(
            double valor, double limite, boolean inclusivo) {

        boolean falla = inclusivo
                ? valor >= limite
                : valor > limite;

        if (falla) {
            return ((valor - limite) / limite) * 100;
        }

        return 0;
    }

    public static boolean evaluarVehiculo(Vehiculo v) {

        Gases g = v.getGases();

        double errorLambda = calcularError(g.getLambda(), 1, false);
        double errorO2 = calcularError(g.getO2(), 2, true);
        double errorN2O = calcularError(g.getN2O(), 40, true);
        double errorCO2 = calcularError(g.getCO2(), 15, true);

        boolean lambda = errorLambda <= 70;
        boolean o2 = errorO2 <= 30;
        boolean n2o = errorN2O <= 80;
        boolean co2 = errorCO2 <= 50;

        double errorTotal = (errorLambda + errorO2 +
                errorN2O + errorCO2) / 4;

        boolean aprobado = lambda && o2 && n2o && co2;

        System.out.printf(
            "%-12s %-12s %-10.2f %-10.2f %-10.2f %-10.2f %-10.2f%% %-25s%n",
            v.getMarca(),
            v.getPlaca(),
            g.getLambda(),
            g.getO2(),
            g.getN2O(),
            g.getCO2(),
            errorTotal,
            aprobado ? "Pasa en Primera Revision"
                     : "Segunda Revision Asignada"
        );

        return aprobado;
    }
}
