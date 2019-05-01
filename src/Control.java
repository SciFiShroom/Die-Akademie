import java.util.ArrayList;
import java.util.Scanner;
public class Control {

    //Estos se inicializan en el Main de Control. (Hasta abajo)
    public static ArrayList<Lista<Sus>> Sustantivos; //La lista se guarde como variable estatica.
    public static ArrayList<Lista<Ver>> Verbos;
    public static ArrayList<Lista<Adj>> Adjetivos;
    public static ArrayList<Lista<Pal>> Palabras;

    //Se usan de ves en cuando, no son tan importantes. Contienen una de cada palabra, sin repeticiones.
    public static Lista<Sus> SustantivosListaSingular;
    public static Lista<Ver> VerbosListaSingular;
    public static Lista<Adj> AdjetivosListaSingular;
    public static Lista<Pal> PalabrasListaSingular;

    //inicializa los temas de tödo el programa
    public static void InicializarTemas() {
        String[] Temas = {
                "comida", "fruta", "vegetal", "país", "ciudades",
                "capital", "test", "cuerpo", "letras", "tiempo",
                "clima", "día", "año", "figuras", "ropa",
                "escuela", "tecnología", "casa", "mueble", "médico",
                "ciudad", "medidas", "transporte", "espcias", "cocina",
                "bebidas", "materiales",

                "moverse", "básco", "modal",
                "menos_básico", "miscelaneo",
                "expresarse", "vista", "tienda", "auxiliar",
                "objeto", "mente",

                "color", "aspecto", "personalidad",

                "conjunciones", "interrogativos", "pronombres", "preposiciones", "acusativo",
                "dativo", "wechsel"
        };

        //Nos aseguramos de que no haya entradas dobles.
        for (int i = 0; i < Temas.length; i++) {
            for (int j = i+1; j < Temas.length; j++) {
                if (Temas[i].equals(Temas[j])) {
                    throw new NullPointerException("Error: Existe una Tag duplicada: " + Temas[i]);
                }
            }
        }

        //Se crean las listas
        ArrayList<Lista<Sus>> listaDeSustantivos = new ArrayList<Lista<Sus>>();
        ArrayList<Lista<Ver>> listaDeVerbos = new ArrayList<Lista<Ver>>();
        ArrayList<Lista<Adj>> listaDeAdjetivos = new ArrayList<Lista<Adj>>();
        ArrayList<Lista<Pal>> listaDePals = new ArrayList<Lista<Pal>>();

        //Llenamos las listas generadas antemente
        for (String temaActual : Temas) {
            listaDeSustantivos.add(new Lista<Sus>(temaActual));
            listaDeVerbos.add(new Lista<Ver>(temaActual));
            listaDeAdjetivos.add(new Lista<Adj>(temaActual));
            listaDePals.add(new Lista<Pal>(temaActual));
        }

        //Las listas dentro de los ArrayLists aún están vasías, pero se llenarán despues en los otros métodos inicializadores.
        Control.Sustantivos = listaDeSustantivos;
        Control.Verbos = listaDeVerbos;
        Control.Adjetivos = listaDeAdjetivos;
        Control.Palabras = listaDePals;
    }


    // Ejecuta la inicialización de los sustantivos. Créa la lista de temas, cuyas tienen los sustantivos.
    public static void InicializarSustantivos() {
        Control.Sustantivos = Sus.GeneradorSus();

        Lista<Sus> listaCompleta = new Lista<Sus>("");
        ArrayList<String> listaIdentificación = new ArrayList<String>();
        for (Lista<Sus> tema : Control.Sustantivos) {
            for (Sus actual : tema) {
                if (!listaIdentificación.contains(actual.toString())) {
                    listaCompleta.add(actual);
                    listaIdentificación.add(actual.toString());
                }
            }
        }//Aqui, listaCompleta tiene todos los sustantivos sin repeticiones

        Control.SustantivosListaSingular = listaCompleta;

        System.out.println("; " + Control.SustantivosListaSingular.size() + " SUSTANTIVOS CREADOS");
    }

