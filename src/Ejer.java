import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;

public class Ejer { //Esta es la clase en donde se escribirán los ejercicios.
    public static final String nullEntry = "---";


    //todo: Actualizar estos
    //SUSTANTIVOS
    /**
     * Lista los ejercicios / comandos de la consola PracticarSustantivos()
     */
    public static void ListaEjerciciosSus() {
        System.out.println();
        System.out.println("-ejercicios: Imprime esta lista");
        System.out.println("-cerrar: Cierra la consola de ejercicios de sustantivos. ");
        System.out.println("-enseñar tema: Le intentará enseñar los sustantivos de algún tema elejido. ");
        System.out.println("-practicar géneros: Dado un tema, practicarás el genero de los sustantivos.");
        System.out.println("-practicar plurales: Dado un tema, practicarás los plurales de los sustantivos singulares.");
        System.out.println("-practicar significados: Dado un tema practicarás los significados de los sustantivos. ");
        System.out.println("-practicar vocabulario: Dado un sustantivo en espñol, escribir la palabra en alemán. ");
        System.out.println("-listar temas: Le enseña una lista de todos los temas. ");
        System.out.println("-imprimir lista tema: Imprime los sustantivos de algún tema, con información de los sustantivos");
        System.out.println("-definir sustantivos: Definirá los sustantivos elejidos. ");
        System.out.println();
    }

    //VERBOS
    /**
     * Lista los ejercicios / comandos de la consola PracticarVerbos()
     */
    public static void ListaEjerciciosVer() {
        System.out.println();
        System.out.println("-ejercicios: Imprime esta lista");
        System.out.println("-cerrar: Cierra la consola de ejercicios de verbos. ");
        //System.out.println("-practicar géneros: Dado un tema, practicarás el genero de los sustantivos.");
        //System.out.println("-practicar plurales: Dado un tema, practicarás los plurales de los sustantivos singulares.");
        System.out.println("-enseñar tema: Le intentará enseñar los verbos de algún tema elejido. ");
        System.out.println("-practicar significados: Dado un tema practicarás los significados de los verbos. ");
        System.out.println("-practicar vocabulario: Dado un verbo en espñol, escribir el verbo en alemán. ");
        System.out.println("-practicar participios: Dado un verbo en alemán, escribir el participio correspondiente");
        System.out.println("-listar temas: Le enseña una lista de todos los temas. ");
        System.out.println("-imprimir lista tema: Imprime los verbos de algún tema, con información de los verbos");
        System.out.println("-definir verbos: Definirá los verbos elejidos. ");
        System.out.println();
    }

    //ADJETIVOS
    /**
     * Lista los ejercicios / comandos de la consola PracticarAdjetivos()
     */
    public static void ListaEjerciciosAdj() {
        System.out.println();
        System.out.println("-ejercicios: Imprime esta lista");
        System.out.println("-cerrar: Cierra la consola de ejercicios de adjetivos.");
        //System.out.println("-practicar géneros: Dado un tema, practicarás el genero de los sustantivos.");
        //System.out.println("-practicar plurales: Dado un tema, practicarás los plurales de los sustantivos singulares.");
        System.out.println("-enseñar tema: Le intentará enseñar los adjetivos de algún tema elejido. ");
        System.out.println("-practicar significados: Dado un tema practicarás los significados de los adjetivos.");
        System.out.println("-practicar vocabulario: Dado un adjetivo en espñol, escribir el adjetivo en alemán.");
        System.out.println("-listar temas: Le enseña una lista de todos los temas.");
        System.out.println("-imprimir lista tema: Imprime los adjetivos de algún tema, con información de los adjetivos");
        System.out.println("-definir adjetivos: Definirá los adjetivos elejidos. ");
        System.out.println();
    }

    //PALABRAS
    /**
     * Lista los ejercicios / comandos de la consola PracticarPalabras()
     */
    public static void ListaEjerciciosPal() {
        System.out.println();
        System.out.println("-ejercicios: Imprime esta lista");
        System.out.println("-cerrar: Cierra la consola de ejercicios de adjetivos.");
        System.out.println("-enseñar tema: Le intentará enseñar una lista de palabras");
        System.out.println("-practicar significados: Dado un tema practicarás los significados de las palabras.");
        System.out.println("-practicar vocabulario: Dado una palabra en espñol, escribir la palabra en alemán.");
        System.out.println("-listar temas: Le enseña una lista de todos los temas.");
        System.out.println("-imprimir lista tema: Imprime las palabras de algún tema, con información sobre ellas. ");
        System.out.println("-definir palabra: Definirá las palabras elejidas. ");
        System.out.println();
    }



    //SUSTANTIVOS
    /** Consola para practicar los sustantivos. Se ejecuta desde Control.consola().
     * Activo hasta que el usuario dice 'cerrar'
     * @param sc el scanner.
     */
    public static void PracticarSustantivos(Scanner sc) {
        System.out.println("Consola de sustantivos. Diga 'ejercicios' para la lista de ejercicios.");

        boolean activo = true;
        while(activo) {
            System.out.println("¿Que quiere practicar?");
            String comando = sc.nextLine();

            boolean entendido = false;
            switch (comando) {

                case "practicar géneros": Ejer.EjerciciosPalabrasSimples("Sus Géneros", sc);           entendido = true; break;

                case "practicar plurales": Ejer.EjerciciosPalabrasSimples("Sus Plurales", sc);         entendido = true; break;

                case "practicar significados": Ejer.EjerciciosPalabrasSimples("Sus Significados", sc); entendido = true; break ;

                case "practicar vocabulario": Ejer.EjerciciosPalabrasSimples("Sus Vocaulario", sc);    entendido = true; break;

                case "ejercicios": ListaEjerciciosSus();                                                      entendido = true; break;

                case "cerrar": activo = false;                                                                entendido = true; break;

                case "imprimir lista tema":
                    throw new NumberFormatException("Aún no me programan!");
                    /**
                    //String tema = Sus.ElejirTema(sc);
                    String tema = Ejer.ElejirTema("Sus", sc);
                    ArrayList<Sus> listaDeTema = Sus.ListaTema(tema);
                    Sus.imprimirListaChido(listaDeTema);

                     entendido = true;
                    break;*/
                case "definir sustantivos":
                    throw new NumberFormatException("Aún no me programan!"); /**
                    System.out.println("Abriendo consola de definiciones. Diga 'cerrar' para salirse de la consola.");
                    try {
                        while (true) {
                            Palabra current = Ejer.ElejirPalabraTipo(Sus.Sus, sc); //De seguro te regresa algo válido
                            current.definir();
                        }
                    } catch (NullPointerException e) {
                        System.out.println("cerrando");
                    }

                    entendido = true;
                    break;*/


                case "listar temas":
                    System.out.println("Un segundo...");
                    for (Lista actual : Control.Sustantivos) {System.out.print(actual.nombre + ", ");}

                    entendido = true;
                    break;
                case "enseñar tema":
                    //Ejer.EnseñarSustantivos(Sus.ElejirTema(sc), sc);
                    //Ejer.EnseñarSustantivos(Ejer.ElejirTema("Sus", sc), sc);
                    Ejer.EnseñarTema(Ejer.ElejirTema("Sus", sc), "Sus", sc);
                    System.out.println("Cerrando ejercicio");

                    entendido = true;
                    break;
            }

            //if(!Control.contiene(comandos, comando)) {
            //    System.out.println("Ejercicio '" + comando + "' no reconocido. Diga 'ejercicios' para la lista de comandos");
            //}

            if (!entendido) { // no se entendió el comando.
                System.out.println("Ejercicio '" + comando + "' no reconocido. Diga 'ejercicios' para la lista de comandos");
            }
        }
        System.out.println("Cerrando ejercicios de sustantivos. Regresando a la consola principal");
    }

