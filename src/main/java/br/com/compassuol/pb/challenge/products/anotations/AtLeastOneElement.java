package br.com.compassuol.pb.challenge.products.anotations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = AtLeastOneElementValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface AtLeastOneElement {
    String message() default "At least one element is required";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}