import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Arrays;
public class Sus extends Palabra{
    public static final String Sus = "Sus";
    public static final String Sustantivo = "Sustantivo";
    public static final String nullEntry = Control.entradaNula;


    //---------------------------[PARAMETROS]-----------------------------------
    public String sustantivo; //El sustantivo
    public String plural; //Plural del sustantivo
    public String género; //Género del sustantivo. M = Masculino, F = Femenino, N = Neutro
    public String significado; // El significado del sustantivo.
    //public String[] tags; //Tags. Agregan funcionalidad extra.


    @Override
    public String getNombre() {return this.sustantivo;}

    @Override
    public String getSignificado() {return this.significado;}



    //---------------------------[---PARAMETROS]--------------------------------
    //---------------------------[CONSTRUCTORES]--------------------------------

    /**
     * Constructor único para los sustantivos. Recuerda que Sus extends Palabra.
     * @param Sustantivo El sustantivo de forma singular (Tisch)
     * @param Plural El sustantivo de forma plural (Tische)
     * @param Genero El género del sustantivo. Puede ser "M", "F", "N". nullEntry (sin género), o "P" (siempre plural)
     * @param Significado El significado. Se añaden especificaciones con [].
     * @param Tags Los tags del sustantivo.
     * @throws NumberFormatException si se le pasan datos invalidos.
     */
    public Sus(String Sustantivo, String Plural, String Genero, String Significado, String[] Tags) {
        Control.SustantivosListaSingular.add(this);

        this.sustantivo = Sustantivo;
        this.plural = Plural;
        if (!Genero.equals("M") && !Genero.equals("F") && !Genero.equals("N") && !Genero.equals(nullEntry) && !Genero.equals("P")) { //nullEntry = no tiene género; P = siempre plural.
            throw new NumberFormatException("Error: Género inválido");
        } //solo se valen estos géneros.
        this.género = Genero;
        this.significado = Significado;

        this.tags = new String[0];

        for (String current : Tags) {
            this.agregarTag(current);
        }
    }

    //---------------------------[---CONSTRUCTORES]-----------------------------







