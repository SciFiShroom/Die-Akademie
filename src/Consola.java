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
                sc.close();
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
            }
        }
    }



}
