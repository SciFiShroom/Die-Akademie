import java.util.ArrayList;

public class Palabra {

    public String[] tags;


    //reemplaza al tagAdd previo
    public void agregarTag(String newTag, String TipoDePalabra) {
        String[] current = new String[this.tags.length + 1];
        for (int i = 0; i < this.tags.length; i++) {
            current[i] = this.tags[i];
        }
        current[this.tags.length] = newTag;
        this.tags = current;


        ArrayList<Lista<Palabra>> listaTematica = null;
        boolean entendido = false;
        switch (TipoDePalabra) {
            case Sus.Sus: listaTematica = Control.Sustantivos; entendido = true; break;
            case Ver.Ver: listaTematica = Control.Verbos; entendido = true; break;
            case Adj.Adj: listaTematica = Control.Adjetivos; entendido = true; break;
            case Pal.Pal: listaTematica = Control.Palabras; entendido = true; break;
        }
        if(!entendido) {throw new NullPointerException("Error: Tipo de palabra no reconocido: " + TipoDePalabra);}


        for (Lista<Palabra> actual : listaTematica) {
            if (actual.nombre.equals(newTag)) {
                actual.add(this);
                return; //Termina la busqueda en cuanto se encuentre la lista correcta
            }
        }

        //Si no se encuentra, echa un error.
        throw new NullPointerException("Error: Tag '" + newTag + "' no reconocida");
    }
}
