import java.util.ArrayList;
import java.util.Arrays;
public class Adj extends Palabra{
    public static final String Adj = "Adj";
    public static final String Adjetivo = "Adjetivo";
    public static final String nullEntry = Control.entradaNula;






    //Constructores
    public Adj(String Adjetivo, String Comparativo, String Superlativo, String Significado, String[] Tags) {
        Control.AdjetivosListaSingular.add(this);

        this.adjetivo = Adjetivo;
        this.comparativo = Comparativo;
        this.superlativo = Superlativo;
        this.significado = Significado;

        this.tags = new String[0];
        for (String current: Tags) {
            this.agregarTag(current);
        }



        Control.RevisarColisiones(this);
    }


    public Adj(String Adjetivo, String Significado, String[] Tags) {
        Control.AdjetivosListaSingular.add(this);

        this.adjetivo = Adjetivo;
        this.significado = Significado;
        this.tags = new String[0];
        for (String current : Tags) {
            this.agregarTag(current);
        }


        String[] letras = adjetivo.split("");
        //for (String current  : letras) {System.out.print(current + ", "); }

        //Detecta casos especiales para el comparativo
        if (letras[letras.length - 1].equals("e")) { //si la palabra termina en 'e'
            //System.out.println("Acaba en -e");
            this.comparativo = adjetivo + "r";
        } else if (letras[letras.length - 3].equals("u") && letras[letras.length - 2].equals("e") && letras[letras.length - 3].equals("r")) { //Acaba en -uer
            //System.out.println("Acaba en -uer");
            this.comparativo = "";
            for (int i = 0; i < letras.length - 2; i++) {
                this.comparativo += letras[i];
            }
            this.comparativo += "rer";
        } else if (letras[letras.length - 2].equals("e") && letras[letras.length - 1].equals("l")) {
            //System.out.println("Acaba en -el");
            this.comparativo = "";
            for (int i = 0; i < letras.length - 2; i++) {
                this.comparativo += letras[i];
            }
            this.comparativo += "ler";
        } else {
            //System.out.println("El adjetivo no acaba en -e, -uer, o -el");
            this.comparativo = adjetivo + "er";
        }

        //Superlativo:
        String ultima = letras[letras.length - 1];
        if (ultima.equals("d") || ultima.equals("t") || ultima.equals("s") || ultima.equals("x") || ultima.equals("z") || ultima.equals("ß")) {
            this.superlativo = adjetivo + "esten";
        } else if (letras[letras.length - 3].equals("s") && letras[letras.length - 2].equals("c") || letras[letras.length - 1].equals("h")) { //Acaba en -sch
            this.superlativo = adjetivo + "esten";
        } else {
            //System.out.println("No acaba en nada especial");
            this.superlativo = adjetivo + "sten";
        }

        //System.out.println(this.adjetivo + ", " + this.comparativo + ", " + this.superlativo);


        Control.RevisarColisiones(this);
    } //constructor con solo el adjetivo que crea el comparativo + superlativo


    public Adj(String Adjetivo, String Significado, String[] Tags, boolean quePex) {
        Control.AdjetivosListaSingular.add(this);

        this.adjetivo = Adjetivo;
        this.significado = Significado;

        this.tags = new String[0];
        for (String current: Tags) {
            this.agregarTag(current);
        }




        Control.RevisarColisiones(this);
    } //Constructor sin comparativo o superlativo

    public String adjetivo;
    public String comparativo;
    public String superlativo;
    public String significado;
    //public String[] tags;



    @Override
    public String getNombre() {return this.adjetivo;}

    @Override
    public String getSignificado() {return this.significado;}

    //Hay casos como "rosa" en los que el adjetivo nunca se declina, ni tiene comparativo o superlativo. Esto lo detecta.
    public boolean seDeclina = true;
    public void actualizarDeclinación(boolean SeDeclina) {
        this.seDeclina = SeDeclina;
        if (!seDeclina) {
            this.comparativo = this.adjetivo;
            this.superlativo = this.adjetivo;
        }
    }

    public boolean tieneComYSup = true;
    public void actualizarComYSup(boolean TieneComYSup) {
        this.tieneComYSup = TieneComYSup;
        if (!tieneComYSup) {
            this.comparativo = this.adjetivo;
            this.superlativo = this.adjetivo;
        }
    }
    /**public boolean seFlecciona = true;
     public void actualizarFlección(boolean SeFlecciona) {
     this.seFlecciona = SeFlecciona;
     }*/





    //todo: Si hay sinónimos presente en un examen 'vocabulario', se marcará como falso.
    //todo: Esto no debería ser demasiado dificil de arreglar, pero es un arreglo que
    //todo: tenemos que hacer en todos los ejercicios 'vocabulatrio'

    //todo: Adjetivos inmutables: Nnca cambian, no se declinan, y no tienen comparativo / superlativo. Osea que "Die Blume ist rosaer" no existe; se diría "Die Blume ist mehr rosa".
    //todo: Lista de adjetivos con esta propiedad: orange, rosa,

