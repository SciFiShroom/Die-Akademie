import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;

public class Pal extends Palabra{
    //para todas las palabras chicas que también nos tenemos que aprender.

    //--------------------------<LISTA DE TODOS LOS TEMAS>-----------------------------------------------------
    public static final String nullEntry = "---";

    //1
    public static final String conjunciónes = "conjunciónes";
    public static ArrayList<Pal> Conjunciónes = new ArrayList<Pal>();
    //2
    public static final String interrogativos = "interrogativos";
    public static ArrayList<Pal> Interrogativos = new ArrayList<Pal>();
    //3
    public static final String pronombres = "pronombres";
    public static ArrayList<Pal> Pronombres = new ArrayList<Pal>();

    //4
    public static final String preposiciones = "preposiciones";
    public static ArrayList<Pal> Preposiciones = new ArrayList<Pal>();
    //5
    public static final String acusativo = "acusativo";
    public static ArrayList<Pal> Acusativo = new ArrayList<Pal>();
    //6
    public static final String dativo = "dativo";
    public static ArrayList<Pal> Dativo = new ArrayList<Pal>();
    //7
    public static final String wechsel = "wechsel";
    public static ArrayList<Pal> Wechsel = new ArrayList<Pal>();



    //--------------------------</LISTA DE TODOS LOS TEMAS>-----------------------------------------------------


    /**
     * Genera una lista de listas. [AQUÍ SE GENERA CONTROL.PALABRAS]
     * Aquí se agregan las listas temáticas que se créan arribita.
     * @return Control.Palabras
     */
    public static ArrayList<ArrayList<Pal>> GeneradorPal() {
        // Todas las listas de palabras se guardan en una lista de listas:
        ArrayList<ArrayList<Pal>> Palabras = new ArrayList<ArrayList<Pal>>();

        //1
        Palabras.add(Conjunciónes); Conjunciónes.add(new Pal(conjunciónes)); //<---Este es el sustantivo nulo.
        //2
        Palabras.add(Interrogativos); Interrogativos.add(new Pal(interrogativos)); //Se tendrán que agregar todos los temas manualmente; desconozco de una manera más facil.
        //3
        Palabras.add(Pronombres); Pronombres.add(new Pal(pronombres)); //Nota: Si quieres tener "el vegetal" como sustantivo practicable, se tenra que agregar en el generador.

        //4
        Palabras.add(Preposiciones); Preposiciones.add(new Pal(preposiciones));
        //5
        Palabras.add(Acusativo); Acusativo.add(new Pal(acusativo));
        //6
        Palabras.add(Dativo); Dativo.add(new Pal(dativo));
        //7
        Palabras.add(Wechsel); Wechsel.add(new Pal(wechsel));


        crearPalabras();

        System.out.print("PALABRAS: " + Palabras.size() + " LISTAS INICIALIZADAS");
        return Palabras;
    }


    //Echa error si agregas una tag que no se reconoce.
    public void tagAdd(String newtag) { //agrega el sustantivo a la lista del tema.
        String[] current = new String[this.tags.length + 1];
        for (int i = 0; i < this.tags.length; i++) {
            current[i] = this.tags[i];
        }
        current[this.tags.length] = newtag;
        this.tags = current;

        boolean entendido = false;
        switch (newtag) {//Aquí se agrega a la lista
            //1
            case conjunciónes: Conjunciónes.add(this); entendido = true; break;
            //2
            case interrogativos: Interrogativos.add(this); entendido = true; break;
            //3
            case pronombres: Pronombres.add(this); entendido = true; break;

            //4
            case preposiciones: Preposiciones.add(this); entendido = true; break;
            //4
            case acusativo: Acusativo.add(this); entendido = true; break;
            //4
            case dativo: Dativo.add(this); entendido = true; break;
            //4
            case wechsel: Wechsel.add(this); entendido = true; break;

        }

        if (!entendido) { throw new NullPointerException("ERROR: Tag no reconocida"); }
    }



    //Constructor básico.
    public Pal(String Palabra, String Significado, String[] Tags) {
        this.palabra = Palabra;
        this.significado = Significado;

        this.tags = new String[0];
        for (String current : Tags) {
            this.tagAdd(current);
        }
    }

