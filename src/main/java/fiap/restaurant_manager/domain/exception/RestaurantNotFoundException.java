package fiap.restaurant_manager.domain.exception;

public class RestaurantNotFoundException extends RuntimeException {

    private static final String DEFAULT_MESSAGE = "Restaurante não encontrado.";

    public RestaurantNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    public RestaurantNotFoundException(Throwable cause) {
        super(DEFAULT_MESSAGE, cause);
    }
}
