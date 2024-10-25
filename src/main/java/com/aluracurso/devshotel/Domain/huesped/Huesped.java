package com.aluracurso.devshotel.Domain.huesped;

import com.aluracurso.devshotel.Domain.address.Address;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "huespedes")
@Entity(name = "Huesped")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Huesped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String first_Name;
    private String last_Name;
    private String email;
    private String phone;
    @Embedded
    private Address address;
    private Boolean active;

    public Huesped(DataHuesped d){
        this.first_Name = d.first_Name();
        this.last_Name = d.last_Name();
        this.email = d.email();
        this.phone = d.phone();
        this.active = true;
        this.address = new Address(d.address());
    }

    public void updateData(DataUpdateHuesped d){
        if(d.email() != null){
            this.email = d.email();
        }
        if(d.phone() != null){
            this.phone = d.phone();
        }
        if(d.address() != null){
            this.address = address.updateAddress(d.address());
        }
    }

    public void desactivateHuesped(){
        this.active = false;
    }
}
