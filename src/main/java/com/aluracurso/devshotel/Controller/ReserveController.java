package com.aluracurso.devshotel.Controller;

import com.aluracurso.devshotel.Domain.reservation.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reserve")
public class ReserveController {

    @Autowired
    private Reserve reserve;

    private final ReservationRepository repository;

    public ReserveController (ReservationRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    @Transactional
    public ResponseEntity reserve(@RequestBody @Valid DataReservation data) {

        var reservation = reserve.reserve(data);
        return ResponseEntity.ok(reservation);
    }
    @GetMapping
    public ResponseEntity<Page<DataListReservation>> getAll(@PageableDefault(size = 5)Pageable pageable){
        reserve.checkReservations();

        return ResponseEntity.ok(repository.findByActiveTrue(pageable).map(DataListReservation::new));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DataResponseReservation> updateReservation(@RequestBody @Valid DataUpdateReservation data) {

        Reservation reservation = repository.getReferenceById(data.id());
        reservation.updateData(data);

        return ResponseEntity.ok(new DataResponseReservation(reservation.getId(), reservation.getHuesped().getFirst_Name(), reservation.getCheck_In(), reservation.getCheck_Out()));
    }
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteReservation(@PathVariable Long id) {
        reserve.checkReservations();

        Reservation reservation = repository.getReferenceById(id);
        reservation.desactivateReservation();

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataReservation> getReservation(@PathVariable Long id) {
        Reservation r = repository.getReferenceById(id);
        var dataReservation = new DataReservation(r.getHuesped().getId(), r.getCheck_In(), r.getCheck_Out(), r.getPrice(), r.getPayment(), r.getActive());

        return ResponseEntity.ok(dataReservation);
    }
}
