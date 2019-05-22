import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;
public class Control {
    public static final String entradaNula = "---";
    public static final String quePex = "?????";

    //NumberFormatExceptio = hice algo mal y el yo del pasado me lo está diciendo
    //SecurityException = Error, pero uno que estoy esperando (catched). También lo uso para cerrar ventanas / consolas.
    //todo: Fix exceptions lul


    public static final String[] Temas = new String[]{
            "comida", "fruta", "verdura", "país", //"ciudades",
            "capital", /**"test",*/ "cuerpo", /**"letras",*/ "tiempo",
            "clima", "día", "año", "figuras", "ropa",
            "escuela", "tecnología", "casa", "mueble", "médico",
            "ciudad", "medidas", "transporte", "especias", "cocina",
            "bebida", "materiales", "trabajo", "platillo", "arte",
            "geografía", "amigos", "festivos", "gramática", "conocidos",
            "planta", "animal", "profesiónes",

            "moverse", "básico", "modal",
            "menos_básico", "misceláneo",
            "comunicación", "vista", "tienda", "auxiliar",
            "objeto", "mente", "escritura",

            "color", "aspecto", "personalidad",

            "conjunciones", "interrogativos", "pronombres", "preposiciones", "acusativo",
            "dativo", "wechsel"
    };

    //Estos se inicializan en el Main de Control. (Hasta abajo)
    public static ArrayList<Lista<Palabra>> Sustantivos; //La lista se guarde como variable estatica.
    public static ArrayList<Lista<Palabra>> Verbos;
    public static ArrayList<Lista<Palabra>> Adjetivos;
    public static ArrayList<Lista<Palabra>> Palabras;

    //Se usan de ves en cuando, no son tan importantes. Contienen una de cada palabra, sin repeticiones.
    public static Lista<Palabra> SustantivosListaSingular = new Lista<Palabra>("hallo!");
    public static Lista<Palabra> VerbosListaSingular = new Lista<Palabra>("hallo!");
    public static Lista<Palabra> AdjetivosListaSingular = new Lista<Palabra>("hallo!");
    public static Lista<Palabra> PalabrasListaSingular = new Lista<Palabra>("hallo!");

    /**
     * regresa la lista del tema especificado, dado el tipo de palabra.
     * La lista es unas de las que es encuentran en Control.Sustantivos, Control.Verbos, etc.
     * @param tema El dema de la lista que buscas
     * @param tipoDePalabra El tipo de Palabra (Sus.Sus, ...)
     * @return La lista, si existe. Echa error si no se encuentra.
     * @throws NumberFormatException si no se encuentra el tema.
     */
    public static Lista<Palabra> getTema(String tema, String tipoDePalabra) {
        Palabra.sanitize(tipoDePalabra);


        ArrayList<Lista<Palabra>> ListaDeTemas = new ArrayList<Lista<Palabra>>();
        switch (tipoDePalabra) {
            case Sus.Sus: ListaDeTemas = Control.Sustantivos; break;
            case Ver.Ver: ListaDeTemas = Control.Verbos; break;
            case Adj.Adj: ListaDeTemas = Control.Adjetivos; break;
            case Pal.Pal: ListaDeTemas = Control.Palabras; break;
        }


        for (Lista<Palabra> actual : ListaDeTemas) {
            if (actual.getNombre().equals(tema)) {return actual;}
        } //Si nos salimos de aquí, buscan algo que no existe.

        throw new NumberFormatException("Error: El tipo de palabra " + tipoDePalabra + " no tiene el tema " + tema);
    }


    public static boolean TemaExiste(String tema, String tipoDePalabra) {
        Palabra.sanitize(tipoDePalabra);


        switch (tipoDePalabra) {
            case Sus.Sus:
                for (Lista actual : Control.Sustantivos) {
                    if (actual.getNombre().equals(tema)) { return true; }
                }
                break;

            case Ver.Ver:
                for (Lista actual : Control.Verbos) {
                    if (actual.getNombre().equals(tema)) { return true; }
                }
                break;

            case Adj.Adj:
                for (Lista actual : Control.Adjetivos) {
                    if (actual.getNombre().equals(tema)) { return true; }
                }
                break;

            case Pal.Pal:
                for (Lista actual : Control.Palabras) {
                    if (actual.getNombre().equals(tema)) { return true; }
                }
                break;
        }

        return false;
    }