    //Los sustantivos seran divididos manualmente por categorias. Será posible agregarles mas tags si es necesario.
    //Aqui se inicializarán los sustantivos
    //todo: Marcador del comienzo de la lista lel
    //todo:
    //todo:
    public static void crearSustantivos() {
        String[] T; //los tags


        //todo: Marcador. Fruta, Comida
        T = new String[]{"comida", "fruta"};
        Sus obst = new Sus("Obst", nullEntry, "N", "fruta", T);

        Sus apfel = new Sus("Apfel", "Äpfel", "M", "manzana", T);
        Sus banane = new Sus("Banane", "Bananen", "F", "platano", T);
        Sus pfirsich = new Sus("Pfirsich", "Pfirsiche", "M", "durazno", T);
        Sus zitrone = new Sus("Zitrone", "Zitronen", "F", "limón [amarillo]", T);
        Sus orange = new Sus ("Orange", "Orangen", "F", "naranja", T);
        Sus traube = new Sus("Traube", "Trauben", "F", "uva", T);
        Sus limette = new Sus("Limette", "Limetten", "F", "limón [verde]", T);
        Sus birne = new Sus("Birne", "Birnen", "F", "pera", T);
        //pera, papaya, guayaba, guanabana, mandarina(?), toronja, zandía, melón, kiwi, mango, cereza, fresa, "apricot", todas las moras.


        //todo: Marcador. Verdura, Comida
        T = new String[]{"comida", "verdura"};
        Sus gemüse = new Sus("Gemüse", "Gemüse", "N", "verdura", T);

        Sus karotte = new Sus("Karotte", "Karotten", "F", "zanahoria", T);
        Sus zwiebel = new Sus("Zwiebel", "Zwiebeln", "F", "cebolla", T);

        //pepino, pepinillo, pimiento, cebolla, papa,

        System.out.println(gemüse.getNombre() + ": ");
        for (String actual : gemüse.tags) {System.out.print(actual + ", ");}


        //todo: Marcador. Comida (Solo cosas básicas, no platillos)
        T = new String[]{"comida"};
        Sus essen = new Sus("Essen", "Essen", "N", "comida", T);

        Sus avocado = new Sus("Avocado", "Avocados", "F", "aguacate", T);
        Sus käse = new Sus("Käse", "Käse", "M", "queso", T);
        Sus salat2 = new Sus("Salat", "Salate", "M", "lechuga", T);
        Sus champignon = new Sus("Champignon", "Champignons", "M", "champiñon", T);
        Sus ei = new Sus("Ei", "Eier", "N", "huevo", T);
        Sus schinken = new Sus("Schinken", "Schinken", "M", "jamón", T);


        //todas las carnes, coctel, hamburguesa, h o d d o g, , , jamón, sandwich,
        //cebolla, zanahoria,
        //espaguetti, ravioles,
        //EIN FETUCCINI ALFREDO
        //enchilada, mollete, quesadilla, torta, pambazo, alambre, fajita, mole, gordita, taco, empanada, tamal; muegano, rosca, concha, pan de muerto, mantecada, tlayuda, tasajo(?),
        //sandwhich, hamburguesa, hotdog, BBQ?, alitas, hash browns, pancakes,


        //todo: Marcador. Platillos
        T = new String[]{"platillo"};
        Sus gericht = new Sus("Gericht", "Gerichte", "N", "platillo", T);

        Sus suppe = new Sus("Suppe", "Suppen", "F", "sopa", T);
        Sus salat = new Sus("Salat", "Salate", "M", "ensalada", T);




        //todo: Marcador: Especias
        T = new String[]{"especias"};
        //nuez moscada, asafrán,  oregano, ajo,  curry
        Sus salz = new Sus("Salz", "Salze", "N", "sal", T);
        Sus pfeffer = new Sus("Pfeffer", "Pfeffer", "M", "pimienta", T);
        Sus basilikum = new Sus("Basilikum", nullEntry, "N", "albahaca", T);
        Sus thymian = new Sus("Thymian", "Thymiane", "M", "tomillo", T);
        Sus koriander = new Sus("Koriander", "Koriander", "M", "cilantro", T);
        Sus zimt = new Sus("Zimt", "Zimte", "M", "canela", T);
        Sus zucker = new Sus("Zucker", "Zucker", "M", "azúcar", T);
        Sus muskatnuss = new Sus("Muskatnuss", "Muskatnüsse", "F", "nuez moscada", T);
        Sus safran = new Sus("Safran", "Safrane", "M", "azafrán", T);
        Sus knoblauch = new Sus("Knoblauch", nullEntry, "M", "ajo", T);


        //todo: Marcador. La cocina
        T = new String[]{"cocina"};
        Sus küche = new Sus("Küche", "Küchen", "F", "cocina", T);

        Sus plato = new Sus("Teller", "Teller", "M", "plato", T);
        Sus gabel = new Sus("Gabel", "Gabeln", "F", "tenedor", T);
        Sus messer = new Sus("Messer", "Messer", "N", "cuchillo", T);
        Sus geschirr = new Sus("Geschirr", "Geschirre", "N", "vajilla [cosas de mesa]", T);
        Sus besteck = new Sus("Besteck", "Bestecke", "N", "cubierto", T);

        Sus löffel = new Sus("Löffel", "Löffel", "M", "cuchara", T);
        Sus suppenlöffel = new Sus("Suppenlöffel", "Suppenlöffel", "M", "cuchara sopera", T);
        Sus messlöffel = new Sus("Messlöffel", "Messlöffel", "M", "cuchara medidora", T);
        Sus schöpflöffel = new Sus("Schöpflöffel", "Schöpflöffel", "M", "cucharón", T);

        Sus tasse = new Sus("Tasse", "Tassen", "F", "taza", T);
        Sus pfanne = new Sus("Pfanne", "Pfannen", "F", "sartén", T);
        Sus kochtopf = new Sus("Kochtopf", "Kochtöpfe", "M", "olla", T);
        Sus mörser = new Sus("Mörser", "Mörser", "M", "molcajete", T);
        Sus kaffeemaschine = new Sus("Kaffeemaschine", "Kaffeemaschinen", "F", "cafetera", T);
        Sus teekanne = new Sus("Teekanne", "Teekannen", "F", "tetera", T);
        Sus kanne = new Sus("Kanne", "Kannen", "F", "jarra", T);
        Sus mikrowelle = new Sus("Mikrowelle", "Mikrowellen", "F", "microondas", T);
        Sus toaster = new Sus("Toaster", "Toaster", "M","tostador", T);
        Sus serviette = new Sus("Serviette", "Servietten", "F", "servilleta", T);
        Sus salzstreuer = new Sus("Salzstreuer", "Salzstreuer", "M", "salero", T);
        Sus pfefferstreuer = new Sus("Pfefferstreuer", "Pfefferstreuer", "M", "pimientero", T);
        Sus seiher = new Sus("Seiher", "Seiher", "M", "colador [para pasta]", T);
        Sus sieb = new Sus("Sieb", "Siebe", "N", "colador [líquidos]", T);
        Sus zitronenpresse = new Sus("Zitronenpresse", "Zitronenpressen", "F", "exprimidor [de limones]", T);
        //vitral, topper?



        //todo: Marcador. Muebles de la cocina.
        T = new String[]{"cocina", "mueble"};

        Sus herd = new Sus("Herd", "Herde", "M", "estufa", T);
        Sus backofen = new Sus("Backofen", "Backöfen", "M", "horno [para cocinar]", T);
        Sus stuhl = new Sus("Stuhl", "Stühle", "M", "silla", T);
        Sus tisch = new Sus("Tisch", "Tische", "M", "mesa", T);
        Sus kühlschrank = new Sus("Kühlschrank", "Kühlschränke", "M", "refrigerador", T);



        //todo: Marcador. Bebidas
        T = new String[]{"bebida"};
        Sus getränk = new Sus("Getränk", "Getränke", "N", "bebida", T);
        //tequila?, A T O L E, glüwein??? whisky?

        Sus kaffee = new Sus("Kaffee", "Kaffees", "M", "café [bebida]", T);
        Sus wasser = new Sus("Wasser", "Wasser", "N", "agua", T);
        Sus milch = new Sus("Milch", nullEntry, "F", "leche", T);
        Sus bier = new Sus("Bier", "Biere", "N", "cerveza", T);
        Sus wein = new Sus("Wein", "Weine", "M", "vino", T);
        Sus tee = new Sus("Tee", "Tees", "M", "té", T);
        Sus mineralwasser = new Sus("Mineralwasser", "Mineralwässer", "N", "agua mineral", T);
        Sus saft = new Sus("Saft", "Säfte", "M", "jugo", T);
        Sus erfrischung = new Sus("Erfrischung", "Erfrischungen", "F", "refresco", T);
        Sus apfelwein = new Sus("Apfelwein", "Apfelweine", "M", "sidra", T);


        //todo: Marcador. Salir con amigos
        T = new String[]{"amigos"};

        Sus restaurant = new Sus("Restaurant", "Restaurants", "N", "restaurante", T);
        restaurant.agregarTag("ciudad");
        Sus bar = new Sus("Bar", "Bars", "F", "bar", T);
        bar.agregarTag("ciudad");
        Sus kino = new Sus("Kino", "Kinos", "N", "cine", T);
        kino.agregarTag("ciudad");

        Sus bowling = new Sus("Bowling", nullEntry, "N", "boliche", T);
        Sus menü = new Sus("Menü", "Menüs", "N", "menú" ,T);
        Sus trinkgeld = new Sus("Trinkgeld", "Trinkgelder", "N", "propina", T);
        Sus radio = new Sus("Radio", "Radios", "N", "radio", T);
        Sus vorspeise = new Sus("Vorspeise", "Vorspeisen", "F", "entrada [comida]", T);
        // efectivo vs tarjeta, disco?
        //Mesa para cuatro, ???


        //todo: Marcador. Fiestas y festivos
        T = new String[]{"festivos"};
        Sus fest = new Sus("Fest", "Feste", "N", "fiesta", T);
        fest.agregarTag("amigos");
        Sus brauch = new Sus("Brauch", "Bräuche", "M", "costumbre", T);
        Sus feiertag = new Sus("Feiertag", "Feiertage", "M", "día festivo", T);

        Sus Ostern = new Sus("Ostern", "Ostern", "N", "Pascua", T);
        Sus halloween = new Sus("Halloween", nullEntry, nullEntry, "Halloween", T);
        Sus valentinstag = new Sus("Valentinstag", "Valentinstage", "M", "día de San Valentín", T);
        Sus Weilnachten= new Sus("Weilnachten", "Weilnachten", "N", "Navidad", T);
        Sus geburstag = new Sus("Geburstag", "Geburstage", "M", "cumpleaños", T);
        Sus oktoberfest = new Sus("Oktoberfest", "Oktoberfeste", "N", "Oktoberfest", T);
        Sus karneval = new Sus("Karneval", "Karnevale", "M", "carnaval", T);



        //---------------------------PAISES, CIUDADES, Y SUS MONUMENTOS-------------------------------------------------
        //todo: Marcador. Países, ciudades, y monumentos
        T = new String[]{"país"};
        String[] Tags2 = {"capital", "ciudad"};

        HashMap<Sus, Sus> Naciones = new HashMap<Sus, Sus>();

        //EL GÉNERO DEL PAÍS / CIUDAD SERÁ SU PREFIJO
        //SI NO TIENE NINGUN PREFIJO NO LLEVA GÉNERO!!!
        Sus kanada = new Sus("Kanada", nullEntry, nullEntry, "Canadá", T);
        Sus ottawa = new Sus("Ottawa", nullEntry, nullEntry, "Ottawa", Tags2);
        Naciones.put(ottawa, kanada);

        Sus eeuu = new Sus("Vereinigten Staaten", nullEntry, "P", "los Estados Unidos", T);
        Sus washington = new Sus("Washington D.C.", nullEntry, nullEntry, "Washington D.C.", Tags2);
        Naciones.put(washington, eeuu);

        Sus mexiko = new Sus("Mexiko", nullEntry, nullEntry, "México", T);
        Sus mexiko_stadt = new Sus("Mexiko Stadt", nullEntry, nullEntry, "La Ciudad de México", Tags2);
        Naciones.put(mexiko_stadt, mexiko);

        Sus argentinien = new Sus("Argentinien", nullEntry, nullEntry, "Argentina", T);
        Sus buenos_aires = new Sus("Buenos Aires", nullEntry, nullEntry, "Buenos Aires", Tags2);
        Naciones.put(buenos_aires, argentinien);

        ///varios más



        //---------------------------/PAISES Y SUS CIUDADES-------------------------------------------------

        //El cuerpo y sus partes (suena raro pero así será)------------------------------------------------
        //todo: Marcador
        T = new String[]{"cuerpo"};
        Sus körper = new Sus("Körper", "Körper", "M", "cuerpo", T);

        Sus arm = new Sus("Arm", "Arme", "M", "brazo", T);
        Sus auge = new Sus("Auge", "Augen", "N", "ojo", T);
        Sus bauch = new Sus("Bauch", "Bäuche", "M", "panza", T);
        Sus bein = new Sus("Bein", "Beine", "N", "pierna", T);
        Sus brust = new Sus("Brust", "Brüste", "F", "pecho", T);
        Sus daumen = new Sus("Daumen", "Daumen", "M", "pulgar", T);
        Sus ellbogen = new Sus("Ellbogen", "Ellbogen", "M", "codo [cuerpo]", T);
        Sus finger = new Sus("Finger", "Finger", "M", "dedo", T);
        Sus fuß = new Sus("Fuß", "Füße", "M", "pie", T);
        Sus gesicht = new Sus("Gesicht", "Gesichter", "N", "cara", T);
        Sus haar = new Sus("Haar", "Haare", "N", "pelo", T); //pelo y cabello son lo mismo: Haare. No hay distinción.
        Sus hals = new Sus("Hals", "Hälse", "M", "cuello", T);
        Sus hand = new Sus("Hand", "Hände", "F", "mano", T);
        Sus kehle = new Sus("Kehle", "Kehlen", "F", "garganta", T);
        Sus knie = new Sus("Knie", "Knie", "N", "rodilla", T);
        Sus kopf = new Sus("Kopf", "Köpfe", "M", "cabeza", T);
        Sus körperteil = new Sus("Körperteil", "Körperteile", "M", "parte el cuerpo", T);
        Sus magen = new Sus("Magen", "Mägen", "M", "estómago", T);
        Sus mund = new Sus("Mund", "Münder", "M", "boca", T);
        Sus nase = new Sus("Nase", "Nasen", "F", "nariz", T);
        Sus ohr = new Sus("Ohr", "Ohren", "N", "oreja", T);
        Sus rücken = new Sus("Rücken", "Rücken", "M", "espalda", T);
        Sus shulter = new Sus("Schulter", "Schultern", "F", "hombro", T);
        Sus zeh = new Sus("Zeh", "Zehen", "M", "dedo del pie", T);
        Sus zahn = new Sus("Zahn", "Zähne", "M", "diente", T);
        Sus zunge = new Sus("Zunge", "Zungen", "F", "lengua", T);

        //---El cuerpo y sus partes (suena raro pero así será)------------------------------------------------
        // Las letras---------
        //Las letras--------


        //todo: Marcador
        //EL tiempo y las estaciones del año
        T = new String[]{"tiempo", "día"};

        Sus montag = new Sus("Montag", "Montage", "M", "lunes", T);
        Sus dienstag = new Sus("Dienstag", "Dienstage", "M", "martes", T);
        Sus mittwoch = new Sus("Mittwoch", "Mittwoche", "M", "miércoles", T);
        Sus donnerstag = new Sus("Donnerstag", "Donnerstage", "M", "jueves", T);
        Sus freitag = new Sus("Freitag", "Freitage", "M", "viernes", T);
        Sus samstag = new Sus("Samstag", "Samstage", "M", "sábado", T);
        Sus sonntag = new Sus("Sonntag", "Sonntage", "M", "domingo", T);
        Sus wochenende = new Sus("Wochenende", "Wochenenden", "N", "fin de semana", T);
        Sus woche = new Sus("Woche", "Wochen", "F", "semana", T);

        Sus morgen = new Sus("Morgen", "Morgen", "M", "mañana", T);
        Sus abend = new Sus("Abend", "Abende", "M", "tarde", T);
        Sus tag = new Sus("Tag", "Tage", "M", "día", T);
        Sus nacht = new Sus("Nacht", "Nächte", "F", "noche", T);

        Sus stunde = new Sus("Stunde", "Stunden", "F", "hora", T);
        Sus minute = new Sus("Minute", "Minuten", "F", "minuto", T);
        Sus sekunde = new Sus("Sekunde", "Sekunden", "F", "segundo [tiempo]", T);


        //todo: Marcador
        T = new String[]{"tiempo", "año"};

        Sus januar = new Sus("Januar", "Januare", "M", "enero", T);
        Sus februar = new Sus("Februar", "Februare", "M", "febrero", T);
        Sus märz = new Sus("März", "Märze", "M", "marzo", T);
        Sus april = new Sus("April", "Aprile", "M", "abril", T);
        Sus mai = new Sus("Mai", "Maie", "M", "mayo", T);
        Sus juni = new Sus("Juni", "Junis", "M", "junio", T);
        Sus juli = new Sus("Juli", "Julis", "M", "julio", T);
        Sus august = new Sus("August", "Auguste", "M", "agosto", T);
        Sus september = new Sus("September", "September", "M", "septiembre", T);
        Sus oktober = new Sus("Oktober", "Oktober", "M", "octubre", T);
        Sus november = new Sus("November", "November", "M", "noviembre", T);
        Sus dezember = new Sus("Dezember", "Dezember", "M", "diciembre", T);

        Sus monat = new Sus("Monat", "Monate", "M", "mes", T);
        Sus jahr = new Sus("Jahr", "Jahre", "N", "año", T);


        //todo: Marcador
        T = new String[]{"tiempo", "clima"};

        Sus sommer = new Sus("Sommer", "Sommer", "M", "verano", T);
        sommer.agregarTag("año");
        Sus winter = new Sus("Winter", "Winter", "M", "invierno", T);
        winter.agregarTag("año");
        Sus herbst = new Sus("Herbst", "Herbste", "M", "otoño", T);
        herbst.agregarTag("año");
        Sus frühling = new Sus("Frühling", "Frühlinge", "M", "primavera", T);
        frühling.agregarTag("año");


        //todo: Marcador
        T = new String[]{"clima"};
        Sus wetter = new Sus("Wetter", nullEntry, "N", "tiempo [clima]", T); //= tiempo [clima]
        Sus klima = new Sus("Klima", "Klimas", "N", "clima", T); //= clima. ¿Existe alguna diferencia?

        Sus regen = new Sus ("Regen", "Regen", "M", "lluvia", T);
        Sus platzregnen = new Sus("Platzregnen", "Platzregnen", "M", "aguacero", T);
        Sus schnee = new Sus("Schnee", nullEntry, "M", "nieve", T);
        Sus schneefall = new Sus("Schneefall", "Schneefälle", "M", "nevada", T);
        Sus blitz = new Sus("Blitz", "Blitze", "M", "rayo [relámpago]", T);
        Sus donner = new Sus("Donner", "Donner", "M", "trueno", T);
        Sus wolke = new Sus("Wolke", "Wolken", "F", "nube", T);
        Sus hagel= new Sus("Hagel", "Hagel", "M", "granizo", T);
        Sus hagelschauer = new Sus("Hagelschauer", "Hagelschauer", "M", "granizar", T);
        Sus gewitter = new Sus("Gewitter", "Gewitter", "N", "tormenta", T);


        //[---EL tiempo y las estaciones del año]


        //todo: Marcador
        //[Medidas]
        T = new String[]{"medidas"};

        stunde.agregarTag("medidas");
        minute.agregarTag("medidas");
        sekunde.agregarTag("medidas");
        jahr.agregarTag("medidas");
        woche.agregarTag("medidas");
        monat.agregarTag("medidas");

        /**
        Sus meter = new Sus("Meter", "Meter", "X", "metro [medida]", Tags);
        Sus quadratmeter = new Sus("Quadratmeter", "Quadratmeter", "X", "metro cuadrado", Tags);
        Sus kubikmeter = new Sus("Kubikmeter", "Kubikmeter", "X", "metro cúbico", Tags);
        Sus zentimeter = new Sus("Zentimeter", "Zentimeter", "X", "centímetro", Tags);
        Sus millimeter = new Sus("Millimeter", "Millimeter", "X", "milímetro", Tags);
        Sus kilometer = new Sus("Kilometer", "Kilometer", "X", "kilómetro", Tags);
         */

        Sus grad = new Sus("Grad", "Grade", "M", "grado [medida]", T);

        Sus gramm = new Sus("Gramm", "Gramm", "N", "gramo", T);
        Sus kilogramm = new Sus("Kilogramm", "Kilogramm", "N", "kilogramo", T);


        //[---Medidas]


        //todo: Marcador: Las figuras y geometría.
        //Las figuras---------------------------------------------------
        T = new String[]{"figuras"};

        //Sus  = new Sus("", "", "", "figura", Tags);
        Sus kreis = new Sus("Kreis", "Kreise", "M", "círculo", T);
        Sus dreieck = new Sus("Dreieck", "Dreiecke", "N", "triangulo", T);
        Sus quadrat = new Sus("Quadrat", "Quadrate", "N", "cuadrado", T);
        Sus fünfeck = new Sus("Fünfeck", "Fünfecke", "M", "pentágono", T);
        Sus sechseck = new Sus("Sechseck", "Sechsecke", "M", "hexágono", T);
        Sus kugel = new Sus("Kugel", "Kugeln", "F", "esfera", T);
        Sus würfel = new Sus("Würfel", "Würfel", "M", "cubo", T);
        Sus pyramide = new Sus("Pyramide", "Piramiden", "F", "pirámide", T);
        Sus octaeder = new Sus("Oktaeder", "Oktaeder", "N", "octaedro", T);
        Sus dodekaeder = new Sus("Dodekaeder", "Dodekaeder", "N", "dodecaedro", T);
        Sus ikosaeder = new Sus("Ikosaeder", "Ikosaeder", "N", "icosaedro", T);
        Sus linie = new Sus("Linie", "Linien", "F", "linea", T);
        Sus winkel = new Sus("Winkel", "Winkel", "M", "angulo", T);
        Sus grad2 = new Sus("Grad", "Grade", "M", "grado [angular]", T);
        Sus radian = new Sus("Radiant", "Radiant", "M", "radian", T);
        //Las figuras----------------------------------


        //todo: Marcador: La ropa
        T = new String[]{"ropa"};
        Sus kleidung = new Sus("Kleidung", "Kleidungen", "F", "ropa", T);

        Sus badeanzug = new Sus("Badeanzug", "Badeanzüge", "M", "traje de baño", T);
        Sus hemd = new Sus("Hemd", "Hemden", "N", "camisa", T);
        Sus hose = new Sus("Hose", "Hosen", "F", "pantalón", T);
        Sus hut = new Sus("Hut", "Hüte", "M", "sombrero", T);
        Sus jacke = new Sus("Jacke", "Jacken", "F", "chamarra", T);
        Sus kleid = new Sus("Kleid", "Kleider", "N", "vestido", T);
        Sus krawatte = new Sus("Krawatte", "Krawatten", "F", "corbata", T);
        Sus mantel = new Sus("Mantel", "Mäntel", "M", "abrigo", T);
        Sus pullover = new Sus("Pullover", "Pullover", "M", "suéter", T);
        Sus rock = new Sus("Rock", "Röcke", "M", "falda", T);
        Sus schuh = new Sus("Schuh", "Schuhe", "M", "zapato", T);
        Sus schirmmütze = new Sus("Schirmmütze", "Schirmmützen", "F", "cachucha", T);
        Sus shorts = new Sus("Shorts", "Shorts", "M", "shorts", T);
        Sus socke = new Sus("Socke", "Socken", "F", "calcetín", T);
        Sus stiefel = new Sus("Stiefel", "Stiefel", "M", "bota", T);
        //[---La ropa]


        //todo: Marcador. La casa
        T = new String[]{"casa"};
        Sus haus = new Sus("Haus", "Häuser", "N", "casa", T);
        haus.agregarTag("ciudad");

        Sus erdgeschoß = new Sus("Erdgeschoß", "Erdgeschoße", "N", "planta baja", T);
        Sus dachgeschoß = new Sus("Dachgeschoß", "Dachgeschoße", "N", "ático", T);
        Sus stockwerke = new Sus("Stockwerk", "Stockwerke", "N", "piso [planta]", T);
        Sus fußboden = new Sus("Fußboden", "Fußböden", "M", "piso [suelo]", T);

        Sus keller = new Sus("Keller", "Keller", "M", "sótano", T);
        Sus zimmer = new Sus("Zimmer", "Zimmer", "N", "cuarto [habitación]", T);
        Sus raum = new Sus("Raum", "Räume", "M", "cuarto [espacio]", T);
        Sus wohnzimmer = new Sus("Wohnzimmer", "Wohnzimmer", "N", "sala", T);
        Sus schlafzimmer = new Sus("Schlafzimmer", "Schlafzimmer", "N", "cuarto [recámara]", T);
        Sus esszimmer = new Sus("Esszimmer", "Esszimmer", "N", "comedor", T);
        Sus badezimmer = new Sus("Badezimmer", "Badezimmer", "N", "baño [cuarto]", T);
        küche.agregarTag("casa");

        Sus terrasse = new Sus("Terrasse", "Terrassen", "F", "terraza", T);
        Sus hof = new Sus("Hof", "Höfe", "M", "patio", T);
        Sus balkon = new Sus("Balkon", "Balkone", "M", "balcón", T);
        Sus fenster = new Sus("Fenster", "Fenster", "N", "ventana", T);
        Sus tür = new Sus("Tür", "Türen", "F", "puerta", T);
        Sus garten = new Sus("Garten", "Gärten", "M", "jardín", T);
        Sus straße = new Sus("Straße", "Straßen", "F", "calle", T);

        Sus miete = new Sus("Miete", "Mieten", "F", "renta", T);
        Sus kaution = new Sus("Kaution", "Kautionen", "F", "fianza", T);



        //todo: Marcador. La tienda
        T = new String[]{"tienda"};
        Sus geschäft = new Sus("Geschäft", "Geschäfte", "N", "tienda [negocio]", T);
        geschäft.agregarTag("ciudad");
        Sus bargel = new Sus("Bargeld", nullEntry, "N", "efectivo [dinero]", T);
        //Pagar en efectivo = bar bezahlen. Bar aquí = adj. "en efectivo", bezahlen = pagar.






        //todo: Marcador : La ciudad
        T = new String[]{"ciudad"};
        Sus stadt = new Sus("Stadt", "Städte", "F", "ciudad", T);
        Sus großstadt = new Sus("Großstadt", "Großstädte", "F", "ciudad grande", T);
        Sus großstadtleben = new Sus("Großstadtleben", "Großstadtleben", "N", "vida en una ciudad grande", T);
        Sus Haupstadt = new Sus("Haupstadt", "Hauptstädte", "F", "capital [ciudad]", T);
        Sus dorf = new Sus("Dorf", "Dörfer", "N", "pueblo", T);
        Sus stadtzentrum = new Sus("Stadtzentrum", "Stadtzentren", "N", "centro [de la ciudad]", T);

        Sus Wohnung = new Sus("Wohnung", "Wohnungen", "F", "departamento [empieza con 'w']", T);
        Sus apartment = new Sus("Apartment", "Apartments", "N", "departamento [empieza con 'a']", T);
        Sus wohngemeinschaft = new Sus("Wohngemeinschaft", "Wohngemeinschaften", "F", "piso compartido", T);
        Sus wolkenkratzer = new Sus("Wolkenkratzer", "Wolkenkratzer", "M", "rascacielos", T);
        Sus gebäude = new Sus("Gebäude", "Gebäude", "N", "edificio", T);

        Sus mitbewohner = new Sus("Mitbewohner [Wohnung]", "Mitbewohnernen", "M", "compañero de piso", T);
        Sus mitbewohner2 = new Sus("Mitbewohner [Haus]", "Mitbewohnernen", "M", "vecino", T);

        Sus museum = new Sus("Museum", "Museen", "N", "museo", T);
        Sus kunstmuseum = new Sus("Kunstmuseum", "Kunstmuseen", "N", "museo de arte", T);
        kunstmuseum.agregarTag("arte");
        Sus geschichtsmuseum = new Sus("Geschichtsmuseum", "Geschichtsmuseen", "N", "museo de historia", T);
        Sus krankenhaus = new Sus("Krankenhaus", "Krankenhäuser", "N", "hospital", T);
        krankenhaus.agregarTag("médico");
        Sus platz = new Sus("Platz", "Plätze", "M", "plaza [ciudad]", T);
        Sus palast = new Sus("Palast", "Paläste", "M", "palacio", T);

        Sus verkehr = new Sus("Verkehr", nullEntry, "N", "tráfico", T);
        Sus straßenlaterne = new Sus("Straßenlaterne", "Straßenlaternen", "F", "farol [de calle]", T);
        Sus Baustelle = new Sus("Baustelle", "Baustellen", "F", "construcción [sitio]", T);
        Sus Altbau = new Sus("Altbau", "Altbauten", "M", "edificio nuevo", T);
        Sus Neubau = new Sus("Neubau", nullEntry, "M", "edificio viejo", T);

        //[---La ciudad]





        //todo: Marcador. Geografía.
        T = new String[]{"geografía"};
        Sus land = new Sus("Land", "Länder", "N", "país", T);

        Sus höhle = new Sus("Höhle", "Höhlen", "F", "cueva", T);
        Sus wasserfall = new Sus("Wasserfall", "Wasserfälle", "M", "cascada [natural]", T);
        Sus naturschutzpark = new Sus("Naturschutzpark", "Naturschutzparks", "M", "parque nacional", T);


        //frontera, río, oceano, mar, jungla, desierto, planicia, bosque, cueva, playa, lago, cañon, montaña, volcán, ...
        //...pero es más importante saber decir "Voy a llegar tarde", y eso aún no lo se...


        //todo: Marcador
        //[Los muebles y cosas de la casa]
        T = new String[]{"mueble"}; //y cosas

        Sus bett = new Sus("Bett", "Betten", "N", "cama", T);

        Sus kommode = new Sus("Kommode", "Kommoden", "F", "cómoda", T);
        Sus sofa = new Sus("Sofa", "Sofas", "N", "sofá", T);

        //[El médico]
        T = new String[]{"médico"};
        Sus rezept = new Sus("Rezept", "Rezepte", "F", "prescripción", T);
        Sus versichertenkarte = new Sus("Versichertenkarte", "Versichertenkarten", "F", "tarjeta del seguro", T);
        Sus krankmeldung = new Sus("Krankmeldung", "Krankmeldungen", "F", "baja de enfermedad", T);
        Sus Medizin = new Sus("Medizin", nullEntry, "F", "medicina", T);
        Sus apotheke = new Sus("Apotheke", "Apotheken", "F", "farmacia", T);
        Sus arzt = new Sus("Arzt", "Ärzte", "M", "médico", T);
        Sus ärztin = new Sus("Ärztin", "Ärztinnen", "F", "médica", T);
        //Sus arzhelferin = new Sus("Arzthelferin", "Arzthelfernen", );
        Sus schmerz = new Sus("Schmerz", "Schmerzen", "M", "dolor", T);
        //Sus krankenschwester = new Sus("enfermera", Tags);
        Sus termin = new Sus("Termin", "Termine", "M", "cita [para el médico]", T);

        //[---El médico]


        //todo: Marcador
        //[La escuela]
        T = new String[]{"escuela"};

        Sus schule = new Sus("Schule", "Schulen", "F", "escuela", T);

        Sus filzstift = new Sus("Filzstift", "Filzstifte", "M",  "plumón", T);
        Sus klasse = new Sus("Klasse", "Klassen", "F", "clase", T);
        Sus klassenzimmer = new Sus("Klassenzimmer", "Klassenzimmer", "N", "aula", T);
        Sus korridor = new Sus("Korridor", "Korridore", "M", "pasillo", T);
        korridor.agregarTag("casa");
        Sus kreide = new Sus("Kreide", "Kreiden", "F", "gis", T);
        Sus kurs = new Sus("Kurs", "Kurse", "M", "curso", T);
        Sus lehrer = new Sus("Lehrer", "Lehrer", "M","maestro", T);
        Sus lehrerin = new Sus("Lehrerin", "Lehrerinnen", "F", "maestra", T);
        Sus professor = new Sus("Professor", "Professoren", "M", "profesor", T);
        Sus professorin = new Sus("Professorin", "Professoninnen", "F", "profesora", T);
        Sus radiergummi = new Sus("Radiergummi", "Radiergummis", "M", "borrador [para lápiz]", T);
        Sus rechner = new Sus("Rechner", "Rechner", "M", "calculadora", T);
        rechner.agregarTag("tecnología");
        Sus rechner2 = new Sus("Rechner", "Rechner", "M", "computadora", T);
        rechner2.agregarTag("tecnología");
        Sus rucksack = new Sus("Rucksack", "Rucksäcke", "M", "mochila", T);
        Sus schreibtisch = new Sus("Schreibtisch", "Schreibtische", "M", "escritório", T);
        Sus student = new Sus("Student", "Studenten", "M", "estudiante [M]", T);
        Sus studentin = new Sus("Studentin", "Studentinen", "F", "estudiante [F]", T);
        Sus tafel = new Sus("Tafel", "Tafeln", "F", "pizarra", T);

        //Exámen, prueba
        //[/La escuela]


        //todo: Marcador
        //[La tecnología]
        T = new String[]{"tecnología"};
        Sus technologie = new Sus("Technologie", "Technologien", "F", "tecnología", T);

        Sus fernseher = new Sus("Fernseher", "Fernseher", "N", "tele", T);
        Sus handy = new Sus("Handy", "Handys", "N", "teléfono", T);
        Sus elektrizität = new Sus("Elektrizität", nullEntry, "F", "electricidad", T);
        Sus Kabel = new Sus("Kabel", "Kabel", "N", "cable [eléctrico]", T);
        //laptop?

        //[/La tecnología]


        //todo: Marcador
        //[transporte]
        T = new String[]{"transporte"};

        Sus auto = new Sus("Auto", "Autos", "N", "coche", T);
        Sus flugzeug = new Sus("Flugzeug", "Flugzeuge", "N", "avión", T);
        Sus fahrrad = new Sus("Fahrrad", "Fahrräder", "N", "bicicleta", T);
        Sus laster = new Sus("Laster", "Laster", "M", "camión", T);
        Sus bus = new Sus("Bus", "Busse", "M", "autobús", T);
        Sus hubschrauber = new Sus("Hubschrauber", "Hubschrauber", "M", "helicóptero", T);
        Sus u_bahn = new Sus("U-Bahn", "U-Bahnen", "F", "metro [transporte]", T);
        Sus motorrad = new Sus("Motorrad", "Motorräder", "N", "moto", T);
        Sus rollschuh = new Sus("Rollshuh", "Rollshuhe", "M", "patin [de ruedas]", T);
        Sus schlittschuh = new Sus("Schlittschuh", "Schlittschuhe", "M", "patín [de nieve]", T);
        Sus skateboard = new Sus("Skateboard", "Skateboards", "N", "patineta", T);
        Sus straßenbahn = new Sus("Straßenbahn", "Straßenbahnen", "F", "tranvía", T);
        Sus zug = new Sus("Zug", "Züge", "M", "tren", T);
        Sus taxi = new Sus("Taxi", "Taxis", "N", "taxi", T);

        //[---transporte]


        //[Viajar]

        //hotel, aeropuerto, "hostal", atracción, estación de autobuses
        //también tendrá reserbr, viajar, conocer, ...

        //[---Viajar]


        //[herramientas]

        //HANS, GIBT MIR DAS HAMMER

        //[Construcción (pero no demolición)
        //todo: Marcador. Materiales
        T = new String[]{"materiales"};
        Sus material = new Sus("Material", "Materialien", "N", "material", T);

        Sus stein = new Sus("Stein", "Steine", "M", "piedra", T);
        Sus ziegelstein = new Sus("Ziegelstein", "Ziegelsteine", "M", "ladrillo", T);
        Sus plastik = new Sus("Plastik", nullEntry, "N", "plastico", T);
        Sus metall = new Sus("Metall", "Metalle", "N", "metal", T);
        Sus beton = new Sus("Beton", "Betons", "M", "concreto", T);
        Sus holz = new Sus("Holz", nullEntry, "N", "madera", T);
        Sus stahl = new Sus("Stahl", "Stähle", "M", "acero", T);
        Sus glas = new Sus("Glas", nullEntry, "N", "vidrio", T);

        //[---Construcción]


        //todo: Marcador. El trabajo
        T = new String[]{"trabajo"};
        Sus arbeit = new Sus("Arbeit", "Arbeiten", "F", "trabajo [empleo]", T);

        Sus stärke = new Sus("Stärke", "Stärken", "F", "fuerza [punto fuerte]", T);
        Sus schwäche = new Sus("Schwäche", "Schwächen", "F", "debilidad [punto débil]", T);

        Sus lebenslauf = new Sus("Lebenslauf", "Lebensläufe", "M", "currículum", T);

        Sus position = new Sus("Position", "Positionen", "F", "puesto [en una empresa]", T);
        Sus ausbildung = new Sus("Ausbildung", "Ausbildungen", "F", "formación [educación, entrenamiento]", T);

        Sus vorstellungsgespräch = new Sus("Vorstellungsgespräch", "Vorstellungsgespräche", "N", "entrevista [a cara]", T);
        Sus firma = new Sus("Firma", "Firmen", "F", "empresa", T);



        //todo: Marcdor. Escritura.
        T = new String[]{"escritura"};
        Sus schrift = new Sus("Schrift", "Schriften", "F", "escritura", T);

        Sus buch = new Sus("Buch", "Bücher", "N", "libro", T);
        Sus entwurf = new Sus("Entwurf", "Entwürfe", "M", "borrador [escritura]", T);
        Sus bleistift = new Sus("Bleistift", "Bleistifte", "M", "lápiz", T);
        Sus feder = new Sus("Feder", "Federn", "F", "pluma [escritura]", T);
        tafel.agregarTag("escritura");
        Sus wörterbuch = new Sus("Wörterbuch", "Wörterbücher", "N", "diccionario", T);
        Sus schreibheft = new Sus("Schreibheft", "Schreibhefte", "N", "cuaderno", T);
        Sus presse = new Sus("Presse", "Pressen", "F", "prensa", T);
        Sus abschnitt = new Sus("Abschnitt", "Abschnitte", "M", "párrafo", T);
        Sus seite = new Sus("Seite", "Seiten", "F", "página", T);
        Sus handschrift = new Sus("Handschrift", "Handschriften", "F", "letra [aparencia]", T);
        //periódico, revista, noticias, ...novela,


        //todo: Marcador. Gramática
        T = new String[]{"gramática"};
        Sus grammatik = new Sus("Grammatik", "Grammatiken", "F", "gramática", T);

        Sus buchstabe = new Sus("Buchstabe", "Buchstaben", "M", "letra [carácter]", T);
        Sus wort = new Sus("Wort", "Wörter", "N", "palabra", T);
        Sus rechtschreibfehler = new Sus("Rechtschreibfehler", "Rechtschreibfehler", "M", "falta de ortografía", T);
        Sus vokal = new Sus("Vokal", "Vokale", "M", "vocal [palabras]", T);
        Sus konsonant = new Sus("Konsonant", "Konsonanten", "M", "consonante", T);

        Sus genus = new Sus("Genus", "Genera", "N", "género [palabra]", T);
        Sus neutrum = new Sus("Neutrum", "Neutra", "N", "neutro [género]", T);
        Sus maskulinum = new Sus("Maskulinum", "Maskulina", "M", "masculino [género]", T);
        Sus femininum = new Sus("Femininum", "Feminima", "N", "femenino [género]", T);

        Sus plural = new Sus("Plural", "Plurale", "M", "plural", T);
        Sus substantiv = new Sus("Substantiv", "Subtantive", "N", "sustantivo", T);
        Sus verb = new Sus("Verb", "Verben", "N", "verbo", T);
        Sus adverb = new Sus("Adverb", "Adverbien", "N", "adverbio", T);
        Sus adjektiv = new Sus("Adjektiv", "Adjektive", "N", "adjetivo", T);
        Sus präposition = new Sus("Präposition", "Präpositionen", "F", "preposición", T);

        //todo: Buchstabieren = deletrear

        //todo: Marcador.
        //Los oficios
        //jardinero, panadero, policía, ...


        //todo: Marcador. comunicación
        T = new String[]{"comunicación"};
        Sus kommunikation = new Sus("Kommunikation", nullEntry, "F", "comunicación", T);

        Sus sprache = new Sus("Sprache", "Sprachen", "F", "idioma", T);
        Sus muttersprache = new Sus("Muttersprache", "Muttersprachen", "F", "idioma materno", T);
        Sus fremdsprace = new Sus("Fremdsprache", "Fremdsprachen", "F", "idioma extranjero", T);
        Sus gespräch = new Sus("Gespräch", "Gespräche", "N", "conversación", T);
        Sus telefongespräch = new Sus("Telefongespräch", "Telefongespräche", "N", "conversación telefónica", T);
        Sus diskussion = new Sus("Diskussion", "Diskussionen", "F", "discusión", T);



        //todo: Estudios
        Sus geschichte = new Sus("Geschichte", nullEntry, "F", "historia [disciplina]", T);

    }
    //todo: mar
    //todo: ca
    //todo: dor




