import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Lector {

    public File archivo; //El archivo .txt
    public Scanner sc; //Scanner que lee el archivo
    public Scanner usuario; //Scanner que detecta input del usuario
    public boolean imprimir; //Booleano que controla si se imprime o no
    public int línea; //La línea actual en el texto. La primera línea es 1.
    public HashMap<String, Integer> controlGoto;
    public int tamaño; //Cantidad de filas en el texto


    public Lector(String Archivo, boolean ejecutar) {
        //File archivo = new File("./src/" + Archivo);

        //Verifica que sí exista el archivo / Que se haya formateado el nombre bien:
        archivo = new File(Archivo);
        try {
            sc = new Scanner(archivo);
        } catch (FileNotFoundException e) {
            throw new NumberFormatException("Error: El archivo '" + Archivo + "' no se encuentra. [Syntaxis ~ ./src/texto.txt]");
        } //Si salimos el Scanner del archivo ya estará funcional.


        //Revisa que los marcadores estén forateados correctamente, y crea un mapa con sus posiciones.
        RevisiónGoto();
        //¡MUY IMPORTANTE!


        //Inicialización de los otros parámetros
        usuario = new Scanner(System.in); //Se utiliza para pausas, input del usuario, goto, ...
        línea = 0;
        imprimir = true;


        if (ejecutar) {lector();}
    }

    public void RevisiónGoto() {
        controlGoto = new HashMap<String, Integer>();
        try {
            Scanner revisiónMarcadores = new Scanner(archivo);

            int líneaActual = 0;
            while (revisiónMarcadores.hasNextLine()) {
                String fila = revisiónMarcadores.nextLine();
                líneaActual++;

                //Detectamos los marcadores
                if (fila.length() >= 9 && fila.substring(0, 9).equals("Marcador ")) {
                    //¡Hallamos un marcador!
                    String clave = fila.substring(9, fila.length());

                    if (clave.equals("")) {
                        throw new NumberFormatException("Error: Marcador sin clave.");
                    } else if (controlGoto.containsKey(clave)) {
                        throw new NumberFormatException("Error: Existen dos marcadores con clave '" + clave + "'. Líneas " + controlGoto.get(clave) + " y " + líneaActual);
                    } else if (clave.substring(clave.length()-1, clave.length()).equals(" ")) {
                        throw new NumberFormatException("Error: Clave acaba con un espacio ' '.");
                    }

                    //Si no hay errores, agregamos la clave.
                    controlGoto.put(clave, líneaActual);
                }
            }

            this.tamaño = líneaActual;


            //Una vez habiendo compilado los marcadores, nos aseguramos de que no haya gotos a marcadores inexistentes.
            revisiónMarcadores = new Scanner(archivo);
            líneaActual = 0;
            while(revisiónMarcadores.hasNextLine()) {

                String fila = revisiónMarcadores.nextLine();
                if (fila.length() > 4 && fila.substring(0,5).equals("GOTO ") ) {

                    String clave = fila.substring(5, fila.length());
                    if (!clave.equals("input") && !controlGoto.containsKey(clave)) {

                        throw new NumberFormatException("Error: No existe marcador '" + fila.substring(5, fila.length())
                                + "'. [Línea " + líneaActual + "]");
                    }
                }

                líneaActual++;
            }


        } catch (FileNotFoundException e) { //should never happen
            System.out.println("You should never have come here");
            throw new NumberFormatException("???");
        }
    }




    public void Goto(int línea) {
        if (línea > tamaño || línea < 1) {
            throw new NumberFormatException("Error: La línea '" + línea + "' no existe. [Número de filas = " + tamaño + "]");
        }

        try {
            sc = new Scanner(archivo); //Reseteamos el scanner
            for (int i = 0; i < línea; i++) {
                sc.nextLine();
            }

        } catch (FileNotFoundException e) {
            throw new NumberFormatException("Este error nunca debería aparecer :/");
        }
    }


    public void ejecutar() {
        lector();
    }


    /**
     * Lector que leerá archivos .txt. Implementa funcionalidad adicional, como GOTO, pausas, etc.
     * Su propósito es facilitar la creación de lecciones nuevas.
     */
    public void lector() {


        boolean imprimir = true;
        int línea = 0;
        while (sc.hasNextLine()) {
            String fila = sc.nextLine();
            //Comentarios: No se imprimen
            if (fila.length() > 1 && fila.substring(0,2).equals("//")) {
                continue;
            }

            //Inicia comentario largo
            if (fila.length() > 2 && fila.substring(0,3).equals("/**")) {
                if (!imprimir) {throw new NumberFormatException("Error: Iniciaste un comentario, pero una ya estaba activo. Línea " + línea);}
                imprimir = false;
                continue;
            }

            //Termina un comentario largo

            if (fila.length() > 1 && fila.substring(0,2).equals("*/")) {
                if (imprimir) {
                    throw new NumberFormatException("Error: Ningún comentario iniciado. Línea " + línea);
                }
                imprimir = true;
                continue;
            }

            //Pausa
            if (fila.equals("{P}")) {
                String ignórame = usuario.nextLine();
                continue;
            }

            //Finalizar el documento
            if (fila.equals("fin")) {
                return;
            }

            //Control GOTO
            if (fila.length() > 4 && fila.substring(0, 5).equals("GOTO ")) {
                String clave = fila.substring(5, fila.length());

                if (clave.equals("input")) {
                    String lugar;
                    while (true) {
                        System.out.print(">>");
                        lugar = usuario.nextLine();
                        if (controlGoto.containsKey(lugar)) {
                            break;
                        } else {
                            System.out.println("No se encuentra el marcador '" + lugar + "'.");
                        }
                    }
                    Goto(controlGoto.get(lugar));
                } else {
                    Goto(controlGoto.get(clave));
                }

                continue;
            }



            if (imprimir) {
                System.out.println(fila);
            }
            línea++;
        }

    }



    public static void main(String[] args) {
        Lector intento1 = new Lector("./src/Verbos_Modales.txt", true);


    }
}
