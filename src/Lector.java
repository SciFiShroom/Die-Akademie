import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Lector {


    /**
     * Lector que leerá archivos .txt. Implementa funcionalidad adicional, como GOTO, pausas, etc.
     * Su propósito es facilitar la creación de lecciones nuevas.
     * @param archivo El archivo que se leerá. Utilizamos la ruta relativa a Die-Akademie, osea que basta decir "texto.txt".
     */
    public static void lector(String archivo) {

        File actual = new File("./src/" + archivo);
        try {
            Scanner sc = new Scanner(actual);
            Scanner usuario = new Scanner(System.in);

            boolean imprimir = true;
            int línea = 0;
            while (sc.hasNextLine()) {
                String fila = sc.nextLine();

                //Comentarios: No se imprimen
                if (fila.length() > 1 && fila.charAt(0) == '/' && fila.charAt(1) == '/') {
                    continue;
                }

                //Inicia comentario largo
                if (fila.length() > 2 && fila.charAt(0) == '/' && fila.charAt(1) == '*' && fila.charAt(2) == '*') {
                    if (!imprimir) {throw new NumberFormatException("Error: Iniciaste un comentario, pero una ya estaba activo. Línea " + línea);}
                    imprimir = false;
                    continue;
                }

                //Termina un comentario largo
                if (fila.length() > 1 && fila.charAt(0) == '*' && fila.charAt(1) == '/') {
                    if (imprimir) {throw new NumberFormatException("Error: Ningún comentario iniciado. Línea " + línea);}
                    imprimir = true;
                    continue;
                }

                //Pausa
                if (fila.equals("{P}")) {
                    String ignorame = usuario.nextLine();
                    continue;
                }

                //Finalizar el documento
                if (fila.equals("fin")) {
                    return;
                }

                //Control GOTO
                if (fila.length() > 3 && fila.substring(0, 3).equals("GOTO ")) {
                    if (fila.equals("GOTO user")) {
                        String lugar = usuario.nextLine();

                    }
                }



                if (imprimir) {System.out.println(fila);}
                línea++;
            }



        } catch (FileNotFoundException e) {
            System.out.println("No se encontró el archivo '" + archivo + "'. ");
            return;
        }


    }



    public static void main(String[] args) {
        lector("intento1.txt");

        //todo: Finish goto LUL
    }
}
