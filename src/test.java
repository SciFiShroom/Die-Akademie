import java.util.Arrays;
import java.util.HashMap;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;



public class test {


    public static boolean emmpiezaConMayuscula(String input, String thing) {
        return Character.isUpperCase(input.charAt(0));
    }






    public static void main(String[] args) {

        /**
        String uno = "definir [palabra]";
        String input = "definir cosa";

        System.out.println(Control.quitarPrefijo(input, "definir "));
        System.out.println(uno.split("\\[")[0] + "'");
        System.out.println();
        System.out.println("'" + Control.quitarPrefijo(input, uno.split("\\[")[0]) + "'");
*/

        //String a = Control.entradaNula;


        try {
            throw new TipoInválidoException("Error: Tipo de palabra inválida");
        } catch (TipoInválidoException e) {
            System.out.println("todo bien :)");
        }




        //String palabra =



        //
        //Control.arrPrint(Control.bidimensional(nums, 3));

        /**
        try {
            throw new NumberFormatException("Error: Super mega no chido.");
        } catch (NumberFormatException e) {
            System.out.println("'" + e.getLocalizedMessage() + "'");
        }
         */



        /**
        String[] nums = new String[]{"1", "2", "3", "4", "5"};
        Lista<String> intento1 = new Lista<String>("hola");
        intento1.add(nums);

        System.out.println(intento1.toString(true));

        intento1.revolver();

        System.out.println(intento1.toString(true));

        for (String actual : nums) {
            System.out.println(actual + ", ");
        }*/


        /**
        //Lector.lector("intento1.txt");
        String test = "\\P";
        System.out.print(test);

        String intento1 = "GOTO A";
        String prefijo = "GOTO ";

        System.out.println("'" + Control.quitarPrefijo(intento1, prefijo) + "'");

        //String test = "Marcador ABC";
        //System.out.println("'" + test.substring(0,9) + "' ; " + test.length());
        //System.out.println("'" + test.substring(9, test.length()) + "'");




        //Control.Inicialización(false, false);


        //System.out.println(Control.quitarSufijo("arbeitest", "este"));
*/

        /**
         Lista<Palabra> lista = Control.getTema("test", "Sus");
         Sus[] Tests = new Sus[5];
         for (int i = 0; i < 5; i++) {Tests[i] = lista.get(i).aSus();}

         for (Sus actual : Tests) {
             System.out.print("Palabra '" + actual.plural + "' colisiona de nombre con ");

             for (int j = 0; j < actual.colisionesSignificado.size(); j++) {
                 System.out.print(actual.colisionesSignificado.get(j).aSus().plural + " ");
             }
             System.out.println();
         }*/


        /**
         Lista<Palabra> listaUno = getTema("test", "Sus");
         System.out.println(listaUno.size());
         Sus actual = listaUno.get(0).aSus();
         System.out.println("{" + actual.getNombre() + "}, {" + actual.plural + "}");
         actual.plural = "pluralote";
         //Palabra[] testo = Palabra.buscarTodo("Test");
         //System.out.println(testo.length);

         Lista<Palabra> listaDos = getTema("test", "Sus");
         Sus actual2 = listaUno.get(0).aSus();
         System.out.println("{" + actual.getNombre() + "}, {" + actual.plural + "}");
         */
        //Palabra[] único = Palabra.buscarTodo("haben");
        //System.out.println(único.length);
        //Ver intento = único[0].aVer();
        //System.out.println(intento.participio);

        /**Hashmaps se acuerdan del objeto percé, y no generan copias. q guai
         HashMap<String, Sus> mapa = new HashMap<String, Sus>();
         Sus test1 = getTema("test", "Sus").get(0).aSus();
         //Sus test2 = getTema("test", "Sus").get(1).aSus();
         System.out.println("SUS {" + test1.getNombre() + "} tiene significado {" + test1.getSignificado() + "}");
         mapa.put(test1.getNombre(), test1);
         test1.significado = "C CAMBIÓ CABRONES";
         Sus nuevo = mapa.get(test1.getNombre());
         System.out.println("SUS {" + nuevo.getNombre() + "} tiene significado {" + nuevo.getSignificado() + "}");
         */


        /**
         Lista<Palabra> intento2 = Control.getTema("auxiliar", "Ver");
         System.out.println(intento2.size());
         Ver haben = intento2.get(0).aVer();
         System.out.println(haben.getNombre());
         System.out.println(haben.getNombreSimple());
         System.out.println(haben.participio);
         */


        //HashMap<String, String> intento1 = new HashMap<String, String>();

        /** Funcionalidad básica
        intento1.put("1", "uno");
        System.out.println(intento1.get("1"));
        intento1.put("1", "ein");
        System.out.println(intento1.get("1"));
        System.out.println(intento1.containsValue("uno")); */

        /**Los HashMaps no guardan pointers, sino una copia de los objetos mismos
        //Por lo tanto, si se cambia el objeto, el HashMap no lo sabrá.
         String uno = "uno";
        String clave = "1";

        intento1.put(clave, uno);
        System.out.println(intento1.get(clave));
        uno = "Uno";
        System.out.println(intento1.get(clave));
        intento1.put(clave, uno);
        System.out.println(intento1.get(clave));*/

        /**
        String uno = "uno";
        ArrayList<String> intento3 = new ArrayList<String>();
        intento3.add(uno);
        uno += "s";
        System.out.println(intento3.contains(uno));
        //intento3.add(uno);*/

    }
}
