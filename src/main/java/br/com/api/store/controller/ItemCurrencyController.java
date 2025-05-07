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

import br.com.api.store.dto.ItemCurrencyDTO;
import br.com.api.store.model.ItemCurrency;
import br.com.api.store.service.ItemCurrencyService;

@RestController
@RequestMapping("/item-currency")
public class ItemCurrencyController {

	private ItemCurrencyService service;

	public ItemCurrencyController(ItemCurrencyService service) {
		this.service = service;
	}

	@GetMapping
	public ResponseEntity<List<ItemCurrency>> getAll() {
		return ResponseEntity.ok(service.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<ItemCurrency> get(@PathVariable Long id) {
		return service.findById(id).map(x -> ResponseEntity.ok().body(x))
				.orElseGet(() -> ResponseEntity.badRequest().build());
	}

	@PostMapping
	public ResponseEntity<ItemCurrency> save(@RequestBody ItemCurrencyDTO itemCurrencyDTO) {
		try {
			ItemCurrency itemResponse = service.save(itemCurrencyDTO);

			return ResponseEntity.status(201).body(itemResponse);
		} catch (NoSuchElementException nsee) {
			return ResponseEntity.badRequest().build();
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@PutMapping
	public ResponseEntity<ItemCurrency> update(@RequestBody ItemCurrencyDTO itemCurrencyDTO) {
		return service.update(itemCurrencyDTO).map(x -> ResponseEntity.status(201).body(x))
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
