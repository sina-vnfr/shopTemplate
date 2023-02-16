package com.example.shoptemplete.controller.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Path;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map handle(ConstraintViolationException exception) {
        return exception
                .getConstraintViolations()
                .stream()
                .collect(Collectors.toMap(this::getFieldName, ConstraintViolation::getMessage, (f, m) -> f));
    }

    private Path.Node getFieldName(ConstraintViolation constraintViolation) {
        return StreamSupport.stream(constraintViolation.getPropertyPath().spliterator(), false).
                reduce((first, second) -> second).orElse(null);
    }
}