    //Constructor nulo
    public Pal(String tema) {
        this.palabra = tema;
        this.nulo = true;
    }

    public String palabra; //La palabra en Alemán
    public String significado; //El significado de la palabra.
    public String[] tags; //Los tags (tipo de palabra, u otro tema. )

    public boolean nulo = false;


    /**Créa todas las palabras.
     *
     */
    public static void crearPalabras() {
        String[] Tags;

        //[Conjunciones]
        Tags = new String[]{conjunciónes};
        Pal aber = new Pal("aber", "pero", Tags);
        Pal und = new Pal("und", "y", Tags);
        Pal oder = new Pal("oder", "o", Tags);
        Pal weil = new Pal("weil", "porque", Tags);
        Pal denn = new Pal("denn", "porque", Tags);

        Pal obwohl = new Pal("obwohl", "aunque", Tags);
        Pal sondern = new Pal("sondern", "sino", Tags);

        Pal also = new Pal("also", "así que", Tags);

        Pal ob;
        Pal wenn = new Pal("wenn", "cuando", Tags);

        //[/Conjunciones]

        //[Interrogativos]
        Tags = new String[]{interrogativos};

        Pal was = new Pal("was", "qué", Tags);
        Pal wie = new Pal("wie", "cómo", Tags);
        Pal warum = new Pal("warum", "porqué", Tags);
        Pal wann = new Pal("wann", "cuándo", Tags);

        Pal wo = new Pal("wo", "dónde", Tags);
        Pal woher = new Pal("woher", "de dónde", Tags);
        Pal wohin = new Pal("wohin", "a dónde", Tags);

        Pal wer = new Pal("wer", "quién [Nom.]", Tags);
        Pal wem = new Pal("wem", "a quién [Dat.]", Tags);
        Pal wen = new Pal("wen", "a quién [Ac.]", Tags);
        Pal wessen = new Pal("wessen", "de quién [Gen.]", Tags);

        Pal wie_viel = new Pal("wie viel [M]", "cuanto", Tags);
        Pal wie_viele = new Pal("wie viele [M]", "cuantos", Tags);

        Pal welcher = new Pal("welcher", "cuál [M]", Tags);
        Pal welches = new Pal("welches", "cuál [N]", Tags);
        Pal welche = new Pal("welche [S.]", "cuál [F]", Tags);
        Pal welche2 = new Pal("welche [P.]", "cuáles", Tags);


        //[/Interrogativos]

        //[Pronombres]
        Tags = new String[]{pronombres};
        Pal dieser = new Pal("dieser", "este", Tags);
        Pal dieses = new Pal("dieses", "este [N.]", Tags);
        Pal diese = new Pal("diese [F.]", "esta", Tags);
        Pal diese2 = new Pal("diese [P.]", "estos [P.]", Tags);

        //mein dein ...
        //ein, kein, jeder, ...


        //[/Pronombres]

        //[Pronombres Personales]

        /**
         Tags = new String[]{pronombres_personales, nominativo};
         Pal ich;
         Pal du;
         Pal er;
         Pal sie;
         Pal es;
         Pal wir;
         Pal ihr;
         Pal sie;
         Pal Sie;

         Tags = new String[]{pronombres_personales, acusativo};
         Pal mich;
         Pal dich;
         Pal ihn;
         Pal sie;
         Pal es;
         Pal uns;
         Pal euch;
         Pal sie;
         Pal Sie;

         Tags = new String[]{pronombres_personales, acusativo};
         Pal mir;
         Pal dir;
         Pal ihm;
         Pal ihr;
         Pal ihm;
         Pal uns;
         Pal euch;
         Pal ihnen;
         Pal Ihnen;

         //quiero que estén en las dos porque estos también se declinan, a diferencia de los otros pronombres personales.
         Tags = new String[]{pronombres_personales, pronombres};
         Pal mein;
         Pal dein;
         Pal sein;
         Pal sein;
         Pal ihr;
         Pal unser;
         Pal euer;
         Pal ihr;
         Pal Ihr;

         */

        //[Preposiciones]
        Tags = new String[]{preposiciones, acusativo};

        Pal durch = new Pal("durch [AKK]", "a través de", Tags);
        Pal für = new Pal("für [AKK]", "para", Tags);
        Pal gegen = new Pal("gegen [AKK]", "contra", Tags);
        Pal ohne = new Pal("ohne [AKK]", "sin", Tags);
        Pal um = new Pal("um [AKK]", "a las [hora]", Tags);


        Tags = new String[]{preposiciones, dativo};

        Pal aus = new Pal("aus", "de [locativo]",  Tags);
        Pal außer = new Pal("außer", "fuera de", Tags);
        Pal bei = new Pal("bei", "en [un sitio]", Tags);
        Pal mit = new Pal("mit", "con", Tags);
        Pal nach = new Pal("nach [locativo]", "hacia [geográfico]", Tags);
        Pal nach2 = new Pal("nach [temporal]", "despues de", Tags);
        Pal seit = new Pal("seit", "desde", Tags);
        Pal von = new Pal("von", "de [posesivo]", Tags);
        Pal zu = new Pal("zu", "hacia [no geográfico]", Tags);


        Tags = new String[]{preposiciones, wechsel};
        //Asegúrate de que no se le añada 'de' a nada.
        Pal in = new Pal("in", "dentro", Tags);
        Pal in2 = new Pal("in", "a [un lugar]", Tags);
        Pal an = new Pal("an [temporal]", "en", Tags);
        Pal an2 = new Pal("an [locativo]", "junto [a]", Tags);
        Pal auf = new Pal("auf" ,"sobre [con contacto]", Tags);
        Pal hinter = new Pal("hinter", "detrás", Tags);
        Pal links = new Pal("links", "izquierda [a la]", Tags);
        Pal neben = new Pal("neben", "junto [n]", Tags);
        Pal rechts = new Pal("rechts", "derecha", Tags);
        Pal über = new Pal("über", "sobre [sin contacto]", Tags);
        Pal unter = new Pal("unter", "debajo", Tags);
        Pal vor = new Pal("vor", "enfrente", Tags);
        Pal zwischen = new Pal("zwischen", "entre", Tags);

        //[-Preposiciones]
    }




