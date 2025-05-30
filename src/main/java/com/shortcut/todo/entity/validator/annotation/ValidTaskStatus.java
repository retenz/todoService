package com.shortcut.todo.entity.validator.annotation;

import com.shortcut.todo.entity.validator.ValidTaskStatusValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
@Constraint(validatedBy = ValidTaskStatusValidator.class)
public @interface ValidTaskStatus {
    String message() default "Invalid task status";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
