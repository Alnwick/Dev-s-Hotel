package com.aluracurso.devshotel.Domain.reservation.Validations;

import com.aluracurso.devshotel.Domain.reservation.DataReservation;
import com.aluracurso.devshotel.Infra.ValidacionExcpetion;
import org.springframework.stereotype.Component;

@Component
public class ValidateFechas implements ValidatorReservation{

    @Override
    public void validate (DataReservation data){
        var check_In = data.check_In();
        var check_Out = data.check_Out();

        System.out.println(check_Out);

        if(check_Out.isBefore(check_In)){
            throw new ValidacionExcpetion("Fecha de Check_Out incorrecta");
        }
    }
}
