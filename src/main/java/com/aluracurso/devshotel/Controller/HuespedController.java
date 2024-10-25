package com.aluracurso.devshotel.Controller;

import com.aluracurso.devshotel.Domain.address.DataAddress;
import com.aluracurso.devshotel.Domain.huesped.*;
import com.aluracurso.devshotel.Domain.huesped.Validations.ValidatorHuesped;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/huesped")
public class HuespedController {

    private final HuespedRepository repository;

    @Autowired
    private List<ValidatorHuesped> validators;

    public HuespedController(HuespedRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DataResponseHuesped> register (@RequestBody @Valid DataHuesped data, UriComponentsBuilder uriBuilder) {
        validators.forEach(v -> v.validate(data));

        Huesped huesped = repository.save(new Huesped(data));

        DataResponseHuesped dataResponse = new DataResponseHuesped(huesped.getId(), huesped.getFirst_Name(), huesped.getEmail());
        URI uri = uriBuilder.path("/huesped/{id}").buildAndExpand(huesped.getId()).toUri();

        return ResponseEntity.created(uri).body(dataResponse);
    }

    @GetMapping
    public ResponseEntity<Page<DataListHuesped>> getAll(@PageableDefault(size = 5) Pageable pageable) {
        return ResponseEntity.ok(repository.findByActiveTrue(pageable).map(DataListHuesped::new));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DataResponseHuesped> updateHuesped(@RequestBody @Valid DataUpdateHuesped data) {

        Huesped huesped = repository.getReferenceById(data.id());
        huesped.updateData(data);

        return ResponseEntity.ok(new DataResponseHuesped(huesped.getId(), huesped.getFirst_Name(), huesped.getEmail()));
    }
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteHuesped(@PathVariable Long id) {

        Huesped huesped = repository.getReferenceById(id);
        huesped.desactivateHuesped();

        return ResponseEntity.noContent().build();
    }
    @GetMapping("/{id}")
    public ResponseEntity<DataHuesped> getHuesped(@PathVariable Long id) {
        Huesped h = repository.getReferenceById(id);
        var dataHuesped = new DataHuesped(h.getFirst_Name(), h.getEmail(), h.getEmail(), h.getPhone(),
                new DataAddress(h.getAddress().getStreet(), h.getAddress().getZ_Code(), h.getAddress().getCity(),
                h.getAddress().getNumber(), h.getAddress().getComplement()));

        return ResponseEntity.ok(dataHuesped);
    }
}
