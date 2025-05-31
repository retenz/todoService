package com.shortcut.todo.entity.validator;

import com.shortcut.todo.entity.ClsTaskStatus;
import com.shortcut.todo.entity.classifier.TaskStatusClassifier;
import com.shortcut.todo.entity.validator.annotation.ValidTaskStatus;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Optional;

import static java.util.Arrays.asList;

public class ValidTaskStatusValidator implements ConstraintValidator<ValidTaskStatus, ClsTaskStatus> {
    @Override
    public boolean isValid(ClsTaskStatus status, ConstraintValidatorContext constraintValidatorContext) {
        if (status == null) {
            buildViolation(constraintValidatorContext, "Status is required parameter", "ClsTaskStatus");
            return false;
        }

        Optional<TaskStatusClassifier> task = TaskStatusClassifier.fromId(status.getId());
        if (task.isEmpty()) {
            buildViolation(constraintValidatorContext, "Invalid status ID: " + status.getId(), "id");
            return false;
        }

        return true;
    }

    private void buildViolation(ConstraintValidatorContext context, String message, String property) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(message)
                .addPropertyNode(property)
                .addConstraintViolation();
    }
}
