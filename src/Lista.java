import java.util.ArrayList;
import java.util.Iterator;

public class Lista<E> implements Iterable<E>{

    private E e;

    //Una lista con funciones adicionales a un ArrayList tradicional.
    public ArrayList lista;
    public String nombre;

    public <E> Lista(String Nombre) {
        this.nombre = Nombre;
        lista = new ArrayList<E>();
    }
    public <E> Lista() {
        lista = new ArrayList<E>();
    }

    /**
    public Lista(String nombreNuevo) {
        this.nombre = nombreNuevo;
        lista = new ArrayList();
    }*/

    public int size() {
        return this.lista.size();
    }
    public void add(E loquesea) {
        this.lista.add(loquesea);
    }
    public String getNombre() {
        return this.nombre;
    }
    public boolean contains(E X) {
        return this.lista.contains(X);
    }
    public E get(int i) {
        return (E)this.lista.get(i);
    }



    public Iterator iterator() {
        return new IteradorDeLista();
    }

    private class IteradorDeLista implements Iterator{
        private int pos = 0; //La posición actual del iterador. Como siempre, empezamos con 0.
        // Esta índice es una índice de Arrays.

        public boolean hasNext() {
            return ((pos+1) < lista.size());
        }


        public E next() {
            if (this.hasNext()) {
                pos++;
                return (E)lista.get(pos);
            } else {return null;}
        }



        public void remove() {
            throw new NullPointerException("¿Cómo le hiciste para llegar aquí?");
        }

    }


    //Escoje "num" valores aleatorios de una Lista, sin repeticiones, y los revuelve. Regresa una lista nueva.
    public static Lista escojerAleatorio(Lista input, int num) {
        if (input.size() < num) {throw new NumberFormatException("Error: Num excede tamaño de lista input");}

        Lista revuelta = new Lista("out mah bois"); //La lista con los valores revueltos
        boolean[] indices = new boolean[input.size()];

        for (int i = 0; i < num; i++) { //'num' iteraciones
            int índice = (int)(input.size()*Math.random()); //Un número aleatorio entre 0 y input.size() - 1
            //Si Math.Random() == 1.0000000000, se rompe tödo.

            if (índice == input.size() || indices[índice]) {
                i--;
                continue;
            } // Esto se asegura de que la índice sea válida.
            //También se asegura de que no aya entradas dobles. Si la índice pasa el filtro, se agrega.
            revuelta.add(input.get(índice));
            indices[índice] = true;
        }
        return revuelta;
    }

    public String toString() {return this.toString(false);}
    public String toString(boolean conNombre) {
        String out = "";

        if (conNombre) {out += this.getNombre() + ": ";}

        out += "[";
        for (int i = 0; i < this.size() - 1; i++) {
            out += this.get(i).toString() + ", ";
        }
        out += this.get(this.size()-1).toString() + "]";
        return out;
    }

    public String[][] toStringChido(boolean print) {
        System.out.println("aún no");
        /**


        if (print) {
            Control.arrPrint();
            return null;
        } else {
            return
        }*/
        return null;
    }


    /**
     * Método que concatena dos listas.
     * @param a la primera lista.
     * @param b la seguna lista
     * @return la lista concatenada.
     */
    public static Lista concatenar(Lista a, Lista b) {
        Lista out = new Lista();

        for (int i = 0; i < a.size(); i++) {
            out.add(a.get(i));
        }

        for (int i = 0; i < b.size(); i++) {
            out.add(b.get(i));
        }

        return out;
    }


    public static void main(String[] args) {
        //System.out.println("y the fuck u runnin from here?");
        //Control.Inicialización(false, false);

        Lista<Integer> intento = new Lista<Integer>("listota");

        for (int i = 1; i < 11; i++) {
            intento.add(i);
        }

        System.out.println(intento.toString(false));

        Lista<Integer> segundo = Lista.escojerAleatorio(intento, 10);

        System.out.println(segundo.toString(false));



        /**
        Lista intento1 = new Lista("cosas");
        intento1.add("hello world");
        System.out.println((String)intento1.get(0));
        System.out.println(intento1.size());

        Lista<String> intento2 = new Lista<String>("hola");
        intento2.add("hi");
        System.out.println("and ");
        intento2.add(5);
        System.out.println(intento2.size());
        System.out.println(intento2.get(1));
         */

        /**
        //Ejemplo de iteración.
        Lista<String> chido = new Lista("chido");
        for (int i = 0; i <= 5; i++) {
            chido.add(Integer.toString(i));
        }
        System.out.print("[");
        for (String actual : chido) {
            System.out.print(actual + ", ");
        }
        System.out.println("]");
        */

        //ejemplo de como funcionan los tipos.
        /*
        Lista<Palabra> listaPalabras = new Lista<Palabra>("nombre");
        Sus susNuevo = new Sus();
        listaPalabras.add(susNuevo);
        listaPalabras.add(new Ver("q ase"));
        //se pueden agregar, ya que extienden Palabra.

        Lista<Sus> soloSustantivos = new Lista<Sus>("aquí solo sustantivos, porfa");
        soloSustantivos.add(new Sus("chido"));
        //soloSustantivos.add(new Ver("chido"));
        //Solo se pueden agregar objetos Sus. :]
        */
    }
}
