import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;
import java.util.HashMap;
public class Control {
    public static final String entradaNula = "---";
    public static final String quePex = "?????";
    public static final String Tema = "Tema";

    //NumberFormatExceptio = hice algo mal y el yo del pasado me lo está diciendo
    //SecurityException = Error, pero uno que estoy esperando (catched). También lo uso para cerrar ventanas / consolas.
    //todo: Fix exceptions lul


    public static final String[] TEMAS = new String[]{
            "comida", "fruta", "verdura", "país", "ciudades",
            "capital", "animal", "cuerpo", /**"letras",*/ "tiempo",
            "clima", "figuras", "ropa", "deporte", "viajar",
            "escuela", "tecnología", "casa", "mueble", "médico",
            "ciudad", "medidas", "transporte", "especias", "cocina",
            "bebida", "materiales", "trabajo", "platillo", "arte",
            "geografía", "amigos", "festivos", "gramática", "conocidos",
            "planta", "profesiónes", "estudios", "calendario", "acuático",
            "física", "anatomía", "ingeniería", "ciencia",

            "test",

            "moverse", "básico", "modal",
            "menos_básico", "misceláneo",
            "comunicación", "vista", "tienda", "auxiliar",
            "objeto", "mente", "escritura", "preguntas",

            "color", "aspecto", "personalidad",

            "conjunciones", "interrogativos", "pronombres", "preposiciones", "acusativo",
            "dativo", "wechsel"
    };

    //Estos se inicializan en el Main de Control. (Hasta abajo)
    public static ArrayList<Lista<Palabra>> Sustantivos; //La lista se guarde como variable estatica.
    public static ArrayList<Lista<Palabra>> Verbos;
    public static ArrayList<Lista<Palabra>> Adjetivos;
    public static ArrayList<Lista<Palabra>> Palabras;
    public static ArrayList<Lista<Palabra>> Temas;

    //Se usan de ves en cuando, no son tan importantes. Contienen una de cada palabra, sin repeticiones.
    public static Lista<Palabra> SustantivosListaSingular = new Lista<Palabra>();
    public static Lista<Palabra> VerbosListaSingular = new Lista<Palabra>();
    public static Lista<Palabra> AdjetivosListaSingular = new Lista<Palabra>();
    public static Lista<Palabra> PalabrasListaSingular = new Lista<Palabra>();

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

