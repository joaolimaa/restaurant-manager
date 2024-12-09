package fiap.restaurant_manager.infrastructure.util;

import lombok.val;

public class formatters {

    public static String formatPostalCode(String postalCode) {
        return postalCode.replaceAll("\\D", "");
    }

    public static String formatCNPJ(String cnpj) {
        val numericCnpj = cnpj.replaceAll("\\D", "");
        return numericCnpj.replaceFirst("(\\d{2})(\\d{3})(\\d{3})(\\d{4})(\\d{2})", "$1.$2.$3/$4-$5");
    }

    public static String formatCPF(String cpf) {
        val numericCpf = cpf.replaceAll("\\D", "");
        return numericCpf.replaceFirst("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4");
    }
}