    //Imprime la lista de todos los temas
    /**
    public static void ListarTemas() {
        for (Lista<Sus> actual : Control.Sustantivos) {
            System.out.print(actual.nombre + ", ");
        }
    }
     */


    //Define un sustantivo
    @Override
    public void definir() {
        switch (this.género) {
            case "M":
                System.out.print("Der ");
                System.out.println(this.sustantivo + " (" + this.plural + ") " + this.significado);
                System.out.println("MASCULINO");
                break;
            case "F":
                System.out.print("Die ");
                System.out.println(this.sustantivo + " (" + this.plural + ") " + this.significado);
                System.out.println("FEMENINO");
                break;
            case "N":
                System.out.print("Das ");
                System.out.println(this.sustantivo + " (" + this.plural + ") " + this.significado);
                System.out.println("NEUTRO");
                break;
        }

        System.out.println(Arrays.toString(this.tags));
    }



    //todo: Quitar esto
    //Escoje aleatoriamente 'número' sustantivos de una lista de sustantivos.
    public static ArrayList<Sus> escojerAleatorio(ArrayList<Sus> listaSustantivos, int número) {
        ArrayList<Sus> lista = new ArrayList<Sus>(); //Una lista temporanea que se utiliza para generar la String[] preguntas.
        for (int i = 0; i < número; i++) {
            int índice = (int)(listaSustantivos.size()*Math.random());

            if (índice == listaSustantivos.size() || índice == 0 || lista.contains(listaSustantivos.get(índice))) {
                i--;
                continue;
            } // Esto se asegura de que la índice sea válida. Recuerda que la índice '0' es el sustantivo nulo.
            //También se asegura de que no aya entradas dobles.
            lista.add(listaSustantivos.get(índice));
        }
        return lista;
    }