    /**
     * Regresa la lista de todas las palabras de un tema dado.
     * @param tema el tema requerido
     * @return la Lista<Palabra> del tema.
     */
    public static Lista<Palabra> getTema(String tema) {
        for (Lista<Palabra> temaActual : Control.Temas) {
            if (temaActual.getNombre().equals(tema)) {return temaActual;}
        }

        throw new NumberFormatException("Error: Tema no existe...?");
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

        int num = TEMAS.length;

        Arrays.sort(TEMAS); //opcional pero mejor para el usuario
        //System.out.println("TEMAS: " + Arrays.toString(TEMAS));


        //Nos aseguramos de que no haya entradas dobles.
        for (int i = 0; i < TEMAS.length; i++) {
            for (int j = i+1; j < TEMAS.length; j++) {
                if (TEMAS[i].equals(TEMAS[j])) {
                    throw new NullPointerException("Error: Existe una Tag duplicada: " + TEMAS[i]);
                }
            }
        }

        //Se crean las listas
        ArrayList<Lista<Palabra>> listaDeSustantivos = new ArrayList<Lista<Palabra>>();
        ArrayList<Lista<Palabra>> listaDeVerbos = new ArrayList<Lista<Palabra>>();
        ArrayList<Lista<Palabra>> listaDeAdjetivos = new ArrayList<Lista<Palabra>>();
        ArrayList<Lista<Palabra>> listaDePals = new ArrayList<Lista<Palabra>>();

        //Llenamos las listas generadas antemente
        for (String temaActual : TEMAS) {
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
        if (Control.TEMAS.length % n == 0) {
            alt = Control.TEMAS.length / n;
        } else {
            alt = 1+(Control.TEMAS.length / n);
        }
        String[][] out = new String[alt][n];

        int counter = 0;
        for (int i = 0; i < alt; i++) {
            for (int j = 0; j < n; j++) {
                if (counter < Control.TEMAS.length) {
                    int numSus = -1;
                    int numVer = -1;
                    int numAdj = -1;
                    int numPal = -1;

                    try { numSus = Control.getTema(TEMAS[counter], Sus.Sus).size();
                    } catch (NumberFormatException e) { numSus = 0; }

                    try { numVer = Control.getTema(TEMAS[counter], Ver.Ver).size();
                    } catch (NumberFormatException e) { numVer = 0; }

                    try { numAdj = Control.getTema(TEMAS[counter], Adj.Adj).size();
                    } catch (NumberFormatException e) { numAdj = 0; }

                    try { numPal = Control.getTema(TEMAS[counter], Pal.Pal).size();
                    } catch (NumberFormatException e) { numPal = 0; }

                    int numTotal = numAdj + numPal + numSus + numVer;

                    out[i][j] = Control.TEMAS[counter] + ": " + numSus + "S " + numVer + "V " + numAdj + "A " + numPal + "P = " + numTotal;
                    counter++;
                } else {
                    out[i][j] = " ";
                }
            }
        }

        Control.arrPrint(out);

    }




    /**
     * Método global que revisa colisiones al inicializar las palabras. Si existen colisiones el .getNombre o .getSignificado, se les informará
     * a todas las palabras que colisionen.
     * @param palabraActual la palabra que se está revisando.
     */
    public static void RevisarColisiones(Palabra palabraActual) {
        //Aquí se revisarán las palabras, y marcarémos todas las colisiones. Toda palabra sabrá las otras palabras que colisionan.

        if (listaDeNombres.containsKey(palabraActual.getNombre())) { //Hemos encontrado una colisión
            Palabra indicador = listaDeNombres.get(palabraActual.getNombre()); //la primera palabra con este nombre que se agregó

            for (Palabra actual : indicador.colisionesNombre) {
                actual.colisionesNombre.add(palabraActual);
                palabraActual.colisionesNombre.add(actual);
            }

            palabraActual.colisionesNombre.add(indicador);
            indicador.colisionesNombre.add(palabraActual);

        } else { //No se ha detectado ninguna colisión.
            //Esta palabra servirá como indicador. Todas las demás palabras se revisarán contra esta palabra.
            listaDeNombres.put(palabraActual.getNombre(), palabraActual);
        }


        //Hacemos exactamente lo mismo con actual.getSignificado();
        if (listaDeSignificados.containsKey(palabraActual.getSignificadoSimple())) { //Hemos encontrado una colisión
            Palabra indicador = listaDeSignificados.get(palabraActual.getSignificadoSimple()); //la primera palabra con este significado que se agregó

            for (Palabra actual : indicador.colisionesSignificado) {
                actual.colisionesSignificado.add(palabraActual);
                palabraActual.colisionesSignificado.add(actual);
            }

            palabraActual.colisionesSignificado.add(indicador);
            indicador.colisionesSignificado.add(palabraActual);

        } else { //No se ha detectado ninguna colisión.
            //Esta palabra servirá como indicador. Todas las demás palabras se revisarán contra esta palabra.
            listaDeSignificados.put(palabraActual.getSignificadoSimple(), palabraActual);
        }



    }
    public static HashMap<String, Palabra> listaDeNombres = new HashMap<String, Palabra>();
    public static HashMap<String, Palabra> listaDeSignificados = new HashMap<String, Palabra>();
    //Estas listas contienen una copia de cada nombre/significado. Si hay dobles, la palabra te lo dirá con .tieneColisiones();




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

        for (String tema : Control.TEMAS) {
            if (TemaExiste(tema, Sus.Sus) || TemaExiste(tema, Ver.Ver) || TemaExiste(tema, Adj.Adj) || TemaExiste(tema, Pal.Pal)) {
                continue;
            }

            throw new NumberFormatException("Error: El tema '" + tema + "' no es utilizado");
        }

        Temas = new ArrayList<Lista<Palabra>>();
        for (String temaActual : TEMAS) {
            Lista<Palabra> sus = new Lista<Palabra>();
            Lista<Palabra> ver = new Lista<Palabra>();
            Lista<Palabra> adj = new Lista<Palabra>();
            Lista<Palabra> pal = new Lista<Palabra>();
            if (Control.TemaExiste(temaActual, Sus.Sus)) {sus = Control.getTema(temaActual, Sus.Sus);}
            if (Control.TemaExiste(temaActual, Ver.Ver)) {ver = Control.getTema(temaActual, Ver.Ver);}
            if (Control.TemaExiste(temaActual, Adj.Adj)) {adj = Control.getTema(temaActual, Adj.Adj);}
            if (Control.TemaExiste(temaActual, Pal.Pal)) {pal = Control.getTema(temaActual, Pal.Pal);}

            Lista<Palabra> listaCompleta = Lista.concatenar(Lista.concatenar(Lista.concatenar(sus, ver), adj), pal);
            listaCompleta.nombre = temaActual;
            Temas.add(listaCompleta);
        }

    }