    //VERBOS
    /** Consola para practicar los verbos. Se ejecuta desde Control.consola().
     * Activo hasta que el usuario dice 'cerrar'
     * @param sc el scanner.
     */
    public static void PracticarVerbos(Scanner sc) {
        System.out.println("Consola de verbos. Diga 'ejercicios' para la lista de ejercicios.");

        boolean activo = true;
        while(activo) {
            System.out.println("¿Que quiere practicar?");
            String comando = sc.nextLine();

            boolean entendido = false;
            switch (comando) {
                case "ejercicios": ListaEjerciciosVer();                                                      entendido = true; break;

                case "cerrar": activo = false;                                                                entendido = true; break;

                case "practicar significados": Ejer.EjerciciosPalabrasSimples("Ver Significados", sc); entendido = true; break;

                case "practicar vocabulario": Ejer.EjerciciosPalabrasSimples("Ver Vocabulario", sc);   entendido = true; break;

                case "practicar participios": Ejer.EjerciciosPalabrasSimples("Ver Participios", sc);   entendido = true; break;

                case "imprimir lista tema":
                    throw new NumberFormatException("Aún no me programan!");
                    /**
                    //String tema = Ver.ElejirTema(sc);
                    String tema = Ejer.ElejirTema("Ver", sc);
                    ArrayList<Ver> listaDeTema = Ver.ListaTema(tema);
                    Ver.imprimirListaChido(listaDeTema);

                     entendido = true;
                    break;*/
                case "definir verbos":
                    throw new NumberFormatException("Aún no me programan!");

                    /**try {
                        System.out.println("Abriendo consola de definiciones. Diga 'cerrar' para salirse de la consola.");
                        while (true) {
                            Palabra actual = Ejer.ElejirPalabraTipo(Ver.Ver, sc); //De seguro te regresa algo válido
                            actual.definir();
                        }
                    } catch (NullPointerException e) {
                        System.out.println("cerrando");
                    }
                    entendido = true;
                    break;*/

                case "listar temas":
                    for (Lista actual : Control.Verbos) {System.out.print(actual.nombre + ", ");}

                    entendido = true;
                    break;
                case "enseñar tema":
                    //Ejer.EnseñarVerbos(Ver.ElejirTema(sc), sc);
                    //Ejer.EnseñarVerbos(Ejer.ElejirTema("Ver", sc), sc);
                    Ejer.EnseñarTema(Ejer.ElejirTema("Ver", sc), "Ver", sc);
                    System.out.println("Cerrando ejercicio");

                    entendido = true;
                    break;
            }


            if (!entendido) { // no se entendió el comando.
                System.out.println("Ejercicio '" + comando + "' no reconocido. Diga 'ejercicios' para la lista de comandos");
            }
        }
        System.out.println("Cerrando consola de verbos. Regresando a la consola principal");
    }

    //ADJETIVOS
    /** Consola para practicar los verbos. Se ejecuta desde Control.consola().
     * Activo hasta que el usuario dice 'cerrar'
     * @param sc el scanner.
     */
    public static void PracticarAdjetivos(Scanner sc) {
        System.out.println("Consola de adjetivos. Diga 'ejercicios' para la lista de ejercicios.");

        boolean activo = true;
        while(activo) {
            System.out.println("¿Que quiere practicar?");
            String comando = sc.nextLine();

            boolean entendido = false;
            switch (comando) {
                case "ejercicios": Ejer.ListaEjerciciosAdj();                                                        entendido = true; break;

                case "cerrar": activo = false;                                                                       entendido = true; break;

                case "practicar significados": Ejer.EjerciciosPalabrasSimples(Adj.Adj + " Significados", sc); entendido = true; break;

                case "practicar vocabulario": Ejer.EjerciciosPalabrasSimples(Adj.Adj + " Vocabulario", sc);   entendido = true; break;

                case "practicar comparativo": Ejer.EjerciciosPalabrasSimples(Adj.Adj + " Comparativos", sc);  entendido = true; break;

                case "practcar superlativos": Ejer.EjerciciosPalabrasSimples(Adj.Adj + " Superlativos", sc);  entendido = true; break;

                case "imprimir lista tema":
                    throw new NumberFormatException("Aún no me programan!");
                    /**
                    //String tema = Ver.ElejirTema(sc);
                    String tema = Ejer.ElejirTema("Adj", sc);
                    ArrayList<Adj> listaDeTema = Adj.ListaTema(tema);
                    Adj.imprimirListaChido(listaDeTema);

                    entendido = true;
                    break;*/
                case "definir adjetivos":
                    throw new NumberFormatException("Aún no me programan!");
                    /**
                    try {
                        System.out.println("Abriendo consola de definiciones. Diga 'cerrar' para salirse de la consola.");
                        while (true) {
                            Adj current = Adj.ElejirAdjetivo(sc); //De seguro te regresa algo válido
                            current.definir();
                        }
                    } catch (NullPointerException e) {
                        System.out.println("cerrando");
                    }
                    entendido = true;
                    break;
                     */

                case "listar temas":
                    for (Lista actual : Control.Adjetivos) {System.out.print(actual.nombre + ", ");}
                    entendido = true;
                    break;

                case "enseñar tema":
                    //Ejer.EnseñarAdjetivos(Adj.ElejirTema(sc), sc);
                    //Ejer.EnseñarAdjetivos(Ejer.ElejirTema("Adj", sc), sc);
                    Ejer.EnseñarTema(Ejer.ElejirTema("Adj", sc), "Adj", sc);
                    System.out.println("Cerrando ejercicio");
                    entendido = true;
                    break;
            }


            if (!entendido) { // no se entendió el comando.
                System.out.println("Ejercicio '" + comando + "' no reconocido. Diga 'ejercicios' para la lista de comandos");
            }
        }
        System.out.println("Cerrando consola de adjetivos. Regresando a la consola principal");
    }

    //PALABRAS
    /** Consola para practicar los verbos. Se ejecuta desde Control.consola().
     * Activo hasta que el usuario dice 'cerrar'
     * @param sc el scanner.
     */
    public static void PracticarPalabras(Scanner sc) {
        System.out.println("Consola de palabras misceláneas. Diga 'ejercicios' para la lista de ejercicios.");

        boolean activo = true;
        while(activo) {
            System.out.println("¿Que quiere practicar?");
            String comando = sc.nextLine();

            boolean entendido = false;
            switch (comando) {
                case "ejercicios": Ejer.ListaEjerciciosPal();                                                        entendido = true; break;

                case "cerrar": activo = false;                                                                       entendido = true; break;

                case "practicar significados": Ejer.EjerciciosPalabrasSimples(Pal.Pal + " Significados", sc); entendido = true; break;

                case "practicar vocabulario": Ejer.EjerciciosPalabrasSimples(Pal.Pal + " Significados", sc);  entendido = true; break;

                case "imprimir lista tema":
                    throw new NumberFormatException("Aún no me programan!");
                    /**
                    //String tema = Pal.ElejirTema(sc);
                    String tema = Ejer.ElejirTema("Pal", sc);
                    ArrayList<Pal> listaDeTema = Pal.ListaTema(tema);
                    Pal.imprimirListaChido(listaDeTema);
                    entendido = true;
                    break;*/
                case "definir palabras":
                    throw new NumberFormatException("Aún no me programan!");
                    /**
                    try {
                        System.out.println("Abriendo consola de definiciones. Diga 'cerrar' para salirse de la consola.");
                        while (true) {
                            Pal current = Pal.ElejirPalabra(sc); //De seguro te regresa algo válido
                            current.definir();
                        }
                    } catch (NullPointerException e) {
                        System.out.println("cerrando");
                    }
                    entendido = true;
                    break;*/

                case "listar temas":
                    for (Lista actual : Control.Palabras) {System.out.print(actual.nombre + ", ");}
                    entendido = true;
                    break;
                case "enseñar tema":
                    //Ejer.EnseñarPalabras(Pal.ElejirTema(sc), sc);
                    //Ejer.EnseñarPalabras(Ejer.ElejirTema("Pal", sc), sc);
                    Ejer.EnseñarTema(Ejer.ElejirTema("Pal", sc), "Pal", sc);
                    System.out.println("Cerrando ejercicio");
                    entendido = true;
                    break;
            }


            if (!entendido) { // no se entendió el comando.
                System.out.println("Ejercicio '" + comando + "' no reconocido. Diga 'ejercicios' para la lista de comandos");
            }
        }
        System.out.println("Cerrando consola de palabras misceláneas. Regresando a la consola principal");
    }


    //GENERAL; EJERCICIOS
    //Consola que te deja elejir una cantidad de ejercicios/preguntas dado un ArrayList<E>
    //Contiene un desface de 1, dado que el tamaño total de la lista inclye la palabra nula al comienzo, cuya no se utiliza.

