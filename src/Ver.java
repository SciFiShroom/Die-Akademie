import java.util.ArrayList;
import java.util.Arrays;
public class Ver extends Palabra{
    public static final String Ver = "Ver";
    public static final String Verbo = "Verbo";
    public static final String nullEntry = Control.entradaNula;
    public static final String reg = "Regular";



    //---------------------------[PARAMETROS]-----------------------------------
    @Override
    public String getNombre() {return this.verbo;}
    public String verbo; //El verbo en imperativo (Lesen, sprechen, etc. )



    @Override
    public String getSignificado() {return this.significado;}
    public String significado; //El significado del verbo


    public boolean tienePresenteRegular;
    public String[] presente; //Indikativ präsens. Se agrega obligatoriamente en la definición del verbo.



    public boolean reflexivo = false; //Aùn no he hecho nada con lo reflexivo...
    public void esReflexivo(boolean EsReflexivo) {
        this.reflexivo = EsReflexivo;
    }



    public boolean tieneParticipioRegular; //?
    public String participio; //Partizip Perfekt. Básicamente el pasado (Ich habe gegessen...)
    public void agregarParticipio(String Participio) {
        if (Participio.equals("haben") || Participio.equals("sein")) {throw new NullPointerException("Créo que te has equivocado con el verbo " + this.verbo);}
        if (this.participio != null) {throw new NumberFormatException("Error: Un participio ya se ha agregado al verbo " + this.verbo);}

        this.participio = Participio;
    }



    public String hilfsverb = "haben"; //Puede ser haben o sein. Casi todos son Haben osea que será el default.
    public void agregarHilfsverb(String Hilfsverb) {
        if (!Hilfsverb.equals("haben") && !Hilfsverb.equals("sein")) {
            throw new NullPointerException("Error: Hilfsverb no reconocido");
        }
        this.hilfsverb = Hilfsverb;
    }



    public boolean tieneImperativoRegular;
    public String[] imperativo; //En general, du = Base = yo-presente menos la 'e' (Komm!), ihr = ihr (Kommt!), sie = Sie (Kommen!)
    public void agregarImperativoNuevo(String REG) {
        if (!REG.equals(reg)) {
            throw new NumberFormatException("Error: declaración incorrecta");
        } else if (this.imperativo != null && this.imperativo.length == 3) {
            throw new NumberFormatException("Error: El verbo '" + this.getNombre() + "' ya tiene un imperativo");
        }

        //Hay tres formas del imperativo: du, ihr, y wir/Sie. En general, definiremos que un imperativo regular es uno cuyo:
        // - forma du es el du-presente sin la ultima 'e'
        // - forma ihr es el ihr-presente
        // - forma wir/Sie es el wir/Sie presente
        //Ej. Los imperativos de Machen son mach, macht, machen.
        //Los imperativos forma du que acaban con 'e' son arcaicos y ya no se usan.

        this.imperativo = new String[3];

        this.imperativo[0] = Control.quitarSufijo(this.presente[0], "e");
        this.imperativo[1] = this.presente[4];
        this.imperativo[2] = this.presente[5];
    }
    public void agregarImperativoNuevo(String du, String ihr, String wir) {
        if (this.imperativo != null && this.imperativo.length == 3) {
            throw new NumberFormatException("Error: El verbo '" + this.verbo + "' ya tiene imperativo");
        }

        this.imperativo = new String[3];
        this.imperativo[0] = du;
        this.imperativo[1] = ihr;
        this.imperativo[2] = wir;
    }



    public boolean tienePreteritoRegular;
    public String[] preterito; //Indikativ präteritum; El pasado
    public void agregarPreterito(String[] Preterito) {
        if (this.preterito != null) {throw new NumberFormatException("Error: El preterito ya se agregó al verbo " + this.verbo);}


        this.tienePreteritoRegular = false;


        if (Preterito.length != 6) {throw new NullPointerException("Error con el preterito de " + this.verbo); }
        this.preterito = Preterito;
    } //Manual
    public void agregarPreterito(String e_est_e_en_et_en) {
        if (this.preterito != null) {throw new NumberFormatException("Error: El Iimperativo ya se agregó al verbo " + this.verbo);}

        this.tienePreteritoRegular = true;

        this.preterito = new String[6];
        this.preterito[0] = e_est_e_en_et_en + "e";
        this.preterito[1] = e_est_e_en_et_en + "est";
        this.preterito[2] = e_est_e_en_et_en + "e";
        this.preterito[3] = e_est_e_en_et_en + "en";
        this.preterito[4] = e_est_e_en_et_en + "et";
        this.preterito[5] = e_est_e_en_et_en + "en";
    }// -e, -est, -e, -en, -et, -en



    public boolean tieneKonjunktivRegular;
    public String[] konjunktivPreterito; //Aparte de el Indikativ präsens, Indikative präteritum, partizip perfekt, y los imperativos, es la única dimension que nos falta.
    public void agregarKonjunktivPreterito(String[] KonjunktivPreterto) {
        if (this.konjunktivPreterito != null) {
            throw new NumberFormatException("Error: Ya se ha agregado el Konjunktiv. ");
        }

        this.tieneKonjunktivRegular = false;


        if (KonjunktivPreterto.length != 6) {
            throw new NumberFormatException("Error de decaración de Konjunktiv");
        }

        this.konjunktivPreterito = KonjunktivPreterto;
    }
    public void agregarKonjunktivPreterito(String e_est_e_en_et_en) {
        if (this.konjunktivPreterito != null) {
            throw new NumberFormatException("Error: Ya se ha agregado el Konjunktiv. ");
        }

        this.tieneKonjunktivRegular = true;

        this.konjunktivPreterito = new String[6];
        konjunktivPreterito[0] = e_est_e_en_et_en + "e";
        konjunktivPreterito[1] = e_est_e_en_et_en + "est";
        konjunktivPreterito[2] = e_est_e_en_et_en + "e";
        konjunktivPreterito[3] = e_est_e_en_et_en + "en";
        konjunktivPreterito[4] = e_est_e_en_et_en + "et";
        konjunktivPreterito[5] = e_est_e_en_et_en + "en";
    }


    //---------------------------[PARÁMETROS DE CONTROL]------------------------
    //-------------------------[PARÁMETROS PARA VERBOS SEPARABLES]------------------------
    public Ver raiz = null; //Por ejemplo, en el verbo ansprechen, raiz = sprechen
    public void agregarRaiz(Ver Raiz) {
        this.raiz = Raiz;
    }


    public String prefijo = null; //El prefijo, si lo tiene
    public boolean TienePrefijo = false;
    public void tienePrefijo(boolean tienePrefijo) {
        this.TienePrefijo = tienePrefijo;
    }


    public ArrayList<Ver> ramas = new ArrayList<Ver>();//El verbo raiz. Tengo entendido que se conjuga de la misma manera. En tod0 caso, los primeros verbos no serán así.

    //-------------------------[---PARÁMETROS PARA VERBOS SEPARABLES]------------------------



    //---------------------------[CONSTRUCTORES]--------------------------------


    //Constructor para verbos normales. Solo se incluyen las conjugaciones presente (präsens). Las demás se agregan despues.
    public Ver(String Verbo, String[] Präsens, String Significado, String[] Tags) {
        Control.VerbosListaSingular.add(this);

        this.tienePresenteRegular = false;

        this.verbo = Verbo;
        if (Präsens.length != 6) {throw new NullPointerException("Error: Formato de verbo " + Verbo + " no aceptado");}
        this.presente = Präsens;
        this.significado = Significado;

        //Tag time
        this.tags = new String[0];
        for (String current : Tags) {
            this.agregarTag(current);
        }


        Control.RevisarColisiones(this);
    }


    //Constructor para verbos rama. Requiere que ya exista un verbo raiz. Si no existe en el idioma, se creará uno y ya veremos que pex.
    public Ver(String Prefijo, Ver Raiz, String Significado, String[] Tags) {
        Control.VerbosListaSingular.add(this);

        this.tienePresenteRegular = Raiz.tienePresenteRegular;
        this.tienePreteritoRegular = Raiz.tienePreteritoRegular;
        this.tieneImperativoRegular = Raiz.tieneImperativoRegular;
        this.tieneParticipioRegular = Raiz.tieneParticipioRegular;

        this.verbo = Prefijo + Raiz.verbo;
        //System.out.println("!!! " + this.verbo + "!!!");
        this.agregarRaiz(Raiz);
        this.tienePrefijo(true);
        this.significado = Significado;
        this.agregarParticipio(Prefijo + Raiz.participio);
        this.prefijo = Prefijo;

        this.presente = new String[6];
        this.presente[0] = Raiz.presente[0] + " " + Prefijo;
        this.presente[1] = Raiz.presente[1] + " " + Prefijo;
        this.presente[2] = Raiz.presente[2] + " " + Prefijo;
        this.presente[3] = Raiz.presente[3] + " " + Prefijo;
        this.presente[4] = Raiz.presente[4] + " " + Prefijo;
        this.presente[5] = Raiz.presente[5] + " " + Prefijo;

        this.imperativo = new String[3];
        this.imperativo[0] = Raiz.imperativo[0] + " " + prefijo;
        this.imperativo[1] = Raiz.imperativo[1] + " " + prefijo;
        this.imperativo[2] = Raiz.imperativo[2] + " " + prefijo;

        this.preterito = new String[6];
        this.preterito[0] = Raiz.preterito[0] + " " + prefijo;
        this.preterito[1] = Raiz.preterito[1] + " " + prefijo;
        this.preterito[2] = Raiz.preterito[2] + " " + prefijo;
        this.preterito[3] = Raiz.preterito[3] + " " + prefijo;
        this.preterito[4] = Raiz.preterito[4] + " " + prefijo;
        this.preterito[5] = Raiz.preterito[5] + " " + prefijo;

        //Tag time
        this.tags = new String[0];
        for (String current : Tags) {
            this.agregarTag(current);
        }

        Raiz.ramas.add(this);



        Control.RevisarColisiones(this);
    }


