import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
public class Ver extends Palabra{
    public static final String Ver = "Ver";
    public static final String nullEntry = "---";



    /**
     * Genera una lista de listas. [AQUÍ SE GENERA CONTROL.VERBOS]
     * Aquí se agregan las listas temáticas que se créan arribita.
     * @return Control.Verbos
     */
    public static ArrayList<ArrayList<Ver>> GeneradorVer() {
        // Todas las lsitas de verbos se oguardan en una lista de listas:
        ArrayList<ArrayList<Ver>> Verbos = new ArrayList<ArrayList<Ver>>();

        //1
        Verbos.add(Moverse); Moverse.add(new Ver(moverse)); //Este es el verbo nulo.
        //2
        Verbos.add(Cuerpo); Cuerpo.add(new Ver(cuerpo)); //Se tendrán que agregar todos los temas manualmente; desconozco de una manera más facil.
        //3
        Verbos.add(Básico); Básico.add(new Ver(básico));
        //4
        Verbos.add(Modal); Modal.add(new Ver(modal));
        //5
        Verbos.add(Clima); Clima.add(new Ver(clima));
        //6
        Verbos.add(Menos_básico); Menos_básico.add(new Ver(menos_básico));
        //7
        Verbos.add(Tecnología); Tecnología.add(new Ver(tecnología));
        //8
        Verbos.add(Comida); Comida.add(new Ver(comida));
        //9
        Verbos.add(Escuela); Escuela.add(new Ver(escuela));
        //10
        Verbos.add(Misceláneo); Misceláneo.add(new Ver(misceláneo));
        //11
        Verbos.add(Casa); Casa.add(new Ver(casa));
        //12
        Verbos.add(Expresarse); Expresarse.add(new Ver(expresarse));
        //13
        Verbos.add(Vista); Vista.add(new Ver(vista));
        //14
        Verbos.add(Tienda); Tienda.add(new Ver(tienda));
        //15
        Verbos.add(Auxiliar); Auxiliar.add(new Ver(auxiliar));
        //16
        Verbos.add(Bahnhof); Bahnhof.add(new Ver(bahnhof));
        //17
        Verbos.add(Objeto); Objeto.add(new Ver(objeto));
        //18
        Verbos.add(Mente); Mente.add(new Ver(mente));



        crearVerbos(); //Crea los verbos (abajito) y los mete a estas listas.

        System.out.print("VERBOS: " + Verbos.size() + " LISTAS INICIALIZADAS");
        return Verbos;
    }


//----------------------------------------------------------------------------------------------------------------------

    //Presente: Yo como pescado
    //Participio: Yo comí pescado


    //Constructor para verbos normales. Solo se incluyen las conjugaciones presente (präsens). Las demás se agregan despues.
    public Ver(String Verbo, String[] Präsens, String Significado, String[] Tags) {
        this.verbo = Verbo;
        if (Präsens.length != 6) {throw new NullPointerException("Error: Formato de verbo " + Verbo + " no aceptado");}
        this.presente = Präsens;
        this.significado = Significado;

        //Tag time
        this.tags = new String[0];
        for (String current : Tags) {
            this.agregarTag(current);
        }
    }

    //Constructor nulo. Cada lista temática usa uno como su primer entrada. Funciona como indicador del tema.
    public Ver(String tema) { //Se usa como indicador en las listas
        this.nulo = true;
        this.verbo = tema;
    }

    //Constructor para verbos rama. Requiere que ya exista un verbo raiz. Si no existe en el idioma, se creará uno y ya veremos que pex.
    public Ver(String Prefijo, Ver Raiz, String Significado, String[] Tags) {
        this.verbo = Prefijo + Raiz.verbo;
        //System.out.println("!!! " + this.verbo + "!!!");
        this.raiz = Raiz;
        this.tienePrefijo = true;
        this.significado = Significado;
        this.agregarParticipio(Prefijo + Raiz.participio);

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
    }

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

    //Constructor que reemplaza kleinePräsens
    public Ver(String Verbo, String Base, String Significado, String[] Tags) {
        String[] conjugaciones = new String[6];
        conjugaciones[0] = Base + "e";
        conjugaciones[1] = Base + "st";
        conjugaciones[2] = Base + "t";
        conjugaciones[3] = Base + "en";
        conjugaciones[4] = Base + "t";
        conjugaciones[5] = Base + "en";

        this.verbo = Verbo;
        this.presente = conjugaciones;
        this.significado = Significado;

        this.tags = new String[0];
        for (String actual : Tags) {
            this.agregarTag(actual);
        }
        //return new Ver(Verbo, conjugaciones, Significado, Tags);


        this.base = Base;
    }

    public String verbo; //El verbo en imperativo (Lesen, sprechen, etc. )



    public String[] presente; //La manera mas facil y modular de guardar la información del verbo. Es con String[] de 1 por 6: ich; du; er,sie,es; wir; ihr; Sie = 6
    public String significado; //El significado del verbo
    public String[] tags; //Tags. Agregan funcionalidad extra.



    public void agregarTag(String newTag) {
        super.agregarTag(newTag, Ver);
    }


    @Override
    public String getNombre() {return this.verbo;}

    @Override
    public String getSignificado() {return this.significado;}

    public String participio; //Básicamente el pasado (Ich habe gegessen...)
    public void agregarParticipio(String Participio) {
        if (Participio.equals("haben") || Participio.equals("sein")) {throw new NullPointerException("Créo que te has equivocado con el verbo " + this.verbo);}
        this.participio = Participio;
    }


    public String hilfsverb = "haben"; //Puede ser haben o sein. Casi todos son Haben osea que será el default.
    public void agregarHilfsverb(String Hilfsverb) {
        if (!Hilfsverb.equals("haben") && !Hilfsverb.equals("sein")) {
            throw new NullPointerException("Error: Hilfsverb no reconocido");
        }
        this.hilfsverb = Hilfsverb;
    }


    public String[] imperativo; //??Cómo funciona???
    public void agregarImperativo(String Singular, String Plural) {
        this.imperativo = new String[3];
        this.imperativo[0] = Singular; //Ven tu
        this.imperativo[1] = Plural; //Vengan ustedes
        this.imperativo[2] = this.verbo; //Venga usted + que vengan ellos
    }
    public String base;
    public void agregarImperativo() {
        this.imperativo = new String[3];
        this.imperativo[0] = this.base;
        this.imperativo[1] = this.base + "t";
        this.imperativo[2] = this.verbo;
    }


    public String[] preterito;
    public void agregarPreterito(String[] Preterito) {
        if (Preterito.length != 6) {throw new NullPointerException("Error con el preterito de " + this.verbo); }
        this.preterito = Preterito;
    } //Manual
    public void agregarPreterito(String Base) {
        this.preterito = new String[6];
        this.preterito[0] = Base + "e";
        this.preterito[1] = Base + "est";
        this.preterito[2] = Base + "e";
        this.preterito[3] = Base + "en";
        this.preterito[4] = Base + "et";
        this.preterito[5] = Base + "en";
    }// -e, -est, -e, -en, -et, -en


    public boolean reflexivo = false;
    public void esReflexivo(boolean EsReflexivo) {
        this.reflexivo = EsReflexivo;
    }


