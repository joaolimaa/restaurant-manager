package fiap.restaurant_manager.infrastructure.util;

public class formatters {

    public static String formatPostalCode(String postalCode) {
        return postalCode.replaceAll("\\D", "");
    }
}
