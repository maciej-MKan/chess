package pl.mkan.controller.dto.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UniquePiecesPositionValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniquePiecesPosition {
    String message() default "Pieces cannot occupy the same position";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
