package com.aluracurso.devshotel.Domain.reservation;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    Page<Reservation> findByActiveTrue(Pageable pageable);

    Boolean existsByHuespedIdAndActiveTrue(Long id);
}