    //inicializa los temas de tödo el programa
    public static int InicializarTemas() {

        int num = Temas.length;

        Arrays.sort(Temas); //opcional pero mejor para el usuario
        //System.out.println("TEMAS: " + Arrays.toString(Temas));


        //Nos aseguramos de que no haya entradas dobles.
        for (int i = 0; i < Temas.length; i++) {
            for (int j = i+1; j < Temas.length; j++) {
                if (Temas[i].equals(Temas[j])) {
                    throw new NullPointerException("Error: Existe una Tag duplicada: " + Temas[i]);
                }
            }
        }

        //Se crean las listas
        ArrayList<Lista<Palabra>> listaDeSustantivos = new ArrayList<Lista<Palabra>>();
        ArrayList<Lista<Palabra>> listaDeVerbos = new ArrayList<Lista<Palabra>>();
        ArrayList<Lista<Palabra>> listaDeAdjetivos = new ArrayList<Lista<Palabra>>();
        ArrayList<Lista<Palabra>> listaDePals = new ArrayList<Lista<Palabra>>();

        //Llenamos las listas generadas antemente
        for (String temaActual : Temas) {
            listaDeSustantivos.add(new Lista<Palabra>(temaActual));
            listaDeVerbos.add(new Lista<Palabra>(temaActual));
            listaDeAdjetivos.add(new Lista<Palabra>(temaActual));
            listaDePals.add(new Lista<Palabra>(temaActual));
        }

        //Las listas dentro de los ArrayLists aún están vasías, pero se llenarán despues en los otros métodos inicializadores.
        Control.Sustantivos = listaDeSustantivos;
        Control.Verbos = listaDeVerbos;
        Control.Adjetivos = listaDeAdjetivos;
        Control.Palabras = listaDePals;

        return num;
    }



    public static void OrganizaciónTemas(int n) {
        int alt;
        if (Control.Temas.length % n == 0) {
            alt = Control.Temas.length / n;
        } else {
            alt = 1+(Control.Temas.length / n);
        }
        String[][] out = new String[alt][n];

        int counter = 0;
        for (int i = 0; i < alt; i++) {
            for (int j = 0; j < n; j++) {
                if (counter < Control.Temas.length) {
                    int numSus = -1;
                    int numVer = -1;
                    int numAdj = -1;
                    int numPal = -1;

                    try { numSus = Control.getTema(Temas[counter], Sus.Sus).size();
                    } catch (NumberFormatException e) { numSus = 0; }

                    try { numVer = Control.getTema(Temas[counter], Ver.Ver).size();
                    } catch (NumberFormatException e) { numVer = 0; }

                    try { numAdj = Control.getTema(Temas[counter], Adj.Adj).size();
                    } catch (NumberFormatException e) { numAdj = 0; }

                    try { numPal = Control.getTema(Temas[counter], Pal.Pal).size();
                    } catch (NumberFormatException e) { numPal = 0; }

                    int numTotal = numAdj + numPal + numSus + numVer;

                    out[i][j] = Control.Temas[counter] + ": " + numSus + "S " + numVer + "V " + numAdj + "A " + numPal + "P = " + numTotal;
                    counter++;
                } else {
                    out[i][j] = " ";
                }
            }
        }

        Control.arrPrint(out);

    }