    //Ejecuta la inicialización de los verbos, de la misma manera que la de los sustantivos.
    public static void InicializarVerbos() {
        Control.Verbos = Ver.GeneradorVer();

        Lista<Ver> listaCompleta = new Lista<Ver>("");
        ArrayList<String> listaIdentificación = new ArrayList<String>();
        for (Lista<Ver> tema : Control.Verbos) {
            for (int i = 1; i < tema.size(); i++) {
                Ver actual = tema.get(i);
                if (!listaIdentificación.contains(actual.toString())) {
                    listaCompleta.add(actual);
                    listaIdentificación.add(actual.toString());
                }
            }
        }//Aqui, listaCompleta tiene todos los verbos sin repeticiones

        Control.VerbosListaSingular = listaCompleta;

        System.out.println("; " + Control.VerbosListaSingular.size() + " VERBOS CREADOS");
    }

    //Ejecuta la inicialización de los adjetivos.
    public static void InicializarAdjetivos() {
        Control.Adjetivos = Adj.GeneradorAdj();

        Lista<Adj> listaCompleta = new Lista<Adj>("");
        ArrayList<String> listaIdentificación = new ArrayList<String>();
        for (Lista<Adj> tema : Control.Adjetivos) {
            for (Adj actual : tema) {
                if (!listaIdentificación.contains(actual.toString())) {
                    listaCompleta.add(actual);
                    listaIdentificación.add(actual.toString());
                }
            }
        }//Aqui, listaCompleta tiene todos los adjetivos sin repeticiones

        Control.AdjetivosListaSingular = listaCompleta;

        System.out.println("; " + Control.AdjetivosListaSingular.size() + " ADJETIVOS CREADOS");
    }

    //Ejecuta la inicialización de las palabras.
    public static void InicializarPalabras() {
        Control.Palabras = Pal.GeneradorPal();

        Lista<Pal> listaCompleta = new Lista<Pal>("");
        ArrayList<String> listaIdentificación = new ArrayList<String>();
        for (Lista<Pal> tema : Control.Palabras) {
            for (Pal actual : tema) {
                if (!listaIdentificación.contains(actual.toString())) {
                    listaCompleta.add(actual);
                    listaIdentificación.add(actual.toString());
                }
            }
        }//Aqui, listaCompleta tiene todas las palabras sin repeticiones

        Control.PalabrasListaSingular = listaCompleta;

        System.out.println("; " + Control.PalabrasListaSingular.size() + " PALABRAS CREADAS");
    }


    public static void clrScreen() {
        int i = 0;
        while (i < 10) {
            System.out.println();
            i++;
        }
    } //Borra la pantalla


    //Returns a scrambled version of input string[]
    public static String[] scramble(String[] input) {
        int num = input.length;
        String[] out = new String[num];
        for (int i = 0; i < num; i++) {out[i] = input[i];}

        for (int i = 0; i < num; i++) {
            int j = (int)(num*Math.random()); //índice aleatoria
            if (j == num) {i--; continue; }

            String temp = out[i];
            out[i] = out[j];
            out[j] = temp;
        }
        return out;
    }


    public static boolean contiene(String[] lista, String prueba) {
        boolean out = false;
        for (String current : lista) {
            if (current.equals(prueba)) {
                out = true;
                break;
            }
        }
        return out;
    } //Checa si una lista contiene una palabra



