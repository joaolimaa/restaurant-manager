package fiap.restaurant_manager.domain.exception;

public class InvalidAddressException extends RuntimeException {

    private static final String DEFAULT_MESSAGE = "Endereço inválido.";

    public InvalidAddressException() {
        super(DEFAULT_MESSAGE);
    }

    public InvalidAddressException(Throwable cause) {
        super(DEFAULT_MESSAGE, cause);
    }
}