    //Constructor para verbos normales, tipo -st
    public Ver(String Verbo, String e_st_t_en_t_en, String Significado, String[] Tags) {
        Control.VerbosListaSingular.add(this);

        this.tienePresenteRegular = true;

        String[] conjugaciones = new String[6];
        //esto es la base.
        conjugaciones[0] = e_st_t_en_t_en + "e";
        conjugaciones[1] = e_st_t_en_t_en + "st";
        conjugaciones[2] = e_st_t_en_t_en + "t";
        conjugaciones[3] = e_st_t_en_t_en + "en";
        conjugaciones[4] = e_st_t_en_t_en + "t";
        conjugaciones[5] = e_st_t_en_t_en + "en";

        this.verbo = Verbo;
        this.presente = conjugaciones;
        this.significado = Significado;

        this.tags = new String[0];
        for (String actual : Tags) {
            this.agregarTag(actual);
        }
        //return new Ver(Verbo, conjugaciones, Significado, Tags);


        //this.base = e_st_t_en_t_en;

        Control.RevisarColisiones(this);
    }


    //Constructor para verbos normales, tipo -EST
    public Ver(String Verbo, String e_est_et_en_et_en, String EST, String Significado, String[] Tags) {
        if (!EST.equals(est)) {throw new NumberFormatException("Error: Declaración inválida [" + Verbo + "]");}

        Control.VerbosListaSingular.add(this);

        this.tienePresenteRegular = true;

        String[] conjugaciones = new String[6];
        //esto es la base.
        conjugaciones[0] = e_est_et_en_et_en + "e";
        conjugaciones[1] = e_est_et_en_et_en + "est";
        conjugaciones[2] = e_est_et_en_et_en + "et";
        conjugaciones[3] = e_est_et_en_et_en + "en";
        conjugaciones[4] = e_est_et_en_et_en + "et";
        conjugaciones[5] = e_est_et_en_et_en + "en";

        this.verbo = Verbo;
        this.presente = conjugaciones;
        this.significado = Significado;

        this.tags = new String[0];
        for (String actual : Tags) {
            this.agregarTag(actual);
        }
        //return new Ver(Verbo, conjugaciones, Significado, Tags);


        Control.RevisarColisiones(this);
    }


    public static final String est = "est"; //indicador de ser verbo -st



    //---------------------------[---CONSTRUCTORES]-----------------------------



    //Presente: Yo COMO pescado
    //Participio: Yo he COMIDO pescado


    //Método para crear erbos con conjugaciones 'presente' normales.
    // ich _e, du _st, ESE _t, wir _en, ihr _t, Sie _en
    /**
     public static Ver kleinPräsens(String Verbo, String Base, String Significado, String[] Tags) {
     String[] conjugaciones = new String[6];
     conjugaciones[0] = Base + "e";
     conjugaciones[1] = Base + "st";
     conjugaciones[2] = Base + "t";
     conjugaciones[3] = Base + "en";
     conjugaciones[4] = Base + "t";
     conjugaciones[5] = Base + "en";
     return new Ver(Verbo, conjugaciones, Significado, Tags);
     }
     */





    public String base;



    //Esto se usará en caso de que haya verbos cuya raiz sea este verbo. Ejemplo: Verbo = Sprechen, ramas.get(0) = ansprechen.
    //Nos dejará practicar listas deverbos cuya raiz es la misma. Será util ya que esto sucede a menudo.



    //Esto se usa para los verbos concatinativos / que tienen prefijo. (ansprechen, por ejemplo)


    //todo: Agregar errores si se trata de añadir algo que ya se añadió.
    //todo: agregar examen de participios perfectos.
    //todo: Agregar indikativo e Imperativo (son el pasado simple y commandos)



