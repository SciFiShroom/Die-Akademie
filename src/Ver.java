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
    public String infinitiv;
    public boolean esTrennbare;
    public String[] tags;

    /**
     * Constructor universal para todos los verbos.
     * @param verbo: El nombre del verbo. Si es trennbare, se indica con un '|'. Ejemplos válidos: "machen", "zu|machen"
     * @param tags: Los tags. También se le pueden agregar manualmente con el método addTag(String tag)
     * @cosa esTrennbare: booleano que indica si el verbo es trennbare o no. "machen" da 'false', y "zu|machen" daría 'true'
     * @cosa prefijo: el prefijo del verbo, si es trennbare. el prefijo de "zu|machen" sería "zu".
     *                si el verbo no  es trennbare, no se inicializa.
     * @cosa verbstamm: El verbstamm del verbo. Si el verbo es trennbare, el prefijo NO se incluye!
     *                  Si el verbo termina con el sufijo "en", "ern", o "eln" (literal casi todos los verbos), el
     *                  verbstamm es el verbo sin el prefijo. En el rarísimo caso de que no termine con estos sufijos,
     *                  el verbstamm se queda sin inicializar.
     * @cosa infinitiv: Se usa en la construcción del Konj. II y el Partizip I. Normalmente, el infinitiv será igual que el string 'verbo'.
     *                  En el caso de que sea un verbo trennbare, como "zu|machen", el infinitiv sería simplemente "zumachen", sin el '|'.
     */
    public Ver(String verbo, String[] tags) {
        //Control.VerbosListaSingular.add(this);

        //se agregan los parámetros básicos
        this.verbo = verbo;
        this.tags = tags;
        // Control.RevisarColisiones(this);

        //Se agrega el verbo al Hashmap universal
        if (TodosLosVerbos.containsKey(verbo)) {
            throw new NumberFormatException("Error: El verbo " + verbo + " se ha agregado dos veces.");
        }
        TodosLosVerbos.put(verbo, this);

        //Si el verbo es trennbare, aquí se detecta.
        if (verbo.contains("|")) {
            this.esTrennbare = true;
            if (verbo.split("\\|").length != 2) {
                throw new NumberFormatException("Error: No entendí " + verbo);
            }
            this.prefijo = verbo.split("\\|")[0];
        }


        //Aquí se genera el verbstamm. Ojo, si el verbo no termina con "en", "ern", o "eln", entonces el verbstamm nunca se inicializa.
        if (verbo.endsWith("en")) {
            verbstamm = verbo.substring(0, verbo.length() - 2);
        } else if (verbo.endsWith("ern") || verbo.endsWith("eln")) {
            verbstamm = verbo.substring(0, verbo.length() - 3);
        }
        //¡Hay que tener cuidado con los verbos trennbare!
        if (this.esTrennbare) {
            //Ojo: Ya sabemos que hay por mucho solo un '|', osea que esto es seguro.
            verbstamm = verbstamm.split("\\|")[1];
        }


        //El infinitiv!
        if (!this.esTrennbare) {
            this.infinitiv = this.verbo;
        } else {
            //Esta es la manera más fácil de hacer esto que yo me sepa; namas estamos quitando el '|'.
            this.infinitiv = this.verbo.split("\\|")[0] + this.verbo.split("\\|")[1];
        }


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
     * Hay seis casos:
     *
     * 1. "SsinE":    Verbos como machen, kommen, y sagen. Verbstamm + [conjugación].
     * 2. "SconE":    Verbos como arbeiten, reden, y antworten: Verbstamm + [Conjugación distinta, que lleva una 'e' adicional]
     * 3. "<raíz>":   Verbos como sehen, fahren, y laufen. Verbstamm cambia para du y er, pero por lo demás son predecibles
     * 4. "Sern":     Verbos como fordern, ändern, y steuern. Terminan con '-ern' y siguen una conjugación predecible.
     * 5. "Seln":     Verbos como handeln, wechseln, y bummeln. Terminan con 'eln', y siguen una conjugación predecible (y distinta al tipo 4)
     * 6. <String[]>: Verbos como tun, sein, y werden. No terminan en "-en", o no siguen ninguna regla.
     */
    public static final String SsinE = "SsinE";
    public static final String SconE = "SconE";
    public static final String Sern = "Sern";
    public static final String Seln = "Seln";


    /**
     * define conjugación 'präsens' para casos 1,2, 3, 4, y 5.
     * TAMBIÉN agrega el imperativo en los casos 1, 2, 4, y 5 (del Präsens).
     * @param aux Indica como se conjuga el verbo. Sigue la guía arriba.
     */
    public void setPräsens(String aux) {

        //En el caso de que el verbo sea trennbare, se debe de agregar el prefijo al final.
        //Obsérvese que si el verbo NO es trennbare, 'sufijo' solo es "". [osea, un string vacío]
        String sufijo = "";
        if (this.esTrennbare) {
            sufijo = " " + this.prefijo;
        }


        switch (aux) {
            case SsinE:
                if (!this.verbo.endsWith("en")) {throw new NumberFormatException("Error en definición Präsens para el verbo [" + this.verbo + "]");}
                this.präsens = new String[]{
                        this.verbstamm + "e"  + sufijo,
                        this.verbstamm + "st" + sufijo,
                        this.verbstamm + "t"  + sufijo,
                        this.verbstamm + "en" + sufijo,
                        this.verbstamm + "t"  + sufijo,
                        this.verbstamm + "en" + sufijo
                };
                this.setImperativ(IsinE); //Se agrega también el imperativo!!!
                return;

            case SconE:
                if (!this.verbo.endsWith("en")) {throw new NumberFormatException("Error en definición Präsens para el verbo [" + this.verbo + "]");}
                this.präsens = new String[]{
                        this.verbstamm + "e"   + sufijo,
                        this.verbstamm + "est" + sufijo,
                        this.verbstamm + "et"  + sufijo,
                        this.verbstamm + "en"  + sufijo,
                        this.verbstamm + "et"  + sufijo,
                        this.verbstamm + "en"  + sufijo
                };
                this.setImperativ(IconE);
                return;

            case Sern:
                if (!this.verbo.endsWith("ern")) {throw new NumberFormatException("Error en definición Präsens para el verbo [" + this.verbo + "]");}
                this.präsens = new String[]{
                        this.verbstamm + "ere"  + sufijo,
                        this.verbstamm + "erst" + sufijo,
                        this.verbstamm + "ert"  + sufijo,
                        this.verbstamm + "ern"  + sufijo,
                        this.verbstamm + "ert"  + sufijo,
                        this.verbstamm + "ern"  + sufijo
                };
                this.setImperativ(Iern);
                return;

            case Seln:
                if (!this.verbo.endsWith("eln")) {throw new NumberFormatException("Error en definición Präsens para el verbo [" + this.verbo + "]");}
                this.präsens = new String[]{
                        this.verbstamm + "le"   + sufijo,
                        this.verbstamm + "elst" + sufijo,
                        this.verbstamm + "elt"  + sufijo,
                        this.verbstamm + "eln"  + sufijo,
                        this.verbstamm + "elt"  + sufijo,
                        this.verbstamm + "eln"  + sufijo
                };
                this.setImperativ(Ieln);
                return;
        }

        //Si estamos aquí, es caso 3
        if (!this.verbo.endsWith("en")) {throw new NumberFormatException("Error en definición Präsens para el verbo [" + this.verbo + "]");}
        this.präsens = new String[]{
                this.verbstamm + "e"  + sufijo,
                           aux + "st" + sufijo,
                           aux + "t"  + sufijo,
                this.verbstamm + "en" + sufijo,
                this.verbstamm + "t"  + sufijo,
                this.verbstamm + "en" + sufijo
        };
    }


    /**
     * define conjugación 'präsens' para caso 6.
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
     * Hay seis casos:
     *
     * 1. "Tnormal":        Verbos como machen, sollen, y wollen. Verbstamm + [conjugación]
     * 2. <raíz>, "TconE":  Verbos como denken, nenne, y wissen. Verbstamm cambia, pero se conjuga igual.
     * 3. <raíz>, "TsinE":  Verbos como kommen, ziehen, y sein. Verbstamm cambia y la conjugación es diferente.
     * 4. "Tern":           Verbos como fordern, äußern, y ändern. Conjugación normal, pero el infinitivo termina con "-ern".
     * 5. "Teln":           Verbos como handeln, bummeln, y regeln. Conjugación normal, pero el infinitivo termina con "eln"
     * 6. <String[]>:       Verbos como tun y werden. Irregular. No encajan con ninguna de las cinco reglas superiores.
     */
    public static final String Tnormal = "Tnormal";
    public static final String TconE = "TconE";
    public static final String TsinE = "TsinE";
    public static final String Tern = "Tern";
    public static final String Teln = "Teln";


    /**
     * Define el präteritum para los casos 1, 4, y 5.
     * @param caso el tipo de conjugación.
     */
    public void setPräteritum(String caso) {
        String sufijo = "";
        if (esTrennbare) {
            sufijo = " " + this.prefijo;
        }


        switch(caso) {
            case Tnormal:
                if (!this.verbo.endsWith("en")) {throw new NumberFormatException("Error en definición Präteritum para el verbo [" + this.verbo + "]");}
                this.präteritum = new String[]{
                        this.verbstamm + "te"   + sufijo,
                        this.verbstamm + "test" + sufijo,
                        this.verbstamm + "te"   + sufijo,
                        this.verbstamm + "ten"  + sufijo,
                        this.verbstamm + "tet"  + sufijo,
                        this.verbstamm + "ten"  + sufijo
                };
                return;

            case Tern:
                if (!this.verbo.endsWith("ern")) {throw new NumberFormatException("Error en definición Präteritum para el verbo [" + this.verbo + "]");}
                this.präteritum = new String[]{
                        this.verbstamm + "erte"   + sufijo,
                        this.verbstamm + "ertest" + sufijo,
                        this.verbstamm + "erte"   + sufijo,
                        this.verbstamm + "erten"  + sufijo,
                        this.verbstamm + "ertet"  + sufijo,
                        this.verbstamm + "erten"  + sufijo
                };
                return;

            case Teln:
                if (!this.verbo.endsWith("eln")) {throw new NumberFormatException("Error en definición Präteritum para el verbo [" + this.verbo + "]");}
                this.präteritum = new String[]{
                        this.verbstamm + "elte"   + sufijo,
                        this.verbstamm + "eltest" + sufijo,
                        this.verbstamm + "elte"   + sufijo,
                        this.verbstamm + "elten"  + sufijo,
                        this.verbstamm + "eltet"  + sufijo,
                        this.verbstamm + "elten"  + sufijo
                };
                return;
        }

        //Si llegamos aquí, se ha pasado un caso inválido.
        throw new NumberFormatException("Error en definición Präteritum para el verbo [" + this.verbo + "]");
    }


    /**
     * Define el Präteritum para los casos 3 y 4.
     * @param raíz la nueva raíz, usada en el Präteritum
     * @param caso el caso de la conjugación
     */
    public void setPräteritum(String raíz, String caso) {
        if (!this.verbo.endsWith("en")) {throw new NumberFormatException("Error en definición Präteritum para el verbo [" + this.verbo + "]");}
        String sufijo = "";
        if (esTrennbare) {
            sufijo = " " + this.prefijo;
        }


        switch (caso) {
            case TconE:
                this.präteritum = new String[]{
                        raíz + "te"   + sufijo,
                        raíz + "test" + sufijo,
                        raíz + "te"   + sufijo,
                        raíz + "ten"  + sufijo,
                        raíz + "tet"  + sufijo,
                        raíz + "ten"  + sufijo
                };
                return;

            case TsinE:
                this.präteritum = new String[]{
                        raíz        + sufijo,
                        raíz + "st" + sufijo,
                        raíz        + sufijo,
                        raíz + "en" + sufijo,
                        raíz + "t"  + sufijo,
                        raíz + "en" + sufijo
                };
                return;
        }

        //Si llegamos aquí, hubo un error en la declaración del Präteritum.
        throw new NumberFormatException("Error en definición Präteritum para el verbo [" + this.verbo + "]");
    }


    /**
     * Define el Präteritum para el caso 6.
     * @param conjugación el präteritum del verbo.
     */
    public void setPräteritum(String[] conjugación) {
        if (conjugación.length != 6) {
            throw new NumberFormatException("Error: Definición de präteritum inválido. verbo = " + this.verbo);
        }

        //detecta si hay espacios. Si alguien intenta añadir un trennbare verb con el prefijo, esto lo previene.
        for (String s : conjugación) {
            if (s.contains(" ")) {
                throw new NumberFormatException("Error: se ha detectado un espacio en la definición del präteritum de " + this.verbo);
            }
        }

        if (esTrennbare) {
            for (int i = 0; i < 6; i++) {
                conjugación[i] += " " + prefijo;
            }
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
    //En particular, nos referimos al konjunktiv II Präteritum o Futur I.
    //Todos los verbos se conjugan como Konj. II - Futur I automáticamente,
    //al menos que manualmente se agregue la conjugación Konj. II - Präteritum.
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
                "würde "   + this.infinitiv,
                "würdest " + this.infinitiv,
                "würde "   + this.infinitiv,
                "würden "  + this.infinitiv,
                "würdet "  + this.infinitiv,
                "würden "  + this.infinitiv
        };
    }

    /**
     * Algunos (pocos) verbos tienen Konjunktiv II irregular. En este caso, se agrega manualmente.
     * @param konjunktivII el konjunktivII[].
     */
    public void setKonjunktivII(String[] konjunktivII) {
        if (konjunktivII.length != 6) {
            throw new NumberFormatException("Error: Konjunktiv II inválido");
        }

        //Se checa que no haya espacios en el konjunktiv. Esto se implementa para asegurar que, si el verbo tiene prefijo, no se añada aquí.
        for (String s : konjunktivII) {
            if (s.contains(" ")) {
                throw new NumberFormatException("Error: El konjunktiv II no debe incluir espacios. Si es trennbare, ¡se omite el prefijo!");
            };
        }

        this.konjunktivII = konjunktivII;

        //Si es trennbare, se agrega el prefijo. Esto se requiere para verbos como "an|kommen" o "ein|gehen" (pero no "bekommen")
        if (this.esTrennbare) {
            for (int i = 0; i < 6; i++) {
                this.konjunktivII[i] += " " + this.prefijo;
            }
        }
    }
    //</Konjunktiv II>


    //<ImperativNuevo>
    /**
     * El Imperativ normalmente se construye del Präsens. Por lo tanto, el Präsens agregará el Imperativ en la mayoría de los casos.
     * 1. "IsinE"       Corresponde con el caso SsinE
     * 2. "IconE"       Corresponde con el caso SconE
     * 3. "Iern"        Corresponde con el caso Sern
     * 4. "Ieln"        Corresponde con el caso Seln
     * 5. <String[]>    Caso irregular. Corresponde con los casos <raíz> y <String[]> del Präsens
     */
    public static final String IsinE = "IsinE";
    public static final String IconE = "IconE";
    public static final String Iern = "Iern";
    public static final String Ieln = "Ieln";
    public String[] imperativ;

    /**
     * Método que añade el imperativ. Este método se llama automáticamente del método setPräsens.
     * Tödo imperativ agregado manualmente debe de ser con el método setImperativ(String[] s).
     * @param caso uno de los cuatro casos posibles.
     */
    public void setImperativ(String caso) {
        //Usamos el mismo truco para agregar el prefijo, si es que hay, de manera eficiente:
        String sufijo = "";
        if (this.esTrennbare) {
            sufijo = " " + this.prefijo;
        }

        switch (caso) {
            case IsinE:
                if (!this.verbo.endsWith("en")) {throw new NumberFormatException("Error en definición Präteritum para el verbo [" + this.verbo + "]");}
                this.imperativ = new String[]{
                        this.verbstamm        + sufijo,
                        this.verbstamm + "t"  + sufijo,
                        this.verbstamm + "en" + sufijo
                };
                return;

            case IconE:
                if (!this.verbo.endsWith("en")) {throw new NumberFormatException("Error en definición Präteritum para el verbo [" + this.verbo + "]");}
                this.imperativ = new String[]{
                        this.verbstamm + "e"  + sufijo,
                        this.verbstamm + "et" + sufijo,
                        this.verbstamm + "en" + sufijo
                };
                return;

            case Iern:
                if (!this.verbo.endsWith("ern")) {throw new NumberFormatException("Error en definición Präteritum para el verbo [" + this.verbo + "]");}
                this.imperativ = new String[]{
                        this.verbstamm + "ere" + sufijo,
                        this.verbstamm + "ert" + sufijo,
                        this.verbstamm + "ern" + sufijo
                };
                return;

            case Ieln:
                if (!this.verbo.endsWith("eln")) {throw new NumberFormatException("Error en definición Präteritum para el verbo [" + this.verbo + "]");}
                this.imperativ = new String[]{
                        this.verbstamm + "le"  + sufijo,
                        this.verbstamm + "elt" + sufijo,
                        this.verbstamm + "eln" + sufijo
                };
                return;
        }


        throw new NumberFormatException("Error en definición Präteritum para el verbo [" + this.verbo + "]");
    }

    /**
     * Método para el imperativ; caso irregular.
     * @param imperativ el imperativ.
     */
    public void setImperativ(String[] imperativ) {
        //Se revisa el tamaño
        if (imperativ.length != 3) {
            throw new NumberFormatException("Error en definición Imperativ para el verbo [" + this.verbo + "]");
        }

        //Se revisa que el imperativ no tenga espacios. Como siempre, esto es para asegurarse que el prefijo no se incluya en la definición del imperativo para los verbos trennbare.
        for (String s : imperativ) {
            if (s.contains(" ")) {
                throw new NumberFormatException("Error en definición Imperativ para el verbo [" + this.verbo + "]");
            }
        }

        //El prefijo, en caso de que sea trennbare
        if (this.esTrennbare) {
            for (int i = 0; i < 3; i++) {
                imperativ[i] += " " + this.prefijo;
            }
        }

        this.imperativ = imperativ;
    }

    /**
     * Este método se usa en el (extraño) caso quel verbo no tenga imperativo.
     * @param sinImperativo no hace nada
     */
    public void setImperativ(boolean sinImperativo) {
        this.imperativ = new String[]{"---", "---", "---"};
    }
    //</ImperativNuevo>




    //<Konjunktiv I>
    //todo: Esto todavía no se termina :P
    public String[] konjunktivI;

    //Asume construcción regular

    /**
     * Ojo: Hay discrepancias en el uso del Konjunktiv I para algunos verbos.
     */
    public void setKonjunktivI() {
        if (this.verbo.endsWith("en")) {
            this.konjunktivI = new String[]{
                    this.verbstamm + "e",
                    this.verbstamm + "est",
                    this.verbstamm + "e",
                    this.verbstamm + "en",
                    this.verbstamm + "et",
                    this.verbstamm + "en",
            };
        } else if (this.verbo.endsWith("eln")) {
            this.konjunktivI = new String[]{
                    this.verbstamm + "ele",
                    this.verbstamm + "elst",
                    this.verbstamm + "ele",
                    this.verbstamm + "eln",
                    this.verbstamm + "elt",
                    this.verbstamm + "eln",
            };
        } else if (this.verbo.endsWith("ern")) {
            this.konjunktivI = new String[]{
                    this.verbstamm + "ere",
                    this.verbstamm + "erst",
                    this.verbstamm + "ere",
                    this.verbstamm + "ern",
                    this.verbstamm + "ert",
                    this.verbstamm + "ern",
            };
        } else {
            throw new NumberFormatException("Error: El konjunktiv I se debe de agregar manualmente para los verbos irregulares. Ver=[" + this.verbo + "]");
        }

        if (this.esTrennbare) {
            for (int i = 0; i < 6; i++) {this.konjunktivI[i] += " " + this.prefijo;}
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
    /**
    public String partizipI;

    //Agrega Partizip I, asumiendo que sea regular. Casi todos los verbos lo son.
    public void setPartizipI() {
        partizipI = this.infinitiv + "d";
    }

    //Si no es regular se agrega. Súper simple
    public void setPartizipI(String partizipI) {
        this.partizipI = partizipI;
    }*/
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
        //System.out.println(this.verbo + " [" + this.partizipI + "-, " + this.perfekt[2] + "]");
        System.out.println(this.verbo); //todo: We need to make this line much better!
        if (this.significados != null) {
            for (int i = 0; i < this.significados.length; i++) {
                significadoVer actual = this.significados[i];
                System.out.print("  " + (i+1) + ". ");
                actual.definir(this);
            }
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


    //Esta es la lista de los verbos con Konjunktiv II irregular.
    //Los verbos como "an|kommen" o "bekommen" también tienen Konjunktiv II irregular.
    //En el posprocesamiento, se revisa automáticamente si el verbo tiene Konj. II irregular o no.
    public static final String[] unregelmäßigeKonjunktivII = new String[]{
            "sein",     "werden",   "haben",    "können",   "müssen",
            "geben",    "finden",   "tun",      "wissen",   "sehen",
            "gehen",    "lassen",   "kommen",   "wollen",   "sollen",
            "dürfen",   "mögen",    "denken",   "brauchen", "bringen",
            "schlafen", "bleiben",  "liegen",   "nehmen",   "halten"
    };
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

                //Se revisa que no deba de tener un konjunktiv II irregular
                //Si es el caso, se debe de añadir manualmente. Al cabo no hay tantos ;)
                for (String s : unregelmäßigeKonjunktivII) {
                    if (actual.verbo.endsWith(s)) {
                        throw new NumberFormatException("Error: El verbo [" + actual.verbo + "] debería de tener un Konjunktiv II irregular.");
                    }
                }
                //Si llegamos aquí, quiere decir que es un verbo con konjunktiv II normal.
                actual.setKonjunktivII();
            } else {
                //Aquí nos aseguramos que los verbos que tengan konjunktiv II irregular realmente lo deban de tener.
                boolean passt = false;
                for (String s : unregelmäßigeKonjunktivII) {
                    if (actual.verbo.endsWith(s)) {
                        passt = true;
                        break;
                    }
                }
                if (!passt) {
                    throw new NumberFormatException("Error: No creo que el verbo [" + actual.verbo + "] deba de tener un Konjuntiv II irregular. ¿Se te olvidó agregarlo a la lista?");
                }
            }

            //Se agrega perfekt
            actual.setPerfekt();

            //Se agrega el konjunktiv I. Específicamente, el Konjunktiv I präsens.
            if (actual.konjunktivI == null) {
                actual.setKonjunktivI();
            }

            //Se revisa que cada verbo sí tenga un imperativ.
            if (actual.imperativ == null) {
                throw new NumberFormatException("Error: El verbo [" + actual.verbo + "] no tiene imperativo definido. Si el verbo no tiene imperativo, se debe declarar!");
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

        T = new String[]{"0"};

        Ver reden = new Ver("reden", T);
        reden.setPräsens(SconE);
        reden.setPräteritum("rede", TconE);
        reden.setPartizipPerfekt("geredet");

        Ver sagen = new Ver("sagen", T);
        sagen.setPräsens(SsinE);
        sagen.setPräteritum(Tnormal);
        sagen.setPartizipPerfekt("gesagt");

        Ver äußern = new Ver("äußern", T);
        äußern.setPräsens(Sern);
        äußern.setPräteritum(Tern);
        äußern.setPartizipPerfekt("geäußert");

        Ver wechseln = new Ver("wechseln", T);
        wechseln.setPräsens(Seln);
        wechseln.setPräteritum(Teln);
        wechseln.setPartizipPerfekt("gewechselt");

        Ver sehen = new Ver("sehen", T);
        sehen.setPräsens("sieh");
        sehen.setPräteritum("sah", TsinE);
        sehen.setPartizipPerfekt("gesehen");
        sehen.setKonjunktivII(new String[]{"sähe", "sähest", "sähe", "sähen", "sähet", "sähen"});
        sehen.setImperativ(new String[]{"sieh", "seht", "sehen"});

        Ver machen = new Ver("machen", T);
        machen.setPräsens(SsinE);
        machen.setPräteritum(Tnormal);
        machen.setPartizipPerfekt("gemacht");

        Ver denken = new Ver("denken", T);
        denken.setPräsens(SsinE);
        denken.setPräteritum("dach", TconE);
        denken.setPartizipPerfekt("gedacht");
        denken.setKonjunktivII(new String[]{"dächte", "dächtest", "dächte", "dächten", "dächtet", "dächten"});

        Ver kommen = new Ver("kommen", T);
        kommen.setPräsens(SsinE);
        kommen.setPräteritum("kam", TsinE);
        kommen.setPartizipPerfekt("gekommen");
        kommen.setKonjunktivII(new String[]{"käme", "kämest", "käme", "kämen", "kämet", "kämen"});
        kommen.setHilfsverbSein();

        Ver fordern = new Ver("fordern", T);
        fordern.setPräsens(Sern);
        fordern.setPräteritum(Tern);
        fordern.setPartizipPerfekt("gefordert");

        Ver handeln = new Ver("handeln", T);
        handeln.setPräsens(Seln);
        handeln.setPräteritum(Teln);
        handeln.setPartizipPerfekt("gehandelt");

        Ver anfassen = new Ver("an|fassen", T);
        anfassen.setPräsens(new String[]{"fasse", "fasst", "fasst", "fassen", "fasst", "fassen"});
        anfassen.setPräteritum(Tnormal);
        anfassen.setPartizipPerfekt("angefasst");
        anfassen.setImperativ(new String[]{"fass", "fasst", "fassen"});

        Ver einnehmen = new Ver("ein|nehmen", T);
        einnehmen.setPräsens("nimm");
        einnehmen.setPräteritum("nahm", TsinE);
        einnehmen.setPartizipPerfekt("eingenommen");
        einnehmen.setKonjunktivII(new String[]{"nähme", "nähmest", "nähme", "nähmen", "nähmet", "nähmen"});
        einnehmen.setImperativ(new String[]{"nimm", "nehmt", "nehmen"});

        Ver ankommen = new Ver("an|kommen", T);
        ankommen.setPräsens(SsinE);
        ankommen.setPräteritum("kam", TsinE);
        ankommen.setPartizipPerfekt("angekommen");
        ankommen.setKonjunktivII(new String[]{"käme", "kämest", "käme", "kämen", "kämet", "kämen"});
        ankommen.setHilfsverbSein();

        Ver anfangen = new Ver("an|fangen", T);
        anfangen.setPräsens("fäng");
        anfangen.setPräteritum("fing", TsinE);
        anfangen.setPartizipPerfekt("angefangen");
        anfangen.setImperativ(new String[]{"fang", "fangt", "fangen"});

        Ver tun = new Ver("tun", T);
        tun.setPräsens(new String[]{"tue", "tust", "tut", "tun", "tut", "tun"});
        tun.setPräteritum(new String[]{"tat", "tatest", "tat", "taten", "tatet", "taten"});//el 'du' también se escribe "tatst"
        tun.setPartizipPerfekt("getan");
        tun.setKonjunktivI(new String[]{"tue", "tuest", "tue", "tuen", "tuet", "tuen"});
        tun.setKonjunktivII(new String[]{"täte", "tätest", "täte", "täten", "tätet", "täten"});
        tun.setImperativ(new String[]{"tu", "tut", "tun"}); //todo: No se si lleva la 'e' o no

        Ver sein = new Ver("sein", T);
        sein.setPräsens(new String[]{"bin", "bist", "ist", "sind", "seid", "sind"});
        sein.setPräteritum(new String[]{"war", "warst", "war", "waren", "wart", "waren"});
        sein.setPartizipPerfekt("gewesen");
        sein.setHilfsverbSein();
        sein.setImperativ(new String[]{"sei", "seid", "seien"});
        sein.setKonjunktivII(new String[]{"wäre", "wärest", "wäre", "wären", "wäret", "wären"});
        sein.setKonjunktivI(new String[]{"sei", "seiest", "sei", "seien", "seiet", "seien"});

        Ver werden = new Ver("werden", T);
        werden.setPräsens(new String[]{"werde", "wirst", "wird", "werden", "werdet", "werden"});
        werden.setPräteritum(new String[]{"wurde", "wurdest", "wurde", "wurden", "wurdet", "wurden"});
        werden.setPartizipPerfekt("geworden");
        werden.setHilfsverbSein();
        werden.setImperativ(new String[]{"werde", "werdet", "werden"});
        werden.setKonjunktivII(new String[]{"würde", "würdest", "würde", "würden", "würdet", "würden"});

        Ver haben = new Ver("haben", T);
        haben.setPräsens("ha");
        haben.setPräteritum("hat", TconE);
        haben.setPartizipPerfekt("gehabt");
        haben.setKonjunktivII(new String[]{"hätte", "hättest", "hätte", "hätten", "hättet", "hätten"});
        haben.setImperativ(new String[]{"hab", "habt", "haben"});

        /**

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
        T = new String[]{"1"};


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

         */
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
        dosMilVerbos("anfassen");
        //System.out.println(megamap.get("2").keySet().size());

        definirTema("0");

        //Ver actual = TodosLosVerbos.get("finden");
        //System.out.println(Arrays.toString(actual.imperativ));
        //actual.definir();



    }


}
