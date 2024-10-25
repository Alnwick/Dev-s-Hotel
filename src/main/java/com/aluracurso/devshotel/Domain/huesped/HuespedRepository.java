package com.aluracurso.devshotel.Domain.huesped;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HuespedRepository extends JpaRepository<Huesped, Long> {

    Page<Huesped> findByActiveTrue(Pageable pageable);
}
