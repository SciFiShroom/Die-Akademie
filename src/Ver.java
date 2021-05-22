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



    //todo: Constructor universal
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
            //Si no termina con "-en", el verbstamm permanece null.
        }


        this.verbo = verbo;
        this.tags = tags;

        // Control.RevisarColisiones(this);
    }



    //todo: Präsens
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
    //todo: /Präsens



    //todo: Präteritum
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
    //todo: /Präteritum



    //todo: Partizip Perfekt
    public String partizipPerfekt;
    /**
     * define el partizip perfekt.
     * @param partizip el Partizip Perfekt (Partizip II).
     */
    public void setPartizipPerfekt(String partizip) {
        this.partizipPerfekt = partizip;
    }
    //todo: /Partizip Perfekt



    //todo: Hilfsverb
    public String hilfsverb = "haben";
    public void setHilfsverbSein() {
        this.hilfsverb = "sein";
    }
    //todo: /Hilfsverb



    //todo: Konjunktiv II
    public boolean konjunvtivIIEsirregular;
    public String[] konjunktivII;
    //todo: /Konjunktiv II

    //todo: Konjunktiv I
    //todo: /Konjunktiv I

    //todo: Imperativ
    //todo: /Imperativ

    //todo: Partizip I
    //todo: /Partizip I

    //todo: Significado
    public significadoVer[] significados; //Cada entrada es un significado diferente

    /**
     * Entendiendo que cana verbo puede tener varios significados / usos distintos, se agregan individualmente de la próxima manera:
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
        for (int i = 0; i < this.significados.length; i++) {
            significadoVer actual = this.significados[i];
            System.out.print(i + ". ");
            actual.definir(this);
        }
    }
    //todo: /Significado

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
        //sein.setKonjunktivII([algo va aquí]);
        //sein.setImperativ([algo va aquí]);
        //sein.addSignificado("ser; estar", new String[]{"Ich bin 21 Jahre alt.", "Bist du da?"}, null, 0);
        //Formato es: [1] Meaning string, [2] Oracion array, [3] declinación. [4] reflexivo 1,0

        //Ver vorstellen = new Ver("vor|stellen",T);
        //vorstellen.set

    }

    public static void posprocesamiento() {
        //Creamos una lista de todos los temas/tags usados
        HashMap<String, Boolean> temas = new HashMap<String, Boolean>();
        for (String verbo : TodosLosVerbos.keySet()) {
            Ver actual = TodosLosVerbos.get(verbo);
            for (String tema : actual.tags) {
                temas.put(tema, true);
            }
        }

        //HashMap Pick up here!


        System.out.println("Todavía no llegamos aquí lel");
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



}