    // Ejecuta la inicialización de los sustantivos. Créa la lista de temas, cuyas tienen los sustantivos.
    public static void InicializarSustantivos() {
        //long comienzo = System.nanoTime();

        Sus.crearSustantivos(); //esto crea todos los sustantivos, y se meten automáticamente a la lista Control.Sustantivos y Control.SustantivosListaSingular.

        //Hay que quitar las listas de los temas vacíos.
        ArrayList<Lista<Palabra>> ListasNoVacías = new ArrayList<Lista<Palabra>>();
        for (Lista<Palabra> actual : Control.Sustantivos) {
            if (actual.size() > 0) {ListasNoVacías.add(actual); }
        }
        Control.Sustantivos = ListasNoVacías;


        //long fin = System.nanoTime();
        //double duración = ((double)fin - comienzo)/1000000;

        System.out.println("SUSTANTIVOS: " + Control.SustantivosListaSingular.size() + " sustantivos creados, " + Control.Sustantivos.size() + " temas utilizados. ");
        //System.out.println("")
    }
    public static void InicializarVerbos() {
        Ver.crearVerbos(); //Crea y organiza todos los verbos en Control.Verbos Y Control.VerbosListaSingular

        //Hay que quitar las listas de los temas vacíos.
        ArrayList<Lista<Palabra>> ListasNoVacías = new ArrayList<Lista<Palabra>>();
        for (Lista<Palabra> actual : Control.Verbos) {
            if (actual.size() > 0) {ListasNoVacías.add(actual); }
        }
        Control.Verbos = ListasNoVacías;

        System.out.println("VERBOS: " + Control.VerbosListaSingular.size() + " verbos creados, " + Control.Verbos.size() + " temas utilizados");
    }
    public static void InicializarAdjetivos() {
        Adj.crearAdjetivos();

        //Hay que quitar las listas de los temas vacíos.
        ArrayList<Lista<Palabra>> ListasNoVacías = new ArrayList<Lista<Palabra>>();
        for (Lista<Palabra> actual : Control.Adjetivos) {
            if (actual.size() > 0) {ListasNoVacías.add(actual); }
        }
        Control.Adjetivos = ListasNoVacías;

        System.out.println("ADJETIVOS: " + Control.AdjetivosListaSingular.size() + " adjetivos creados, " + Control.Adjetivos.size() + " temas utilizados");
    }
    public static void InicializarPalabras() {
        Pal.crearPalabras();

        //Hay que quitar las listas de los temas vacíos.
        ArrayList<Lista<Palabra>> ListasNoVacías = new ArrayList<Lista<Palabra>>();
        for (Lista<Palabra> actual : Control.Palabras) {
            if (actual.size() > 0) {ListasNoVacías.add(actual); }
        }
        Control.Palabras = ListasNoVacías;

        System.out.println("PALABRAS: " + Control.PalabrasListaSingular.size() + " palabras creadas, " + Control.Palabras.size() + " temas utilizados");
    }

