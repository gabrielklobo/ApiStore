package br.com.api.store.service;

import org.springframework.stereotype.Service;

import br.com.api.store.model.Category;
import br.com.api.store.repository.CategoryRepository;

@Service
public class CategoryService extends GenericServiceImpl<Category, Long>{

	public CategoryService(CategoryRepository repository) {
		super(repository);
	}
}
