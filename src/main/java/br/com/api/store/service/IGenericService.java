package br.com.api.store.service;

import java.util.List;
import java.util.Optional;

public interface IGenericService<T, ID> {

	List<T> findAll();

	Optional<T> findById(ID id);

	T save(T entity);

	Optional<T> update(T entity);
	
	boolean deleteByIdWithReturn(ID id);	
}
