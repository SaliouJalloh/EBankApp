package org.msd.ebankingbackend.services;

import java.util.List;

public interface AbstractService<T> {

  T save(T dto);

  List<T> findAll();

  T findById(Long id);

  void delete(Long id);

}