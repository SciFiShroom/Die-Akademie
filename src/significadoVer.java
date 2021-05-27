import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;

public class significadoVer {

    public String significado;
    public String[][] oraciónes;
    public String auxiliar;
    public String[] commentarios;

    public significadoVer(String significado, String[][] oraciónes, String auxiliar, String[] commentarios) {
        this.significado = significado;
        this.oraciónes = oraciónes;
        this.auxiliar = auxiliar;
        this.commentarios = commentarios;
    }

    public void definir(Ver verbo) {
                if (auxiliar != null) {
            System.out.print(" (" + auxiliar + ") ");
        }
        System.out.println(significado);
        for (int i = 0; i < oraciónes.length; i++) {
            System.out.println("\"" + oraciónes[i][0] + "\"");
            System.out.println("\"" + oraciónes[i][1] + "\"");
            System.out.println();
        }
        //System.out.println();
        for (String com : commentarios) {System.out.println(com);}
        System.out.println("Tags: " + Arrays.toString(verbo.tags));
    }

}
