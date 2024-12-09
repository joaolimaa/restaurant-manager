package fiap.restaurant_manager.domain.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CepValidator implements ConstraintValidator<ValidCep, String> {

    // Regex para validar os formatos de CEP (xx.xxx-xxx, xxxxxx-xxx, etc)
    private static final String POSTAL_CODE_REGEX = "^((\\d{2}\\.\\d{3}-\\d{3})|(\\d{2}\\d{3}-\\d{3})|(\\d{8}))$";

    @Override
    public boolean isValid(String postalCode, ConstraintValidatorContext context) {
        return postalCode != null && postalCode.matches(POSTAL_CODE_REGEX);
    }
}