    /**
     * Consola que te deja elejir una cantidad de algo, dado un tamaño máximo.
     * @param tamañoTotal el número máximo que se puede elejir (inclusivo)
     * @param mensaje Un mensaje que se dirá al principio y que se repetirá hasta que se elija una cantidad viable
     * @param sc el scanner
     * @return La cantidad como un int
     * @throws SecurityException si se dice 'cerrar'.
     */
    public static int ElejirCantidad (int tamañoTotal, String mensaje, Scanner sc) {

        int número;
        while (true) {
            System.out.println(mensaje);
            String intento = sc.nextLine();
            if (intento.equals("cerrar")) {throw new SecurityException("Cerrando aplicación"); }

            try {
                número = Integer.parseInt(intento); //Procesa input del usuario. Regresa un entero funcional.
                if (número < 1) {
                    System.out.println("El número " + número + " es demasiado chico. ");
                    continue;
                } else if (número > tamañoTotal) {
                    System.out.println("El número " + número + " es demasiado grande. ");
                    continue;
                } // Aquí sabemos que el número si es valido para el ejercicio.
            } catch (NumberFormatException e) { //Oye, eso no es un numero...
                System.out.println("'" + intento + "' no es un número válido. Diga 'cerrar' para cerrar la consola");
                continue;
            }
            break;
        }
        return número;
    }




