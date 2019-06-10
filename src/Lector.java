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


    //Indicador Comando
    public static final String Comando = "\\";

    //-------------------Parámetros solitarios-------------------------
    //Crea una pausa; El programa no continua hasta que se presione 'intr'
    public static final String Pausa = Comando + "P";

    //Finaliza la lectura del texto.
    public static final String Fin = Comando + "Fin";


    //-----------------Misc------------------------
    //Indica que la respuesta del usuario se leerá.
    public static final String Input = "input";

    //String que le dice al usuario que se espera su respuesta
    public static final String PromptUsuario = ">>";

    //---------Parámetros que esperan algo más [Nota el espacio]---------------

    //Lleva al usuario al marcador indicado (Marcador va despues de Lector.Goto)
    public static final String Goto = Comando + "Goto ";

    //Indica ruta a Goto. Igual que 'lbl' en BASIC.
    public static final String Marcador = Comando + "Marcador ";

    //Define la palabra en alemán.
    public static final String Definir = Comando + "Def ";


    //----------------Comentarios------------------------------------------
    public static final String Comentario = "//";
    public static final String ComentarioCom = "/**";
    public static final String ComentarioFin = "*/";




    public Lector(String Archivo, boolean ejecutar) {
        //File archivo = new File("./src/" + Archivo);

        //Verifica que sí exista el archivo / Que se haya formateado el nombre bien:
        archivo = new File(Archivo);
        try {
            sc = new Scanner(archivo);
        } catch (NullPointerException e) {
            throw new NumberFormatException("Error: Se recibió un archivo nulo. ");
        } catch (FileNotFoundException e) {
            throw new NumberFormatException("Error: El archivo '" + Archivo + "' no se encuentra. [Syntaxis ~ ./src/texto.txt]");
        } //Si salimos el Scanner del archivo ya estará funcional.


        //Revisa que los marcadores estén forateados correctamente, y crea un mapa con sus posiciones.
        RevisiónSintaxis();
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
                if (Control.empiezaCon(fila, Marcador)) {
                    //¡Hallamos un marcador!
                    String clave = Control.quitarPrefijo(fila, Marcador);

                    if (clave.equals("")) {
                        throw new NumberFormatException("Error: Marcador sin clave.");
                    } else if (controlGoto.containsKey(clave)) {
                        throw new NumberFormatException("Error: Existen dos marcadores con clave '" + clave + "'. Líneas " + controlGoto.get(clave) + " y " + líneaActual);
                    } else if (clave.endsWith(" ")) {
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
                if (fila.startsWith(Lector.Goto)) {

                    String clave = Control.quitarPrefijo(fila, Lector.Goto);
                    if (!clave.equals(Lector.Input) && !controlGoto.containsKey(clave)) {

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


    public void RevisiónSintaxis() {
        try {
            Scanner sc = new Scanner(archivo);

            int linActual = 0;
            while(sc.hasNextLine()) {
                String fila = sc.nextLine();
                linActual++;

                if (fila.startsWith(Comando)) {

                    if (fila.startsWith(Pausa)) {
                        if (!fila.equals(Pausa)) { //debe de ser exacto
                            throw new NumberFormatException("Error de declaración de comando 'pausa'; línea " + linActual);
                        } else {continue;}
                    } else if (fila.startsWith(Goto)) {
                        continue; //Goto y Marcador se revisan por separado en .revisiónGoto()
                    } else if (fila.startsWith(Marcador)) {
                        continue;
                    } else if (fila.startsWith(Fin)) {
                        if (!fila.equals(Fin)) { //debe que ser exacto
                            throw new NumberFormatException("Error de declaración de comando 'fin'; línea " + linActual);
                        } else {continue;}
                    } else if (fila.startsWith(Definir)) {
                        if (fila.equals(Definir)) { //...debe de haber algo que definir, ¿no?
                            throw new NumberFormatException("Error de declaración de comando 'definir'; línea " + linActual);
                        } else {continue;}
                    }

                    throw new NumberFormatException("Error de declaración: Comando no reconocido en línea " + linActual);


                }
            }
        } catch (FileNotFoundException e) {
            throw new NumberFormatException("HOW COULD THIS HAPPEN TO MEEEEE");
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

            //---------------------Comentarios: No se imprimen--------------------------
            //Comentario básico
            if (Control.empiezaCon(fila, Comentario)) {
                continue;
            }

            //Inicia comentario largo
            if (Control.empiezaCon(fila, ComentarioCom)) {
                if (!imprimir) {throw new NumberFormatException("Error: Iniciaste un comentario, pero una ya estaba activo. Línea " + línea);}
                imprimir = false;
                continue;
            }

            //Termina un comentario largo
            if (Control.empiezaCon(fila, ComentarioFin)) {
                if (imprimir) {
                    throw new NumberFormatException("Error: Ningún comentario iniciado. Línea " + línea);
                }
                imprimir = true;
                continue;
            }

            if (fila.startsWith(Lector.Comando)) {
                //Pausa
                if (fila.equals(Lector.Pausa)) {
                    String ignórame = usuario.nextLine();
                    continue;
                }

                //Finalizar el documento
                if (fila.equals(Lector.Fin)) {
                    return;
                }

                //Control GOTO
                if (Control.empiezaCon(fila, Goto)) {
                    String clave = Control.quitarPrefijo(fila, Goto);

                    if (clave.equals(Lector.Input)) {
                        String lugar;
                        while (true) {
                            System.out.print(Lector.PromptUsuario);
                            lugar = usuario.nextLine();
                            if (controlGoto.containsKey(lugar)) {
                                break;
                            } else {
                                System.out.println("No se encuentra el marcador '" + lugar + "'.");
                            }
                        }
                        Goto(controlGoto.get(lugar));
                    } else {
                        //System.out.println("La clave es '" + clave + "'");
                        Goto(controlGoto.get(clave));
                    }

                    continue;
                }


                //Si llegamos aquí, la fila empieza con Lector.Comando, pero no se reconoció el comando. Asumiremos que es un error de formatéo.
                throw new NumberFormatException("Error de formateo en fila " + línea + "; '" + fila + "'");
            }





            if (imprimir) {
                System.out.println(fila);
                //En un instante podríamos agregar comentarios después de una línea normal, pero podría causar errores durante el formateo de la línea.
            }
            línea++;
        }

    }



    public static void main(String[] args) {
        Lector intento1 = new Lector("./src/Verbos_Modales.txt", true);


    }
}
