package com.aluracurso.devshotel.Domain.address;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DataAddress(
        @NotBlank
        String street,
        @NotBlank
        @Pattern(regexp = "\\d{5}")
        String z_Code,
        @NotBlank
        String city,
        @NotBlank
        String number,
        @NotBlank
        String complement ) {
}
