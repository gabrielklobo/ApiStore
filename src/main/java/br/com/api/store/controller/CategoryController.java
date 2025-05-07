package br.com.api.store.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.store.model.Category;
import br.com.api.store.service.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {

	private CategoryService service;

	public CategoryController(CategoryService service) {
		this.service = service;
	}

	@GetMapping
	public ResponseEntity<List<Category>> getAll() {
		return ResponseEntity.ok(service.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Category> get(@PathVariable Long id) {
		return service.findById(id).map(x -> ResponseEntity.ok().body(x))
				.orElseGet(() -> ResponseEntity.badRequest().build());
	}

	@PostMapping
	public ResponseEntity<Category> save(@RequestBody Category category) {
		try {
			Category categoryResponse = service.save(category);

			return ResponseEntity.status(201).body(categoryResponse);
		} catch (NoSuchElementException nsee) {
			return ResponseEntity.badRequest().build();
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@PutMapping
	public ResponseEntity<Category> update(@RequestBody Category category) {
		return service.update(category).map(x -> ResponseEntity.status(201).body(x))
				.orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		if (service.deleteByIdWithReturn(id)) {
			return ResponseEntity.ok().build();
		}

		return ResponseEntity.notFound().build();
	}

}
