package org.example;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Ruleta {
    public static final int MAX_HISTORIAL = 100;
    public static int[] historialNumeros = new int[MAX_HISTORIAL];
    public static int[] historialApuestas = new int[MAX_HISTORIAL];
    public static boolean[] historialAciertos = new boolean[MAX_HISTORIAL];
    public static int historialSize = 0;
    public static Random rng = new Random();
    public static int[] numerosRojos =
            {1,3,5,7,9,12,14,16,18,19,21,23,25,27,30,32,34,36};
    /**
     * Método principal: inicia el programa llamando al menú.
     */
    public static void main(String[] args) {
        menu();
    }
    /**
     * Controla el flujo principal del programa mostrando un menú en consola.
     */
    public static void menu() {
        while (true) {
            mostrarMenu();
            Scanner scanner = new Scanner(System.in);
            int opcion = leerOpcion(scanner);
            ejecutarOpcion(opcion, scanner);
        }
    }
    /**
     * Muestra en consola las opciones disponibles del menú.
     */

    public static void mostrarMenu() {
        System.out.println("1.Iniciar ronda");
        System.out.println("2.Mostrar estadisticas");
        System.out.println("3.Salir");
    }
    /**
     * Lee la opción elegida por el usuario desde teclado.
     * @param in Scanner para entrada por consola.
     * @return número de opción ingresado.
     */
    public static int leerOpcion(Scanner in) {
        int opcion = in.nextInt();
        return opcion;
    }
    /**
     * Ejecuta la acción correspondiente a la opción del menú.
     * @param opcion opción elegida por el usuario.
     * @param in Scanner para entrada por consola.
     */
    public static void ejecutarOpcion(int opcion, Scanner in) {
        if (opcion == 1){
            System.out.println("Ejecutando...");
            iniciarRonda(in);
        } else if (opcion == 2) {
            mostrarEstadisticas();
        } else if (opcion == 3) {
            System.exit(0);
        }
    }
    /**
     * Inicia una ronda de la ruleta: leer apuesta, girar, evaluar y mostrar resultado.
     * @param in Scanner para entrada por consola.
     */
    public static void iniciarRonda(Scanner in) {
        System.out.println("Indique el tipo de apuesta (R/N/P/I): ");
        char tipo = leerTipoApuesta(in);
        System.out.println("Indique el monto: ");
        int monto = in.nextInt();
        int numero = girarRuleta();
        boolean acierto = evaluarResultado(numero, tipo);
        registrarResultado(numero,monto, acierto);
        mostrarResultado(numero,tipo,monto,acierto);
    }
    /**
     * Permite al usuario seleccionar el tipo de apuesta (R/N/P/I).
     * @param in Scanner para entrada por consola.
     * @return el tipo de apuesta elegido.
     */
    public static char leerTipoApuesta(Scanner in) {
        char seleccion = in.next().charAt(0);
        return seleccion;
    }
    /**
     * Simula el giro de la ruleta generando un número aleatorio de 0 a 36.
     * @return número de la ruleta.
     */
    public static int girarRuleta() {
        int numeroRuleta = rng.nextInt(37);
        return numeroRuleta;
    }
    /**
     * Evalúa si la apuesta realizada por el jugador fue acertada.
     * @param numero número obtenido en la ruleta.
     * @param tipo tipo de apuesta elegida.
     * @return true si acertó, false si perdió.
     */
    public static boolean evaluarResultado(int numero, char tipo) {
        if (tipo == 'R') {
            boolean acierto = esRojo(numero);
            return acierto;

        }else if (tipo == 'N'){
            boolean acierto = esRojo(numero);
            return acierto;
        }else if (tipo == 'P'){
            if (numero % 2 == 0){
                return true;
            }else {
                return false;
            }
        }else if (tipo == 'I'){
            if (numero %2 ==1){
                return true;
            }else  {
                return false;
            }
        }
        return false;
    }
    /**
     * Determina si un número corresponde a color rojo.
     * @param n número de la ruleta.
     * @return true si es rojo, false en caso contrario.
     */
    public static boolean esRojo(int n) {
        for (int numero : numerosRojos){
            if (numero == n){
                return true;
            }
        }
        return false;
    }
    /**
     * Registra los resultados de la ronda en los arreglos de historial.
     * @param numero número obtenido en la ruleta.
     * @param apuesta monto apostado.
     * @param acierto si el jugador acertó o no.
     */
    public static void registrarResultado(int numero, int apuesta, boolean acierto) {
        historialNumeros[historialSize] = numero;
        historialApuestas[historialSize] = apuesta;
        historialAciertos[historialSize] = acierto;
        historialSize += 1;
    }
    /**
     * Muestra en consola el resultado de la ronda.
     * @param numero número obtenido en la ruleta.
     * @param tipo tipo de apuesta realizada.
     * @param monto monto apostado.
     * @param acierto si el jugador ganó o perdió.
     */
    public static void mostrarResultado(int numero, char tipo, int monto, boolean
            acierto) {
        System.out.println("Este giro cayo en:");
        System.out.println(numero);
        System.out.println("La apuesta fue:");
        System.out.println(tipo);
        System.out.println("El monto apostado fue:");
        System.out.println(monto);
        System.out.println("La apuesta fue:");
        System.out.println(acierto);
    }
    /**
     * Muestra estadísticas generales de todas las rondas jugadas.
     */
    public static void mostrarEstadisticas() {
        int montoTotal = 0;
        int totalAciertos = 0;
        int ganancia = 0;
        float porcentaje;

        for (int i = 0; i < historialSize; i++){
            montoTotal += historialApuestas[i];
            if (historialAciertos[i]){
                totalAciertos++;
                ganancia += historialApuestas[i];
            }else {
                ganancia -= historialApuestas[i];
            }
        }

        porcentaje = ((float) totalAciertos /historialSize)*100;

        System.out.println("Se han jugado "+ historialSize + "rondas.");
        System.out.println("Se ha apostado un total de " + montoTotal +"$");
        System.out.println("Se ha acertado la apuesta un total de " + totalAciertos + " veces.");
        System.out.println("Tiene un porcentaje de acierto del " + porcentaje + "%");
        System.out.println("En total su ganancia/perdida fue de: " + ganancia + "$");
    }
}