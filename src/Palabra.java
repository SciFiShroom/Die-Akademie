import java.lang.reflect.Array;
import java.util.ArrayList;

public class Palabra {
    public static final String nullEntry = "---";

    public String[] tags;
    public String palabra;

    //reemplaza al tagAdd previo
    public void agregarTag(String newTag) {
        String[] current = new String[this.tags.length + 1];
        for (int i = 0; i < this.tags.length; i++) {
            current[i] = this.tags[i];
        }
        current[this.tags.length] = newTag;
        this.tags = current;


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
        throw new NullPointerException("Error: Tag '" + newTag + "' no reconocida");
    }

    //todo: Agregar método de significado largo, que incluya []. Haz que .getSignificado() no los incluya.

    public String getNombre() {throw new NumberFormatException("teneis los cojones cuadrados");}
    public String getSignificado(){throw new NumberFormatException("Ostras, ¿qué quereis?");}

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


}
