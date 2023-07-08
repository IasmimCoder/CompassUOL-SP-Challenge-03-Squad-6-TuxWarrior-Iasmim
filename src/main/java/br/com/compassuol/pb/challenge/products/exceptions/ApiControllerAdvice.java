package br.com.compassuol.pb.challenge.products.exceptions;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ApiControllerAdvice {

    @ResponseBody
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<MessageExceptionHandler> entityNotFound(EntityNotFoundException entityNotFoundException) {
        MessageExceptionHandler error = new MessageExceptionHandler(
            new Date(),
            HttpStatus.NOT_FOUND.value(),
            "Category Not Found"
        );
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
    
    @ResponseBody
    @ExceptionHandler(EntityAlreadyExistsException.class)
    public ResponseEntity<MessageExceptionHandler> entityAlreadyExist(EntityAlreadyExistsException entityNotFoundException) {
        MessageExceptionHandler error = new MessageExceptionHandler(
            new Date(),
            HttpStatus.BAD_REQUEST.value(),
            entityNotFoundException.getMessage()
        );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}