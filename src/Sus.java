import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Arrays;
public class Sus extends Palabra{
    public static final String Sus = "Sus";
    public static final String nullEntry = "---";


    //---------------------------[PARAMETROS]-----------------------------------
    public String sustantivo; //El sustantivo
    public String plural; //Plural del sustantivo
    public String género; //Género del sustantivo. M = Masculino, F = Femenino, N = Neutro
    public String significado; // El significado del sustantivo.
    public String[] tags; //Tags. Agregan funcionalidad extra.


    @Override
    public String getNombre() {return this.sustantivo;}

    @Override
    public String getSignificado() {return this.significado;}



    //---------------------------[---PARAMETROS]--------------------------------
    //---------------------------[CONSTRUCTORES]--------------------------------

    //Constructor. Very basic.
    public Sus(String Sustantivo, String Plural, String Genero, String Significado, String[] Tags) {
        this.sustantivo = Sustantivo;
        this.plural = Plural;
        if (!Genero.equals("M") && !Genero.equals("F") && !Genero.equals("N") && !Genero.equals(nullEntry) && !Genero.equals("P")) { //nullEntry = no tiene género; P = siempre plural.
            throw new NullPointerException("Error: Género inválido");
        } //solo se valen estos géneros.
        this.género = Genero;
        this.significado = Significado;

        this.tags = new String[0];
        for (String current : Tags) {
            this.agregarTag(current, Sus);
        }
    }

    //---------------------------[---CONSTRUCTORES]-----------------------------







