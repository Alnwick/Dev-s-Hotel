package com.aluracurso.devshotel.Infra;

public class ValidacionExcpetion extends RuntimeException{
    public ValidacionExcpetion(String mensaje) {
        super(mensaje);
    }
}