    //Esto se usará en caso de que haya verbos cuya raiz sea este verbo. Ejemplo: Verbo = Sprechen, ramas.get(0) = ansprechen.
    //Nos dejará practicar listas deverbos cuya raiz es la misma. Será util ya que esto sucede a menudo.
    public ArrayList<Ver> ramas = new ArrayList<Ver>();


    //Verbo nulo. Sirve como indicador en las listas temáticas de abajo.
    public boolean nulo = false;


    //Esto se usa para los verbos concatinativos / que tienen prefijo. (ansprechen, por ejemplo)
    public boolean tienePrefijo = false;
    public String prefijo = null; //El prefijo, si lo tiene

    //El verbo raiz. Tengo entendido que se conjuga de la misma manera. En tod0 caso, los primeros verbos no serán así.
    public Ver raiz = null; //Por ejemplo, en el verbo ansprechen, raiz = sprechen

//----------------------------------------------------------------------------------------------------------------------

    //todo: Agregar errores si se trata de añadir algo que ya se añadió.

//todo: aregar examen de participios perfectos.
    //todo: Agregar indikativo e Imperativo (son el pasado simple y commandos)
    //todo: La meta es llegar al punto en el que todos los verbos estén categorizados con tags que NO SEAN BÁSICO / MENOS_BÁSICO