    //Estos ya no se usan.
    /**
     * Ejecuta varios ejercicios de los sustantivos
     * @param ejercicio el ejercicio que se practicará. (generos, plurales, significados, vocabulario)
     * @param sc el escaneador
     */
    public static void ejerciciosSus(String ejercicio, Scanner sc) {
        if (!ejercicio.equals("géneros") && !ejercicio.equals("plurales") && !ejercicio.equals("significados") && !ejercicio.equals("vocabulario") ) {
            System.out.println("El ejercicio '" + ejercicio + "' no existe");
            return;
        }

        String tema; //El tema que elije el usuario
        try {
            //tema = Sus.ElejirTema(sc); //Echa un error si no funciona.
            tema = Ejer.ElejirTema("Sus", sc);
        } catch (SecurityException e){
            return; //usuario dijo 'cerrar'
        }


        ArrayList<Sus> listaDeSustantivosTema = Palabra.ConvertirListaASus(Control.getTema(tema, Sus.Sus).lista); //La lista de sustantivos del tema

        System.out.println("Hay " + listaDeSustantivosTema.size() + " sustantivos en el tema '" + tema + "'. ");
        int número = -1;
        try {
            número = Ejer.ElejirCantidad(listaDeSustantivosTema.size(), "¿Cuántos deséa practicar?", sc);
        } catch (SecurityException e) {
            return; //Dijo 'cerrar'
        }

        ArrayList<Sus> listaSustantivos = Sus.escojerAleatorio(listaDeSustantivosTema, número);
        //Ahora la lista tiene sustantivos aleatorios del tema elejido, sin dobles, y sin errores :LUL:
        //Nota: Estos son objetos Sus.

        String[] preguntas = new String[número]; //Esta lista es la que se utilizará en el ejercicio
        String[] respuestas = new String[número]; //Esta igual. NO SE CONFUNDE CON respuestasUsuario.

        //Creación de preguntas y sus respuestas. Nota las diferencias.
        for (int i = 0; i < número; i++) {
            Sus current = listaSustantivos.get(i);
            switch (ejercicio) {
                case "géneros":
                    preguntas[i] = current.sustantivo;
                    respuestas[i] = current.género;
                    break;
                case "plurales":
                    preguntas[i] = current.sustantivo;
                    respuestas[i] = current.plural;
                    break;
                case "significados":
                    preguntas[i] = current.sustantivo;
                    respuestas[i] = current.significado.split("\\[")[0];
                    break;
                case "vocabulario":
                    preguntas[i] = current.significado;
                    respuestas[i] = current.sustantivo.split("\\[")[0];
                    break;
            }
        }

        String[] respuestasUsuario = new String[número];
        double calificación;
        //Aquí se ejecutan los ejercicios. También se califican.
        try { //Siempre echa error NullPointerException si el usuario cierra el ejercicio
            switch (ejercicio) {
                case "géneros":
                    respuestasUsuario = Ejer.EjercicioSimple(preguntas, "Sustantivo", "Género (M, F, o N)", "---", sc);
                    calificación = Ejer.calificar(preguntas, respuestasUsuario, respuestas, "Género");
                    System.out.println("Calificación final: " + calificación);
                    break;
                case "plurales":
                    respuestasUsuario = Ejer.EjercicioSimple(preguntas, "Sustantivo", "Plural", "---", sc);
                    calificación = Ejer.calificar(preguntas, respuestasUsuario, respuestas, "Plural");
                    System.out.println("Calificación final: " + calificación);
                    break;
                case "significados":
                    respuestasUsuario = Ejer.EjercicioMatching(preguntas, respuestas, "Sustantivo", "Significado", "---", sc);
                    calificación = Ejer.calificar(preguntas, respuestasUsuario, respuestas, "Significado");
                    System.out.println("Calificación final: " + calificación);
                    break;
                case "vocabulario":
                    respuestasUsuario = Ejer.EjercicioSimple(preguntas, "Significado", "Sustantivo", "---", sc);
                    calificación = Ejer.calificar(preguntas, respuestasUsuario, respuestas, "Significado");
                    System.out.println("Calificación final: " + calificación);
                    System.out.println("NOTA: Es posible que hayas escrito un sustantivo sinonimo. Esta calificación puede estar equivocada");
                    break;
            }
        } catch (NullPointerException e) { //Indica que se cerro el ejercicio con el comando 'cerrar ejercicio'
            System.out.println("Ejercicio cerrado");
            return;
        }
    }
    /**
     * Ejecuta varios ejercicios de los verbos
     * @param ejercicio el ejercicio que se practicará. (significados, vocabulario)
     * @param sc el escaneador
     */
    public static void ejerciciosVer(String ejercicio, Scanner sc) {
        //Se checa que el ejercicio exista
        if (!ejercicio.equals("significados")
                && !ejercicio.equals("vocabulario")
                && !ejercicio.equals("participios") ) {

            System.out.println("El ejercicio '" + ejercicio + "' no existe");
            return;
        }

        //Se elije un tema
        String tema; //El tema que elije el usuario
        try {
            //tema = Ver.ElejirTema(sc); //Echa un error si no funciona.
            tema = Ejer.ElejirTema("Ver", sc);
        } catch (NullPointerException e){
            return; //Ya dice que se acabo...
        }

        //La lista de verbos del tema
        ArrayList<Ver> listaDeVerbosTema = Palabra.ConvertirListaAVer(Control.getTema(tema, Ver.Ver).lista);

        //int número = Ver.ElejirCantidad(listaDeVerbosTema, sc); //La cantidad de verbos que se practicarán.
        int número = Ejer.ElejirCantidad(listaDeVerbosTema.size(), "¿Cuántos deséa practicar?", sc); //La cantidad de verbos que se practicarán.

        ArrayList<Ver> listaVerbos = Ver.escojerAleatorio(listaDeVerbosTema, número);
        //Ahora la lista tiene verbos aleatorios del tema elejido, sin dobles, y sin errores :LUL:
        //Nota: Estos son objetos Ver.

        String[] preguntas = new String[número]; //Esta lista es la que se utilizará en el ejercicio
        String[] respuestas = new String[número]; //Esta igual. NO SE CONFUNDE CON respuestasUsuario.

        //Creación de preguntas y sus respuestas. Nota las diferencias.
        for (int i = 0; i < número; i++) {
            Ver actual = listaVerbos.get(i);
            switch (ejercicio) {
                case "significados":
                    preguntas[i] = actual.verbo;
                    respuestas[i] = actual.significado.split("\\[")[0];
                    break;
                case "vocabulario":
                    preguntas[i] = actual.significado;
                    respuestas[i] = actual.verbo.split("\\[")[0];
                    break;
                case "participios":
                    if (actual.participio == null) {throw new NullPointerException("Error: El verbo " + actual.verbo + "no tiene participio. ¡Alberto lo agregará en seguida!");}
                    preguntas[i] = actual.verbo;
                    respuestas[i] = actual.participio;
                    break;
            }
        }

        String[] respuestasUsuario = new String[número];
        double calificación;
        //Aquí se ejecutan los ejercicios. También se califican.
        try { //Siempre echa error NullPointerException si el usuario cierra el ejercicio
            switch (ejercicio) {
                case "significados":
                    respuestasUsuario = Ejer.EjercicioMatching(preguntas, respuestas, "Verbo", "Significado", "---", sc);
                    calificación = Ejer.calificar(preguntas, respuestasUsuario, respuestas, "Significado");
                    System.out.println("Calificación final: " + calificación);
                    break;
                case "vocabulario":
                    respuestasUsuario = Ejer.EjercicioSimple(preguntas, "Significado", "Verbo", "---", sc);
                    calificación = Ejer.calificar(preguntas, respuestasUsuario, respuestas, "Significado");
                    System.out.println("Calificación final: " + calificación);
                    System.out.println("NOTA: Es posible que haya escrito un sustantivo sinonimo. Esta calificación puede estar equivocada");
                    break;
                case "participio":
                    respuestasUsuario = Ejer.EjercicioSimple(preguntas, "Verbo", "Participio", "---", sc);
                    calificación = Ejer.calificar(preguntas, respuestasUsuario, respuestas, "Participio");
                    System.out.println("Calificación final: " + calificación);
                    break;
            }
        } catch (NullPointerException e) { //Indica que se cerró el ejercicio con el comando 'cerrar ejercicio'
            System.out.println("Ejercicio cerrado");
            return;
        }
    }
    /**
     * Ejecuta varios ejercicios de los adjetivos
     * @param ejercicio el ejercicio que se practicará. (significados, vocabulario)
     * @param sc el escaneador
     */
    public static void ejerciciosAdj(String ejercicio, Scanner sc) {
        //Se checa que el ejercicio exista
        if (!ejercicio.equals("significados") && !ejercicio.equals("vocabulario") && !ejercicio.equals("comparativo") && !ejercicio.equals("superlativo") ) {
            System.out.println("El ejercicio '" + ejercicio + "' no existe");
            return;
        }

        String tema; //El tema que elije el usuario
        try {
            //tema = Adj.ElejirTema(sc); //Echa un error si no funciona.
            tema = Ejer.ElejirTema("Adj", sc);
        } catch (NullPointerException e){
            return; //Ya dice que se acabo...
        }

        ArrayList<Adj> listaDeAdjetivosTema = Palabra.ConvertirListaAAdj(Control.getTema(tema, Adj.Adj).lista); //La lista de sustantivos del tema

        //int número = Adj.ElejirCantidad(listaDeAdjetivosTema, sc); //La cantidad de sustantivos que se practicarán.
        int número = Ejer.ElejirCantidad(listaDeAdjetivosTema.size(), "¿Cuántos deséa practicar?", sc); //La cantidad de sustantivos que se practicarán.

        ArrayList<Adj> listaAdjetivos = Adj.escojerAleatorio(listaDeAdjetivosTema, número);
        //Ahora la lista tiene verbos aleatorios del tema elejido, sin dobles, y sin errores :LUL:
        //Nota: Estos son objetos Adj.

        String[] preguntas = new String[número]; //Esta lista es la que se utilizará en el ejercicio
        String[] respuestas = new String[número]; //Esta igual. NO SE CONFUNDE CON respuestasUsuario.

        //Creación de preguntas y sus respuestas. Nota las diferencias.
        for (int i = 0; i < número; i++) {
            Adj actual = listaAdjetivos.get(i);
            switch (ejercicio) {
                case "significados":
                    preguntas[i] = actual.adjetivo;
                    //respuestas[i] = actual.significado;
                    respuestas[i] = actual.significado.split("\\[")[0]; //Si hay [...] en el significado del adjetivo, no se enseñará.
                    break;
                case "vocabulario":
                    preguntas[i] = actual.significado;
                    respuestas[i] = actual.adjetivo.split("\\[")[0];
                    break;
                case "comparativo":
                    preguntas[i] = actual.adjetivo;
                    respuestas[i] = actual.comparativo;
                    break;
                case "superlativo":
                    preguntas[i] = actual.adjetivo;
                    respuestas[i] = actual.superlativo;
                    break;
            }
        }

        String[] respuestasUsuario = new String[número];
        double calificación;
        //Aquí se ejecutan los ejercicios. También se califican.
        try { //Siempre echa error NullPointerException si el usuario cierra el ejercicio
            switch (ejercicio) {
                case "significados":
                    respuestasUsuario = Ejer.EjercicioMatching(preguntas, respuestas, "Adjetivo", "Significado", "---", sc);
                    calificación = Ejer.calificar(preguntas, respuestasUsuario, respuestas, "Significado");
                    System.out.println("Calificación final: " + calificación);
                    break;
                case "vocabulario":
                    respuestasUsuario = Ejer.EjercicioSimple(preguntas, "Significado", "Adjetivo", "---", sc);
                    calificación = Ejer.calificar(preguntas, respuestasUsuario, respuestas, "Significado");
                    System.out.println("Calificación final: " + calificación);
                    System.out.println("NOTA: Es posible que haya escrito un sustantivo sinonimo. Esta calificación puede estar equivocada");
                    break;
                case "comparativo":
                    respuestasUsuario = Ejer.EjercicioSimple(preguntas, "Adjetivo", "Comparativo", "---", sc);
                    calificación = Ejer.calificar(preguntas, respuestasUsuario, respuestas, "Comparativo");
                    System.out.println("Calificación final: " + calificación);
                    break;
                case "superlativo":
                    respuestasUsuario = Ejer.EjercicioSimple(preguntas, "Adjetivo", "Superlativo", "---", sc);
                    calificación = Ejer.calificar(preguntas, respuestasUsuario, respuestas, "Superlativo");
                    System.out.println("Calificación final: " + calificación);
                    break;

            }
        } catch (NullPointerException e) { //Indica que se cerró el ejercicio con el comando 'cerrar ejercicio'
            System.out.println("Ejercicio cerrado");
            return;
        }
    }
    /**
     * Ejecuta varios ejercicios de las palabras (Pal)
     * @param ejercicio el ejercicio que se practicará. (significados, vocabulario)
     * @param sc el escaneador
     */
    public static void ejerciciosPal(String ejercicio, Scanner sc) {
        //Se checa que el ejercicio exista
        if (!ejercicio.equals("significados") && !ejercicio.equals("vocabulario")) {
            System.out.println("El ejercicio '" + ejercicio + "' no existe");
            return;
        }

        String tema; //El tema que elije el usuario
        try {
            //tema = Pal.ElejirTema(sc); //Echa un error si no funciona.
            tema = Ejer.ElejirTema("Pal", sc);
        } catch (NullPointerException e){
            return; //Ya dice que se acabo...
        }

        ArrayList<Pal> listaDepalabrasTema = Palabra.ConvertirListaAPal(Control.getTema(tema, Pal.Pal).lista); //La lista de sustantivos del tema

        //int número = Pal.ElejirCantidad(listaDepalabrasTema, sc); //La cantidad de sustantivos que se practicarán.

        //todo: "Hay [num] de cosas en este tema"
        int número = Ejer.ElejirCantidad(listaDepalabrasTema.size(), "¿Cuántos deséa practicar?", sc); //La cantidad de sustantivos que se practicarán.

        ArrayList<Pal> listaPalabras = Pal.escojerAleatorio(listaDepalabrasTema, número);
        //Ahora la lista tiene verbos aleatorios del tema elejido, sin dobles, y sin errores :LUL:
        //Nota: Estos son objetos Pal.

        String[] preguntas = new String[número]; //Esta lista es la que se utilizará en el ejercicio
        String[] respuestas = new String[número]; //Esta igual. NO SE CONFUNDE CON respuestasUsuario.

        //Creación de preguntas y sus respuestas. Nota las diferencias.
        for (int i = 0; i < número; i++) {
            Pal actual = listaPalabras.get(i);
            switch (ejercicio) {
                case "significados":
                    preguntas[i] = actual.palabra;
                    //respuestas[i] = actual.significado;
                    respuestas[i] = actual.significado.split("\\[")[0]; //Si hay [...] en el significado de la palabra, no se enseñará.
                    break;
                case "vocabulario":
                    preguntas[i] = actual.significado;
                    respuestas[i] = actual.palabra.split("\\[")[0]; //Igual...
                    break;
            }
        }

        String[] respuestasUsuario = new String[número];
        double calificación;
        //Aquí se ejecutan los ejercicios. También se califican.
        try { //Siempre echa error NullPointerException si el usuario cierra el ejercicio
            switch (ejercicio) {
                case "significados":
                    respuestasUsuario = Ejer.EjercicioMatching(preguntas, respuestas, "Palabra", "Significado", "---", sc);
                    calificación = Ejer.calificar(preguntas, respuestasUsuario, respuestas, "Significado");
                    System.out.println("Calificación final: " + calificación);
                    break;
                case "vocabulario":
                    respuestasUsuario = Ejer.EjercicioSimple(preguntas, "Significado", "Palabra", "---", sc);
                    calificación = Ejer.calificar(preguntas, respuestasUsuario, respuestas, "Significado");
                    System.out.println("Calificación final: " + calificación);
                    System.out.println("NOTA: Es posible que haya escrito un sustantivo sinonimo. Esta calificación puede estar equivocada");
                    break;
            }
        } catch (NullPointerException e) { //Indica que se cerró el ejercicio con el comando 'cerrar ejercicio'
            System.out.println("Ejercicio cerrado");
            return;
        }
    }



