package org.msd.ebankingbackend.mappers;

import org.msd.ebankingbackend.dtos.AddressDto;
import org.msd.ebankingbackend.entities.Address;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class AddressMapper {

    public static AddressDto toAddressDto(Address address) {
        AddressDto addressDto = new AddressDto();
        BeanUtils.copyProperties(address,addressDto);
        return  addressDto;
    }

    public static Address toAddress(AddressDto addressDto) {
        Address address = new Address();
        BeanUtils.copyProperties(addressDto,address);
        return address;
    }
}
