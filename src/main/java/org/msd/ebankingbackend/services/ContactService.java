package org.msd.ebankingbackend.services;


import org.msd.ebankingbackend.dtos.ContactDto;

import java.util.List;

public interface ContactService extends AbstractService<ContactDto> {
    List<ContactDto> findAllByCustomerId(Long userId);
}
