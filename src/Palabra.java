import java.lang.reflect.Array;
import java.util.ArrayList;

public class Palabra {
    public static final String nullEntry = "---";

    public String[] tags;
    public String palabra;

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


        /**
        ArrayList<Lista<Palabra>> listaTematica = null;
        boolean entendido = false;

        if (this instanceof Sus) {listaTematica = Control.Sustantivos; entendido = true;}
        if (this instanceof Ver) {listaTematica = Control.Verbos; entendido = true;}
        if (this instanceof Adj) {listaTematica = Control.Adjetivos; entendido = true;}
        if (this instanceof Pal) {listaTematica = Control.Palabras; entendido = true;}
        if(!entendido) {throw new NumberFormatException("Error: Tipo de palabra no reconocido");}


        for (Lista<Palabra> actual : listaTematica) {
            if (actual.nombre.equals(newTag)) {
                actual.add(this);
                return; //Termina la busqueda en cuanto se encuentre la lista correcta
            }
        }

        //Si no se encuentra, echa un error.
        throw new NullPointerException("Error: Tag '" + newTag + "' no reconocida");*/
    }

    //todo: Agregar método de significado largo, que incluya []. Haz que .getSignificado() no los incluya.

    public String getNombre() {throw new NumberFormatException("teneis los cojones cuadrados");}
    public String getSignificado(){throw new NumberFormatException("Ostras, ¿qué quereis?");}

    //revisar que getNombre no tenga que ser gtNombreSimple
    //y, cómo arreglaremos el asunto de palabras con os significados?

    public String getSignificadoSimple() {
        return this.getSignificado().split("\\[")[0];
    }

    public String getNombreSimple() {
        return this.getNombre().split("\\[")[0];
    }

    //método para buscar una palabra en una lista de palabras
    public static Palabra[] buscarTodo(String Nombre) {
        ArrayList<Palabra> Resultados = new ArrayList<Palabra>();

        for (Palabra actual: Control.SustantivosListaSingular) {
            if (actual.getNombre().equals(Nombre)) {Resultados.add(actual);}
        }

        for (Palabra actual: Control.VerbosListaSingular) {
            if (actual.getNombre().equals(Nombre)) {Resultados.add(actual);}
        }

        for (Palabra actual: Control.AdjetivosListaSingular) {
            if (actual.getNombre().equals(Nombre)) {Resultados.add(actual);}
        }

        for (Palabra actual: Control.PalabrasListaSingular) {
            if (actual.getNombre().equals(Nombre)) {Resultados.add(actual);}
        }

        Palabra[] out = new Palabra[Resultados.size()];
        for (int i = 0; i < out.length; i++) {out[i] = Resultados.get(i);}
        return out;
    }

    public static Palabra[] buscarTipo(String Nombre, String tipo) {
        Palabra.sanitize(tipo);

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
        return out;
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
    }
}