    //todo: Verbos separables: Constuctor debe actualizar preterito.
    //Los verbos están divididos manualmente por categorias. Será posible agregarles mas tags si es necesario.
    //Aqui se inicializarán los verbos.
    public static void crearVerbos() {
        String[] T;

        //[Auxiliares]
        T = new String[]{"auxiliar"};
        Ver haben = new Ver("haben", new String[]{"habe", "hast", "hat", "haben", "habt", "haben"}, "tener", T);
        haben.agregarParticipio("gehabt");
        haben.agregarPreterito(new String[]{"hatte", "hattest", "hatte", "hatten", "hattet", "hatten"});
        haben.agregarImperativo("hab", "habt");

        Ver sein = new Ver("sein", new String[]{"bin", "bist", "ist", "sind", "seid", "sind"}, "ser", T);
        sein.agregarHilfsverb("sein");
        sein.agregarParticipio("gewesen");
        sein.agregarPreterito(new String[]{"war", "warst", "war", "waren", "wart", "waren"});
        sein.agregarImperativo("sei", "seid");
        sein.imperativo[2] = "seien";

        Ver werden = new Ver("werden", new String[]{"werde", "wirst", "wird", "werden", "werdet", "werden"}, "llegar a ser", T);
        werden.agregarHilfsverb("sein");
        werden.agregarParticipio("geworden");
        werden.agregarPreterito(new String[]{"wurde", "wurdest", "wurde", "wurden", "wurdet", "wurden"});
        werden.agregarImperativo("werde", "werdet");

        //[---Auxiliares]

        //[   objeto]
        T = new String[]{"objeto"};

        Ver bringen = new Ver("bringen", "bring", "traer", T);
        bringen.agregarParticipio("gebracht");
        bringen.agregarPreterito(new String[]{"brachte", "brachtest", "brachte", "brachten", "brachtet", "brachten"});
        bringen.agregarImperativo("bring", "bringt");

        Ver finden = new Ver("finden", new String[]{"finde", "findest", "findet", "finden", "findet", "finden"}, "encontrar", T);
        finden.agregarParticipio("gefunden");
        finden.agregarPreterito(new String[]{"fand", "fandest", "fand", "fanden", "fandet", "fanden"});
        finden.agregarImperativo("find", "findet");

        Ver machen = new Ver("machen", "mach", "hacer [empieza con m]", T);
        machen.agregarParticipio("gemacht");
        machen.agregarPreterito("macht");
        machen.agregarImperativo();

        Ver aufmachen = new Ver("auf", machen, "abrir [empieza con 'a']", T);

        Ver zumachen = new Ver("zu", machen, "cerrar [empieza con 'z']", T);

        Ver schließen = new Ver("schließen", new String[]{"schließe", "schließt", "schließt", "schließen", "schließt", "schließen"}, "cerrar [empieza con 's']", T);
        schließen.agregarParticipio("geschlossen");
        schließen.agregarPreterito(new String[]{"schloss", "schlossest", "schloss", "schlossen", "schlosst", "schlossen"});
        schließen.agregarImperativo("schließ", "schließt");

        Ver öffnen = new Ver("öffnen", new String[]{"öffne", "öffnest", "öffnet", "öffnen", "öffnet", "öffnen"}, "abrir [empieza con 'ö']", T);
        öffnen.agregarParticipio("geöffnet");
        öffnen.agregarPreterito("öffnet");
        öffnen.agregarImperativo("öffne", "öffnet");

        Ver tun = new Ver("tun", new String[]{"tue", "tust", "tut", "tun", "tut", "tun"}, "hacer [empieza con 't']", T);
        tun.agregarParticipio("getan");
        tun.agregarPreterito(new String[]{"tat", "tatest", "tat", "taten", "tatet", "taten"});
        tun.agregarImperativo("tu", "tut");

        Ver nehmen = new Ver("nehmen", new String[]{"nehme", "nimmst", "nimmt", "nehmen", "nehmt", "nehmen"}, "tomar [un objeto]", T);
        nehmen.agregarParticipio("genommen");
        nehmen.agregarPreterito(new String[]{"nahm", "nahmst", "nahm", "nahmen", "nahmt", "nahmen"});
        nehmen.agregarImperativo("nimm", "nehmt");



        Ver stellen = new Ver("stellen", "stell", "poner [parado]", T);
        stellen.agregarParticipio("gestellt");
        stellen.agregarPreterito("stellt");
        stellen.agregarImperativo();

        Ver stehen = new Ver("stehen", "steh", "estar parado", T);
        //stehen.agregarHilfsverb("sein");
        stehen.agregarParticipio("gestanden");
        stehen.agregarPreterito(new String[]{"stand", "standest", "stand", "standen", "standet", "standen"});
        stehen.agregarImperativo();


        Ver legen = new Ver("legen", "leg", "poner [acostar]", T);
        legen.agregarParticipio("gelegt");
        legen.agregarPreterito("legt");
        legen.agregarImperativo();
        legen.esReflexivo(true);

        Ver liegen = new Ver("liegen", "lieg", "estar acostado", T);
        liegen.agregarParticipio("gelegen");
        liegen.agregarTag("casa");
        liegen.agregarPreterito(new String[]{"lag", "lagst", "lag", "lagen", "lagt", "lagen"});
        liegen.agregarImperativo();


        Ver setzen = new Ver("setzen", new String[]{"setze", "setzt", "setzt", "setzen", "setzt", "setzen"}, "poner [sentar]", T);
        setzen.agregarParticipio("gesetzt");
        //setzen.agregarHilfsverb("sein");
        setzen.agregarPreterito("setzt");
        setzen.agregarImperativo("setz", "setzt");
        setzen.esReflexivo(true);

        Ver sitzen = new Ver("sitzen", new String[]{"sitze", "sitzt", "sitzt", "sitzen", "sitzt", "sitzen"}, "estar sentado", T);
        sitzen.agregarParticipio("gesessen");
        sitzen.agregarImperativo("sitz", "sitzt");
        sitzen.agregarPreterito(new String[]{"saß", "saßest", "saß", "saßen", "saßt", "saßen"});


        Ver hängen = new Ver("hängen [estado]", "häng", "estar colgado", T);
        hängen.agregarPreterito(new String[]{"hing", "hingst", "hing", "hingen", "hingt", "hingen"});
        hängen.agregarImperativo("häng", "hängt");
        hängen.agregarParticipio("gehangen");

        Ver hängen2 = new Ver("hängen [acción]", "häng", "colgar", T);
        hängen2.agregarPreterito("hängt");
        hängen2.agregarImperativo("häng", "hängt");
        hängen2.agregarParticipio("gehängt");


        //[---objeto]




        //-------------------------------Básicos------------------------------------------------
        T = new String[]{"básico"};
        Ver arbeiten = new Ver("arbeiten", new String[]{"arbeite", "arbeitest", "arbeitet", "arbeiten", "arbeitet", "arbeiten"}, "trabajar", T);
        arbeiten.agregarParticipio("gearbeitet");
        arbeiten.agregarPreterito(new String[]{"arbeitete", "arbeitetest", "arbeitete", "arbeiteten", "arbeitetet", "arbeiteten"});
        arbeiten.agregarImperativo("arbeit", "arbeitet");

        Ver bekommen = new Ver("bekommen", new String[]{"bekomme", "bekommst", "bekommt", "bekommen", "bekommt", "bekommen"}, "recibir [empieza con 'b']", T);
        bekommen.agregarParticipio("bekommen"); //irregular
        bekommen.agregarHilfsverb("sein");
        bekommen.agregarPreterito(new String[]{"bekam", "bekamst", "bekam", "bekamen", "bekamt", "bekamen"});
        bekommen.agregarImperativo("bekomm", "bekommt");

        Ver bleiben = new Ver("bleiben", new String[]{"bleibe", "bleibst", "bleibt", "bleiben", "bleibt", "bleiben"}, "quedarse", T);
        bleiben.agregarParticipio("geblieben");
        bleiben.agregarHilfsverb("sein");
        bleiben.agregarPreterito(new String[]{"blieb", "bliebst", "blieb", "blieben", "bliebt", "blieben"});
        bleiben.agregarImperativo("bleib", "bleibt");

        Ver brauchen = new Ver("brauchen", new String[]{"brauche", "brauchst", "braucht", "brauchen", "braucht", "brauchen"}, "necesitar", T);
        brauchen.agregarParticipio("gebraucht");
        brauchen.agregarPreterito(new String[]{"brauchte", "brauchtest", "brauchte", "brauchten", "brauchtet", "brauchten"});
        brauchen.agregarImperativo("brauch", "braucht");

        Ver erzählen = new Ver("erzählen", "erzähl", "contar [un cuento]", T);
        erzählen.agregarParticipio("erzählt");
        erzählen.agregarPreterito("erzählt");
        erzählen.agregarImperativo("erzähl", "erzählt");

        Ver fahren = new Ver("fahren", new String[]{"fahre", "fährst", "fährt", "fahren", "fahrt", "fahren"}, "ir [no a pie]", T);
        fahren.agregarParticipio("gefahren");
        fahren.agregarHilfsverb("sein");
        fahren.agregarTag("moverse");
        fahren.agregarPreterito(new String[]{"fuhr", "fuhrst", "fuhr", "fuhren", "fuhrt", "fuhren"});
        fahren.agregarImperativo("fahr", "fahrt");

        Ver gehen = new Ver("gehen", "geh", "ir [a pie]", T);
        gehen.agregarParticipio("gegangen");
        gehen.agregarHilfsverb("sein");
        gehen.agregarTag("moverse");
        gehen.agregarPreterito(new String[]{"ging", "gingst", "ging", "gingen", "gingt", "gingen"});
        gehen.agregarImperativo("geh", "geht");

        Ver folgen = new Ver("folgen", "folg", "seguir", T);
        folgen.agregarParticipio("gefolgt");
        folgen.agregarHilfsverb("sein");
        folgen.agregarPreterito("folgt");
        folgen.agregarTag("moverse");
        folgen.agregarImperativo("folg", "folgt");

        Ver geben = new Ver("geben", new String[]{"gebe", "gibst", "gibt", "geben", "gebt", "geben"}, "dar", T);
        geben.agregarParticipio("gegeben");
        geben.agregarPreterito(new String[]{"gab", "gabst", "gab", "gaben", "gabt", "gaben"});
        geben.agregarImperativo("gib", "gebt");

        Ver glauben = new Ver("glaube", "glaub", "creer", T);
        glauben.agregarParticipio("geglaubt");
        glauben.agregarPreterito("glaubt");
        glauben.agregarImperativo("glaub", "glaubt");

        Ver kennen = new Ver("kennen", "kenn", "conocer", T);
        kennen.agregarParticipio("gekannt");
        kennen.agregarPreterito("kannt");
        kennen.agregarImperativo();

        Ver kommen = new Ver("kommen", "komm", "venir", T);
        kommen.agregarParticipio("gekommen");
        kommen.agregarTag("moverse");
        kommen.agregarHilfsverb("sein");
        kommen.agregarPreterito(new String[]{"kam", "kamst", "kam", "kamen" ,"kamt", "kamt"});
        kommen.agregarImperativo();

        Ver lassen = new Ver("lassen", new String[]{"lasse", "lässt", "lässt", "lassen", "lasst", "lassen"}, "dejar", T);
        lassen.agregarParticipio("gelassen");
        lassen.agregarPreterito(new String[]{"ließ", "ließest", "ließ", "ließen", "ließt", "ließen"});
        lassen.agregarImperativo("lass", "lasst");

        Ver leben = new Ver("leben", "leb", "vivir [en un país]", T);
        leben.agregarParticipio("gelebt");
        leben.agregarPreterito("lebte");
        leben.agregarImperativo();

        Ver wohnen = new Ver("wohnen", "wohn", "vivir [en una casa]", T);
        wohnen.agregarParticipio("gewohnt");
        wohnen.agregarTag("casa");
        wohnen.agregarPreterito("wohnt");
        wohnen.agregarImperativo();

        Ver spielen = new Ver("spielen", "spiel", "jugar", T);
        spielen.agregarParticipio("gespielt");
        spielen.agregarPreterito("spielt");
        spielen.agregarImperativo();

        Ver suchen = new Ver("suchen", "such", "buscar", T);
        suchen.agregarParticipio("gesucht");
        suchen.agregarImperativo();
        suchen.agregarPreterito("sucht");



        Ver versuchen = new Ver("versuchen", "versuch", "intentar", T);
        versuchen.agregarParticipio("versucht");
        versuchen.agregarPreterito("versucht");
        versuchen.agregarImperativo();

        Ver zeigen = new Ver("zeigen", "zeig", "mostrar", T);
        zeigen.agregarParticipio("gezeigt");
        zeigen.agregarPreterito("zeigt");
        zeigen.agregarImperativo();

        //--------------------------------/basicos


        //todo: Marcador
        //Expresarse
        T = new String[]{"expresarse"};

        Ver bestehen = new Ver("bestehen [empieza con 'i']", new String[]{"bestehe", "bestehst", "besteht", "bestehen", "besteht", "bestehen"}, "insistir", T);
        bestehen.agregarParticipio("bestanden");
        bestehen.agregarPreterito(new String[]{"bestand", "bestandest", "bestand", "bestanden", "bestandet", "bestanden"});
        bestehen.agregarImperativo("besteh", "besteht");

        Ver heißen = new Ver("heißen", "heiß", "llamarse", T);
        heißen.agregarParticipio("geheißen");
        heißen.agregarPreterito(new String[]{"hieß", "hießest", "hieß", "hießen", "hießt", "hießen"});
        heißen.agregarImperativo("heiß", "heißt");

        Ver nennen = new Ver("nennen", "nenn", "nombrar [mencionar]", T);
        nennen.agregarParticipio("gennant");
        nennen.agregarPreterito("nannt");
        nennen.agregarImperativo();

        Ver ernennen = new Ver("ernennen", "ernenn", "nombrar [designar]", T);
        ernennen.agregarParticipio("ernannt");
        ernennen.agregarPreterito("ernannt");
        ernennen.agregarImperativo();

        Ver sagen = new Ver("sagen", "sag", "decir", T);
        sagen.agregarParticipio("gesagt");
        sagen.agregarPreterito("sagt");
        sagen.agregarImperativo();

        Ver sprechen = new Ver("sprechen", new String[]{"spreche", "sprichst", "spricht", "sprechen", "sprecht", "sprechen"}, "hablar [un idioma]", T);
        sprechen.agregarParticipio("gesprochen");
        sprechen.agregarPreterito(new String[]{"sprach", "sprachst", "sprach", "sprachen", "spracht", "sprachen"});
        sprechen.agregarImperativo("sprich", "sprecht");

        Ver reden = new Ver("reden", new String[]{"rede", "redest", "redet", "reden", "redet", "reden"}, "hablar [de algo]", T);
        reden.agregarParticipio("geredet");
        reden.agregarPreterito("redet");
        reden.agregarImperativo("red", "redet");


        //[---Expresarse]




        //todo: Marcador
        //-----------------------Verbos "modales"---------------------------
        T = new String[]{"modal"};

        //NO TIENEN IMPERATIVO LOS MODALES
        String[] NoTienenImperativo = {nullEntry, nullEntry, nullEntry};

        Ver können = new Ver("können", new String[]{"kann", "kannst", "kann", "können", "könnt", "können"}, "poder", T);
        können.agregarParticipio("gekonnt");
        können.agregarPreterito("konnt");
        können.imperativo = NoTienenImperativo;

        Ver dürfen = new Ver("dürfen", new String[]{"darf", "darfst", "darf", "dürfen", "dürft", "dürfen"}, "tener permiso", T);
        dürfen.agregarParticipio("gedurft");
        dürfen.agregarPreterito("durft");
        dürfen.imperativo = NoTienenImperativo;

        Ver mögen = new Ver("mögen", new String[]{"mag", "magst", "mag", "mögen", "mögt", "mögen"}, "gustar", T);
        mögen.agregarParticipio("gemocht");
        mögen.agregarPreterito("mocht");
        mögen.imperativo = NoTienenImperativo;

        Ver müssen = new Ver("müssen", new String[]{"muss", "musst", "muss", "müssen", "müsst", "müssen"}, "tener que", T);
        müssen.agregarParticipio("gemusst");
        müssen.agregarPreterito("musst");
        müssen.imperativo = NoTienenImperativo;

        Ver sollen = new Ver("sollen", new String[]{"soll", "sollst", "soll", "sollen", "sollt", "sollen"}, "deber", T);
        sollen.agregarParticipio("gesollt");
        sollen.agregarPreterito("sollt");
        sollen.imperativo = NoTienenImperativo;

        Ver wollen = new Ver("wollen", new String[]{"will", "willst", "will", "wollen", "wollt", "wollen"}, "querer [hacer algo]", T);
        wollen.agregarParticipio("gewollt");
        wollen.agregarPreterito("wollt");
        wollen.imperativo = NoTienenImperativo;

        //----------------------/Verbos "modales"---------------------------

        //<Moverse>-------------------------------------------------------------------------------------

        //</Moverse>-------------------------------------------------------------------------------------

//nadar, ahogarse, secar, mojar, pescar, empapar(??)

        //[La Mente]
        T = new String[]{"mente"};

        glauben.agregarTag("mente");
        kennen.agregarTag("mente");

        Ver denken = new Ver("denken", "denk", "pensar", T);
        denken.agregarParticipio("gedacht");
        denken.agregarPreterito(new String[]{"dachte", "dachtest", "dachte", "dachten", "dachtet", "dachten"});
        denken.agregarImperativo("denk", "denkt");

        Ver erkennen = new Ver("erkennen", "erkenn", "reconocer", T);
        erkennen.agregarParticipio("erkannt");
        erkennen.agregarTag("vista");
        erkennen.agregarPreterito("erkannt");
        erkennen.agregarImperativo();

        Ver erinnern = new Ver("erinnern", new String[]{"erinnere", "erinnerst", "erinnert", "erinnern", "erinnert", "erinnern"}, "acordarse", T);
        erinnern.agregarParticipio("erinnert");
        erinnern.agregarImperativo("erinnere", "erinnert");
        erinnern.agregarPreterito("erinnert");

        Ver meinen = new Ver("meinen", "mein", "opinar", T);
        meinen.agregarParticipio("gemeint");
        meinen.agregarPreterito("meint");
        meinen.agregarImperativo("mein", "meint");

        Ver wissen = new Ver("wissen", new String[]{"weiß", "weißt", "weiß", "wissen", "wisst", "wissen"}, "saber", T);
        wissen.agregarParticipio("gewusst");
        wissen.agregarPreterito("wusst");
        wissen.agregarImperativo("wisse", "wisst");



        //entender, olvidar, entender, confundir / estar confundido
        //[---Mente]



        //todo: Marcador
        //[Cuerpo]
        T = new String[]{"cuerpo"};



        Ver hören = new Ver("hören", "hör", "oir", T);
        hören.agregarParticipio("gehört");
        hören.agregarPreterito("hört");
        hören.agregarImperativo("hör", "hört");
        hören.agregarTag("cuerpo");

        //Ver schmecken = new Ver();
        //Ver riechen = new Ver();
        //Ver berühren = new Ver();

        Ver fühlen = new Ver("fühlen", "fühl", "sentirse", T);
        fühlen.agregarParticipio("gefühlt");
        fühlen.agregarPreterito("fühlt");
        fühlen.agregarImperativo();
        fühlen.esReflexivo(true);

        Ver weh_tun = new Ver("weh ", tun, "doler", T); //el espacio es muy importante

        //lastimarse, recuperarse, enfermarse, sanarse, mejorarse(?), aturdirse

        //[-Cuerpo]


        //todo: Marcador
        //<Clima>-------------------------------
        T = new String[]{"clima"};

        Ver regnen = new Ver("regnen", new String[]{nullEntry, nullEntry, "regnet", nullEntry, nullEntry, "regnen"}, "llover", T);
        regnen.agregarParticipio("geregnet");
        regnen.agregarHilfsverb("sein");
        regnen.agregarPreterito(new String[]{nullEntry, nullEntry, "regnete", nullEntry, nullEntry, "regneten"});
        regnen.imperativo = new String[]{nullEntry, nullEntry};

        Ver schneien = new Ver("schneien", "schnei", "nevar", T);
        schneien.agregarParticipio("geschneit");
        schneien.agregarHilfsverb("sein");
        schneien.agregarPreterito("schneit");
        schneien.agregarImperativo();

        //Amanecer, atardecer, oscurecer,
        //</Clima>-------------------------------



        //[Expresarse]
        //todo: Marcador
        T = new String[]{"expresarse"};
        Ver rufen = new Ver("rufen", "ruf", "llamar [a alguien]", T);
        rufen.agregarParticipio("gerufen");
        rufen.agregarImperativo();
        rufen.agregarPreterito(new String[]{"rief", "riefst", "rief", "riefen", "rieft", "riefen"});
        //[/Expresarse]



        //todo: Marcador
        //Vista
        T = new String[]{"vista"};

        Ver sehen = new Ver("sehen", new String[]{"sehe", "siehst", "sieht", "sehen", "seht", "sehen"}, "ver", T);
        sehen.agregarParticipio("gesehen");
        sehen.agregarPreterito(new String[]{"sah", "sahst", "sah", "sehen", "saht", "sehen"});
        sehen.agregarImperativo("sieh", "seht");
        sehen.agregarTag("cuerpo");

        Ver ansehen = new Ver("an", sehen, "mirar", T);

        Ver aussehen = new Ver("aus", sehen, "verse", T);

        Ver scheinen = new Ver("scheine", "schein", "brillar", T );
        scheinen.agregarParticipio("gescheinen");
        scheinen.agregarPreterito(new String[]{"schien", "schienst", "schien", "schienen", "schient", "schienen"});
        scheinen.agregarImperativo("schein", "scheint");

        Ver schauen = new Ver("schauen", "schau", "mirar [nada en particular]", T);
        schauen.agregarPreterito("schaut");
        schauen.agregarParticipio("geschaut");
        schauen.agregarImperativo("schau", "schaut");

        Ver anschauen = new Ver("an", schauen, "mirar [un objeto]", T);

        Ver ausschauen = new Ver("aus", schauen, "admirar", T);

        //Vista



        //todo: Marcador
        //Tecnología
        T = new String[]{"tecnología"};

        Ver schalten = new Ver("schalten", new String[]{"schalte", "schaltest", "schaltet", "schalten", "schaltet", "schalten"}, "?????", T);
        schalten.agregarParticipio("geschaltet");
        schalten.agregarPreterito("schaltet");
        schalten.agregarImperativo("schalt", "schaltet");

        Ver einschalten = new Ver("ein", schalten, "encender", T);
        Ver ausschalten = new Ver("aus", schalten, "apagar", T);
        Ver fernsehen = new Ver("fern", sehen, "ver la tele", T);

        Ver anrufen = new Ver("an", rufen, "llamar por teléfono", new String[]{"misceláneo"});
        anrufen.agregarTag("tecnología");
        //tecnología



        //todo: Marcador
        //<menos básico>----------------------------------------------------------------------------------------------------------------------
        T = new String[]{"menos_básico"};

        Ver ankommen = new Ver("an", kommen, "llegar", T);
        ankommen.agregarHilfsverb("sein");
        ankommen.agregarTag("moverse");



        Ver beginnen = new Ver("beginnen", "beginn", "empezar", T);
        beginnen.agregarParticipio("begonnen");
        beginnen.agregarPreterito(new String[]{"begann", "begannst", "begann", "begannen", "begannt", "begannen"});
        beginnen.agregarImperativo();

        Ver betreffen = new Ver("betreffen", new String[]{"betreffe", "betriffst", "betrifft", "beterffen", "betrefft", "betreffen"}, "afectar", T);
        betreffen.agregarParticipio("betroffen");
        betreffen.agregarImperativo("betriff", "betrefft");
        betreffen.agregarPreterito(new String[]{"betraf", "betrafst", "betraf", "betrafen", "betraft", "betrafen"});

        Ver bieten = new Ver("bieten", new String[]{"biete", "bietest", "bietet", "bieten", "bietet", "bieten"}, "ofrecer", T);
        bieten.agregarParticipio("geboten");
        bieten.agregarPreterito(new String[]{"bot", "botest", "bot", "boten", "botet", "boten"});
        bieten.agregarImperativo("biet", "bietet");

        Ver darstellen = new Ver("dar", stellen, "representar", T);

        Ver entsprechen = new Ver("entsprechen", new String[]{"entspreche", "entsprichst", "entspricht", "entsprechen", "entsprecht", "entsprechen"}, "corresponder", T);
        entsprechen.agregarParticipio("entsprochen");
        entsprechen.agregarPreterito(new String[]{"entsprach", "entsprachst", "entsprach", "entsprachen", "entspracht", "entsprachen"});
        entsprechen.agregarImperativo("entsprich", "entsprecht");

        Ver entstehen = new Ver("entstehen", "entsteh", "originar", T);
        entstehen.agregarHilfsverb("sein");
        entstehen.agregarParticipio("entstanden");
        entstehen.agregarImperativo("entsteh", "entsteht");
        entstehen.agregarPreterito(new String[]{"entstand", "entstandest", "entstand", "entstanden", "entstandet", "entstanden"});

        Ver entwickeln = new Ver("entwickeln", new String[]{"entwickele", "entwickelst", "entwickelt", "entwickeln", "entwickelt", "entwickeln"}, "desarollar", T);
        entwickeln.agregarParticipio("entwickelt");
        entwickeln.agregarPreterito("entwickelt");
        entwickeln.agregarImperativo("entwickele", "entwickelt");

        Ver erhalten = new Ver("erhalten", new String[]{"erhalte", "erhältst", "erhält", "erhalten", "erhaltet", "erhalten"}, "recibir [empieza con 'e']", T);
        erhalten.agregarParticipio("erhalten");
        erhalten.agregarImperativo("erhalt", "erhaltet");
        erhalten.agregarPreterito(new String[]{"erheilt", "erheiltest", "erheilt", "erheilten", "erheiltet", "erheilten"});

        Ver erscheinen = new Ver("erscheinen", "erschein", "aparecer", T);
        erscheinen.agregarHilfsverb("sein");
        erscheinen.agregarParticipio("erschienen");
        erscheinen.agregarImperativo();
        erscheinen.agregarPreterito(new String[]{"erschien", "erschienst", "erschien", "erschienen", "erschient", "erschienen"});

        Ver fallen = new Ver("fallen", new String[]{"falle", "fällst", "fällt", "fallen", "fallt", "fallen"}, "caer", T);
        fallen.agregarHilfsverb("sein");
        fallen.agregarParticipio("gefallen");
        fallen.agregarPreterito(new String[]{"fiel", "fielst", "fiel", "fielen", "fielt", "fielen"});
        fallen.agregarImperativo("fall", "fallt");

        Ver führen = new Ver("führen", "führ", "dirigir", T);
        führen.agregarParticipio("geführt");
        führen.agregarImperativo();
        führen.agregarPreterito("führt");

        Ver gehören = new Ver("gehören", "gehör", "pertenecer", T);
        gehören.agregarParticipio("gehört");
        gehören.agregarImperativo();
        gehören.agregarPreterito("gehört");

        Ver gelten = new Ver("gelten", new String[]{"gelte", "gilst", "gilt", "gelten", "geltet", "gelten"}, "ser valido", T);
        gelten.agregarParticipio("gegolten");
        gelten.agregarPreterito(new String[]{"galt", "galtest", "galt", "galten", "galtet", "galten"});
        gelten.agregarImperativo("gilt", "geltet");

        Ver gewinnen = new Ver("gewinnen", "gewinn", "ganar", T);
        gewinnen.agregarParticipio("gewonnen");
        gewinnen.agregarImperativo();
        gewinnen.agregarPreterito(new String[]{"gewann", "gewannst", "gewann", "gewannen", "gewannt", "gewannen"});

        Ver kaufen = new Ver("kaufen", "kauf", "comprar", T);
        kaufen.agregarParticipio("gekauft");
        kaufen.agregarTag("tienda");
        kaufen.agregarPreterito("kauft");
        kaufen.agregarImperativo();

        Ver kosten = new Ver("kosten", new String[]{"koste", "kostest", "kostet", "kosten", "kostet", "kosten"}, "costar", T);
        kosten.agregarPreterito("kostet");
        kosten.agregarParticipio("gekostet");
        kosten.agregarImperativo("kost", "kostet");
        kosten.agregarTag("tienda");

        Ver einkaufen = new Ver("ein", kaufen, "ir de compras", T);
        einkaufen.agregarTag("tienda");

        Ver verschenken = new Ver("verschenken", "verschenk", "regalar", T);
        verschenken.agregarParticipio("verschenkt");
        verschenken.agregarTag("tienda");
        verschenken.agregarPreterito("verschenkt");
        verschenken.agregarImperativo();

        Ver lachen = new Ver("lachen", "lach", "reirse", T);
        lachen.agregarParticipio("gelacht");
        lachen.agregarImperativo();
        lachen.agregarPreterito("lacht");

        Ver laufen = new Ver("laufen", new String[]{"laufe", "läufst", "läuft", "laufen", "lauft", "laufen"}, "correr", T);
        laufen.agregarHilfsverb("sein");
        laufen.agregarParticipio("gelaufen");
        laufen.agregarTag("moverse");
        laufen.agregarImperativo("lauf", "lauft");
        laufen.agregarPreterito(new String[]{"lief", "liefst", "lief", "liefen", "lieft", "liefen"});

        Ver reisen = new Ver("reisen", new String[]{"reise", "reist", "reist", "reisen", "reist", "reisen"}, "viajar", T);
        reisen.agregarHilfsverb("sein");
        reisen.agregarParticipio("gereist");
        reisen.agregarTag("moverse");
        reisen.agregarImperativo("reis", "reist");
        reisen.agregarPreterito("reist");



        Ver schlafen = new Ver("schlafen", new String[]{"schlafe", "schläfst", "schläft", "schlafen", "schlaft", "schlafen"}, "dormir", T);
        schlafen.agregarParticipio("geschlafen");
        schlafen.agregarTag("casa");
        schlafen.agregarImperativo("schlaf", "schlaft");
        schlafen.agregarPreterito(new String[]{"schlief", "schliefst", "schlief", "schliefen", "schlieft", "schliefen"});

        Ver einschlafen = new Ver("ein", schlafen, "dormirse", T);
        einschlafen.agregarHilfsverb("sein");
        einschlafen.agregarTag("casa");

        Ver aufstehen = new Ver("auf", stehen, "pararse [levantarse]", T);
        aufstehen.agregarHilfsverb("sein");

        Ver tragen = new Ver("tragen", new String[]{"trage", "trägst", "trägt", "tragen", "tragt", "tragen"}, "llevar [cargar]", T);
        tragen.agregarParticipio("getragen");
        tragen.agregarTag("tienda");
        tragen.agregarPreterito(new String[]{"trug", "trugst", "trug", "trugen", "trugt", "trugen"});
        tragen.agregarImperativo("trag", "tragt");

        Ver tragen2 = new Ver("tragen", new String[]{"trage", "trägst", "trägt", "tragen", "tragt", "tragen"}, "traer puesto", T);
        tragen2.agregarParticipio("getragen");
        tragen2.agregarTag("tienda");
        tragen2.agregarPreterito(new String[]{"trug", "trugst", "trug", "trugen", "trugt", "trugen"});
        tragen2.agregarImperativo("trag", "tragt");

        Ver verbinden = new Ver("verbinden", new String[]{"verbinde", "verbindest", "verbindet", "verbinden", "verbindet", "verbinden"}, "conectar", T);
        verbinden.agregarParticipio("verbunden");
        verbinden.agregarPreterito(new String[]{"verband", "verbandest", "verband", "verbanden", "verbandet", "verbanden"});
        verbinden.agregarImperativo("verbind", "verbindet");

        Ver vergehen = new Ver("vergehen", "vergeh", "transcurrir", T);
        vergehen.agregarParticipio("vergangen");
        vergehen.agregarHilfsverb("sein");
        vergehen.agregarPreterito(new String[]{"verging", "vergingst", "verging","vergingen", "vergingt", "vergingen"});
        vergehen.agregarImperativo("vergeh", "vergeht");

        Ver verlieren = new Ver("verlieren", "verlier", "perder", T);
        verlieren.agregarParticipio("verloren");
        verlieren.agregarPreterito(new String[]{"verlor", "verlorst", "verlor", "verloren", "verlort", "verloren"});
        verlieren.agregarImperativo();

        Ver vorstellen = new Ver("vor", stellen, "presentar", T);
        vorstellen.verbo += " [empieza con 'p']";

        Ver vorstellen2 = new Ver("vor", stellen, "imaginarse", T);
        vorstellen2.verbo += " [empieza con 'i']";

        Ver wachen = new Ver("wachen", "wach", "velar", T);
        wachen.agregarParticipio("gewacht");
        wachen.agregarTag("casa");
        wachen.agregarPreterito("wacht");
        wachen.agregarImperativo();

        Ver aufwachen = new Ver("auf", wachen, "despertarse", T);
        aufwachen.agregarHilfsverb("sein");
        aufwachen.agregarTag("casa");

        Ver warten = new Ver("warten", new String[]{"warte", "wartest", "wartet", "warten", "wartet", "warten"}, "esperar [tiempo]", T);
        warten.agregarParticipio("gewartet");
        warten.agregarImperativo("wart", "warte");
        warten.agregarPreterito("wartet");

        Ver erwarten = new Ver("erwarten", new String[]{"erwarte", "erwartest", "erwartet", "erwarten", "erwartet", "erwarten"}, "esperar [algo de alguien]", T);
        erwarten.agregarParticipio("erwartet");
        erwarten.agregarPreterito("erwartet");
        erwarten.agregarImperativo("erwart", "erwartet");

        Ver werfen = new Ver("werfen", new String[]{"werfe", "wirfst", "wirft", "werfen", "werft", "werfen"}, "tirar", T);
        werfen.agregarParticipio("geworfen");
        werfen.agregarPreterito(new String[]{"warf", "warfst", "warf", "warfen", "warft", "warfen"});
        werfen.agregarImperativo("wirf","werft");

        Ver ziehen = new Ver("ziehen", "zieh", "jalar", T);
        ziehen.agregarParticipio("gezogen");
        ziehen.agregarImperativo();
        ziehen.agregarPreterito(new String[]{"zog", "zogst", "zog", "zogen", "zogt", "zogen"});

        Ver ziehen2 = new Ver("ziehen", "zieh", "mudarse", T);
        ziehen2.agregarParticipio("geziehen");
        ziehen2.agregarTag("casa");
        ziehen2.agregarPreterito(new String[]{"zieh", "ziehst", "zieh", "ziehen", "zieht", "ziehen"});
        ziehen2.agregarImperativo("zeih", "zeiht");

        Ver anziehen = new Ver("an", ziehen, "ponerse", new String[]{"misceláneo"});
        anziehen.agregarTag("tienda");



        //ESCUELA------------------------------------------------------
        T = new String[]{"escuela"};

        Ver fragen = new Ver("fragen", "frag", "preguntar", T);
        fragen.agregarParticipio("gefragt");
        fragen.agregarPreterito("fragt");
        fragen.agregarImperativo("frag", "fragt");

        Ver verstehen = new Ver("verstehen", "versteh", "entender", T);
        verstehen.agregarParticipio("verstanden");
        verstehen.agregarImperativo();
        verstehen.agregarPreterito(new String[]{"verstand", "verstandest", "verstand", "verstanden", "verstandet", "verstanden"});

        Ver fehlen = new Ver("fehlen", "fehl", "faltar", T);
        fehlen.agregarParticipio("gefehlt");
        fehlen.agregarPreterito("fehlt");
        fehlen.agregarImperativo();

        Ver erklären = new Ver("erklären", "erklär", "explicar", T);
        erklären.agregarParticipio("erklärt");
        erklären.agregarPreterito("erklärt");
        erklären.agregarImperativo();

        Ver helfen = new Ver("helfen", new String[]{"helfe", "hilfst", "hilft", "helfen", "helft", "helfen"}, "ayudar", T);
        helfen.agregarParticipio("geholfen");
        helfen.agregarPreterito(new String[]{"half", "halfst", "half", "halfen" ,"halft", "halfen"});
        helfen.agregarImperativo("hilf", "helft");

        Ver interessieren = new Ver("interessieren", "interessier", "interesar", T);
        interessieren.agregarParticipio("interessiert");
        interessieren.agregarImperativo();
        interessieren.agregarPreterito("interessiert");

        Ver lesen = new Ver("lesen", new String[]{"lese", "liest", "liest", "lesen", "lest", "lesen"}, "leer", T);
        lesen.agregarParticipio("gelesen");
        lesen.agregarPreterito(new String[]{"las", "lasest", "las", "lasen", "last", "lasen"});
        lesen.agregarImperativo("lies", "lest");

        Ver vorlesen = new Ver("vor", lesen, "leer en voz alta", T);

        Ver schreiben = new Ver("schreiben", "schreib", "escribir", T);
        schreiben.agregarParticipio("geschreiben");
        schreiben.agregarPreterito(new String[]{"schrieb", "schriebst", "schrieb", "schrieben", "schriebt", "schrieben"});
        schreiben.agregarImperativo();

        Ver studieren = new Ver("studieren", "studier", "estudiar", T);
        studieren.agregarParticipio("studiert");
        studieren.agregarImperativo();
        studieren.agregarPreterito("studiert");

        Ver zählen = new Ver("zählen", "zähl", "contar [números]", T);
        zählen.agregarParticipio("gezählt");
        zählen.agregarPreterito("zählt");
        zählen.agregarImperativo("zähl", "zählt");

        Ver lehren = new Ver("lehren", "lehr", "enseñar [un tema]", T);
        lehren.agregarParticipio("gelehrt");
        lehren.agregarPreterito("lehrt");
        lehren.agregarImperativo();

        Ver lernen = new Ver("lernen", "lern", "aprender", T);
        lernen.agregarParticipio("gelernt");
        lernen.agregarPreterito("lernt");
        lernen.agregarImperativo();

        Ver bestehen2 = new Ver("bestehen [empieza con 'a']", new String[]{"bestehe", "bestehst", "besteht", "bestehen", "besteht", "bestehen"}, "aprobar", T);
        bestehen2.agregarParticipio("bestanden");
        bestehen2.agregarPreterito(new String[]{"bestand", "bestandest", "bestand", "bestanden", "bestandet", "bestanden"});
        bestehen2.agregarImperativo("besteh", "besteht");

        Ver bedeuten = new Ver("bedeuten", new String[]{"bedeute", "bedeutest", "bedeutet", "bedeuten", "bedeutet", "bedeuten"}, "significar", T);
        bedeuten.agregarParticipio("bedeutet");
        bedeuten.agregarPreterito("bedeutet");
        bedeuten.agregarImperativo("bedeut", "bedeutet");

        Ver vergessen = new Ver("vergessen", new String[]{"vergesse", "vergisst", "vergisst", "vergessen", "vergesst", "vergessen"}, "olvidar", T);
        vergessen.agregarParticipio("vergessen");
        vergessen.agregarImperativo("vergiss", "vergesst");
        vergessen.agregarPreterito(new String[]{"vergaß", "vergaßest", "vergaß", "vergaßen", "vergaßt", "vergaßen"});

        //notieren: Cotizar o apuntar.
        Ver aufschreiben = new Ver("auf", schreiben, "apuntar", T);

        //ESCUELA------------------------------------------------------
        ///Ver antworten
        //Ver beantworten
        //Ver berechnen;
        //Ver bewerten;
        //Ver untersuchen;
        //Ver teilnehmen;

        //Ver halten

        //romper
        //robar

        //tanzen
        //zuzamenbrucht?
        //cantar
        //festejar
        //

        //verbos que no se que pex
        //Ver aufhören;
        //Ver treffen;
        //Ver bilden; // – educar, construir
        //Ver handeln; // – tratar, negociar
        //Ver ergeben; // – resultar
        //Ver wachen = ???????????????????????????????????????
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

        Ver verkaufen = new Ver("verkaufen", "verkauf", "vender", T);
        verkaufen.agregarParticipio("verkauft");
        verkaufen.agregarPreterito("verkauft");
        verkaufen.agregarImperativo();

        //[/tienda]


        //[Comida]
        T = new String[]{"comida"};

        Ver essen = new Ver("essen", new String[]{"esse", "isst", "isst", "essen", "esst", "essen"}, "comer", T);
        essen.agregarParticipio("gegessen");
        essen.agregarPreterito(new String[]{"aß", "aßest", "aß", "aßen", "aßt", "aßen"});
        essen.agregarImperativo("iss", "esst");

        Ver kochen = new Ver("kochen", "koch", "cocinar", T);
        kochen.agregarParticipio("gekocht");
        kochen.agregarPreterito("kocht");
        kochen.agregarImperativo();

        Ver trinken = new Ver("trinken", "trink", "tomar [líquido]", T);
        trinken.agregarParticipio("getrunken");
        trinken.agregarImperativo();
        trinken.agregarPreterito(new String[]{"trank", "trankst", "trank", "tranken", "trankt", "tranken"});

        Ver frühstücken = new Ver("frühstücken", "frühstück", "desayunar", T);
        frühstücken.agregarParticipio("gefrühstück");
        frühstücken.agregarImperativo();
        frühstücken.agregarPreterito("frühstückt");

        //Cenar, comer?

        Ver vespern = new Ver("vespern", "vesper", "merendar", T);
        vespern.agregarPreterito("vespert");
        vespern.agregarImperativo("vespere", "vespert");
        vespern.agregarParticipio("gevespert");

        //[/Comida]

        Ver fangen = new Ver("fangen", "fang", "?????", new String[]{"misceláneo"});
        fangen.agregarParticipio("gefangen");
        fangen.agregarImperativo();
        fangen.agregarPreterito(new String[]{"fing", "fingst", "fing", "fingen", "fingt", "fingen"});
        Ver anfangen = new Ver("an", fangen, "empezar [empieza con 'a']", new String[]{"básico"});

        Ver reservieren = new Ver("reservieren", "reservier", "reservar", new String[]{"misceláneo"});
        reservieren.agregarParticipio("reserviert");
        reservieren.agregarPreterito("reserviert");
        reservieren.agregarImperativo();

        Ver parken = new Ver("parken", "park", "estacionar", new String[]{"misceláneo"});
        parken.agregarParticipio("geparkt");
        parken.agregarPreterito("parkt");
        parken.agregarImperativo();

        Ver lösen = new Ver("lösen", new String[]{"löse", "löst", "löst", "lösen", "löst", "lösen"}, "resolver", new String[]{"misceláneo"});
        lösen.agregarParticipio("gelöst");
        lösen.agregarPreterito("löst");
        lösen.agregarImperativo("lös", "löst");

        Ver vereinbaren = new Ver("vereinbaren", "vereinbar", "concertar", new String[]{"misceláneo"});
        vereinbaren.agregarParticipio("vereinbart");
        vereinbaren.agregarImperativo();
        vereinbaren.agregarPreterito("vereinbart");

        Ver bestellen = new Ver("bestellen", "bestell", "encargar", new String[]{"misceláneo"});
        bestellen.agregarParticipio("bestellt");
        bestellen.agregarPreterito("bestellt");
        bestellen.agregarImperativo();

        Ver übersetzen = new Ver("über", setzen, "cruzar [al otro lado]", new String[]{"misceláneo"});
        übersetzen.agregarHilfsverb("sein");
        übersetzen.agregarTag("moverse");

        Ver übersetzen2 = new Ver("übersetzen", new String[]{"übersetze", "übersetzt", "übersetzt", "übersetzen", "übersetzt", "übersetzen"}, "traducir", new String[]{"misceláneo"});
        übersetzen2.agregarParticipio("übersetzt");
        übersetzen2.agregarPreterito("übersetzt");
        übersetzen2.agregarImperativo("übersetz", "übersetzt");





        Ver loslassen = new Ver("los", lassen, "soltar", new String[]{"misceláneo"});


        //todo: Marcador
        //Tags = new String[]{religión};
        //perdonar, disculpar, evangelizar, rezar, ??? comulgar, bautizar, cazar[se]; amar,

        //todo: Marcador
        //Tags = new String[]{viajar};
        //poner reservieren aqui
        //fahren, gehen; manejar???
        //recuerda que cuando hagamos el sistema para ejecutar exámenes de varios tipos de palabras, no importará que haya pocas palabras DE UN TIPO en una categoría.

    }
//todo: Marcador para el final
    //todo: lel

