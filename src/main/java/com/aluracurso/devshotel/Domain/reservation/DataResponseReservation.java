package com.aluracurso.devshotel.Domain.reservation;

import java.time.LocalDateTime;

public record DataResponseReservation(
        Long id,
        String nombre_Huesped,
        LocalDateTime check_In,
        LocalDateTime check_Out
) {
    public DataResponseReservation (Reservation r){
        this(r.getId(), r.getHuesped().getFirst_Name(), r.getCheck_In(), r.getCheck_Out());
    }
}