    //Imprime una lista de sustantivos sin formateo grande.
    public static void imprimirListaSimple(ArrayList<Sus> tema) {
        for (int i = 0; i < tema.size() - 1; i++) {
            System.out.print(tema.get(i).sustantivo + ", ");
        }
        System.out.println(tema.get(tema.size() - 1).sustantivo);
    }


    //Imprime una lista de sustantivos con formateo chido
    public static void imprimirListaChido(ArrayList<Sus> tema) {
        String[][] arr = new String[tema.size()][5];
        //Nota: El primer ringlón es para el display, pero la lista de sustantivos empieza
        //con el sustantivo nulo cuyo no se imprimirá, o sea que se balancéa.
        arr[0][0] = "Sustantivo";
        arr[0][1] = "Plural";
        arr[0][2] = "Género";
        arr[0][3] = "Significado";
        arr[0][4] = "Atributos";

        for (int i = 1; i < tema.size(); i++) {
            Sus sus = tema.get(i);
            arr[i][0] = sus.sustantivo;
            arr[i][1] = sus.plural;
            arr[i][2] = sus.género;
            arr[i][3] = sus.significado;
            arr[i][4] = "";
            for (int j = 0; j < sus.tags.length - 1; j++) {
                arr[i][4] += sus.tags[j] + "; ";
            }
            arr[i][4] += sus.tags[sus.tags.length - 1];
        }
        Control.arrPrint(arr);
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
     * Se tiene que agregar manualmente a generadorSus Y a tagAdd.
     */
}



