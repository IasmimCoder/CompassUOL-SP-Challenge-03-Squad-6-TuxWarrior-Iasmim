package br.com.compassuol.pb.challenge.products.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.compassuol.pb.challenge.products.exceptions.EntityNotFoundException;
import br.com.compassuol.pb.challenge.products.exceptions.BusinessExceptions;

@ControllerAdvice
public class ExceptionsHandler extends ResponseEntityExceptionHandler {

    // @ExceptionHandler(BusinessExceptions.class)
    // protected ResponseEntity<ExceptionResponse> handleSecurity(BusinessExceptions e) {
    //     return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(new ExceptionResponse(e.getMessage()));
    // }

    // @ExceptionHandler(EntityNotFoundException.class)
    // protected ResponseEntity<ExceptionResponse> handleSecurity(EntityErrorDetails e) {
    //     return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionResponse(e.getMessage()));
    // }
}