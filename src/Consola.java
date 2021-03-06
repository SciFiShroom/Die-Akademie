import java.util.HashMap;
import java.util.Scanner;
import java.util.ArrayList;
public class Consola {

    //inicializado al comienzo de la consola.
    public Scanner sc;

    //El nombre de la consola
    public String nombre;

    //Mensaje enseñado al comienzo
    public String[] startUp;

    //Mensaje enseñado cada iteración
    public String mensajeIter;

    //Lista de comandos accecibles por la consola
    public String[] comandosDisponibles;

    //Indicador de input del usuario
    public static final String inputUsuario = ">>";


    /**
     * Consola de uso general capaz de ejecutar los comandos en Ejer.java
     * La validez de los comandos revisa al inicializar la consola.
     * La consola se puede activar en cualquier instante, y se puede re-activar si se cerró en algún instante.
     * @param Nombre el nombre de la consola.
     * @param StartUp Mensaje que se imprime al actvar la consola
     * @param Comandos Un String[] con las claves de los comandos cuyos pueden ser accedidos por la consola.
     * @param MensajeIter Mensaje que se enseña cada iterción de la consola.
     * @param activar se activa la consola inmediatamente si activar==cierto. Si no, usa .activar para activarla después.
     */
    public Consola(String Nombre, String[] StartUp, String[] Comandos, String MensajeIter, boolean activar) {
        this.nombre = Nombre;
        this.mensajeIter = MensajeIter;
        this.startUp = StartUp;

        if (Ejer.revisarColisionesComandos(Comandos)) {
            comandosDisponibles = Comandos;
        } else {
            throw new NumberFormatException("Error: Comandos no válidos");
        }


        if (activar) this.activar();
    }




    public void activar() {
        this.sc = new Scanner(System.in);
        for (String Mensaje : this.startUp) {
            System.out.println(Mensaje);
        }


        while (true) {
            System.out.print(Consola.inputUsuario);
            String comando = sc.nextLine();

            //Hay solo dos comandos especiales que interactúan con la consola, que cada consola tendrá: 'cerrar' y 'listar comandos'
            if (comando.equals("cerrar")) {
                System.out.println("Cerrando consola " + nombre);
                return;
            }
            if (comando.equals("listar comandos")) {
                System.out.println("Comandos: " + this.nombre);
                System.out.println(" - listar comandos: Imprime esta lista.");
                System.out.println(" - cerrar: Cierra la consola.");
                for (String comandoActual : comandosDisponibles) {
                    System.out.println(" - " + Ejer.ComandosParse.get(comandoActual) + ": " + Ejer.ComandosDescripción.get(comandoActual));
                }
                continue;
            }

            try {
                Ejer.EjecutarComando(comando, this.comandosDisponibles);
            } catch (SecurityException h) { //No se recinoció el comando
                System.out.println("El comando '" + comando + "' no se reconoce. Diga 'listar comandos' para ver la lista completa de comandos.");
            } catch (ConsolaCerradaException e) {
                System.out.println("Se ha cerrado el ejercicio.");
            }
        }
    }



}
