package Practica5Hilos;

public class Vehiculo {

    private String marca;
    private String placa;
    private Gases gases;

    public Vehiculo(String marca, String placa, Gases gases) {

        this.marca = marca;
        this.placa = placa;
        this.gases = gases;
    }

    public String getMarca() {
        return marca;
    }

    public String getPlaca() {
        return placa;
    }

    public Gases getGases() {
        return gases;
    }
}
