package com.aluracurso.devshotel.Domain.reservation.Validations;

import com.aluracurso.devshotel.Domain.reservation.DataReservation;
import com.aluracurso.devshotel.Domain.reservation.ReservationRepository;
import com.aluracurso.devshotel.Infra.ValidacionExcpetion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidateRevervationActive implements ValidatorReservation{

    @Autowired
    private ReservationRepository repository;

    @Override
    public void validate(DataReservation data) {
        var huespedWithReservation = repository.existsByHuespedIdAndActiveTrue(data.id_Huesped());

        if(huespedWithReservation){
            throw new ValidacionExcpetion("Huesped con una reservacion activa");
        }
    }
}
