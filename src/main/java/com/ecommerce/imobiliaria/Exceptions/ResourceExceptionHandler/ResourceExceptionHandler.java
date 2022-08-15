package com.ecommerce.imobiliaria.Exceptions.ResourceExceptionHandler;

import com.ecommerce.imobiliaria.Exceptions.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({DataIntegrityViolationException.class, IllegalStateException.class, DataIntegrityViolationException.class, EntityNotFoundException.class, IllegalArgumentException.class})
    public ResponseEntity<StandardError> dataIntegrityViolationException(DataIntegrityViolationException ex,
                                                                         HttpServletRequest request){
        StandardError error = new StandardError(
                System.currentTimeMillis(),
                HttpStatus.CONFLICT.value(),
                "Conflict Data Value",
                ex.getMessage(),
                request.getRequestURI());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }
}
