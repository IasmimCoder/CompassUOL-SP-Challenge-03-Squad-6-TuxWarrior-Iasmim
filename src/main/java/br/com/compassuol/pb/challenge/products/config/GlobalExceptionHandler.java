package br.com.compassuol.pb.challenge.products.config;

import java.util.Date;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.compassuol.pb.challenge.products.exceptions.MessageExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<MessageExceptionHandler> handleValidationException(MethodArgumentNotValidException ex) {
        FieldError fieldError = ex.getBindingResult().getFieldError();
        String errorMessage = fieldError.getDefaultMessage();

        MessageExceptionHandler messageExceptionHandler = new MessageExceptionHandler(new Date(), 400, errorMessage);
        return ResponseEntity.badRequest().body(messageExceptionHandler);
    }
}