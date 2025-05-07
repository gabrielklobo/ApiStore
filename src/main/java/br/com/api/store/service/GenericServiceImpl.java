package br.com.api.store.service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

import br.com.api.store.repository.IGenericRepository;

public abstract class GenericServiceImpl<T, ID> implements IGenericService<T, ID> {

	protected final IGenericRepository<T, ID> repository;

	public GenericServiceImpl(IGenericRepository<T, ID> repository) {
		this.repository = repository;
	}

	@Override
	public List<T> findAll() {
		return repository.findAll();
	}

	@Override
	public Optional<T> findById(ID id) {
		return repository.findById(id);
	}

	@Override
	public T save(T entity) {
		return repository.save(entity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Optional<T> update(T entity) {
		Field id = null;
		try {
			id = entity.getClass().getDeclaredField("id");
			id.setAccessible(true);
			return repository.findById((ID) id.get(entity)).map(o -> repository.save(entity));
		} catch (Exception e) {
			throw new RuntimeException("", e);
		}
	}

	@Override
	public boolean deleteByIdWithReturn(ID id) {
		return repository.deleteByIdWithReturn(id) > 0;
	}

}
