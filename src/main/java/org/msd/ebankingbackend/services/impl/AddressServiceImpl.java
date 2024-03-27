package org.msd.ebankingbackend.services.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.msd.ebankingbackend.dtos.AddressDto;
import org.msd.ebankingbackend.entities.Address;
import org.msd.ebankingbackend.mappers.AddressMapper;
import org.msd.ebankingbackend.repositories.AddressRepository;
import org.msd.ebankingbackend.services.AddressService;
import org.msd.ebankingbackend.validators.EntityValidatorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

  private final AddressRepository repository;
  private final EntityValidatorService<AddressDto> validator;

  @Override
  public AddressDto save(AddressDto dto) {
    validator.validateInput(dto);
    Address address = AddressMapper.toAddress(dto);
    Address savedAddress = repository.save(address);
    return AddressMapper.toAddressDto(savedAddress);
  }

  @Override
  public List<AddressDto> findAll() {
    return repository.findAll()
        .stream()
        .map(AddressMapper::toAddressDto)
        .collect(Collectors.toList());
  }

  @Override
  public AddressDto findById(Long id) {
    return repository.findById(id)
        .map(AddressMapper::toAddressDto)
        .orElseThrow(() -> new EntityNotFoundException("No address found with the ID : " + id));
  }

  @Override
  public void delete(Long id) {
    // todo check delete
    repository.deleteById(id);
  }
}
