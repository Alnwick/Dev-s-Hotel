package com.aluracurso.devshotel.Domain.huesped;

import com.aluracurso.devshotel.Domain.address.Address;
import jakarta.validation.constraints.NotNull;

public record   DataUpdateHuesped(
        @NotNull
        Long id,
        String email,
        String phone,
        Address address
) {
}
