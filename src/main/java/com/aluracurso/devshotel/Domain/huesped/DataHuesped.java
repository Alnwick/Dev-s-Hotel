package com.aluracurso.devshotel.Domain.huesped;

import com.aluracurso.devshotel.Domain.address.DataAddress;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DataHuesped(
        @NotBlank
        String first_Name,
        @NotBlank
        String last_Name,
        @NotBlank
        @Email
        String email,
        @NotBlank
        String phone,
        @NotNull
        @Valid
        DataAddress address
) {
}
