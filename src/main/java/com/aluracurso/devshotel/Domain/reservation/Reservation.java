package com.aluracurso.devshotel.Domain.reservation;

import com.aluracurso.devshotel.Domain.huesped.Huesped;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "reservas")
@Entity(name  = "Reserva")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")

public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "huesped_id")
    private Huesped huesped;

    private LocalDateTime check_In;
    private LocalDateTime check_Out;
    private double price;
    @Enumerated(EnumType.STRING)
    private Payment payment;
    private Boolean active;

    public void updateData(DataUpdateReservation d){
        if(d.payment() != null){
            this.payment = d.payment();
        }
        if(d.price() != this.price){
            this.price = d.price();
        }
    }

    public void desactivateReservation(){
        this.active = false;
    }
}