    //Los sustantivos seran divididos manualmente por categorias. Será posible agregarles mas tags si es necesario.
    //Aqui se inicializarán los sustantivos
    //todo: Marcador del comienzo de la lista lel
    //todo:
    //todo:
    public static void crearSustantivos() {
        String[] Tags;

        //<COMIDA>------------------------------------------------------
        //todo: Fruta, Comida
        Tags = new String[]{"comida", "fruta"};
        Sus apfel = new Sus("Apfel", "Äpfel", "M", "manzana", Tags);
        Sus banane = new Sus("Banane", "Bananen", "F", "platano", Tags);
        Sus pfirsich = new Sus("Pfirsich", "Pfirsiche", "M", "durazno", Tags);
        Sus zitrone = new Sus("Zitrone", "Zitronen", "F", "limón amarillo", Tags);
        Sus orange = new Sus ("Orange", "Orangen", "F", "naranja", Tags);
        Sus traube = new Sus("Traube", "Trauben", "F", "uva", Tags);

        //pera, papaya, guayaba, guanabana, mandarina(?), toronja, zandía, melón, kiwi, mango, cereza, fresa, "apricot", todas las moras.


        //todo: Vegetal, Comida
        Tags = new String[]{"comida", "vegetal"};

        Sus karotte = new Sus("Karotte", "Karotten", "F", "zanahoria", Tags);
        //pepino, pepinillo, pimiento, cebolla, papa,


        //todo: Comida
        Tags = new String[]{"comida"};

        Sus avocado = new Sus("Avocado", "Avocados", "F", "aguacate", Tags);
        Sus käse = new Sus("Käse", "Käse", "M", "queso", Tags);
        //sopa, todas las carnes, coctel, hamburguesa, h o d d o g, ensalada, lechuga, jamón, huevos, sandwich, champiñon,
        //cebolla, zanahoria,
        //espaguetti, ravioles,
        //EIN FETUCCINI ALFREDO
        //enchilada, mollete, quesadilla, torta, pambazo, alambre, fajita, mole, gordita, taco, empanada, tamal; muegano, rosca, concha, pan de muerto, mantecada, tlayuda, tasajo(?),
        //sandwhich, hamburguesa, hotdog, BBQ?, alitas, hash browns, pancakes,

        //todo: Especias
        Tags = new String[]{"especias"};

        //nuez moscada, asafrán, sal, pimienta, cilantro, oregano, ajo, albaca, tomillo, canela, azúcar, curry



        //todo: Cocina
        //cocina
        Tags = new String[]{"cocina"};

        //taza, cuchara, cuchillo, sartén, olla, tenedor, mesa, silla, estufa, refrigerador, cafetera, tetera, vitral, topper?,
        //servilleta, salero, pimientero, colador, exprimidor, m o l c a j e t e,


        //todo: Bebidas
        Tags = new String[]{"bebidas"};
        //agua, café, leche, jugo, vino, cerveza, tequila?, té, refresco, agua mineral,  A T O L E, glüwein??? whisky?

        //[---bebidas]


        //---------------------------PAISES, CIUDADES, Y SUS MONUMENTOS-------------------------------------------------
        Tags = new String[]{"país"};

        Sus país = new Sus("Land", "Länder", "N", "país", Tags);
        String[] Tags2 = {"capital", "ciudad"};

        HashMap<Sus, Sus> Naciones = new HashMap<Sus, Sus>();

        //EL GÉNERO DEL PAÍS / CIUDAD SERÁ SU PREFIJO
        //SI NO TIENE NINGUN PREFIJO NO LLEVA GÉNERO!!!
        Sus kanada = new Sus("Kanada", nullEntry, nullEntry, "Canadá", Tags);
        Sus ottawa = new Sus("Ottawa", nullEntry, nullEntry, "Ottawa", Tags2);
        Naciones.put(ottawa, kanada);

        Sus eeuu = new Sus("Vereinigten Staaten", nullEntry, "P", "los Estados Unidos", Tags);
        Sus washington = new Sus("Washington D.C.", nullEntry, nullEntry, "Washington D.C.", Tags2);
        Naciones.put(washington, eeuu);

        Sus mexiko = new Sus("Mexiko", nullEntry, nullEntry, "México", Tags);
        Sus mexiko_stadt = new Sus("Mexiko Stadt", nullEntry, nullEntry, "La Ciudad de México", Tags2);
        Naciones.put(mexiko_stadt, mexiko);

        Sus argentinien = new Sus("Argentinien", nullEntry, nullEntry, "Argentina", Tags);
        Sus buenos_aires = new Sus("Buenos Aires", nullEntry, nullEntry, "Buenos Aires", Tags2);
        Naciones.put(buenos_aires, argentinien);

        ///varios más



        //---------------------------/PAISES Y SUS CIUDADES-------------------------------------------------

        //El cuerpo y sus partes (suena raro pero así será)------------------------------------------------
        //todo: Marcador
        Tags = new String[]{"cuerpo"};

        Sus arm = new Sus("Arm", "Arme", "M", "brazo", Tags);
        Sus auge = new Sus("Auge", "Augen", "N", "ojo", Tags);
        Sus bauch = new Sus("Bauch", "Bäuche", "M", "panza", Tags);
        Sus bein = new Sus("Bein", "Beine", "N", "pierna", Tags);
        Sus brust = new Sus("Brust", "Brüste", "F", "pecho", Tags);
        Sus daumen = new Sus("Daumen", "Daumen", "M", "pulgar", Tags);
        Sus ellbogen = new Sus("Ellbogen", "Ellbogen", "M", "codo [cuerpo]", Tags);
        Sus finger = new Sus("Finger", "Finger", "M", "dedo", Tags);
        Sus fuß = new Sus("Fuß", "Füße", "M", "pie", Tags);
        Sus gesicht = new Sus("Gesicht", "Gesichter", "N", "cara", Tags);
        Sus haar = new Sus("Haar", "Haare", "N", "pelo", Tags); //pelo y cabello son lo mismo: Haare. No hay distinción.
        Sus hals = new Sus("Hals", "Hälse", "M", "cuello", Tags);
        Sus hand = new Sus("Hand", "Hände", "F", "mano", Tags);
        Sus kehle = new Sus("Kehle", "Kehlen", "F", "garganta", Tags);
        Sus knie = new Sus("Knie", "Knie", "N", "rodilla", Tags);
        Sus kopf = new Sus("Kopf", "Köpfe", "M", "cabeza", Tags);
        Sus körper = new Sus("Körper", "Körper", "M", "cuerpo", Tags);
        Sus körperteil = new Sus("Körperteil", "Körperteile", "M", "parte el cuerpo", Tags);
        Sus magen = new Sus("Magen", "Mägen", "M", "estómago", Tags);
        Sus mund = new Sus("Mund", "Münder", "M", "boca", Tags);
        Sus nase = new Sus("Nase", "Nasen", "F", "nariz", Tags);
        Sus ohr = new Sus("Ohr", "Ohren", "N", "oreja", Tags);
        Sus rücken = new Sus("Rücken", "Rücken", "M", "espalda", Tags);
        Sus shulter = new Sus("Schulter", "Schultern", "F", "hombro", Tags);
        Sus zeh = new Sus("Zeh", "Zehen", "M", "dedo del pie", Tags);
        Sus zahn = new Sus("Zahn", "Zähne", "M", "diente", Tags);
        Sus zunge = new Sus("Zunge", "Zungen", "F", "lengua", Tags);

        //---El cuerpo y sus partes (suena raro pero así será)------------------------------------------------
        // Las letras---------
        //Las letras--------


        //todo: Marcador
        //EL tiempo y las estaciones del año
        Tags = new String[]{"tiempo", "día"};
        Sus montag = new Sus("Montag", "Montage", "M", "lunes", Tags);
        Sus dienstag = new Sus("Dienstag", "Dienstage", "M", "martes", Tags);
        Sus mittwoch = new Sus("Mittwoch", "Mittwoche", "M", "miércoles", Tags);
        Sus donnerstag = new Sus("Donnerstag", "Donnerstage", "M", "jueves", Tags);
        Sus freitag = new Sus("Freitag", "Freitage", "M", "viernes", Tags);
        Sus samstag = new Sus("Samstag", "Samstage", "M", "sábado", Tags);
        Sus sonntag = new Sus("Sonntag", "Sonntage", "M", "domingo", Tags);
        Sus wochenende = new Sus("Wochenende", "Wochenenden", "N", "fin de semana", Tags);
        Sus woche = new Sus("Woche", "Wochen", "F", "semana", Tags);

        Sus morgen = new Sus("Morgen", "Morgen", "M", "mañana", Tags);
        Sus abend = new Sus("Abend", "Abende", "M", "tarde", Tags);
        Sus tag = new Sus("Tag", "Tage", "M", "día", Tags);
        Sus nacht = new Sus("Nacht", "Nächte", "F", "noche", Tags);

        Sus stunde = new Sus("Stunde", "Stunden", "F", "hora", Tags);
        Sus minute = new Sus("Minute", "Minuten", "F", "minuto", Tags);
        Sus sekunde = new Sus("Sekunde", "Sekunden", "F", "segundo [tiempo]", Tags);


        //todo: Marcador
        Tags = new String[]{"tiempo", "año"};

        Sus januar = new Sus("Januar", "Januare", "M", "enero", Tags);
        Sus februar = new Sus("Februar", "Februare", "M", "febrero", Tags);
        Sus märz = new Sus("März", "Märze", "M", "marzo", Tags);
        Sus april = new Sus("April", "Aprile", "M", "abril", Tags);
        Sus mai = new Sus("Mai", "Maie", "M", "mayo", Tags);
        Sus juni = new Sus("Juni", "Junis", "M", "junio", Tags);
        Sus juli = new Sus("Juli", "Julis", "M", "julio", Tags);
        Sus august = new Sus("August", "Auguste", "M", "agosto", Tags);
        Sus september = new Sus("September", "September", "M", "septiembre", Tags);
        Sus oktober = new Sus("Oktober", "Oktober", "M", "octubre", Tags);
        Sus november = new Sus("November", "November", "M", "noviembre", Tags);
        Sus dezember = new Sus("Dezember", "Dezember", "M", "diciembre", Tags);

        Sus monat = new Sus("Monat", "Monate", "M", "mes", Tags);
        Sus jahr = new Sus("Jahr", "Jahre", "N", "año", Tags);


        //todo: Marcador
        Tags = new String[]{"tiempo", "clima"};

        Sus sommer = new Sus("Sommer", "Sommer", "M", "verano", Tags);
        sommer.agregarTag("año");
        Sus winter = new Sus("Winter", "Winter", "M", "invierno", Tags);
        winter.agregarTag("año");
        Sus herbst = new Sus("Herbst", "Herbste", "M", "otoño", Tags);
        herbst.agregarTag("año");
        Sus frühling = new Sus("Frühling", "Frühlinge", "M", "primavera", Tags);
        frühling.agregarTag("año");


        //todo: Marcador
        Tags = new String[]{"clima"};

        Sus regen = new Sus ("Regen", "Regen", "M", "lluvia", Tags);
        Sus platzregnen = new Sus("Platzregnen", "Platzregnen", "M", "aguacero", Tags);
        Sus schnee = new Sus("Schnee", nullEntry, "M", "nieve", Tags);
        Sus schneefall = new Sus("Schneefall", "Schneefälle", "M", "nevada", Tags);
        Sus blitz = new Sus("Blitz", "Blitze", "M", "rayo [relámpago]", Tags);
        Sus donner = new Sus("Donner", "Donner", "M", "trueno", Tags);
        Sus wolke = new Sus("Wolke", "Wolken", "F", "nube", Tags);
        Sus hagel= new Sus("Hagel", "Hagel", "M", "granizo", Tags);
        Sus hagelschauer = new Sus("Hagelschauer", "Hagelschauer", "M", "granizar", Tags);
        Sus gewitter = new Sus("Gewitter", "Gewitter", "N", "tormenta", Tags);


        //[---EL tiempo y las estaciones del año]


        //todo: Marcador
        //[Medidas]
        Tags = new String[]{"medidas"};

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

        Sus grad = new Sus("Grad", "Grade", "M", "grado [medida]", Tags);

        Sus gramm = new Sus("Gramm", "Gramm", "N", "gramo", Tags);
        Sus kilogramm = new Sus("Kilogramm", "Kilogramm", "N", "kilogramo", Tags);


        //[---Medidas]


        //todo: Marcador
        //Las figuras---------------------------------------------------
        Tags = new String[]{"figuras"};

        //Sus  = new Sus("", "", "", "figura", Tags);
        Sus kreis = new Sus("Kreis", "Kreise", "M", "círculo", Tags);
        Sus dreieck = new Sus("Dreieck", "Dreiecke", "N", "triangulo", Tags);
        Sus quadrat = new Sus("Quadrat", "Quadrate", "N", "cuadrado", Tags);
        Sus fünfeck = new Sus("Fünfeck", "Fünfecke", "M", "pentágono", Tags);
        Sus sechseck = new Sus("Sechseck", "Sechsecke", "M", "hexágono", Tags);
        Sus kugel = new Sus("Kugel", "Kugeln", "F", "esfera", Tags);
        Sus würfel = new Sus("Würfel", "Würfel", "M", "cubo", Tags);
        Sus pyramide = new Sus("Pyramide", "Piramiden", "F", "pirámide", Tags);
        Sus octaeder = new Sus("Oktaeder", "Oktaeder", "N", "octaedro", Tags);
        Sus dodekaeder = new Sus("Dodekaeder", "Dodekaeder", "N", "dodecaedro", Tags);
        Sus ikosaeder = new Sus("Ikosaeder", "Ikosaeder", "N", "icosaedro", Tags);
        Sus linie = new Sus("Linie", "Linien", "F", "linea", Tags);
        Sus winkel = new Sus("Winkel", "Winkel", "M", "angulo", Tags);
        Sus grad2 = new Sus("Grad", "Grade", "M", "grado [angular]", Tags);
        Sus radian = new Sus("Radiant", "Radiant", "M", "radian", Tags);
        //Las figuras----------------------------------


        //todo: Marcador
        //La ropa
        Tags = new String[]{"ropa"};

        Sus badeanzug = new Sus("Badeanzug", "Badeanzüge", "M", "traje de baño", Tags);
        Sus hemd = new Sus("Hemd", "Hemden", "N", "camisa", Tags);
        Sus hose = new Sus("Hose", "hosen", "F", "pantalón", Tags);
        Sus hut = new Sus("Hut", "Hüte", "M", "sombrero", Tags);
        Sus jacke = new Sus("Jacke", "Jacken", "F", "chamarra", Tags);
        Sus kleid = new Sus("Kleid", "Kleider", "N", "vestido", Tags);
        Sus krawatte = new Sus("Krawatte", "Krawatten", "F", "corbata", Tags);
        Sus mantel = new Sus("Mantel", "Mäntel", "M", "abrigo", Tags);
        Sus pullover = new Sus("Pullover", "Pullover", "M", "suéter", Tags);
        Sus rock = new Sus("Rock", "Röcke", "M", "falda", Tags);
        Sus schuh = new Sus("Schuh", "Schuhe", "M", "zapato", Tags);
        Sus schirmmütze = new Sus("Schirmmütze", "Schirmmützen", "F", "cachucha", Tags);
        Sus shorts = new Sus("Shorts", "Shorts", "M", "shorts", Tags);
        Sus socke = new Sus("Socke", "Socken", "F", "calcetín", Tags);
        Sus stiefel = new Sus("Stiefel", "Stiefel", "M", "bota", Tags);
        //[---La ropa]


        //todo: Marcador
        //[   La casa]
        Tags = new String[]{"casa"};

        Sus erdgeschoß = new Sus("Erdgeschoß", "Erdgeschoße", "N", "planta baja", Tags);
        Sus dachgeschoß = new Sus("Dachgeschoß", "Dachgeschoße", "N", "ático", Tags);
        Sus stockwerke = new Sus("Stockwerk", "Stockwerke", "N", "piso [planta]", Tags);
        Sus fußboden = new Sus("Fußboden", "Fußböden", "M", "piso [suelo]", Tags);

        Sus keller = new Sus("Keller", "Keller", "M", "sótano", Tags);
        Sus zimmer = new Sus("Zimmer", "Zimmer", "N", "cuarto [habitación]", Tags);
        Sus raum = new Sus("Raum", "Räume", "M", "cuarto [espacio]", Tags);
        Sus wohnzimmer = new Sus("Wohnzimmer", "Wohnzimmer", "N", "sala", Tags);
        Sus schlafzimmer = new Sus("Schlafzimmer", "Schlafzimmer", "N", "cuarto [recámara]", Tags);
        Sus esszimmer = new Sus("Esszimmer", "Esszimmer", "N", "comedor", Tags);
        Sus badezimmer = new Sus("Badezimmer", "Badezimmer", "N", "baño [cuarto]", Tags);
        Sus küche = new Sus("Küche", "Küchen", "F", "cocina", Tags);

        Sus terrasse = new Sus("Terrasse", "Terrassen", "F", "terraza", Tags);
        Sus hof = new Sus("Hof", "Höfe", "M", "patio", Tags);
        Sus balkon = new Sus("Balkon", "Balkone", "M", "balcón", Tags);
        Sus fenster = new Sus("Fenster", "Fenster", "N", "ventana", Tags);
        Sus tür = new Sus("Tür", "Türen", "F", "puerta", Tags);
        Sus garten = new Sus("Garten", "Gärten", "M", "jardín", Tags);
        Sus straße = new Sus("Straße", "Straßen", "F", "calle", Tags);

        Sus Miete = new Sus("Miete", "Mieten", "F", "renta", Tags);
        Sus kaution = new Sus("Kaution", "Kautionen", "F", "fianza", Tags);

        System.out.println("la equis bocs.");
        //[---La casa]


        //todo: Marcador
        //[   La ciudad]
        Tags = new String[]{"ciudad"};
        String[] t = new String[]{"ciudad"};

        Sus Wohnung = new Sus("Wohnung", "Wohnungen", "F", "departamento [empieza con 'w']", Tags);
        Sus apartment = new Sus("Apartment", "Apartments", "N", "departamento [empieza con 'a']", Tags);
        Sus Haus = new Sus("Haus", "Häuser", "N", "casa", Tags);
        Sus wohngemeinschaft = new Sus("Wohngemeinschaft", "Wohngemeinschaften", "F", "piso compartido", Tags);
        Sus wolkenkratzer = new Sus("Wolkenkratzer", "Wolkenkratzer", "M", "rascacielos", Tags);
        Sus gebëude = new Sus("Gebäude", "Gebäude", "N", "edificio", Tags);

        Sus mitbewohner = new Sus("Mitbewohner [Wohnung]", "Mitbewohnernen", "M", "compañero de piso", Tags);
        Sus mitbewohner2 = new Sus("Mitbewohner [Haus]", "Mitbewohnernen", "M", "vecino", Tags);

        Sus bar = new Sus("Bar", "Bars", "F", "bar", Tags);
        Sus museum = new Sus("Museum", "Museen", "N", "museo", t );
        Sus krankenhaus = new Sus("Krankenhaus", "Krankenhäuser", "N", "hospital", t );
        krankenhaus.agregarTag("médico");

        Sus dorf = new Sus("Dorf", "Dörfer", "N", "pueblo", t);

        Sus stadt = new Sus("Stadt", "Städte", "F", "ciudad", t );
        Sus großstadt = new Sus("Großstadt", "Großstädte", "F", "ciudad grande", t );
        Sus großstadtleben = new Sus("Großstadtleben", "Großstadtleben", "N", "vida en una ciudad grande", t);
        Sus verkehr = new Sus("Verkehr", nullEntry, "N", "tráfico", t );

        Sus Baustelle = new Sus("Baustelle", "Baustellen", "F", "construcción [sitio]", Tags);
        Sus Altbau = new Sus("Altbau", "Altbauten", "M", "edificio nuevo", t);
        Sus Neubau = new Sus("Neubau", nullEntry, "M", "edificio viejo", t);

        //[---La ciudad]


        //todo: Marcador
        //[Los muebles y cosas de la casa]
        Tags = new String[]{"mueble"}; //y cosas

        Sus bett = new Sus("Bett", "Betten", "N", "cama", Tags);
        Sus stuhl = new Sus("Stuhl", "Stühle", "M", "silla", Tags);
        Sus tisch = new Sus("Tisch", "Tische", "M", "mesa", Tags);
        Sus kühlschrank = new Sus("Kühlschrank", "Kühlschränke", "M", "refrigerador", Tags);
        Sus kommode = new Sus("Kommode", "Kommoden", "F", "cómoda", Tags);
        Sus sofa = new Sus("Sofa", "Sofas", "N", "sofá", Tags);

        //[El médico]
        Tags = new String[]{"médico"};
        Sus rezept = new Sus("Rezept", "Rezepte", "F", "prescripción", Tags);
        Sus versichertenkarte = new Sus("Versichertenkarte", "Versichertenkarten", "F", "tarjeta del seguro", Tags);
        Sus krankmeldung = new Sus("Krankmeldung", "Krankmeldungen", "F", "baja de enfermedad", Tags);
        Sus Medizin = new Sus("Medizin", nullEntry, "F", "medicina", Tags);
        Sus apotheke = new Sus("Apotheke", "Apotheken", "F", "farmacia", Tags);
        Sus arzt = new Sus("Arzt", "Ärzte", "M", "médico", Tags);
        Sus ärztin = new Sus("Ärztin", "Ärztinnen", "F", "médica", Tags);
        //Sus arzhelferin = new Sus("Arzthelferin", "Arzthelfernen", );
        Sus schmerz = new Sus("Schmerz", "Schmerzen", "M", "dolor", Tags);
        //Sus krankenschwester = new Sus("enfermera", Tags);
        Sus termin = new Sus("Termin", "Termine", "M", "cita [para el médico]", Tags);

        //[---El médico]


        //todo: Marcador
        //[La escuela]
        Tags = new String[]{"escuela"};

        Sus schule = new Sus("Schule", "Schulen", "F", "escuela", Tags);

        Sus bleistift = new Sus("Bleistift", "Bleistifte", "M", "lápiz", Tags);
        Sus buch = new Sus("Buch", "Bücher", "N", "libro", Tags);
        Sus entwurf = new Sus("Entwurf", "Entwürfe", "M", "borrador [escritura]", Tags);
        Sus feder = new Sus("Feder", "Federn", "F", "pluma [escritura]", Tags);
        Sus filzstift = new Sus("Filzstift", "Filzstifte", "M",  "plumón", Tags);
        Sus klasse = new Sus("Klasse", "Klassen", "F", "clase", Tags);
        Sus klassenzimmer = new Sus("Klassenzimmer", "Klassenzimmer", "N", "aula", Tags);
        Sus korridor = new Sus("Korridor", "Korridore", "M", "pasillo", Tags);
        korridor.agregarTag("casa");
        Sus kreide = new Sus("Kreide", "Kreiden", "F", "gis", Tags);
        Sus kurs = new Sus("Kurs", "Kurse", "M", "curso", Tags);
        //Sus lehrer = new Sus("Lehrer", "maestro", Tags);
        //Sus professor = new Sus("Professor", "profesor", Tags);
        Sus radiergummi = new Sus("Radiergummi", "Radiergummis", "M", "borrador [para lápiz]", Tags);
        Sus rechner = new Sus("Rechner", "Rechner", "M", "calculadora", Tags);
        rechner.agregarTag("tecnología");
        Sus rechner2 = new Sus("Rechner", "Rechner", "M", "computadora", Tags);
        rechner2.agregarTag("tecnología");
        Sus rucksack = new Sus("Rucksack", "Rucksäcke", "M", "mochila", Tags);
        Sus schreibtisch = new Sus("Schreibtisch", "Schreibtische", "M", "escritório", Tags);
        //Sus student = new Sus("Student", "estudiante", Tags);
        Sus tafel = new Sus("Tafel", "Tafeln", "F", "pizarra", Tags);
        Sus wörterbuch = new Sus("Wörterbuch", "Wörterbücher", "N", "diccionario", Tags);

        //Exámen, prueba
        //[/La escuela]


        //todo: Marcador
        //[La tecnología]
        Tags = new String[]{"tecnología"};

        Sus fernseher = new Sus("Fernseher", "Fernseher", "N", "tele", Tags);
        Sus handy = new Sus("Handy", "Handys", "N", "teléfono", Tags);
        //electricidad, cable, laptop?

        //[/La tecnología]


        //todo: Marcador
        //[transporte]
        Tags = new String[]{"transporte"};

        Sus auto = new Sus("Auto", "Autos", "N", "coche", Tags);
        Sus flugzeug = new Sus("Flugzeug", "Flugzeuge", "N", "avión", Tags);
        Sus fahrrad = new Sus("Fahrrad", "Fahrräder", "N", "bicicleta", Tags);
        Sus laster = new Sus("Laster", "Laster", "M", "camión", Tags);
        Sus bus = new Sus("Bus", "Busse", "M", "autobús", Tags);
        Sus hubschrauber = new Sus("Hubschrauber", "Hubschrauber", "M", "helicóptero", Tags);
        Sus u_bahn = new Sus("U-Bahn", "U-Bahnen", "F", "metro [transporte]", Tags);
        Sus motorrad = new Sus("Motorrad", "Motorräder", "N", "moto", Tags);
        Sus rollschuh = new Sus("Rollshuh", "Rollshuhe", "M", "patin [de ruedas]", Tags);
        Sus schlittschuh = new Sus("Schlittschuh", "Schlittschuhe", "M", "patín [de nieve]", Tags);
        Sus skateboard = new Sus("Skateboard", "Skateboards", "N", "patineta", Tags);
        Sus straßenbahn = new Sus("Straßenbahn", "Straßenbahnen", "F", "tranvía", Tags);
        Sus zug = new Sus("Zug", "Züge", "M", "tren", Tags);
        Sus taxi = new Sus("Taxi", "Taxis", "N", "taxi", Tags);

        //[---transporte]


        //[Viajar]

        //hotel, aeropuerto, "hostel", atracción, estación de autobuses
        //también tendrá reserbr, viajar, conocer, ...

        //[---Viajar]


        //[herramientas]

        //HANS, GIBT MIR DAS HAMMER

        //[Construcción (pero no demolición)
        Tags = new String[]{"materiales"};

        Sus stein = new Sus("Stein", "Steine", "M", "piedra", Tags);
        Sus ziegelstein = new Sus("Ziegelstein", "Ziegelsteine", "M", "ladrillo", Tags);
        Sus plastik = new Sus("Plastik", nullEntry, "N", "plastico", Tags);
        Sus metall = new Sus("Metall", "Metalle", "N", "metal", Tags);
        Sus beton = new Sus("Beton", "Betons", "M", "concreto", Tags);
        Sus holz = new Sus("Holz", nullEntry, "N", "madera", Tags);
        Sus stahl = new Sus("Stahl", "Stähle", "M", "acero", Tags);



        //[---Construcción]


        //Los oficios
        //jardinero, panadero, poliía, ...
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


    //Consola que te deja elejir un tema de la lista de temas de los sustantivos.
    //Echa NullPointerException si el usuario dice 'cerrar'
    /**
     public static String ElejirTema(Scanner sc) {
     String tema;
     while (true) {
     System.out.println("Favor de elejir un tema: ");
     String intento = sc.nextLine();

     switch (intento) {
     case "listar temas":
     Sus.ListarTemas();
     continue;
     case "cerrar":
     throw new NullPointerException("Ejercicio cerrado");
     } //Solo hay dos inputs raros que pueden ocurrir aqui.

     try {
     ArrayList<Sus> temp = Sus.ListaTema(intento);
     tema = intento; //Le dice al programa que si funcionó. Si no existe, se saldrá arribita.
     if (temp.size() <= 1) {throw new NumberFormatException("Error: Esta lista está vasia.");}
     } catch (NumberFormatException e) {
     System.out.println(e.getLocalizedMessage());
     continue;
     }catch (NullPointerException e) {
     System.out.print("El tema '" + intento + "' no se encuentra. Diga 'listar temas' para ver los temas.");
     System.out.println(" Diga 'cerrar' para cerrar el ejercicio. ");
     continue;
     }
     break;
     }
     return tema;
     }
     */

    //Consola que te deja elejir un sustantivo de la lista completa de todos los sustantivos.
    //Echa NullPointerException si el usuario dice 'cerrar'
    public static Sus ElejirSustantivo(Scanner sc) {
        Sus out;
        System.out.println("Favor de elejir un sustantivo:");
        while (true) {
            String intento = sc.nextLine();
            if (intento.equals("cerrar")) {throw new NullPointerException("Cerrando. ");}
            out = Sus.buscar(intento);
            if (out == null) {
                System.out.println("El sustantivo '" + intento + "' no se encuentra. Asgurese que el sustantivo sea singular y de capitalizar la primera letra.");
                continue;
            }
            break;
        }
        return out;
    }



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


    /**
     //Consola que te deja elejir una cantidad de ejercicios dado un ArrayList<E>
     public static int ElejirCantidad (ArrayList<Sus> listaSustantivos, Scanner sc) {
     System.out.println("Hay " + (listaSustantivos.size()-1) + " sustantivos en este tema.");
     int número;
     while (true) {
     System.out.println("¿Cuántos deséa practicar?");
     String intento = sc.nextLine();
     try {

     número = Integer.parseInt(intento); //Procesa input del usuario. Regresa un entero funcional.
     if (número < 1) {
     System.out.println("El número " + número + " es demasiado chico. ");
     continue;
     } else if (número > listaSustantivos.size()-1) {
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

    //todo really should be a Control function. Temas are now sorted completely there, not here.
    //Regresa la lista de sustantivos del tema indicado, dado la lista completa "Sustantivos"
    public static Lista<Palabra> ListaTema(String tema) {
        for (Lista<Palabra> listaActual: Control.Sustantivos) {
            if (listaActual.nombre.equals(tema)) {return listaActual;}
        }
        //System.out.println("El tema '" + tema + "' no se encuentra. Diga 'listar temas sustantivos' para ver los temas. ");
        throw new NullPointerException();
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



