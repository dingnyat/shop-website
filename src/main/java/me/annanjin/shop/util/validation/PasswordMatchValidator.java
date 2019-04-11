package me.annanjin.shop.util.validation;

import me.annanjin.shop.model.RegisterForm;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchValidator implements ConstraintValidator<PasswordMatcher, Object> {
    @Override
    public void initialize(PasswordMatcher constraintAnnotation) {
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        RegisterForm registerForm = (RegisterForm) value;
        return registerForm.getPassword().equals(registerForm.getConfirmPassword());
    }
}
