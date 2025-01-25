package com.bigeventbackend.validation;

import com.bigeventbackend.anno.State;
import jakarta.validation.ConstraintValidator;

public class StateValidation implements ConstraintValidator<State, String> {
    /**
     *
     * @param value
     * @param context
     * @return
     */
    @Override
    public boolean isValid(String value, jakarta.validation.ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }
        if (value.equals("posted") || value.equals("draft")) {
            return true;
        }
        return false;
    }
}
