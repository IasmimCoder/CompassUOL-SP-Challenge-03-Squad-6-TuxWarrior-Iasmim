package br.com.compassuol.pb.challenge.products.anotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class AtLeastOneElementValidator implements ConstraintValidator<AtLeastOneElement, List<?>> {
    @Override
    public boolean isValid(List<?> value, ConstraintValidatorContext context) {
        if (value == null || value.isEmpty()) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("At least one category is required")
                    .addConstraintViolation();
            return false;
        }
        return true;
    }
}