    /**
     * Consola de ejercicios simples para Sus, Ver, Pal, y Adj. Todos funcionan mediante Ejer.EjercicioSimple y Ejer.EjercicioMatching
     * @param ejercicio el ejercicio que quieres hacer (la clave del ejercicio)
     * @param sc el scanner.
     * @throws NumberFormatException si se le pasa un ejercicio equivocado / no existente, o si otro error ocurre.
     */
    public static void EjerciciosPalabrasSimples(String ejercicio, Scanner sc) {
        //El String "ejercicio" será único para cada ejercicio. Seguirá la siguiente leyenda:

        String tipoDePalabra = ejercicio.split(" ")[0]; //"Sus", "Ver", ...
        Palabra.sanitize(tipoDePalabra);


        boolean ejercicioExiste = false;
        switch (ejercicio) {
            case "Sus Significados": ejercicioExiste = true; break;
            case "Ver Significados": ejercicioExiste = true; break;
            case "Adj Significados": ejercicioExiste = true; break;
            case "Pal Significados": ejercicioExiste = true; break;

            case "Sus Vocabulario": ejercicioExiste = true; break;
            case "Adj Vocabulario": ejercicioExiste = true; break;
            case "Ver Vocabulario": ejercicioExiste = true; break;
            case "Pal Vocabulario": ejercicioExiste = true; break;

            case "Sus Géneros": ejercicioExiste = true; break;
            case "Sus Plurales": ejercicioExiste = true; break;

            case "Ver Participios": ejercicioExiste = true; break;

            case "Adj Comparativos": ejercicioExiste = true; break;
            case "Adj Superlativos": ejercicioExiste = true; break;
        }
        if (!ejercicioExiste) {throw new NumberFormatException("Error: El ejercicio '" + ejercicio + "' no existe. ");}


        //Se eljie un tema
        String tema;
        try {
            tema = Ejer.ElejirTema(tipoDePalabra, sc);
        } catch (SecurityException e) { //dijo "cerrar"
            System.out.println("Cerrando ejercicio");
            return;
        }


        //la lista de palabras completa
        Lista listaDePalabrasCompleta = Control.getTema(tema, tipoDePalabra);


        System.out.println("Hay " + listaDePalabrasCompleta.size() + " palabras en el tema '" + tema + "'. ");
        int num = -1;
        try {
            num = Ejer.ElejirCantidad(listaDePalabrasCompleta.size(), "¿Cuántos deséa practicar?", sc);
        } catch (SecurityException e) {
            System.out.println("Cerrando ejercicio");
            return;
        }

        //la lista de palabras utilizadas en el ejercicio (escogidas aleatoriamente)
        Lista<Palabra> listaDePalabrasUsadas = Lista.escojerAleatorio(listaDePalabrasCompleta, num);


        String[] preguntas = new String[num];
        String[] respuestasCorrectas = new String[num];
        String[] respuestasUsuario = new String[num];
        double calificación;


        for (int i = 0; i < num; i++) {
            Palabra actual = listaDePalabrasUsadas.get(i);

            if (ejercicio.split(" ")[1].equals("Significados")) {
                preguntas[i] = actual.getNombre();
                respuestasCorrectas[i] = actual.getSignificadoSimple();
                continue;
            } else if (ejercicio.split(" ")[1].equals("Vocabulario")) {
                preguntas[i] = actual.getSignificado();
                respuestasCorrectas[i] = actual.getNombreSimple();
                continue;
            } else if (ejercicio.equals("Sus Géneros")) {
                Sus susActual = actual.aSus();
                preguntas[i] = susActual.getNombre();
                respuestasCorrectas[i] = susActual.género;
                continue;
            } else if (ejercicio.equals("Sus Plurales")) {
                Sus susActual = actual.aSus();
                preguntas[i] = susActual.getNombre();
                respuestasCorrectas[i] = susActual.plural;
                continue;
            } else if (ejercicio.equals("Ver Participios")) {
                Ver verActual = actual.aVer();
                preguntas[i] = verActual.getNombre();
                respuestasCorrectas[i] = verActual.participio;
                continue;
            } else if (ejercicio.equals("Adj Comparativos")) {
                Adj adjActual = actual.aAdj();
                preguntas[i] = adjActual.getNombre();
                respuestasCorrectas[i] = adjActual.comparativo;
                continue;
            } else if (ejercicio.equals("Adj Superlativos")) {
                Adj adjActual = actual.aAdj();
                preguntas[i] = adjActual.getNombre();
                respuestasCorrectas[i] = adjActual.superlativo;
                continue;
            }

            //si estámos aquí, no reconocimos bien el ejercicio.
            throw new NumberFormatException("Error: El ejercicio" + ejercicio + "parece estar incompleto.");
        }


        //para el display más abajo
        switch (tipoDePalabra) {
            case Sus.Sus: tipoDePalabra = Sus.Sustantivo; break;
            case Ver.Ver: tipoDePalabra = Ver.Verbo; break;
            case Adj.Adj: tipoDePalabra = Adj.Adjetivo; break;
            case Pal.Pal: tipoDePalabra = Pal.Palabra; break;
        }


        //Aquí comienza el ejercicio.
        try {

            switch (ejercicio.split(" ")[1]) {
                case "Significados":
                    respuestasUsuario = Ejer.EjercicioMatching(preguntas, respuestasCorrectas, tipoDePalabra,"Significado", nullEntry, sc);
                    calificación = Ejer.calificar(preguntas, respuestasUsuario, respuestasCorrectas, "Significado");
                    System.out.println("Calificación final: " + calificación);
                    return;
                case "Vocabulario":
                    respuestasUsuario = Ejer.EjercicioSimple(preguntas, "Significado", tipoDePalabra, nullEntry, sc);
                    calificación = Ejer.calificar(preguntas, respuestasUsuario, respuestasCorrectas, tipoDePalabra);
                    System.out.println("Calificación final: " + calificación);
                    System.out.println("NOTA: Es posible que hayas escrito un sustantivo sinonimo. Esta calificación puede estar equivocada");
                    return;
                case "Género":
                    respuestasUsuario = Ejer.EjercicioSimple(preguntas, "Sustantivo", "Género (M, F, N, o P)", nullEntry, sc);
                    calificación = Ejer.calificar(preguntas, respuestasUsuario, respuestasCorrectas, "Género");
                    System.out.println("Calificación final: " + calificación);
                    return;
                case "Plurales":
                    respuestasUsuario = Ejer.EjercicioSimple(preguntas, "Sustantivo", "Plural", nullEntry, sc);
                    calificación = Ejer.calificar(preguntas, respuestasUsuario, respuestasCorrectas, "Plural");
                    System.out.println("Calificación final: " + calificación);
                    return;
                case "Participios":
                    respuestasUsuario = Ejer.EjercicioSimple(preguntas, "Verbo", "Participio", nullEntry, sc);
                    calificación = Ejer.calificar(preguntas, respuestasUsuario, respuestasCorrectas, "Participio");
                    System.out.println("Calificación final: " + calificación);
                    return;
                case "Comparativos":
                    respuestasUsuario = Ejer.EjercicioSimple(preguntas, "Adjetivo", "Comparativo", nullEntry, sc);
                    calificación = Ejer.calificar(preguntas, respuestasUsuario, respuestasCorrectas, "Comparativo");
                    System.out.println("Calificación final: " + calificación);
                    return;
                case "Superlativos":
                    respuestasUsuario = Ejer.EjercicioSimple(preguntas, "Adjetivo", "Superlativo", nullEntry, sc);
                    calificación = Ejer.calificar(preguntas, respuestasUsuario, respuestasCorrectas, "Superlativo");
                    System.out.println("Calificación final: " + calificación);
                    return;
            }

        } catch (SecurityException e) {
            System.out.println("Cerrando el ejercicio");
            return;
        }

        //Si llegamos aquí, es que no se reconoció el ejercicio en el switch arribita.
        throw new NumberFormatException("Error: No reconocí bien el ejercicio '" + ejercicio + "'. Reclámale a Alberto");

    }