    //Borra la pantalla con 'n' líneas blancas
    public static void clrScreen(int n) {
        if (n < 1) {throw new NumberFormatException("Número invalido >:(");}
        int i = 0;
        while (i < n) {
            System.out.println();
            i++;
        }
    }


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

    //convierte un String[] ineal a un String[][] bidimensional con anchura 'anchura'
    public static String[][] bidimensional(String[] input, int anchura) {
        int numDeValores = input.length;
        int alturaFinal = -1;
        if (numDeValores % anchura == 0) {
            alturaFinal = (numDeValores / anchura);
        } else {
            alturaFinal = 1 + (numDeValores / anchura);
        }

        String[][] arr = new String[alturaFinal][anchura];

        for (int i = 0; i < alturaFinal; i++) {
            for (int j = 0; j < anchura; j++) {
                int numActual = j + (i*anchura);
                if (numActual >= input.length) {
                    arr[i][j] = "";
                } else {
                    arr[i][j] = input[numActual];
                }
            }
        }
        return arr;
    }



    public static boolean empiezaCon(String raíz, String prefijo) {
        if (raíz == null || prefijo == null) {
            return false;
        } else if (prefijo.length() > raíz.length()) {
            return false;
        }

        return (raíz.substring(0, prefijo.length()).equals(prefijo));
    }
    public static String quitarPrefijo(String raíz, String prefijo)  {
        if (!empiezaCon(raíz, prefijo)) {
            throw new NumberFormatException("Error: La raíz '" + raíz + "' no empieza con prefijo '" + prefijo + "'.");
        } else {
            return raíz.substring(prefijo.length(), raíz.length());
        }
    }
    public static String quitarSufijo(String raíz, String sufijo) {
        if (raíz != null && sufijo != null) {
            if (raíz.endsWith(sufijo)) {
                return raíz.substring(0, raíz.length() - sufijo.length());
            } else {
                throw new NumberFormatException("Error: El sufijo '" + sufijo + "' no se encuentra en la palabra '" + raíz + "'");
            }
        } else {
            throw new NumberFormatException("Error: Parametro null lel");
        }
    }
    public static boolean acabaCon(String raíz, String sufijo) {
        return raíz.endsWith(sufijo);
        //No sabía que ya existía este comando ... :P
    }



    public static void comandos() {
        System.out.println("-comandos: Imprime esta lista.");
        System.out.println("-hola: Dice ¡Hola!");
        System.out.println("-cerrar: Cierra la aplicación.");
        System.out.println("-practicar sustantivos: Arranca los ejercicios de los sustantivos");
        System.out.println("-practicar verbos: Arranca los ejercicios de los verbos");
        System.out.println("-practicar adjetivos: Arranca los ejercicios de los adjetivos");
        System.out.println("-practicar palabras: Arranca los ejercicios de palabras miscelánes (interrogativos, preposiciones, etc.)");
        System.out.println("-practicar lecciones: Activa la consola de las lecciones gramaticales del alemán.");
        System.out.println("-buscador: Enciende el buscador. <WIP>");
        System.out.println("-listar temas: Lista todos los temas, y muestra que tipo de palabras los usan.");

        System.out.println();

        //System.out.println();

    } //imprime la lista de comandos


