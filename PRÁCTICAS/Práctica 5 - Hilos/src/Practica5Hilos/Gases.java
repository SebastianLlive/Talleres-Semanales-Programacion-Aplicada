package Practica5Hilos;

public class Gases {

    private double lambda;
    private double o2;
    private double n2o;
    private double co2;

    public Gases(double lambda, double o2,
                 double n2o, double co2) {

        this.lambda = lambda;
        this.o2 = o2;
        this.n2o = n2o;
        this.co2 = co2;
    }

    public double getLambda() {
        return lambda;
    }

    public double getO2() {
        return o2;
    }

    public double getN2O() {
        return n2o;
    }

    public double getCO2() {
        return co2;
    }
}
