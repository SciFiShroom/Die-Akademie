import java.util.ArrayList;
public class Lista {

    //Una lista con funciones adicionales a un ArrayList tradicional.
    public ArrayList lista;
    private String nombre;

    public <E> Lista(String nombreNuevo) {
        this.nombre = nombreNuevo;
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

    public void add(Object loquesea) {
        this.lista.add(loquesea);
    }

    public String Nombre() {
        return this.nombre;
    }

    public boolean contains(Object X) {
        return this.lista.contains(X);
    }

    public Object get(int i) {
        return this.lista.get(i);
    }



    public static void main(String[] args) {
        Lista intento1 = new Lista("cosas");
        intento1.add("hello world");
        System.out.println((String)intento1.get(0));
        System.out.println(intento1.size());

    }
}