    //Define un verbo
    public void definir() {
        System.out.println(this.verbo + " - " + this.significado);
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

        System.out.println(Arrays.toString(this.tags));
    }



    /**
     * Te deja buscar un verbo de la lista Control.Verbos
     * @param nombre el verbo que buscas
     * @return el verbo si se encuentra, null si no se encuentra.
     */
    public static Ver buscar(String nombre) {
        for (Lista<Ver> actual: Control.Verbos) {
            for (Ver temp : actual) {
                if (temp.verbo.equals(nombre)) {return temp;}
            }
        }
        return null;
    }



    //Imprime la lista de todos los temas
    /**
    public static void ListarTemas() {
        for (ArrayList<Ver> current : Control.Verbos) {
            System.out.print(current.get(0).verbo + ", ");
        }
    }
    */

    //CONSOLA
    /**
     * Consola que te deja elejir un tema de la lista de temas de los verbos.
     * Echa un NullPointerException si el usuario dice 'cerrar'
     * @param sc el scanner
     * @return
     */
    //que honda con esto

    /**
     public static String ElejirTema(Scanner sc) {
     String tema;
     System.out.println("Diga 'listar temas' para ver los temas. Diga 'cerrar' para cerrar la consola. ");
     while (true) {
     System.out.println("Favor de elejir un tema: ");
     String intento = sc.nextLine();

     switch (intento) {
     case "listar temas": Ver.ListarTemas(); continue;
     case "cerrar": throw new NullPointerException("Ejercicio cerrado");
     } //Solo hay dos inputs raros que pueden ocurrir aqui.

     try {
     Ver.ListaTema(intento); //No se utiliza esta lista, pero nos dice si existe el tema.
     tema = intento; //Le dice al programa que si funcionó. Si no existe, se saldrá arribita.
     } catch (NullPointerException e) { //El tema no existe
     System.out.print("El tema '" + intento + "' no se encuentra. Diga 'listar temas' para ver los temas.");
     System.out.println(" Diga 'cerrar' para cerrar el ejercicio. ");
     continue;
     }
     break;
     }
     return tema;
     }
     */

