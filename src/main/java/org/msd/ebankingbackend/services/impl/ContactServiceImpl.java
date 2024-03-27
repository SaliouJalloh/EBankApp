package org.msd.ebankingbackend.services.impl;


import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.msd.ebankingbackend.dtos.ContactDto;
import org.msd.ebankingbackend.entities.Contact;
import org.msd.ebankingbackend.mappers.ContactMapper;
import org.msd.ebankingbackend.repositories.ContactRepository;
import org.msd.ebankingbackend.services.ContactService;
import org.msd.ebankingbackend.validators.EntityValidatorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {

  private final ContactRepository repository;
  private final EntityValidatorService<ContactDto> validator;

  @Override
  public ContactDto save(ContactDto dto) {
    validator.validateInput(dto);
    Contact contact = ContactMapper.toContact(dto);
    Contact saved = repository.save(contact);
    return ContactMapper.toContactDto(saved);
  }

  @Override
  public List<ContactDto> findAll() {
    return repository.findAll()
        .stream()
        .map(ContactMapper::toContactDto)
        .collect(Collectors.toList());
  }

  @Override
  public ContactDto findById(Long id) {
    return repository.findById(id)
        .map(ContactMapper::toContactDto)
        .orElseThrow(() -> new EntityNotFoundException("No contact was found with the ID :" + id));
  }

  @Override
  public void delete(Long id) {
    // todo check delete
    repository.deleteById(id);
  }
}
