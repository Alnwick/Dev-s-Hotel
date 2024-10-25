package com.aluracurso.devshotel.Infra;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class ErrorHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tratarError404(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarError400(MethodArgumentNotValidException e){

        var errores =e.getFieldErrors().stream().map(DatosError :: new).toList();
        return ResponseEntity.badRequest().body(errores);
    }
    @ExceptionHandler(ValidacionExcpetion.class)
    public ResponseEntity tratarErrorDeValidacion(ValidacionExcpetion e){

        return ResponseEntity.badRequest().body(e.getMessage());
    }

    private record DatosError(String campo, String error){
        public DatosError(FieldError error){
            this(error.getField(), error.getDefaultMessage());
        }
    }
}