    //SUS, VER, ADJ, O PAL
    /**
     * Intenta enseñarte un tema.
     * @param tema el tema que se enseñará
     * @param tipoDePalabra Sus, Ver, Pal, o Adj. Este no es el lugar de enseñar el tema completo.
     * @param sc scanniboi
     */
    public static void EnseñarTema(String tema, String tipoDePalabra, Scanner sc) {
        throw new NumberFormatException("Aún no me programan!");
        /**
        System.out.println("Iniciando consola de enseñansa de sustantivos.");
        System.out.println("Presione 'Enter' para seguir. Diga 'cerrar' para cerrar el ejercicio.");
        ArrayList listaDePalabras = new ArrayList();
        boolean entendido = false;
        switch (tipoDePalabra) {
            case "Sus": listaDePalabras = Sus.ListaTema(tema); entendido = true; break;
            case "Ver": listaDePalabras = Ver.ListaTema(tema); entendido = true; break;
            case "Adj": listaDePalabras = Adj.ListaTema(tema); entendido = true; break;
            case "Pal": listaDePalabras = Pal.ListaTema(tema); entendido = true; break;
        }
        if (!entendido) {throw new NullPointerException("Error: Tipo de palabra invalido. ");}
        ArrayList listaFinal = Control.escojerAleatorio(listaDePalabras, listaDePalabras.size());

        for (int i = 0; i < listaFinal.size(); i++) {
            System.out.println();
            System.out.println("(" + (i+1) + "/" + (listaFinal.size()) + ") ");

            switch (tipoDePalabra) {
                case "Sus": Sus sus = (Sus)listaFinal.get(i); sus.definir(); break;
                case "Ver": Ver ver = (Ver)listaFinal.get(i); ver.definir(); break;
                case "Adj": Adj adj = (Adj)listaFinal.get(i); adj.definir(); break;
                case "Pal": Pal pal = (Pal)listaFinal.get(i); pal.definir(); break;
            }

            String pausa = sc.nextLine(); //Genera una pausa, lo cual deja que el usuario vaya al paso que quiera.
            if (pausa.equals("cerrar")) {
                System.out.println("Cerrando ejercicio");
                return;
            }
        }*/
    }


    //GENERAL
    /**
     * Ejercicio simple, de preguntas y resuestas. Contiene su propia consola.
     * @throws SecurityException si se dice "cerrar"
     * @param preguntas String array con las preguntas.
     * @param questionType //Indicador de pregunta
     * @param answertype //Indicaor de respuesta
     * @param sc //El scanner
     * @return //Un String[] con los Strings de respuesta. Si se entrega sin acabar, tendra EL STRING INDICADO
     */
    public static String[] EjercicioSimple(String[] preguntas, String questionType, String answertype, String noRespondido, Scanner sc) {

        String[][] ejerArr = new String[preguntas.length + 1][3]; //Aquí se mantiene toda la informacón del eercicio.
        ejerArr[0][0] = "";
        ejerArr[0][1] = questionType;
        ejerArr[0][2] = answertype;
        for (int i = 1; i < preguntas.length+1; i++) {//Empieza con 1
            ejerArr[i][0] = i + "."; //A ver si funciona esto :P
            ejerArr[i][1] = preguntas[i-1]; //preguntas[] usa indices arr, pero ejerArr usa indices numéricos (1, 2...)
            ejerArr[i][2] = noRespondido; //Puede ser lo que quieras.
        }


        /**
         //For display, length is length of longest question.
         int length = -1;
         for (int i = 0; i < preguntas.length; i++) {
         if (length < preguntas[i].length()) {length = preguntas[i].length();}
         }*/


        System.out.println("Comenzando ejercicio");
        System.out.println("Diga '[número] = [respuesta]' para marcar su respuesta. ");
        System.out.println("Diga 'cerrar ejercicio' para cerrar el ejercicio.");
        System.out.println("Diga 'entregar' para entregar el ejercicio. ");



        boolean completado = false;
        while (!completado) { //This recurses for answering all questions

            Control.arrPrint(ejerArr); //Imprime tod o

            while (true) {//This is for parsing answers.
                try {
                    String respuesta = sc.nextLine(); //Lo que responde el usuario
                    switch (respuesta) {
                        case "cerrar ejercicio": throw new SecurityException("Ejercicio terminado");
                        case "entregar":
                            String[] respuestas2 = new String[preguntas.length];
                            for (int i = 0; i < preguntas.length; i++) {
                                respuestas2[i] = ejerArr[i+1][2];
                            }
                            return respuestas2;
                    }


                    String[] tokens = respuesta.split(" ");
                    int index = Integer.parseInt(tokens[0]); //Esta indice debe ser numérica, no de arrays.
                    if (index < 1 || index > preguntas.length) {
                        System.out.println("La pregunta #" + index + " no existe. ");
                        continue;
                    } else if (!tokens[1].equals("=") || tokens.length < 3) { //Chequa el formatéo.
                        System.out.println("xxxNo entendí su respuesta. Diga '[número] = [respuesta]' para marcar su respuesta. ");
                        continue;
                    }

                    //Compila la respuesta (Esto es importante si la respuesta es más de una palabra)
                    //Esto lo deja formateado bien: Sin espacios antes o despues de la respuesta, pero CON espacios en medio (Si los tiene. )
                    String respuestaFinal = "";
                    for (int i = 2; i < tokens.length-1; i++) {
                        respuestaFinal += tokens[i] + " ";
                    }
                    respuestaFinal += tokens[tokens.length-1];

                    //La respuesta que usará el sistema. Nota que la respuesta que se guarda es lo que le sigue al '='.
                    //Si erstamos aquí, quiere decir que la respuesta es valida.
                    ejerArr[index - 0][2] = respuestaFinal;
                } catch (NullPointerException e ) { //Implica que cerramos el ejercicio.
                    throw new NullPointerException("Ejercicio cerrado");
                } catch (Exception e) {
                    //System.out.println(e.getLocalizedMessage());
                    //This can be array index out of bounds, number format exception
                    System.out.println("No entendí su respuesta. Diga '[número] = [respuesta]' para marcar su respuesta. ");
                    continue;
                }
                break;
            } //Si llegamos aquí, es que ya se añadió la repuesta a la lista "respuestas".

            //Detecta la terminación del ejercicio.
            completado = true;
            for (int i = 1; i < ejerArr.length; i++) {
                if (ejerArr[i][2].equals(noRespondido)) {completado = false;}
            }

            //Ultimo revisaje!
            if (completado) {
                Control.arrPrint(ejerArr);
                System.out.println("¿Deséa cambiar sus respuestas? (si/no)");
                while (true) {
                    boolean entendí = false;
                    switch (sc.nextLine()) {
                        case "si": completado = false; entendí = true; break;
                        case "no": entendí = true; break;
                    }

                    //Si no entendimos, te volveremos a preguntar.
                    if (!entendí) {
                        System.out.println("No entendi.");
                    } else {
                        break;
                    }
                }
            }

        }

        //Igual a la entrega de respuestas si dices "entregar"
        String[] respuestas2 = new String[preguntas.length];
        for (int i = 0; i < preguntas.length; i++) {
            respuestas2[i] = ejerArr[i+1][2];
        }
        return respuestas2;

        //return respuestas;
    }


