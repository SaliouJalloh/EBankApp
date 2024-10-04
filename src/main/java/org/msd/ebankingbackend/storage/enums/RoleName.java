package org.msd.ebankingbackend.storage.enums;

import lombok.Getter;

@Getter
public enum RoleName {

    ADMIN("ADMIN", "User with administrative privileges and full access to the system"),
    USER("PASSENGER", "User who provides services to bank");

    private final String name;

    private final String description;

    RoleName(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
