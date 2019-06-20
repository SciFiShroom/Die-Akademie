public class ConsolaCerradaException extends RuntimeException{

    /**
     * Esta excepción se utiliza cuando una consola que tiene que regresar un valor se cierra por el usuario.
     * En caso de que no tenga que regresar un valor (por ejemplo, cerrar la consola principal),
     * se recomienda simplemente utilizar 'return;', ya que harían lo mismo.
     */

    public ConsolaCerradaException() {
        super();
    }

    public ConsolaCerradaException(String mensaje) {
        super(mensaje);
    }

    public ConsolaCerradaException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }

    public ConsolaCerradaException(Throwable causa) {
        super(causa);
    }


}
