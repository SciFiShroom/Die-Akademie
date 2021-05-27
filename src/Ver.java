import java.security.Signature;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Ver extends Palabra{
    public static final String Ver = "Ver";
    public static final String Verbo = "Verbo";
    public static final String nullEntry = Control.entradaNula;
    public static final String reg = "Regular";



    //Aquí se guardan todos los verbos. No hay ninguna estructura ni orden; eso viene despues.
    public static HashMap<String,Ver> TodosLosVerbos = new HashMap<String, Ver>(); //Sintaxis <Ver.verbo, [el objeto verbo]>


    //Parametros
    public String verbo;
    public String prefijo;
    public String verbstamm;
    public boolean esTrennbare;
    public boolean terminaConEN;
    public String[] tags;
    public String infinitiv;



    //Constructor universal
    public Ver(String verbo, String[] tags) {
        Control.VerbosListaSingular.add(this);

        //Se agrega el verbo al Hashmap universal
        if (TodosLosVerbos.containsKey(verbo)) {
            throw new NumberFormatException("Error: El verbo " + verbo + " se ha agregado dos veces.");
        }
        TodosLosVerbos.put(verbo, this);


        if (verbo.contains("|")) { //Trennbare!
            this.esTrennbare = true;
            if (verbo.split("\\|").length != 2) {
                throw new NumberFormatException("Error: No entiendí " + verbo);
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
     * Caso A: Verbos como machen, kommen, y sagen. Verbstamm + [conjugación].
     * Caso B: Verbos como sehen, fahren, y laufen. Verbstamm cambia para du y er.
     * Caso C: Verbos como arbeiten, reden, y antworten: Verbstamm + [Conjugación distinta]
     * Caso D: Verbos como tun, sein, y werden. No terminan en "-en", o no siguen ninguna regla.
     */
    public static final String sinT_Präsens = "sinT_Präsens";
    public static final String conT_Präsens = "conT_Präsens";

    /**
     * define conjugación 'präsens' para casos 1,2, y 3.
     * @param aux Indica como se conjuga el verbo:
     *            Caso 1: aux = sinT_Präsens. Ej. machen, kommen, sagen
     *            Caso 3: aux = conT_Präsens. Ej. arbeiten, reden, antworten
     *            Caso 2: aux = otro verbstamm. Ej. sehen, fahren, laufen
     */
    public void setPräsens(String aux) {
        if (!this.terminaConEN) {
            throw new NumberFormatException("Error: El verbo " + this.verbo + "no tiene präteritum regular. Revisar definición.");
        }

        String sufijo;
        if (!esTrennbare) {
            sufijo = "";
        } else {
            sufijo = " " + prefijo;
        }

        if (aux.equals(sinT_Präsens)) {
            präsens[0] = this.verbstamm + "e" + sufijo;
            präsens[1] = this.verbstamm + "st" + sufijo;
            präsens[2] = this.verbstamm + "t" + sufijo;
            präsens[3] = this.verbstamm + "en" + sufijo;
            präsens[4] = this.verbstamm + "t" + sufijo;
            präsens[5] = this.verbstamm + "en" + sufijo;
        }  else if (aux.equals(conT_Präsens)){
            präsens[0] = this.verbstamm + "e" + sufijo;
            präsens[0] = this.verbstamm + "est" + sufijo;
            präsens[0] = this.verbstamm + "et" + sufijo;
            präsens[0] = this.verbstamm + "en" + sufijo;
            präsens[0] = this.verbstamm + "et" + sufijo;
            präsens[0] = this.verbstamm + "en" + sufijo;
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
     *                    Todo verbo que no termine con "-en" cae aquí
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
     * Caso 1: Verbos como machen, sollwn, y wollen. Verbstamm + [conjugación]
     * Caso 2: Verbos como denken, nenne, y wissen. Verbstamm cambia, pero se conjuga igual.
     * Caso 3: Verbos como kommen, ziehen, y sein. Verbstamm cambia y la conjugación es diferente.
     * Caso 4: Verbos como tun, fordern, y werden. Irregular. No terminan en "-en", o no sigen ninguna de los formatos superiores.
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
    //En particular, nos referímos al konjunktiv - irreales Zukunft.
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
    public static final String sinE_Imperativ = "sinE_Imperativ";
    public static final String conE_Imperativ = "conE_Imperativ";
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
        } else if (terminaConEN && caso.equals(sinE_Imperativ)) {
            this.imperativ = new String[]{
                    verbstamm,
                    verbstamm + "t",
                    verbstamm + "en"
            };
        } else if (terminaConEN && caso.equals(conE_Imperativ)) {
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
     *  - comentarios: new String[]{"Para preguntar cuanto mide algo, normalmente se usa una construcción con 'wie'", "Ej. 'Wie hoch ist der Baum?' para preguntar 'Cuánto mide el arbol'"}
     */
    public void addSignificado(String significado, String[][] oraciones, String auxiliar, String[] comentarios) {
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
            System.out.print((i+1) + ". ");
            actual.definir(this);
        }
        System.out.println();
        ArrayList<String[]> info = new ArrayList<>();
        info.add(concatenarLargo(new String[]{"Präsens "}, this.präsens));
        info.add(concatenarLargo(new String[]{"Präteritum "}, this.präteritum));
        info.add(concatenarLargo(new String[]{"Konjunktiv I "}, this.konjunktivI));
        info.add(concatenarLargo(new String[]{"Konjunktiv II "}, this.konjunktivII));
        String[] imperativ = {"Imperativ", "---", this.imperativ[0], "---", this.imperativ[2], this.imperativ[1], this.imperativ[2]};
        info.add(imperativ);
        Control.arrPrint(concatenarAncho(info));
    }
    //</Significado>




    /**
     * Aquí se definen todos los verbos.}
     */
    public static void crearVerbos() {
        String[] T;

        T = new String[]{"1"};//Habrá 20 grupos, cada uno con 100 verbos. Aquí van los primeros 100.

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
        sein.addSignificado("ser; estar", new String[][]{
                {"Ich bin hier", "Estoy aquí"},
                {"Ich bin da gefahren", "He viajado ayá"}
        }, null, new String[]{"De los verbos más comunes del alemán."});

        //Ver vorstellen = new Ver("vor|stellen",T);
        //vorstellen.set

        /**
         * Liste:
         * geben
         * lesen
         * sehen
         * sein
         * tun
         * wechseln
         * arbeiten
         * haben
         * werden
         * machen
         * kommen
         * tun
         * sagen
         * fahren
         * reden
         * laufen
         * wissen
         * heißen
         * denken
         * ziehen
         * nennen
         * sollen
         * wollen
         */

    }




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
    //Escoje aleatoriamente 'número' verbos de una lista de verbos.
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



    public static void main(String[] args) {
        inicializar();
        //String[] a = {"1", "2", "3"};
        //String[] b = {"a", "b", "c", "d"};
        //System.out.println(Arrays.toString(concatenarLargo(a,b)));

        Ver sein = megamap.get("hilfsverb").get("sein");
        sein.definir();

    }

}
