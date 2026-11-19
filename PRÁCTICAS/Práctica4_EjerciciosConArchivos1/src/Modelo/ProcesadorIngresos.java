
package Modelo;

import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class ProcesadorIngresos {

    private Pattern patronCedula;
    private Pattern patronCorreo;
    private Pattern patronMonto;

    public ProcesadorIngresos() {

        // Buscar cedulas precedidas por cedula o CI
        patronCedula = Pattern.compile(
            "(?i)\\b(?:c[eé]dula|CI)\\b\\s*(?:es\\s*)?[:]?\\s*([0-9]+)"
        );

        // Buscar correos despues de la palabra correo
        patronCorreo = Pattern.compile(
            "(?i)\\bcorreo(?:\\s+electr[oó]nico)?\\s*(?:es\\s*)?[:]?\\s*([^\\s,;]+)"
        );

        // Buscar montos con $ antes o despues,
        // o con las palabras dolares y USD
        patronMonto = Pattern.compile(
            "(?<![\\w])(?:\\$\\s*(\\d+(?:[.,]\\d{1,2})?)" +
            "|(\\d+(?:[.,]\\d{1,2})?)\\s*(?:d[oó]lares|USD|\\$))(?![\\w])",
            Pattern.CASE_INSENSITIVE
        );
    }

    // Clase auxiliar para conservar la posicion de cada dato
    private static class Dato {

        String valor;
        int posicion;

        public Dato(String valor, int posicion) {
            this.valor = valor;
            this.posicion = posicion;
        }
    }

    // Extraer cedulas
    public ArrayList<Dato> buscarCedulas(String texto) {

        ArrayList<Dato> lista = new ArrayList<>();

        Matcher matcher = patronCedula.matcher(texto);

        while (matcher.find()) {

            lista.add(new Dato(
                matcher.group(1),
                matcher.start(1)
            ));
        }

        return lista;
    }

    // Extraer correos
    public ArrayList<Dato> buscarCorreos(String texto) {

        ArrayList<Dato> lista = new ArrayList<>();

        Matcher matcher = patronCorreo.matcher(texto);

        while (matcher.find()) {

            String correo = matcher.group(1);

            // Quitar signos de puntuacion finales
            correo = correo.replaceAll("[.!?:,;]+$", "");

            lista.add(new Dato(
                correo,
                matcher.start(1)
            ));
        }

        return lista;
    }

    // Extraer montos
    public ArrayList<Dato> buscarMontos(String texto) {

        ArrayList<Dato> lista = new ArrayList<>();

        Matcher matcher = patronMonto.matcher(texto);

        while (matcher.find()) {

            String valor = matcher.group(1);

            if (valor == null) {
                valor = matcher.group(2);
            }

            valor = valor.replace(",", ".");

            lista.add(new Dato(
                valor,
                matcher.start()
            ));
        }

        return lista;
    }

    // Validar cedula: exactamente 10 digitos
    public boolean validarCedula(String cedula) {

        return cedula != null && cedula.matches("\\d{10}");
    }

    // Validar estructura del correo
    public boolean validarCorreo(String correo) {

        if (correo == null || correo.isEmpty()) {
            return false;
        }

        String regex =
            "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";

        return correo.matches(regex);
    }

    // Validar que el monto sea positivo
    public boolean validarMonto(double monto) {

        return monto > 0;
    }

    // Buscar el dato mas cercano a la posicion de la cedula
    private int buscarMasCercano(
            ArrayList<Dato> datos,
            boolean[] utilizados,
            int posicion) {

        int indice = -1;
        int distanciaMinima = Integer.MAX_VALUE;

        for (int i = 0; i < datos.size(); i++) {

            if (!utilizados[i]) {

                int distancia = Math.abs(
                    datos.get(i).posicion - posicion
                );

                if (distancia < distanciaMinima) {

                    distanciaMinima = distancia;
                    indice = i;
                }
            }
        }

        return indice;
    }

    // Procesar toda la informacion del archivo
    public ArrayList<Aportador> procesarInformacion(String texto) {

        ArrayList<Aportador> aportadores = new ArrayList<>();

        ArrayList<Dato> cedulas = buscarCedulas(texto);
        ArrayList<Dato> correos = buscarCorreos(texto);
        ArrayList<Dato> montos = buscarMontos(texto);

        boolean[] correosUtilizados = new boolean[correos.size()];
        boolean[] montosUtilizados = new boolean[montos.size()];

        // Relacionar cada cedula con el correo y monto
        // encontrados mas cerca dentro del texto
        for (Dato datoCedula : cedulas) {

            String cedula = datoCedula.valor;
            String correo = "";
            double monto = 0;

            int indiceCorreo = buscarMasCercano(
                correos,
                correosUtilizados,
                datoCedula.posicion
            );

            if (indiceCorreo != -1) {

                correo = correos.get(indiceCorreo).valor;
                correosUtilizados[indiceCorreo] = true;
            }

            int indiceMonto = buscarMasCercano(
                montos,
                montosUtilizados,
                datoCedula.posicion
            );

            if (indiceMonto != -1) {

                monto = Double.parseDouble(
                    montos.get(indiceMonto).valor
                );

                montosUtilizados[indiceMonto] = true;
            }

            Aportador aportador = new Aportador(
                cedula,
                correo,
                monto
            );

            aportador.setCedulaValida(
                validarCedula(cedula)
            );

            aportador.setCorreoValido(
                validarCorreo(correo)
            );

            aportador.setMontoValido(
                indiceMonto != -1 && validarMonto(monto)
            );

            aportadores.add(aportador);
        }

        return aportadores;
    }
}