    //todo: Verbos separables: Constuctor debe actualizar preterito.
    //Los verbos están divididos manualmente por categorias. Será posible agregarles mas tags si es necesario.
    //Aqui se inicializarán los verbos.
    public static void crearVerbos() {
        String[] T;

        //todo: Marcador. Los tres verbos auxiliares.
        T = new String[]{"auxiliar"};
        Ver haben = new Ver("haben", new String[]{"habe", "hast", "hat", "haben", "habt", "haben"}, "tener", T);
        haben.agregarParticipio("gehabt");
        haben.agregarPreterito("hatt");
        haben.agregarKonjunktivPreterito("hätt");
        haben.agregarImperativoNuevo(reg);

        Ver sein = new Ver("sein", new String[]{"bin", "bist", "ist", "sind", "seid", "sind"}, "ser", T);
        sein.agregarHilfsverb("sein");
        sein.agregarParticipio("gewesen");
        sein.agregarPreterito(new String[]{"war", "warst", "war", "waren", "wart", "waren"});
        sein.agregarKonjunktivPreterito("wär");
        sein.imperativo = new String[]{"sei", "seid", "seien"};

        Ver werden = new Ver("werden", new String[]{"werde", "wirst", "wird", "werden", "werdet", "werden"}, "llegar a ser", T);
        werden.agregarHilfsverb("sein");
        werden.agregarParticipio("geworden");
        werden.agregarPreterito("wurde");
        werden.agregarKonjunktivPreterito("würd");
        werden.agregarImperativoNuevo(reg);

        //[---Auxiliares]





        //todo: Marcador. Los 6 verbos modales.
        T = new String[]{"modal"};

        //NO TIENEN IMPERATIVO LOS MODALES
        String[] NoTienenImperativo = {nullEntry, nullEntry, nullEntry};

        Ver können = new Ver("können", new String[]{"kann", "kannst", "kann", "können", "könnt", "können"}, "poder", T);
        können.agregarParticipio("gekonnt");
        können.agregarPreterito("konnt");
        können.agregarKonjunktivPreterito("könnt");
        können.imperativo = NoTienenImperativo;

        Ver dürfen = new Ver("dürfen", new String[]{"darf", "darfst", "darf", "dürfen", "dürft", "dürfen"}, "tener permiso", T);
        dürfen.agregarParticipio("gedurft");
        dürfen.agregarPreterito("durft");
        dürfen.agregarKonjunktivPreterito("dürft");
        dürfen.imperativo = NoTienenImperativo;

        Ver mögen = new Ver("mögen", new String[]{"mag", "magst", "mag", "mögen", "mögt", "mögen"}, "gustar", T);
        mögen.agregarParticipio("gemocht");
        mögen.agregarPreterito("mocht");
        mögen.agregarKonjunktivPreterito("möcht");
        mögen.imperativo = NoTienenImperativo;

        Ver müssen = new Ver("müssen", new String[]{"muss", "musst", "muss", "müssen", "müsst", "müssen"}, "tener que", T);
        müssen.agregarParticipio("gemusst");
        müssen.agregarPreterito("musst");
        müssen.agregarKonjunktivPreterito("müsst");
        müssen.imperativo = NoTienenImperativo;

        Ver sollen = new Ver("sollen", new String[]{"soll", "sollst", "soll", "sollen", "sollt", "sollen"}, "deber", T);
        sollen.agregarParticipio("gesollt");
        sollen.agregarPreterito("sollt");
        sollen.agregarKonjunktivPreterito("sollt");
        sollen.imperativo = NoTienenImperativo;

        Ver wollen = new Ver("wollen", new String[]{"will", "willst", "will", "wollen", "wollt", "wollen"}, "querer [hacer algo]", T);
        wollen.agregarParticipio("gewollt");
        wollen.agregarPreterito("wollt");
        wollen.agregarKonjunktivPreterito("wollt");
        wollen.imperativo = NoTienenImperativo;





        //todo: Marcador. Los objetos (interactuar con ellos)
        T = new String[]{"objeto"};

        Ver bringen = new Ver("bringen", "bring", "traer", T);
        bringen.agregarParticipio("gebracht");
        bringen.agregarPreterito("bracht");
        bringen.agregarImperativoNuevo(reg);

        Ver finden = new Ver("finden", "find", est, "encontrar", T);
        finden.agregarParticipio("gefunden");
        finden.agregarPreterito(new String[]{"fand", "fandest", "fand", "fanden", "fandet", "fanden"});
        finden.agregarImperativoNuevo(reg);

        Ver machen = new Ver("machen", "mach", "hacer", T);
        machen.agregarParticipio("gemacht");
        machen.agregarPreterito("macht");
        machen.agregarImperativoNuevo(reg);

        Ver aufmachen = new Ver("auf", machen, "abrir", T);

        Ver zumachen = new Ver("zu", machen, "cerrar", T);

        Ver schließen = new Ver("schließen", new String[]{"schließe", "schließt", "schließt", "schließen", "schließt", "schließen"}, "cerrar", T);
        schließen.agregarParticipio("geschlossen");
        schließen.agregarPreterito(new String[]{"schloss", "schlossest", "schloss", "schlossen", "schlosst", "schlossen"});
        schließen.agregarImperativoNuevo(reg);

        Ver öffnen = new Ver("öffnen", "öffn", est, "abrir", T);
        öffnen.agregarParticipio("geöffnet");
        öffnen.agregarPreterito("öffnet");
        öffnen.agregarImperativoNuevo("öffne", "öffnet", "öffnen");

        Ver tun = new Ver("tun", new String[]{"tue", "tust", "tut", "tun", "tut", "tun"}, "hacer", T);
        tun.agregarParticipio("getan");
        tun.agregarPreterito(new String[]{"tat", "tatest", "tat", "taten", "tatet", "taten"});
        tun.agregarImperativoNuevo(reg);

        Ver nehmen = new Ver("nehmen", new String[]{"nehme", "nimmst", "nimmt", "nehmen", "nehmt", "nehmen"}, "tomar [un objeto]", T);
        nehmen.agregarParticipio("genommen");
        nehmen.agregarPreterito(new String[]{"nahm", "nahmst", "nahm", "nahmen", "nahmt", "nahmen"});
        nehmen.agregarImperativoNuevo("nimm", "nehmt", "nehmen");

        Ver stellen = new Ver("stellen", "stell", "poner [parado]", T);
        stellen.agregarParticipio("gestellt");
        stellen.agregarPreterito("stellt");
        stellen.agregarImperativoNuevo(reg);

        Ver stehen = new Ver("stehen", "steh", "estar parado", T);
        stehen.agregarParticipio("gestanden");
        stehen.agregarPreterito(new String[]{"stand", "standest", "stand", "standen", "standet", "standen"});
        stehen.agregarImperativoNuevo(reg);

        Ver legen = new Ver("legen", "leg", "poner [acostar]", T);
        legen.agregarParticipio("gelegt");
        legen.agregarPreterito("legt");
        legen.agregarImperativoNuevo(reg);
        legen.esReflexivo(true);

        Ver liegen = new Ver("liegen", "lieg", "estar acostado", T);
        liegen.agregarTag("casa");
        liegen.agregarParticipio("gelegen");
        liegen.agregarPreterito(new String[]{"lag", "lagst", "lag", "lagen", "lagt", "lagen"});
        liegen.agregarImperativoNuevo(reg);

        Ver setzen = new Ver("setzen", new String[]{"setze", "setzt", "setzt", "setzen", "setzt", "setzen"}, "poner [sentar]", T);
        setzen.agregarParticipio("gesetzt");
        setzen.agregarPreterito("setzt");
        setzen.agregarImperativoNuevo(reg);
        setzen.esReflexivo(true);

        Ver sitzen = new Ver("sitzen", new String[]{"sitze", "sitzt", "sitzt", "sitzen", "sitzt", "sitzen"}, "estar sentado", T);
        sitzen.agregarParticipio("gesessen");
        sitzen.agregarPreterito(new String[]{"saß", "saßest", "saß", "saßen", "saßt", "saßen"});
        sitzen.agregarImperativoNuevo(reg);

        Ver hängen = new Ver("hängen [estado]", "häng", "estar colgado", T);
        hängen.agregarParticipio("gehangen");
        hängen.agregarPreterito(new String[]{"hing", "hingst", "hing", "hingen", "hingt", "hingen"});
        hängen.agregarImperativoNuevo(reg);

        Ver hängen2 = new Ver("hängen [acción]", "häng", "colgar", T);
        hängen2.agregarParticipio("gehängt");
        hängen2.agregarPreterito("hängt");
        hängen2.agregarImperativoNuevo(reg);

        Ver fallen = new Ver("fallen", new String[]{"falle", "fällst", "fällt", "fallen", "fallt", "fallen"}, "caer", T);
        fallen.agregarHilfsverb("sein");
        fallen.agregarParticipio("gefallen");
        fallen.agregarPreterito(new String[]{"fiel", "fielst", "fiel", "fielen", "fielt", "fielen"});
        fallen.agregarImperativoNuevo("fall", "fallt", "fallen");




        //-------------------------------Básicos------------------------------------------------
        T = new String[]{"básico"};
        Ver arbeiten = new Ver("arbeiten", "arbeit", est, "trabajar", T);
        arbeiten.agregarParticipio("gearbeitet");
        arbeiten.agregarPreterito("arbeitet");

        Ver bekommen = new Ver("bekommen", "bekomm", "recibir", T);
        bekommen.agregarHilfsverb("sein");
        bekommen.agregarParticipio("bekommen"); //irregular
        bekommen.agregarPreterito(new String[]{"bekam", "bekamst", "bekam", "bekamen", "bekamt", "bekamen"});
        bekommen.agregarImperativoNuevo(reg);

        Ver bleiben = new Ver("bleiben", "bleib", "quedarse", T);
        bleiben.agregarHilfsverb("sein");
        bleiben.agregarParticipio("geblieben");
        bleiben.agregarPreterito(new String[]{"blieb", "bliebst", "blieb", "blieben", "bliebt", "blieben"});
        bleiben.agregarImperativoNuevo(reg);

        Ver brauchen = new Ver("brauchen", "brauch", "necesitar", T);
        brauchen.agregarParticipio("gebraucht");
        brauchen.agregarPreterito("braucht");
        brauchen.agregarImperativoNuevo(reg);

        Ver erzählen = new Ver("erzählen", "erzähl", "contar [un cuento]", T);
        erzählen.agregarParticipio("erzählt");
        erzählen.agregarPreterito("erzählt");
        erzählen.agregarImperativoNuevo(reg);

        Ver feiern = new Ver("feiern", new String[]{"feiere", "feierst", "feiert", "feiern", "feiert", "feiern"}, "celebrar", T);
        feiern.agregarParticipio("gefeiert");
        feiern.agregarPreterito("feiert");
        feiern.agregarImperativoNuevo("feiere", "feiert", "feiern");

        Ver geben = new Ver("geben", new String[]{"gebe", "gibst", "gibt", "geben", "gebt", "geben"}, "dar", T);
        geben.agregarParticipio("gegeben");
        geben.agregarPreterito(new String[]{"gab", "gabst", "gab", "gaben", "gabt", "gaben"});
        geben.agregarImperativoNuevo("gib", "gebt", "geben");

        Ver lassen = new Ver("lassen", new String[]{"lasse", "lässt", "lässt", "lassen", "lasst", "lassen"}, "dejar", T);
        lassen.agregarParticipio("gelassen");
        lassen.agregarPreterito(new String[]{"ließ", "ließest", "ließ", "ließen", "ließt", "ließen"});
        lassen.agregarImperativoNuevo("lass", "lasst", "lassen");

        Ver teilnehmen = new Ver("teil", nehmen, "participar", T);

        Ver spielen2 = new Ver("spielen", "spiel", "tocar [un instrumento, una canción]", T);
        spielen2.agregarParticipio("gespielt");
        spielen2.agregarPreterito("spielt");
        spielen2.agregarImperativoNuevo(reg);

        Ver suchen = new Ver("suchen", "such", "buscar", T);
        suchen.agregarParticipio("gesucht");
        suchen.agregarPreterito("sucht");
        suchen.agregarImperativoNuevo(reg);

        Ver versuchen = new Ver("versuchen", "versuch", "intentar", T);
        versuchen.agregarParticipio("versucht");
        versuchen.agregarPreterito("versucht");
        versuchen.agregarImperativoNuevo(reg);

        Ver zeigen = new Ver("zeigen", "zeig", "presentar [algo]", T);
        zeigen.agregarParticipio("gezeigt");
        zeigen.agregarPreterito("zeigt");
        zeigen.agregarImperativoNuevo(reg);

        Ver anzeigen = new Ver("an", zeigen, "mostrar", T);

        //--------------------------------/basicos


        //, , , , cuestionar,
        //todo: Marcador. Preguntas, explicaciones, ...
        T = new String[]{"preguntas"};
        Ver antworten = new Ver("antworten", "antwort", est, "contestar; responder [a alguien]", T);
        antworten.agregarPreterito("antwortet");
        antworten.agregarParticipio("geantwortet");
        antworten.agregarImperativoNuevo(reg);
        antworten.agregarDescripción(new String[]{"'antworten' significa contestarle a alguien, y por lo tanto rige acusativo. ", "Para contestar algo (ej. una pregunta), se usaría beantworten.", "'Er antwortet der Lehrerin'"});

        Ver beantworten = new Ver("beantworten", "beantwort", est, "contestar; responder [algo]", T);
        beantworten.agregarParticipio("beantwortet");
        beantworten.agregarPreterito("beantwortet");
        beantworten.agregarImperativoNuevo(reg);
        beantworten.agregarDescripción(new String[]{"'beantworten' significa 'contestar algo', y por lo tanto rige acusativo. ", "Para contestarle a alguien (ej. a un profesor), se utilizaría antworten", "Ich beantworte seine Frage."});

        Ver beraten = new Ver("beraten", new String[]{"berate", "berätst", "berät", "beraten", "beratet", "beraten"}, "aconsejar", T);
        beraten.agregarParticipio("beraten");
        beraten.agregarPreterito(new String[]{"beriet", "betietest", "beriet", "berieten", "berietet", "berieten"});
        beraten.agregarImperativoNuevo(reg);

        Ver zweifeln = new Ver("zweifeln", new String[]{"zweifele", "zweifelst", "zweifelt", "zweifeln", "zweifelt", "zweifeln"}, "dudar [que]", T);
        zweifeln.agregarParticipio("gezweifelt");
        zweifeln.agregarPreterito("zweifelt");
        zweifeln.agregarImperativoNuevo("zweifele", "zweifelt", "zweifeln");
        zweifeln.agregarDescripción(new String[]{"Se puede usar sin objeto [Ich zweifel.], o con un objeto [Ich zweifel an...].", " Generalmente, se usa 'bezweifeln' cuando existe un objeto.", "'Ich zweifle an der Richtigkeit dieser Aussage'"});

        Ver bezweifeln = new Ver("bezweifeln", new String[]{"bezweifele", "bezweifelst", "bezweifelt", "bezweifeln", "bezweifelt", "bezweifeln"}, "dudar [de], T", T);
        bezweifeln.agregarParticipio("bezweifelt");
        bezweifeln.agregarPreterito("bezweifelt");
        bezweifeln.agregarImperativoNuevo("bezweifele", "bezweifelt", "bezweifeln");
        bezweifeln.agregarDescripción(new String[]{"'Bezweifeln' asume que existe un objeto.", " 'Ich bezweifle die Richtigkeit dieser Aussage.' "});

        Ver fragen = new Ver("fragen", "frag", "preguntar", T);
        fragen.agregarParticipio("gefragt");
        fragen.agregarPreterito("fragt");
        fragen.agregarImperativoNuevo(reg);

        Ver erklären = new Ver("erklären", "erklär", "explicar", T);
        erklären.agregarParticipio("erklärt");
        erklären.agregarPreterito("erklärt");
        erklären.agregarImperativoNuevo(reg);
        erklären.agregarTag("escuela");

        Ver klären = new Ver("klären", "klär", "clarificar", T);
        klären.agregarParticipio("geklärt");
        klären.agregarPreterito("klärt");
        klären.agregarImperativoNuevo(reg);




        //todo: Marcador. Comunicación
        T = new String[]{"comunicación"};

        Ver bestehen = new Ver("bestehen", "besteh", "insistir", T);
        bestehen.agregarParticipio("bestanden");
        bestehen.agregarPreterito(new String[]{"bestand", "bestandest", "bestand", "bestanden", "bestandet", "bestanden"});
        bestehen.agregarImperativoNuevo(reg);

        Ver heißen = new Ver("heißen", "heiß", "llamarse", T);
        heißen.agregarParticipio("geheißen");
        heißen.agregarPreterito(new String[]{"hieß", "hießest", "hieß", "hießen", "hießt", "hießen"});
        heißen.agregarImperativoNuevo(reg);

        Ver nennen = new Ver("nennen", "nenn", "nombrar [mencionar]", T);
        nennen.agregarParticipio("gennant");
        nennen.agregarPreterito("nannt");
        nennen.agregarImperativoNuevo(reg);

        Ver ernennen = new Ver("ernennen", "ernenn", "nombrar [designar]", T);
        ernennen.agregarParticipio("ernannt");
        ernennen.agregarPreterito("ernannt");
        ernennen.agregarImperativoNuevo(reg);

        Ver sagen = new Ver("sagen", "sag", "decir", T);
        sagen.agregarParticipio("gesagt");
        sagen.agregarPreterito("sagt");
        sagen.agregarImperativoNuevo(reg);

        Ver sprechen = new Ver("sprechen", new String[]{"spreche", "sprichst", "spricht", "sprechen", "sprecht", "sprechen"}, "hablar [un idioma]", T);
        sprechen.agregarParticipio("gesprochen");
        sprechen.agregarPreterito(new String[]{"sprach", "sprachst", "sprach", "sprachen", "spracht", "sprachen"});
        sprechen.agregarImperativoNuevo("sprich", "spricht", "sprechen");

        Ver aussprechen = new Ver("aus", sprechen, "pronunciar", T);

        Ver telefonieren = new Ver("telefonieren", "telefonier", "llamar por teléfono", T);
        telefonieren.agregarParticipio("telefoniert");
        telefonieren.agregarPreterito("telefoniert");
        telefonieren.agregarImperativoNuevo(reg);

        Ver reden = new Ver("reden", "red", est, "hablar [de algo]", T);
        reden.agregarParticipio("geredet");
        reden.agregarPreterito("redet");
        reden.agregarImperativoNuevo(reg);

        Ver rufen = new Ver("rufen", "ruf", "llamar [a alguien]", T);
        rufen.agregarParticipio("gerufen");
        rufen.agregarPreterito(new String[]{"rief", "riefst", "rief", "riefen", "rieft", "riefen"});
        rufen.agregarImperativoNuevo(reg);

        Ver vorstellen = new Ver("vor", stellen, "presentar [a alguien]", T);





        //todo: Marcador. Moverse
        T = new String[]{"moverse"};

        Ver kommen = new Ver("kommen", "komm", "venir", T);
        kommen.agregarParticipio("gekommen");
        kommen.agregarHilfsverb("sein");
        kommen.agregarPreterito(new String[]{"kam", "kamst", "kam", "kamen" ,"kamt", "kamt"});
        kommen.agregarImperativoNuevo(reg);

        Ver ankommen = new Ver("an", kommen, "llegar", T);
        ankommen.agregarHilfsverb("sein");

        Ver laufen = new Ver("laufen", new String[]{"laufe", "läufst", "läuft", "laufen", "lauft", "laufen"}, "correr", T);
        laufen.agregarHilfsverb("sein");
        laufen.agregarParticipio("gelaufen");
        laufen.agregarImperativoNuevo("lauf", "lauft", "laufen");
        laufen.agregarPreterito(new String[]{"lief", "liefst", "lief", "liefen", "lieft", "liefen"});

        Ver besichtigen = new Ver("besichtigen", "besichtig", "visitar [lugar]", T);
        besichtigen.agregarParticipio("besichtigt");
        besichtigen.agregarPreterito("besichtigt");
        besichtigen.agregarImperativoNuevo(reg);

        Ver besuchen = new Ver("besuchen", "besuch", "visitar [a alguien]", T);
        besuchen.agregarParticipio("besucht");
        besuchen.agregarPreterito("besucht");
        besuchen.agregarImperativoNuevo(reg);

        Ver übersetzen = new Ver("über", setzen, "cruzar [al otro lado]", T);
        übersetzen.agregarHilfsverb("sein");

        Ver fahren = new Ver("fahren", new String[]{"fahre", "fährst", "fährt", "fahren", "fahrt", "fahren"}, "ir [no a pie]", T);
        fahren.agregarHilfsverb("sein");
        fahren.agregarParticipio("gefahren");
        fahren.agregarPreterito(new String[]{"fuhr", "fuhrst", "fuhr", "fuhren", "fuhrt", "fuhren"});
        fahren.agregarImperativoNuevo("fahr", "fahrt", "fahren");

        Ver gehen = new Ver("gehen", "geh", "ir [a pie]", T);
        gehen.agregarHilfsverb("sein");
        gehen.agregarParticipio("gegangen");
        gehen.agregarPreterito(new String[]{"ging", "gingst", "ging", "gingen", "gingt", "gingen"});
        gehen.agregarImperativoNuevo(reg);

        Ver folgen = new Ver("folgen", "folg", "seguir", T);
        folgen.agregarHilfsverb("sein");
        folgen.agregarParticipio("gefolgt");
        folgen.agregarPreterito("folgt");
        folgen.agregarImperativoNuevo(reg);




        //todo: Marcador. El agua.
        // nadar, ahogarse, secar, mojar, pescar, empapar(??)



        //todo: Marcador. La mente.
        T = new String[]{"mente"};
        Ver glauben = new Ver("glaube", "glaub", "creer", T);
        glauben.agregarParticipio("geglaubt");
        glauben.agregarPreterito("glaubt");
        glauben.agregarImperativoNuevo(reg);

        Ver kennen = new Ver("kennen", "kenn", "conocer", T);
        kennen.agregarParticipio("gekannt");
        kennen.agregarPreterito("kannt");
        kennen.agregarImperativoNuevo(reg);

        Ver denken = new Ver("denken", "denk", "pensar", T);
        denken.agregarParticipio("gedacht");
        denken.agregarPreterito("dacht");
        denken.agregarImperativoNuevo(reg);

        Ver erkennen = new Ver("erkennen", "erkenn", "reconocer", T);
        erkennen.agregarParticipio("erkannt");
        erkennen.agregarPreterito("erkannt");
        erkennen.agregarImperativoNuevo(reg);
        erkennen.agregarTag("vista");

        Ver erinnern = new Ver("erinnern", new String[]{"erinnere", "erinnerst", "erinnert", "erinnern", "erinnert", "erinnern"}, "acordarse", T);
        erinnern.agregarParticipio("erinnert");
        erinnern.agregarPreterito("erinnert");
        erinnern.agregarImperativoNuevo("erinnere", "erinnert", "erinnern");

        Ver meinen = new Ver("meinen", "mein", "opinar", T);
        meinen.agregarParticipio("gemeint");
        meinen.agregarPreterito("meint");
        meinen.agregarImperativoNuevo(reg);

        Ver wissen = new Ver("wissen", new String[]{"weiß", "weißt", "weiß", "wissen", "wisst", "wissen"}, "saber", T);
        wissen.agregarParticipio("gewusst");
        wissen.agregarPreterito("wusst");
        wissen.agregarImperativoNuevo("wisse", "wisst", "wissen");

        Ver hoffen = new Ver("hoffen", "hoff", "esperar [tener esperanza]", T);
        hoffen.agregarParticipio("gehofft");
        hoffen.agregarPreterito("hofft");
        hoffen.agregarImperativoNuevo(reg);

        Ver vergessen = new Ver("vergessen", new String[]{"vergesse", "vergisst", "vergisst", "vergessen", "vergesst", "vergessen"}, "olvidar", T);
        vergessen.agregarParticipio("vergessen");
        vergessen.agregarPreterito(new String[]{"vergaß", "vergaßest", "vergaß", "vergaßen", "vergaßt", "vergaßen"});
        vergessen.agregarImperativoNuevo("vergiss", "vergesst", "vergessen");

        Ver verstehen = new Ver("verstehen", "versteh", "entender", T);
        verstehen.agregarParticipio("verstanden");
        verstehen.agregarPreterito(new String[]{"verstand", "verstandest", "verstand", "verstanden", "verstandet", "verstanden"});
        verstehen.agregarImperativoNuevo(reg);


        //confundir / estar confundido
        //[---Mente]



        //todo: Marcador
        //[Cuerpo]
        T = new String[]{"cuerpo"};

        Ver hören = new Ver("hören", "hör", "oir", T);
        hören.agregarParticipio("gehört");
        hören.agregarPreterito("hört");
        hören.agregarImperativoNuevo(reg);

        Ver heilen = new Ver("heilen", "heil", "sanar; curar", T);
        heilen.agregarHilfsverb("sein");
        heilen.agregarParticipio("geheilt");
        heilen.agregarPreterito("heilt");
        heilen.agregarImperativoNuevo(reg);

        Ver erkranken = new Ver("erkranken", "erkrank", "enfermarse", T);
        erkranken.agregarHilfsverb("sein");
        erkranken.agregarParticipio("erkrankt");
        erkranken.agregarPreterito("erkrankte");
        erkranken.agregarImperativoNuevo(reg);
        //genesen = heilen??

        Ver überleben = new Ver("überleben", "überleb", "sobrevivir", T);
        überleben.agregarParticipio("überlebt");
        überleben.agregarPreterito("überlebt");
        überleben.agregarImperativoNuevo(reg);


        //Ver schmecken = new Ver();
        //Ver riechen = new Ver();
        //Ver berühren = new Ver();

        Ver fühlen = new Ver("fühlen", "fühl", "sentirse", T);
        fühlen.agregarParticipio("gefühlt");
        fühlen.agregarPreterito("fühlt");
        fühlen.agregarImperativoNuevo(reg);
        fühlen.esReflexivo(true);

        Ver weh_tun = new Ver("weh ", tun, "doler", T); //el espacio es muy importante

        //lastimarse, recuperarse, enfermarse, sanarse, mejorarse(?), aturdirse

        //[-Cuerpo]


        //todo: Marcador
        //<Clima>-------------------------------
        T = new String[]{"clima"};

        Ver regnen = new Ver("regnen", new String[]{nullEntry, nullEntry, "regnet", nullEntry, nullEntry, "regnen"}, "llover", T);
        regnen.agregarHilfsverb("sein");
        regnen.agregarParticipio("geregnet");
        regnen.agregarPreterito(new String[]{nullEntry, nullEntry, "regnete", nullEntry, nullEntry, "regneten"});
        regnen.imperativo = new String[]{nullEntry, nullEntry, nullEntry};

        Ver schneien = new Ver("schneien", "schnei", "nevar", T);
        schneien.agregarParticipio("geschneit");
        schneien.agregarHilfsverb("sein");
        schneien.agregarPreterito("schneit");
        schneien.imperativo = new String[]{nullEntry, nullEntry, nullEntry};

        //Amanecer, atardecer, oscurecer,
        //</Clima>-------------------------------



        //[Expresarse]
        //todo: Marcador
        T = new String[]{"expresarse"};



        //[/Expresarse]



        //todo: Marcador
        //Vista
        T = new String[]{"vista"};

        Ver sehen = new Ver("sehen", new String[]{"sehe", "siehst", "sieht", "sehen", "seht", "sehen"}, "ver", T);
        sehen.agregarTag("cuerpo");
        sehen.agregarParticipio("gesehen");
        sehen.agregarPreterito(new String[]{"sah", "sahst", "sah", "sehen", "saht", "sehen"});
        sehen.agregarImperativoNuevo("sieh", "seht", "sehen");

        Ver ansehen = new Ver("an", sehen, "mirar", T);

        Ver aussehen = new Ver("aus", sehen, "verse", T);

        Ver scheinen = new Ver("scheine", "schein", "brillar", T );
        scheinen.agregarParticipio("gescheinen");
        scheinen.agregarPreterito(new String[]{"schien", "schienst", "schien", "schienen", "schient", "schienen"});
        scheinen.agregarImperativoNuevo(reg);

        Ver schauen = new Ver("schauen", "schau", "mirar [nada en particular]", T);
        schauen.agregarPreterito("schaut");
        schauen.agregarParticipio("geschaut");
        schauen.agregarImperativoNuevo(reg);

        Ver anschauen = new Ver("an", schauen, "mirar [un objeto]", T);

        Ver ausschauen = new Ver("aus", schauen, "admirar", T);

        //Vista



        //todo: Marcador
        //Tecnología
        T = new String[]{"tecnología"};

        Ver schalten = new Ver("schalten", "schalt", est, "conectar", T);
        schalten.agregarParticipio("geschaltet");
        schalten.agregarPreterito("schaltet");
        schalten.agregarImperativoNuevo(reg);

        Ver einschalten = new Ver("ein", schalten, "encender", T);
        Ver ausschalten = new Ver("aus", schalten, "apagar", T);
        Ver fernsehen = new Ver("fern", sehen, "ver la tele", T);

        Ver anrufen = new Ver("an", rufen, "llamar por teléfono", new String[]{"misceláneo"});
        anrufen.agregarTag("tecnología");
        //tecnología



        //todo: Marcador
        //<menos básico>----------------------------------------------------------------------------------------------------------------------
        T = new String[]{"menos_básico"};

        Ver ackern = new Ver("ackern", new String[]{"ackere", "ackerst", "ackert", "ackern", "ackert", "ackern"}, "arar", T);
        ackern.agregarParticipio("geackert");
        ackern.agregarPreterito("ackert");
        ackern.agregarKonjunktivPreterito(new String[]{"ackere", "ackerst", "ackere", "ackern", "ackert", "ackern"});
        ackern.agregarImperativoNuevo("ackere", "ackert", "ackern");

        Ver beginnen = new Ver("beginnen", "beginn", "empezar", T);
        beginnen.agregarParticipio("begonnen");
        beginnen.agregarPreterito(new String[]{"begann", "begannst", "begann", "begannen", "begannt", "begannen"});
        beginnen.agregarImperativoNuevo(reg);

        Ver betreffen = new Ver("betreffen", new String[]{"betreffe", "betriffst", "betrifft", "beterffen", "betrefft", "betreffen"}, "afectar", T);
        betreffen.agregarParticipio("betroffen");
        betreffen.agregarPreterito(new String[]{"betraf", "betrafst", "betraf", "betrafen", "betraft", "betrafen"});
        betreffen.agregarImperativoNuevo("betriff", "betrefft", "betreffen");

        Ver bieten = new Ver("bieten", "biet", est, "ofrecer", T);
        bieten.agregarParticipio("geboten");
        bieten.agregarPreterito(new String[]{"bot", "botest", "bot", "boten", "botet", "boten"});
        bieten.agregarImperativoNuevo(reg);

        Ver darstellen = new Ver("dar", stellen, "representar", T);

        Ver dauern = new Ver("dauern", new String[]{"dauere", "dauerst", "dauert", "dauern", "dauert", "dauern"}, "tardar [durar tiempo]", T);
        dauern.agregarParticipio("gedauert");
        dauern.agregarPreterito("dauert");

        Ver dauern2 = new Ver("dauern", new String[]{"dauere", "dauerst", "dauert", "dauern", "dauert", "dauern"}, "durar", T);
        dauern2.agregarParticipio("gedauert");
        dauern2.agregarPreterito("dauert");

        Ver enden = new Ver("enden", "end", est, "terminar", T);
        enden.agregarParticipio("geendet");
        enden.agregarPreterito("endet");

        Ver entsprechen = new Ver("entsprechen", new String[]{"entspreche", "entsprichst", "entspricht", "entsprechen", "entsprecht", "entsprechen"}, "corresponder", T);
        entsprechen.agregarParticipio("entsprochen");
        entsprechen.agregarPreterito(new String[]{"entsprach", "entsprachst", "entsprach", "entsprachen", "entspracht", "entsprachen"});
        entsprechen.agregarImperativoNuevo("entsprich", "entsprecht", "entsprechen");

        Ver entstehen = new Ver("entstehen", "entsteh", "originar", T);
        entstehen.agregarHilfsverb("sein");
        entstehen.agregarParticipio("entstanden");
        entstehen.agregarPreterito(new String[]{"entstand", "entstandest", "entstand", "entstanden", "entstandet", "entstanden"});
        entstehen.agregarImperativoNuevo(reg);

        Ver entwickeln = new Ver("entwickeln", new String[]{"entwickele", "entwickelst", "entwickelt", "entwickeln", "entwickelt", "entwickeln"}, "desarollar", T);
        entwickeln.agregarParticipio("entwickelt");
        entwickeln.agregarPreterito("entwickelt");
        //entwickeln.agregarImperativo("entwickele", "entwickelt", "entwickeln");

        Ver erhalten = new Ver("erhalten", new String[]{"erhalte", "erhältst", "erhält", "erhalten", "erhaltet", "erhalten"}, "recibir", T);
        erhalten.agregarParticipio("erhalten");
        erhalten.agregarImperativoNuevo(reg);
        erhalten.agregarPreterito(new String[]{"erheilt", "erheiltest", "erheilt", "erheilten", "erheiltet", "erheilten"});

        //https://hinative.com/es-MX/questions/4169617
        Ver erlauben = new Ver("erlauben", "erlaub", "permitir [más coloquial]", T);
        erlauben.agregarParticipio("erlaubt");
        erlauben.agregarPreterito("erlaubt");
        erlauben.agregarImperativoNuevo(reg);

        Ver falten = new Ver("falten", "falt", est, "doblar", T);
        falten.agregarParticipio("gefaltet");
        falten.agregarPreterito("faltet");
        falten.agregarImperativoNuevo(reg);

        Ver gestatten = new Ver("gestatten", "gestatt", est, "permitir [más formal]", T);
        gestatten.agregarParticipio("gestattet");
        gestatten.agregarPreterito("gestattet");
        gestatten.agregarImperativoNuevo(reg);

        Ver erscheinen = new Ver("erscheinen", "erschein", "aparecer", T);
        erscheinen.agregarHilfsverb("sein");
        erscheinen.agregarParticipio("erschienen");
        erscheinen.agregarPreterito(new String[]{"erschien", "erschienst", "erschien", "erschienen", "erschient", "erschienen"});
        erscheinen.agregarImperativoNuevo("erscheine", "erscheint", "erscheinen");

        Ver fließen = new Ver("fließen", new String[]{"fließe", "fließt", "fließt", "fließen", "fließt", "fließen"}, "fluir", T);
        fließen.agregarHilfsverb("sein");
        fließen.agregarPreterito(new String[]{"floß", "floßt", "floß", "flossen", "floßt", "flossen"});
        fließen.agregarParticipio("geflossen");
        fließen.agregarImperativoNuevo(reg);

        Ver führen = new Ver("führen", "führ", "dirigir", T);
        führen.agregarParticipio("geführt");
        führen.agregarImperativoNuevo(reg);
        führen.agregarPreterito("führt");

        Ver gehören = new Ver("gehören", "gehör", "pertenecer", T);
        gehören.agregarParticipio("gehört");
        gehören.agregarImperativoNuevo(reg);
        gehören.agregarPreterito("gehört");

        Ver gelten = new Ver("gelten", new String[]{"gelte", "gilst", "gilt", "gelten", "geltet", "gelten"}, "ser valido", T);
        gelten.agregarParticipio("gegolten");
        gelten.agregarPreterito(new String[]{"galt", "galtest", "galt", "galten", "galtet", "galten"});
        gelten.agregarImperativoNuevo("gilt", "geltet", "gelten");

        Ver gewinnen = new Ver("gewinnen", "gewinn", "ganar", T);
        gewinnen.agregarParticipio("gewonnen");
        gewinnen.agregarImperativoNuevo(reg);
        gewinnen.agregarPreterito(new String[]{"gewann", "gewannst", "gewann", "gewannen", "gewannt", "gewannen"});

        Ver gründen = new Ver("gründen", "gründ", est, "fundar [crear]", T);
        gründen.agregarParticipio("gegründet");
        gründen.agregarPreterito("gründet");
        gründen.agregarImperativoNuevo(reg);

        Ver halten = new Ver("halten", new String[]{"halte", "hälst", "hält", "halten", "haltet", "halten"},"pararse [detenerse]", T);
        halten.agregarParticipio("gehalten");
        halten.agregarPreterito(new String[]{"hielt", "hieltest", "hielt", "hielten", "hieltet", "hielten"});
        halten.agregarImperativoNuevo(reg);

        Ver lachen = new Ver("lachen", "lach", "reirse", T);
        lachen.agregarParticipio("gelacht");
        lachen.agregarPreterito("lacht");
        lachen.agregarImperativoNuevo(reg);

        Ver rechnen = new Ver("rechnen", "rechn", est, "calcular", T);
        rechnen.agregarPreterito("rechnet");
        rechnen.agregarParticipio("gerechnet");

        Ver schlafen = new Ver("schlafen", new String[]{"schlafe", "schläfst", "schläft", "schlafen", "schlaft", "schlafen"}, "dormir", T);
        schlafen.agregarParticipio("geschlafen");
        schlafen.agregarTag("casa");
        schlafen.agregarImperativoNuevo(reg);
        schlafen.agregarPreterito(new String[]{"schlief", "schliefst", "schlief", "schliefen", "schlieft", "schliefen"});

        Ver einschlafen = new Ver("ein", schlafen, "dormirse", T);
        einschlafen.agregarHilfsverb("sein");
        einschlafen.agregarTag("casa");

        Ver sorgen = new Ver("sorgen", "sorg", "preocuparse", T);
        sorgen.agregarParticipio("gesorgt");
        sorgen.agregarImperativoNuevo(reg);
        sorgen.agregarPreterito("sorgt");

        Ver spülen = new Ver("spülen", "spül", "fregar", T);
        spülen.agregarPreterito("spült");
        spülen.agregarImperativoNuevo(reg);
        spülen.agregarParticipio("gespült");
        spülen.agregarDescripción(new String[]{"Se utiliza para decir 'Lavar los platos'. "});

        Ver aufstehen = new Ver("auf", stehen, "pararse [levantarse]", T);
        aufstehen.agregarHilfsverb("sein");

        Ver tragen = new Ver("tragen", new String[]{"trage", "trägst", "trägt", "tragen", "tragt", "tragen"}, "llevar [cargar]", T);
        tragen.agregarParticipio("getragen");
        tragen.agregarTag("tienda");
        tragen.agregarPreterito(new String[]{"trug", "trugst", "trug", "trugen", "trugt", "trugen"});
        tragen.agregarImperativoNuevo(reg);

        Ver verbinden = new Ver("verbinden", "verbind", est, "conectar", T);
        verbinden.agregarParticipio("verbunden");
        verbinden.agregarPreterito(new String[]{"verband", "verbandest", "verband", "verbanden", "verbandet", "verbanden"});
        verbinden.agregarImperativoNuevo(reg);

        Ver vergehen = new Ver("vergehen", "vergeh", "transcurrir [tiempo]", T);
        vergehen.agregarHilfsverb("sein");
        vergehen.agregarParticipio("vergangen");
        vergehen.agregarPreterito(new String[]{"verging", "vergingst", "verging","vergingen", "vergingt", "vergingen"});
        vergehen.agregarImperativoNuevo(reg);

        Ver verlieren = new Ver("verlieren", "verlier", "perder", T);
        verlieren.agregarParticipio("verloren");
        verlieren.agregarPreterito(new String[]{"verlor", "verlorst", "verlor", "verloren", "verlort", "verloren"});
        verlieren.agregarImperativoNuevo(reg);

        Ver vorstellen2 = new Ver("vor", stellen, "imaginarse", T);

        Ver wachen = new Ver("wachen", "wach", "velar", T);
        wachen.agregarTag("casa");
        wachen.agregarParticipio("gewacht");
        wachen.agregarPreterito("wacht");
        wachen.agregarImperativoNuevo(reg);

        Ver aufwachen = new Ver("auf", wachen, "despertarse", T);
        aufwachen.agregarHilfsverb("sein");
        aufwachen.agregarTag("casa");

        Ver warten = new Ver("warten", "wart", est, "esperar [tiempo]", T);
        warten.agregarParticipio("gewartet");
        warten.agregarPreterito("wartet");
        warten.agregarImperativoNuevo(reg);

        Ver erwarten = new Ver("erwarten", "erwart", est, "esperar [algo de alguien]", T);
        erwarten.agregarParticipio("erwartet");
        erwarten.agregarPreterito("erwartet");
        erwarten.agregarImperativoNuevo(reg);

        Ver waschen = new Ver("waschen", new String[]{"wasche", "wäschst", "wäscht", "waschen", "wascht", "waschen"}, "lavar", T);
        waschen.agregarParticipio("gewaschen");
        waschen.agregarPreterito(new String[]{"wusch", "wuschest", "wusch", "wuschen", "wuscht", "wuschen"});
        waschen.agregarImperativoNuevo(reg);
        waschen.agregarDescripción(new String[]{"El verbo 'waschen' se usa igual que 'lavar' en el español, pero para decir 'Lavar los platos', se utiliza el verbo 'spülen'."});

        Ver werfen = new Ver("werfen", new String[]{"werfe", "wirfst", "wirft", "werfen", "werft", "werfen"}, "tirar", T);
        werfen.agregarParticipio("geworfen");
        werfen.agregarPreterito(new String[]{"warf", "warfst", "warf", "warfen", "warft", "warfen"});
        werfen.agregarImperativoNuevo("wirf", "werft", "werfen");

        /**
        Ver ziehen = new Ver("ziehen", "zieh", "jalar???", T);
        ziehen.agregarParticipio("gezogen");
        ziehen.agregarImperativo();
        ziehen.agregarPreterito(new String[]{"zog", "zogst", "zog", "zogen", "zogt", "zogen"});*/

        Ver ziehen2 = new Ver("ziehen", "zieh", "mudarse", T);
        ziehen2.agregarParticipio("gezogen");
        ziehen2.agregarTag("casa");
        ziehen2.agregarPreterito(new String[]{"zog", "zogst", "zog", "zogen", "zogt", "zogen"});
        ziehen2.agregarImperativoNuevo(reg);

        Ver anziehen = new Ver("an", ziehen2, "ponerse", new String[]{"misceláneo"});
        anziehen.agregarTag("tienda");



        //ESCUELA------------------------------------------------------
        T = new String[]{"escuela"};



        Ver fehlen = new Ver("fehlen", "fehl", "faltar", T);
        fehlen.agregarParticipio("gefehlt");
        fehlen.agregarPreterito("fehlt");
        fehlen.agregarImperativoNuevo(reg);

        Ver helfen = new Ver("helfen", new String[]{"helfe", "hilfst", "hilft", "helfen", "helft", "helfen"}, "ayudar", T);
        helfen.agregarParticipio("geholfen");
        helfen.agregarPreterito(new String[]{"half", "halfst", "half", "halfen" ,"halft", "halfen"});
        helfen.agregarImperativoNuevo("hilf", "helft", "helfen");

        Ver interessieren = new Ver("interessieren", "interessier", "interesar", T);
        interessieren.agregarParticipio("interessiert");
        interessieren.agregarPreterito("interessiert");
        interessieren.agregarImperativoNuevo(reg);

        Ver studieren = new Ver("studieren", "studier", "estudiar", T);
        studieren.agregarParticipio("studiert");
        studieren.agregarPreterito("studiert");
        studieren.agregarImperativoNuevo(reg);

        Ver zählen = new Ver("zählen", "zähl", "contar [números]", T);
        zählen.agregarParticipio("gezählt");
        zählen.agregarPreterito("zählt");
        zählen.agregarImperativoNuevo(reg);

        Ver lehren = new Ver("lehren", "lehr", "enseñar [un tema]", T);
        lehren.agregarParticipio("gelehrt");
        lehren.agregarPreterito("lehrt");
        lehren.agregarImperativoNuevo(reg);

        Ver lernen = new Ver("lernen", "lern", "aprender", T);
        lernen.agregarParticipio("gelernt");
        lernen.agregarPreterito("lernt");
        lernen.agregarImperativoNuevo(reg);

        Ver bestehen2 = new Ver("bestehen", "besteh", "aprobar [un examen]", T);
        bestehen2.agregarParticipio("bestanden");
        bestehen2.agregarPreterito(new String[]{"bestand", "bestandest", "bestand", "bestanden", "bestandet", "bestanden"});
        bestehen2.agregarImperativoNuevo(reg);

        Ver bedeuten = new Ver("bedeuten", "bedeut", est, "significar", T);
        bedeuten.agregarParticipio("bedeutet");
        bedeuten.agregarPreterito("bedeutet");
        bedeuten.agregarImperativoNuevo(reg);



        //notieren: Cotizar o apuntar.


        //ESCUELA------------------------------------------------------
        ///Ver antworten
        //Ver beantworten
        //Ver berechnen;
        //Ver bewerten;
        //Ver untersuchen;

        //romper
        //robar

        //zuzamenbrucht?
        //festejar
        //

        //verbos que no se que pex
        //Ver aufhören;
        //Ver treffen;
        //Ver bilden; // – educar, construir
        //Ver handeln; // – tratar, negociar
        //Ver ergeben; // – resultar
        //erreichen – conseguir, llegar
        //schaffen – crear, administrar
        //holen
        //abholen

        //anbieten – ofrecer
        //vergleichen – comparar

        //buchen: new Ver("buchen", "buch", "reservar", Tags);
        //buchen.agregarParticipio("gebucht");


        //[tienda]
        T = new String[]{"tienda"};

        Ver bestücken = new Ver("bestücken", "bestück", "abastecer", T);
        bestücken.agregarParticipio("bestückt");
        bestücken.agregarPreterito("bestückt");
        bestücken.agregarImperativoNuevo(reg);

        Ver kaufen = new Ver("kaufen", "kauf", "comprar", T);
        kaufen.agregarParticipio("gekauft");
        kaufen.agregarPreterito("kauft");
        kaufen.agregarImperativoNuevo(reg);

        Ver kosten = new Ver("kosten", "kost", est, "costar", T);
        kosten.agregarParticipio("gekostet");
        kosten.agregarPreterito("kostet");
        kosten.agregarImperativoNuevo(reg);
        kosten.agregarDescripción("Para preguntar cuanto cuesta algo, se dice 'Wie viel kostet das?'.");

        Ver einkaufen = new Ver("ein", kaufen, "ir de compras", T);

        Ver tragen2 = new Ver("tragen", new String[]{"trage", "trägst", "trägt", "tragen", "tragt", "tragen"}, "traer puesto", T);
        tragen2.agregarParticipio("getragen");
        tragen2.agregarPreterito(new String[]{"trug", "trugst", "trug", "trugen", "trugt", "trugen"});
        tragen2.agregarImperativoNuevo(reg);

        //Sinonimos
        Ver schenken = new Ver("schenken", "schenk", "regalar", T);
        schenken.agregarParticipio("geschenkt");
        schenken.agregarPreterito("schenkt");
        schenken.agregarImperativoNuevo(reg);

        Ver verschenken = new Ver("verschenken", "verschenk", "regalar", T);
        verschenken.agregarParticipio("verschenkt");
        verschenken.agregarPreterito("verschenkt");
        verschenken.agregarImperativoNuevo(reg);

        Ver verkaufen = new Ver("verkaufen", "verkauf", "vender", T);
        verkaufen.agregarParticipio("verkauft");
        verkaufen.agregarPreterito("verkauft");
        verkaufen.agregarImperativoNuevo(reg);

        //Sinonimos, al parecer.
        Ver bezahlen = new Ver("bezahlen", "bezahl", "pagar", T);
        bezahlen.agregarParticipio("bezahlt");
        bezahlen.agregarPreterito("bezahlt");
        bezahlen.agregarImperativoNuevo(reg);

        Ver zahlen = new Ver("zahlen", "zahl", "pagar", T);
        zahlen.agregarParticipio("gezahlt");
        zahlen.agregarPreterito("zahlt");
        zahlen.agregarImperativoNuevo(reg);




        //todo: Marcador. Comida
        T = new String[]{"comida"};

        Ver essen = new Ver("essen", new String[]{"esse", "isst", "isst", "essen", "esst", "essen"}, "comer", T);
        essen.agregarParticipio("gegessen");
        essen.agregarPreterito(new String[]{"aß", "aßest", "aß", "aßen", "aßt", "aßen"});
        essen.agregarImperativoNuevo("iss", "esst", "essen");
        //¿existe 'tragar'?

        Ver frühstücken = new Ver("frühstücken", "frühstück", "desayunar", T);
        frühstücken.agregarParticipio("gefrühstück");
        frühstücken.agregarPreterito("frühstückt");
        frühstücken.agregarImperativoNuevo(reg);

        Ver kochen = new Ver("kochen", "koch", "cocinar", T);
        kochen.agregarParticipio("gekocht");
        kochen.agregarPreterito("kocht");
        kochen.agregarImperativoNuevo(reg);

        Ver süßen = new Ver("süßen", new String[]{"süße", "süßt", "süßt", "süßen", "süßt", "süßen"}, "endulzar", T);
        süßen.agregarPreterito("süßt");
        süßen.agregarParticipio("gesüßt");
        süßen.agregarImperativoNuevo(reg);

        Ver trinken = new Ver("trinken", "trink", "tomar [líquido]", T);
        trinken.agregarParticipio("getrunken");
        trinken.agregarPreterito(new String[]{"trank", "trankst", "trank", "tranken", "trankt", "tranken"});
        trinken.agregarImperativoNuevo(reg);

        Ver vespern = new Ver("vespern", "vesper", "merendar", T);
        vespern.agregarPreterito("vespert");
        vespern.agregarImperativoNuevo("vespere", "vespert", "vespern");
        vespern.agregarParticipio("gevespert");

        Ver braten = new Ver("braten", new String[]{"brate", "brätst", "brät", "braten", "bratet", "braten"}, "asar", T);
        braten.agregarParticipio("gebraten");
        braten.agregarPreterito(new String[]{"briet", "brietest", "briet", "brieten", "brietet", "brieten"});
        braten.agregarImperativoNuevo(reg);

        Ver schmoren = new Ver("schmoren", "schmor", "cocer [estofar]", T);
        schmoren.agregarParticipio("geschmort");
        schmoren.agregarPreterito("schmort");
        schmoren.agregarImperativoNuevo(reg);

        Ver toasten = new Ver("toasten", "toast", est, "tostar [un pan]", T);
        toasten.agregarParticipio("getoastet");
        toasten.agregarPreterito("toastet");
        toasten.agregarImperativoNuevo(reg);

        //räuchern; salar?

        //braten, schmoren, und grllen und frittieren


        //todo: Marcador. Misceláneo
        T = new String[]{"misceláneo"};

        /**Ver fangen = new Ver("fangen", "fang", "atrapar", T);
        fangen.agregarParticipio("gefangen");
        fangen.agregarPreterito(new String[]{"fing", "fingst", "fing", "fingen", "fingt", "fingen"});
        fangen.agregarImperativoNuevo(reg);*/

        //ergreifen; fassen; fangen; nehmen = Agarrar? y cazar, percar,...

        //Ver anfangen = new Ver("an", fangen, "empezar", new String[]{"básico"});

        Ver parken = new Ver("parken", "park", "estacionar", T);
        parken.agregarParticipio("geparkt");
        parken.agregarPreterito("parkt");
        parken.agregarImperativoNuevo(reg);

        Ver lösen = new Ver("lösen", new String[]{"löse", "löst", "löst", "lösen", "löst", "lösen"}, "resolver", T);
        lösen.agregarParticipio("gelöst");
        lösen.agregarPreterito("löst");
        lösen.agregarImperativoNuevo("lös", "löst", "lösen");

        Ver vereinbaren = new Ver("vereinbaren", "vereinbar", "concertar", T);
        vereinbaren.agregarParticipio("vereinbart");
        vereinbaren.agregarImperativoNuevo(reg);
        vereinbaren.agregarPreterito("vereinbart");

        Ver bestellen = new Ver("bestellen", "bestell", "encargar", T);
        bestellen.agregarParticipio("bestellt");
        bestellen.agregarPreterito("bestellt");
        bestellen.agregarImperativoNuevo(reg);

        Ver übersetzen2 = new Ver("übersetzen", new String[]{"übersetze", "übersetzt", "übersetzt", "übersetzen", "übersetzt", "übersetzen"}, "traducir", T);
        übersetzen2.agregarParticipio("übersetzt");
        übersetzen2.agregarPreterito("übersetzt");
        übersetzen2.agregarImperativoNuevo(reg);

        Ver loslassen = new Ver("los", lassen, "soltar", T);


        //todo: Marcador. Religión
        //T = new String[]{religión};
        //perdonar, disculpar, evangelizar, rezar, ??? comulgar, bautizar, cazar[se]; amar,

        //todo: Marcador. Viajar
        T = new String[]{"viajar"};
        //poner reservieren aqui
        //fahren, gehen; manejar???
        //recuerda que cuando hagamos el sistema para ejecutar exámenes de varios tipos de palabras, no importará que haya pocas palabras DE UN TIPO en una categoría.

        Ver reservieren = new Ver("reservieren", "reservier", "reservar", T);
        reservieren.agregarParticipio("reserviert");
        reservieren.agregarPreterito("reserviert");
        reservieren.agregarImperativoNuevo(reg);

        Ver reisen = new Ver("reisen", new String[]{"reise", "reist", "reist", "reisen", "reist", "reisen"}, "viajar", T);
        reisen.agregarHilfsverb("sein");
        reisen.agregarParticipio("gereist");
        reisen.agregarImperativoNuevo(reg);
        reisen.agregarPreterito("reist");



        //todo: Marcador. Escritura
        T = new String[]{"escritura"};

        Ver buchstabieren;

        Ver lesen = new Ver("lesen", new String[]{"lese", "liest", "liest", "lesen", "lest", "lesen"}, "leer", T);
        lesen.agregarParticipio("gelesen");
        lesen.agregarPreterito(new String[]{"las", "lasest", "las", "lasen", "last", "lasen"});
        lesen.agregarImperativoNuevo("lies", "lest", "lesen");

        Ver vorlesen = new Ver("vor", lesen, "leer en voz alta", T);

        Ver schreiben = new Ver("schreiben", "schreib", "escribir", T);
        schreiben.agregarParticipio("geschrieben");
        schreiben.agregarPreterito(new String[]{"schrieb", "schriebst", "schrieb", "schrieben", "schriebt", "schrieben"});
        schreiben.agregarImperativoNuevo(reg);

        Ver aufschreiben = new Ver("auf", schreiben, "apuntar", T);

        //todo: Marcador. Comida


        //todo: Marcador. Arte (Bellas artes, etc. )
        T = new String[]{"arte"};

        //Ver cantar, bailar
        Ver singen = new Ver("singen", "sing", "cantar", T);
        singen.agregarParticipio("gesungen");
        singen.agregarPreterito(new String[]{"sang", "sangst", "sang", "sangen", "sangt", "sangen"});
        singen.agregarImperativoNuevo(reg);

        Ver tanzen = new Ver("tanzen", new String[]{"tanze", "tanzt", "tanzt", "tanzen", "tanzt", "tanzen"}, "bailar", T);
        tanzen.agregarParticipio("getanzt");
        tanzen.agregarPreterito("tanzt");
        tanzen.agregarImperativoNuevo(reg);

        Ver malen = new Ver("malen", "mal", "pintar [un cadro]", T);
        malen.agregarParticipio("gemalt");
        malen.agregarPreterito("mält");
        malen.agregarImperativoNuevo(reg);

        Ver üben = new Ver("üben", "üb", "practicar", T);
        üben.agregarParticipio("geübt");
        üben.agregarPreterito("übt");
        üben.agregarImperativoNuevo(reg);

        Ver zeichnen = new Ver("zeichnen", "zeichn", est, "dibujar", T);
        zeichnen.agregarParticipio("gezeichnet");
        zeichnen.agregarPreterito("zeichnet");
        zeichnen.agregarImperativoNuevo("zeichne", "zeichnet", "zeichnen");



        //todo: Marcador. Deportes
        T = new String[]{"deporte"};
        Ver spielen = new Ver("spielen", "spiel", "jugar", T);
        spielen.agregarParticipio("gespielt");
        spielen.agregarPreterito("spielt");
        spielen.agregarImperativoNuevo(reg);




        //todo: Marcador. La ciudad.
        T = new String[]{"ciudad"};
        Ver mieten = new Ver("mieten", "miet", est, "alquilar; rentar", T);
        mieten.agregarParticipio("gemietet");
        mieten.agregarPreterito("mietet");
        mieten.agregarImperativoNuevo(reg);

        Ver leben = new Ver("leben", "leb", "vivir", T);
        leben.agregarParticipio("gelebt");
        leben.agregarPreterito("lebte");
        leben.agregarImperativoNuevo(reg);
        leben.agregarDescripción(new String[]{"Sinonimo con 'wohnen'. En general, 'leben' se usa para indicar que vives en un país o en una ciudad.", "'Mein Bruder lebt in München.'"});

        Ver wohnen = new Ver("wohnen", "wohn", "vivir [en una casa]", T);
        wohnen.agregarParticipio("gewohnt");
        wohnen.agregarTag("casa");
        wohnen.agregarPreterito("wohnt");
        wohnen.agregarImperativoNuevo(reg);
        wohnen.agregarDescripción(new String[]{"Sinonimo con 'leben'. En general se usa para indicar la vivienda, o la calle en la que vives.", "'Ich wohne in Schlossstraße', 'Er wohnt in einer kleinen Wohnung'"});

        Ver limpiar;



    }
    //todo: Marcador para el final
    //todo: lel
    //todo: Lel



