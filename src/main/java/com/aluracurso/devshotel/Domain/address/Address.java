package com.aluracurso.devshotel.Domain.address;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    private String street;
    private String z_Code;
    private String city;
    private String number;
    private String complement;

    public Address(DataAddress d) {
        this.street = d.street();
        this.z_Code = d.z_Code();
        this.city = d.city();
        this.number = d.number();
        this.complement = d.complement();
    }

    public Address updateAddress(Address address) {
        this.street = address.street;
        this.z_Code = address.z_Code;
        this.city = address.city;
        this.number = address.number;
        this.complement = address.complement;
        return this;
    }
}