    public static void InicializarTodo() {
        Sus.crearSustantivos();
        Ver.crearVerbos();
        Adj.crearAdjetivos();
        Pal.crearPalabras();


        ArrayList<Lista<Palabra>> ListasNoVacías = new ArrayList<Lista<Palabra>>();
        for (Lista<Palabra> actual : Control.Sustantivos) { if (actual.size() > 0) {ListasNoVacías.add(actual); }}
        Control.Sustantivos = ListasNoVacías;
        System.out.println("SUSTANTIVOS: " + Control.SustantivosListaSingular.size() + " sustantivos creados, " + Control.Sustantivos.size() + " temas utilizados. ");

        ListasNoVacías = new ArrayList<Lista<Palabra>>();
        for (Lista<Palabra> actual : Control.Verbos) { if (actual.size() > 0) {ListasNoVacías.add(actual); }}
        Control.Verbos = ListasNoVacías;
        System.out.println("VERBOS: " + Control.VerbosListaSingular.size() + " verbos creados, " + Control.Verbos.size() + " temas utilizados");

        ListasNoVacías = new ArrayList<Lista<Palabra>>();
        for (Lista<Palabra> actual : Control.Adjetivos) { if (actual.size() > 0) {ListasNoVacías.add(actual); }}
        Control.Adjetivos = ListasNoVacías;
        System.out.println("ADJETIVOS: " + Control.AdjetivosListaSingular.size() + " adjetivos creados, " + Control.Adjetivos.size() + " temas utilizados");

        ListasNoVacías = new ArrayList<Lista<Palabra>>();
        for (Lista<Palabra> actual : Control.Palabras) { if (actual.size() > 0) {ListasNoVacías.add(actual); }}
        Control.Palabras= ListasNoVacías;
        System.out.println("PALABRAS: " + Control.PalabrasListaSingular.size() + " palabras creadas, " + Control.Palabras.size() + " temas utilizados");


        //revisión de tags no usados

        for (String tema : Control.Temas) {
            if (TemaExiste(tema, Sus.Sus) || TemaExiste(tema, Ver.Ver) || TemaExiste(tema, Adj.Adj) || TemaExiste(tema, Pal.Pal)) {
                continue;
            }

            throw new NumberFormatException("Error: El tema '" + tema + "' no es utilizado");
        }

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
        for (Palabra actual : Control.VerbosListaSingular) {
            //Revisión de significados:
            if (listaSignificados.contains(actual.getSignificado())) {
                System.out.println("El significado " + actual.getSignificado() + " es repetido. " + actual.getNombre());
            } else {
                listaSignificados.add(actual.getSignificado());
            }

            if (actual instanceof Ver) {
                //Varios verbos no tendrán tödo. Aquí se veran todos los casos.
                Ver verActual = (Ver) actual;
                if (verActual.presente == null) {System.out.println("El verbo '" + verActual.verbo+ "' no tiene presente. ");}
                if (verActual.participio == null) {System.out.println("El verbo '" + verActual.verbo + "' no tiene participio. ");}
                if (verActual.imperativo == null) {System.out.println("El verbo '" + verActual.verbo + "' no tiene imperativo. ");}
                if (verActual.preterito == null) {System.out.println("El verbo '" + verActual.verbo + "' no tiene preterito. ");}
            }

            //Ningún verbo debería de tener parámetros escritos con mayúsculas
            //pero no creo que esto importe tanto.
        }
    }
    public static void revisarAdj(){

        ArrayList<String> listaSignificados = new ArrayList<String>();
        for (Palabra actual : Control.AdjetivosListaSingular) {
            if (listaSignificados.contains(actual.getSignificado())) {
                System.out.println("El significado " + actual.getSignificado() + " es repetido: " + actual.getNombre());
            } else {
                listaSignificados.add(actual.getSignificado());
            }
        }
    }
    public static void revisarSus(){
        ArrayList<String> listaSignificados = new ArrayList<String>();
        for (Palabra actual : Control.SustantivosListaSingular) {
            Sus susActual = actual.aSus();

            //Revisión de parámetros: A ningún sustantivo le debería de faltar algo.
            if (susActual.plural == null) {System.out.println("El sustantivo '" + susActual.getNombre() + "' no tien plural");}


            //Revisión de significados: No debería haber dobles
            if (listaSignificados.contains(actual.getSignificado())) {
                System.out.println("El significado " + actual.getSignificado() + " es repetido: " + actual.getNombre());
            } else {
                listaSignificados.add(actual.getSignificado());
            }

            //Revisión de capitalización: Todos los sustantivos se capitalizan.
            if (!Character.isUpperCase(actual.getNombre().charAt(0))) {
                System.out.println("El sustantivo " + actual.getNombre() + " no está capitalizado. ");
            }

            //También revisamos la capitalización de los plurales.

            if (!Character.isUpperCase(susActual.plural.charAt(0))) {
                System.out.println("El sustantivo '" + susActual.getNombre() + "' tiene un plural sin capitalizar");
            }

        }
    }
    public static void revisarPal(){
        ArrayList<String> listaSignificados = new ArrayList<String>();
        for (Palabra actual : Control.PalabrasListaSingular) {
            if (listaSignificados.contains(actual.getSignificado())) {
                System.out.println("El significado " + actual.getSignificado() + " es repetido: " + actual.getNombre());
            } else {
                listaSignificados.add(actual.getSignificado());
            }
        }
    }
    public static void revisiónCompleta() {
        int numeroDeErrores = 0;

        System.out.println("----------------Revisión de palabras-------------------");
        ArrayList<String> listaSignificados = new ArrayList<String>();
        ArrayList<String> listaNombres = new ArrayList<String>();

        Lista<Palabra> todasLasPalabras = Lista.concatenar(SustantivosListaSingular, Lista.concatenar(VerbosListaSingular, Lista.concatenar(AdjetivosListaSingular, PalabrasListaSingular)));

        for (Palabra actual : todasLasPalabras) {
            if (actual.getNombreSimple().equals("")) {
                System.out.println("ERROR: Palabra sin nombre. Tags: " + Arrays.toString(actual.tags));
                numeroDeErrores++;
                continue;
            }
            if(actual.getSignificadoSimple().equals("")) {
                System.out.println("ERROR: Palabra sin significado. " + actual.getNombre());
                numeroDeErrores++;
                continue;
            }



            if (listaNombres.contains(actual.getNombre())) {
                System.out.println("El nombre '" + actual.getNombre() + "' es repetido. " + actual.getNombre()); numeroDeErrores++;
            } else {
                listaNombres.add(actual.getSignificado());
            }

            if (listaSignificados.contains(actual.getSignificado())) {
                System.out.println("El significado '" + actual.getSignificado() + "' es repetido. " + actual.getNombre()); numeroDeErrores++;
            } else {
                listaSignificados.add(actual.getSignificado());
            }


            String[] analysisNombre = actual.getNombreSimple().split("");
            String[] analysisSignificado = actual.getSignificadoSimple().split("");

            for (String sim : analysisNombre) {
                if (sim.equals(" ") || Control.carácterEsRaro(sim.charAt(0), true)) {
                    System.out.println("La palabra '" + actual.getNombre() + "' tiene carácteres raros. ");
                    numeroDeErrores++;
                }
            }

            for (String sim : analysisSignificado) {
                if (Control.carácterEsRaro(sim.charAt(0), false)) {
                    System.out.println("La palabra '" + actual.getNombre() + "' tiene carácteres raros en el significado."); numeroDeErrores++;
                }
            }



            if (actual instanceof Ver) {

                //Varios verbos no tendrán tödo. Aquí se veran todos los casos.
                Ver verActual = (Ver) actual;
                if (verActual.presente == null) {
                    System.out.println("El verbo '" + verActual.verbo+ "' no tiene presente. ");
                    numeroDeErrores++;
                }
                if (verActual.participio == null) {
                    System.out.println("El verbo '" + verActual.verbo + "' no tiene participio. ");
                    numeroDeErrores++;
                }
                if (verActual.imperativo == null) {
                    System.out.println("El verbo '" + verActual.verbo + "' no tiene imperativo. ");
                    numeroDeErrores++;
                }
                if (verActual.preterito == null) {
                    System.out.println("El verbo '" + verActual.verbo + "' no tiene preterito. ");
                    numeroDeErrores++;
                }

            } else if (actual instanceof Sus) {

                Sus susActual = (Sus) actual;
                if (susActual.plural == null) {
                    System.out.println("El sustantivo '" + susActual.getNombre() + "' no tien plural");
                    numeroDeErrores++;
                }

                //Revisión de capitalización: Todos los sustantivos se capitalizan.
                if (!Character.isUpperCase(susActual.getNombre().charAt(0))) {
                    System.out.println("El sustantivo '" + actual.getNombre() + "' no está capitalizado. ");
                    numeroDeErrores++;
                }

                //También revisamos la capitalización de los plurales.
                if (!susActual.plural.equals(entradaNula) && !Character.isUpperCase(susActual.plural.charAt(0))) {
                    System.out.println("El sustantivo '" + susActual.getNombre() + "' tiene un plural sin capitalizar");
                    numeroDeErrores++;
                }

            } else if (actual instanceof Adj) {

                Adj adjActual = (Adj) actual;
                if (adjActual.comparativo == null) {
                    System.out.println("El adjetivo '" + adjActual.getNombre() + "' no tiene comparativo");
                    numeroDeErrores++;
                }
                if (adjActual.superlativo == null) {
                    System.out.println("El adjetivo '" + adjActual.getNombre() + "' no tiene superlativo");
                    numeroDeErrores++;
                }

            } else if (actual instanceof Pal) {

                //no hay nada aquí
            }
        }

        System.out.println("-------------------Revisión de palabras completada exitosamente: " + numeroDeErrores + " errores detectados-----------------------------");
    }



