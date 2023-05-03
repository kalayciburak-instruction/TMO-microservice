package com.kodlamaio.commonpackage.configuration.exceptions;

import com.kodlamaio.commonpackage.utils.exceptions.BusinessException;
import com.kodlamaio.commonpackage.utils.results.ExceptionResult;
import org.modelmapper.ValidationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST) // 400
    public ExceptionResult<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        Map<String, String> validateErrors = new HashMap<>();
        for (FieldError fieldError : exception.getBindingResult().getFieldErrors()) {
            validateErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        return new ExceptionResult<>("Validation Exception", validateErrors);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY) // 422
    public ExceptionResult<Object> handleValidationException(ValidationException exception) {
        return new ExceptionResult<>("Validation Exception", exception.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ExceptionResult<Object> handleBusinessException(BusinessException exception) {
        return new ExceptionResult<>("Business Exception", exception.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT) // 409
    public ExceptionResult<Object> handleDataIntegrityViolation(DataIntegrityViolationException exception) {
        return new ExceptionResult<>("Data Integrity Violation Exception", exception.getMessage());
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) // 500
    public ExceptionResult<Object> handleException(Exception exception) {
        return new ExceptionResult<>("Exception", exception.getMessage());
    }

}