    //Define un verbo
    @Override
    public void definir() {

        if (this.TienePrefijo) {
            System.out.println(this.prefijo + "|" + this.raiz.getNombre() + " - " + this.significado);
        } else {
            System.out.println(this.verbo + " - " + this.significado);
        }


        String[][] arr = new String[3][2];
        arr[0][0] = "      ich " + this.presente[0];
        arr[1][0] = "       du " + this.presente[1];
        arr[2][0] = "er/sie/es " + this.presente[2];
        arr[0][1] = "      wir " + this.presente[3];
        arr[1][1] = "      ihr " + this.presente[4];
        arr[2][1] = "  sie/Sie " + this.presente[5];
        Control.arrPrint(arr);

        if (this.participio != null) {
            System.out.print("Participio: " + this.participio + ", Hilfsverb " + this.hilfsverb);
        } else {
            System.out.print("Ningun participio agregado. ");
        }

        if (this.imperativo != null) {
            System.out.print("Imperativo: " + this.imperativo[0] + ", " + this.imperativo[1]);
        } else {
            System.out.print("Ningun imperativo agregado. ");
        }

        System.out.println("Temas: " + Arrays.toString(this.tags));
    }








    /**
     //Consola que te deja elejir una cantidad de ejercicios/preguntas dado un ArrayList<E>
     public static int ElejirCantidad (ArrayList<Ver> listaVerbos, Scanner sc) {
     System.out.println("Hay " + (listaVerbos.size()-1) + " verbos en este tema.");
     int número;
     while (true) {
     System.out.println("¿Cuántos deséa practicar?");
     String intento = sc.nextLine();
     try {
     número = Integer.parseInt(intento); //Procesa input del usuario. Regresa un entero funcional.
     if (número < 1) {
     System.out.println("El número " + número + " es demasiado chico. ");
     continue;
     } else if (número > listaVerbos.size()-1) {
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


    //todo: Esto tiene que irse
    //Escoje aleatoriamente 'número' verbos de una lista de verbos.
    public static ArrayList<Ver> escojerAleatorio(ArrayList<Ver> listaVerbos, int número) {
        ArrayList<Ver> lista = new ArrayList<Ver>(1); //Una lista temporanea que se utiliza para generar la String[] preguntas.
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




    //CONSOLA
    /**
     * Imprime una lista de verbos dado un ArrayList<Ver> que los contiene todos
     * AÚN NO SE ESCRIBE ESTE CODIGO PORQUE NO SE COMO SE GUARDARÁ LA INFORMACIÓN DE LOS VERBOS
     * @param tema el ArrayList<Ver> de los verbos
     */
    //todo: this is almost certainly incorrect.
    public static void imprimirListaChido(ArrayList<Ver> tema) {
        //System.out.println("¡Aún no me escriben! :) Vuelve pronto...");

        String[][] arr = new String[tema.size()][5];
        //Nota: El primer ringlón es para el display, pero la lista de sustantivos empieza
        //con el sustantivo nulo cuyo no se imprimirá, o sea que se balancéa.
        arr[0][0] = "Verbo";
        arr[0][1] = "Participio";
        arr[0][2] = "Auxiliar";
        arr[0][3] = "Significado";
        arr[0][4] = "Atributos";

        for (int i = 1; i < tema.size(); i++) {
            Ver actual = tema.get(i);
            arr[i][0] = actual.verbo;
            arr[i][1] = actual.participio;
            arr[i][2] = actual.hilfsverb;
            arr[i][3] = actual.significado;
            arr[i][4] = "";
            for (int j = 0; j < actual.tags.length - 1; j++) {
                arr[i][4] += actual.tags[j] + "; ";
            }
            arr[i][4] += actual.tags[actual.tags.length - 1];
        }
        Control.arrPrint(arr);
    }



}