    //todo: Adjetivos que arreglar: silber(silbrig, silbriger, silbrigsten),

    //todo: durstig.

    public static void crearAdjetivos() {
        String[] Tags;
        //<Colores>------------------------------------
        Tags = new String[]{"color"};

        Adj rot = new Adj("rot", "röter", "rötesten", "rojo", Tags);

        Adj orange = new Adj("orange", "naranja [Adj.]", Tags);
        orange.actualizarDeclinación(false);

        Adj gelb= new Adj("gelb", "amarillo", Tags);
        Adj grün = new Adj("grün", "verde", Tags);
        Adj cyan = new Adj("cyan", "cian", Tags);
        Adj blau = new Adj("blau", "azul", Tags);
        Adj lila = new Adj("lila", "morado", Tags);

        Adj rosa = new Adj("rosa", "rosa", Tags);
        rosa.actualizarDeclinación(false);

        Adj weiß = new Adj("weiß", "blanco", Tags);
        Adj grau = new Adj("grau", "gris", Tags);

        Adj schwarz = new Adj("schwarz", "schwärzer", "schwärzesten", "negro", Tags);
        Adj braun = new Adj("braun", "café", Tags);

        Adj gold = new Adj("golden", "dorado", Tags);

        //Adj silbrig = new Adj("silbrig", "plateado", Tags);
        //silbrig.actualizarComYSup(false);

        Adj hell = new Adj("hell", "claro [color]", Tags);
        Adj dunkel = new Adj("dunkel", "oscuro", Tags);

        //</Colores>------------------------------------

        //<Básico>------------------------------------
        Tags = new String[]{"básico"};

        //alto malo flaco rico
        //Arreglar sinónimos
        //klar

        Adj alt = new Adj("alt", "älter", "ältesten", "viejo", Tags);
        alt.agregarTag("aspecto");

        Adj arm = new Adj("arm", "ärmer", "ärmsten", "pobre", Tags);

        Adj billig = new Adj("billig", "barato", Tags);

        Adj böse = new Adj("böse", "malo [acaba con 'e']", Tags);
        böse.agregarTag("personalidad");

        Adj breit = new Adj("breit", "ancho", Tags);

        Adj dick = new Adj("dick", "gordo", Tags);
        dick.agregarTag("aspecto");

        Adj dumm = new Adj("dumm", "dümmer", "dümmsten", "tonto", Tags);
        dumm.agregarTag("personalidad");

        Adj dünn = new Adj("dünn", "flaco [acaba con 'n']", Tags);
        dünn.agregarTag("aspecto");

        Adj falsch = new Adj("falsch", "falso", Tags);

        Adj fern = new Adj("fern", "lejos", Tags);

        Adj früh = new Adj("früh", "temprano", Tags);

        Adj gern = new Adj("gern", "lieber", "liebsten", "gusto", Tags);

        Adj gesund = new Adj("gesund", "sano", Tags);
        gesund.agregarTag("aspecto");

        Adj glücklich = new Adj("glücklich", "feliz", Tags);
        glücklich.agregarTag("personalidad");
        Adj glücklich2 = new Adj("glücklich", "afortunado", Tags);
        glücklich2.agregarTag("personalidad"    );

        Adj groß = new Adj("groß", "größer", "größten", "grande", Tags);

        Adj gut = new Adj("gut", "besser", "besten", "bueno", Tags);
        gut.agregarTag("personalidad");

        Adj hart = new Adj("hart", "härter", "härtesten", "duro", Tags);

        Adj heiß = new Adj("heiß", "caliente", Tags);

        Adj hoch = new Adj("hoch", "höher", "höchsten", "alto [acaba con 'h']", Tags);

        Adj jung = new Adj("jung", "jünger", "jüngsten", "joven", Tags);
        jung.agregarTag("aspecto");

        Adj kalt = new Adj("kalt", "kälter", "kältesten", "frío", Tags);

        Adj klein = new Adj("klein", "chiquito", Tags);

        Adj klug = new Adj("klug", "klüger", "klügten", "inteligente", Tags);
        klug.agregarTag("personalidad");

        Adj krank = new Adj("krank", "kränker", "kränksten", "enfermo", Tags);
        krank.agregarTag("aspecto");

        Adj kurz = new Adj("kurz", "kürzer", "kürzesten", "corto", Tags);

        Adj lang = new Adj("lang", "länger", "längsten", "largo", Tags);

        Adj laut = new Adj("laut", "alto [acaba con 't']", Tags);
        laut.agregarTag("aspecto");

        Adj leicht = new Adj("leicht", "ligero", Tags);

        Adj leise = new Adj("leise", "silencioso", Tags);

        Adj müde = new Adj("müde", "cansado", Tags);

        Adj nah = new Adj("nah", "näher", "nächsten", "cerca", Tags);

        Adj naß = new Adj("nass", "nässer", "nässesten", "mojado", Tags);

        Adj neu = new Adj("neu", "nuevo", Tags);

        Adj reich = new Adj("reich", "rico", Tags);

        Adj richtig = new Adj("richtig", "correcto", Tags);

        Adj sauber = new Adj("sauber", "limpio", Tags);

        Adj schlank = new Adj("schlank", "flaco [acaba con 'k']", Tags);
        schlank.agregarTag("aspecto");

        Adj schlecht = new Adj("schlecht", "malo [acaba con 't']", Tags);

        Adj schnell = new Adj("schnell", "rápido", Tags);

        Adj schmal = new Adj("schmal", "schmäler", "schmälsten",  "flaco [acaba con 'l']", Tags);

        Adj schmutzig = new Adj ("schmutzig", "sucio", Tags);

        Adj schwach = new Adj("schwach", "schwächer", "schwächsten", "débil", Tags);

        Adj schwer = new Adj("schwer", "pesado", Tags);

        Adj spät = new Adj("spät", "tarde [Adj.]", Tags);

        Adj stark = new Adj("stark", "stärker", "stärksten", "fuerte", Tags);
        stark.agregarTag("aspecto");

        Adj stolz = new Adj("stolz", "stolzer", "stolzesten", "orgulloso", Tags);

        Adj traurig = new Adj("traurig", "triste", Tags);
        traurig.agregarTag("personalidad");

        Adj teuer = new Adj("teuer", "caro", Tags);
        //teuer, teurer, teuersten
        Adj tief = new Adj("tief", "profundo", Tags);

        Adj trocken = new Adj("trocken", "seco", Tags);

        Adj viel = new Adj("viel", "mehr", "meisten", "mucho", Tags);

        Adj warm = new Adj("warm", "wärmer", "wärmsten", "cálido", Tags);

        Adj wenig = new Adj("wenig", "poco", Tags);


        //</Básico>------------------------------------


        //todo: Marcador. Comida
        Tags = new String[]{"comida"};

        Adj salzig = new Adj("salzig", "salado", Tags);
        Adj scharf = new Adj("scharf", "schärfer", "schärfsten", "picante", Tags);
        Adj süß = new Adj("süß", "dulce", Tags);


        //todo: Marcador. Tiempo
        //temprano, tarde, mañana, ayer, ...antes, despues de, ahorita
    }