    //GENERAL
    //todo: Add index variable. Maybe we want to not start at A, but at 1, or a, or Ñ, or ?
    /**
     * Ejercicio de matching: Dado dos listas, una de preguntas y una de respuestas, decir cual respuesta le corresponde a cual prgunta.
     * Echa NumberFormatException si las listas de preguntas y respuestas no son del mismo tamaño.
     * Echa NullPointerException si se cierra el ejercicio.
     * @param preguntas Lista de preguntas
     * @param respuestas Lista de respuestas. DEBE DE ESTAR ORGANIZADA CON RESPETO A LAS PREGUNTAS.
     * @param questionType Título de las preguntas (e.g Sustantivos)
     * @param answertype Título de las respuestas (e.g. Significado)
     * @param noRespondido String que indica que la pregunta aún no se ha respuesto.
     * @param sc El escaneador.
     * @return La lista de respuestas del usuario. Si todas están correctas, debería ser identica a @paramrespuestas.
     *         Si no, estarán en un orden distinto. Asi se debe de calificar.
     */
    public static String[] EjercicioMatching(String[] preguntas, String[] respuestas, String questionType, String answertype, String noRespondido, Scanner sc) {

        //Ind.      Sustanivos    Respuestas      Ind.        Significado
        //1.        käse          noRespuesto     A           Queso

        //Verificando que los inputs séan válidos
        if (preguntas.length != respuestas.length) {
            System.out.println("Error: Diferencia entre nuúmero de preguntas y respuestas");
            throw new NumberFormatException("Error: Diferencia entre nuúmero de preguntas y respuestas");
        }

        //Se mexclarán las respuestas
        respuestas = Control.scramble(respuestas);

        //EjerArr es la matriz que contiene toda la información del ejercicio.
        //Este es el formatéo inicial.
        String[][] ejerArr = new String[preguntas.length + 1][5]; //Aquí se mantiene toda la informacón del eercicio.
        ejerArr[0][0] = "Ind.";
        ejerArr[0][1] = questionType;
        ejerArr[0][2] = "Respuestas";
        ejerArr[0][3] = "Ind.";
        ejerArr[0][4] = answertype;
        for (int i = 1; i < preguntas.length + 1; i++) {//Empieza con 1
            ejerArr[i][0] = i + ".";
            ejerArr[i][1] = preguntas[i - 1];
            ejerArr[i][2] = noRespondido; //preguntas[] usa indices arr, pero ejerArr usa indices numéricos (1, 2...)
            ejerArr[i][3] = (char) (i + 64) + ""; //Son indices de letras mayúsculas
            ejerArr[i][4] = respuestas[i - 1];
        }

        //Control.arrPrint(ejerArr);

        System.out.println("Comenzando ejercicio");
        System.out.println("Diga '[número] = [letra]' para marcar su respuesta. ");
        System.out.println("Diga 'cerrar ejercicio' para cerrar el ejercicio.");
        System.out.println("Diga 'entregar' para acabar el ejercicio. ");
        System.out.println("---------------------------------------------------");

        boolean completado = false;
        while (!completado) { //This recurses for nswering all questions

            Control.arrPrint(ejerArr);
            while (true) { //This is for parsing answers. Repeats until a valid answer is found.
                try {
                    String respuesta = sc.nextLine();
                    switch (respuesta) { //Si es un comando, aqupi se lé.
                        case "cerrar ejercicio": throw new NullPointerException("Ejercicio Cerrado");
                        case "entregar": //Igual a el entregue del final del programa.
                            String[] respuestasUsuario = new String[respuestas.length];
                            for (int i = 1; i < ejerArr.length; i++) {
                                respuestasUsuario[i-1] = ejerArr[i][2];
                            }
                            return respuestasUsuario;
                    }

                    //Aquí se decifra el input del usuario.
                    String[] tokens = respuesta.split(" ");
                    int index = Integer.parseInt(tokens[0]); //Esta indice debe ser numérica, no de arrays.
                    if (index < 1 || index > preguntas.length) {
                        System.out.println("La pregunta #" + index + " no existe. ");
                        continue;
                    } else if (!tokens[1].equals("=") || tokens.length != 3) { //Chequa el formatéo.
                        System.out.println("xxxNo entendí su respuesta. Diga '[número] = [respuesta]' para marcar su respuesta. ");
                        continue;
                    }

                    //Decifrámos la índice dada. Debería ser una índice numérica, no de arrays.
                    int índiceRespuesa = (int)tokens[2].charAt(0) - 64; //A = 1, B = 2, ...
                    if (índiceRespuesa < 1 || índiceRespuesa >= ejerArr.length || tokens[2].length() != 1) {
                        System.out.println("La respuesta '" + tokens[2] + "' no existe. ");
                        continue;
                    }

                    //Si llegamos aquí, tenemos una respuesta válida. Aquí mero se marcará
                    ejerArr[index][2] = ejerArr[índiceRespuesa][4];

                } catch (NullPointerException e) {
                    throw e;
                } catch (Exception e) {
                    System.out.println("No entendí su respuesta. Diga '[número] = [letra]' para marcar su respuesta. ");
                    continue;
                }
                break;
            }

            //Chequa la terminación del ejercicio.
            completado = true;
            for (int i = 1; i < ejerArr.length; i++) { //Comienza en el 1, hasta el final de la lista
                if (ejerArr[i][2].equals(noRespondido)) {completado = false; break; }
            }

            //Si ya terminamos, te pediremos que revises tus respuestas.

            //Ultimo revisaje!
            if (completado) {
                Control.arrPrint(ejerArr);
                System.out.println("¿Deséa cambiar sus respuestas? (si/no)");
                while (true) {
                    boolean entendí = false;
                    switch (sc.nextLine()) {
                        case "si": completado = false; entendí = true; break; //Te dejará seguir trabajando
                        case "no": entendí = true; break; //Se entregará abajito.
                    }

                    //Si no entendimos, te volveremos a preguntar.
                    if (!entendí) {
                        System.out.println("No entendi.");
                    } else {
                        break;
                    }
                }
            }

            //Si hemos llegado aquí, quiere decir que ya completaste el ejercicio y quieres que se entregue
        } //Se termina el While Loop.

        //Compilación de resupuestas del usuario
        String[] respuestasUsuario = new String[respuestas.length];
        for (int i = 1; i < ejerArr.length; i++) {
            respuestasUsuario[i-1] = ejerArr[i][2];
        }
        return respuestasUsuario;
    }


    //GENERAL
    /**
     * Califica un ejercicio e imprime los resultados
     * @param preguntas un String[] de preguntas. Deben de ser las preguntas que respondió el usuario
     * @param respuestasUsuario las respuestas del usuario
     * @param respuestasCorrectas las respuestas correctas.
     * @param pregunta Título de lo que tenia que responder el usuario (Género, significado, etc. )
     * @return La calificación de 0 a 1.
     */
    public static double calificar(String[] preguntas, String[] respuestasUsuario, String[] respuestasCorrectas, String pregunta) {
        int num = respuestasUsuario.length;
        String[][] resultados = new String[num + 1][5];
        //número    pregunta    respuesta usuario   respuesta correcta   calificación
        resultados[0][0] = "#";
        resultados[0][1] = pregunta;
        resultados[0][2] = "Respondido";
        resultados[0][3] = "Correcto";
        resultados[0][4] = "Calificación";

        double númeroCorrecto = 0;
        for (int i = 1; i < num + 1; i++) {
            resultados[i][0] = i + ""; //Anota el indice
            resultados[i][1] = preguntas[i - 1]; //Nota el desface
            resultados[i][2] = respuestasUsuario[i - 1];
            resultados[i][3] = respuestasCorrectas[i - 1];
            boolean correcto = (respuestasUsuario[i - 1].equals(respuestasCorrectas[i - 1]));
            if (correcto) {
                resultados[i][4] = "correcto";
                númeroCorrecto++;
            } else {
                resultados[i][4] = "falso";
            }
        }
        double calificación = númeroCorrecto / num;

        Control.arrPrint(resultados);

        return calificación; //Es posible que no se use, pero es bueno tenerlo como algo que se pueda utilizar.
    }



    //GENERAL
    /**
     * Consola que te deja elejir un tema de los usados por algún tipo de palabra.
     * @param tipoDePalabra el tipo de palabra
     * @param sc el Scanner
     * @return un String con el tema elejido
     * @throws SecurityException si se dice 'cerrar'
     */
    public static String ElejirTema(String tipoDePalabra, Scanner sc) {
        Palabra.sanitize(tipoDePalabra);

        String tema;
        while (true) {
            System.out.println("Favor de elejir un tema: ");
            String intento = sc.nextLine();

            switch (intento) { //detecta casos especiales
                case "listar temas":
                    switch (tipoDePalabra) {
                        case "Sus": for(Lista actual : Control.Sustantivos) {System.out.print(actual.nombre + ", ");} break;
                        case "Ver": for(Lista actual : Control.Verbos) {System.out.print(actual.nombre + ", ");} break;
                        case "Adj": for(Lista actual : Control.Adjetivos) {System.out.print(actual.nombre + ", ");} break;
                        case "Pal": for(Lista actual : Control.Palabras) {System.out.print(actual.nombre + ", ");} break;
                    }
                    System.out.println();
                    continue;
                case "cerrar":
                    throw new SecurityException("Ejercicio cerrado");
            } //Solo hay dos inputs raros que pueden ocurrir aqui.


            if (Control.TemaExiste(intento, tipoDePalabra)) {
                tema = intento; //Regresa el tema
                break;
            } else { //te dice que vielvas a intentar
                System.out.print("El tema '" + intento + "' no se encuentra. Diga 'listar temas' para ver los temas.");
                System.out.println(" Diga 'cerrar' para cerrar el ejercicio. ");
                continue;
            }
        }
        return tema;
    }


