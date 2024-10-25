package com.aluracurso.devshotel.Domain.huesped.Validations;

import com.aluracurso.devshotel.Domain.huesped.DataHuesped;
import com.aluracurso.devshotel.Domain.huesped.Huesped;
import com.aluracurso.devshotel.Domain.huesped.HuespedRepository;
import com.aluracurso.devshotel.Infra.ValidacionExcpetion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ValidateSameNames implements ValidatorHuesped{

    @Autowired
    private HuespedRepository repository;

    @Override
    public void validate(DataHuesped data) {
        List<Huesped> huespedList = repository.findAll();

        huespedList.forEach(h -> {
            if (h.getFirst_Name().equals(data.first_Name()) && h.getLast_Name().equals(data.last_Name())) {
                throw new ValidacionExcpetion("Huesped ya existente");
            }
        });
    }
}