    //Imprime una lista de sustantivos con formateo chido
    public static void imprimirListaChido(ArrayList<Adj> tema) {
        String[][] arr = new String[tema.size()][5];
        //Nota: El primer ringlón es para el display, pero la lista de sustantivos empieza
        //con el sustantivo nulo cuyo no se imprimirá, o sea que se balancéa.
        arr[0][0] = "Adjetivo";
        arr[0][1] = "Comparativo";
        arr[0][2] = "Superlativo";
        arr[0][3] = "Significado";
        arr[0][4] = "Atributos";

        for (int i = 1; i < tema.size(); i++) {
            Adj actual = tema.get(i);
            arr[i][0] = actual.adjetivo;
            arr[i][1] = actual.comparativo;
            arr[i][2] = actual.superlativo;
            arr[i][3] = actual.significado;
            arr[i][4] = "";
            for (int j = 0; j < actual.tags.length - 1; j++) {
                arr[i][4] += actual.tags[j] + "; ";
            }
            arr[i][4] += actual.tags[actual.tags.length - 1];
        }
        Control.arrPrint(arr);
    }



    //Define un adjetivo
    @Override
    public void definir() {
        System.out.println(this.adjetivo + ": " + this.significado);
        System.out.println("Comparativo: " + this.comparativo + ", Superlativo: " + this.superlativo);
        System.out.println(Arrays.toString(this.tags));
        System.out.println("Aún no acabo con esto...");

        System.out.println("TEMAS: " + Arrays.toString(this.tags));
    }


    //Escoje aleatoriamente 'número' verbos de una lista de verbos.
    public static ArrayList<Adj> escojerAleatorio(ArrayList<Adj> listaAdjetivos, int número) {
        ArrayList<Adj> lista = new ArrayList<Adj>(); //Una lista temporanea que se utiliza para generar la String[] preguntas.
        for (int i = 0; i < número; i++) {
            int índice = (int)(listaAdjetivos.size()*Math.random());
            //System.out.println("Intentando índice " + índice);
            if (índice == listaAdjetivos.size()) {
                //System.out.println("La indice " + índice + " es demasiado grande");
                i--;
                continue;
            } else if (índice == 0) {
                //System.out.println("La índice " + índice  + " es demasiado chica");
                i--;
                continue;
            }  else if (lista.contains(listaAdjetivos.get(índice))) {
                //System.out.println("La índice " + índice + " ya se ha usado");
                i--;
                continue;
            } // Esto se asegura de que la índice sea válida. Recuerda que la índice '0' es el sustantivo nulo.
            //También se asegura de que no aya entradas dobles.
            lista.add(listaAdjetivos.get(índice));
            //System.out.println("Índice " + índice + " añadida");
        }
        return lista;
    }


}
