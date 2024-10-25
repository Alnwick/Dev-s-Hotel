package com.aluracurso.devshotel.Domain.reservation;

import com.aluracurso.devshotel.Domain.huesped.HuespedRepository;
import com.aluracurso.devshotel.Domain.reservation.Validations.ValidatorReservation;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class Reserve {

    @Autowired
    private HuespedRepository huespedRepository;

    @Autowired
    private ReservationRepository resRepository;

    @Autowired
    private List<ValidatorReservation> validators;

    public DataResponseReservation reserve(DataReservation data){
        if(!huespedRepository.existsById(data.id_Huesped())){
            throw new ValidationException("Huesped inexistente");
        }

        validators.forEach(v -> v.validate(data));

        var huesped = huespedRepository.findById(data.id_Huesped()).get();
        var reservation = new Reservation(null, huesped, data.check_In(), data.check_Out(), data.price(), data.payment(), true);
        resRepository.save(reservation);

        return new DataResponseReservation(reservation);
    }

    public void checkReservations(){
        List<Reservation> reservations = resRepository.findAll();

        reservations.forEach(r -> {
            if (r.getCheck_Out().isAfter(LocalDateTime.now())) {
                r.getActive().equals(false);
            }
        });
    }
}