    //CONSOLA
    /**
     * Consola que te deja elejir un verbo de la lista completa de todos los verbos [Control.Verbos]
     * ECHA NullPointerException si el usuario dice 'cerrar' <-------
     * @param sc el Scanner
     * @return El verbo elejido
     */
    public static Ver ElejirVerbo(Scanner sc) {
        Ver out;
        System.out.println("Favor de elejir un verbo:");
        while (true) {
            String intento = sc.nextLine();
            if (intento.equals("cerrar")) {throw new NullPointerException("Cerrando. ");}
            out = Ver.buscar(intento);
            if (out == null) {
                System.out.println("El verbo '" + intento + "' no se encuentra. Asgúrese que el verbo sea infinitivo y de no capitalizar ninguna letra.");
                continue;
            }
            break;
        }
        return out;
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
     * Regresa la lista de verbos del tema indicado, dado la lista completa "Verbos" [Control.Verbos]
     * Echa un NullPointerException si el tema no se encuentra
     * @param tema el tema cuya lista necesitas
     * @return un ArrayList<Ver> de todos los verbos del tema elejido.
     */
    public static Lista<Ver> ListaTema(String tema) {
        for (Lista<Ver> actual : Control.Verbos) {
            if (actual.get(0).verbo.equals(tema)) {return actual;}
        }
        throw new NullPointerException("El tema '" + tema + "' no se encuentra. Diga 'listar temas verbos' para ver los temas. ");
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



    //PROGRAMA
    //q ase esto?
    public static void OrganizacióndeTags() {
        for (Lista<Palabra> actual: Control.Verbos) {
            System.out.println(actual.nombre + ": " + (actual.size()));
            for (int i = 0; i < actual.size(); i++) {
                System.out.print(actual.get(i).getNombre() + "(" + actual.get(i).getSignificado() + "), ");
            }
            System.out.println();
            System.out.println();
        }
    }



    /**
     * Apuntes:
     *
     * El programa tiene una lista de sustantivos llamado Control.Sustantivos.
     * Esta lista contiene listas de cada tema que reconoce el programa.
     * La primer enrada en la lista de cada tema es un sustantivo nulo que le sirve al programa como indicador del tema.
     * Esto se usa para el control y distribución de sustantivos.
     *
     * Todos los temas tienen un ArrayList y un String publico y estatico.
     * Esto se genera manualmente hasta arriba
     * Se agregan a la lista de listas de sustantivos en la función GeneradorSus.
     *
     * Los Sustantivos se agregan manualmente. Tags se agregan manualmente.
     * Cada sustantivo se inicializa con un String[] de Tags.
     * Cada tag que se agrega a un sustantivo lo mete a la lista del tema.
     *
     * Cada tema se inicializa en el area "LISTA TEMAS"
     * Se tiene que agregar manualmente a generadorSus Y a agregarTag.
     */


}