    /**
     * Consola que te deja elejir una palabra de un tipo especificado.
     * @throws NullPointerException si se cierra. Sabe que hacer si hay más de un resultado
     * @param tipoDePalabra el tipo de palabra.
     * @param sc el scanner
     * @return la palabra elejida.
     */
    public static Palabra ElejirPalabraTipo(String tipoDePalabra, Scanner sc) {
        Palabra.sanitize(tipoDePalabra);
        Palabra[] resultados;

        System.out.print("Favor de elejir ");
        switch (tipoDePalabra) {
            case Sus.Sus: System.out.println("un sustantivo:");
            case Ver.Ver: System.out.println("un verbo:");
            case Adj.Adj: System.out.println("un adjetivo:");
            case Pal.Pal: System.out.println("una palabra:");
        }


        while (true) {
            String intento = sc.nextLine();
            if (intento.equals("cerrar")) {throw new NullPointerException("Cerrando. ");}

            resultados = Palabra.buscarTipo(intento, tipoDePalabra);

            if (resultados.length == 0) { //No se encontró nada.
                System.out.println("La palabra '" + intento + "' no se encuentra. ");
                switch (tipoDePalabra) {
                    case Sus.Sus: System.out.println("Asegúrese de que el sustantivo sea singular y capitalizado.");
                    case Ver.Ver: System.out.println("Asgúrese de que el verbo sea infinitivo y de no capitalizar ninguna letra.");
                    case Adj.Adj: System.out.println("Asegúrese de que el adjetivo no sea comparativo o superlativo.");
                    case Pal.Pal: System.out.println("Asegúrese de no capitalizar ninguna letra.");
                }
                continue; //volvemos a intentar

            } else if (resultados.length > 1) { //Hay más de una palabra con este nombre
                System.out.println("Se encontro más de una palabra llamada '" + intento + "'. Favor de elejir la correcta: ");
                for (int i = 0; i < resultados.length; i++) {
                    System.out.println((i+1) + ". " + resultados[i].getNombre() + ": " + resultados[i].getSignificado());
                }

                int respuesta = Ejer.ElejirCantidad(resultados.length, "¿Cuál buscas?", sc);
                return resultados[respuesta - 1];

            } else { //hay solo una palabra yuju
                return resultados[0];
            }
        }
    }



    //todo: Importante: Estos métodos tienen un Bug: Siempre les falta un elemento del tema.
    //todo: Consolidarlos a un solo ejercicio universal.
    //SUSTANTIVOS
    /**
     * Te 'enseña' todos los sustantivos de algun tema.
     * @param tema El tema elejido
     * @param sc el scanner
     */
    public static void EnseñarSustantivos(String tema, Scanner sc) {
        throw new NumberFormatException("Aún no me programan!");
        /**
        System.out.println("Iniciando consola de enseñansa de sustantivos.");
        System.out.println("Presione 'Enter' para seguir. Diga 'cerrar' para cerrar el ejercicio.");
        ArrayList<Sus> lista = Palabra.ConvertirListaASus(Control.getTema(tema, Sus.Sus).lista);
        lista = Sus.escojerAleatorio(lista, lista.size() - 1);
        for (int i = 1; i < lista.size(); i++) {
            System.out.println();
            System.out.println("(" + i + "/" + (lista.size()-1) + ") ");
            Sus actual = lista.get(i);

            switch (actual.género) {
                case "M":
                    System.out.print("Der ");
                    System.out.println(actual.sustantivo + " (" + actual.plural + ") " + actual.significado);
                    System.out.println("MASCULINO");
                    break;
                case "F":
                    System.out.print("Die ");
                    System.out.println(actual.sustantivo + " (" + actual.plural + ") " + actual.significado);
                    System.out.println("FEMENINO");
                    break;
                case "N":
                    System.out.print("Das ");
                    System.out.println(actual.sustantivo + " (" + actual.plural + ") " + actual.significado);
                    System.out.println("NEUTRO");
                    break;
            }

            String pausa = sc.nextLine(); //Genera una pausa, lo cual deja que el usuario vaya al paso que quiera.
            if (pausa.equals("cerrar")) {
                System.out.println("Cerrando ejercicio");
                return;
            }
        }
         */
    }
    //VERBOS
    /**
     * Te 'enseña' todos los verbos de algun tema.
     * @param tema El tema elejido
     * @param sc el scanner
     */
    public static void EnseñarVerbos(String tema, Scanner sc) {
        throw new NumberFormatException("Aún no me programan!");

        /**System.out.println("Iniciando consola de enseñansa de verbos.");
        System.out.println("Presione 'Enter' para seguir. Diga 'cerrar' para cerrar el ejercicio.");
        ArrayList<Ver> lista = Ver.ListaTema(tema);
        lista = Ver.escojerAleatorio(lista, lista.size() - 1);
        for (int i = 1; i < lista.size(); i++) {
            System.out.println();
            System.out.println("(" + i + "/" + (lista.size()-1) + ") ");
            Ver actual = lista.get(i);


            actual.definir();


            String pausa = sc.nextLine(); //Genera una pausa, lo cual deja que el usuario vaya al paso que quiera.
            if (pausa.equals("cerrar")) {
                System.out.println("Cerrando ejercicio");
                return;
            }
        }*/
    }
    //ADJETIVOS
    /**
     * Te 'enseña' todos los adjetivos de algun tema.
     * @param tema El tema elejido
     * @param sc el scanner
     */
    public static void EnseñarAdjetivos(String tema, Scanner sc) {
        throw new NumberFormatException("Aún no me programan!");
        /**System.out.println("Iniciando consola de enseñansa de adjetivos. ");
        System.out.println("Presione 'Enter' para seguir. Diga 'cerrar' para cerrar el ejercicio. ");
        ArrayList<Adj> lista = Adj.ListaTema(tema);
        lista = Adj.escojerAleatorio(lista, lista.size() - 1);
        for (int i = 1; i < lista.size(); i++) {
            System.out.println();
            System.out.println("(" + i + "/" + (lista.size()-1) + ") ");
            Adj actual = lista.get(i);

            System.out.println(actual.adjetivo + " - " + actual.significado);
            System.out.println(actual.comparativo + ", " + actual.superlativo);

            String pausa = sc.nextLine(); //Genera una pausa, lo cual deja que el usuario vaya al paso que quiera.
            if (pausa.equals("cerrar")) {
                System.out.println("Cerrando ejercicio");
                return;
            }
        }*/
    }
    //PALABRAS
    /**
     * Te 'enseña' todas las palabras de algun tema.
     * @param tema El tema elejido
     * @param sc el scanner
     */
    public static void EnseñarPalabras(String tema, Scanner sc) {
        throw new NumberFormatException("Aún no me programan!"); /**
        System.out.println("Iniciando consola de enseñansa de palabras. ");
        System.out.println("Presione 'Enter' para seguir. Diga 'cerrar' para cerrar el ejercicio. ");
        ArrayList<Pal> lista = Pal.ListaTema(tema);
        lista = Pal.escojerAleatorio(lista, lista.size() - 1);
        for (int i = 1; i < lista.size(); i++) {
            System.out.println();
            System.out.println("(" + i + "/" + (lista.size()-1) + ") ");
            Pal actual = lista.get(i);

            System.out.println(actual.palabra + " - " + actual.significado);

            String pausa = sc.nextLine(); //Genera una pausa, lo cual deja que el usuario vaya al paso que quiera.
            if (pausa.equals("cerrar")) {
                System.out.println("Cerrando ejercicio");
                return;
            }
        }*/
    }

}
