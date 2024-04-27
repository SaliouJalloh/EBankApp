package org.msd.ebankingbackend.controllers;


import lombok.RequiredArgsConstructor;
import org.msd.ebankingbackend.dtos.ContactDto;
import org.msd.ebankingbackend.services.ContactService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/contacts")
@RequiredArgsConstructor
public class ContactController {

    private final ContactService service;
    private static final String ID = "/{contactId}";

    @PostMapping
    public ResponseEntity<ContactDto> save(@RequestBody ContactDto contactDto) {
        return ResponseEntity.ok(service.save(contactDto));
    }

    @GetMapping
    public ResponseEntity<List<ContactDto>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping(ID)
    public ResponseEntity<ContactDto> findById(@PathVariable Long contactId) {
        return ResponseEntity.ok(service.findById(contactId));
    }

    @DeleteMapping(ID)
    public ResponseEntity<Void> delete(@PathVariable Long contactId) {
        service.delete(contactId);
        return ResponseEntity.accepted().build();
    }

}
