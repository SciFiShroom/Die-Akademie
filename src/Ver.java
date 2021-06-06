import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Ver extends Palabra{
    public static final String Ver = "Ver";
    public static final String Verbo = "Verbo";
    public static final String nullEntry = Control.entradaNula;
    public static final String reg = "Regular";



    //Aquí se guardan todos los verbos. No hay ninguna estructura ni orden; eso viene después.
    public static HashMap<String,Ver> TodosLosVerbos = new HashMap<String, Ver>(); //Sintaxis <Ver.verbo, [el objeto verbo]>


    //Parámetros
    public String verbo;
    public String prefijo;
    public String verbstamm;
    public boolean esTrennbare;
    public boolean terminaConEN;
    public String[] tags;
    public String infinitiv;



    //Constructor universal
    public Ver(String verbo, String[] tags) {
        //Control.VerbosListaSingular.add(this);

        //Se agrega el verbo al Hashmap universal
        if (TodosLosVerbos.containsKey(verbo)) {
            throw new NumberFormatException("Error: El verbo " + verbo + " se ha agregado dos veces.");
        }
        TodosLosVerbos.put(verbo, this);


        if (verbo.contains("|")) { //Trennbare!
            this.esTrennbare = true;
            if (verbo.split("\\|").length != 2) {
                throw new NumberFormatException("Error: No entendí " + verbo);
            }
            this.prefijo = verbo.split("\\|")[0];
        }

        if (verbo.substring(verbo.length()-2,verbo.length()).equals("en")) {
            terminaConEN = true;
            verbstamm = verbo.substring(0,verbo.length()-2);
        } else {
            //En el caso de que no termine con "-en", todavía es útil tener el verbstamm. Esto se usa, por ejemplo,
            //en la construcción del imperativo.
            terminaConEN = false;
            verbstamm = verbo.substring(0,verbo.length()-1);
        }


        //Construimos el infinitiv
        String[] temp = verbo.split("\\|");
        this.infinitiv = "";
        for (String s : temp) {infinitiv += s;}


        this.verbo = verbo;
        this.tags = tags;

        // Control.RevisarColisiones(this);
    }

    //Para agregar tags adicionales
    public void addTag(String tag) {
        for (String t : this.tags) {
            if (t.equals(tag)) {throw new NumberFormatException("Error: Tag agregada dos veces.");}
        }
        this.tags = Arrays.copyOf(this.tags, this.tags.length+1);
        this.tags[this.tags.length - 1] = tag;
    }




    //<Präsens>
    public String[] präsens;
    /**
     * Hay cuatro casos.
     *
     * 1. "SsinE":    Verbos como machen, kommen, y sagen. Verbstamm + [conjugación].
     * 2. "SconE":    Verbos como arbeiten, reden, y antworten: Verbstamm + [Conjugación distinta, que lleva una 'e' adicional]
     * 3. "<raíz>":   Verbos como sehen, fahren, y laufen. Verbstamm cambia para du y er, pero por lo demás son predecibles
     * 4. "Sern":     Verbos como fordern, ändern, y steuern. Terminan con '-ern' y siguen una conjugación predecible.
     * 5. "Seln":     Verbos como handeln, wechseln, y bummeln. Terminan con 'eln', y siguen una conjugación predecible (y distinta al tipo 4)
     * 6. <String[]>: Verbos como tun, sein, y werden. No terminan en "-en", o no siguen ninguna regla.
     */
    public static final String PsinE_Präsens = "sinE_Präsens";
    public static final String PconE_Präsens = "conE_Präsens";

    /**
     * define conjugación 'präsens' para casos 1,2, y 3.
     * @param aux Indica como se conjuga el verbo:
     *            Caso 1: aux = sinT_Präsens. Ej. machen, kommen, sagen
     *            Caso 3: aux = conT_Präsens. Ej. arbeiten, reden, antworten
     *            Caso 2: aux = otro verbstamm. Ej. sehen, fahren, laufen
     */
    public void setPräsens(String aux) {
        if (!this.terminaConEN) {
            throw new NumberFormatException("Error: El verbo " + this.verbo + "no tiene präsens regular. Revisar definición.");
        }

        String sufijo;
        if (!esTrennbare) {
            sufijo = "";
        } else {
            sufijo = " " + prefijo;
        }

        this.präsens = new String[6];
        if (aux.equals(PsinE_Präsens)) {
            präsens[0] = this.verbstamm + "e" + sufijo;
            präsens[1] = this.verbstamm + "st" + sufijo;
            präsens[2] = this.verbstamm + "t" + sufijo;
            präsens[3] = this.verbstamm + "en" + sufijo;
            präsens[4] = this.verbstamm + "t" + sufijo;
            präsens[5] = this.verbstamm + "en" + sufijo;
        }  else if (aux.equals(PconE_Präsens)){
            präsens[0] = this.verbstamm + "e" + sufijo;
            präsens[1] = this.verbstamm + "est" + sufijo;
            präsens[2] = this.verbstamm + "et" + sufijo;
            präsens[3] = this.verbstamm + "en" + sufijo;
            präsens[4] = this.verbstamm + "et" + sufijo;
            präsens[5] = this.verbstamm + "en" + sufijo;
        } else { //Asumimos que es caso 2
            präsens[0] = this.verbstamm + "e" + sufijo;
            präsens[1] = aux + "st" + sufijo;
            präsens[2] = aux + "t" + sufijo;
            präsens[3] = this.verbstamm + "en" + sufijo;
            präsens[4] = this.verbstamm + "t" + sufijo;
            präsens[5] = this.verbstamm + "en" + sufijo;
        }
    }

    /**
     * define conjugación 'präsens' para caso 4.
     * Ojo! Si el verbo es trennbare, no se incluye el prefijo aquí; solo la raíz.
     * El método echa un error si detecta que se ha incluido el prefijo del trennbare verb.
     * @param conjugación la conjugación präsens.
     *                    Caso 4: Irregular. Ej: sein, tun, werden.
     *                    Tödo verbo que no termine con "-en" cae aquí
     */
    public void setPräsens(String[] conjugación) {
        if (conjugación.length != 6) {
            throw new NumberFormatException("Error: Definición de präsens inválido. verbo = " + this.verbo);
        }

        //detecta si hay espacios. Si alguien intenta añadir un trennbare verb con el prefijo, esto lo previene.
        for (String s : conjugación) {
            if (s.contains(" ")) {
                throw new NumberFormatException("Error: se ha detectado un espacio en la definición del präsens de " + this.verbo);
            }
        }

        if (esTrennbare) {
            for (int i = 0; i < 6; i++) {
                conjugación[i] += " " + prefijo;
            }
        }
        this.präsens = conjugación;
    }
    //</Präsens>




    //<Präteritum>
    public String[] präteritum = new String[6];
    /**
     * Hay cuatro casos:
     *
     * Caso 1: Verbos como machen, sollen, y wollen. Verbstamm + [conjugación]
     * Caso 2: Verbos como denken, nenne, y wissen. Verbstamm cambia, pero se conjuga igual.
     * Caso 3: Verbos como kommen, ziehen, y sein. Verbstamm cambia y la conjugación es diferente.
     * Caso 4: Verbos como tun, fordern, y werden. Irregular. No terminan en "-en", o no siguen ninguna de los formatos superiores.
     */
    public static final String vacío = "VACÍO";
    public static final String noVacío = "NO_VACÍO";

    /**
     * define conjugación 'präteritum'. Asume caso 1.
     */
    public void setPräteritum() {
        if (!this.terminaConEN) {
            throw new NumberFormatException("Error: El verbo " + this.verbo + "no tiene präteritum regular. Revisar definición.");
        }

        String sufijo;
        if (esTrennbare) {
            sufijo = " " + prefijo;
        } else {
            sufijo = "";
        }

        präteritum[0] = verbstamm + "te" + sufijo;
        präteritum[1] = verbstamm + "test" + sufijo;
        präteritum[2] = verbstamm + "te" + sufijo;
        präteritum[3] = verbstamm + "ten" + sufijo;
        präteritum[4] = verbstamm + "tet" + sufijo;
        präteritum[5] = verbstamm + "ten" + sufijo;
    }

    /**
     *
     * @param neuStamm el nuevo verbstamm que se usa para la conjugación. Ej. 'denken' da 'dach'.
     * @param caso "vacío" = caso 3. "noVacío" = caso 2. "vacío" se refiere a la ausencia de conjugación
     *             en la conjugación "ich" y "er". Ej. Ich kam.
     */
    public void setPräteritum(String neuStamm, String caso) {
        String sufijo;
        if (esTrennbare) {
            sufijo = " " + prefijo;
        } else {
            sufijo = "";
        }

        if (caso.equals(vacío)) {
            präteritum[0] = neuStamm + sufijo;
            präteritum[1] = neuStamm + "st" + sufijo;
            präteritum[2] = neuStamm + sufijo;
            präteritum[3] = neuStamm + "en" + sufijo;
            präteritum[4] = neuStamm + "t" + sufijo;
            präteritum[5] = neuStamm + "en" + sufijo;
        } else if (caso.equals(noVacío)) {
            präteritum[0] = neuStamm + "te" + sufijo;
            präteritum[1] = neuStamm + "test" + sufijo;
            präteritum[2] = neuStamm + "te" + sufijo;
            präteritum[3] = neuStamm + "ten" + sufijo;
            präteritum[4] = neuStamm + "tet" + sufijo;
            präteritum[5] = neuStamm + "ten" + sufijo;
        } else {
            throw new NumberFormatException("Error en definición 'präteritum' en verbo " + this.verbo);
        }
    }

    /**
     * define conjugación 'präteritum'. Asume caso 4.
     * @param conjugación La conjugación präteritum, añadida manualmente.
     */
    public void setPräteritum(String[] conjugación) {
        if (conjugación.length != 6) {
            throw new NumberFormatException("Error: Definición de präteritum inválido. verbo = " + this.verbo);
        }
        this.präteritum = conjugación;
    }
    //</Präteritum>




    //<Partizip Perfekt>
    public String partizipPerfekt;

    /**
     * define el partizip perfekt.
     * @param partizip el Partizip Perfekt (Partizip II).
     */
    public void setPartizipPerfekt(String partizip) {
        this.partizipPerfekt = partizip;
    }
    //</Partizip Perfekt>




    //<Perfekt>
    public String[] perfekt;

    public void setPerfekt() {
        String[] base;
        if (this.hilfsverb.equals("haben")) {
            base = new String[]{"habe", "hast", "hat", "haben", "habt", "haben"};
        } else {
            base = new String[]{"bin", "bist", "ist", "sind", "seid", "sind"};
        }

        for (int i = 0; i < 6; i++) {base[i] += " " + this.partizipPerfekt;}
        this.perfekt = base;
    }
    //</Perfekt>




    //<Hilfsverb>
    public String hilfsverb = "haben";
    public void setHilfsverbSein() {
        this.hilfsverb = "sein";
    }
    //</Hilfsverb>




    //<Konjunktiv II>
    //En particular, nos referimos al konjunktiv - irreales Zukunft.
    public boolean konjunktivIIEsIrregular;
    public String[] konjunktivII;

    /**
     * Si no se define un Konjunktiv II, se genera automáticamente. (Este es el caso con casi todos los verbos)
     */
    public void setKonjunktivII() {
        //Asume caso regular
        if (this.konjunktivII != null) {
            throw new NumberFormatException("Error: El verbo " + this.verbo + "ya tiene Konjunktiv II.");
        }
        this.konjunktivII = new String[]{
                "würde " + this.infinitiv,
                "würdest " + this.infinitiv,
                "würde " + this.infinitiv,
                "würden " + this.infinitiv,
                "würdet " + this.infinitiv,
                "würden " + this.infinitiv
        };
    }

    /**
     * Algunos (pocos) verbos tienen Konjunktiv II irregular. En este caso, se agrega manualmente.
     * @param konjunktivII el konjunktivII[].
     */
    public void setKonjunktivII(String[] konjunktivII) {
        if (konjunktivII.length != 6) {throw new NumberFormatException("Error: Konjunktiv II inválido");}
        this.konjunktivII = konjunktivII;
    }
    //</Konjunktiv II>




    //<Imperativ>
    /**
     * Hay cuatro casos para el imperativ. Hay dos casos regulares para los verbos que terminan con "-en",
     * un caso regular para los que no terminan con "-en", y un caso irregular.
     *
     * No todos los verbos tienen imperativ; tödo verbo comienza asumiendo que no tiene imperativo (aunque casi todos lo tienen)
     */
    public static final String IsinE_Imperativ = "sinE_Imperativ";
    public static final String IconE_Imperativ = "conE_Imperativ";
    public static final String noTerminaConEN = "noTerminaConEN";
    public String[] imperativ; //Sintaxis: {du, ihr, wir/Sie}
    public boolean tieneImperativ = false;

    /**
     * Aquí se construyen los imperativos
     * @param caso el tipo de imperativ.
     */
    public void setImperativ(String caso) {
        if (this.tieneImperativ) {throw new NumberFormatException("Error: imperativ ya declarado");}
        this.tieneImperativ = true;

        if (!terminaConEN && caso.equals(noTerminaConEN)) {
            this.imperativ = new String[]{
                    verbstamm + "e",
                    verbstamm + "t",
                    verbstamm + "en"
            };
        } else if (terminaConEN && caso.equals(IsinE_Imperativ)) {
            this.imperativ = new String[]{
                    verbstamm,
                    verbstamm + "t",
                    verbstamm + "en"
            };
        } else if (terminaConEN && caso.equals(IconE_Imperativ)) {
            this.imperativ = new String[]{
                    verbstamm + "e",
                    verbstamm + "et",
                    verbstamm + "en"
            };
        } else {
            throw new NumberFormatException("Error: Declaración de Imperativo inválido. ");
        }

        //Trennbare!
        if (this.esTrennbare) {
            for (int i = 0; i < this.imperativ.length; i++) {this.imperativ[i] += " " + this.prefijo;}
        }
    }

    /**
     * Imperativ irregular. Se agrega manualmente. Si el verbo es trennbare, NO se agrega el prefijo.
     * @param imperativ el imperativ[].
     */
    public void setImperativ(String[] imperativ) {
        if (tieneImperativ) {
            throw new NumberFormatException("Error: Ya se había declarado un imperativ");
        } else if (imperativ.length != 3) {
            throw new NumberFormatException("Error: Imperativ inválido");
        } else {
            //Esto se implementa para asegurarnos que no se hayan incluido los prefijos en la declaración del imperativ.
            for (String s : imperativ) {
                if (s.contains(" ")) {
                    throw new NumberFormatException("Error: declaración de Imperativ inválida. ");
                }
            }
            this.imperativ = imperativ;
            this.tieneImperativ = true;
        }

        //Trennbare!
        if (this.esTrennbare) {
            for (int i = 0; i < this.imperativ.length; i++) {this.imperativ[i] += " " + this.prefijo;}
        }
    }

    /**
     * En algunas ocasiones (muy inusuales), un verbo no tendrá imperativo. En este caso, se usa este método.
     * @param sinImperativ realmente no hace nada.
     */
    public void setImperativ(boolean sinImperativ) {
        if (tieneImperativ) {
            throw new NumberFormatException("Error: Ya se había declarado un imperativ");
        }

        this.tieneImperativ = true;
        this.imperativ = new String[]{"---", "---", "---"};

    }
    //</Imperativ>




    //<Konjunktiv I>
    public String[] konjunktivI;

    //Asume construcción regular
    public void setKonjunktivI() {
        if (!this.terminaConEN) {
            throw new NumberFormatException(
                    "Error: El konjunktiv I se debe de agregar manualmente para los verbos que no terminan con '-en'");
        }

        this.konjunktivI = new String[]{
                this.verbstamm + "e",
                this.verbstamm + "est",
                this.verbstamm + "e",
                this.verbstamm + "en",
                this.verbstamm + "et",
                this.verbstamm + "en",
        };

        if (this.esTrennbare) {
            for (String s : this.konjunktivI) { s += " " + this.prefijo;}
        }
    }

    /**
     * En el (extraño) caso de que no tenga un konjunktiv I regular, aquí se agrega manualmente.
     * @param konjunktivI el konjunktiv I.
     */
    public void setKonjunktivI(String[] konjunktivI) {
        //No queremos que se pongan los prefijos aquí, como siempre.
        for (String s : konjunktivI) {if (s.contains(" ")) {throw new NumberFormatException("Error: Sin prefijos!");}}

        this.konjunktivI = konjunktivI;

        if (this.esTrennbare) {
            for (String s : this.konjunktivI) { s += " " + this.prefijo;}
        }
    }
    //</Konjunktiv I>




    //<Partizip I>
    public String partizipI;

    //Agrega Partizip I, asumiendo que sea regular. Casi todos los verbos lo son.
    public void setPartizipI() {
        partizipI = this.infinitiv + "d";
    }

    //Si no es regular se agrega. Súper simple
    public void setPartizipI(String partizipI) {
        this.partizipI = partizipI;
    }
    //</Partizip I>




    //<Significado>
    public significadoVer[] significados; //Cada entrada es un significado diferente

    /**
     * Entendiendo que cada verbo puede tener varios significados / usos distintos, se agregan individualmente de la próxima manera:
     * @param significado el significado del verbo
     * @param oraciones oraciones en alemán y español demostrando el uso del verbo.
     * @param auxiliar En caso de que haya declinaciones, sea reflexivo, use una preposición, u otro detalle, se agrega aquí. Si no, se deja como null.
     * @param comentarios Comentarios opcionales. si no hay, se deja como null.
     *
     * Ejemplo: messen.
     *  - significado: "medir [un valor físico; con una herramienta]";
     *  - oraciones: new String[][]{{"Ich messe die Temperatur", "Mido la temperatura"},{"Der Baum misst sieben Meter", "El árbol mide siete metros"}}
     *  - auxiliar: null
     *  - comentarios: new String[]{"Para preguntar cuanto mide algo, normalmente se usa una construcción con 'wie'", "Ej. 'Wie hoch ist der Baum?' para preguntar 'Cuánto mide el árbol'"}
     */
    public void addSignificado(String significado, String auxiliar, String[][] oraciones, String[] comentarios) {
        if (this.significados == null) {
            this.significados = new significadoVer[1];
        } else {
            significadoVer[] nuevo = new significadoVer[significados.length + 1];
            for (int i = 0; i < significados.length; i++) {
                nuevo[i] = significados[i];
                significados = nuevo;
            }
        }
        significados[significados.length - 1] = new significadoVer(significado, oraciones, auxiliar, comentarios);
    }

    public void definir() {
        System.out.println(this.verbo + " [" + this.partizipI + "-, " + this.perfekt[2] + "]");
        for (int i = 0; i < this.significados.length; i++) {
            significadoVer actual = this.significados[i];
            System.out.print("  " + (i+1) + ". ");
            actual.definir(this);
        }
        System.out.println();
        ArrayList<String[]> info = new ArrayList<>();
        info.add(concatenarLargo(new String[]{"Präsens"}, this.präsens));
        info.add(concatenarLargo(new String[]{"Präteritum"}, this.präteritum));
        info.add(concatenarLargo(new String[]{"Perfekt"}, this.perfekt));
        info.add(concatenarLargo(new String[]{"Konjunktiv I"}, this.konjunktivI));
        info.add(concatenarLargo(new String[]{"Konjunktiv II"}, this.konjunktivII));
        String[] imperativ = {"Imperativ", "---", this.imperativ[0], "---", this.imperativ[2], this.imperativ[1], this.imperativ[2]};
        info.add(imperativ);
        Control.arrPrint(concatenarAncho(info),3);
    }
    //</Significado>





    /**
     * Se define el Megamap: Diccionario organizado con todos los verbos.
     * Ex: megamap.get(tag) = [Hashmap con todos los verbos tipo tag. Llámese "temap"]
     * Ex. temap.get(verbo) = [Objeto Ver]
     */
    public static HashMap<String, HashMap<String, Ver>> megamap;
    public static void posprocesamiento() {
        //Creamos una lista de todos los temas/tags usados
        HashMap<String, Boolean> temas = new HashMap<String, Boolean>();
        for (String verbo : TodosLosVerbos.keySet()) {
            Ver actual = TodosLosVerbos.get(verbo);
            for (String tema : actual.tags) {
                temas.put(tema, true);
            }
        }


        megamap = new HashMap<>();
        for (String tema : temas.keySet()) {
            HashMap<String, Ver> temap = new HashMap<String, Ver>();
            megamap.put(tema, temap);
        }

        //posprocesamiento!
        for (String verbo : TodosLosVerbos.keySet()) {
            Ver actual = TodosLosVerbos.get(verbo);

            //Se llena el megamap:
            for (String tag : actual.tags) {
                megamap.get(tag).put(verbo, actual);
            }

            //Se agrega el Konjunktiv II
            if (actual.konjunktivII == null) {
                actual.setKonjunktivII();
            }

            //Se agrega perfekt
            actual.setPerfekt();

            //Se agrega el konjunktiv I. Específicamente, el Konjunktiv I präsens.
            if (actual.konjunktivI == null) {
                if (!actual.terminaConEN) {
                    throw new NumberFormatException("[" + actual.verbo + "] Error: Se debe de definir el Konjunktiv I manualmente cundo el verbo no termina con '-en'");
                }
                actual.konjunktivI = new String[]{
                        actual.verbstamm + "e",
                        actual.verbstamm + "est",
                        actual.verbstamm + "e",
                        actual.verbstamm + "en",
                        actual.verbstamm + "et",
                        actual.verbstamm + "en",
                };
                if (actual.esTrennbare) {for (String s : actual.konjunktivI) {s += " " + actual.prefijo;}}
            }


        }

    }



    /**
     * Inicializa la clase Verbos. Esto se debe ejecutar antes de usar la clase.
     */
    public static void inicializar() {
        crearVerbos();
        posprocesamiento();
    }


    /**
     * Muestra una tabla completa con toda la información sobre el verbo.
     * Se usa para revisar que los datos de la palabra se hayan agregado correctamente.
     */
    public void mostrarConjugaciones() {
        System.out.println(this.verbo + " [" + this.partizipI + "-, " + this.perfekt[2] + "]");



    }


    public static String[] concatenarLargo(String[] a, String[] b) {
        String[] out = new String[a.length + b.length];
        for (int i = 0; i < a.length; i++) {
            out[i] = a[i];
        }
        for (int j = a.length; j < out.length; j++) {
            out[j] = b[j-a.length];
        }
        return out;
    }


    /**
     * Concatena String[] para crear una String[] grande.
     *
     * Ej: Pasando { {1,2,3}, {a,b,c,d,e}, {9,8,7,6}} regresaría el siguiente string[][]
     * {1,  a,  9 }
     * {2,  b,  8 }
     * {3,  c,  7 }
     * {"", d,  6 }
     * {"", 3,  ""}
     *
     * @param in input
     * @return el String[][] grande. No se le agregan espacios.
     */
    public static String[][] concatenarAncho(ArrayList<String[]> in) {
        int maxLength = -1;
        for (String[] s : in) {
            if (s.length > maxLength) {
                maxLength = s.length;
            }
        }
        String[][] out = new String[maxLength][in.size()];

        for (int j = 0; j < in.size(); j++) {
            for (int i = 0; i < maxLength; i++) {
                String[] actual = in.get(j);
                if (i < actual.length) {out[i][j] = actual[i];} else {out[i][j] = "";}
            }
        }

        return out;
    }



    //todo: Esto tiene que irse
    //Escoge aleatoriamente 'número' verbos de una lista de verbos.
    public static ArrayList<Ver> escogerAleatorio(ArrayList<Ver> listaVerbos, int número) {
        ArrayList<Ver> lista = new ArrayList<Ver>(1); //Una lista temporánea que se utiliza para generar la String[] preguntas.
        for (int i = 0; i < número; i++) {
            int índice = (int)(listaVerbos.size()*Math.random());

            if (índice == listaVerbos.size() || índice == 0 || lista.contains(listaVerbos.get(índice))) {
                i--;
                continue;
            } // Esto se asegura de que la índice sea válida. Recuerda que la índice '0' es el verbo nulo.
            //También se asegura de que no aya entradas dobles.
            lista.add(listaVerbos.get(índice));
        }
        return lista;
    }



    /**
     * Método para hacer debugging. Se usa para revisar que las palabras se hayan agregado correctamente
     * [sin errores ortográficos u omisiones]
     * @param tema el tema que se quiere revisar.
     */
    public static void definirTema(String tema) {
        HashMap<String, Ver> temap = megamap.get(tema);
        if (temap == null) {
            System.out.println("Error: Tema no existe");
            return;
        }

        System.out.println("Presione [Enter] para avanzar la lista");

        int num = temap.keySet().size();
        int counter = 0;
        Scanner sc = new Scanner(System.in);
        for (String s : temap.keySet()) {
            counter++;
            System.out.println("(" + counter + "/" + num + ") ");
            Ver actual = temap.get(s);
            actual.definir();
            sc.nextLine();
            System.out.println("==================================================================================");
        }
    }


    /**
     * Busca el verbo en la lista de los 2000 verbos más comunes. Dice si existe (y donde), o si no se encuentra.
     * @param verbo el infinitivo del verbo que buscas.
     */
    public static void dosMilVerbos(String verbo) {
        //Este programita buscará el verbo en la lista de 2000 verbos y regresará el número, si aparece.

        System.out.print(verbo + ": ");
        for (int i = 1; i <= 9; i++) {
            String nombre = i + ".txt";
            File file;
            try {
                file = new File("/home/scifishroom/IdeaProjects/Die-Akademie/src/GranListaDeVerbos/" + nombre);
                Scanner sc = new Scanner(file);

                int counter = 1;
                while (true) {
                    if (sc.nextLine().equals(verbo)) {
                        System.out.println("i = " + i + ", counter = " + counter + "; num = " + ((250*(i-1))+counter) );
                        return;
                    }
                    counter++;
                }

            } catch (Exception e) {}
        }
        System.out.println("¡No se encontró!");

    }



    /**
     * temp. muestra la primera palabra todavía no implementada.
     */
    public static void próximaPalabra() {
        for (int i = 1; i <= 9; i++) {
            String nombre = i + ".txt";
            File file;
            try {
                file = new File("/home/scifishroom/IdeaProjects/Die-Akademie/src/GranListaDeVerbos/" + nombre);
                Scanner sc = new Scanner(file);

                int counter = 1;
                while (true) {
                    //revisamos si ya se agregó este verbo
                    String s = sc.nextLine();
                    int n = (250*(i-1))+counter;
                    int t = (int)Math.ceil((double)n/20);
                    String ß = Integer.toString(t);
                    //System.out.println(megamap.get(ß).get(s));


                    boolean foo = true; //Se asume que el verbo no se ha agregado, hasta que se demuestre que sí lo haya sido.
                    try {
                        foo = !megamap.get(Integer.toString(t)).containsKey(s);
                    } catch (Exception e) {
                        //No existe el grupo! Esto debe de permanecer vacío
                    }
                    if (foo) {
                        System.out.println("Próximo verbo: " + s + "[n=" + n + "]");
                        return;
                    }
                    counter++;
                }

            } catch (Exception e) {}
        }
        System.out.println("Todo agregado!");
    }



    /**
     * Aquí se definen todos los verbos.}
     */
    public static void crearVerbos() {
        String[] T;

        //Habrá 100 grupos, cada uno con 20 verbos. Estoy 100.01% seguro que los agregaré todos




        T = new String[]{"0"}; //lugar de almacenamiento para los verbos
        Ver anwenden = new Ver("an|wenden", T);
        anwenden.addTag("usar");
        anwenden.addSignificado("aplicar; emplear [un método; una regla]", null, new String[][]{
                {"Das theorie wendet in diesem System nicht an.", "El teorema no aplica en este sistema. "},
                {"Wir werden Ockhams Rasiermesser anwenden.", "Aplicaremos la navaja de Occam."}
        }, new String[]{
                "Se usa de manera similar a 'gelten', que significa 'tener validez', pero no son sinónimos.",
                "No se confunde con 'verwenden', que significa 'usar'. "
        });


        Ver wohnen = new Ver("wohnen", T);
        wohnen.addSignificado("vivir", "wohnen in +Dat", new String[][]{
                {"Ich wohne im dritten Stock.", "Vivo en el tercer piso"},
                {}
        }, new String[]{
                "Muy similar a 'leben', pero normalmente solo se usa para indicar el hogar físico en el que se vive. ",
                "'Vivo en alemania' sería con 'leben', no con 'wohnen'."
        });


        Ver ansehen = new Ver("an|sehen", T);


        Ver anfangen = new Ver("an|fangen", T);


        Ver ankommen = new Ver("an|kommen", T);


        Ver legen = new Ver("legen", T);
        legen.addTag("colocar");

        Ver liegen = new Ver("liegen", T);
        liegen.addTag("colocar");

        Ver hängen = new Ver("hängen", T);
        hängen.addTag("colocar");

        //Ver hängen2 = new Ver("hängen", T);
        //hängen2.addTag("colocar");

        Ver setzen = new Ver("setzen", T);
        setzen.addTag("colocar");

        Ver sitzen = new Ver("sitzen", T);
        sitzen.addTag("colocar");







        //todo: Marcador: comienzo de los verbos.
        T = new String[]{"1"}; //Revisado 1 Junio 2021


        Ver werden = new Ver("werden", T);
        werden.setPräsens(new String[]{"werde", "wirst", "wird", "werden", "werdet", "werden"});
        werden.setPräteritum(new String[]{"wurde", "wurdest", "wurde", "wurden", "wurdet", "wurden"});
        werden.setImperativ(IconE_Imperativ);
        werden.setPartizipPerfekt("geworden");
        werden.setHilfsverbSein();
        werden.addTag("hilfsverb");
        werden.setKonjunktivII(new String[]{"würde", "würdest", "würde", "würden", "würdet", "würden"});
        werden.addSignificado("Verbo auxiliar que indica futuro.", null, new String[][]{
                {"Ich wird erfolgreich", "Seré exitoso"}
        }, new String[]{"El verbo más común del alemán, por mucho"});


        Ver einen = new Ver("einen", T);
        einen.setPräsens(PsinE_Präsens);
        einen.setPräteritum();
        einen.setImperativ(new String[]{"eine", "eint", "einen"});
        einen.setPartizipPerfekt("geeint");
        einen.addSignificado("unir; unirse [dicho de personas]", null, new String[][]{
                {"Er hat die Arbeiter geeint.","El unió los trabajadores."},
                {"Die Arbeiter haben sich geeint.","Los trabajadores se han unido."}
        }, new String[]{
                "No se confunde con 'vereinen'. 'einen' siempre es dicho de un grupo de personas,",
                "mientras que 'vereinen' se puede usar con cualquier objeto. ",
                "Por ejemplo, 'unirse a un grupo' sería con 'vereinen', no con 'einen'. "
        });


        Ver verfahren = new Ver("verfahren", T);
        verfahren.setPräsens("verfähr");
        verfahren.setPräteritum("verfuhr", vacío);
        verfahren.setImperativ(IsinE_Imperativ);
        verfahren.setPartizipPerfekt("verfahren");
        verfahren.setHilfsverbSein();
        verfahren.addSignificado("perderse [en un vehículo]", "sich verfahren", new String[][]{
                {"Der Bus ist sich auf der Schnellstraße verfahren.","El autobús se perdió en la carretera."}
        }, new String[]{
                "No se confunde con 'verlaufen', que se usa cuando alguien se ha perdido a pie. ",
                "'perderse en la estación de trenes' iría con 'verlaufen'."
        });
        verfahren.addSignificado("actuar; proceder", "+sein", new String[][]{
                {"Wie sollen wir vor dieser Situation verfahren?", "¿Cómo debemos actuar ante esta situación?"}
        }, new String[]{
                "Muy similar a 'vorgehen', pero no son sinónimos;","'vorgehen' significa proceder, con respeto a un procedimiento ya establecido."
        });


        Ver haben = new Ver("haben", T);
        haben.addTag("hilfsverb");
        haben.setPräsens(new String[]{"habe", "hast", "hat", "haben", "habt", "haben"});
        haben.setPräteritum("hat", noVacío);
        haben.setImperativ(IsinE_Imperativ);
        haben.setPartizipPerfekt("gehabt");
        haben.setKonjunktivII(new String[]{"hätte", "hättest", "hätte", "hätten", "hättet", "hätten"});
        haben.addSignificado("tener [algo; un objeto]", null, new String[][]{
                {"Ich habe einen Hund", "Tengo un perro"}
        }, new String[]{
                "No se confunde con 'müssen', que significa 'tener que'. ", "'Tengo que irme' sería 'Ich muss gehen'"
        });
        haben.addSignificado("Hilfsverb para la gran mayoría de los verbos, utilizado para mostrar conjugaciones temporales", null, new String[][]{
                {"Ich habe ein neues Auto gekauft", "He comprado un coche nuevo"},
                {"Ich hätte ein neues Auto gekauft, aber ich hatte nicht genug Geld. ", "Hubiera comprado un coche nuevo, pero no tenía suficiente dinero. "}
        }, new String[]{
                "De los verbos más comunes del alemán"
        });


        Ver können = new Ver("können", T);
        können.addTag("modalverb");
        können.setPräsens(new String[]{"kann", "kannst", "kann", "können", "könnt", "können"});
        können.setPräteritum("konn", noVacío);
        können.setPartizipPerfekt("gekonnt");
        können.setImperativ(true);
        können.setKonjunktivII(new String[]{"könnte", "könntest", "könnte", "könnten", "könntet", "könnten"});
        //können no tiene imperativ
        können.addSignificado("poder [hacer algo]", "Modalverb", new String[][]{
                {"Ich kann das nicht glauben!", "No lo puedo creer"}
        }, new String[]{"Como modalverb, oraciones con 'können' normalmente tienen dow verbos distintos. "});


        Ver müssen = new Ver("müssen", T);
        müssen.addTag("modalverb");
        müssen.setPräsens(new String[]{"muss", "musst", "muss", "müssen", "müsst", "müssen"});
        müssen.setPräteritum("muss", noVacío);
        müssen.setImperativ(false);
        müssen.setPartizipPerfekt("gemusst");
        müssen.setKonjunktivII(new String[]{"müsste", "müsstest", "müsste", "müssten", "müsstet", "müssten"});
        müssen.addSignificado("Tener que [hacer algo]", "Modalverb", new String[][]{
                {"Ich muss gehen, weil ich bald einen Termin habe. ", "Tengo que irme, porque pronto tengo una cita. "},
                {"Warum müssen wir Obst, essen?", "¿Porqué tenemos que comer fruta?"}
        }, new String[]{
                "No se confunde con 'haben', que significa 'tener algo'. 'Tengo fruta' sería 'Ich habe Obst'"
        });


        Ver sein = new Ver("sein",T);
        sein.setPräsens(new String[]{"bin", "bist", "ist", "sind", "seid", "sind"}); //Präsens irregular, agregamos los 6 individualmente
        sein.setPräteritum("war", vacío);
        sein.setPartizipPerfekt("gewesen"); //Siempre se agrega manualmente
        sein.setHilfsverbSein(); //Siempre asumimos que el hilfsverb es "haben". Si no lo es, usamos este comando.
        sein.setKonjunktivII(new String[]{"wäre", "wärest", "wäre", "wären", "wäret", "wären"});
        sein.addTag("hilfsverb");
        sein.setImperativ(new String[]{"sei", "seid", "seien"}); //imperativ irregular
        sein.setPartizipI("seiend");
        sein.setKonjunktivI(new String[]{"sei", "seiest", "sei", "seien", "seiet", "seien"});
        sein.addSignificado("ser; estar", null, new String[][]{
                {"Ich bin hier", "Estoy aquí"},
                {"Ich bin da gefahren", "He viajado ayá"}
        }, new String[]{"De los verbos más comunes del alemán."});
        sein.addSignificado("Hilfsverb para los verbos que indican movimiento", null, new String[][]{
                {"Ich bin gegangen","Me fuí"},
                {"Für unseren Urlaub, wir sind nach Spanien gereist.", "Para nuestras vacaciones, viajamos a España."}
        }, new String[]{
                "Casi siempre actúa sobre verbos que expresan movimiento"
        });


        Ver machen = new Ver("machen", T);
        machen.setPräsens(PsinE_Präsens);
        machen.setPräteritum();
        machen.setImperativ(IsinE_Imperativ);
        machen.setPartizipPerfekt("gemacht");
        machen.addSignificado("Hacer [un objeto, un proceso]", null, new String[][]{
                {"Ich werde mich einen Kaffee machen. ", "Me haré un café. "},
                {"Er macht seine Karriere an einer Universität", "El está haciendo su carrera en una universidad. "}
        }, new String[]{
                "No se confunde con 'tun' -  Los dos significan 'hacer algo', ",
                "pero 'tun' se usa cuando no hay un objeto especifico que se haya hecho. ",
                "'No hay nada que hacer' sería 'Es gibt nichts zu tun'."
        });


        Ver geben = new Ver("geben", T);
        geben.setPräsens("gib");
        geben.setPräteritum("gab", vacío);
        geben.setImperativ(new String[]{"gib", "gebt", "geben"});
        geben.setPartizipPerfekt("gegeben");
        geben.setKonjunktivII(new String[]{"gäbe", "gäbest", "gäbe", "gäben", "gäbet", "gäben"});
        geben.addSignificado("dar [algo a alguien]", null, new String[][]{
                {"Könntest du mir bitte ein Wasserglas geben?","¿Me podrías dar un vaso de agua, por favor?"},
                {"Er gab mir einen guten Ratschlag. ","El me dió un buen consejo."}
        },null);
        geben.addSignificado("haber", "es gibt + Akk", new String[][]{
                {"Es gibt eine Fliege in meine Suppe. ", "Hay una mosca en mi sopa"},
                {"Was gibt es Neues?", "¿Qué hay de nuevo?"},
                {"Wird es Leute im Büro geben?", "¿Habrá gente en la oficina?"}
        }, new String[]{
                "En el aleman, no existe un verbo que signifique 'haber'; se tiene que usar la construcción 'es gibt + [cosa]'."
        });


        Ver sagen = new Ver("sagen", T);
        sagen.setPräsens(PsinE_Präsens);
        sagen.setPräteritum();
        sagen.setImperativ(IsinE_Imperativ);
        sagen.setPartizipPerfekt("gesagt");
        sagen.addTag("expresarse");
        sagen.addSignificado("decir [algo, +Akk; a alguien + Dat]", null, new String[][]{
                {"Er hat mir gesagt, dass er nicht kommen werde", "El me dijo que no vendrá"},
                {"Ich sage, dass wir ins Kino gehen sollen.", "Yo digo que deberíamos ir al cine"},
        }, new String[]{
                "Esta palabra es muy general, y su significado se sobrepone con el de varios otros verbos. ",
                "'Decirle a alguien' = 'sagen zu jemanden'",
                "No se confunde con 'sprechen' que significa 'hablar'. 'Hablamos de la película' sería 'wir haben von dem Film gesprochen'"
        });


        Ver klicken = new Ver("klicken", T);
        klicken.setPräsens(PsinE_Präsens);
        klicken.setPräteritum();
        klicken.setImperativ(IsinE_Imperativ);
        klicken.addTag("Informática");
        klicken.setPartizipPerfekt("geklickt");
        klicken.addSignificado("hacer click; picar [con un maus]", "auf + Akk", new String[][]{
                {"Klicken sie auf die Schaltfläche", "Piquen el botón."}
        } ,new String[]{
                "No se confunde con 'drücken', que se utiliza cuando el botón es algo físico (ej. un botón en un elevador)",
        });


        Ver sehen = new Ver("sehen", T);
        sehen.setPräsens("sieh");
        sehen.setPräteritum("sah", vacío);
        sehen.setImperativ(new String[]{"sieh", "seht", "sehen"});
        sehen.setPartizipPerfekt("gesehen");
        sehen.setKonjunktivII(new String[]{"sähe", "sähest", "sähe", "sähen", "sähet", "sähen"});
        sehen.addSignificado("ver", null, new String[][]{
                {"Ich sah einen Vogel im Baum.","Ví un pájaro en el árbol. "},
                {"Wir haben uns schon lange nicht mehr gesehen!", "¡No nos hemos visto en mucho tiempo!"}
        }, new String[]{
                "No se confunde con 'ansehen' (mirar)."
        });


        Ver unternehmen = new Ver("unternehmen", T);
        unternehmen.setPräsens("unternimm");
        unternehmen.setPräteritum("unternahm", vacío);
        unternehmen.setImperativ(new String[]{"unternimm", "unternehmt", "unternehmen"});
        unternehmen.setPartizipPerfekt("unternommen");
        unternehmen.addSignificado("hacer [algo]; realizar [un plan]", null, new String[][]{
                {"Wir sollten einmal zusammen etwas unternehmen.", "Deberíamos de hacer algo unos de estos días."},
                {"Sie haben eine Reise unternommen.", "Han hecho un viaje."}
        }, new String[]{
                "Muy similar a 'machen' y 'tun'. En general, se usa 'unternehmen' cuando la cosa hecha es algo que ha sido planeado desde antes. ",
                "En general, yo recomendaría usar 'machen' o 'tun', ya que son mucho más comunes, y las tres comparten significado. "
        });



        Ver wissen = new Ver("wissen", T);
        wissen.setPartizipPerfekt("gewusst");
        wissen.setPräsens(new String[]{"weiß", "weißt", "weiß", "wissen", "wisst", "wissen"});
        wissen.setPräteritum("wuss", noVacío);
        wissen.setImperativ(new String[]{"wisse", "wisst", "wissen"});
        wissen.setKonjunktivII(new String[]{"wüsste", "wüsstest", "wüsste", "wüssten", "wüsstet", "wüssten"});
        wissen.addSignificado("saber [algo]", null, new String[][]{
                {"Weißt du, wann der Film fängt an?", "¿Sabes cuándo empieza la película?"},
                {"Ich habe das nicht gewusst!", "Eso no lo sabía!"}
        }, null);


        Ver tun = new Ver("tun", T);
        tun.setPartizipPerfekt("getan");
        tun.setPräsens(new String[]{"tue", "tust", "tut", "tun", "tut", "tun"});
        tun.setPräteritum(new String[]{"tat", "tatest", "tat", "taten", "tatet", "taten"});
        tun.setImperativ(new String[]{"tu", "tut", "tun"});
        tun.setKonjunktivI(new String[]{"tue", "tuest", "tue", "tuen", "tuet", "tuen"});
        tun.setKonjunktivII(new String[]{"täte", "tätest", "täte", "täten", "tätet", "täten"});
        tun.addSignificado("hacer; tomar [una acción]", null, new String[][]{
                {"Kann ich etwas für Sie tun?", "¿Puedo hacer algo por Usted?"},
                {"Heute habe ich gar nichts getan", "Hoy no he hecho absolutamente nada."}
        }, new String[]{
                "No se confunde con 'machen' (aunque los dos verbos son muy similares). 'machen' se usa cuando lo hecho es algo físico.",
                "'Hice mi tarea' sería con 'machen', no con 'tun'."
        });


        Ver finden = new Ver("finden", T);
        finden.setPräsens(PconE_Präsens);
        finden.setPräteritum(new String[]{"fand", "fandest", "fand", "fanden", "fandet", "fanden", });
        finden.setImperativ(new String[]{"find", "findet", "finden"});
        finden.setPartizipPerfekt("gefunden");
        finden.setKonjunktivII(new String[]{"fände", "fändest", "fände", "fänden", "fändet", "fänden"});
        finden.addSignificado("encontrar", null, new String[][]{
                {"Ich habe das Bch gefunden.", "Encontré el libro."}
        }, null);
        finden.addSignificado("opinar; hacerse [de una opinión]", null, new String[][]{
                {"Ich finde den Preis ein bisschen hoch.", "El precio se me hace un poco caro."},
                {"Das finde ich unglaublich!", "¡Eso se me hace impensable!"},
                {"Ich finde, dass Kaffee besser ohne Milch schmeckt.", "Opino que el café sabe mejor sin leche."}
        }, new String[]{
                "Se usa frecuentemente para expresar opiniones; 'Ich finde das toll!'"
        });


        Ver wählen = new Ver("wählen", T);
        wählen.setPräsens(PsinE_Präsens);
        wählen.setPräteritum();
        wählen.setImperativ(IsinE_Imperativ);
        wählen.setPartizipPerfekt("gewählt");
        wählen.addSignificado("escoger; elegir", null, new String[][]{
                {"Sie haben den neuen Direktor gewählt.", "Eligieron al nuevo director."},
                {"ich kann nicht wählen!", "¡No puedo escoger!"}
        }, null);


        Ver lassen = new Ver("lassen", T);
        lassen.setPräsens(new String[]{"lasse", "lässt", "lässt", "lassen", "lasst", "lassen"});
        lassen.setPräteritum(new String[]{"ließ", "ließt", "ließ", "ließen", "ließt", "ließen"});
        lassen.setImperativ(IsinE_Imperativ);
        lassen.setPartizipPerfekt("gelassen");
        lassen.setKonjunktivII(new String[]{"ließe", "ließest", "ließe", "ließen", "ließet", "ließen"});
        lassen.addSignificado("dejar; permitir", "lassen; sich lassen", new String[][]{
                {"Ich lasse dich nicht zum Konzert gehen.", "No te dejo ir al concierto."},
                {"Lassen Sie seine Schuhe am Eingang.","Dejen los zapatos a la entrada."},
                {"Das Problem lässt sich nicht lösen.", "El problema no se deja resolver. "}
        }, new String[]{
                "Este verbo es de los pocos al cual se le puede añadir otro verbo sin usar 'zu'. "
        });
        lassen.addSignificado("mandar [hacer algo]; encargar; pedir [que se haga algo]", null, new String[][]{
                {"Ich will mein Auto reparieren lassen.", "Quiero mandar mi coche a reparar."},
                {"Ich lasse ihnen die Küche putzen.", "Les encargo que limpien la cocina."}
        }, new String[]{
                "Este verbo es de los pocos al cual se le puede añadir otro verbo sin usar 'zu'. "
        });


        Ver gehen = new Ver("gehen", T);
        gehen.setPartizipPerfekt("gegangen");
        gehen.setPräsens(PsinE_Präsens);
        gehen.setPräteritum("ging", vacío);
        gehen.setImperativ(IsinE_Imperativ);
        gehen.setHilfsverbSein();
        gehen.setKonjunktivII(new String[]{"ginge", "gingest", "ginge", "gingen", "ginget", "gingen"});
        gehen.addSignificado("ir / andar; caminar", "zu,nach + Dat / in + Akk", new String[][]{
                {"Ich gehe nach Hause.", "Voy a casa."},
                {"Er ist zur Schule gegangen", "El se fue a la escuela. "},
                {"Du solltest zum Artz gehen.", "Deberías ir al hospital. "},
                {"Wir gehen ins Kino", "Vamos al cine."}
        }, new String[]{
                "Se usa 'nach' cuando se puede omitir el artículo.",
                "El use de 'in' implica que el destino es dentro del lugar (Wasser geht ins Glas.)",
                "El uso de 'zu' solo implica que vas al lugar. (Wir gehen zum Konzert)",
                "No se confunde con 'fahren' [conducir], que se usa cuando se usa un vehículo (ej. una bici, un coche, etc.)"
        });
        gehen.addSignificado("ir [algo]", null, new String[][]{
                {"Wie geht es ihnen?", "¿Cómo les va?"},
                {"Alles geht gut", "Todo va bien."}
        }, new String[]{"Saludo normal."});


        Ver verwenden = new Ver("verwenden", T);
        verwenden.setPräsens(PconE_Präsens);
        verwenden.setPräteritum("verwand", noVacío);
        verwenden.setImperativ(new String[]{"verwend", "verwendet", "verwenden"});
        verwenden.setPartizipPerfekt("verwandt");
        verwenden.addSignificado("usar [algo]", null, new String[][]{
                {"Wie wird dieses Werkzeug verwendet?", "¿Cómo se usa esta herramienta?"},
                {"Ich habe dieses Lehrbuch nie verwendet", "Nunca usé este libro de texto."}
        }, new String[]{
                "Sinónimo de 'benutzen', pero 'verwenden' es más formal y bonito."
        });
        verwenden.addTag("usar");





        T = new String[]{"2"};


        Ver wollen = new Ver("wollen", T);
        wollen.setPräsens(new String[]{"will", "willst", "will", "wollen", "wollt", "wollen"});
        wollen.setPräteritum();
        wollen.setImperativ(false);
        wollen.setPartizipPerfekt("gewollt");
        wollen.setKonjunktivII(new String[]{"wollte", "wolltest", "wollte", "wollten", "wolltet", "wollten"});
        wollen.addSignificado("querer [hacer algo; tener algo]", "Modalverb", new String[][]{
                {"Ich will eine Torte", "Quiero un pastel."},
                {"Willst du einen Film mit mir sehen?", "¿Quieres ver una película conmigo?"},
                {"Ich wollte immer eine Katze.","Siempre quise un gato."}
        }, new String[]{
                "El konjunktiv II de 'wollen' y 'sollen' es igual que el Präteritum. Sin embargo, ",
                "no se suele usar la construcción 'würden' para expresar el konjunktiv II con estos dos verbos. "}
        );


        Ver leben = new Ver("leben", T);
        leben.setPartizipPerfekt("gelebt");
        leben.setPräsens(PsinE_Präsens);
        leben.setPräteritum();
        leben.setImperativ(IsinE_Imperativ);
        leben.addSignificado("vivir", "leben in +Dat", new String[][]{
                {"Ich wohne in Deutschland.", "Vivo en alemania."},
                {"Er lebt für die Kunst.", "El vive para el arte."},
                {"Ich habe nie allein gelebt.", "Nunca he vivido solo"}
        }, new String[]{
                "'leben' comparte significado con 'wohnen', pero 'leben' es mucho más general, y siempre funciona."
        });


        Ver verscheiden = new Ver("verscheiden", T);
        verscheiden.setPräsens(PconE_Präsens);
        verscheiden.setPräteritum("verschied", vacío);
        verscheiden.setHilfsverbSein();
        verscheiden.setImperativ(IconE_Imperativ);
        verscheiden.setPartizipPerfekt("verschieden");
        verscheiden.addSignificado("fallecer", null, new String[][]{
                {"Sie verschied nach langer Krankenheit.", "Falleció después de una enfermedad larga."}
        }, new String[]{
                "Sinónimo de 'versterben', aunque se usa menos. Similar a 'sterben', que significa 'morir'.",
        });


        Ver kommen = new Ver("kommen", T);
        kommen.setHilfsverbSein();
        kommen.setPräsens(PsinE_Präsens);
        kommen.setPräteritum("kam", vacío);
        kommen.setImperativ(IsinE_Imperativ);
        kommen.setPartizipPerfekt("gekommen");
        kommen.setKonjunktivII(new String[]{"käme", "kämest", "käme", "kämen", "kämet", "kämen"});
        kommen.addSignificado("venir", null, new String[][]{
                {"Wir kommen aus dem Kino.", "Venimos del cine. "},
                {"Alles sind zur Party gekommen.", "Todos vinieron a la fiesta."}
        }, new String[]{
                "No se confunde con 'ankommen', que significa 'llegar'."
        });


        Ver erstellen = new Ver("erstellen", T);
        erstellen.setPräsens(PsinE_Präsens);
        erstellen.setPräteritum();
        erstellen.setImperativ(IsinE_Imperativ);
        erstellen.setPartizipPerfekt("erstellt");
        erstellen.addSignificado("crear, construir", "informática", new String[][]{
                {"Erstellen Sie eine Datei auf dem Computer", "Cree un archivo en la computadora. "},
        }, new String[]{
                "Muy similar a 'bauen' y 'schaffen'. pero solo suele usarse en contextos de informática",
        });


        Ver erhalten = new Ver("erhalten", T);
        erhalten.setPräsens("erhäl");
        erhalten.setPräteritum("erhielt", vacío);
        erhalten.setPartizipPerfekt("erhalten");
        erhalten.setImperativ(new String[]{"erhalt", "erhaltet", "erhalten"});
        erhalten.addSignificado("recibir; conseguir", null, new String[][]{
                {"Er erhielt das Fahrrad als Geburtstagsgeschenk.", "El recibió la bicicleta como regalo de cumpleaños. "}
        }, new String[]{
                "Significa lo mismo que 'bekommen' y 'kriegen', pero no se usa tan frecuentemente, y no siempre se puede usar.",
                "Por ejemplo, puedes decir 'Kopfschmerzen bekommen', pero no 'Kopfschmerzen erhalten'. "
        });
        erhalten.addSignificado("conservar [mantener en buen estado]", null, new String[][]{
                {"Der Kathedrale ist sehr gut erhalten", "La catedral está muy bien conservada. "},
        }, new String[]{
                "No se confunde con 'halten', que significa 'mantener'. "
        });


        Ver sollen = new Ver("sollen", T);
        sollen.addTag("modalverb");
        sollen.setPräsens(new String[]{"soll", "sollst", "soll", "sollen", "sollt", "sollen"});
        sollen.setPräteritum();
        sollen.setImperativ(false);
        sollen.setPartizipPerfekt("gesollt");
        sollen.setKonjunktivII(new String[]{"sollte", "solltest", "sollte", "sollten", "solltet", "sollten"});
        sollen.addSignificado("deber [hacer algo]", "modalverb", new String[][]{
                {"Wir sollten in Kino gehen. ", "Deberíamos ir al cine."},
                {"Ich sollte nicht so viel essen.", "No debí de haber comido tanto."}
        }, new String[]{
                "El konjunktiv II de 'sollen' y 'wollen' es igual que el Präteritum. Sin embargo, ",
                "no se suele usar la construcción 'würden' para expresar el konjunktiv II con estos dos verbos. "
        });


        Ver ändern = new Ver("ändern", T);
        ändern.setPräsens(new String[]{"ändere", "änderst", "ändert", "ändern", "ändert", "ändern"});
        ändern.setPräteritum("ändert", noVacío);
        ändern.setImperativ(new String[]{"ändere", "ändert", "ändern"});
        ändern.setPartizipPerfekt("geändert");
        ändern.setKonjunktivI(new String[]{"ändere", "änderest", "ändere", "ändern", "ändert", "ändern"});
        ändern.addSignificado("cambiar", "ändern; sich ändern", new String[][]{
                {"Du hast dich sehr geändert.", "Has cambiado mucho."},
                {"Er änderte seine Meinung nach der Präsentation.", "Cambió de opinión tras verl la presentación."}
        }, null);


        Ver stellen = new Ver("stellen", T);
        stellen.addTag("colocar");
        stellen.setPräsens(PsinE_Präsens);
        stellen.setPräteritum();
        stellen.setImperativ(IsinE_Imperativ);
        stellen.setPartizipPerfekt("gestellt");
        stellen.addSignificado("poner; colocar", null, new String[][]{
                {"Stell die Flasche auf den Tisch.", "Pon la botella en las mesa."},
                {"Ich möchte dich eine Frage stellen.", "Me gustaría ponerte una una pregunta."}
        }, new String[]{
                "Véase 'colocar'"
        });


        Ver fragen = new Ver("fragen", T);
        fragen.setPräsens(PsinE_Präsens);
        fragen.setPräteritum();
        fragen.setImperativ(IsinE_Imperativ);
        fragen.setPartizipPerfekt("gefragt");
        fragen.addSignificado("preguntar", "fragen + Akk", new String[][]{
                {"Ich muss dich fragen, wo schneidest du deine Haare?", "Te tengo que preguntar, ¿dónde te cortas el pelo?"},
                {"Ich frage mich, wohin er gegangen ist.", "Me pregunto a donde se habrá ido."}
        }, null);


        Ver enthalten = new Ver("enthalten", T);
        enthalten.setPräsens("enthäl");
        enthalten.setPräteritum("enthielt", noVacío);
        enthalten.setImperativ(IconE_Imperativ);
        enthalten.setPartizipPerfekt("enthalten");
        enthalten.addSignificado("contener", null, new String[][]{
                {"Diese Truhe enthält einen großen Schatz.", "Este cofre contiene un gran tesoro."}
        }, null);
        enthalten.addSignificado("abstener", "sich enthalten", new String[][]{
                {"Ich habe mich der Stimme enthalten", "Me abstuve de votar."},
        }, null);


        Ver stehen = new Ver("stehen", T);
        stehen.addTag("colocar");
        stehen.setPräsens(PsinE_Präsens);
        stehen.setPräteritum(new String[]{"stand", "standst", "stand", "standen", "standet", "standen"});
        stehen.setImperativ(IsinE_Imperativ);
        stehen.setPartizipPerfekt("gestanden");
        stehen.addSignificado("estar [parado]; estar [en un estado]", null, new String[][]{
                {"Die Flache steht auf dem Tisch.", "La botella está sobre la mesa."},
                {"Der Tank steht under Druck.", "El tanque está bajo presión."},
                {"Es steht im Vertrag.", "Está en el contrato. "},
                {"Ich stehe hinter dir!", "¡Te apoyo!"}
        }, new String[]{
                "'stehen' es de esas palabras que tienen mil significados diferentes."
        });


        Ver meinen = new Ver("meinen", T);
        meinen.setPräsens(PsinE_Präsens);
        meinen.setPräteritum();
        meinen.setImperativ(IconE_Imperativ);
        meinen.setPartizipPerfekt("gemeint");
        meinen.addSignificado("decir; creer; opinar", null, new String[][]{
                {"Was meinst du über das Thema 'erneuerbare Energie'?", "¿Qué opinas del tema 'energía renovable'?"},
                {"Meinst du das im Ernst?", "¿Lo dices enserio?"}
        }, new String[]{
                "Comparte mucho significado con palabras como 'sagen', 'glauben', y 'äußern', pero no siempre son intercambiables."
        });


        Ver führen = new Ver("führen", T);
        führen.setPräsens(PsinE_Präsens);
        führen.setPräteritum();
        führen.setImperativ(IconE_Imperativ);
        führen.setPartizipPerfekt("geführt");
        führen.addSignificado("dirigir; guiar [a algún lado]; llevar", null , new String[][]{
                {"Er führt uns durch den Wald. ", "El no dirigió a través del bosque. "},
                {"Diese Straße führt nach München.", "Esta carretera te lleva a München. "},
                {"Der Plan würde nur zu Probleme führen.", "El plan solo llevaría a problemas. "}
        }, new String[]{
                "Muy similar a 'leiten', aunque 'führen' suele se más general."
        });


        Ver sondern = new Ver("sondern", T);
        sondern.setPräsens(new String[]{"sondere", "sonderst", "sondert", "sondern", "sondert", "sondern"});
        sondern.setPräteritum(new String[]{"sonderte", "sondertest", "sonderte", "sonderten", "sondertet", "sonderten"});
        sondern.setImperativ(noTerminaConEN);
        sondern.setPartizipPerfekt("gesondert");
        sondern.setKonjunktivI(new String[]{"sondere", "sonderest", "sondere", "sondern", "sondert", "sondern"});
        sondern.addSignificado("separar; apartar [crear divisiones dentro de un grupo de personas u objetos]", null, new String[][]{
                {"Sie haben die toten Pflanzen von der gesunden sondern. ", "Han separado las plantas muertas de las sanas. "}
        }, new String[]{
                "Palabra vieja; Normalmente, se usa 'trennen', que significa lo mismo."
        });


        Ver arbeiten = new Ver("arbeiten", T);
        arbeiten.setPräsens(PconE_Präsens);
        arbeiten.setPräteritum();
        arbeiten.setImperativ(IconE_Imperativ);
        arbeiten.setPartizipPerfekt("gearbeitet");
        arbeiten.addSignificado("trabajar", "arbeiten als + ...; arbeiten an +Dat", new String[][]{
                {"Er arbeitet als Profesor an einer Universität.", "El trabaja como profesor en una universidad."},
                {"Woran arbeitest du?", "¿En qué trabajas?"},
                {"Wir sind zusammen an dieser Aufgabe arbeiten.", "Estamos trabajando juntos en esta tarea."}
        }, null);


        Ver halten = new Ver("halten", T);
        halten.setPräsens("hält");
        halten.setPräteritum(new String[]{"hielt", "hieltst", "hielt", "hielten", "hieltet", "hielten"});
        halten.setImperativ(IconE_Imperativ);
        halten.setPartizipPerfekt("gehalten");
        halten.addSignificado("parar; detener / aguantar", null, new String[][]{
                {"Halte da!", "¡Detente ahí!"},
                {"Der Bus hielt nicht.", "El autobús no se detuvo."},
                {"Ihre Freundschaft hat nicht lange gehalten", "Su amistad no aguantó mucho."},
                {"Mit diesen Vorkehrungen wird die Brücke noch einige Jahre halten.", "Con estos arreglos aguantará el puente varios años más."}
        }, new String[]{
                "'halten' es de esas palabras que tienen un montón de significados y usos. ",
                "No te sorprendas si vez el verbo usado en otro contexto."
        });


        Ver nehmen = new Ver("nehmen", T);
        nehmen.setPräsens("nimm");
        nehmen.setPräteritum("nahm", vacío);
        nehmen.setImperativ(new String[]{"nimm", "nehmt", "nehmen"});
        nehmen.setPartizipPerfekt("genommen");
        nehmen.addSignificado("tomar; coger / llevar", "nehmen + Akk", new String[][]{
                {"Sie hat seine Medizin genommen.", "Se tomó su medicina."},
                {"Ich nahm ein Taxi", "Tomé un taxi."},
                {"Nimm den Kuchen.", "Llevate el pastel."}
        }, new String[]{
                "No se confunde con 'trinken', que significa 'tomar [un líquido]'. "
        });


        Ver bringen = new Ver("bringen", T);
        bringen.setPräsens(PsinE_Präsens);
        bringen.setPräteritum("brach", noVacío);
        bringen.setImperativ(IsinE_Imperativ);
        bringen.setPartizipPerfekt("gebracht");
        bringen.setKonjunktivII(new String[]{"brächte", "brächtest", "brächte", "brächten", "brächtet", "brächten"});
        bringen.addSignificado("llevar [algo a un lugar]; traer [algo a un lugar]", null, new String[][]{
                {"Er hat den Koffer zum Bahnhof gebracht.", "Se llevó la maleta a la estación de trenes. "},
                {"Ich bringe eine gute Nachricht!", "¡Traigo buenas noticias!"},
                {"Soll ich dich nach Hause bringen?", "¿Te llevo a la casa?"}
        }, null);


        Ver helfen = new Ver("helfen", T);
        helfen.setPräsens("hilf");
        helfen.setPräteritum("half", vacío);
        helfen.setImperativ(new String[]{"hilf", "helft", "helfen"});
        helfen.setPartizipPerfekt("geholfen");
        helfen.addSignificado("ayudar", "helfen + Dat", new String[][]{
                {"Kann ich Ihnen helfen?", "¿Puedo ayudarle?"},
                {"Dies hat mir sehr geholfen.", "Esto me ha ayudado mucho."},
                {"Hilf mir!", "¡Ayúdame!"}
        }, null);
        helfen.addSignificado("servir [a alguien]", "helfen + Dat", new String[][]{
                {"Hat dir die Medizin geholfen?", "¿Te ha servido la medicina?"},
        }, new String[]{
                "Muy similar a 'dienen', que también significa 'servir'.",
                "Se diferencian en que 'helfen' requiere sujeto dativo [servir a alguien], mientras que 'dienen' solo significa 'servir'.",
                "'El cable no sirve' sería con 'dienen', no con 'helfen'. "
        });

        //todo: Marcador: Fin de los verbos

        /**
         * Lista (no oficial) de TAGS utilizados:
         *  - expresar
         *  - investigar
         *  - modalverb
         *  - hilfsverb
         *  - tecnología
         *  - usar
         *  - ver
         */

        /**
         * Palabras "ver". No se si se necesite este grupo.
         *  - sehen     ver
         *  - ansehen   mirar. Versión formal de anschauen
         *  - gucken    ???
         *  - schauen   no se usa mucho
         *  - anschauen  sinónimo de ansehen.
         */

        /**
         * Palabras: Usar
         *
         *  - gelten: Tener validez; ser válido.
         *  - nutzen
         *  - benutzen
         *  - verwenden: Igual que benutzen, pero más bonito y formal.
         *  - anwenden:
         *  - gelten: Tener validez; ser válido.
         *  - gebrauchen
         *


        /** PALABRAS: INGRESO DE INFORMACIÓN
         * forschen:
         * erforschen: Explorar
         * untersuchen: Analisar; Examinar; Estudiar.
         * analysieren: Analisar
         * erkunden: explorar
         * ermitteln:
         * prüfen: Checar; comprobar; verificar
         * überprüfen:
         * nachprüfen:
         * studieren: Estudiar
         * abfragen
         * entdecken: Descubrir
         * beobachten: Observar
         * bemerken: Notar
         */

        /** PALABRAS: Expresarse
         * sagen
         * sprechen
         * erzählen
         * äußern
         * meinen
         * ergänzen
         * erklären
         * behaupten
         * begründen
         * betonen
         */

        /**
         * PALABRAS: Separar:
         *  - sondern
         *          "separar; apartar. Es palabra desusada."
         *
         * https://www.reddit.com/r/German/comments/hgqhz4/unterschied_zwischen_aufteilen_verteilen/
         *
         *  - teilen:       ?
         *  - aufteilen:    repartir; distribuir (algo en cantidades iguales a todos lo que lo reciban. ej. cubiertos; cuadernos; exámenes, etc. )
         *          "Das letzte Stück Kuchen unter Geschwistern war aufgeteilt.", "El ultimo trozo de pastel fue compartido entre los hermanos."
         *          "Die schüler wurden in vier Gruppen aufgeteilt.", "Los alumnos fueron divididos en cuatro grupos."
         *          "Hilf mir das Besteck an die Gäste aufzuteilen.", "Ayúdame a repartir los cubiertos."
         *  - unterteilen:  Subdividir
         *  - verteilen:    repartir; distribuir (algo, no necesariamente en cantidades iguales o de manera homogénea)
         *          "Auf der Konferenz wurden Broschüren verteilt.", "Se distribuyeron folletos en la conferencia. "
         *  - einteilen:    1: igual que aufteilen
         *                  2: Asignar. "Me asignaron el turno de la noche". (einteilen zu +Akk)
         *  -
         *
         */
    }





    public static void main(String[] args) {
        inicializar();
        //próximaPalabra();
        //dosMilVerbos("dienen");
        //System.out.println(megamap.get("2").keySet().size());

        definirTema("1");

        //Ver actual = TodosLosVerbos.get("finden");
        //System.out.println(Arrays.toString(actual.imperativ));
        //actual.definir();



        /**
         * UNREGELMÄßIGE KONJUNKTIV II
         *  - sein
         *  - werden
         *  - haben*
         *  - können
         *  - müssen
         *  - geben
         *  - finden
         *  - tun
         *  - wissen
         *  - sehen
         *  - gehen
         *  - lassen
         *  - kommen
         *  - wollen:
         *  - sollen
         *
         * dürfen
         * mögen
         * denken
         * brauchen
         * bringen
         * schlafen
         * bleiben
         * liegen
         */

    }


}