    //Revisan repeticiones en los significados de las palabras respectivas.
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
                //System.out.println("El nombre '" + actual.getNombre() + "' es repetido. " + actual.getNombre()); numeroDeErrores++;
            } else {
                //listaNombres.add(actual.getSignificado());
            }

            if (listaSignificados.contains(actual.getSignificado())) {
                //System.out.println("El significado '" + actual.getSignificado() + "' es repetido. " + actual.getNombre()); numeroDeErrores++;
            } else {
                //listaSignificados.add(actual.getSignificado());
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

                //Esto podría irse, pero...
                if (susActual.género.equals(entradaNula)) {
                    //System.out.println("¿Seguro que " + susActual.sustantivo + " no tiene género?");
                    //numeroDeErrores++;
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
        Control.clrScreen(20);
        boolean activo = true;
        System.out.println("¡Bienvenido a la academia!");

        while (activo) {
            boolean entendido = false;

            System.out.println("¿Qué quiere hacer?");
            System.out.print(">>");
            String comando = sc.nextLine();
            System.out.println();
            switch(comando) {
                case "hola": System.out.println("¡Hola!");                                  entendido = true; break;

                case "cerrar": System.out.println("Cerrando aplicación."); activo = false;  entendido = true; break;

                case "comandos": Control.comandos();                                        entendido = true; break;

                case "practicar sustantivos":
                    //Ejer.PracticarSustantivos(sc);
                    Ejer.ConsolaSus.activar();
                    continue;

                case "practicar verbos":
                    //Ejer.PracticarVerbos(sc);
                    Ejer.ConsolaVer.activar();
                    continue;

                case "practicar adjetivos":
                    //Ejer.PracticarAdjetivos(sc);
                    Ejer.ConsolaAdj.activar();
                    continue;

                case "practicar palabras":
                    //necesita un nombre menos ambiguo
                    //Ejer.PracticarPalabras(sc);
                    Ejer.ConsolaPal.activar();
                    continue;

                case "practicar temas": Ejer.ConsolaTem.activar(); continue;

                case "practicar lecciones": Ejer.Lecciones(sc);                             entendido = true; break;

                case "buscador": Control.schnellBedeutung(sc);                              entendido = true; break;

                case "listar temas": Control.OrganizaciónTemas(6);/**el num no importa*/ entendido = true; break;

            }
            if(!entendido) {
                System.out.println("Comando '" + comando + "' no reconocido. Diga 'comandos' para la lista de comandos");
            }
        }
    }


    public static void Inicialización(boolean revisiónDePalabras, boolean activarConsola) {
        long comienzoInicialización = System.nanoTime();


        //ejecuta inicialización y guarda el número de temas.
        int numDeTemas = InicializarTemas();


        //Crea y formatéa todos los diccionarios en el programa.
        //Esto debe de suceder despues de la inicialización de temas, pero antes que tödo lo demás.
        InicializarTodo();

        int numDePalabras = Control.SustantivosListaSingular.size() + Control.VerbosListaSingular.size()
                + Control.AdjetivosListaSingular.size() + Control.PalabrasListaSingular.size();
        System.out.println("TOTAL: " + numDePalabras + " PALABRAS CREADAS, " + numDeTemas + " TEMAS USADOS");


        //Método opcional que revisa los diccionarios y identifica errores posibles.
        if (revisiónDePalabras) {
            Control.revisiónCompleta();
        }


        //Inicializa las lecciones y los ejercicios.
        //Debe suceder despues de la creación de los diccionarios.
        Ejer.Inicialización();


        long finInicialización = System.nanoTime();
        double duración = ((double)(finInicialización - comienzoInicialización))/1000000; //ms
        System.out.println("Iniciaización completada en " + duración + " ms");

        if (activarConsola) {
            Control.consola();
        }
    }


    //todo: Convertir esto a una Consola()
    public static void schnellBedeutung(Scanner sc) {
        System.out.println("Comenzando buscador. Ingrese la palabra o significado. ");
        System.out.println("Diga 'cerrar buscador' para cerrar el buscador.");

        while (true) {
            String input = sc.nextLine();

            if (input.equals("cerrar")) { System.out.println("[Diga 'cerrar buscador' para cerrar el buscador]"); }

            if (input.equals("cerrar buscador")) {
                System.out.println("Cerrando buscador.");
                return;
            }

            Palabra[] listaNom = new Palabra[0];
            Palabra[] listaDef = new Palabra[0];

            try {
                listaNom = Palabra.buscarNombre(input);
            } catch (SecurityException e) {}

            try {
                listaDef = Palabra.buscarSignificadoSimple(input);
            } catch (SecurityException e) {}


            if (listaNom.length == 0 && listaDef.length == 0) {
                System.out.println("No se encontro '" + input + "'. Diga 'cerrar buscador' para cerrar el buscador.");
                continue;
            }

            //Si encontramos algo

            if (listaNom.length != 0) {
                System.out.println("Palabra '" + input + "' en alemán:");
                for (Palabra actual : listaNom) {
                    actual.definir(); System.out.println();
                }
            }

            System.out.println();

            if (listaDef.length != 0) {
                System.out.println("Palabras en alemán que signifiquen '" + input + "':");
                for (Palabra actual : listaDef) {
                    actual.definir(); System.out.println();
                }
            }


        }


    }




    public static void main(String[] args) {
        Control.Inicialización(false, true);

        //todo: Investigar a creación de ecepciones nuevas. Nos ayudaría un chorro...
        //todo: En ejercicios de significados, se debería de displeyar el significado completo, no el simple.
        //todo: make custom errors more clear and less ambiguous programs.
        //Ejer.ConsolaVer.activar();

        //Ejer.EjecutarComando("listar temas", Ejer.ConsolaAdj.comandosDisponibles);

        //Ejer.imprimirTema("casa", "Sus");

        //Consola ejerciciosSus = new Consola("Ejercicios de Sustantivos", new String[]{"h"}, "???");
        /**
         * Cada comando debe de tener una clave única y universalmente leible.
         * Cada comando también debería de tener un identificador para el usuario.
         *    Ej. "practicar vocabulario" en la consola de los verbos no es igual a "practicar vocabulario" en la consola de los sustantivos.
         *    Por lo tanto, nombraríamos a uno "Sus Vocabulario" y el otro "Ver Vocabulario", pero tendrían la misma clave. Tödo dependería
         *    de donde se accede.
         *
         *    Existirá un Hashmap que dado la clave del programa, regrese el nombre simple (No inyectivo).
         *    Al inicializar cada consola, se le pasará la lista de claves de comandos que debería tener.
         */


        //todo: Agregar método para hallar palabras con significados múltiples, sinónimos. etc.

        //todo: Añadirle ">>" al display cada vez que queramos enseñar algo.
        //todo: Actualizar consolas, tan bien chafas
        //    todo: De hecho, nos vendría bien hacer una clase nueva de consolas.
        //Agregar otros parámetros al buscador (plurales, participios, etc. )
        //Agregarle opción a los métodos definir() para incluir una indentación y número
        //todo: Declinación del sustantivo y del artículo...¿Cómo funcionan?
        //Control.OrganizaciónTemas(7);

        //ziehen está chingado

        //todo: agergar otros parámetros al buscardor
        //todo: Verificar que las profesiones estén correctas.
        //fiarse.
        // agregar verificador para la generación de los ADJ
        // q pex con dauern y el imperaivo...
        //estoy para la encuesta. Me podeis haber avisado. Le mande un correo ya hace tiempo, y uno esta mañana.
        //Pues no lo he podido leer. Teneis que dcirme con anticipacion...
        //equis de. flipante
        //kleingeld = dinero suelto.
        //Anchura? Altura? Profundidad, peso, ...temperatura, regla, termómetro, báscula
        //Viga [madera] = Balken -'' M
        //Viga [metal] = Träger -'' M
        //Ver unterrichten = dar clases de. Er unterricht Deutsch  =el da classes de alemán.
        //"No importa que el resultado no salga bien, siempre y cuando los signos sean correctos"

        //agregar explicación para leben vs wohnen.

        //agregar string estática Vocabulario en Ejer; modularizar las firmas de los métodos "Sus vocabulario"; hacer lista de ejers utilizando esto.


        //Agregar indicadores a Sus, Ver, y Adj que indiquen si es regular o no un parámetro.


        //se arreglo el método Control.revisiónCompleta()
        //hay que arreglar la cosa de significados dobles.
        //se reescribió Control.Inicialización(). Ahora es un método general.
        //hay que agregar un método .parseSimple() que formatée los nombres y significados no simples.
        //ya que ahorita, .getNombreSimple incluye " " antes del "[". en la versión actual.
        //se agregaron varios sustantivos nuevos en los temas de comida y cocina.
        //se agregó Control.OrganizaciónTemas(int anchura), que te deja ver como se utilizan los temas.

        /**
         * Mi WishList
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
         * ausdrücken = expresarse. chido
         * Tender la cama = Das Bett Machen
         * Der Fall (Fälle): caso [en el caso de; En el mejor de los casos; Esto es un caso especial; por si acaso, ...]
         *
         * aquí
         * ayá (o allá?)
         * esto
         * eso (y "aquello" que?)
         * siempre
         * tödo
         * nada
         * nunca
         * un poco
         * normalmente / a menudo
         * que pex con fangen
         * comunicar [unir] = verbinden; comunicar [informar] = mitteilen
         * agregar o revisar [enthülle, gritar, susurrar, nennen, ernennen, lassen, werfen, ziehen]
         */

        //todo: crear Ejer.ConsolaDefinir(String tipoDePalabra, Scanner sc)
        //todo: agregarle javadoc con @throws a todas las funciónes

        //Todo: cambiar NullPointerExceptions a NumberFormatExceptions para evitar problemas.



        //todo: Acabar de ordenar los verbos :pepeEyes:
        //todo: Método que revise que la detección de errores de declaraciones si funcione

        //todo: asegurarse de que palabras con 'nullEntry' no entren a los ejercicios que lo necesitan.

        //todo: enseñar tema debería continuar con otros temas despues de terminar.
        //todo: BUG: ENSEÑAR TEMA NO UTILIZA TODAS LAS PALABRAS (SE SALTA UNA)
        //      ENSEÑAR TEMA OBJETO ENSEÑA 16 VERBOS PERO TENEMOS 17 (SE SALTÓ LIEGEN)


    }
}
