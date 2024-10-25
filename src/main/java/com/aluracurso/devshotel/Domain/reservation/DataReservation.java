package com.aluracurso.devshotel.Domain.reservation;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Positive;
import org.antlr.v4.runtime.misc.NotNull;
import jakarta.validation.Valid;

import java.time.LocalDateTime;

public record DataReservation(
        @NotNull
        @Valid
        Long id_Huesped,
        @NotNull
        @Future
        LocalDateTime check_In,
        @NotNull
        @Future
        LocalDateTime check_Out,
        @NotNull
        @Positive
        double price,
        @NotNull
        Payment payment,
        Boolean active
) {

}