    public static boolean carácterEsRaro(char c, boolean checarAcentos) {
        //àèìòù
        //ÀÈÌÒÙ
        //âêîôû
        //ÂÊÎÔÛ
        switch (c) {
            case '(':
            case ')':
            case '?':
            case '¿':
            case '.':
            case ',':
            case 'à':
            case 'è':
            case 'ì':
            case 'ò':
            case 'ù':
            case 'À':
            case 'È':
            case 'Ì':
            case 'Ò':
            case 'Ù':
            case 'â':
            case 'ê':
            case 'î':
            case 'ô':
            case 'û':
            case 'Â':
            case 'Ê':
            case 'Î':
            case 'Ô':
            case 'Û': return true;
        }

        if (checarAcentos) {
            switch (c) {
                case 'á':
                case 'Á':
                case 'é':
                case 'É':
                case 'í':
                case 'Í':
                case 'ó':
                case 'Ó':
                case 'ú':
                case 'Ú': return true;
            }
        }

        return false;
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


    public static void Inicialización(boolean revisiónDePalabras, boolean activarConsola) {
        int numDeTemas = InicializarTemas(); //ejecuta inicialización y guarda el número de temas.

        long comienzoInicialización = System.nanoTime();


        //InicializarSustantivos();
        //InicializarVerbos();
        //InicializarAdjetivos();
        //InicializarPalabras();
        InicializarTodo();

        int numDePalabras = Control.SustantivosListaSingular.size() + Control.VerbosListaSingular.size()
                + Control.AdjetivosListaSingular.size() + Control.PalabrasListaSingular.size();

        System.out.println("TOTAL: " + numDePalabras + " PALABRAS CREADAS, " + numDeTemas + " TEMAS USADOS");


        if (revisiónDePalabras) {
            /**Control.revisarVer();
            Control.revisarAdj();
            Control.revisarSus();
            Control.revisarPal();*/

            Control.revisiónCompleta();
        }




        long finInicialización = System.nanoTime();
        double duración = ((double)(finInicialización - comienzoInicialización))/1000000; //ms
        System.out.println("Iniciaización completada en " + duración + " ms");

        if (activarConsola) {
            Control.consola();
        }
    }



    public static void schnellBedeutung() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            String palActual = sc.nextLine();
            if (palActual.equals("cerrar")) {return;}

            Palabra[] arrActual = Palabra.buscarTodo(palActual);
            if (arrActual == null) {System.out.println("No se encontro '" + palActual + "'. "); continue; }
            for (Palabra actual : arrActual) {actual.definir();}

        }


    }


    public static void main(String[] args) {
        Control.Inicialización(true, false);
        //verificar que a los verbos no se les pueda agregar algo si yá se les agregó.
        //Planetarium [Planetarien] NNN
//verkehrsmittel; fiarse. no acabaremos, te lo apuesto.
//q pex con dauern y el imperaivo...
        //estoy para la encuesta. Me podeis haber avisado. Le mande un correo ya hace tiempo, y uno esta mañana.
        //Pues no lo he podido leer. Teneis que dcirme con anticipacion...
        //equis de. flipante
        //kleingeld = dinero suelto.
        //Dicke -n (F)  grosor.
        //Viga [madera] = Balken -'' M
        //Viga [metal] = Träger -'' M
        //Ver unterrichten = dar clases de. Er unterricht Deutsch  =el da classes de alemán.
        //"No importa que el resultado no salga bien, siempre y cuando los signos sean correctos"

        //agregar método que se asegure que los significados no estén capitalisados.
        //müde = Adj = cansado

        //Sus intento1 = new Sus("el sus", "el plur", "N", "el sig", new String[]{"comida", "verdura"});
        //System.out.println(Arrays.toString(intento1.tags));


        //agregar string estática Vocabulario en Ejer; modularizar las firmas de los métodos "Sus vocabulario"; hacer lista de ejers utilizando esto.

        //profesiones acadpemicas:
        //T = new String[]{"estudio"};
        //Medizin = medicina;
        //Lehramt = "profesión docente" = profesor pero más chido
        //Maschinenbau = Ingeniería Mecánica
        //Physik = física
        //Jura = derecho
        //Lehrer = maestro (ya lo tenemos)
        //Anwalt (Anwältin) Abogado
        //Zukunft = futuro




        //Morgen?
        //Control.schnellBedeutung();
        //OrganizaciónTemas(5);

        //se arreglo el método Control.revisiónCompleta()
        //hay que arreglar la cosa de significados dobles.
        //se reescribió Control.Inicialización(). Ahora es un método general.
        //hay que agregar un método .parseSimple() que formatée los nombres y significados no simples.
        //ya que ahorita, .getNombreSimple incluye " " antes del "[". en la versión actual.
        //se agregaron varios sustantivos nuevos en los temas de comida y cocina.
        //se agregó Control.OrganizaciónTemas(int anchura), que te deja ver como se utilizan los temas.

        /**
         * Mi WishList
         * - consola que te deje definir o buscar palabras velozmente.
         * - ejercicio que te enseñe a decir la hora, medir tiempo, y utilizar un reloj en alemán
         * - ejercicio que te enseñe a utilizar las preposiciones y verbos de objetos en sus contextos.
         * - ejercicio que te enseñe las declinaciones de los articulos y de los adjetivos.
         * - ejercicio(s) que te enseñen a conjugar verbos
         * - ejercicios que te enseñen los pronombres posesivos.
         * - El abecedario alemán
         * - Los países y sus capitales
         *    - Geografía en alemán?
         * - ejercicio que te enseñe a escribir los oficios CON Géneros (actor vs. actriz)
         * (proyecto) Constructor de oraciones coherentes.
         */


        /**
         * Palabras Wishlist
         * telefonieren
         * falls
         * ausdrücken = expresarse. chido
         * Tender la cama = Das Bett Machen
         *
         * ahorita
         * aquí
         * ayá (o allá?)
         * esto
         * eso (y "aquello" que?)
         * despues de
         * se hará despues.
         * antes
         * más tarde
         * más temprano
         *
         * mañana
         * ayer
         *
         * siempre
         * todo
         * nada
         * nunca
         * un poco
         * normalmente / a menudo
         *
         *
         */





        //todo: crear Ejer.ConsolaDefinir(String tipoDePalabra, Scanner sc)
        //todo: Agregar palabras titulares (Der Haus, Der Essen, etc.)
        //todo: agregarle javadoc con @throws a todas las funciónes

        //todo: Asegurarse que getNombre no tenga símbolos raros.

        //Todo: cambiar NullPointerExceptions a NumberFormatExceptions para evitar problemas.

        //todo: Revisar revisar verbos: Los significados tienen ")"?

        //Ver.OrganizacióndeTags();



        //todo: Agregar parser que lea la funcion crear[cosa]() y la ponga en un documento .txt
        //todo: agregar algo que lea un documento .txt y crée las palabras a partir de ello.

        //todo: Agregar palabras indicativas (Fruta, ciudad, especias, escuela, ...) a todas las listas.
        //todo: Organizar "Marcadores" en todos los diccionarios.
        //todo: Acabar de ordenar los verbos :pepeEyes:

        //todo: Definir palabra debería de saber que hacer si 'nullEntry' aparece
        //todo: asegurarse de que palabras con 'nullEntry' no entren a los ejercicios que lo necesitan.

        //todo: enseñar tema debería continuar con otros temas despues de terminar.
        //todo: BUG: ENSEÑAR TEMA NO UTILIZA TODAS LAS PALABRAS (SE SALTA UNA)
        //      ENSEÑAR TEMA OBJETO ENSEÑA 16 VERBOS PERO TENEMOS 17 (SE SALTÓ LIEGEN)

        //todo: enthülle
        //todo: gritar, susurrar,...
        //todo: Conjunciones

        //todo: Agregar imperativo y conjugacion preterito a definicion verbo
        //todo: Investigar si es posible imprimir texto con color. Ayudaría con el control y en la enseñanza.

        //todo: Checar nennen y ernennen; lassen


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


        //todo: imprimir lista tema crashéa cuando se cierra manualmente on el comando 'cerrar'. También debería darte la opción de listar todos los temas.
        //todo: Definir debería de incluir la frase "Temas" cuando lista los temas.
        //todo: Agregar las letras


        //todo: Tabmién hay que averiguar como funcionarán las conjugaciones. Créo que deberíamos de tener funciones que te den la conjugacion correcta de un verbo, a diferencia de lo que hemos estado haciendo (ej. Ver.significado... deberia ser Ver.conjugar(xxx) = String conjugado. )

    }
}
