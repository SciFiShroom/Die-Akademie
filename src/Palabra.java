import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Palabra {
    public static final String nullEntry = Control.entradaNula;

    public String[] tags;

    public Lista<String> lecciones;

    public void agregarLección(String lección) {
        if (lecciones.contains(lección)) {
            throw new NumberFormatException("Error: La palabra '" + this.getNombre() + "' ya tene la lección '" + lección + "' agregada.");
        } else {
            lecciones.add(lección);
        }
    }

    //-----------------------------MÉTODOS DE COLISIONES--------------------------------------

    public ArrayList<Palabra> colisionesSignificado = new ArrayList<Palabra>();
    public ArrayList<Palabra> colisionesNombre = new ArrayList<Palabra>();

    public boolean tieneColisionesNombre() {
        if (this.colisionesNombre != null) {
            return (this.colisionesNombre.size() > 0);
        }
        return false;
    }

    public boolean tieneColisionesSignificado() {
        if (this.colisionesSignificado != null) {
            return (this.colisionesSignificado.size() > 0);
        }
        return false;
    }
    //----------------------------------------------------------------------------------------



    //reemplaza al tagAdd previo

    /**
     * Método universal para agregar una Tag. Funciona aún si no tiene ninguna tag, o si aún no se inicializan sus tags.
     * @param newTag el nuevo tag
     * @throws NumberFormatException si se trata de añadir una tag que ya tiene.
     */
    public void agregarTag(String newTag) {

        String[] current;
        if (this.tags == null || this.tags.length == 0) { //Aún no se inicializa la palabra
            current = new String[1];
            current[0] = newTag;
            this.tags = current;
        } else { //ya tiene tags
            //Revisamos que no tenga ya la tag.
            if (Control.contiene(this.tags, newTag)) {throw new NumberFormatException("Error: La palabra '" + this.getNombre() + "' tiene la Tag '" + newTag + "' doble. ");}

            current = new String[this.tags.length + 1];
            for (int i = 0; i < this.tags.length; i++) {
                current[i] = this.tags[i];
            }
            current[this.tags.length] = newTag;
            this.tags = current;
        }


        Control.getTema(newTag, this.TipoDePalabra()).add(this);
    }

    //todo: Agregar método de significado largo, que incluya []. Haz que .getSignificado() no los incluya.

    public String getNombre() {throw new NumberFormatException("teneis los cojones cuadrados");}
    public String getSignificado(){throw new NumberFormatException("Ostras, ¿qué quereis?");}

    //revisar que getNombre no tenga que ser gtNombreSimple
    //y, cómo arreglaremos el asunto de palabras con os significados?

    public String getSignificadoSimple() {
        String sinCorchetes = this.getSignificado().split("\\[")[0];
        //Es posible que tenga " " perdidos. Se los quitaremos de manera general:

        String[] palabras = sinCorchetes.split(" ");
        String out = palabras[0];
        for (int i = 1; i < palabras.length; i++) {
            out += " " + palabras[i];
        }

        return out;
    }
    public String getNombreSimple() {
        return this.getNombre().split("\\[")[0];
    }

    //método para buscar una palabra en una lista de palabras
    public static Palabra[] buscarNombre(String Nombre) {

        //Se busca la palabra "NOMBRE" en la lista de pabras alemánas
        if (Control.listaDeNombres.containsKey(Nombre)) {

            Palabra hallazgo = Control.listaDeNombres.get(Nombre);
            Palabra[] resultados = new Palabra[hallazgo.colisionesNombre.size() + 1]; //la lista no incluye la palabra misma.
            for (int i = 0; i < hallazgo.colisionesNombre.size(); i++) {
                resultados[i] = hallazgo.colisionesNombre.get(i);
            }
            resultados[resultados.length - 1] = hallazgo;
            return resultados;
        }

        throw new SecurityException("Error: No se encontró la palabra '" + Nombre + "'");
    }

    //método para buscar un significado en una lista de palabras
    public static Palabra[] buscarSignificadoSimple(String Significado) {

        //igual que buscarNombre(nom);
        if (Control.listaDeSignificados.containsKey(Significado)) {

            Palabra hallazgo = Control.listaDeSignificados.get(Significado);
            Palabra[] resultados = new Palabra[hallazgo.colisionesSignificado.size() + 1]; //la lista no incluye la palabra misma.
            for (int i = 0; i < hallazgo.colisionesSignificado.size(); i++) {
                resultados[i] = hallazgo.colisionesSignificado.get(i);
            }
            resultados[resultados.length - 1] = hallazgo;
            return resultados;
        }

        throw new SecurityException("Error: No se encontró ninguna palabra con significado '" + Significado + "'");
    }


    /**
     * Método que busca una palabra Nombre, pero que sólo regresa las palabras e tipo Tipo.
     * @param Nombre el Nombre que se busca.
     * @param tipo el tipo de palabra.
     * @throws SecurityException si no se encuentra nada (Se podría cambiar a regresar null, pero...esto me gusta. )
     * @throws SecurityException si se encuentran palabras Nombre, pero ninguna que sea de tipo Tipo.
     * @return una Palabra[] con todos los resultados que se hayan encontrado.
     */
    public static Palabra[] buscarNombreTipo(String Nombre, String tipo) {
        Palabra.sanitize(tipo);

        Palabra[] todo;
        try { //Hay que asegurarnos de que existan
            todo = Palabra.buscarNombre(Nombre);
        } catch (SecurityException e) {
            throw e;
        }

        int numDeTipo = 0;
        for (Palabra actual : todo) {
            if (actual.TipoDePalabra().equals(tipo)) {numDeTipo++;}
        }

        if (numDeTipo == 0) { //También necesita haber del tipo que buscamos
            throw new SecurityException("Error: No se encontró la palabra '" + Nombre + "' de tipo " + tipo);
        }

        //Si llegamos aquí, existen las palabras que buscamos.
        Palabra[] out = new Palabra[numDeTipo];

        int counter = 0;
        for (Palabra actual : todo) {
            if (actual.TipoDePalabra().equals(tipo)) {
                out[counter] = actual;
                counter++;
            }
        }

        return out;
        /**
        Lista<Palabra> ListaDeTipo = new Lista<Palabra>(nullEntry);
        ArrayList<Palabra> Resultados = new ArrayList<Palabra>();


        switch (tipo) {
            case Sus.Sus: ListaDeTipo = Control.SustantivosListaSingular; break;
            case Ver.Ver: ListaDeTipo = Control.VerbosListaSingular; break;
            case Adj.Adj: ListaDeTipo = Control.AdjetivosListaSingular; break;
            case Pal.Pal: ListaDeTipo = Control.PalabrasListaSingular; break;
        }

        for (Palabra actual : ListaDeTipo) {
            if (actual.getNombre().equals(Nombre)) {Resultados.add(actual);}
        }

        Palabra[] out = new Palabra[Resultados.size()];
        for (int i = 0; i < out.length; i++) {out[i] = Resultados.get(i);}
        return out;*/
    }


    /**
     *
     * @param input
     * @return todos los resultados.
     */
    public static Palabra[] buscadorUsuario(String input) {
        return null;
    }



    //Define una palabra, a detalle. Overridden.
    public void definir() {
        System.out.println("Error: definir() no debería de aplicarse a Palabra");
        throw new NumberFormatException("Ich weiß nicht, was du hast gemacht");
        /**
        if (this instanceof Sus) {((Sus) this).definir(); return;}
        if (this instanceof Ver) {((Ver) this).definir(); return;}
        if (this instanceof Adj) {((Adj) this).definir(); return;}
        if (this instanceof Pal) {((Pal) this).definir(); return;}
        */
    }


    public String definirSimple() {
        return (this.getNombre() + "(" + this.getSignificado() + ")");
    }


    public String TipoDePalabra() {
        if (this instanceof Sus) {return Sus.Sus;}
        if (this instanceof Ver) {return Ver.Ver;}
        if (this instanceof Adj) {return Adj.Adj;}
        if (this instanceof Pal) {return Pal.Pal;}

        throw new NumberFormatException("Error: Tipo de palabra no reconocido");
    }
    public String TipoDePalabraCompleto() {
        if (this instanceof Sus) {return Sus.Sustantivo;}
        if (this instanceof Ver) {return Ver.Verbo;}
        if (this instanceof Adj) {return Adj.Adjetivo;}
        if (this instanceof Pal) {return Pal.Palabra;}

        throw new NumberFormatException("Error: Tipo de palabra no reconocido");
    }


    //Transforman una Palabra a su tipo específico.
    public Sus aSus() {
        if (this instanceof Sus) { return (Sus)this; } else {
            throw new NumberFormatException("Error: No se puede convertir de " + this.TipoDePalabra() + " a Sus");
        }
    }
    public Ver aVer() {
        if (this instanceof Ver) { return (Ver)this; } else {
            throw new NumberFormatException("Error: No se puede convertir de " + this.TipoDePalabra() + " a Ver");
        }
    }
    public Adj aAdj() {
        if (this instanceof Adj) { return (Adj)this; } else {
            throw new NumberFormatException("Error: No se puede convertir de " + this.TipoDePalabra() + " a Adj");
        }
    }
    public Pal aPal() {
        if (this instanceof Pal) { return (Pal)this; } else {
            throw new NumberFormatException("Error: No se puede convertir de " + this.TipoDePalabra() + " a pal");
        }
    }

    //Transforman una ArrayList<Palabra> a un ArrayList<Tipo>.
    public static ArrayList<Sus> ConvertirListaASus(ArrayList<Palabra> original) {
        ArrayList<Sus> out = new ArrayList<Sus>();
        for (Palabra actual : original) {
            out.add(actual.aSus());
        }
        return out;
    }
    public static ArrayList<Ver> ConvertirListaAVer(ArrayList<Palabra> original) {
        ArrayList<Ver> out = new ArrayList<Ver>();
        for (Palabra actual : original) {
            out.add(actual.aVer());
        }
        return out;
    }
    public static ArrayList<Adj> ConvertirListaAAdj(ArrayList<Palabra> original) {
        ArrayList<Adj> out = new ArrayList<Adj>();
        for (Palabra actual : original) {
            out.add(actual.aAdj());
        }
        return out;
    }
    public static ArrayList<Pal> ConvertirListaAPal(ArrayList<Palabra> original) {
        ArrayList<Pal> out = new ArrayList<Pal>();
        for (Palabra actual : original) {
            out.add(actual.aPal());
        }
        return out;
    }

    //Lo mismo pero con una Lista
    public static Lista<Sus> ConvertirListaASus(Lista<Palabra> original) {
        Lista out = new Lista<Sus>(original.nombre);
        out.lista = ConvertirListaASus(original.lista);
        return out;
    }



    //Usa esta función para asegurarte de que no haya tipos de palabras invalidas.
    public static void sanitize(String palabra) {
        switch (palabra) {
            case Sus.Sus: return;
            case Ver.Ver: return;
            case Adj.Adj: return;
            case Pal.Pal: return;
        }

        throw new NumberFormatException("Error: Tipo de palabra no reconocido");
    }


    @Override
    public String toString() {
        return this.getNombreSimple();
    } //Se usa en Lista


    public Lista<Palabra> sinónimos; //palabras que significan exactamete lo mismo, sin ninguna diferencia en contexto o uso.
    public Lista<Palabra> significados; //Se añaden otros significados. Esta es fácil de implementar.
    //todo: mayhaps a name hashmap?



    public String[] descripción;
    public void agregarDescripción(String[] Descripción) {
        if (this.descripción!= null && this.descripción.length != 0) {
            throw new NumberFormatException("Error: Descripción ya agregada a la palabra '" + this.getNombre() + "'");
        } //Ya se agregó una descripción

        this.descripción = Descripción;
    }

    public void agregarDescripción(String Descripción) {
        agregarDescripción(new String[]{Descripción});
    }

}