    //Fancy print for string arrays
    public static void arrPrint(String[][] arr){
        int[] length = new int[arr[0].length];

        //for (int i : length) {i = 0;}
        //Dudo que esto sea necesario.

        //First, max length for this column is ?
        for(int i = 0; i < arr.length; i++) { //i is height
            for (int j = 0; j < arr[0].length; j++) { //j is width
                if (length[j] < arr[i][j].length()) {length[j] = arr[i][j].length();}
            }
        }

        //now we pront
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j]);
                for (int k = 0; k < (length[j]-arr[i][j].length()); k++) {
                    System.out.print(" ");
                }
                System.out.print(" ");//Un espacio ménimo entre columnas
            }
            System.out.println();
        }
        //we good
    }


    //Escoje "num" valores aleatorios de un ArrayList, sin repeticiones, y los revuelve. Regresa una lista nueva.
    //La lista que regresa es similar a un ArayList<Object>. Pero, con casting, tödo debería funcionar bien.
    public static ArrayList<Object> escojerAleatorio(ArrayList input, int num) {
        ArrayList<Object> lista = new ArrayList<Object>(); //Una lista temporanea
        for (int i = 0; i < num; i++) {
            int índice = (int)(input.size()*Math.random());

            if (índice == input.size() || índice == 0 || lista.contains(input.get(índice))) {
                i--;
                continue;
            } // Esto se asegura de que la índice sea válida. Recuerda que la índice '0' es el sustantivo nulo.
            //También se asegura de que no aya entradas dobles.
            lista.add(input.get(índice));
        }
        return lista;
    }


    public static void comandos() {
        System.out.println("-comandos: Imprime esta lista. ");
        System.out.println("-hola: Dice ¡Hola!");
        System.out.println("-cerrar: Cierra la aplicación. ");
        System.out.println("-practicar sustantivos: Arranca los ejercicios de los sustantivos");
        System.out.println("-practicar verbos: Arranca los ejercicios de los verbos");
        System.out.println("-practicar adjetivos: Arranca los ejercicios de los adjetivos");
        System.out.println("-practicar palabras misceláneas: Arranca los ejercicios de palabras miscelánes (interrogativos preposiciones, etc.)");
        System.out.println();

        //System.out.println();

    } //imprime la lista de comandos


    //Revisan repeticiones en los significados de las palabras respectivas.
    public static void revisarVer(){
        ArrayList<String> listaSignificados = new ArrayList<String>();
        for (Ver actual : Control.VerbosListaSingular) {
            if (listaSignificados.contains(actual.significado)) {
                System.out.println("El significado " + actual.significado + " es repetido. " + actual.verbo);
            } else {
                listaSignificados.add(actual.significado);
            }

            if (actual.presente == null) {System.out.println("El verbo '" + actual.verbo+ "' no tiene presente. ");}
            if (actual.participio == null) {System.out.println("El verbo '" + actual.verbo + "' no tiene participio. ");}
            if (actual.imperativo == null) {System.out.println("El verbo '" + actual.verbo + "' no tiene imperativo. ");}
            if (actual.preterito == null) {System.out.println("El verbo '" + actual.verbo + "' no tiene preterito. ");}
        }
    }
    public static void revisarAdj(){

        ArrayList<String> listaSignificados = new ArrayList<String>();
        for (Adj actual : Control.AdjetivosListaSingular) {
            if (listaSignificados.contains(actual.significado)) {
                System.out.println("El significado " + actual.significado + " es repetido: " + actual.adjetivo);
            } else {
                listaSignificados.add(actual.significado);
            }
        }
    }
    public static void revisarSus(){
        ArrayList<String> listaSignificados = new ArrayList<String>();
        for (Sus actual : Control.SustantivosListaSingular) {
            if (listaSignificados.contains(actual.significado)) {
                System.out.println("El significado " + actual.significado + " es repetido: " + actual.sustantivo);
            } else {
                listaSignificados.add(actual.significado);
            }
        }
    }
    public static void revisarPal(){
        ArrayList<String> listaSignificados = new ArrayList<String>();
        for (Pal actual : Control.PalabrasListaSingular) {
            if (listaSignificados.contains(actual.significado)) {
                System.out.println("El significado " + actual.significado + " es repetido: " + actual.palabra);
            } else {
                listaSignificados.add(actual.significado);
            }
        }
    }


    public static void consola() {
        Scanner sc = new Scanner(System.in); //Este es es scanner que leerá lo que escribe el usuario.
        Control.clrScreen();
        boolean activo = true;
        System.out.println("¡Bienvenido a la academia!");

        while (activo) {
            boolean entendido = false;

            System.out.println("¿Qué quiere hacer?");
            String comando = sc.nextLine();
            System.out.println();
            switch(comando) {
                case "hola":
                    System.out.println("¡Hola!");
                    entendido = true;
                    break;
                case "cerrar":
                    System.out.println("Cerrando aplicación.");
                    activo = false;
                    entendido = true;
                    break;
                case "comandos":
                    Control.comandos();
                    entendido = true;
                    break;
                //case "listar temas sustantivos": Sus.ListarTemas(); break;
                case "practicar sustantivos":
                    Ejer.PracticarSustantivos(sc);
                    entendido = true;
                    break;
                case "practicar verbos":
                    Ejer.PracticarVerbos(sc);
                    entendido = true;
                    break;
                case "practicar adjetivos":
                    Ejer.PracticarAdjetivos(sc);
                    entendido = true;
                    break;
                case "practicar palabras":
                    Ejer.PracticarPalabras(sc);
                    entendido = true;
                    break;
            }
            if(!entendido) {
                System.out.println("Comando '" + comando + "' no reconocido. Diga 'comandos' para la lista de comandos");
            }
        }
    }


    public static void Inicialización() {
        InicializarTemas();
        InicializarSustantivos();
        InicializarVerbos();
        InicializarAdjetivos();
        InicializarPalabras();
    }


    public static void main(String[] args) {
        Inicialización();
        //todo: Cambios se hicieron a los métodos listartemas y elejir temas. Ahora son parte de la clase Ejer.
        //todo: Si se agrega un tipo de palabra nuevo (Lo cual lo dudo, por lo menos por ahora) recuerda actualizar esos métodos,
        //todo: ya que si no lo haces el programa no reconocerá el tipo de palabra.





        //todo: Revisar revisar verbos: Los significados tienen ")"?

        //Ver.OrganizacióndeTags();

        //todo: Estandarizar los tags usados, para poder hacer ejercicios con TODAS las palabras que tengan los mismos tags, independientemente del tipo de palabra.

        //todo: Agregar método para revisar que los sustantivos emíezen con mayuscula y que las demás palabras no.

        //todo: Agregar parser que lea la funcion crear[cosa]() y la ponga en un documento .txt
        //todo: agregar algo que lea un documento .txt y crée las palabras a partir de ello.

        //todo: Lehrer, Student, Professor,...???


        //todo: Quitar bahnhof

        //todo: Agregar palabras indicativas (Fruta, ciudad, especias, escuela, ...) a todas las listas.
        //todo: Organizar "Marcadores" en todos los diccionarios.
        //todo: Acabar de ordenar los verbos :pepeEyes:

        Control.revisarVer();
        Control.revisarAdj();
        Control.revisarSus();
        Control.revisarPal(); //todo: modularizar esto; agregarlo como opción buleana a inicializarX();

        //todo: Si a una palabra se le añade un tag que ya tine, el programa debería de echar un error.

        //Definir palabra debería de saber que hacer si 'nullEntry' aparece
        //asegurarse de que palabras con 'nullEntry' no entren a los ejercicios que lo necesitan.

        //todo: enseñar tema debería continuar con otros temas despues de terminar.
        //todo: BUG: ENSEÑAR TEMA NO UTILIZA TODAS LAS PALABRAS (SE SALTA UNA)
        //      ENSEÑAR TEMA OBJETO ENSEÑA 16 VERBOS PERO TENEMOS 17 (SE SALTÓ LIEGEN)

        //todo: enthülle
        //todo: gritar, susurrar,...
        //todo: Conjunciones

        //todo: Agregar imperativo y conjugacion preterito a definicion verbo
        //todo: Investigar si es posible imprimir texto con color. Ayudaría con el control y en la enseñanza.
        //todo: Organizar todos.
        //todo: Checar nennen y ernennen; lassen

        //todo: Habrá alguna manera de modularizar la definición de los temas???
        Control.consola();
//todo: werfen y ziehen???
        //todo: agregar preterito a los verbos. Crear algún método para ver cuales verbos no tienen preterito / participio / etc.
        /*
        aber: pero      A, pero B
        sondern: sino   no A, sino B
        oder: o         A o B
        denn: porque    A porque B
        weil: porque    A porque B
        und: y          A y B
        wenn: si [en caso de que]        Si A, entonces B
        ob: si [No es wenn]         Si A.


         */

        //todo: BUG: en la consola de elejir cantidad, no hay manera de salirse con cerrar...
        //todo: Arreglar silbrig y tras palabras confuzas

        //todo: Asegurarse que el comando ElejurCantidad no se rompa si la lista solo tiene la palabra nula y nada mas.


        //todo: imprimir lista tema crashéa cuando se cierra manualmente on el comando 'cerrar'. También debería darte la opción de listar todos los temas.
        //todo: Definir debería de incluir la frase "Temas" cuando lista los temas.
        //todo: Agregar las letras


        //todo: Tabmién hay que averiguar como funcionarán las conjugaciones. Créo que deberíamos de tener funciones que te den la conjugacion correcta de un verbo, a diferencia de lo que hemos estado haciendo (ej. Ver.significado... deberia ser Ver.conjugar(xxx) = String conjugado. )

    }
}
