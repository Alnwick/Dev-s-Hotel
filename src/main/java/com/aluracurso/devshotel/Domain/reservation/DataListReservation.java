package com.aluracurso.devshotel.Domain.reservation;

import java.time.LocalDateTime;

public record DataListReservation(
        Long id,
        String first_Name,
        LocalDateTime check_In,
        LocalDateTime check_Out
) {
    public DataListReservation (Reservation r){
        this(r.getId(), r.getHuesped().getFirst_Name(), r.getCheck_In(), r.getCheck_Out());
    }
}
