package com.codexmind.establishment.configuration;

import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.codexmind.establishment.exceptions.AuthorizationException;
import com.codexmind.establishment.exceptions.DataIntegrityException;
import com.codexmind.establishment.exceptions.EntityNotFoundException;
import com.codexmind.establishment.exceptions.StandardError;
import jakarta.servlet.http.HttpServletRequest;
import org.hibernate.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity notFound404() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity badRequest400(MethodArgumentNotValidException ex) {

        var list = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(list.stream().map(ErrorValidationData::new).toList());
    }

    @ExceptionHandler(DataIntegrityException.class)
    public ResponseEntity<StandardError> dataIntegrity(DataIntegrityException ex) {

        StandardError err = new StandardError(HttpStatus.BAD_REQUEST.value(), ex.getMessage(), System.currentTimeMillis(), null);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    public record ErrorValidationData(String field, String message) {

        public ErrorValidationData(FieldError error) {
            this(error.getField(), error.getDefaultMessage());
        }
    }

    @ExceptionHandler(AuthorizationException.class)
    public ResponseEntity<StandardError> authorization(ObjectNotFoundException e, HttpServletRequest req) {
        StandardError err = new StandardError(HttpStatus.FORBIDDEN.value(), e.getMessage(), System.currentTimeMillis(), req.getRequestURI());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(err);

    }

    @ExceptionHandler(AmazonS3Exception.class)
    public ResponseEntity<StandardError> authorization(AmazonS3Exception e, HttpServletRequest req) {
        StandardError err = new StandardError(HttpStatus.BAD_REQUEST.value(), e.getMessage(), System.currentTimeMillis(), req.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<StandardError> handleUnauthorized(AuthenticationException ex, HttpServletRequest req) {
        // Cria um objeto StandardError para retornar ao cliente
        StandardError err = new StandardError(
                HttpStatus.UNAUTHORIZED.value(),
                "Acesso não autorizado. Verifique suas credenciais.",
                System.currentTimeMillis(),
                req.getRequestURI()
        );

        // Retorna a resposta com código HTTP 401 (Unauthorized)
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(err);
    }
}
