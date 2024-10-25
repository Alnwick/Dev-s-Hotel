package com.aluracurso.devshotel.Domain.huesped;

public record DataListHuesped(
        Long id,
        String first_Name,
        String last_Name,
        String email ) {

    public DataListHuesped(Huesped huesped) {
        this(huesped.getId(), huesped.getFirst_Name(), huesped.getLast_Name(), huesped.getEmail());
    }
}
