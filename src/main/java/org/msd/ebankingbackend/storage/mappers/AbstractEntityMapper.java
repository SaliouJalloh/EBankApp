package org.msd.ebankingbackend.storage.mappers;

public interface AbstractEntityMapper<M, E> {

    M toModel(E entity);

    E toEntity(M model);
}
