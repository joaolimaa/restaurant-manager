package fiap.restaurant_manager.domain.exception;

public class UserNotFoundException extends RuntimeException {

    private static final String DEFAULT_MESSAGE = "Usuário não encontrado.";

    public UserNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    public UserNotFoundException(Throwable cause) {
        super(DEFAULT_MESSAGE, cause);
    }
}