    /**
     public static String ElejirTemas(Scanner sc) {
     String tema;
     while (true) {
     System.out.println("Favor de elejir un tema: ");
     String intento = sc.nextLine();

     switch (intento) {
     case "listar temas": Pal.ListarTemas(); continue;
     case "cerrar": throw new NullPointerException("Ejercicio cerrado");
     } //Solo hay dos inputs raros que pueden ocurrir aqui.

     try {
     Pal.ListaTema(intento);
     tema = intento; //Le dice al programa que si funcionó. Si no existe, se saldrá arribita.
     } catch (NullPointerException e) {
     System.out.print("El tema '" + intento + "' no se encuentra. Diga 'listar temas' para ver los temas.");
     System.out.println(" Diga 'cerrar' para cerrar el ejercicio. ");
     continue;
     }
     break;
     }
     return tema;
     }
     */



    //Imprime la lista de todos los temas
    public static void ListarTemas() {
        for (ArrayList<Pal> current : Control.Palabras) {
            System.out.print(current.get(0).palabra + ", ");
        }
    }


    /**
     * Busca la lista de palabras el tema elejido de la lista Control.Palabras.
     * Echa NullPointerException si no se encuentra una lista con el tem indicado.
     * @param tema el tema elejido
     * @return la lista en formato ArrayList<Pal>
     */
    public static ArrayList<Pal> ListaTema(String tema) {
        for (ArrayList<Pal> current : Control.Palabras) {
            if (current.get(0).palabra.equals(tema)) {return current;}
        }
        //System.out.println("El tema '" + tema + "' no se encuentra. Diga 'listar temas sustantivos' para ver los temas. ");
        throw new NullPointerException();
    }


