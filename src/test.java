import java.util.Arrays;
public class test {

    /**
     * @param input also hi
     * @param thing hi
     * @return hiii
     */
    public static boolean emmpiezaConMayuscula(String input, String thing) {
        return Character.isUpperCase(input.charAt(0));
    }

    public static void main(String[] args) {

        String[] listota = new String[]{"Hola", "que", "pex?"};

        System.out.println(Control.contiene(listota, "Hola"));


    }
}
