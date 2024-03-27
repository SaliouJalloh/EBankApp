package org.msd.ebankingbackend.mappers;

import org.msd.ebankingbackend.dtos.ContactDto;
import org.msd.ebankingbackend.entities.Contact;
import org.springframework.beans.BeanUtils;

public class ContactMapper {

    public static ContactDto toContactDto(Contact contact) {
        ContactDto contactDto = new ContactDto();
        BeanUtils.copyProperties(contact,contactDto);
        return  contactDto;
    }

    public static Contact toContact(ContactDto contactDto) {
        Contact contact = new Contact();
        BeanUtils.copyProperties(contactDto, contact);
        return contact;
    }
}
