public class TipoInválidoException extends RuntimeException {

    /**
     * Se utiliza cuando se recibe un tipo de palabra inválida o nó reconocida por algún programa.
     *
     * También sirve como marcador para todos los métodos que dependan de un tipo de palabra específico.
     */

    public TipoInválidoException() {
        super();
    }

    public TipoInválidoException(String mensaje) {
        super(mensaje);
    }

    public TipoInválidoException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }

    public TipoInválidoException(Throwable causa) {
        super(causa);
    }


}
