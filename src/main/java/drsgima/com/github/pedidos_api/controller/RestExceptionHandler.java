package drsgima.com.github.pedidos_api.controller;

import drsgima.com.github.pedidos_api.exception.DataIntegratyException;
import drsgima.com.github.pedidos_api.exception.ObjectNotFoundException;
import drsgima.com.github.pedidos_api.exception.StandardError;
import drsgima.com.github.pedidos_api.exception.ValidationError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> handlerMethodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request) {
        ValidationError validationError = new ValidationError(HttpStatus.BAD_REQUEST.value(), "Erro de validação", System.currentTimeMillis());
        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            validationError.addError(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validationError);
    }

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> handlerObjectNotFoundException(ObjectNotFoundException e, HttpServletRequest request) {
        StandardError standardError = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(standardError);
    }

    @ExceptionHandler(DataIntegratyException.class)
    public ResponseEntity<StandardError> handlerDataIntegratyException(
            DataIntegratyException e,
            HttpServletRequest request) {

        StandardError standardError = new StandardError(
                HttpStatus.BAD_REQUEST.value(),
                e.getMessage(),
                System.currentTimeMillis());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(standardError);
    }
}
