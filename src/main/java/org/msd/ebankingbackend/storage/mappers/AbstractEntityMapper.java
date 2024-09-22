package org.msd.ebankingbackend.storage.mappers;

import org.mapstruct.MappingTarget;

public interface AbstractEntityMapper<M, E> {

    M toModel(E entity);

    E toEntity(M model);

    M populateModel(@MappingTarget M model, E entity);
}
