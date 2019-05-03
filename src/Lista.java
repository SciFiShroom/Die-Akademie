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

    public String Nombre() {
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
            throw new NullPointerException("ostia morro que me habeis ditcho?");
        }

    }


    public static void main(String[] args) {
        //System.out.println("y the fuck u runnin from here?");

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
