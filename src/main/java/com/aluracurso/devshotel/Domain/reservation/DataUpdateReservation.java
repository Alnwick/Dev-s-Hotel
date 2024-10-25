package com.aluracurso.devshotel.Domain.reservation;

import jakarta.validation.constraints.NotNull;

public record DataUpdateReservation(
        @NotNull
        Long id,
        double price,
        Payment payment
) {
}
