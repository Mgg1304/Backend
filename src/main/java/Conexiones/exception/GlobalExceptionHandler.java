package Conexiones.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ReservaInvalidaException.class)
    public ResponseEntity<String> manejarReservaInvalida(ReservaInvalidaException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(UsuarioNoEncontradoException.class)
    public ResponseEntity<String> manejarUsuarioNoEncontrado(UsuarioNoEncontradoException ex) {
        return ResponseEntity.status(404).body(ex.getMessage());
    }
}

