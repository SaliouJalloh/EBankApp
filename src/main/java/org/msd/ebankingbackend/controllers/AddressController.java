package org.msd.ebankingbackend.controllers;


import lombok.RequiredArgsConstructor;
import org.msd.ebankingbackend.dtos.AddressDto;
import org.msd.ebankingbackend.services.AddressService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService service;
    private static final String ID = "/{addressId}";

    @PostMapping
    public ResponseEntity<AddressDto> save(@RequestBody AddressDto addressDto) {
        return ResponseEntity.ok(service.save(addressDto));
    }

    @GetMapping
    public ResponseEntity<List<AddressDto>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping(ID)
    public ResponseEntity<AddressDto> findById(@PathVariable Long addressId) {
        return ResponseEntity.ok(service.findById(addressId));
    }

    @DeleteMapping(ID)
    public ResponseEntity<Void> delete(@PathVariable Long addressId) {
        service.delete(addressId);
        return ResponseEntity.accepted().build();
    }
}