    /**
     * Consola que te deja elejir una palabra de la lista Control.Palabras.
     * ECHA NullPointerException si el usuario dice 'cerrar'
     * @param sc el escaneador
     * @return la palabra si se encuentra
     */
    public static Pal ElejirPalabra(Scanner sc) {
        Pal out;
        System.out.println("Favor de elejir una palabra:");
        while (true) {
            String intento = sc.nextLine();
            if (intento.equals("cerrar")) {throw new NullPointerException("Cerrando. ");}
            out = Pal.buscar(intento);
            if (out == null) {
                System.out.println("La palabra '" + intento + "' no se encuentra. Asgúrese que la palabra esté escrito sin declinación.");
                continue;
            }
            break;
        }
        return out;
    }


    /**
     * Te deja buscar un adjetivo de la lista Control.Adjetivos
     * @param nombre el adjetivo que buscas
     * @return el adjetivo si se encuentra, null si no se encuentra.
     */
    public static Pal buscar(String nombre) {
        for (ArrayList<Pal> actual: Control.Palabras) {
            for (Pal temp : actual) {
                if (temp.palabra.equals(nombre)) {return temp;}
            }
        }
        return null;
    }


    //Define una palabra
    public void definir() {
        System.out.println(this.palabra + ": " + this.significado);
        System.out.println(Arrays.toString(this.tags));
    }


    //Imprime una lista de palabras con formateo chido
    public static void imprimirListaChido(ArrayList<Pal> tema) {
        String[][] arr = new String[tema.size()][3];
        //Nota: El primer ringlón es para el display, pero la lista de sustantivos empieza
        //con el sustantivo nulo cuyo no se imprimirá, o sea que se balancéa.
        arr[0][0] = "Palabra";
        arr[0][1] = "Significado";
        arr[0][2] = "Atributos";

        for (int i = 1; i < tema.size(); i++) {
            Pal actual = tema.get(i);
            arr[i][0] = actual.palabra;
            arr[i][1] = actual.significado;
            arr[i][2] = "";
            for (int j = 0; j < actual.tags.length - 1; j++) {
                arr[i][4] += actual.tags[j] + "; ";
            }
            arr[i][4] += actual.tags[actual.tags.length - 1];
        }
        Control.arrPrint(arr);
    }


    /**
     //Consola que te deja elejir una cantidad de ejercicios/preguntas dado un ArrayList<E>
     public static int ElejirCantidad (ArrayList<Pal> listaPalabras, Scanner sc) {
     System.out.println("Hay " + (listaPalabras.size()-1) + " palabras en este tema.");
     int número;
     while (true) {
     System.out.println("¿Cuántas deséa practicar?");
     String intento = sc.nextLine();
     try {
     número = Integer.parseInt(intento); //Procesa input del usuario. Regresa un entero funcional.
     if (número < 1) {
     System.out.println("El número " + número + " es demasiado chico. ");
     continue;
     } else if (número > listaPalabras.size()-1) {
     System.out.println("El número " + número + " es demasiado grande. ");
     continue;
     } // Aquí sabemos que el número si es valido para el ejercicio.
     } catch (NumberFormatException e) { //Oye, eso no es un numero...
     System.out.println("'" + intento + "' no es un número válido. ");
     continue;
     }
     break;
     }
     return número;
     }
     */


    //Escoje aleatoriamente 'número' palabras de una lista de palabras.
    public static ArrayList<Pal> escojerAleatorio(ArrayList<Pal> listaPalabras, int número) {
        ArrayList<Pal> lista = new ArrayList<Pal>(); //Una lista temporanea que se utiliza para generar la String[] preguntas.
        for (int i = 0; i < número; i++) {
            int índice = (int)(listaPalabras.size()*Math.random());
            //System.out.println("Intentando índice " + índice);
            if (índice == listaPalabras.size()) {
                //System.out.println("La indice " + índice + " es demasiado grande");
                i--;
                continue;
            } else if (índice == 0) {
                //System.out.println("La índice " + índice  + " es demasiado chica");
                i--;
                continue;
            }  else if (lista.contains(listaPalabras.get(índice))) {
                //System.out.println("La índice " + índice + " ya se ha usado");
                i--;
                continue;
            } // Esto se asegura de que la índice sea válida. Recuerda que la índice '0' es el sustantivo nulo.
            //También se asegura de que no aya entradas dobles.
            lista.add(listaPalabras.get(índice));
            //System.out.println("Índice " + índice + " añadida");
        }
        return lista;
    }

}
