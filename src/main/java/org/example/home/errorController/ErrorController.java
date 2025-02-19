package org.example.home.errorController;

import jakarta.validation.ConstraintViolationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorController {
    @ExceptionHandler(ConstraintViolationException.class)

    public ErrorDTO errorValidation (ConstraintViolationException e) {
        return new ErrorDTO(400,e.getMessage());
    }

}
